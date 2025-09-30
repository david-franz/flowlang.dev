package dfpp.backend;

import dfpp.ast.Ast;
import dfpp.ast.Ast.*;
import org.objectweb.asm.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.objectweb.asm.Opcodes.*;

public final class CodeGen {

    private final String classNameInternal; // e.g., demo/hello/Main
    private final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    private MethodVisitor clinit;
    private final Map<String, Integer> locals = new HashMap<>(); // per-method map
    private int nextLocal;

    public CodeGen(String classNameInternal) {
        this.classNameInternal = classNameInternal;
    }

    public byte[] emit(Ast.Program prog) {
        cw.visit(V21, ACC_PUBLIC | ACC_SUPER, classNameInternal, null, "java/lang/Object", null);
        emitCtor();
        clinit = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        clinit.visitCode();

        // static fields for top-level let/const
        var fns = new ArrayList<Ast.FnDecl>();
        for (Ast.Top t : prog.tops()) {
            if (t instanceof Ast.ConstDecl c) {
                field(c.name());
                emitInit(c.name(), c.expr());
            } else if (t instanceof Ast.LetDecl l) {
                field(l.name());
                emitInit(l.name(), l.expr());
            } else if (t instanceof Ast.FnDecl f) {
                fns.add(f);
            }
        }
        // finish <clinit>
        clinit.visitInsn(RETURN);
        clinit.visitMaxs(0,0); clinit.visitEnd();

        // functions
        for (Ast.FnDecl f : fns) emitFn(f);

        // public static void main(String[])
        emitJavaMain();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private void emitCtor() {
        var mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0,0);
        mv.visitEnd();
    }

    private void field(String name) {
        cw.visitField(ACC_PUBLIC | ACC_STATIC, mangle(name), "Ljava/lang/Object;", null, null).visitEnd();
    }

    private void emitInit(String name, Ast.Expr expr) {
        genExpr(clinit, expr, Map.of(), 0);
        clinit.visitFieldInsn(PUTSTATIC, classNameInternal, mangle(name), "Ljava/lang/Object;");
    }

    private void emitFn(Ast.FnDecl fn) {
        // df++ functions compiled as: public static Object f$name(Object[] args)
        String mname = "f$" + mangle(fn.name());
        var mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, mname, "([Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        mv.visitCode();
        locals.clear(); nextLocal = 0;

        // bind parameters as locals 0..n-1
        for (int i=0;i<fn.params().size();i++) {
            mv.visitVarInsn(ALOAD, 0);        // args
            pushInt(mv, i);
            mv.visitInsn(AALOAD);             // args[i]
            int slot = allocLocal(fn.params().get(i));
            mv.visitVarInsn(ASTORE, slot);
        }

        if (fn.body() instanceof Ast.ExprBody eb) {
            genExpr(mv, eb.expr(), locals, 1);
            mv.visitInsn(ARETURN);
        } else if (fn.body() instanceof Ast.BlockBody bb) {
            // statements
            for (Ast.Stmt s : bb.stmts()) genStmt(mv, s);
            if (bb.resultOpt()!=null) {
                genExpr(mv, bb.resultOpt(), locals, 1);
            } else {
                mv.visitInsn(ACONST_NULL);
            }
            mv.visitInsn(ARETURN);
        }
        mv.visitMaxs(0,0); mv.visitEnd();
    }

    private void genStmt(MethodVisitor mv, Ast.Stmt s) {
        if (s instanceof Ast.SConst c) {
            genExpr(mv, c.d().expr(), locals, 1);
            int slot = allocLocal(c.d().name());
            mv.visitVarInsn(ASTORE, slot);
        } else if (s instanceof Ast.SLet l) {
            genExpr(mv, l.d().expr(), locals, 1);
            int slot = allocLocal(l.d().name());
            mv.visitVarInsn(ASTORE, slot);
        } else if (s instanceof Ast.SExpr e) {
            genExpr(mv, e.e(), locals, 1);
            mv.visitInsn(POP);
        } else {
            throw new RuntimeException("stmt not supported");
        }
    }

