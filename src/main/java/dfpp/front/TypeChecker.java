package dfpp.front;

import dfpp.ast.Ast;
import dfpp.ast.Ast.*;
import java.util.*;

/**
 * A simple static type checker.
 *
 * v1 supported only primitive types (Int, String, Bool, Unit).
 * This update introduces minimal, structural support for a few generic and
 * composite types used by the language surface today:
 *  - List<T>: element type captured and propagated through literals and indexing
 *  - Record { f1: T1, f2: T2, ... }: field types captured at literal sites and through access
 *  - Set<T> and Map<K,V>: recognized in annotations; carried through but not yet used by expressions
 *
 * The goal is not a full-blown parametric polymorphic typer, but enough structure
 * to keep type information flowing for collections and records, and to validate
 * obvious mismatches.
 */
public final class TypeChecker {
    public static class TypeException extends RuntimeException {
        public TypeException(String message) {
            super(message);
        }
    }

    // Type representation (structural). Keep equals/hashCode semantics for comparisons.
    private sealed interface Type permits TInt, TString, TBool, TUnit, TUnknown, TList, TSet, TMap, TRecord, TTuple {}
    private static final class TInt implements Type { @Override public boolean equals(Object o){return o instanceof TInt;} @Override public int hashCode(){return 1;} @Override public String toString(){return "Int";} }
    private static final class TString implements Type { @Override public boolean equals(Object o){return o instanceof TString;} @Override public int hashCode(){return 2;} @Override public String toString(){return "String";} }
    private static final class TBool implements Type { @Override public boolean equals(Object o){return o instanceof TBool;} @Override public int hashCode(){return 3;} @Override public String toString(){return "Bool";} }
    private static final class TUnit implements Type { @Override public boolean equals(Object o){return o instanceof TUnit;} @Override public int hashCode(){return 4;} @Override public String toString(){return "Unit";} }
    private static final class TUnknown implements Type { @Override public boolean equals(Object o){return o instanceof TUnknown;} @Override public int hashCode(){return 5;} @Override public String toString(){return "Unknown";} }
    private static final class TList implements Type {
        final Type elem;
        TList(Type elem){ this.elem = elem; }
        @Override public boolean equals(Object o){ return (o instanceof TList t) && Objects.equals(elem, t.elem); }
        @Override public int hashCode(){ return 31 * 7 + elem.hashCode(); }
        @Override public String toString(){ return "List<"+elem+">"; }
    }
    private static final class TSet implements Type {
        final Type elem;
        TSet(Type elem){ this.elem = elem; }
        @Override public boolean equals(Object o){ return (o instanceof TSet t) && Objects.equals(elem, t.elem); }
        @Override public int hashCode(){ return 31 * 11 + elem.hashCode(); }
        @Override public String toString(){ return "Set<"+elem+">"; }
    }
    private static final class TMap implements Type {
        final Type key, val;
        TMap(Type key, Type val){ this.key = key; this.val = val; }
        @Override public boolean equals(Object o){ return (o instanceof TMap t) && Objects.equals(key,t.key) && Objects.equals(val,t.val); }
        @Override public int hashCode(){ return 31*13 + key.hashCode()*17 + val.hashCode(); }
        @Override public String toString(){ return "Map<"+key+","+val+">"; }
    }
    private static final class TRecord implements Type {
        final Map<String, Type> fields; // insertion-ordered for stable msgs
        TRecord(Map<String, Type> fields){ this.fields = new LinkedHashMap<>(fields); }
        @Override public boolean equals(Object o){ return (o instanceof TRecord t) && Objects.equals(fields, t.fields); }
        @Override public int hashCode(){ return Objects.hash(fields); }
        @Override public String toString(){ return "{" + String.join(", ", fields.entrySet().stream().map(e -> e.getKey()+": "+e.getValue()).toList()) + "}"; }
    }

    
    private static final class TTuple implements Type {
        final java.util.List<Type> elems;
        TTuple(java.util.List<Type> elems){ this.elems = java.util.List.copyOf(elems); }
        @Override public boolean equals(Object o){ return (o instanceof TTuple t) && java.util.Objects.equals(elems, t.elems); }
        @Override public int hashCode(){ return java.util.Objects.hash(elems); }
        @Override public String toString(){ return "(" + String.join(", ", elems.stream().map(Object::toString).toList()) + ")"; }
    }
private final Map<String, Type> globals = new HashMap<>();
    private final Map<String, FnDecl> functions = new HashMap<>();
    private final Map<String, Ast.TaskDecl> tasks = new HashMap<>();

