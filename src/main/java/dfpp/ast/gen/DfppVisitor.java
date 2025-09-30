package dfpp.ast.gen;// Generated from /Users/davidfranz/Documents/df++/dfpp/src/main/antlr/Dfpp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DfppParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DfppVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DfppParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DfppParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#moduleDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDecl(DfppParser.ModuleDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#importDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecl(DfppParser.ImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#qid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQid(DfppParser.QidContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(DfppParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#topDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopDecl(DfppParser.TopDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(DfppParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#letDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetDecl(DfppParser.LetDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#typeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(DfppParser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#typeParamsDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParamsDecl(DfppParser.TypeParamsDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#typeParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParam(DfppParser.TypeParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#sumType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumType(DfppParser.SumTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#variant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariant(DfppParser.VariantContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#fnDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnDecl(DfppParser.FnDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(DfppParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(DfppParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#taskDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskDecl(DfppParser.TaskDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DfppParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(DfppParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#runStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunStmt(DfppParser.RunStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#parallelStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParallelStmt(DfppParser.ParallelStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#stringList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringList(DfppParser.StringListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DfppParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(DfppParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#logicalOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOr(DfppParser.LogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#logicalAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAnd(DfppParser.LogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(DfppParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#relational}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(DfppParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#additive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive(DfppParser.AdditiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#multiplicative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative(DfppParser.MultiplicativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(DfppParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#postfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix(DfppParser.PostfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#postfixOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixOp(DfppParser.PostfixOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(DfppParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(DfppParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(DfppParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#runCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRunCall(DfppParser.RunCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#matchExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchExpr(DfppParser.MatchExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#matchArm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchArm(DfppParser.MatchArmContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(DfppParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#patField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatField(DfppParser.PatFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#recordLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordLit(DfppParser.RecordLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#recField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecField(DfppParser.RecFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#arrayLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLit(DfppParser.ArrayLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RecordType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordType(DfppParser.RecordTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NamedOrAppliedType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedOrAppliedType(DfppParser.NamedOrAppliedTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionType(DfppParser.FunctionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#typeArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgs(DfppParser.TypeArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#typeField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeField(DfppParser.TypeFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#lambdaExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpr(DfppParser.LambdaExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DfppParser#lambdaHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaHead(DfppParser.LambdaHeadContext ctx);
}