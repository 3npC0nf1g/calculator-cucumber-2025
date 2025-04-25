// Generated from ./LogicExprParser.g4 by ANTLR 4.13.1
package parser.logic;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LogicExprParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, NEG=4, CONJ=5, DISJ=6, IMPLY=7, XORR=8, EQUIV=9, 
		TRUTH=10, SPACE=11;
	public static final int
		RULE_entry = 0, RULE_logicExpr = 1, RULE_infixForm = 2, RULE_prefixForm = 3, 
		RULE_postfixForm = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"entry", "logicExpr", "infixForm", "prefixForm", "postfixForm"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "','", "'NOT'", "'AND'", "'OR'", "'IMPL'", "'XOR'", 
			"'EQ'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "NEG", "CONJ", "DISJ", "IMPLY", "XORR", "EQUIV", 
			"TRUTH", "SPACE"
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
	public String getGrammarFileName() { return "LogicExprParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LogicExprParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EntryContext extends ParserRuleContext {
		public LogicExprContext logicExpr() {
			return getRuleContext(LogicExprContext.class,0);
		}
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			logicExpr();
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
	public static class LogicExprContext extends ParserRuleContext {
		public InfixFormContext infixForm() {
			return getRuleContext(InfixFormContext.class,0);
		}
		public PrefixFormContext prefixForm() {
			return getRuleContext(PrefixFormContext.class,0);
		}
		public PostfixFormContext postfixForm() {
			return getRuleContext(PostfixFormContext.class,0);
		}
		public LogicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterLogicExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitLogicExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitLogicExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExprContext logicExpr() throws RecognitionException {
		LogicExprContext _localctx = new LogicExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_logicExpr);
		try {
			setState(15);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				infixForm(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				prefixForm();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(14);
				postfixForm(0);
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
	public static class InfixFormContext extends ParserRuleContext {
		public InfixFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixForm; }
	 
		public InfixFormContext() { }
		public void copyFrom(InfixFormContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixEquivContext extends InfixFormContext {
		public List<InfixFormContext> infixForm() {
			return getRuleContexts(InfixFormContext.class);
		}
		public InfixFormContext infixForm(int i) {
			return getRuleContext(InfixFormContext.class,i);
		}
		public TerminalNode EQUIV() { return getToken(LogicExprParserParser.EQUIV, 0); }
		public InfixEquivContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixEquiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixEquiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixEquiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixNegationContext extends InfixFormContext {
		public TerminalNode NEG() { return getToken(LogicExprParserParser.NEG, 0); }
		public InfixFormContext infixForm() {
			return getRuleContext(InfixFormContext.class,0);
		}
		public InfixNegationContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixImpliesContext extends InfixFormContext {
		public List<InfixFormContext> infixForm() {
			return getRuleContexts(InfixFormContext.class);
		}
		public InfixFormContext infixForm(int i) {
			return getRuleContext(InfixFormContext.class,i);
		}
		public TerminalNode IMPLY() { return getToken(LogicExprParserParser.IMPLY, 0); }
		public InfixImpliesContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixImplies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixImplies(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixImplies(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixAndContext extends InfixFormContext {
		public List<InfixFormContext> infixForm() {
			return getRuleContexts(InfixFormContext.class);
		}
		public InfixFormContext infixForm(int i) {
			return getRuleContext(InfixFormContext.class,i);
		}
		public TerminalNode CONJ() { return getToken(LogicExprParserParser.CONJ, 0); }
		public InfixAndContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixLiteralContext extends InfixFormContext {
		public TerminalNode TRUTH() { return getToken(LogicExprParserParser.TRUTH, 0); }
		public InfixLiteralContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixOrContext extends InfixFormContext {
		public List<InfixFormContext> infixForm() {
			return getRuleContexts(InfixFormContext.class);
		}
		public InfixFormContext infixForm(int i) {
			return getRuleContext(InfixFormContext.class,i);
		}
		public TerminalNode DISJ() { return getToken(LogicExprParserParser.DISJ, 0); }
		public InfixOrContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixXorContext extends InfixFormContext {
		public List<InfixFormContext> infixForm() {
			return getRuleContexts(InfixFormContext.class);
		}
		public InfixFormContext infixForm(int i) {
			return getRuleContext(InfixFormContext.class,i);
		}
		public TerminalNode XORR() { return getToken(LogicExprParserParser.XORR, 0); }
		public InfixXorContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixXor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixGroupContext extends InfixFormContext {
		public InfixFormContext infixForm() {
			return getRuleContext(InfixFormContext.class,0);
		}
		public InfixGroupContext(InfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterInfixGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitInfixGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitInfixGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfixFormContext infixForm() throws RecognitionException {
		return infixForm(0);
	}

	private InfixFormContext infixForm(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InfixFormContext _localctx = new InfixFormContext(_ctx, _parentState);
		InfixFormContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_infixForm, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEG:
				{
				_localctx = new InfixNegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(18);
				match(NEG);
				setState(19);
				infixForm(8);
				}
				break;
			case TRUTH:
				{
				_localctx = new InfixLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(20);
				match(TRUTH);
				}
				break;
			case T__0:
				{
				_localctx = new InfixGroupContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				match(T__0);
				setState(22);
				infixForm(0);
				setState(23);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(42);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new InfixAndContext(new InfixFormContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixForm);
						setState(27);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(28);
						match(CONJ);
						setState(29);
						infixForm(8);
						}
						break;
					case 2:
						{
						_localctx = new InfixOrContext(new InfixFormContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixForm);
						setState(30);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(31);
						match(DISJ);
						setState(32);
						infixForm(7);
						}
						break;
					case 3:
						{
						_localctx = new InfixImpliesContext(new InfixFormContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixForm);
						setState(33);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(34);
						match(IMPLY);
						setState(35);
						infixForm(6);
						}
						break;
					case 4:
						{
						_localctx = new InfixXorContext(new InfixFormContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixForm);
						setState(36);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(37);
						match(XORR);
						setState(38);
						infixForm(5);
						}
						break;
					case 5:
						{
						_localctx = new InfixEquivContext(new InfixFormContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixForm);
						setState(39);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(40);
						match(EQUIV);
						setState(41);
						infixForm(4);
						}
						break;
					}
					} 
				}
				setState(46);
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
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixFormContext extends ParserRuleContext {
		public PrefixFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixForm; }
	 
		public PrefixFormContext() { }
		public void copyFrom(PrefixFormContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixXorContext extends PrefixFormContext {
		public TerminalNode XORR() { return getToken(LogicExprParserParser.XORR, 0); }
		public List<PrefixFormContext> prefixForm() {
			return getRuleContexts(PrefixFormContext.class);
		}
		public PrefixFormContext prefixForm(int i) {
			return getRuleContext(PrefixFormContext.class,i);
		}
		public PrefixXorContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixXor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixNegationContext extends PrefixFormContext {
		public TerminalNode NEG() { return getToken(LogicExprParserParser.NEG, 0); }
		public PrefixFormContext prefixForm() {
			return getRuleContext(PrefixFormContext.class,0);
		}
		public PrefixNegationContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixImpliesContext extends PrefixFormContext {
		public TerminalNode IMPLY() { return getToken(LogicExprParserParser.IMPLY, 0); }
		public List<PrefixFormContext> prefixForm() {
			return getRuleContexts(PrefixFormContext.class);
		}
		public PrefixFormContext prefixForm(int i) {
			return getRuleContext(PrefixFormContext.class,i);
		}
		public PrefixImpliesContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixImplies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixImplies(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixImplies(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixGroupContext extends PrefixFormContext {
		public PrefixFormContext prefixForm() {
			return getRuleContext(PrefixFormContext.class,0);
		}
		public PrefixGroupContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixGroup(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixOrContext extends PrefixFormContext {
		public TerminalNode DISJ() { return getToken(LogicExprParserParser.DISJ, 0); }
		public List<PrefixFormContext> prefixForm() {
			return getRuleContexts(PrefixFormContext.class);
		}
		public PrefixFormContext prefixForm(int i) {
			return getRuleContext(PrefixFormContext.class,i);
		}
		public PrefixOrContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixEquivContext extends PrefixFormContext {
		public TerminalNode EQUIV() { return getToken(LogicExprParserParser.EQUIV, 0); }
		public List<PrefixFormContext> prefixForm() {
			return getRuleContexts(PrefixFormContext.class);
		}
		public PrefixFormContext prefixForm(int i) {
			return getRuleContext(PrefixFormContext.class,i);
		}
		public PrefixEquivContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixEquiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixEquiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixEquiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixAndContext extends PrefixFormContext {
		public TerminalNode CONJ() { return getToken(LogicExprParserParser.CONJ, 0); }
		public List<PrefixFormContext> prefixForm() {
			return getRuleContexts(PrefixFormContext.class);
		}
		public PrefixFormContext prefixForm(int i) {
			return getRuleContext(PrefixFormContext.class,i);
		}
		public PrefixAndContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixLiteralContext extends PrefixFormContext {
		public TerminalNode TRUTH() { return getToken(LogicExprParserParser.TRUTH, 0); }
		public PrefixLiteralContext(PrefixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPrefixLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPrefixLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPrefixLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixFormContext prefixForm() throws RecognitionException {
		PrefixFormContext _localctx = new PrefixFormContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_prefixForm);
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEG:
				_localctx = new PrefixNegationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				match(NEG);
				setState(48);
				prefixForm();
				}
				break;
			case CONJ:
				_localctx = new PrefixAndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				match(CONJ);
				setState(50);
				match(T__0);
				setState(51);
				prefixForm();
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(54); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(52);
						match(T__2);
						setState(53);
						prefixForm();
						}
						}
						setState(56); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(60);
				match(T__1);
				}
				break;
			case DISJ:
				_localctx = new PrefixOrContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				match(DISJ);
				setState(63);
				match(T__0);
				setState(64);
				prefixForm();
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(67); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(65);
						match(T__2);
						setState(66);
						prefixForm();
						}
						}
						setState(69); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(73);
				match(T__1);
				}
				break;
			case IMPLY:
				_localctx = new PrefixImpliesContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				match(IMPLY);
				setState(76);
				match(T__0);
				setState(77);
				prefixForm();
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(80); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(78);
						match(T__2);
						setState(79);
						prefixForm();
						}
						}
						setState(82); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(86);
				match(T__1);
				}
				break;
			case XORR:
				_localctx = new PrefixXorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(88);
				match(XORR);
				setState(89);
				match(T__0);
				setState(90);
				prefixForm();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(93); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(91);
						match(T__2);
						setState(92);
						prefixForm();
						}
						}
						setState(95); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(99);
				match(T__1);
				}
				break;
			case EQUIV:
				_localctx = new PrefixEquivContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				match(EQUIV);
				setState(102);
				match(T__0);
				setState(103);
				prefixForm();
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(106); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(104);
						match(T__2);
						setState(105);
						prefixForm();
						}
						}
						setState(108); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(112);
				match(T__1);
				}
				break;
			case TRUTH:
				_localctx = new PrefixLiteralContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(114);
				match(TRUTH);
				}
				break;
			case T__0:
				_localctx = new PrefixGroupContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(115);
				match(T__0);
				setState(116);
				prefixForm();
				setState(117);
				match(T__1);
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
	public static class PostfixFormContext extends ParserRuleContext {
		public PostfixFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixForm; }
	 
		public PostfixFormContext() { }
		public void copyFrom(PostfixFormContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixImpliesContext extends PostfixFormContext {
		public List<PostfixFormContext> postfixForm() {
			return getRuleContexts(PostfixFormContext.class);
		}
		public PostfixFormContext postfixForm(int i) {
			return getRuleContext(PostfixFormContext.class,i);
		}
		public TerminalNode IMPLY() { return getToken(LogicExprParserParser.IMPLY, 0); }
		public PostfixImpliesContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixImplies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixImplies(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixImplies(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixNegationContext extends PostfixFormContext {
		public PostfixFormContext postfixForm() {
			return getRuleContext(PostfixFormContext.class,0);
		}
		public TerminalNode NEG() { return getToken(LogicExprParserParser.NEG, 0); }
		public PostfixNegationContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixXorContext extends PostfixFormContext {
		public List<PostfixFormContext> postfixForm() {
			return getRuleContexts(PostfixFormContext.class);
		}
		public PostfixFormContext postfixForm(int i) {
			return getRuleContext(PostfixFormContext.class,i);
		}
		public TerminalNode XORR() { return getToken(LogicExprParserParser.XORR, 0); }
		public PostfixXorContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixXor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixAndContext extends PostfixFormContext {
		public List<PostfixFormContext> postfixForm() {
			return getRuleContexts(PostfixFormContext.class);
		}
		public PostfixFormContext postfixForm(int i) {
			return getRuleContext(PostfixFormContext.class,i);
		}
		public TerminalNode CONJ() { return getToken(LogicExprParserParser.CONJ, 0); }
		public PostfixAndContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixOrContext extends PostfixFormContext {
		public List<PostfixFormContext> postfixForm() {
			return getRuleContexts(PostfixFormContext.class);
		}
		public PostfixFormContext postfixForm(int i) {
			return getRuleContext(PostfixFormContext.class,i);
		}
		public TerminalNode DISJ() { return getToken(LogicExprParserParser.DISJ, 0); }
		public PostfixOrContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixEquivContext extends PostfixFormContext {
		public List<PostfixFormContext> postfixForm() {
			return getRuleContexts(PostfixFormContext.class);
		}
		public PostfixFormContext postfixForm(int i) {
			return getRuleContext(PostfixFormContext.class,i);
		}
		public TerminalNode EQUIV() { return getToken(LogicExprParserParser.EQUIV, 0); }
		public PostfixEquivContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixEquiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixEquiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixEquiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixLiteralContext extends PostfixFormContext {
		public TerminalNode TRUTH() { return getToken(LogicExprParserParser.TRUTH, 0); }
		public PostfixLiteralContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixGroupContext extends PostfixFormContext {
		public PostfixFormContext postfixForm() {
			return getRuleContext(PostfixFormContext.class,0);
		}
		public PostfixGroupContext(PostfixFormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).enterPostfixGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LogicExprParserListener ) ((LogicExprParserListener)listener).exitPostfixGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LogicExprParserVisitor ) return ((LogicExprParserVisitor<? extends T>)visitor).visitPostfixGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixFormContext postfixForm() throws RecognitionException {
		return postfixForm(0);
	}

	private PostfixFormContext postfixForm(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixFormContext _localctx = new PostfixFormContext(_ctx, _parentState);
		PostfixFormContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_postfixForm, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				_localctx = new PostfixAndContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(122);
				match(T__0);
				setState(123);
				postfixForm(0);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(124);
						match(T__2);
						setState(125);
						postfixForm(0);
						}
						}
						setState(128); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(132);
				match(T__1);
				setState(133);
				match(CONJ);
				}
				break;
			case 2:
				{
				_localctx = new PostfixOrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				match(T__0);
				setState(136);
				postfixForm(0);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(139); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(137);
						match(T__2);
						setState(138);
						postfixForm(0);
						}
						}
						setState(141); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(145);
				match(T__1);
				setState(146);
				match(DISJ);
				}
				break;
			case 3:
				{
				_localctx = new PostfixImpliesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(T__0);
				setState(149);
				postfixForm(0);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(152); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(150);
						match(T__2);
						setState(151);
						postfixForm(0);
						}
						}
						setState(154); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(158);
				match(T__1);
				setState(159);
				match(IMPLY);
				}
				break;
			case 4:
				{
				_localctx = new PostfixXorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(T__0);
				setState(162);
				postfixForm(0);
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(165); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(163);
						match(T__2);
						setState(164);
						postfixForm(0);
						}
						}
						setState(167); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(171);
				match(T__1);
				setState(172);
				match(XORR);
				}
				break;
			case 5:
				{
				_localctx = new PostfixEquivContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				match(T__0);
				setState(175);
				postfixForm(0);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(178); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(176);
						match(T__2);
						setState(177);
						postfixForm(0);
						}
						}
						setState(180); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(184);
				match(T__1);
				setState(185);
				match(EQUIV);
				}
				break;
			case 6:
				{
				_localctx = new PostfixLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				match(TRUTH);
				}
				break;
			case 7:
				{
				_localctx = new PostfixGroupContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(188);
				match(T__0);
				setState(189);
				postfixForm(0);
				setState(190);
				match(T__1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PostfixNegationContext(new PostfixFormContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_postfixForm);
					setState(194);
					if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
					setState(195);
					match(NEG);
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return infixForm_sempred((InfixFormContext)_localctx, predIndex);
		case 4:
			return postfixForm_sempred((PostfixFormContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean infixForm_sempred(InfixFormContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean postfixForm_sempred(PostfixFormContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000b\u00ca\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u0010\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u001a\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002+\b\u0002\n\u0002\f\u0002.\t\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0004\u00037\b\u0003\u000b\u0003\f\u00038\u0003\u0003;\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0004\u0003D\b\u0003\u000b\u0003\f\u0003E\u0003\u0003"+
		"H\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0004\u0003Q\b\u0003\u000b\u0003\f\u0003R\u0003"+
		"\u0003U\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0004\u0003^\b\u0003\u000b\u0003\f\u0003"+
		"_\u0003\u0003b\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003k\b\u0003\u000b\u0003"+
		"\f\u0003l\u0003\u0003o\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003x\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u007f"+
		"\b\u0004\u000b\u0004\f\u0004\u0080\u0003\u0004\u0083\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004\u008c\b\u0004\u000b\u0004\f\u0004\u008d\u0003\u0004\u0090"+
		"\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0004\u0004\u0099\b\u0004\u000b\u0004\f\u0004\u009a"+
		"\u0003\u0004\u009d\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00a6\b\u0004\u000b\u0004"+
		"\f\u0004\u00a7\u0003\u0004\u00aa\b\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00b3"+
		"\b\u0004\u000b\u0004\f\u0004\u00b4\u0003\u0004\u00b7\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004\u00c1\b\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"\u00c5\b\u0004\n\u0004\f\u0004\u00c8\t\u0004\u0001\u0004\u0000\u0002\u0004"+
		"\b\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000\u00ef\u0000\n\u0001\u0000"+
		"\u0000\u0000\u0002\u000f\u0001\u0000\u0000\u0000\u0004\u0019\u0001\u0000"+
		"\u0000\u0000\u0006w\u0001\u0000\u0000\u0000\b\u00c0\u0001\u0000\u0000"+
		"\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\u0001\u0001\u0000\u0000\u0000"+
		"\f\u0010\u0003\u0004\u0002\u0000\r\u0010\u0003\u0006\u0003\u0000\u000e"+
		"\u0010\u0003\b\u0004\u0000\u000f\f\u0001\u0000\u0000\u0000\u000f\r\u0001"+
		"\u0000\u0000\u0000\u000f\u000e\u0001\u0000\u0000\u0000\u0010\u0003\u0001"+
		"\u0000\u0000\u0000\u0011\u0012\u0006\u0002\uffff\uffff\u0000\u0012\u0013"+
		"\u0005\u0004\u0000\u0000\u0013\u001a\u0003\u0004\u0002\b\u0014\u001a\u0005"+
		"\n\u0000\u0000\u0015\u0016\u0005\u0001\u0000\u0000\u0016\u0017\u0003\u0004"+
		"\u0002\u0000\u0017\u0018\u0005\u0002\u0000\u0000\u0018\u001a\u0001\u0000"+
		"\u0000\u0000\u0019\u0011\u0001\u0000\u0000\u0000\u0019\u0014\u0001\u0000"+
		"\u0000\u0000\u0019\u0015\u0001\u0000\u0000\u0000\u001a,\u0001\u0000\u0000"+
		"\u0000\u001b\u001c\n\u0007\u0000\u0000\u001c\u001d\u0005\u0005\u0000\u0000"+
		"\u001d+\u0003\u0004\u0002\b\u001e\u001f\n\u0006\u0000\u0000\u001f \u0005"+
		"\u0006\u0000\u0000 +\u0003\u0004\u0002\u0007!\"\n\u0005\u0000\u0000\""+
		"#\u0005\u0007\u0000\u0000#+\u0003\u0004\u0002\u0006$%\n\u0004\u0000\u0000"+
		"%&\u0005\b\u0000\u0000&+\u0003\u0004\u0002\u0005\'(\n\u0003\u0000\u0000"+
		"()\u0005\t\u0000\u0000)+\u0003\u0004\u0002\u0004*\u001b\u0001\u0000\u0000"+
		"\u0000*\u001e\u0001\u0000\u0000\u0000*!\u0001\u0000\u0000\u0000*$\u0001"+
		"\u0000\u0000\u0000*\'\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000\u0000"+
		",*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-\u0005\u0001\u0000"+
		"\u0000\u0000.,\u0001\u0000\u0000\u0000/0\u0005\u0004\u0000\u00000x\u0003"+
		"\u0006\u0003\u000012\u0005\u0005\u0000\u000023\u0005\u0001\u0000\u0000"+
		"3:\u0003\u0006\u0003\u000045\u0005\u0003\u0000\u000057\u0003\u0006\u0003"+
		"\u000064\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u000086\u0001\u0000"+
		"\u0000\u000089\u0001\u0000\u0000\u00009;\u0001\u0000\u0000\u0000:6\u0001"+
		"\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000"+
		"<=\u0005\u0002\u0000\u0000=x\u0001\u0000\u0000\u0000>?\u0005\u0006\u0000"+
		"\u0000?@\u0005\u0001\u0000\u0000@G\u0003\u0006\u0003\u0000AB\u0005\u0003"+
		"\u0000\u0000BD\u0003\u0006\u0003\u0000CA\u0001\u0000\u0000\u0000DE\u0001"+
		"\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"FH\u0001\u0000\u0000\u0000GC\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000"+
		"\u0000HI\u0001\u0000\u0000\u0000IJ\u0005\u0002\u0000\u0000Jx\u0001\u0000"+
		"\u0000\u0000KL\u0005\u0007\u0000\u0000LM\u0005\u0001\u0000\u0000MT\u0003"+
		"\u0006\u0003\u0000NO\u0005\u0003\u0000\u0000OQ\u0003\u0006\u0003\u0000"+
		"PN\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000"+
		"\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000\u0000\u0000TP\u0001\u0000"+
		"\u0000\u0000TU\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0005"+
		"\u0002\u0000\u0000Wx\u0001\u0000\u0000\u0000XY\u0005\b\u0000\u0000YZ\u0005"+
		"\u0001\u0000\u0000Za\u0003\u0006\u0003\u0000[\\\u0005\u0003\u0000\u0000"+
		"\\^\u0003\u0006\u0003\u0000][\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`b\u0001\u0000"+
		"\u0000\u0000a]\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0001"+
		"\u0000\u0000\u0000cd\u0005\u0002\u0000\u0000dx\u0001\u0000\u0000\u0000"+
		"ef\u0005\t\u0000\u0000fg\u0005\u0001\u0000\u0000gn\u0003\u0006\u0003\u0000"+
		"hi\u0005\u0003\u0000\u0000ik\u0003\u0006\u0003\u0000jh\u0001\u0000\u0000"+
		"\u0000kl\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000"+
		"\u0000\u0000mo\u0001\u0000\u0000\u0000nj\u0001\u0000\u0000\u0000no\u0001"+
		"\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pq\u0005\u0002\u0000\u0000"+
		"qx\u0001\u0000\u0000\u0000rx\u0005\n\u0000\u0000st\u0005\u0001\u0000\u0000"+
		"tu\u0003\u0006\u0003\u0000uv\u0005\u0002\u0000\u0000vx\u0001\u0000\u0000"+
		"\u0000w/\u0001\u0000\u0000\u0000w1\u0001\u0000\u0000\u0000w>\u0001\u0000"+
		"\u0000\u0000wK\u0001\u0000\u0000\u0000wX\u0001\u0000\u0000\u0000we\u0001"+
		"\u0000\u0000\u0000wr\u0001\u0000\u0000\u0000ws\u0001\u0000\u0000\u0000"+
		"x\u0007\u0001\u0000\u0000\u0000yz\u0006\u0004\uffff\uffff\u0000z{\u0005"+
		"\u0001\u0000\u0000{\u0082\u0003\b\u0004\u0000|}\u0005\u0003\u0000\u0000"+
		"}\u007f\u0003\b\u0004\u0000~|\u0001\u0000\u0000\u0000\u007f\u0080\u0001"+
		"\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000"+
		"\u0000\u0000\u0081\u0083\u0001\u0000\u0000\u0000\u0082~\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000"+
		"\u0000\u0084\u0085\u0005\u0002\u0000\u0000\u0085\u0086\u0005\u0005\u0000"+
		"\u0000\u0086\u00c1\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u0001\u0000"+
		"\u0000\u0088\u008f\u0003\b\u0004\u0000\u0089\u008a\u0005\u0003\u0000\u0000"+
		"\u008a\u008c\u0003\b\u0004\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0001\u0000\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000\u008f"+
		"\u008b\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u0002\u0000\u0000\u0092"+
		"\u0093\u0005\u0006\u0000\u0000\u0093\u00c1\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0005\u0001\u0000\u0000\u0095\u009c\u0003\b\u0004\u0000\u0096\u0097"+
		"\u0005\u0003\u0000\u0000\u0097\u0099\u0003\b\u0004\u0000\u0098\u0096\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009d\u0001"+
		"\u0000\u0000\u0000\u009c\u0098\u0001\u0000\u0000\u0000\u009c\u009d\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005"+
		"\u0002\u0000\u0000\u009f\u00a0\u0005\u0007\u0000\u0000\u00a0\u00c1\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0005\u0001\u0000\u0000\u00a2\u00a9\u0003"+
		"\b\u0004\u0000\u00a3\u00a4\u0005\u0003\u0000\u0000\u00a4\u00a6\u0003\b"+
		"\u0004\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000\u00a9\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0005\u0002\u0000\u0000\u00ac\u00ad\u0005\b\u0000"+
		"\u0000\u00ad\u00c1\u0001\u0000\u0000\u0000\u00ae\u00af\u0005\u0001\u0000"+
		"\u0000\u00af\u00b6\u0003\b\u0004\u0000\u00b0\u00b1\u0005\u0003\u0000\u0000"+
		"\u00b1\u00b3\u0003\b\u0004\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b7\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005\u0002\u0000\u0000\u00b9"+
		"\u00ba\u0005\t\u0000\u0000\u00ba\u00c1\u0001\u0000\u0000\u0000\u00bb\u00c1"+
		"\u0005\n\u0000\u0000\u00bc\u00bd\u0005\u0001\u0000\u0000\u00bd\u00be\u0003"+
		"\b\u0004\u0000\u00be\u00bf\u0005\u0002\u0000\u0000\u00bf\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c0y\u0001\u0000\u0000\u0000\u00c0\u0087\u0001\u0000\u0000"+
		"\u0000\u00c0\u0094\u0001\u0000\u0000\u0000\u00c0\u00a1\u0001\u0000\u0000"+
		"\u0000\u00c0\u00ae\u0001\u0000\u0000\u0000\u00c0\u00bb\u0001\u0000\u0000"+
		"\u0000\u00c0\u00bc\u0001\u0000\u0000\u0000\u00c1\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\n\b\u0000\u0000\u00c3\u00c5\u0005\u0004\u0000\u0000"+
		"\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c7\t\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u001b"+
		"\u000f\u0019*,8:EGRT_alnw\u0080\u0082\u008d\u008f\u009a\u009c\u00a7\u00a9"+
		"\u00b4\u00b6\u00c0\u00c6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}