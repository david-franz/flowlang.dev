package dfpp.ast;

import java.util.List;

public final class Ast {
    // Type references (with optional type arguments)
    public record TypeRef(String name, List<TypeRef> args) {}
    // Function parameter with type annotation and source line
    public record Param(String name, TypeRef type, int line) {}

    // Program
    public record Program(List<Top> tops) {}

    // Top-level decls
    public sealed interface Top permits ConstDecl, LetDecl, FnDecl {}

    /** Top-level constant with optional type annotation. */
    public record ConstDecl(String name, TypeRef type, Expr expr, int line) implements Top {}
    /** Top-level let-binding with optional type annotation. */
    public record LetDecl(String name, TypeRef type, Expr expr, int line) implements Top {}

    /** Function declaration: name, parameters with types, return type, body, and source line. */
    public record FnDecl(String name, List<Param> params, TypeRef returnType, Body body, int line) implements Top {}
    public sealed interface Body permits ExprBody, BlockBody {}
    public record ExprBody(Expr expr) implements Body {}
    public record BlockBody(List<Stmt> stmts, Expr resultOpt) implements Body {}

    // Statements (inside block bodies)
    public sealed interface Stmt permits SConst, SLet, SExpr {}
    public record SConst(ConstDecl d) implements Stmt {}
    public record SLet(LetDecl d) implements Stmt {}
    public record SExpr(Expr e) implements Stmt {}

    // Expressions (subset)
public sealed interface Expr permits LitInt, LitStr, LitBool, Var, Call, Bin, Un, Ternary, Paren, Match, Record, GetField, ListLit, Index, ModuleField {}

    public record LitInt(int value) implements Expr {}
    public record LitStr(String value) implements Expr {}
    public record LitBool(boolean value) implements Expr {}
    public record Var(String name) implements Expr {}

    public record Call(Expr callee, List<Expr> args) implements Expr {}
    public record Bin(String op, Expr left, Expr right) implements Expr {}   // "+","-","*","/","%","==","!=","<","<=",">",">="
    public record Un(String op, Expr expr) implements Expr {}                // "!"
    public record Ternary(Expr cond, Expr thenE, Expr elseE) implements Expr {}
    public record Paren(Expr inner) implements Expr {}

    /** Pattern-matching expression: match <scrut> { arms... } */
    public record Match(Expr scrut, java.util.List<MatchArm> arms) implements Expr {}
    /** One arm of a pattern match */
    public record MatchArm(Pattern pat, Expr expr) {}

    /** Patterns for match expressions */
    public sealed interface Pattern permits PWildcard, PLitInt, PLitStr, PLitBool {}
    public record PWildcard() implements Pattern {}
    public record PLitInt(int value) implements Pattern {}
    public record PLitStr(String value) implements Pattern {}
    public record PLitBool(boolean value) implements Pattern {}

    /** Record literal: { k1 = v1, ... } */
    public record Record(java.util.Map<String, Expr> fields) implements Expr {}

    /** Field access: base.field */
    public record GetField(Expr base, String name) implements Expr {}

    /** List literal: [e1, e2, ...] */
    public record ListLit(java.util.List<Expr> elems) implements Expr {}

    /** Indexing: base[index] */
    public record Index(Expr base, Expr index) implements Expr {}

    /** Access to a static field on an imported module/class */
    public record ModuleField(String moduleInternal, String name) implements Expr {}
}