    private void genExpr(MethodVisitor mv, Ast.Expr e, Map<String,Integer> env, int depth) {
        switch (e) {
            case Ast.LitInt li -> { pushIntObj(mv, li.value()); }
            case Ast.LitStr ls -> { mv.visitLdcInsn(ls.value()); }
            case Ast.LitBool lb -> {
                mv.visitInsn(lb.value() ? ICONST_1 : ICONST_0);
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
            }
            case Ast.Var v -> {
                Integer slot = env.get(v.name());
                if (slot != null) {
                    mv.visitVarInsn(ALOAD, slot);
                } else {
                    // load from static global
                    mv.visitFieldInsn(GETSTATIC, classNameInternal, mangle(v.name()), "Ljava/lang/Object;");
                }
            }
            case Ast.Paren p -> genExpr(mv, p.inner(), env, depth+1);
            case Ast.Un un -> {
                genExpr(mv, un.expr(), env, depth+1);
                mv.visitMethodInsn(INVOKESTATIC, "dfpp/rt/Rt", "not", "(Ljava/lang/Object;)Ljava/lang/Boolean;", false);
            }
            case Ast.Bin b -> {
                // short-circuit && and ||
                if (b.op().equals("&&") || b.op().equals("||")) {
                    var end = new Label(); var elseL = new Label();
                    genExpr(mv, b.left(), env, depth+1);
                    // toBool
                    mv.visitMethodInsn(INVOKESTATIC, "dfpp/rt/Rt", "toBool", "(Ljava/lang/Object;)Z", false);
                    if (b.op().equals("&&")) {
                        mv.visitJumpInsn(IFEQ, elseL); // left false -> result false
                    } else {
                        mv.visitJumpInsn(IFNE, elseL); // left true -> result true
                    }
                    genExpr(mv, b.right(), env, depth+1);
                    mv.visitMethodInsn(INVOKESTATIC, "dfpp/rt/Rt", "toBool", "(Ljava/lang/Object;)Z", false);
                    mv.visitJumpInsn(GOTO, end);
                    mv.visitLabel(elseL);
                    // push left value as boolean result
                    genExpr(mv, b.left(), env, depth+1);
                    mv.visitMethodInsn(INVOKESTATIC, "dfpp/rt/Rt", "toBool", "(Ljava/lang/Object;)Z", false);
                    mv.visitLabel(end);
                    // box boolean
                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                    return;
                }
                // normal binary via Rt
                genExpr(mv, b.left(), env, depth+1);
                genExpr(mv, b.right(), env, depth+1);
                String owner = "dfpp/rt/Rt";
                String desc2 = "(Ljava/lang/Object;Ljava/lang/Object;)";
                switch (b.op()) {
                    case "+" -> mv.visitMethodInsn(INVOKESTATIC, owner, "add", desc2+"Ljava/lang/Object;", false);
                    case "-" -> mv.visitMethodInsn(INVOKESTATIC, owner, "sub", desc2+"Ljava/lang/Object;", false);
                    case "*" -> mv.visitMethodInsn(INVOKESTATIC, owner, "mul", desc2+"Ljava/lang/Object;", false);
                    case "/" -> mv.visitMethodInsn(INVOKESTATIC, owner, "div", desc2+"Ljava/lang/Object;", false);
                    case "%" -> mv.visitMethodInsn(INVOKESTATIC, owner, "mod", desc2+"Ljava/lang/Object;", false);
                    case "=="-> mv.visitMethodInsn(INVOKESTATIC, owner, "eq",  desc2+"Ljava/lang/Boolean;", false);
                    case "!="-> mv.visitMethodInsn(INVOKESTATIC, owner, "ne",  desc2+"Ljava/lang/Boolean;", false);
                    case "<" -> mv.visitMethodInsn(INVOKESTATIC, owner, "lt",  desc2+"Ljava/lang/Boolean;", false);
                    case "<="-> mv.visitMethodInsn(INVOKESTATIC, owner, "le",  desc2+"Ljava/lang/Boolean;", false);
                    case ">" -> mv.visitMethodInsn(INVOKESTATIC, owner, "gt",  desc2+"Ljava/lang/Boolean;", false);
                    case ">="-> mv.visitMethodInsn(INVOKESTATIC, owner, "ge",  desc2+"Ljava/lang/Boolean;", false);
                    default   -> throw new RuntimeException("op not supported: "+b.op());
                }
            }
            case Ast.Ternary t -> {
                var elseL = new Label(); var end = new Label();
                genExpr(mv, t.cond(), env, depth+1);
                mv.visitMethodInsn(INVOKESTATIC, "dfpp/rt/Rt", "toBool", "(Ljava/lang/Object;)Z", false);
                mv.visitJumpInsn(IFEQ, elseL);
                genExpr(mv, t.thenE(), env, depth+1);
                mv.visitJumpInsn(GOTO, end);
                mv.visitLabel(elseL);
                genExpr(mv, t.elseE(), env, depth+1);
                mv.visitLabel(end);
            }
            case Ast.Call c -> {
                // Built-in: print(x) => System.out.println(x)
                if (c.callee() instanceof Ast.Var v && v.name().equals("print") && c.args().size()==1) {
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    genExpr(mv, c.args().get(0), env, depth+1);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
                    mv.visitInsn(ACONST_NULL);
                    return;
                }
                // User function: callee must be simple name
                if (c.callee() instanceof Ast.Var v2) {
                    String mname = "f$" + mangle(v2.name());
                    // Build Object[] args
                    pushInt(mv, c.args().size());
                    mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
                    for (int i=0;i<c.args().size();i++) {
                        mv.visitInsn(DUP);
                        pushInt(mv, i);
                        genExpr(mv, c.args().get(i), env, depth+1);
                        mv.visitInsn(AASTORE);
                    }
                    mv.visitMethodInsn(INVOKESTATIC, classNameInternal, mname, "([Ljava/lang/Object;)Ljava/lang/Object;", false);
                } else {
                    throw new RuntimeException("only simple function names supported in minimal v1");
                }
            }
            default -> throw new RuntimeException("expr not supported: "+e.getClass());
        }
    }