    public static void check(Program prog) {
        new TypeChecker().run(prog);
    }

    private void run(Program prog) {
        // Top-level const/let
        for (Top top : prog.tops()) {
            if (top instanceof ConstDecl c) {
                Type t = inferExprType(c.expr(), globals, new HashMap<>());
                if (c.type() != null) {
                    Type declared = resolveTypeRef(c.type());
                    if (!declared.equals(t)) error("Const '" + c.name() + "' at line " + c.line()
                        + ": expected type " + declared + " but found " + t);
                    globals.put(c.name(), declared);
                } else {
                    globals.put(c.name(), t);
                }
            } else if (top instanceof LetDecl l) {
                Type t = inferExprType(l.expr(), globals, new HashMap<>());
                if (l.type() != null) {
                    Type declared = resolveTypeRef(l.type());
                    if (!declared.equals(t)) error("Let '" + l.name() + "' at line " + l.line()
                        + ": expected type " + declared + " but found " + t);
                    globals.put(l.name(), declared);
                } else {
                    globals.put(l.name(), t);
                }
            } else if (top instanceof FnDecl f) {
                functions.put(f.name(), f);
            } else if (top instanceof Ast.TaskDecl td) {
                tasks.put(td.name(), td);
            }
        }
        // Functions
        for (Top top : prog.tops()) {
            if (top instanceof FnDecl f) {
                checkFunction(f);
            }
            if (top instanceof Ast.TaskDecl t) {
                checkTask(t);
            }
        }
    }

    private void checkTask(Ast.TaskDecl t) {
        Map<String, Type> env = new HashMap<>(globals);
        if (t.preOpt()!=null) {
            Type pt = inferExprType(t.preOpt(), globals, env);
            if (!(pt instanceof TBool)) error("Task '"+t.name()+"' precondition is not Bool");
        }
        // act block should be Unit (ignore value if present)
        Type at = inferBlock(t.act(), globals, env);
        // allow any, but warn-like enforced to Unit in minimal checker
        if (t.postOpt()!=null) {
            Type q = inferExprType(t.postOpt(), globals, env);
            if (!(q instanceof TBool)) error("Task '"+t.name()+"' postcondition is not Bool");
        }
    }

    private void checkFunction(FnDecl f) {
        Map<String, Type> env = new HashMap<>(globals);
        // parameters
        for (Param p : f.params()) {
            if (p.type() == null) error("Parameter '" + p.name() + "' of function '" + f.name()
                + "' missing type at line " + p.line());
            Type pt = resolveTypeRef(p.type());
            env.put(p.name(), pt);
        }
        // return type (infer when not declared)
        // body
        Type retActual;
        if (f.body() instanceof ExprBody eb) {
            retActual = inferExprType(eb.expr(), globals, env);
        } else if (f.body() instanceof BlockBody bb) {
            retActual = inferBlock(bb, globals, env);
        } else {
            retActual = new TUnit();
        }
        Type retDecl;
        if (f.returnType() != null) {
            retDecl = resolveTypeRef(f.returnType());
            if (!retActual.equals(retDecl)) {
                error("Function '" + f.name() + "' at line " + f.line()
                    + ": return type mismatch, expected " + retDecl + " but found " + retActual);
            }
        } else {
            // no declared return type: infer it and record
            retDecl = retActual;
            // Best-effort: materialize a TypeRef for common shapes; if not representable, leave as-is
            TypeRef inferred = toTypeRefOrNull(retDecl);
            if (inferred != null) {
                functions.put(f.name(), new FnDecl(f.name(), f.params(), inferred, f.body(), f.line()));
            } else {
                // Keep original declaration so subsequent calls still type-check args; return remains unchecked.
                functions.put(f.name(), f);
            }
        }
    }

