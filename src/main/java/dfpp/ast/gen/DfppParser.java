package dfpp.ast.gen;// Generated from /Users/davidfranz/Documents/df++/dfpp/src/main/antlr/Dfpp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DfppParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, MODULE=2, IMPORT=3, AS=4, TYPE=5, FN=6, TASK=7, PRE=8, ACT=9, 
		POS=10, RUN=11, PARALLEL=12, MATCH=13, CONST=14, LET=15, MUT=16, TRUE=17, 
		FALSE=18, NULL=19, UNDERSCORE=20, INTLIT=21, DECLIT=22, STRING=23, ID=24, 
		BTICKID=25, LP=26, RP=27, LB=28, RB=29, LSB=30, RSB=31, COMMA=32, DOT=33, 
		COLON=34, EQ=35, QMARK=36, ARROW=37, PLUS=38, MINUS=39, STAR=40, SLASH=41, 
		PERC=42, AND=43, OR=44, NOT=45, EE=46, NE=47, LT=48, LE=49, GT=50, GE=51, 
		WS=52, LINE_COMMENT=53, BLOCK_COMMENT=54;
	public static final int
		RULE_program = 0, RULE_moduleDecl = 1, RULE_importDecl = 2, RULE_qid = 3, 
		RULE_ident = 4, RULE_topDecl = 5, RULE_constDecl = 6, RULE_letDecl = 7, 
		RULE_typeDecl = 8, RULE_typeParamsDecl = 9, RULE_typeParam = 10, RULE_sumType = 11, 
		RULE_variant = 12, RULE_fnDecl = 13, RULE_paramList = 14, RULE_param = 15, 
		RULE_taskDecl = 16, RULE_block = 17, RULE_stmt = 18, RULE_runStmt = 19, 
		RULE_parallelStmt = 20, RULE_stringList = 21, RULE_expr = 22, RULE_conditional = 23, 
		RULE_logicalOr = 24, RULE_logicalAnd = 25, RULE_equality = 26, RULE_relational = 27, 
		RULE_additive = 28, RULE_multiplicative = 29, RULE_unary = 30, RULE_postfix = 31, 
		RULE_postfixOp = 32, RULE_primary = 33, RULE_literal = 34, RULE_args = 35, 
		RULE_runCall = 36, RULE_matchExpr = 37, RULE_matchArm = 38, RULE_pattern = 39, 
		RULE_patField = 40, RULE_recordLit = 41, RULE_recField = 42, RULE_arrayLit = 43, 
		RULE_typeRef = 44, RULE_typeArgs = 45, RULE_typeField = 46, RULE_lambdaExpr = 47, 
		RULE_lambdaHead = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "moduleDecl", "importDecl", "qid", "ident", "topDecl", "constDecl", 
			"letDecl", "typeDecl", "typeParamsDecl", "typeParam", "sumType", "variant", 
			"fnDecl", "paramList", "param", "taskDecl", "block", "stmt", "runStmt", 
			"parallelStmt", "stringList", "expr", "conditional", "logicalOr", "logicalAnd", 
			"equality", "relational", "additive", "multiplicative", "unary", "postfix", 
			"postfixOp", "primary", "literal", "args", "runCall", "matchExpr", "matchArm", 
			"pattern", "patField", "recordLit", "recField", "arrayLit", "typeRef", 
			"typeArgs", "typeField", "lambdaExpr", "lambdaHead"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'|'", "'module'", "'import'", "'as'", "'type'", "'fn'", "'task'", 
			"'pre'", "'act'", "'pos'", "'run'", "'parallel'", "'match'", "'const'", 
			"'let'", "'mut'", "'true'", "'false'", "'null'", "'_'", null, null, null, 
			null, null, "'('", "')'", "'{'", "'}'", "'['", "']'", "','", "'.'", "':'", 
			"'='", "'?'", "'->'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&&'", "'||'", 
			"'!'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "MODULE", "IMPORT", "AS", "TYPE", "FN", "TASK", "PRE", "ACT", 
			"POS", "RUN", "PARALLEL", "MATCH", "CONST", "LET", "MUT", "TRUE", "FALSE", 
			"NULL", "UNDERSCORE", "INTLIT", "DECLIT", "STRING", "ID", "BTICKID", 
			"LP", "RP", "LB", "RB", "LSB", "RSB", "COMMA", "DOT", "COLON", "EQ", 
			"QMARK", "ARROW", "PLUS", "MINUS", "STAR", "SLASH", "PERC", "AND", "OR", 
			"NOT", "EE", "NE", "LT", "LE", "GT", "GE", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Dfpp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DfppParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(DfppParser.EOF, 0); }
		public ModuleDeclContext moduleDecl() {
			return getRuleContext(ModuleDeclContext.class,0);
		}
		public List<ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public List<TopDeclContext> topDecl() {
			return getRuleContexts(TopDeclContext.class);
		}
		public TopDeclContext topDecl(int i) {
			return getRuleContext(TopDeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MODULE) {
				{
				setState(98);
				moduleDecl();
				}
			}

			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(101);
				importDecl();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 121056L) != 0)) {
				{
				{
				setState(107);
				topDecl();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleDeclContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(DfppParser.MODULE, 0); }
		public QidContext qid() {
			return getRuleContext(QidContext.class,0);
		}
		public ModuleDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterModuleDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitModuleDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitModuleDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleDeclContext moduleDecl() throws RecognitionException {
		ModuleDeclContext _localctx = new ModuleDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_moduleDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(MODULE);
			setState(116);
			qid();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDeclContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(DfppParser.IMPORT, 0); }
		public QidContext qid() {
			return getRuleContext(QidContext.class,0);
		}
		public TerminalNode AS() { return getToken(DfppParser.AS, 0); }
		public TerminalNode ID() { return getToken(DfppParser.ID, 0); }
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(IMPORT);
			setState(119);
			qid();
			setState(120);
			match(AS);
			setState(121);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QidContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(DfppParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(DfppParser.DOT, i);
		}
		public QidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterQid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitQid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitQid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QidContext qid() throws RecognitionException {
		QidContext _localctx = new QidContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_qid);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			ident();
			setState(128);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(124);
					match(DOT);
					setState(125);
					ident();
					}
					} 
				}
				setState(130);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DfppParser.ID, 0); }
		public TerminalNode BTICKID() { return getToken(DfppParser.BTICKID, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==BTICKID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TopDeclContext extends ParserRuleContext {
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public LetDeclContext letDecl() {
			return getRuleContext(LetDeclContext.class,0);
		}
		public TypeDeclContext typeDecl() {
			return getRuleContext(TypeDeclContext.class,0);
		}
		public FnDeclContext fnDecl() {
			return getRuleContext(FnDeclContext.class,0);
		}
		public TaskDeclContext taskDecl() {
			return getRuleContext(TaskDeclContext.class,0);
		}
		public RunStmtContext runStmt() {
			return getRuleContext(RunStmtContext.class,0);
		}
		public ParallelStmtContext parallelStmt() {
			return getRuleContext(ParallelStmtContext.class,0);
		}
		public TopDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTopDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTopDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTopDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopDeclContext topDecl() throws RecognitionException {
		TopDeclContext _localctx = new TopDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_topDecl);
		try {
			setState(140);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONST:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				constDecl();
				}
				break;
			case LET:
			case MUT:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				letDecl();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				typeDecl();
				}
				break;
			case FN:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				fnDecl();
				}
				break;
			case TASK:
				enterOuterAlt(_localctx, 5);
				{
				setState(137);
				taskDecl();
				}
				break;
			case RUN:
				enterOuterAlt(_localctx, 6);
				{
				setState(138);
				runStmt();
				}
				break;
			case PARALLEL:
				enterOuterAlt(_localctx, 7);
				{
				setState(139);
				parallelStmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstDeclContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(DfppParser.CONST, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode EQ() { return getToken(DfppParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DfppParser.COLON, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public ConstDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterConstDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitConstDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitConstDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(CONST);
			setState(143);
			ident();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(144);
				match(COLON);
				setState(145);
				typeRef(0);
				}
			}

			setState(148);
			match(EQ);
			setState(149);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetDeclContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode EQ() { return getToken(DfppParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LET() { return getToken(DfppParser.LET, 0); }
		public TerminalNode MUT() { return getToken(DfppParser.MUT, 0); }
		public TerminalNode COLON() { return getToken(DfppParser.COLON, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public LetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterLetDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitLetDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitLetDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetDeclContext letDecl() throws RecognitionException {
		LetDeclContext _localctx = new LetDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_letDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_la = _input.LA(1);
			if ( !(_la==LET || _la==MUT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(152);
			ident();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(153);
				match(COLON);
				setState(154);
				typeRef(0);
				}
			}

			setState(157);
			match(EQ);
			setState(158);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDeclContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(DfppParser.TYPE, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode EQ() { return getToken(DfppParser.EQ, 0); }
		public SumTypeContext sumType() {
			return getRuleContext(SumTypeContext.class,0);
		}
		public TypeParamsDeclContext typeParamsDecl() {
			return getRuleContext(TypeParamsDeclContext.class,0);
		}
		public TypeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTypeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclContext typeDecl() throws RecognitionException {
		TypeDeclContext _localctx = new TypeDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(TYPE);
			setState(161);
			ident();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(162);
				typeParamsDecl();
				}
			}

			setState(165);
			match(EQ);
			setState(166);
			sumType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeParamsDeclContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(DfppParser.LT, 0); }
		public List<TypeParamContext> typeParam() {
			return getRuleContexts(TypeParamContext.class);
		}
		public TypeParamContext typeParam(int i) {
			return getRuleContext(TypeParamContext.class,i);
		}
		public TerminalNode GT() { return getToken(DfppParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public TypeParamsDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParamsDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTypeParamsDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTypeParamsDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTypeParamsDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParamsDeclContext typeParamsDecl() throws RecognitionException {
		TypeParamsDeclContext _localctx = new TypeParamsDeclContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeParamsDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(LT);
			setState(169);
			typeParam();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(170);
				match(COMMA);
				setState(171);
				typeParam();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeParamContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TypeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTypeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTypeParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTypeParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParamContext typeParam() throws RecognitionException {
		TypeParamContext _localctx = new TypeParamContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SumTypeContext extends ParserRuleContext {
		public List<VariantContext> variant() {
			return getRuleContexts(VariantContext.class);
		}
		public VariantContext variant(int i) {
			return getRuleContext(VariantContext.class,i);
		}
		public SumTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sumType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterSumType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitSumType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitSumType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SumTypeContext sumType() throws RecognitionException {
		SumTypeContext _localctx = new SumTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sumType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			variant();
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(182);
				match(T__0);
				setState(183);
				variant();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariantContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public List<TypeRefContext> typeRef() {
			return getRuleContexts(TypeRefContext.class);
		}
		public TypeRefContext typeRef(int i) {
			return getRuleContext(TypeRefContext.class,i);
		}
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public VariantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterVariant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitVariant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitVariant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariantContext variant() throws RecognitionException {
		VariantContext _localctx = new VariantContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			ident();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(190);
				match(LP);
				setState(191);
				typeRef(0);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(192);
					match(COMMA);
					setState(193);
					typeRef(0);
					}
					}
					setState(198);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(199);
				match(RP);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FnDeclContext extends ParserRuleContext {
		public TerminalNode FN() { return getToken(DfppParser.FN, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public TerminalNode EQ() { return getToken(DfppParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DfppParser.COLON, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FnDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterFnDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitFnDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitFnDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnDeclContext fnDecl() throws RecognitionException {
		FnDeclContext _localctx = new FnDeclContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fnDecl);
		int _la;
		try {
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				match(FN);
				setState(204);
				ident();
				setState(205);
				match(LP);
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID || _la==BTICKID) {
					{
					setState(206);
					paramList();
					}
				}

				setState(209);
				match(RP);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(210);
					match(COLON);
					setState(211);
					typeRef(0);
					}
				}

				setState(214);
				match(EQ);
				setState(215);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				match(FN);
				setState(218);
				ident();
				setState(219);
				match(LP);
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID || _la==BTICKID) {
					{
					setState(220);
					paramList();
					}
				}

				setState(223);
				match(RP);
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(224);
					match(COLON);
					setState(225);
					typeRef(0);
					}
				}

				setState(228);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			param();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(233);
				match(COMMA);
				setState(234);
				param();
				}
				}
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DfppParser.COLON, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			ident();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(241);
				match(COLON);
				setState(242);
				typeRef(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TaskDeclContext extends ParserRuleContext {
		public TerminalNode TASK() { return getToken(DfppParser.TASK, 0); }
		public TerminalNode STRING() { return getToken(DfppParser.STRING, 0); }
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public TerminalNode ACT() { return getToken(DfppParser.ACT, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public TerminalNode PRE() { return getToken(DfppParser.PRE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode POS() { return getToken(DfppParser.POS, 0); }
		public TaskDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taskDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTaskDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTaskDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTaskDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TaskDeclContext taskDecl() throws RecognitionException {
		TaskDeclContext _localctx = new TaskDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_taskDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(TASK);
			setState(246);
			match(STRING);
			setState(247);
			match(LB);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRE) {
				{
				setState(248);
				match(PRE);
				setState(249);
				expr();
				}
			}

			setState(252);
			match(ACT);
			setState(253);
			block();
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POS) {
				{
				setState(254);
				match(POS);
				setState(255);
				expr();
				}
			}

			setState(258);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(LB);
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(261);
					stmt();
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35185847314432L) != 0)) {
				{
				setState(267);
				expr();
				}
			}

			setState(270);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public LetDeclContext letDecl() {
			return getRuleContext(LetDeclContext.class,0);
		}
		public RunStmtContext runStmt() {
			return getRuleContext(RunStmtContext.class,0);
		}
		public ParallelStmtContext parallelStmt() {
			return getRuleContext(ParallelStmtContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stmt);
		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				constDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				letDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				runStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				parallelStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(276);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RunStmtContext extends ParserRuleContext {
		public RunCallContext runCall() {
			return getRuleContext(RunCallContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(DfppParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(DfppParser.DOT, i);
		}
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public List<TerminalNode> LP() { return getTokens(DfppParser.LP); }
		public TerminalNode LP(int i) {
			return getToken(DfppParser.LP, i);
		}
		public List<TerminalNode> RP() { return getTokens(DfppParser.RP); }
		public TerminalNode RP(int i) {
			return getToken(DfppParser.RP, i);
		}
		public List<ArgsContext> args() {
			return getRuleContexts(ArgsContext.class);
		}
		public ArgsContext args(int i) {
			return getRuleContext(ArgsContext.class,i);
		}
		public RunStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_runStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterRunStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitRunStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitRunStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RunStmtContext runStmt() throws RecognitionException {
		RunStmtContext _localctx = new RunStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_runStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			runCall();
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(280);
				match(DOT);
				setState(281);
				ident();
				setState(282);
				match(LP);
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35185847314432L) != 0)) {
					{
					setState(283);
					args();
					}
				}

				setState(286);
				match(RP);
				}
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParallelStmtContext extends ParserRuleContext {
		public TerminalNode PARALLEL() { return getToken(DfppParser.PARALLEL, 0); }
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public ParallelStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parallelStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterParallelStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitParallelStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitParallelStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParallelStmtContext parallelStmt() throws RecognitionException {
		ParallelStmtContext _localctx = new ParallelStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parallelStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(PARALLEL);
			setState(294);
			match(LB);
			setState(295);
			stringList();
			setState(296);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringListContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(DfppParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(DfppParser.STRING, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public StringListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterStringList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitStringList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitStringList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringListContext stringList() throws RecognitionException {
		StringListContext _localctx = new StringListContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(STRING);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(299);
				match(COMMA);
				setState(300);
				match(STRING);
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			conditional();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalContext extends ParserRuleContext {
		public LogicalOrContext logicalOr() {
			return getRuleContext(LogicalOrContext.class,0);
		}
		public TerminalNode QMARK() { return getToken(DfppParser.QMARK, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COLON() { return getToken(DfppParser.COLON, 0); }
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_conditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			logicalOr();
			setState(314);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(309);
				match(QMARK);
				setState(310);
				expr();
				setState(311);
				match(COLON);
				setState(312);
				expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrContext extends ParserRuleContext {
		public List<LogicalAndContext> logicalAnd() {
			return getRuleContexts(LogicalAndContext.class);
		}
		public LogicalAndContext logicalAnd(int i) {
			return getRuleContext(LogicalAndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(DfppParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(DfppParser.OR, i);
		}
		public LogicalOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitLogicalOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrContext logicalOr() throws RecognitionException {
		LogicalOrContext _localctx = new LogicalOrContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_logicalOr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			logicalAnd();
			setState(321);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(317);
					match(OR);
					setState(318);
					logicalAnd();
					}
					} 
				}
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndContext extends ParserRuleContext {
		public List<EqualityContext> equality() {
			return getRuleContexts(EqualityContext.class);
		}
		public EqualityContext equality(int i) {
			return getRuleContext(EqualityContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(DfppParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(DfppParser.AND, i);
		}
		public LogicalAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitLogicalAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndContext logicalAnd() throws RecognitionException {
		LogicalAndContext _localctx = new LogicalAndContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_logicalAnd);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			equality();
			setState(329);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(325);
					match(AND);
					setState(326);
					equality();
					}
					} 
				}
				setState(331);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualityContext extends ParserRuleContext {
		public List<RelationalContext> relational() {
			return getRuleContexts(RelationalContext.class);
		}
		public RelationalContext relational(int i) {
			return getRuleContext(RelationalContext.class,i);
		}
		public List<TerminalNode> EE() { return getTokens(DfppParser.EE); }
		public TerminalNode EE(int i) {
			return getToken(DfppParser.EE, i);
		}
		public List<TerminalNode> NE() { return getTokens(DfppParser.NE); }
		public TerminalNode NE(int i) {
			return getToken(DfppParser.NE, i);
		}
		public EqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityContext equality() throws RecognitionException {
		EqualityContext _localctx = new EqualityContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_equality);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			relational();
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(333);
					_la = _input.LA(1);
					if ( !(_la==EE || _la==NE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(334);
					relational();
					}
					} 
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationalContext extends ParserRuleContext {
		public List<AdditiveContext> additive() {
			return getRuleContexts(AdditiveContext.class);
		}
		public AdditiveContext additive(int i) {
			return getRuleContext(AdditiveContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(DfppParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(DfppParser.LT, i);
		}
		public List<TerminalNode> LE() { return getTokens(DfppParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(DfppParser.LE, i);
		}
		public List<TerminalNode> GT() { return getTokens(DfppParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(DfppParser.GT, i);
		}
		public List<TerminalNode> GE() { return getTokens(DfppParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(DfppParser.GE, i);
		}
		public RelationalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitRelational(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalContext relational() throws RecognitionException {
		RelationalContext _localctx = new RelationalContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_relational);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			additive();
			setState(345);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(341);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4222124650659840L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(342);
					additive();
					}
					} 
				}
				setState(347);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveContext extends ParserRuleContext {
		public List<MultiplicativeContext> multiplicative() {
			return getRuleContexts(MultiplicativeContext.class);
		}
		public MultiplicativeContext multiplicative(int i) {
			return getRuleContext(MultiplicativeContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(DfppParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(DfppParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(DfppParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(DfppParser.MINUS, i);
		}
		public AdditiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterAdditive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitAdditive(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitAdditive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveContext additive() throws RecognitionException {
		AdditiveContext _localctx = new AdditiveContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_additive);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			multiplicative();
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(349);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(350);
					multiplicative();
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeContext extends ParserRuleContext {
		public List<UnaryContext> unary() {
			return getRuleContexts(UnaryContext.class);
		}
		public UnaryContext unary(int i) {
			return getRuleContext(UnaryContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(DfppParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(DfppParser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(DfppParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(DfppParser.SLASH, i);
		}
		public List<TerminalNode> PERC() { return getTokens(DfppParser.PERC); }
		public TerminalNode PERC(int i) {
			return getToken(DfppParser.PERC, i);
		}
		public MultiplicativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterMultiplicative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitMultiplicative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitMultiplicative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeContext multiplicative() throws RecognitionException {
		MultiplicativeContext _localctx = new MultiplicativeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_multiplicative);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			unary();
			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(357);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7696581394432L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(358);
					unary();
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(DfppParser.NOT, 0); }
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public PostfixContext postfix() {
			return getRuleContext(PostfixContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_unary);
		try {
			setState(367);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(364);
				match(NOT);
				setState(365);
				unary();
				}
				break;
			case RUN:
			case MATCH:
			case TRUE:
			case FALSE:
			case NULL:
			case INTLIT:
			case DECLIT:
			case STRING:
			case ID:
			case BTICKID:
			case LP:
			case LB:
			case LSB:
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				postfix();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public List<PostfixOpContext> postfixOp() {
			return getRuleContexts(PostfixOpContext.class);
		}
		public PostfixOpContext postfixOp(int i) {
			return getRuleContext(PostfixOpContext.class,i);
		}
		public PostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_postfix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			primary();
			setState(373);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(370);
					postfixOp();
					}
					} 
				}
				setState(375);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixOpContext extends ParserRuleContext {
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode DOT() { return getToken(DfppParser.DOT, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LSB() { return getToken(DfppParser.LSB, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RSB() { return getToken(DfppParser.RSB, 0); }
		public PostfixOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterPostfixOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitPostfixOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitPostfixOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixOpContext postfixOp() throws RecognitionException {
		PostfixOpContext _localctx = new PostfixOpContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_postfixOp);
		int _la;
		try {
			setState(395);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(LP);
				setState(378);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35185847314432L) != 0)) {
					{
					setState(377);
					args();
					}
				}

				setState(380);
				match(RP);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				match(DOT);
				setState(382);
				ident();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(383);
				match(DOT);
				setState(384);
				ident();
				setState(385);
				match(LP);
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35185847314432L) != 0)) {
					{
					setState(386);
					args();
					}
				}

				setState(389);
				match(RP);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(391);
				match(LSB);
				setState(392);
				expr();
				setState(393);
				match(RSB);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public RecordLitContext recordLit() {
			return getRuleContext(RecordLitContext.class,0);
		}
		public ArrayLitContext arrayLit() {
			return getRuleContext(ArrayLitContext.class,0);
		}
		public MatchExprContext matchExpr() {
			return getRuleContext(MatchExprContext.class,0);
		}
		public RunCallContext runCall() {
			return getRuleContext(RunCallContext.class,0);
		}
		public LambdaExprContext lambdaExpr() {
			return getRuleContext(LambdaExprContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_primary);
		try {
			setState(408);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(397);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(398);
				match(LP);
				setState(399);
				expr();
				setState(400);
				match(RP);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(402);
				recordLit();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(403);
				arrayLit();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(404);
				matchExpr();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(405);
				runCall();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(406);
				lambdaExpr();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(407);
				ident();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTLIT() { return getToken(DfppParser.INTLIT, 0); }
		public TerminalNode DECLIT() { return getToken(DfppParser.DECLIT, 0); }
		public TerminalNode STRING() { return getToken(DfppParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(DfppParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(DfppParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(DfppParser.NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15597568L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			expr();
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(413);
				match(COMMA);
				setState(414);
				expr();
				}
				}
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RunCallContext extends ParserRuleContext {
		public TerminalNode RUN() { return getToken(DfppParser.RUN, 0); }
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public TerminalNode STRING() { return getToken(DfppParser.STRING, 0); }
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public RunCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_runCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterRunCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitRunCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitRunCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RunCallContext runCall() throws RecognitionException {
		RunCallContext _localctx = new RunCallContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_runCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(RUN);
			setState(421);
			match(LP);
			setState(422);
			match(STRING);
			setState(423);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchExprContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(DfppParser.MATCH, 0); }
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public List<MatchArmContext> matchArm() {
			return getRuleContexts(MatchArmContext.class);
		}
		public MatchArmContext matchArm(int i) {
			return getRuleContext(MatchArmContext.class,i);
		}
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public MatchExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterMatchExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitMatchExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitMatchExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchExprContext matchExpr() throws RecognitionException {
		MatchExprContext _localctx = new MatchExprContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_matchExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			match(MATCH);
			setState(426);
			match(LP);
			setState(427);
			expr();
			setState(428);
			match(RP);
			setState(429);
			match(LB);
			setState(430);
			matchArm();
			setState(435);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(431);
					match(COMMA);
					setState(432);
					matchArm();
					}
					} 
				}
				setState(437);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			setState(439);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(438);
				match(COMMA);
				}
			}

			setState(441);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchArmContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(DfppParser.ARROW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MatchArmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchArm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterMatchArm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitMatchArm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitMatchArm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchArmContext matchArm() throws RecognitionException {
		MatchArmContext _localctx = new MatchArmContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_matchArm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			pattern();
			setState(444);
			match(ARROW);
			setState(445);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public List<PatFieldContext> patField() {
			return getRuleContexts(PatFieldContext.class);
		}
		public PatFieldContext patField(int i) {
			return getRuleContext(PatFieldContext.class,i);
		}
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode UNDERSCORE() { return getToken(DfppParser.UNDERSCORE, 0); }
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_pattern);
		int _la;
		try {
			setState(475);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case BTICKID:
				enterOuterAlt(_localctx, 1);
				{
				setState(447);
				ident();
				setState(460);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LP) {
					{
					setState(448);
					match(LP);
					setState(457);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID || _la==BTICKID) {
						{
						setState(449);
						ident();
						setState(454);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(450);
							match(COMMA);
							setState(451);
							ident();
							}
							}
							setState(456);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(459);
					match(RP);
					}
				}

				}
				break;
			case LB:
				enterOuterAlt(_localctx, 2);
				{
				setState(462);
				match(LB);
				setState(463);
				patField();
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(464);
					match(COMMA);
					setState(465);
					patField();
					}
					}
					setState(470);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(471);
				match(RB);
				}
				break;
			case TRUE:
			case FALSE:
			case NULL:
			case INTLIT:
			case DECLIT:
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(473);
				literal();
				}
				break;
			case UNDERSCORE:
				enterOuterAlt(_localctx, 4);
				{
				setState(474);
				match(UNDERSCORE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatFieldContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode EQ() { return getToken(DfppParser.EQ, 0); }
		public PatFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterPatField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitPatField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitPatField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatFieldContext patField() throws RecognitionException {
		PatFieldContext _localctx = new PatFieldContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_patField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477);
			ident();
			setState(478);
			match(EQ);
			setState(479);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordLitContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public List<RecFieldContext> recField() {
			return getRuleContexts(RecFieldContext.class);
		}
		public RecFieldContext recField(int i) {
			return getRuleContext(RecFieldContext.class,i);
		}
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public RecordLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterRecordLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitRecordLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitRecordLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordLitContext recordLit() throws RecognitionException {
		RecordLitContext _localctx = new RecordLitContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_recordLit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			match(LB);
			setState(482);
			recField();
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(483);
				match(COMMA);
				setState(484);
				recField();
				}
				}
				setState(489);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(490);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecFieldContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode EQ() { return getToken(DfppParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RecFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterRecField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitRecField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitRecField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecFieldContext recField() throws RecognitionException {
		RecFieldContext _localctx = new RecFieldContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_recField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			ident();
			setState(493);
			match(EQ);
			setState(494);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLitContext extends ParserRuleContext {
		public TerminalNode LSB() { return getToken(DfppParser.LSB, 0); }
		public TerminalNode RSB() { return getToken(DfppParser.RSB, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public ArrayLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterArrayLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitArrayLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitArrayLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLitContext arrayLit() throws RecognitionException {
		ArrayLitContext _localctx = new ArrayLitContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_arrayLit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			match(LSB);
			setState(505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35185847314432L) != 0)) {
				{
				setState(497);
				expr();
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(498);
					match(COMMA);
					setState(499);
					expr();
					}
					}
					setState(504);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(507);
			match(RSB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeRefContext extends ParserRuleContext {
		public TypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeRef; }
	 
		public TypeRefContext() { }
		public void copyFrom(TypeRefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RecordTypeContext extends TypeRefContext {
		public TerminalNode LB() { return getToken(DfppParser.LB, 0); }
		public List<TypeFieldContext> typeField() {
			return getRuleContexts(TypeFieldContext.class);
		}
		public TypeFieldContext typeField(int i) {
			return getRuleContext(TypeFieldContext.class,i);
		}
		public TerminalNode RB() { return getToken(DfppParser.RB, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public RecordTypeContext(TypeRefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterRecordType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitRecordType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitRecordType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NamedOrAppliedTypeContext extends TypeRefContext {
		public QidContext qid() {
			return getRuleContext(QidContext.class,0);
		}
		public TypeArgsContext typeArgs() {
			return getRuleContext(TypeArgsContext.class,0);
		}
		public NamedOrAppliedTypeContext(TypeRefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterNamedOrAppliedType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitNamedOrAppliedType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitNamedOrAppliedType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionTypeContext extends TypeRefContext {
		public List<TypeRefContext> typeRef() {
			return getRuleContexts(TypeRefContext.class);
		}
		public TypeRefContext typeRef(int i) {
			return getRuleContext(TypeRefContext.class,i);
		}
		public TerminalNode ARROW() { return getToken(DfppParser.ARROW, 0); }
		public FunctionTypeContext(TypeRefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitFunctionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeRefContext typeRef() throws RecognitionException {
		return typeRef(0);
	}

	private TypeRefContext typeRef(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeRefContext _localctx = new TypeRefContext(_ctx, _parentState);
		TypeRefContext _prevctx = _localctx;
		int _startState = 88;
		enterRecursionRule(_localctx, 88, RULE_typeRef, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case BTICKID:
				{
				_localctx = new NamedOrAppliedTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(510);
				qid();
				setState(512);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(511);
					typeArgs();
					}
					break;
				}
				}
				break;
			case LB:
				{
				_localctx = new RecordTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(514);
				match(LB);
				setState(515);
				typeField();
				setState(520);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(516);
					match(COMMA);
					setState(517);
					typeField();
					}
					}
					setState(522);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(523);
				match(RB);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(532);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FunctionTypeContext(new TypeRefContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_typeRef);
					setState(527);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(528);
					match(ARROW);
					setState(529);
					typeRef(2);
					}
					} 
				}
				setState(534);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeArgsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(DfppParser.LT, 0); }
		public List<TypeRefContext> typeRef() {
			return getRuleContexts(TypeRefContext.class);
		}
		public TypeRefContext typeRef(int i) {
			return getRuleContext(TypeRefContext.class,i);
		}
		public TerminalNode GT() { return getToken(DfppParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public TypeArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTypeArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTypeArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTypeArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgsContext typeArgs() throws RecognitionException {
		TypeArgsContext _localctx = new TypeArgsContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_typeArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			match(LT);
			setState(536);
			typeRef(0);
			setState(541);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(537);
				match(COMMA);
				setState(538);
				typeRef(0);
				}
				}
				setState(543);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(544);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeFieldContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DfppParser.COLON, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TypeFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterTypeField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitTypeField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitTypeField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeFieldContext typeField() throws RecognitionException {
		TypeFieldContext _localctx = new TypeFieldContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_typeField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			ident();
			setState(547);
			match(COLON);
			setState(548);
			typeRef(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaExprContext extends ParserRuleContext {
		public LambdaHeadContext lambdaHead() {
			return getRuleContext(LambdaHeadContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(DfppParser.ARROW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LambdaExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExprContext lambdaExpr() throws RecognitionException {
		LambdaExprContext _localctx = new LambdaExprContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_lambdaExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			lambdaHead();
			setState(551);
			match(ARROW);
			setState(552);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaHeadContext extends ParserRuleContext {
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode LP() { return getToken(DfppParser.LP, 0); }
		public TerminalNode RP() { return getToken(DfppParser.RP, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DfppParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DfppParser.COMMA, i);
		}
		public LambdaHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).enterLambdaHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DfppListener ) ((DfppListener)listener).exitLambdaHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DfppVisitor ) return ((DfppVisitor<? extends T>)visitor).visitLambdaHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaHeadContext lambdaHead() throws RecognitionException {
		LambdaHeadContext _localctx = new LambdaHeadContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_lambdaHead);
		int _la;
		try {
			setState(567);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case BTICKID:
				enterOuterAlt(_localctx, 1);
				{
				setState(554);
				ident();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 2);
				{
				setState(555);
				match(LP);
				setState(564);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID || _la==BTICKID) {
					{
					setState(556);
					ident();
					setState(561);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(557);
						match(COMMA);
						setState(558);
						ident();
						}
						}
						setState(563);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(566);
				match(RP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 44:
			return typeRef_sempred((TypeRefContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean typeRef_sempred(TypeRefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00016\u023a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u0001\u0000\u0003\u0000"+
		"d\b\u0000\u0001\u0000\u0005\u0000g\b\u0000\n\u0000\f\u0000j\t\u0000\u0001"+
		"\u0000\u0005\u0000m\b\u0000\n\u0000\f\u0000p\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003\u007f\b\u0003\n\u0003\f\u0003\u0082\t\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u008d\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u0093\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u009c\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u00a4"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00ad"+
		"\b\t\n\t\f\t\u00b0\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u00b9\b\u000b\n\u000b\f\u000b\u00bc\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00c3\b\f\n\f\f\f\u00c6"+
		"\t\f\u0001\f\u0001\f\u0003\f\u00ca\b\f\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0003\r\u00d0\b\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d5\b\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00de\b\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u00e3\b\r\u0001\r\u0001\r\u0003\r\u00e7\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000e\u00ec\b\u000e\n\u000e\f\u000e\u00ef"+
		"\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00f4\b\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00fb\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u0101\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u0107\b\u0011\n\u0011\f\u0011\u010a\t\u0011\u0001\u0011\u0003\u0011\u010d"+
		"\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u0116\b\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u011d\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u0121\b\u0013\n\u0013\f\u0013\u0124\t\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0005\u0015\u012e\b\u0015\n\u0015\f\u0015\u0131\t\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u013b\b\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0005\u0018\u0140\b\u0018\n\u0018\f\u0018\u0143\t\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0005\u0019\u0148\b\u0019\n\u0019\f\u0019\u014b"+
		"\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u0150\b\u001a"+
		"\n\u001a\f\u001a\u0153\t\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0005"+
		"\u001b\u0158\b\u001b\n\u001b\f\u001b\u015b\t\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u0160\b\u001c\n\u001c\f\u001c\u0163\t\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u0168\b\u001d\n\u001d\f\u001d"+
		"\u016b\t\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0170\b"+
		"\u001e\u0001\u001f\u0001\u001f\u0005\u001f\u0174\b\u001f\n\u001f\f\u001f"+
		"\u0177\t\u001f\u0001 \u0001 \u0003 \u017b\b \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0003 \u0184\b \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0003 \u018c\b \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0003!\u0199\b!\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001#\u0005#\u01a0\b#\n#\f#\u01a3\t#\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0005%\u01b2"+
		"\b%\n%\f%\u01b5\t%\u0001%\u0003%\u01b8\b%\u0001%\u0001%\u0001&\u0001&"+
		"\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0005\'\u01c5\b"+
		"\'\n\'\f\'\u01c8\t\'\u0003\'\u01ca\b\'\u0001\'\u0003\'\u01cd\b\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0005\'\u01d3\b\'\n\'\f\'\u01d6\t\'\u0001\'"+
		"\u0001\'\u0001\'\u0001\'\u0003\'\u01dc\b\'\u0001(\u0001(\u0001(\u0001"+
		"(\u0001)\u0001)\u0001)\u0001)\u0005)\u01e6\b)\n)\f)\u01e9\t)\u0001)\u0001"+
		")\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0005+\u01f5"+
		"\b+\n+\f+\u01f8\t+\u0003+\u01fa\b+\u0001+\u0001+\u0001,\u0001,\u0001,"+
		"\u0003,\u0201\b,\u0001,\u0001,\u0001,\u0001,\u0005,\u0207\b,\n,\f,\u020a"+
		"\t,\u0001,\u0001,\u0003,\u020e\b,\u0001,\u0001,\u0001,\u0005,\u0213\b"+
		",\n,\f,\u0216\t,\u0001-\u0001-\u0001-\u0001-\u0005-\u021c\b-\n-\f-\u021f"+
		"\t-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001"+
		"/\u00010\u00010\u00010\u00010\u00010\u00050\u0230\b0\n0\f0\u0233\t0\u0003"+
		"0\u0235\b0\u00010\u00030\u0238\b0\u00010\u0000\u0001X1\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`\u0000\u0007\u0001\u0000\u0018\u0019\u0001"+
		"\u0000\u000f\u0010\u0001\u0000./\u0001\u000003\u0001\u0000&\'\u0001\u0000"+
		"(*\u0002\u0000\u0011\u0013\u0015\u0017\u0255\u0000c\u0001\u0000\u0000"+
		"\u0000\u0002s\u0001\u0000\u0000\u0000\u0004v\u0001\u0000\u0000\u0000\u0006"+
		"{\u0001\u0000\u0000\u0000\b\u0083\u0001\u0000\u0000\u0000\n\u008c\u0001"+
		"\u0000\u0000\u0000\f\u008e\u0001\u0000\u0000\u0000\u000e\u0097\u0001\u0000"+
		"\u0000\u0000\u0010\u00a0\u0001\u0000\u0000\u0000\u0012\u00a8\u0001\u0000"+
		"\u0000\u0000\u0014\u00b3\u0001\u0000\u0000\u0000\u0016\u00b5\u0001\u0000"+
		"\u0000\u0000\u0018\u00bd\u0001\u0000\u0000\u0000\u001a\u00e6\u0001\u0000"+
		"\u0000\u0000\u001c\u00e8\u0001\u0000\u0000\u0000\u001e\u00f0\u0001\u0000"+
		"\u0000\u0000 \u00f5\u0001\u0000\u0000\u0000\"\u0104\u0001\u0000\u0000"+
		"\u0000$\u0115\u0001\u0000\u0000\u0000&\u0117\u0001\u0000\u0000\u0000("+
		"\u0125\u0001\u0000\u0000\u0000*\u012a\u0001\u0000\u0000\u0000,\u0132\u0001"+
		"\u0000\u0000\u0000.\u0134\u0001\u0000\u0000\u00000\u013c\u0001\u0000\u0000"+
		"\u00002\u0144\u0001\u0000\u0000\u00004\u014c\u0001\u0000\u0000\u00006"+
		"\u0154\u0001\u0000\u0000\u00008\u015c\u0001\u0000\u0000\u0000:\u0164\u0001"+
		"\u0000\u0000\u0000<\u016f\u0001\u0000\u0000\u0000>\u0171\u0001\u0000\u0000"+
		"\u0000@\u018b\u0001\u0000\u0000\u0000B\u0198\u0001\u0000\u0000\u0000D"+
		"\u019a\u0001\u0000\u0000\u0000F\u019c\u0001\u0000\u0000\u0000H\u01a4\u0001"+
		"\u0000\u0000\u0000J\u01a9\u0001\u0000\u0000\u0000L\u01bb\u0001\u0000\u0000"+
		"\u0000N\u01db\u0001\u0000\u0000\u0000P\u01dd\u0001\u0000\u0000\u0000R"+
		"\u01e1\u0001\u0000\u0000\u0000T\u01ec\u0001\u0000\u0000\u0000V\u01f0\u0001"+
		"\u0000\u0000\u0000X\u020d\u0001\u0000\u0000\u0000Z\u0217\u0001\u0000\u0000"+
		"\u0000\\\u0222\u0001\u0000\u0000\u0000^\u0226\u0001\u0000\u0000\u0000"+
		"`\u0237\u0001\u0000\u0000\u0000bd\u0003\u0002\u0001\u0000cb\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000dh\u0001\u0000\u0000\u0000eg\u0003"+
		"\u0004\u0002\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000in\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000km\u0003\n\u0005\u0000lk\u0001\u0000\u0000"+
		"\u0000mp\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000"+
		"\u0000\u0000oq\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000qr\u0005"+
		"\u0000\u0000\u0001r\u0001\u0001\u0000\u0000\u0000st\u0005\u0002\u0000"+
		"\u0000tu\u0003\u0006\u0003\u0000u\u0003\u0001\u0000\u0000\u0000vw\u0005"+
		"\u0003\u0000\u0000wx\u0003\u0006\u0003\u0000xy\u0005\u0004\u0000\u0000"+
		"yz\u0005\u0018\u0000\u0000z\u0005\u0001\u0000\u0000\u0000{\u0080\u0003"+
		"\b\u0004\u0000|}\u0005!\u0000\u0000}\u007f\u0003\b\u0004\u0000~|\u0001"+
		"\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000\u0000\u0080~\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0007\u0001\u0000"+
		"\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0084\u0007\u0000"+
		"\u0000\u0000\u0084\t\u0001\u0000\u0000\u0000\u0085\u008d\u0003\f\u0006"+
		"\u0000\u0086\u008d\u0003\u000e\u0007\u0000\u0087\u008d\u0003\u0010\b\u0000"+
		"\u0088\u008d\u0003\u001a\r\u0000\u0089\u008d\u0003 \u0010\u0000\u008a"+
		"\u008d\u0003&\u0013\u0000\u008b\u008d\u0003(\u0014\u0000\u008c\u0085\u0001"+
		"\u0000\u0000\u0000\u008c\u0086\u0001\u0000\u0000\u0000\u008c\u0087\u0001"+
		"\u0000\u0000\u0000\u008c\u0088\u0001\u0000\u0000\u0000\u008c\u0089\u0001"+
		"\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008b\u0001"+
		"\u0000\u0000\u0000\u008d\u000b\u0001\u0000\u0000\u0000\u008e\u008f\u0005"+
		"\u000e\u0000\u0000\u008f\u0092\u0003\b\u0004\u0000\u0090\u0091\u0005\""+
		"\u0000\u0000\u0091\u0093\u0003X,\u0000\u0092\u0090\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000"+
		"\u0094\u0095\u0005#\u0000\u0000\u0095\u0096\u0003,\u0016\u0000\u0096\r"+
		"\u0001\u0000\u0000\u0000\u0097\u0098\u0007\u0001\u0000\u0000\u0098\u009b"+
		"\u0003\b\u0004\u0000\u0099\u009a\u0005\"\u0000\u0000\u009a\u009c\u0003"+
		"X,\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0005#\u0000\u0000"+
		"\u009e\u009f\u0003,\u0016\u0000\u009f\u000f\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a1\u0005\u0005\u0000\u0000\u00a1\u00a3\u0003\b\u0004\u0000\u00a2\u00a4"+
		"\u0003\u0012\t\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005"+
		"#\u0000\u0000\u00a6\u00a7\u0003\u0016\u000b\u0000\u00a7\u0011\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u00050\u0000\u0000\u00a9\u00ae\u0003\u0014\n"+
		"\u0000\u00aa\u00ab\u0005 \u0000\u0000\u00ab\u00ad\u0003\u0014\n\u0000"+
		"\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000\u0000\u0000"+
		"\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000"+
		"\u00af\u00b1\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b2\u00052\u0000\u0000\u00b2\u0013\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b4\u0003\b\u0004\u0000\u00b4\u0015\u0001\u0000\u0000\u0000\u00b5\u00ba"+
		"\u0003\u0018\f\u0000\u00b6\u00b7\u0005\u0001\u0000\u0000\u00b7\u00b9\u0003"+
		"\u0018\f\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00bc\u0001\u0000"+
		"\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000"+
		"\u0000\u0000\u00bb\u0017\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bd\u00c9\u0003\b\u0004\u0000\u00be\u00bf\u0005\u001a\u0000"+
		"\u0000\u00bf\u00c4\u0003X,\u0000\u00c0\u00c1\u0005 \u0000\u0000\u00c1"+
		"\u00c3\u0003X,\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c7\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0005\u001b\u0000\u0000\u00c8\u00ca\u0001"+
		"\u0000\u0000\u0000\u00c9\u00be\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001"+
		"\u0000\u0000\u0000\u00ca\u0019\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005"+
		"\u0006\u0000\u0000\u00cc\u00cd\u0003\b\u0004\u0000\u00cd\u00cf\u0005\u001a"+
		"\u0000\u0000\u00ce\u00d0\u0003\u001c\u000e\u0000\u00cf\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d4\u0005\u001b\u0000\u0000\u00d2\u00d3\u0005\"\u0000"+
		"\u0000\u00d3\u00d5\u0003X,\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d7\u0005#\u0000\u0000\u00d7\u00d8\u0003,\u0016\u0000\u00d8\u00e7\u0001"+
		"\u0000\u0000\u0000\u00d9\u00da\u0005\u0006\u0000\u0000\u00da\u00db\u0003"+
		"\b\u0004\u0000\u00db\u00dd\u0005\u001a\u0000\u0000\u00dc\u00de\u0003\u001c"+
		"\u000e\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e2\u0005\u001b"+
		"\u0000\u0000\u00e0\u00e1\u0005\"\u0000\u0000\u00e1\u00e3\u0003X,\u0000"+
		"\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e5\u0003\"\u0011\u0000\u00e5"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e6\u00cb\u0001\u0000\u0000\u0000\u00e6"+
		"\u00d9\u0001\u0000\u0000\u0000\u00e7\u001b\u0001\u0000\u0000\u0000\u00e8"+
		"\u00ed\u0003\u001e\u000f\u0000\u00e9\u00ea\u0005 \u0000\u0000\u00ea\u00ec"+
		"\u0003\u001e\u000f\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee"+
		"\u0001\u0000\u0000\u0000\u00ee\u001d\u0001\u0000\u0000\u0000\u00ef\u00ed"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f3\u0003\b\u0004\u0000\u00f1\u00f2\u0005"+
		"\"\u0000\u0000\u00f2\u00f4\u0003X,\u0000\u00f3\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u001f\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f6\u0005\u0007\u0000\u0000\u00f6\u00f7\u0005\u0017\u0000"+
		"\u0000\u00f7\u00fa\u0005\u001c\u0000\u0000\u00f8\u00f9\u0005\b\u0000\u0000"+
		"\u00f9\u00fb\u0003,\u0016\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fd\u0005\t\u0000\u0000\u00fd\u0100\u0003\"\u0011\u0000\u00fe\u00ff"+
		"\u0005\n\u0000\u0000\u00ff\u0101\u0003,\u0016\u0000\u0100\u00fe\u0001"+
		"\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0001"+
		"\u0000\u0000\u0000\u0102\u0103\u0005\u001d\u0000\u0000\u0103!\u0001\u0000"+
		"\u0000\u0000\u0104\u0108\u0005\u001c\u0000\u0000\u0105\u0107\u0003$\u0012"+
		"\u0000\u0106\u0105\u0001\u0000\u0000\u0000\u0107\u010a\u0001\u0000\u0000"+
		"\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000"+
		"\u0000\u0109\u010c\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000"+
		"\u0000\u010b\u010d\u0003,\u0016\u0000\u010c\u010b\u0001\u0000\u0000\u0000"+
		"\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0005\u001d\u0000\u0000\u010f#\u0001\u0000\u0000\u0000\u0110"+
		"\u0116\u0003\f\u0006\u0000\u0111\u0116\u0003\u000e\u0007\u0000\u0112\u0116"+
		"\u0003&\u0013\u0000\u0113\u0116\u0003(\u0014\u0000\u0114\u0116\u0003,"+
		"\u0016\u0000\u0115\u0110\u0001\u0000\u0000\u0000\u0115\u0111\u0001\u0000"+
		"\u0000\u0000\u0115\u0112\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000"+
		"\u0000\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0116%\u0001\u0000\u0000"+
		"\u0000\u0117\u0122\u0003H$\u0000\u0118\u0119\u0005!\u0000\u0000\u0119"+
		"\u011a\u0003\b\u0004\u0000\u011a\u011c\u0005\u001a\u0000\u0000\u011b\u011d"+
		"\u0003F#\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000"+
		"\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u011f\u0005\u001b"+
		"\u0000\u0000\u011f\u0121\u0001\u0000\u0000\u0000\u0120\u0118\u0001\u0000"+
		"\u0000\u0000\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000"+
		"\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\'\u0001\u0000\u0000"+
		"\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0125\u0126\u0005\f\u0000\u0000"+
		"\u0126\u0127\u0005\u001c\u0000\u0000\u0127\u0128\u0003*\u0015\u0000\u0128"+
		"\u0129\u0005\u001d\u0000\u0000\u0129)\u0001\u0000\u0000\u0000\u012a\u012f"+
		"\u0005\u0017\u0000\u0000\u012b\u012c\u0005 \u0000\u0000\u012c\u012e\u0005"+
		"\u0017\u0000\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u0131\u0001"+
		"\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u012f\u0130\u0001"+
		"\u0000\u0000\u0000\u0130+\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000"+
		"\u0000\u0000\u0132\u0133\u0003.\u0017\u0000\u0133-\u0001\u0000\u0000\u0000"+
		"\u0134\u013a\u00030\u0018\u0000\u0135\u0136\u0005$\u0000\u0000\u0136\u0137"+
		"\u0003,\u0016\u0000\u0137\u0138\u0005\"\u0000\u0000\u0138\u0139\u0003"+
		",\u0016\u0000\u0139\u013b\u0001\u0000\u0000\u0000\u013a\u0135\u0001\u0000"+
		"\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b/\u0001\u0000\u0000"+
		"\u0000\u013c\u0141\u00032\u0019\u0000\u013d\u013e\u0005,\u0000\u0000\u013e"+
		"\u0140\u00032\u0019\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u0140\u0143"+
		"\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0141\u0142"+
		"\u0001\u0000\u0000\u0000\u01421\u0001\u0000\u0000\u0000\u0143\u0141\u0001"+
		"\u0000\u0000\u0000\u0144\u0149\u00034\u001a\u0000\u0145\u0146\u0005+\u0000"+
		"\u0000\u0146\u0148\u00034\u001a\u0000\u0147\u0145\u0001\u0000\u0000\u0000"+
		"\u0148\u014b\u0001\u0000\u0000\u0000\u0149\u0147\u0001\u0000\u0000\u0000"+
		"\u0149\u014a\u0001\u0000\u0000\u0000\u014a3\u0001\u0000\u0000\u0000\u014b"+
		"\u0149\u0001\u0000\u0000\u0000\u014c\u0151\u00036\u001b\u0000\u014d\u014e"+
		"\u0007\u0002\u0000\u0000\u014e\u0150\u00036\u001b\u0000\u014f\u014d\u0001"+
		"\u0000\u0000\u0000\u0150\u0153\u0001\u0000\u0000\u0000\u0151\u014f\u0001"+
		"\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u01525\u0001\u0000"+
		"\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0154\u0159\u00038\u001c"+
		"\u0000\u0155\u0156\u0007\u0003\u0000\u0000\u0156\u0158\u00038\u001c\u0000"+
		"\u0157\u0155\u0001\u0000\u0000\u0000\u0158\u015b\u0001\u0000\u0000\u0000"+
		"\u0159\u0157\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000"+
		"\u015a7\u0001\u0000\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015c"+
		"\u0161\u0003:\u001d\u0000\u015d\u015e\u0007\u0004\u0000\u0000\u015e\u0160"+
		"\u0003:\u001d\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u0163\u0001"+
		"\u0000\u0000\u0000\u0161\u015f\u0001\u0000\u0000\u0000\u0161\u0162\u0001"+
		"\u0000\u0000\u0000\u01629\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000"+
		"\u0000\u0000\u0164\u0169\u0003<\u001e\u0000\u0165\u0166\u0007\u0005\u0000"+
		"\u0000\u0166\u0168\u0003<\u001e\u0000\u0167\u0165\u0001\u0000\u0000\u0000"+
		"\u0168\u016b\u0001\u0000\u0000\u0000\u0169\u0167\u0001\u0000\u0000\u0000"+
		"\u0169\u016a\u0001\u0000\u0000\u0000\u016a;\u0001\u0000\u0000\u0000\u016b"+
		"\u0169\u0001\u0000\u0000\u0000\u016c\u016d\u0005-\u0000\u0000\u016d\u0170"+
		"\u0003<\u001e\u0000\u016e\u0170\u0003>\u001f\u0000\u016f\u016c\u0001\u0000"+
		"\u0000\u0000\u016f\u016e\u0001\u0000\u0000\u0000\u0170=\u0001\u0000\u0000"+
		"\u0000\u0171\u0175\u0003B!\u0000\u0172\u0174\u0003@ \u0000\u0173\u0172"+
		"\u0001\u0000\u0000\u0000\u0174\u0177\u0001\u0000\u0000\u0000\u0175\u0173"+
		"\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176?\u0001"+
		"\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000\u0178\u017a\u0005"+
		"\u001a\u0000\u0000\u0179\u017b\u0003F#\u0000\u017a\u0179\u0001\u0000\u0000"+
		"\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000"+
		"\u0000\u017c\u018c\u0005\u001b\u0000\u0000\u017d\u017e\u0005!\u0000\u0000"+
		"\u017e\u018c\u0003\b\u0004\u0000\u017f\u0180\u0005!\u0000\u0000\u0180"+
		"\u0181\u0003\b\u0004\u0000\u0181\u0183\u0005\u001a\u0000\u0000\u0182\u0184"+
		"\u0003F#\u0000\u0183\u0182\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000"+
		"\u0000\u0000\u0184\u0185\u0001\u0000\u0000\u0000\u0185\u0186\u0005\u001b"+
		"\u0000\u0000\u0186\u018c\u0001\u0000\u0000\u0000\u0187\u0188\u0005\u001e"+
		"\u0000\u0000\u0188\u0189\u0003,\u0016\u0000\u0189\u018a\u0005\u001f\u0000"+
		"\u0000\u018a\u018c\u0001\u0000\u0000\u0000\u018b\u0178\u0001\u0000\u0000"+
		"\u0000\u018b\u017d\u0001\u0000\u0000\u0000\u018b\u017f\u0001\u0000\u0000"+
		"\u0000\u018b\u0187\u0001\u0000\u0000\u0000\u018cA\u0001\u0000\u0000\u0000"+
		"\u018d\u0199\u0003D\"\u0000\u018e\u018f\u0005\u001a\u0000\u0000\u018f"+
		"\u0190\u0003,\u0016\u0000\u0190\u0191\u0005\u001b\u0000\u0000\u0191\u0199"+
		"\u0001\u0000\u0000\u0000\u0192\u0199\u0003R)\u0000\u0193\u0199\u0003V"+
		"+\u0000\u0194\u0199\u0003J%\u0000\u0195\u0199\u0003H$\u0000\u0196\u0199"+
		"\u0003^/\u0000\u0197\u0199\u0003\b\u0004\u0000\u0198\u018d\u0001\u0000"+
		"\u0000\u0000\u0198\u018e\u0001\u0000\u0000\u0000\u0198\u0192\u0001\u0000"+
		"\u0000\u0000\u0198\u0193\u0001\u0000\u0000\u0000\u0198\u0194\u0001\u0000"+
		"\u0000\u0000\u0198\u0195\u0001\u0000\u0000\u0000\u0198\u0196\u0001\u0000"+
		"\u0000\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0199C\u0001\u0000\u0000"+
		"\u0000\u019a\u019b\u0007\u0006\u0000\u0000\u019bE\u0001\u0000\u0000\u0000"+
		"\u019c\u01a1\u0003,\u0016\u0000\u019d\u019e\u0005 \u0000\u0000\u019e\u01a0"+
		"\u0003,\u0016\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u01a0\u01a3\u0001"+
		"\u0000\u0000\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a1\u01a2\u0001"+
		"\u0000\u0000\u0000\u01a2G\u0001\u0000\u0000\u0000\u01a3\u01a1\u0001\u0000"+
		"\u0000\u0000\u01a4\u01a5\u0005\u000b\u0000\u0000\u01a5\u01a6\u0005\u001a"+
		"\u0000\u0000\u01a6\u01a7\u0005\u0017\u0000\u0000\u01a7\u01a8\u0005\u001b"+
		"\u0000\u0000\u01a8I\u0001\u0000\u0000\u0000\u01a9\u01aa\u0005\r\u0000"+
		"\u0000\u01aa\u01ab\u0005\u001a\u0000\u0000\u01ab\u01ac\u0003,\u0016\u0000"+
		"\u01ac\u01ad\u0005\u001b\u0000\u0000\u01ad\u01ae\u0005\u001c\u0000\u0000"+
		"\u01ae\u01b3\u0003L&\u0000\u01af\u01b0\u0005 \u0000\u0000\u01b0\u01b2"+
		"\u0003L&\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b2\u01b5\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000"+
		"\u0000\u0000\u01b4\u01b7\u0001\u0000\u0000\u0000\u01b5\u01b3\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b8\u0005 \u0000\u0000\u01b7\u01b6\u0001\u0000\u0000"+
		"\u0000\u01b7\u01b8\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000"+
		"\u0000\u01b9\u01ba\u0005\u001d\u0000\u0000\u01baK\u0001\u0000\u0000\u0000"+
		"\u01bb\u01bc\u0003N\'\u0000\u01bc\u01bd\u0005%\u0000\u0000\u01bd\u01be"+
		"\u0003,\u0016\u0000\u01beM\u0001\u0000\u0000\u0000\u01bf\u01cc\u0003\b"+
		"\u0004\u0000\u01c0\u01c9\u0005\u001a\u0000\u0000\u01c1\u01c6\u0003\b\u0004"+
		"\u0000\u01c2\u01c3\u0005 \u0000\u0000\u01c3\u01c5\u0003\b\u0004\u0000"+
		"\u01c4\u01c2\u0001\u0000\u0000\u0000\u01c5\u01c8\u0001\u0000\u0000\u0000"+
		"\u01c6\u01c4\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000\u0000"+
		"\u01c7\u01ca\u0001\u0000\u0000\u0000\u01c8\u01c6\u0001\u0000\u0000\u0000"+
		"\u01c9\u01c1\u0001\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000"+
		"\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cb\u01cd\u0005\u001b\u0000\u0000"+
		"\u01cc\u01c0\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000"+
		"\u01cd\u01dc\u0001\u0000\u0000\u0000\u01ce\u01cf\u0005\u001c\u0000\u0000"+
		"\u01cf\u01d4\u0003P(\u0000\u01d0\u01d1\u0005 \u0000\u0000\u01d1\u01d3"+
		"\u0003P(\u0000\u01d2\u01d0\u0001\u0000\u0000\u0000\u01d3\u01d6\u0001\u0000"+
		"\u0000\u0000\u01d4\u01d2\u0001\u0000\u0000\u0000\u01d4\u01d5\u0001\u0000"+
		"\u0000\u0000\u01d5\u01d7\u0001\u0000\u0000\u0000\u01d6\u01d4\u0001\u0000"+
		"\u0000\u0000\u01d7\u01d8\u0005\u001d\u0000\u0000\u01d8\u01dc\u0001\u0000"+
		"\u0000\u0000\u01d9\u01dc\u0003D\"\u0000\u01da\u01dc\u0005\u0014\u0000"+
		"\u0000\u01db\u01bf\u0001\u0000\u0000\u0000\u01db\u01ce\u0001\u0000\u0000"+
		"\u0000\u01db\u01d9\u0001\u0000\u0000\u0000\u01db\u01da\u0001\u0000\u0000"+
		"\u0000\u01dcO\u0001\u0000\u0000\u0000\u01dd\u01de\u0003\b\u0004\u0000"+
		"\u01de\u01df\u0005#\u0000\u0000\u01df\u01e0\u0003\b\u0004\u0000\u01e0"+
		"Q\u0001\u0000\u0000\u0000\u01e1\u01e2\u0005\u001c\u0000\u0000\u01e2\u01e7"+
		"\u0003T*\u0000\u01e3\u01e4\u0005 \u0000\u0000\u01e4\u01e6\u0003T*\u0000"+
		"\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e6\u01e9\u0001\u0000\u0000\u0000"+
		"\u01e7\u01e5\u0001\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000"+
		"\u01e8\u01ea\u0001\u0000\u0000\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000"+
		"\u01ea\u01eb\u0005\u001d\u0000\u0000\u01ebS\u0001\u0000\u0000\u0000\u01ec"+
		"\u01ed\u0003\b\u0004\u0000\u01ed\u01ee\u0005#\u0000\u0000\u01ee\u01ef"+
		"\u0003,\u0016\u0000\u01efU\u0001\u0000\u0000\u0000\u01f0\u01f9\u0005\u001e"+
		"\u0000\u0000\u01f1\u01f6\u0003,\u0016\u0000\u01f2\u01f3\u0005 \u0000\u0000"+
		"\u01f3\u01f5\u0003,\u0016\u0000\u01f4\u01f2\u0001\u0000\u0000\u0000\u01f5"+
		"\u01f8\u0001\u0000\u0000\u0000\u01f6\u01f4\u0001\u0000\u0000\u0000\u01f6"+
		"\u01f7\u0001\u0000\u0000\u0000\u01f7\u01fa\u0001\u0000\u0000\u0000\u01f8"+
		"\u01f6\u0001\u0000\u0000\u0000\u01f9\u01f1\u0001\u0000\u0000\u0000\u01f9"+
		"\u01fa\u0001\u0000\u0000\u0000\u01fa\u01fb\u0001\u0000\u0000\u0000\u01fb"+
		"\u01fc\u0005\u001f\u0000\u0000\u01fcW\u0001\u0000\u0000\u0000\u01fd\u01fe"+
		"\u0006,\uffff\uffff\u0000\u01fe\u0200\u0003\u0006\u0003\u0000\u01ff\u0201"+
		"\u0003Z-\u0000\u0200\u01ff\u0001\u0000\u0000\u0000\u0200\u0201\u0001\u0000"+
		"\u0000\u0000\u0201\u020e\u0001\u0000\u0000\u0000\u0202\u0203\u0005\u001c"+
		"\u0000\u0000\u0203\u0208\u0003\\.\u0000\u0204\u0205\u0005 \u0000\u0000"+
		"\u0205\u0207\u0003\\.\u0000\u0206\u0204\u0001\u0000\u0000\u0000\u0207"+
		"\u020a\u0001\u0000\u0000\u0000\u0208\u0206\u0001\u0000\u0000\u0000\u0208"+
		"\u0209\u0001\u0000\u0000\u0000\u0209\u020b\u0001\u0000\u0000\u0000\u020a"+
		"\u0208\u0001\u0000\u0000\u0000\u020b\u020c\u0005\u001d\u0000\u0000\u020c"+
		"\u020e\u0001\u0000\u0000\u0000\u020d\u01fd\u0001\u0000\u0000\u0000\u020d"+
		"\u0202\u0001\u0000\u0000\u0000\u020e\u0214\u0001\u0000\u0000\u0000\u020f"+
		"\u0210\n\u0001\u0000\u0000\u0210\u0211\u0005%\u0000\u0000\u0211\u0213"+
		"\u0003X,\u0002\u0212\u020f\u0001\u0000\u0000\u0000\u0213\u0216\u0001\u0000"+
		"\u0000\u0000\u0214\u0212\u0001\u0000\u0000\u0000\u0214\u0215\u0001\u0000"+
		"\u0000\u0000\u0215Y\u0001\u0000\u0000\u0000\u0216\u0214\u0001\u0000\u0000"+
		"\u0000\u0217\u0218\u00050\u0000\u0000\u0218\u021d\u0003X,\u0000\u0219"+
		"\u021a\u0005 \u0000\u0000\u021a\u021c\u0003X,\u0000\u021b\u0219\u0001"+
		"\u0000\u0000\u0000\u021c\u021f\u0001\u0000\u0000\u0000\u021d\u021b\u0001"+
		"\u0000\u0000\u0000\u021d\u021e\u0001\u0000\u0000\u0000\u021e\u0220\u0001"+
		"\u0000\u0000\u0000\u021f\u021d\u0001\u0000\u0000\u0000\u0220\u0221\u0005"+
		"2\u0000\u0000\u0221[\u0001\u0000\u0000\u0000\u0222\u0223\u0003\b\u0004"+
		"\u0000\u0223\u0224\u0005\"\u0000\u0000\u0224\u0225\u0003X,\u0000\u0225"+
		"]\u0001\u0000\u0000\u0000\u0226\u0227\u0003`0\u0000\u0227\u0228\u0005"+
		"%\u0000\u0000\u0228\u0229\u0003,\u0016\u0000\u0229_\u0001\u0000\u0000"+
		"\u0000\u022a\u0238\u0003\b\u0004\u0000\u022b\u0234\u0005\u001a\u0000\u0000"+
		"\u022c\u0231\u0003\b\u0004\u0000\u022d\u022e\u0005 \u0000\u0000\u022e"+
		"\u0230\u0003\b\u0004\u0000\u022f\u022d\u0001\u0000\u0000\u0000\u0230\u0233"+
		"\u0001\u0000\u0000\u0000\u0231\u022f\u0001\u0000\u0000\u0000\u0231\u0232"+
		"\u0001\u0000\u0000\u0000\u0232\u0235\u0001\u0000\u0000\u0000\u0233\u0231"+
		"\u0001\u0000\u0000\u0000\u0234\u022c\u0001\u0000\u0000\u0000\u0234\u0235"+
		"\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000\u0000\u0000\u0236\u0238"+
		"\u0005\u001b\u0000\u0000\u0237\u022a\u0001\u0000\u0000\u0000\u0237\u022b"+
		"\u0001\u0000\u0000\u0000\u0238a\u0001\u0000\u0000\u0000;chn\u0080\u008c"+
		"\u0092\u009b\u00a3\u00ae\u00ba\u00c4\u00c9\u00cf\u00d4\u00dd\u00e2\u00e6"+
		"\u00ed\u00f3\u00fa\u0100\u0108\u010c\u0115\u011c\u0122\u012f\u013a\u0141"+
		"\u0149\u0151\u0159\u0161\u0169\u016f\u0175\u017a\u0183\u018b\u0198\u01a1"+
		"\u01b3\u01b7\u01c6\u01c9\u01cc\u01d4\u01db\u01e7\u01f6\u01f9\u0200\u0208"+
		"\u020d\u0214\u021d\u0231\u0234\u0237";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}