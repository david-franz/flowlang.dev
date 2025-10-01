package dfpp.ast.gen;// Generated from /Users/davidfranz/Documents/df++/dfpp/src/main/antlr/Dfpp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DfppParser}.
 */
public interface DfppListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DfppParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DfppParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DfppParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#moduleDecl}.
	 * @param ctx the parse tree
	 */
	void enterModuleDecl(DfppParser.ModuleDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#moduleDecl}.
	 * @param ctx the parse tree
	 */
	void exitModuleDecl(DfppParser.ModuleDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(DfppParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(DfppParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#qid}.
	 * @param ctx the parse tree
	 */
	void enterQid(DfppParser.QidContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#qid}.
	 * @param ctx the parse tree
	 */
	void exitQid(DfppParser.QidContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(DfppParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(DfppParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#topDecl}.
	 * @param ctx the parse tree
	 */
	void enterTopDecl(DfppParser.TopDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#topDecl}.
	 * @param ctx the parse tree
	 */
	void exitTopDecl(DfppParser.TopDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(DfppParser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(DfppParser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#letDecl}.
	 * @param ctx the parse tree
	 */
	void enterLetDecl(DfppParser.LetDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#letDecl}.
	 * @param ctx the parse tree
	 */
	void exitLetDecl(DfppParser.LetDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeDecl(DfppParser.TypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeDecl(DfppParser.TypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#typeParamsDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeParamsDecl(DfppParser.TypeParamsDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#typeParamsDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeParamsDecl(DfppParser.TypeParamsDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#typeParam}.
	 * @param ctx the parse tree
	 */
	void enterTypeParam(DfppParser.TypeParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#typeParam}.
	 * @param ctx the parse tree
	 */
	void exitTypeParam(DfppParser.TypeParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#sumType}.
	 * @param ctx the parse tree
	 */
	void enterSumType(DfppParser.SumTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#sumType}.
	 * @param ctx the parse tree
	 */
	void exitSumType(DfppParser.SumTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#variant}.
	 * @param ctx the parse tree
	 */
	void enterVariant(DfppParser.VariantContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#variant}.
	 * @param ctx the parse tree
	 */
	void exitVariant(DfppParser.VariantContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#fnDecl}.
	 * @param ctx the parse tree
	 */
	void enterFnDecl(DfppParser.FnDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#fnDecl}.
	 * @param ctx the parse tree
	 */
	void exitFnDecl(DfppParser.FnDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(DfppParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(DfppParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(DfppParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(DfppParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#taskDecl}.
	 * @param ctx the parse tree
	 */
	void enterTaskDecl(DfppParser.TaskDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#taskDecl}.
	 * @param ctx the parse tree
	 */
	void exitTaskDecl(DfppParser.TaskDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DfppParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DfppParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(DfppParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(DfppParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#runStmt}.
	 * @param ctx the parse tree
	 */
	void enterRunStmt(DfppParser.RunStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#runStmt}.
	 * @param ctx the parse tree
	 */
	void exitRunStmt(DfppParser.RunStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#parallelStmt}.
	 * @param ctx the parse tree
	 */
	void enterParallelStmt(DfppParser.ParallelStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#parallelStmt}.
	 * @param ctx the parse tree
	 */
	void exitParallelStmt(DfppParser.ParallelStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#stringList}.
	 * @param ctx the parse tree
	 */
	void enterStringList(DfppParser.StringListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#stringList}.
	 * @param ctx the parse tree
	 */
	void exitStringList(DfppParser.StringListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DfppParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DfppParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(DfppParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(DfppParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(DfppParser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(DfppParser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(DfppParser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(DfppParser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(DfppParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(DfppParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(DfppParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(DfppParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#additive}.
	 * @param ctx the parse tree
	 */
	void enterAdditive(DfppParser.AdditiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#additive}.
	 * @param ctx the parse tree
	 */
	void exitAdditive(DfppParser.AdditiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#multiplicative}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative(DfppParser.MultiplicativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#multiplicative}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative(DfppParser.MultiplicativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(DfppParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(DfppParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#postfix}.
	 * @param ctx the parse tree
	 */
	void enterPostfix(DfppParser.PostfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#postfix}.
	 * @param ctx the parse tree
	 */
	void exitPostfix(DfppParser.PostfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#postfixOp}.
	 * @param ctx the parse tree
	 */
	void enterPostfixOp(DfppParser.PostfixOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#postfixOp}.
	 * @param ctx the parse tree
	 */
	void exitPostfixOp(DfppParser.PostfixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(DfppParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(DfppParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DfppParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DfppParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(DfppParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(DfppParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#runCall}.
	 * @param ctx the parse tree
	 */
	void enterRunCall(DfppParser.RunCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#runCall}.
	 * @param ctx the parse tree
	 */
	void exitRunCall(DfppParser.RunCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#matchExpr}.
	 * @param ctx the parse tree
	 */
	void enterMatchExpr(DfppParser.MatchExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#matchExpr}.
	 * @param ctx the parse tree
	 */
	void exitMatchExpr(DfppParser.MatchExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#matchArm}.
	 * @param ctx the parse tree
	 */
	void enterMatchArm(DfppParser.MatchArmContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#matchArm}.
	 * @param ctx the parse tree
	 */
	void exitMatchArm(DfppParser.MatchArmContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(DfppParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(DfppParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#patField}.
	 * @param ctx the parse tree
	 */
	void enterPatField(DfppParser.PatFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#patField}.
	 * @param ctx the parse tree
	 */
	void exitPatField(DfppParser.PatFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#recordLit}.
	 * @param ctx the parse tree
	 */
	void enterRecordLit(DfppParser.RecordLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#recordLit}.
	 * @param ctx the parse tree
	 */
	void exitRecordLit(DfppParser.RecordLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#recField}.
	 * @param ctx the parse tree
	 */
	void enterRecField(DfppParser.RecFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#recField}.
	 * @param ctx the parse tree
	 */
	void exitRecField(DfppParser.RecFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#arrayLit}.
	 * @param ctx the parse tree
	 */
	void enterArrayLit(DfppParser.ArrayLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#arrayLit}.
	 * @param ctx the parse tree
	 */
	void exitArrayLit(DfppParser.ArrayLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RecordType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterRecordType(DfppParser.RecordTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RecordType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitRecordType(DfppParser.RecordTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NamedOrAppliedType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterNamedOrAppliedType(DfppParser.NamedOrAppliedTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NamedOrAppliedType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitNamedOrAppliedType(DfppParser.NamedOrAppliedTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(DfppParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(DfppParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TupleType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterTupleType(DfppParser.TupleTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TupleType}
	 * labeled alternative in {@link DfppParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitTupleType(DfppParser.TupleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#typeArgs}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgs(DfppParser.TypeArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#typeArgs}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgs(DfppParser.TypeArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#typeField}.
	 * @param ctx the parse tree
	 */
	void enterTypeField(DfppParser.TypeFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#typeField}.
	 * @param ctx the parse tree
	 */
	void exitTypeField(DfppParser.TypeFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#lambdaExpr}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(DfppParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#lambdaExpr}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(DfppParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DfppParser#lambdaHead}.
	 * @param ctx the parse tree
	 */
	void enterLambdaHead(DfppParser.LambdaHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link DfppParser#lambdaHead}.
	 * @param ctx the parse tree
	 */
	void exitLambdaHead(DfppParser.LambdaHeadContext ctx);
}