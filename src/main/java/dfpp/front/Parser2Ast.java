package dfpp.front;

import dfpp.ast.Ast.*;
import dfpp.ast.Ast;
import dfpp.ast.gen.DfppBaseVisitor;
import dfpp.ast.gen.DfppParser;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class Parser2Ast extends DfppBaseVisitor<Object> {

    public Ast.Program build(DfppParser.ProgramContext p) {
        var tops = new ArrayList<Ast.Top>();
        for (var t : p.topDecl()) {
            var top = (Ast.Top) visitTopDecl(t);
            if (top != null) tops.add(top);
        }
        return new Ast.Program(tops);
    }

    public Object visitTopDecl(DfppParser.TopDeclContext ctx) {
        if (ctx.constDecl()!=null) return visitConstDecl(ctx.constDecl());
        if (ctx.letDecl()!=null)   return visitLetDecl(ctx.letDecl());
        if (ctx.fnDecl()!=null)    return visitFnDecl(ctx.fnDecl());
        // ignore other top-level constructs for minimal v1:
        // typeDecl, taskDecl, runStmt, parallelStmt
        return null;
    }

    public Object visitConstDecl(DfppParser.ConstDeclContext ctx) {
        var name = ident(ctx.ident());
        var expr = (Ast.Expr) visitExpr(ctx.expr());
        return new Ast.ConstDecl(name, expr);
    }

    public Object visitLetDecl(DfppParser.LetDeclContext ctx) {
        var name = ident(ctx.ident());
        var expr = (Ast.Expr) visitExpr(ctx.expr());
        return new Ast.LetDecl(name, expr);
    }

    public Object visitFnDecl(DfppParser.FnDeclContext ctx) {
        var name = ctx.ident().getText().replace("`", "");
        var params = new ArrayList<String>();
        if (ctx.paramList()!=null) {
            for (var p : ctx.paramList().param()) params.add(ident(p.ident()));
        }
        Ast.Body body;
        if (ctx.block()!=null) {
            var blk = ctx.block();
            var stmts = new ArrayList<Ast.Stmt>();
            for (var s : blk.stmt()) stmts.add((Ast.Stmt) visitStmt(s));
            var res = blk.expr()!=null ? (Ast.Expr) visitExpr(blk.expr()) : null;
            body = new Ast.BlockBody(stmts, res);
        } else {
            body = new Ast.ExprBody((Ast.Expr) visitExpr(ctx.expr()));
        }
        return new Ast.FnDecl(name, params, body);
    }

    public Object visitStmt(DfppParser.StmtContext ctx) {
        if (ctx.constDecl()!=null) return new Ast.SConst((Ast.ConstDecl) visitConstDecl(ctx.constDecl()));
        if (ctx.letDecl()!=null)   return new Ast.SLet((Ast.LetDecl) visitLetDecl(ctx.letDecl()));
        if (ctx.expr()!=null)      return new Ast.SExpr((Ast.Expr) visitExpr(ctx.expr()));
        throw unsupported(ctx.start, "unsupported stmt");
    }

    // --------- Expressions (subset) ----------

    public Object visitExpr(DfppParser.ExprContext ctx) {
        return visit(ctx.conditional());
    }

    public Object visitConditional(DfppParser.ConditionalContext ctx) {
        var left = (Ast.Expr) visit(ctx.logicalOr());
        if (ctx.QMARK()!=null) {
            var thenE = (Ast.Expr) visit(ctx.expr(0));
            var elseE = (Ast.Expr) visit(ctx.expr(1));
            return new Ast.Ternary(left, thenE, elseE);
        }
        return left;
    }

    public Object visitLogicalOr(DfppParser.LogicalOrContext ctx) {
        var e = (Ast.Expr) visit(ctx.logicalAnd(0));
        for (int i=1;i<ctx.logicalAnd().size();i++) {
            e = new Ast.Bin("||", e, (Ast.Expr) visit(ctx.logicalAnd(i)));
        }
        return e;
    }

    public Object visitLogicalAnd(DfppParser.LogicalAndContext ctx) {
        var e = (Ast.Expr) visit(ctx.equality(0));
        for (int i=1;i<ctx.equality().size();i++) {
            e = new Ast.Bin("&&", e, (Ast.Expr) visit(ctx.equality(i)));
        }
        return e;
    }

    public Object visitEquality(DfppParser.EqualityContext ctx) {
        var e = (Ast.Expr) visit(ctx.relational(0));
        for (int i=1;i<ctx.relational().size();i++) {
            var op = ctx.getChild(2*i-1).getText();
            e = new Ast.Bin(op, e, (Ast.Expr) visit(ctx.relational(i)));
        }
        return e;
    }

    public Object visitRelational(DfppParser.RelationalContext ctx) {
        var e = (Ast.Expr) visit(ctx.additive(0));
        for (int i=1;i<ctx.additive().size();i++) {
            var op = ctx.getChild(2*i-1).getText();
            e = new Ast.Bin(op, e, (Ast.Expr) visit(ctx.additive(i)));
        }
        return e;
    }

    public Object visitAdditive(DfppParser.AdditiveContext ctx) {
        var e = (Ast.Expr) visit(ctx.multiplicative(0));
        for (int i=1;i<ctx.multiplicative().size();i++) {
            var op = ctx.getChild(2*i-1).getText();
            e = new Ast.Bin(op, e, (Ast.Expr) visit(ctx.multiplicative(i)));
        }
        return e;
    }

    public Object visitMultiplicative(DfppParser.MultiplicativeContext ctx) {
        var e = (Ast.Expr) visit(ctx.unary(0));
        for (int i=1;i<ctx.unary().size();i++) {
            var op = ctx.getChild(2*i-1).getText();
            e = new Ast.Bin(op, e, (Ast.Expr) visit(ctx.unary(i)));
        }
        return e;
    }

    public Object visitUnary(DfppParser.UnaryContext ctx) {
        if (ctx.NOT()!=null) return new Ast.Un("!", (Ast.Expr) visit(ctx.unary()));
        return visit(ctx.postfix());
    }

    public Object visitPostfix(DfppParser.PostfixContext ctx) {
        var base = (Ast.Expr) visit(ctx.primary());
        // Only handle simple f(...) calls and member calls a.b(...)
        for (var op : ctx.postfixOp()) {
            if (op.LP()!=null && op.DOT()==null) {
                var args = op.args()==null ? List.<Ast.Expr>of()
                        : op.args().expr().stream().map(e -> (Ast.Expr) visitExpr(e)).collect(toList());
                base = new Ast.Call(base, args);
            } else if (op.DOT()!=null && op.LP()!=null) {
                var name = op.ident().getText().replace("`","");
                var callee = new Ast.Call(new Ast.Var(nameOfMember(base, name)), List.of()); // simplify: treat as free fn call name; or special-case print
                // but simpler: just build Call(Select(base,name),args) – we don't implement member dispatch today
                var args = op.args()==null ? List.<Ast.Expr>of()
                        : op.args().expr().stream().map(e -> (Ast.Expr) visitExpr(e)).collect(toList());
                base = new Ast.Call(new Ast.Call(base, List.of()), args); // noop; we don't implement member methods in v1
            } else if (op.DOT()!=null && op.LP()==null) {
                // field access: a.b
                String name = op.ident().getText().replace("`", "");
                base = new Ast.GetField(base, name);
            } else if (op.DOT()!=null) {
                // method call not supported in minimal v1
                throw unsupported(op.DOT().getSymbol(), "method call not supported in minimal v1");
            } else if (op.LSB()!=null) {
                throw unsupported(op.LSB().getSymbol(), "indexing not supported in minimal v1");
            }
        }
        return base;
    }

    public Object visitPrimary(DfppParser.PrimaryContext ctx) {
        if (ctx.literal()!=null) return visitLiteral(ctx.literal());
        if (ctx.expr()!=null)    return new Ast.Paren((Ast.Expr) visitExpr(ctx.expr()));
        if (ctx.matchExpr()!=null) return visitMatchExpr(ctx.matchExpr());
        if (ctx.recordLit()!=null) return visitRecordLit(ctx.recordLit());
        if (ctx.ident()!=null)   return new Ast.Var(ident(ctx.ident()));
        if (ctx.arrayLit()!=null || ctx.runCall()!=null || ctx.lambdaExpr()!=null)
            throw unsupported(ctx.start, "construct not supported in minimal v1");
        return null;
    }

    public Object visitLiteral(DfppParser.LiteralContext ctx) {
        if (ctx.INTLIT()!=null)   return new Ast.LitInt(Integer.parseInt(ctx.INTLIT().getText()));
        if (ctx.STRING()!=null)   return new Ast.LitStr(unquote(ctx.STRING().getText()));
        if (ctx.TRUE()!=null)     return new Ast.LitBool(true);
        if (ctx.FALSE()!=null)    return new Ast.LitBool(false);
        if (ctx.NULL()!=null)     return new Ast.LitStr("null"); // we don't model null yet
        throw unsupported(ctx.start, "literal");
    }

    public Object visitMatchExpr(DfppParser.MatchExprContext ctx) {
        Ast.Expr scrut = (Ast.Expr) visitExpr(ctx.expr());
        var arms = new java.util.ArrayList<Ast.MatchArm>();
        for (var mctx : ctx.matchArm()) {
            Ast.Pattern pat = (Ast.Pattern) visitPattern(mctx.pattern());
            Ast.Expr expr = (Ast.Expr) visitExpr(mctx.expr());
            arms.add(new Ast.MatchArm(pat, expr));
        }
        return new Ast.Match(scrut, arms);
    }

    public Object visitPattern(DfppParser.PatternContext ctx) {
        if (ctx.UNDERSCORE()!=null) {
            return new Ast.PWildcard();
        }
        if (ctx.literal()!=null) {
            Object litObj = visitLiteral(ctx.literal());
            if (litObj instanceof Ast.LitInt li) return new Ast.PLitInt(li.value());
            if (litObj instanceof Ast.LitStr ls) return new Ast.PLitStr(ls.value());
            if (litObj instanceof Ast.LitBool lb) return new Ast.PLitBool(lb.value());
        }
        throw unsupported(ctx.start, "pattern not supported in minimal v1");
    }

    public Object visitRecordLit(DfppParser.RecordLitContext ctx) {
        var map = new java.util.LinkedHashMap<String, Ast.Expr>();
        for (var rf : ctx.recField()) {
            String name = ident(rf.ident());
            Ast.Expr val = (Ast.Expr) visitExpr(rf.expr());
            map.put(name, val);
        }
        return new Ast.Record(map);
    }

    private static String ident(DfppParser.IdentContext id) {
        return id.getText().replace("`","");
    }

    private static String unquote(String s) {
        if (s.length()>=2) return s.substring(1, s.length()-1)
                .replace("\\\"", "\"").replace("\\'", "'").replace("\\\\","\\");
        return s;
    }

    private static RuntimeException unsupported(Token t, String what) {
        return new RuntimeException("Unsupported in minimal v1 at "+t.getLine()+":"+t.getCharPositionInLine()+": "+what);
    }

    private static String nameOfMember(Ast.Expr base, String member) {
        // For minimal v1 we don't support member calls; leave a hint
        return member;
    }
}