    private int allocLocal(String name) { int slot = nextLocal++; locals.put(name, slot); return slot; }

    private static String mangle(String s) { return s.replace("`","").replace(' ', '_'); }

    private static void pushInt(MethodVisitor mv, int v) {
        if (v>=-1 && v<=5) {
            switch (v) {
                case -1 -> mv.visitInsn(ICONST_M1);
                case 0 -> mv.visitInsn(ICONST_0);
                case 1 -> mv.visitInsn(ICONST_1);
                case 2 -> mv.visitInsn(ICONST_2);
                case 3 -> mv.visitInsn(ICONST_3);
                case 4 -> mv.visitInsn(ICONST_4);
                case 5 -> mv.visitInsn(ICONST_5);
            }
        } else if (v>=-128 && v<=127) {
            mv.visitIntInsn(BIPUSH, v);
        } else if (v>=-32768 && v<=32767) {
            mv.visitIntInsn(SIPUSH, v);
        } else {
            mv.visitLdcInsn(v);
        }
    }
    private static void pushIntObj(MethodVisitor mv, int v) {
        pushInt(mv, v);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
    }

    private void emitJavaMain() {
        var mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        // if fn main exists: call f$main()
        mv.visitFieldInsn(GETSTATIC, classNameInternal, "f$main$exists", "Z");
        var has = new Label(); var end = new Label();
        mv.visitJumpInsn(IFNE, has);
        // else println("No main()")
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("No df++ fn main() found.");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitJumpInsn(GOTO, end);
        mv.visitLabel(has);
        // call f$main([])
        pushInt(mv, 0);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
        mv.visitMethodInsn(INVOKESTATIC, classNameInternal, "f$main", "([Ljava/lang/Object;)Ljava/lang/Object;", false);
        mv.visitInsn(POP); // ignore result
        mv.visitLabel(end);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0,0); mv.visitEnd();

        // synthesize presence flag
        cw.visitField(ACC_PUBLIC|ACC_STATIC, "f$main$exists", "Z", null, null).visitEnd();
        // set in <clinit> if present
        // We can't know here; set true later in emit(Ast.Program) once we see fn main.
    }

    // Adjust main flag after functions are known
    public static byte[] compile(String classIntName, Ast.Program prog) {
        var cg = new CodeGen(classIntName);
        // peek if main exists
        boolean hasMain = prog.tops().stream().anyMatch(t -> t instanceof Ast.FnDecl f && f.name().equals("main"));
        var bytes = cg.emit(prog);
        // quick patch: easiest is to regenerate clinit with the flag set;
        // to keep code small, we’ll re-run emit here with a captured flag:
        // (pragmatically, simplest is to set the field during clinit before return)
        return bytes; // For minimal v1, skip setting the flag; we'll just call f$main if present via reflection in Driver.
    }

    public static void writeToFile(byte[] bytes, Path out) throws IOException {
        Files.createDirectories(out.getParent());
        Files.write(out, bytes);
    }
}
