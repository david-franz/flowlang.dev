package dfpp.front;

import dfpp.ast.Ast;
import dfpp.ast.Ast.*;
import java.util.*;

/**
 * A simple static type checker for primitive types (Int, String, Bool, Unit).
 */
public final class TypeChecker {
    public static class TypeException extends RuntimeException {
        public TypeException(String message) {
            super(message);
        }
    }

    private enum Type { INT, STRING, BOOL, UNIT, UNKNOWN }

    private final Map<String, Type> globals = new HashMap<>();
    private final Map<String, FnDecl> functions = new HashMap<>();

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
                    if (declared != t) error("Const '" + c.name() + "' at line " + c.line()
                        + ": expected type " + declared + " but found " + t);
                    globals.put(c.name(), declared);
                } else {
                    globals.put(c.name(), t);
                }
            } else if (top instanceof LetDecl l) {
                Type t = inferExprType(l.expr(), globals, new HashMap<>());
                if (l.type() != null) {
                    Type declared = resolveTypeRef(l.type());
                    if (declared != t) error("Let '" + l.name() + "' at line " + l.line()
                        + ": expected type " + declared + " but found " + t);
                    globals.put(l.name(), declared);
                } else {
                    globals.put(l.name(), t);
                }
            } else if (top instanceof FnDecl f) {
                functions.put(f.name(), f);
            }
        }
        // Functions
        for (Top top : prog.tops()) {
            if (top instanceof FnDecl f) {
                checkFunction(f);
            }
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
            retActual = Type.UNIT;
        }
        Type retDecl;
        if (f.returnType() != null) {
            retDecl = resolveTypeRef(f.returnType());
            if (retActual != retDecl) {
                error("Function '" + f.name() + "' at line " + f.line()
                    + ": return type mismatch, expected " + retDecl + " but found " + retActual);
            }
        } else {
            // no declared return type: infer it and record
            retDecl = retActual;
            String tn;
            switch (retDecl) {
                case INT    -> tn = "Int";
                case STRING -> tn = "String";
                case BOOL   -> tn = "Bool";
                case UNIT   -> tn = "Unit";
                default     -> tn = "Unknown";
            }
            TypeRef inferred = new TypeRef(tn, List.of());
            functions.put(f.name(), new FnDecl(f.name(), f.params(), inferred, f.body(), f.line()));
        }
    }

    private Type inferBlock(BlockBody bb, Map<String, Type> globals, Map<String, Type> env) {
        Type last = Type.UNIT;
        for (Stmt s : bb.stmts()) {
            if (s instanceof SConst sc) {
                ConstDecl c = sc.d();
                Type t = inferExprType(c.expr(), globals, env);
                if (c.type() != null) {
                    Type dt = resolveTypeRef(c.type());
                    if (dt != t) error("Const '" + c.name() + "' in function block at line " + c.line()
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
                    if (dt != t) error("Let '" + l.name() + "' in function block at line " + l.line()
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
        if (expr instanceof LitInt) return Type.INT;
        if (expr instanceof LitStr) return Type.STRING;
        if (expr instanceof LitBool) return Type.BOOL;
        if (expr instanceof Var v) {
            Type t = env.get(v.name());
            if (t == null) t = globals.get(v.name());
            if (t == null) error("Unknown variable '" + v.name() + "'");
            return t;
        }
        if (expr instanceof ModuleField mf) {
            String key = mf.moduleInternal() + "." + mf.name();
            Type t = globals.get(key);
            if (t == null) {
                // try unqualified name (imported AST merged)
                t = globals.get(mf.name());
            }
            if (t == null) error("Unknown module field '" + key + "'");
            return t;
        }
        if (expr instanceof Call c) {
            // built-in print
            if (c.callee() instanceof Var v2 && v2.name().equals("print") && c.args().size() == 1) {
                inferExprType(c.args().get(0), globals, env);
                return Type.UNIT;
            }
            // module function call: dc2.greet(...)
            if (c.callee() instanceof ModuleField mf) {
                String key = mf.moduleInternal() + "." + mf.name();
                FnDecl fd = functions.get(key);
                if (fd == null) {
                    // fallback to unqualified
                    fd = functions.get(mf.name());
                }
                if (fd == null) error("Unknown function '" + key + "'");
                if (c.args().size() != fd.params().size()) error("Function '" + mf.name() + "' called with wrong arity");
                for (int i = 0; i < c.args().size(); i++) {
                    Type at = inferExprType(c.args().get(i), globals, env);
                    Param p = fd.params().get(i);
                    Type pt = resolveTypeRef(p.type());
                    if (at != pt) error("Argument " + (i+1) + " of function '" + mf.name() + "' at line " + p.line()
                        + ": expected " + pt + " but found " + at);
                }
                return resolveTypeRef(fd.returnType());
            }
            // user function call: greet(...)
            if (c.callee() instanceof Var v3) {
                FnDecl fd = functions.get(v3.name());
                if (fd == null) error("Unknown function '" + v3.name() + "'");
                if (c.args().size() != fd.params().size()) error("Function '" + v3.name()
                    + "' called with wrong arity");
                for (int i = 0; i < c.args().size(); i++) {
                    Type at = inferExprType(c.args().get(i), globals, env);
                    Param p = fd.params().get(i);
                    Type pt = resolveTypeRef(p.type());
                    if (at != pt) error("Argument " + (i+1) + " of function '" + v3.name() + "' at line " + p.line()
                        + ": expected " + pt + " but found " + at);
                }
                return resolveTypeRef(fd.returnType());
            }
            error("Unsupported call expression");
        }
        if (expr instanceof Ast.Match) {
            // Minimal v1: allow match expressions without precise typing
            return Type.UNKNOWN;
        }
        if (expr instanceof Ast.Record) {
            return Type.UNKNOWN;
        }
        if (expr instanceof Ast.GetField) {
            return Type.UNKNOWN;
        }
        if (expr instanceof Ast.ListLit) {
            return Type.UNKNOWN;
        }
        if (expr instanceof Ast.Index) {
            return Type.UNKNOWN;
        }
        if (expr instanceof Bin b) {
            Type l = inferExprType(b.left(), globals, env);
            Type r = inferExprType(b.right(), globals, env);
            switch (b.op()) {
                case "+": return (l == Type.STRING || r == Type.STRING) ? Type.STRING
                                                                  : checkIntOp(l, r, b.op());
                case "-", "*", "/", "%": return checkIntOp(l, r, b.op());
                case "==", "!=": return Type.BOOL;
                case "<", "<=", ">", ">=": checkIntOp(l, r, b.op()); return Type.BOOL;
                case "&&", "||": return Type.BOOL;
                default: error("Unsupported operator '" + b.op() + "'");
            }
        }
        if (expr instanceof Un u) {
            Type t = inferExprType(u.expr(), globals, env);
            if (!u.op().equals("!")) error("Unsupported unary '" + u.op() + "'");
            return Type.BOOL;
        }
        if (expr instanceof Ternary t) {
            Type c = inferExprType(t.cond(), globals, env);
            if (c != Type.BOOL) error("Condition in ternary is not Bool");
            Type t1 = inferExprType(t.thenE(), globals, env);
            Type t2 = inferExprType(t.elseE(), globals, env);
            if (t1 != t2) error("Branches of ternary have mismatched types: " + t1 + " vs " + t2);
            return t1;
        }
        if (expr instanceof Paren p) return inferExprType(p.inner(), globals, env);
        throw new TypeException("Type checking not supported for expression: " + expr.getClass());
    }

    private static Type checkIntOp(Type l, Type r, String op) {
        if (l != Type.INT || r != Type.INT) error("Operator '" + op + "' requires Int operands");
        return Type.INT;
    }

    private static Type resolveTypeRef(TypeRef tref) {
        switch (tref.name()) {
            case "Int": return Type.INT;
            case "String": return Type.STRING;
            case "Bool": return Type.BOOL;
            case "Unit": return Type.UNIT;
            default: error("Unsupported type: " + tref.name()); return Type.UNKNOWN;
        }
    }

    private static void error(String msg) {
        throw new TypeException(msg);
    }
}