    private Type inferBlock(BlockBody bb, Map<String, Type> globals, Map<String, Type> env) {
        Type last = new TUnit();
        for (Stmt s : bb.stmts()) {
            if (s instanceof SConst sc) {
                ConstDecl c = sc.d();
                Type t = inferExprType(c.expr(), globals, env);
                if (c.type() != null) {
                    Type dt = resolveTypeRef(c.type());
                    if (!dt.equals(t)) error("Const '" + c.name() + "' in function block at line " + c.line()
                        + ": expected " + dt + " but found " + t);
                    env.put(c.name(), dt);
                } else {
                    env.put(c.name(), t);
                }
            } else if (s instanceof SLet sl) {
                LetDecl l = sl.d();
                Type t = inferExprType(l.expr(), globals, env);
                if (l.type() != null) {
                    Type dt = resolveTypeRef(l.type());
                    if (!dt.equals(t)) error("Let '" + l.name() + "' in function block at line " + l.line()
                        + ": expected " + dt + " but found " + t);
                    env.put(l.name(), dt);
                } else {
                    env.put(l.name(), t);
                }
            } else if (s instanceof SExpr se) {
                last = inferExprType(se.e(), globals, env);
            }
        }
        // optional result expression
        if (bb.resultOpt() != null) {
            last = inferExprType(bb.resultOpt(), globals, env);
        }
        return last;
    }

    
    private Type inferExprType(Expr expr, Map<String, Type> globals, Map<String, Type> env) {
        if (expr instanceof LitInt) return new TInt();
        if (expr instanceof LitStr) return new TString();
        if (expr instanceof LitBool) return new TBool();
        if (expr instanceof Var v) {
            Type t = env.get(v.name());
            if (t == null) t = globals.get(v.name());
            if (t == null) error("Unknown variable '" + v.name() + "'");
            return t;
        }
        if (expr instanceof ModuleField mf) {
            String key = mf.moduleInternal() + "." + mf.name();
            Type t = globals.get(key);
            if (t == null) t = globals.get(mf.name());
            if (t == null) error("Unknown module field '" + key + "'");
            return t;
        }
        if (expr instanceof Call c) {
            if (c.callee() instanceof Var v2 && v2.name().equals("print") && c.args().size() == 1) {
                inferExprType(c.args().get(0), globals, env);
                return new TUnit();
            }
            if (c.callee() instanceof ModuleField mf) {
                String key = mf.moduleInternal() + "." + mf.name();
                FnDecl fd = functions.get(key);
                if (fd == null) fd = functions.get(mf.name());
                if (fd == null) error("Unknown function '" + key + "'");
                if (c.args().size() != fd.params().size()) error("Function '" + mf.name() + "' called with wrong arity: expected " + fd.params().size() + ", found " + c.args().size());
                for (int i = 0; i < c.args().size(); i++) {
                    Type at = inferExprType(c.args().get(i), globals, env);
                    Param p = fd.params().get(i);
                    if (p.type() != null) {
                        Type pt = resolveTypeRef(p.type());
                        if (!at.equals(pt)) error("Argument " + (i+1) + " of function '" + mf.name() + "' at line " + p.line() + ": expected " + pt + " but found " + at);
                    }
                }
                return resolveTypeRef(fd.returnType());
            }
            if (c.callee() instanceof Var v3) {
                FnDecl fd = functions.get(v3.name());
                if (fd == null) error("Unknown function '" + v3.name() + "'");
                if (c.args().size() != fd.params().size()) error("Function '" + v3.name() + "' called with wrong arity: expected " + fd.params().size() + ", found " + c.args().size());
                for (int i = 0; i < c.args().size(); i++) {
                    Type at = inferExprType(c.args().get(i), globals, env);
                    Param p = fd.params().get(i);
                    if (p.type() != null) {
                        Type pt = resolveTypeRef(p.type());
                        if (!at.equals(pt)) error("Argument " + (i+1) + " of function '" + v3.name() + "' at line " + p.line() + ": expected " + pt + " but found " + at);
                    }
                }
                return resolveTypeRef(fd.returnType());
            }
            error("Unsupported call expression");
        }
        if (expr instanceof Ast.RunTask rt) {
            if (!tasks.containsKey(rt.name())) error("Unknown task '"+rt.name()+"'");
            return new TUnit();
        }
        if (expr instanceof Ast.Match) return new TUnknown();
        if (expr instanceof Ast.Record r) {
            java.util.Map<String, Type> fts = new java.util.LinkedHashMap<>();
            for (var e : r.fields().entrySet()) fts.put(e.getKey(), inferExprType(e.getValue(), globals, env));
            return new TRecord(fts);
        }
        if (expr instanceof Ast.GetField gf) {
            Type bt = inferExprType(gf.base(), globals, env);
            if (bt instanceof TRecord rec) {
                Type ft = rec.fields.get(gf.name());
                return ft != null ? ft : new TUnknown();
            }
            return new TUnknown();
        }
        if (expr instanceof Ast.ListLit l) {
            Type elem = null;
            for (var ex : l.elems()) {
                Type et = inferExprType(ex, globals, env);
                if (elem == null || elem instanceof TUnknown) elem = et;
                else if (et instanceof TUnknown) {}
                else if (!elem.equals(et)) error("List literal elements have mismatched types: " + elem + " vs " + et);
            }
            if (elem == null) elem = new TUnknown();
            return new TList(elem);
        }
        if (expr instanceof Ast.Index idx) {
            Type bt = inferExprType(idx.base(), globals, env);
            if (bt instanceof TList tl) return tl.elem;
            if (bt instanceof TMap tm) return tm.val;
            if (!(bt instanceof TUnknown)) error("Indexing not supported on type: " + bt);
            return new TUnknown();
        }
        if (expr instanceof Ast.Slice sl) {
            Type bt = inferExprType(sl.base(), globals, env);
            if (sl.startOpt()!=null) { Type st = inferExprType(sl.startOpt(), globals, env); if (!(st instanceof TInt)) error("Slice start must be Int"); }
            if (sl.endOpt()!=null)   { Type et = inferExprType(sl.endOpt(), globals, env); if (!(et instanceof TInt)) error("Slice end must be Int"); }
            if (sl.stepOpt()!=null)  { Type pt = inferExprType(sl.stepOpt(), globals, env); if (!(pt instanceof TInt)) error("Slice step must be Int"); }
            if (bt instanceof TList tl) return bt;
            if (!(bt instanceof TUnknown)) error("Slicing not supported on type: " + bt);
            return new TUnknown();
        }
        if (expr instanceof Bin b) {
            Type l = inferExprType(b.left(), globals, env);
            Type r = inferExprType(b.right(), globals, env);
            switch (b.op()) {
                case "+": return (l instanceof TString || r instanceof TString) ? new TString() : checkIntOp(l, r, b.op());
                case "-", "*", "/", "%": return checkIntOp(l, r, b.op());
                case "==", "!=": return new TBool();
                case "<", "<=", ">", ">=": checkIntOp(l, r, b.op()); return new TBool();
                case "&&", "||": return new TBool();
                default: error("Unsupported operator '" + b.op() + "'");
            }
        }
        if (expr instanceof Un u) {
            Type t = inferExprType(u.expr(), globals, env);
            if (u.op().equals("!")) return new TBool();
            if (u.op().equals("-")) { if (!(t instanceof TInt)) error("Unary '-' requires Int operand"); return new TInt(); }
            error("Unsupported unary '" + u.op() + "'");
        }
        if (expr instanceof Ast.ListComp lc) {
            Type srcT = inferExprType(lc.src(), globals, env);
            Type elemT = new TUnknown();
            if (srcT instanceof TList tl) elemT = tl.elem;
            else if (!(srcT instanceof TUnknown)) error("List comprehension expects a List source, got: " + srcT);
            java.util.Map<String, Type> env2 = new java.util.HashMap<>(env);
            env2.put(lc.var(), elemT);
            if (lc.guardOpt()!=null) {
                Type gt = inferExprType(lc.guardOpt(), globals, env2);
                if (!(gt instanceof TBool)) error("List comprehension guard must be Bool");
            }
            Type outElem = inferExprType(lc.elem(), globals, env2);
            return new TList(outElem);
        }
        if (expr instanceof Ternary t) {
            Type c = inferExprType(t.cond(), globals, env);
            if (!(c instanceof TBool)) error("Condition in ternary is not Bool");
            Type t1 = inferExprType(t.thenE(), globals, env);
            Type t2 = inferExprType(t.elseE(), globals, env);
            if (!t1.equals(t2)) error("Branches of ternary have mismatched types: " + t1 + " vs " + t2);
            return t1;
        }
        if (expr instanceof Paren p) return inferExprType(p.inner(), globals, env);
        throw new TypeException("Type checking not supported for expression: " + expr.getClass());
    }
    private static Type checkIntOp(Type l, Type r, String op) {
        if (!(l instanceof TInt) || !(r instanceof TInt)) error("Operator '" + op + "' requires Int operands");
        return new TInt();
    }

