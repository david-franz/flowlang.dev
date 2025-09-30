package dfpp.ast;

import java.util.List;

public final class Ast {
    // Program
    public record Program(List<Top> tops) {}

    // Top-level decls
    public sealed interface Top permits ConstDecl, LetDecl, FnDecl {}

    public record ConstDecl(String name, Expr expr) implements Top {}
    public record LetDecl(String name, Expr expr) implements Top {}

    public record FnDecl(String name, List<String> params, Body body) implements Top {}
    public sealed interface Body permits ExprBody, BlockBody {}
    public record ExprBody(Expr expr) implements Body {}
    public record BlockBody(List<Stmt> stmts, Expr resultOpt) implements Body {}

    // Statements (inside block bodies)
    public sealed interface Stmt permits SConst, SLet, SExpr {}
    public record SConst(ConstDecl d) implements Stmt {}
    public record SLet(LetDecl d) implements Stmt {}
    public record SExpr(Expr e) implements Stmt {}

    // Expressions (subset)
    public sealed interface Expr permits LitInt, LitStr, LitBool, Var, Call, Bin, Un, Ternary, Paren {}

    public record LitInt(int value) implements Expr {}
    public record LitStr(String value) implements Expr {}
    public record LitBool(boolean value) implements Expr {}
    public record Var(String name) implements Expr {}

    public record Call(Expr callee, List<Expr> args) implements Expr {}
    public record Bin(String op, Expr left, Expr right) implements Expr {}   // "+","-","*","/","%","==","!=","<","<=",">",">="
    public record Un(String op, Expr expr) implements Expr {}                // "!"
    public record Ternary(Expr cond, Expr thenE, Expr elseE) implements Expr {}
    public record Paren(Expr inner) implements Expr {}
}
