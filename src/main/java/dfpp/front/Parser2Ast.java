package dfpp.front;

import dfpp.ast.Ast.*;
import dfpp.ast.Ast.TypeRef;
import dfpp.ast.Ast.Param;
import dfpp.ast.Ast;
import dfpp.ast.gen.DfppBaseVisitor;
import dfpp.ast.gen.DfppParser;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public final class Parser2Ast extends DfppBaseVisitor<Object> {
    private final java.util.Map<String,String> imports = new java.util.HashMap<>();

    /** Returns the import alias->module mapping for this file. */
    public Map<String,String> getImports() {
        return imports;
    }

    public Ast.Program build(DfppParser.ProgramContext p) {
        // process imports first
        for (var idctx : p.importDecl()) {
            visitImportDecl(idctx);
        }
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
        if (ctx.taskDecl()!=null)  return visitTaskDecl(ctx.taskDecl());
        // ignore other top-level constructs for minimal v1:
        // typeDecl, runStmt, parallelStmt
        return null;
    }

    public Object visitConstDecl(DfppParser.ConstDeclContext ctx) {
        String name = ident(ctx.ident());
        TypeRef type = ctx.typeRef()!=null ? (TypeRef) visit(ctx.typeRef()) : null;
        var expr = (Ast.Expr) visitExpr(ctx.expr());
        int line = ctx.start.getLine();
        return new Ast.ConstDecl(name, type, expr, line);
    }

    public Object visitLetDecl(DfppParser.LetDeclContext ctx) {
        String name = ident(ctx.ident());
        TypeRef type = ctx.typeRef()!=null ? (TypeRef) visit(ctx.typeRef()) : null;
        var expr = (Ast.Expr) visitExpr(ctx.expr());
        int line = ctx.start.getLine();
        return new Ast.LetDecl(name, type, expr, line);
    }

    public Object visitFnDecl(DfppParser.FnDeclContext ctx) {
        var name = ctx.ident().getText().replace("`", "");
        var params = new ArrayList<Param>();
        if (ctx.paramList()!=null) {
            for (var p : ctx.paramList().param()) {
                String pname = ident(p.ident());
                TypeRef ptype = p.typeRef()!=null ? (TypeRef) visit(p.typeRef()) : null;
                int pline = p.start.getLine();
                params.add(new Param(pname, ptype, pline));
            }
        }
        Ast.Body body;
        if (ctx.block()!=null) {
            var blk = ctx.block();
            var stmts = new ArrayList<Ast.Stmt>();
            for (var s : blk.stmt()) {
                Object v = visitStmt(s);
                if (v instanceof Ast.Stmt st) stmts.add(st);
                else if (v instanceof java.util.List<?> lst) {
                    for (Object o : lst) stmts.add((Ast.Stmt) o);
                }
            }
            Ast.Expr res = null;
            if (blk.expr()!=null) {
                res = (Ast.Expr) visitExpr(blk.expr());
            } else if (!stmts.isEmpty() && stmts.get(stmts.size()-1) instanceof Ast.SExpr se) {
                res = se.e();
                stmts.remove(stmts.size()-1);
            }
            body = new Ast.BlockBody(stmts, res);
        } else {
            body = new Ast.ExprBody((Ast.Expr) visitExpr(ctx.expr()));
        }
        TypeRef returnType = ctx.typeRef()!=null ? (TypeRef) visit(ctx.typeRef()) : null;
        int line = ctx.start.getLine();
        return new Ast.FnDecl(name, params, returnType, body, line);
    }

    public Object visitStmt(DfppParser.StmtContext ctx) {
        if (ctx.constDecl()!=null) return new Ast.SConst((Ast.ConstDecl) visitConstDecl(ctx.constDecl()));
        if (ctx.letDecl()!=null)   return new Ast.SLet((Ast.LetDecl) visitLetDecl(ctx.letDecl()));
        if (ctx.runStmt()!=null) {
            var out = new java.util.ArrayList<Ast.Stmt>();
            var rs = ctx.runStmt();
            String first = unquote(rs.runCall().STRING().getText());
            out.add(new Ast.SExpr(new Ast.RunTask(first)));
            // Parse chained .then("...")
            for (int i = 0; i < rs.getChildCount(); i++) {
                var ch = rs.getChild(i);
                if (ch instanceof dfpp.ast.gen.DfppParser.IdentContext idctx) {
                    String method = ident(idctx);
                    if (!method.equals("then")) throw unsupported(idctx.start, "only then(...) supported on run(...) in v1");
                    // find next ArgsContext sibling
                    dfpp.ast.gen.DfppParser.ArgsContext args = null;
                    for (int j = i+1; j < rs.getChildCount(); j++) {
                        var ch2 = rs.getChild(j);
                        if (ch2 instanceof dfpp.ast.gen.DfppParser.ArgsContext a) { args = a; break; }
                    }
                    if (args == null || args.expr().size()!=1) {
                        throw unsupported(idctx.start, "then requires a single string literal");
                    }
                    Object v = visitExpr(args.expr(0));
                    if (!(v instanceof Ast.LitStr ls)) {
                        throw unsupported(idctx.start, "then requires a single string literal");
                    }
                    String nm = ls.value();
                    out.add(new Ast.SExpr(new Ast.RunTask(nm)));
                }
            }
            return out;
        }
        if (ctx.parallelStmt()!=null) {
            var out = new java.util.ArrayList<Ast.Stmt>();
            var sl = ctx.parallelStmt().stringList();
            for (var tok : sl.STRING()) {
                out.add(new Ast.SExpr(new Ast.RunTask(unquote(tok.getText()))));
            }
            return out;
        }
        if (ctx.expr()!=null)      return new Ast.SExpr((Ast.Expr) visitExpr(ctx.expr()));
        throw unsupported(ctx.start, "unsupported stmt");
    }

    public Object visitImportDecl(DfppParser.ImportDeclContext ctx) {
        String moduleName = ctx.qid().getText();
        String alias = ctx.ID().getText().replace("`", "");
        imports.put(alias, moduleName);
        return null;
    }

    // --------- Expressions (subset) ----------

    @Override
    public Object visitNamedOrAppliedType(DfppParser.NamedOrAppliedTypeContext ctx) {
        String name = ctx.qid().getText();
        var args = new java.util.ArrayList<TypeRef>();
        if (ctx.typeArgs() != null) {
            for (var tr : ctx.typeArgs().typeRef()) {
                args.add((TypeRef) visit(tr));
            }
        }
        return new TypeRef(name, args);
    }

    @Override
    public Object visitTupleType(DfppParser.TupleTypeContext ctx) {
        var args = new java.util.ArrayList<TypeRef>();
        for (var tr : ctx.typeRef()) {
            args.add((TypeRef) visit(tr));
        }
        return new TypeRef("Tuple", args);
    }


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
        // Support unary minus: -expr
        if (ctx.getChildCount() == 2 && ctx.getChild(0).getText().equals("-")) {
            return new Ast.Un("-", (Ast.Expr) visit(ctx.unary()));
        }
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
                String name = op.ident().getText().replace("`", "");
                if (base instanceof Ast.Var v && imports.containsKey(v.name())) {
                    String mod = imports.get(v.name());
                    base = new Ast.ModuleField(mod.replace('.', '/'), name);
                } else {
                    // record field access
                    base = new Ast.GetField(base, name);
                }
            } else if (op.DOT()!=null) {
                // method call not supported in minimal v1
                throw unsupported(op.DOT().getSymbol(), "method call not supported in minimal v1");
            } else if (op.LSB()!=null) {
                // Distinguish index vs slice via sliceSpec()
                var ss = op.getRuleContexts(dfpp.ast.gen.DfppParser.SliceSpecContext.class);
                dfpp.ast.gen.DfppParser.SliceSpecContext spec = ss.isEmpty()? null : ss.get(0);
                if (spec == null) {
                    // fallback to single expr child
                    var exprs = op.getRuleContexts(dfpp.ast.gen.DfppParser.ExprContext.class);
                    Ast.Expr idx = (Ast.Expr) visitExpr(exprs.get(0));
                    base = new Ast.Index(base, idx);
                } else if (spec.index != null) {
                    Ast.Expr idx = (Ast.Expr) visitExpr(spec.index);
                    base = new Ast.Index(base, idx);
                } else {
                    Ast.Expr start = spec.start != null ? (Ast.Expr) visitExpr(spec.start) : null;
                    Ast.Expr end   = spec.end   != null ? (Ast.Expr) visitExpr(spec.end)   : null;
                    Ast.Expr step  = spec.step  != null ? (Ast.Expr) visitExpr(spec.step)  : null;
                    base = new Ast.Slice(base, start, end, step);
                }
            }
        }
        return base;
    }

    public Object visitPrimary(DfppParser.PrimaryContext ctx) {
        if (ctx.literal()!=null) return visitLiteral(ctx.literal());
        if (ctx.expr()!=null)    return new Ast.Paren((Ast.Expr) visitExpr(ctx.expr()));
        if (ctx.matchExpr()!=null) return visitMatchExpr(ctx.matchExpr());
        if (ctx.recordLit()!=null) return visitRecordLit(ctx.recordLit());
        if (ctx.arrayLit()!=null)  return visitArrayLit(ctx.arrayLit());
        if (ctx.ident()!=null)     return new Ast.Var(ident(ctx.ident()));
        if (ctx.runCall()!=null) {
            var rc = ctx.runCall();
            String raw = rc.STRING().getText();
            String name = unquote(raw);
            return new Ast.RunTask(name);
        }
        if (ctx.lambdaExpr()!=null)
            throw unsupported(ctx.start, "construct not supported in minimal v1");
        return null;
    }

    public Object visitTaskDecl(DfppParser.TaskDeclContext ctx) {
        String name = unquote(ctx.STRING().getText());
        Ast.Expr pre = null;
        Ast.Expr post = null;
        // Up to two expr() directly under taskDecl: pre (if any) and post (if any)
        java.util.List<dfpp.ast.gen.DfppParser.ExprContext> exprs = ctx.getRuleContexts(dfpp.ast.gen.DfppParser.ExprContext.class);
        Ast.BlockBody act;
        var blk = ctx.block();
        var stmts = new ArrayList<Ast.Stmt>();
        for (var s : blk.stmt()) {
            Object v = visitStmt(s);
            if (v instanceof Ast.Stmt st) stmts.add(st);
            else if (v instanceof java.util.List<?> lst) {
                for (Object o : lst) stmts.add((Ast.Stmt) o);
            }
        }
        Ast.Expr res = null;
        if (blk.expr()!=null) {
            res = (Ast.Expr) visitExpr(blk.expr());
        } else if (!stmts.isEmpty() && stmts.get(stmts.size()-1) instanceof Ast.SExpr se) {
            res = se.e();
            stmts.remove(stmts.size()-1);
        }
        act = new Ast.BlockBody(stmts, res);
        if (!exprs.isEmpty()) {
            // First expr is PRE (when present) else POS (if PRE absent and POS present)
            // Disambiguate by position: If there are two exprs, it's PRE then POS; if one, it could be PRE or POS.
            // We rely on order: grammar emits (PRE expr)? ... (POS expr)?
            if (exprs.size() == 1) {
                // We cannot easily know if it's PRE or POS without tokens; default to PRE
                pre = (Ast.Expr) visitExpr(exprs.get(0));
            } else if (exprs.size() >= 2) {
                pre = (Ast.Expr) visitExpr(exprs.get(0));
                post = (Ast.Expr) visitExpr(exprs.get(1));
            }
        }
        int line = ctx.start.getLine();
        return new Ast.TaskDecl(name, pre, act, post, line);
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

    public Object visitArrayLit(DfppParser.ArrayLitContext ctx) {
        // Detect comprehension form: [ expr for x in expr (if expr)? ]
        boolean hasFor = false;
        String varName = null;
        int inExprIdx = -1; // index in ctx.expr() of source expr
        int guardExprIdx = -1;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            var ch = ctx.getChild(i);
            String txt = ch.getText();
            if (txt != null && txt.equals("for")) {
                hasFor = true;
                // next ident child is the variable name
                for (int j = i+1; j < ctx.getChildCount(); j++) {
                    var ch2 = ctx.getChild(j);
                    if (ch2 instanceof dfpp.ast.gen.DfppParser.IdentContext id2) {
                        varName = id2.getText().replace("`", "");
                        break;
                    }
                }
            }
            if (txt != null && txt.equals("in")) {
                // the next expr() is the source
                int seenExpr = 0;
                for (int j = 0; j < ctx.getChildCount(); j++) {
                    if (ctx.getChild(j) instanceof dfpp.ast.gen.DfppParser.ExprContext) {
                        if (j > i) { inExprIdx = seenExpr; break; }
                        seenExpr++;
                    }
                }
            }
            if (txt != null && txt.equals("if")) {
                int seenExpr = 0;
                for (int j = 0; j < ctx.getChildCount(); j++) {
                    if (ctx.getChild(j) instanceof dfpp.ast.gen.DfppParser.ExprContext) {
                        if (j > i) { guardExprIdx = seenExpr; break; }
                        seenExpr++;
                    }
                }
            }
        }
        if (hasFor && varName != null && inExprIdx >= 0) {
            Ast.Expr mapExpr = (Ast.Expr) visitExpr(ctx.expr(0));
            Ast.Expr srcExpr = (Ast.Expr) visitExpr(ctx.expr(inExprIdx));
            Ast.Expr guardExpr = guardExprIdx >= 0 && guardExprIdx < ctx.expr().size()
                    ? (Ast.Expr) visitExpr(ctx.expr(guardExprIdx))
                    : null;
            return new Ast.ListComp(mapExpr, varName, srcExpr, guardExpr);
        }
        // Fallback: normal list literal
        var list = new java.util.ArrayList<Ast.Expr>();
        for (var ectx : ctx.expr()) {
            list.add((Ast.Expr) visitExpr(ectx));
        }
        return new Ast.ListLit(list);
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