    private static Type resolveTypeRef(TypeRef tref) {
        if (tref == null) return new TUnknown();
        String name = tref.name();
        // Handle qualified names like java.util.List as simple name on the rightmost segment
        int dot = name.lastIndexOf('.');
        if (dot >= 0) name = name.substring(dot+1);
        switch (name) {
            case "Int": return new TInt();
            case "String": return new TString();
            case "Bool": return new TBool();
            case "Unit": return new TUnit();
            case "Tuple": {
                var elems = new java.util.ArrayList<Type>();
                for (var arg : tref.args()) elems.add(resolveTypeRef(arg));
                return new TTuple(elems);
            }
            case "List": {
                if (tref.args()==null || tref.args().size()!=1) error("List<T> requires exactly one type argument");
                return new TList(resolveTypeRef(tref.args().get(0)));
            }
            case "Set": {
                if (tref.args()==null || tref.args().size()!=1) error("Set<T> requires exactly one type argument");
                return new TSet(resolveTypeRef(tref.args().get(0)));
            }
            case "Map": {
                if (tref.args()==null || tref.args().size()!=2) error("Map<K,V> requires exactly two type arguments");
                return new TMap(resolveTypeRef(tref.args().get(0)), resolveTypeRef(tref.args().get(1)));
            }
            default:
                error("Unsupported type: " + tref.name());
                return new TUnknown();
        }
        /*
            Type bt = inferExprType(sl.base(), globals, env);
            if (sl.startOpt()!=null) { Type st = inferExprType(sl.startOpt(), globals, env); if (!(st instanceof TInt)) error("Slice start must be Int"); }
            if (sl.endOpt()!=null)   { Type et = inferExprType(sl.endOpt(), globals, env); if (!(et instanceof TInt)) error("Slice end must be Int"); }
            if (sl.stepOpt()!=null)  { Type pt = inferExprType(sl.stepOpt(), globals, env); if (!(pt instanceof TInt)) error("Slice step must be Int"); }
            if (bt instanceof TList tl) return bt; // slicing preserves list element type
            if (!(bt instanceof TUnknown)) error("Slicing not supported on type: " + bt);
            return new TUnknown();
        }
        */
    }

    private static void error(String msg) {
        throw new TypeException(msg);
    }

    private static TypeRef toTypeRefOrNull(Type t) {
        if (t instanceof TInt) return new TypeRef("Int", List.of());
        if (t instanceof TString) return new TypeRef("String", List.of());
        if (t instanceof TBool) return new TypeRef("Bool", List.of());
        if (t instanceof TUnit) return new TypeRef("Unit", List.of());
        if (t instanceof TTuple tt) {
            var args = new java.util.ArrayList<TypeRef>();
            for (var e : tt.elems) {
                var tr = toTypeRefOrNull(e);
                if (tr == null) return null;
                args.add(tr);
            }
            return new TypeRef("Tuple", args);
        }
        if (t instanceof TList tl) {
            TypeRef inner = toTypeRefOrNull(tl.elem);
            if (inner == null) return null;
            return new TypeRef("List", List.of(inner));
        }
        if (t instanceof TSet ts) {
            TypeRef inner = toTypeRefOrNull(ts.elem);
            if (inner == null) return null;
            return new TypeRef("Set", List.of(inner));
        }
        if (t instanceof TMap tm) {
            TypeRef k = toTypeRefOrNull(tm.key);
            TypeRef v = toTypeRefOrNull(tm.val);
            if (k == null || v == null) return null;
            return new TypeRef("Map", List.of(k, v));
        }
        // Structural record or unknown -> not representable as nominal TypeRef here
        return null;
    }
}
