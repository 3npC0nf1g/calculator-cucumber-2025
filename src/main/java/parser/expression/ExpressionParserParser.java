// Generated from ExpressionParser.g4 by ANTLR 4.13.1
package parser.expression;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import parser.expression.ExpressionParserVisitor;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExpressionParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, MULT=4, DIV=5, PLUS=6, MINUS=7, NUM=8, SPACE=9;
	public static final int
		RULE_start = 0, RULE_expression = 1, RULE_infixExpr = 2, RULE_prefixExpr = 3, 
		RULE_postfixExpr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "expression", "infixExpr", "prefixExpr", "postfixExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "','", "'*'", "'/'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "MULT", "DIV", "PLUS", "MINUS", "NUM", "SPACE"
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
	public String getGrammarFileName() { return "ExpressionParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor) return ((ExpressionParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			expression();
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
	public static class ExpressionContext extends ParserRuleContext {
		public InfixExprContext infixExpr() {
			return getRuleContext(InfixExprContext.class,0);
		}
		public PrefixExprContext prefixExpr() {
			return getRuleContext(PrefixExprContext.class,0);
		}
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(15);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				infixExpr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				prefixExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(14);
				postfixExpr();
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
	public static class InfixExprContext extends ParserRuleContext {
		public InfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixExpr; }
	 
		public InfixExprContext() { }
		public void copyFrom(InfixExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixIntegerContext extends InfixExprContext {
		public TerminalNode NUM() { return getToken(ExpressionParserParser.NUM, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParserParser.MINUS, 0); }
		public InfixIntegerContext(InfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInfixInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInfixInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInfixInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixMulDivContext extends InfixExprContext {
		public List<InfixExprContext> infixExpr() {
			return getRuleContexts(InfixExprContext.class);
		}
		public InfixExprContext infixExpr(int i) {
			return getRuleContext(InfixExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(ExpressionParserParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExpressionParserParser.DIV, 0); }
		public InfixMulDivContext(InfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInfixMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInfixMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInfixMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixAddSubContext extends InfixExprContext {
		public List<InfixExprContext> infixExpr() {
			return getRuleContexts(InfixExprContext.class);
		}
		public InfixExprContext infixExpr(int i) {
			return getRuleContext(InfixExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ExpressionParserParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParserParser.MINUS, 0); }
		public InfixAddSubContext(InfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInfixAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInfixAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInfixAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixGroupedContext extends InfixExprContext {
		public InfixExprContext infixExpr() {
			return getRuleContext(InfixExprContext.class,0);
		}
		public InfixGroupedContext(InfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInfixGrouped(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInfixGrouped(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInfixGrouped(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfixExprContext infixExpr() throws RecognitionException {
		return infixExpr(0);
	}

	private InfixExprContext infixExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InfixExprContext _localctx = new InfixExprContext(_ctx, _parentState);
		InfixExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_infixExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case NUM:
				{
				_localctx = new InfixIntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(18);
					match(MINUS);
					}
				}

				setState(21);
				match(NUM);
				}
				break;
			case T__0:
				{
				_localctx = new InfixGroupedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(T__0);
				setState(23);
				infixExpr(0);
				setState(24);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(34);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new InfixMulDivContext(new InfixExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixExpr);
						setState(28);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(29);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(30);
						infixExpr(5);
						}
						break;
					case 2:
						{
						_localctx = new InfixAddSubContext(new InfixExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_infixExpr);
						setState(31);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(32);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(33);
						infixExpr(4);
						}
						break;
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
	public static class PrefixExprContext extends ParserRuleContext {
		public PrefixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixExpr; }
	 
		public PrefixExprContext() { }
		public void copyFrom(PrefixExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixGroupedContext extends PrefixExprContext {
		public PrefixExprContext prefixExpr() {
			return getRuleContext(PrefixExprContext.class,0);
		}
		public PrefixGroupedContext(PrefixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPrefixGrouped(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPrefixGrouped(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPrefixGrouped(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixMulDivContext extends PrefixExprContext {
		public List<PrefixExprContext> prefixExpr() {
			return getRuleContexts(PrefixExprContext.class);
		}
		public PrefixExprContext prefixExpr(int i) {
			return getRuleContext(PrefixExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(ExpressionParserParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExpressionParserParser.DIV, 0); }
		public PrefixMulDivContext(PrefixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPrefixMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPrefixMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPrefixMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixAddSubContext extends PrefixExprContext {
		public List<PrefixExprContext> prefixExpr() {
			return getRuleContexts(PrefixExprContext.class);
		}
		public PrefixExprContext prefixExpr(int i) {
			return getRuleContext(PrefixExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ExpressionParserParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParserParser.MINUS, 0); }
		public PrefixAddSubContext(PrefixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPrefixAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPrefixAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPrefixAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixIntegerContext extends PrefixExprContext {
		public TerminalNode NUM() { return getToken(ExpressionParserParser.NUM, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParserParser.MINUS, 0); }
		public PrefixIntegerContext(PrefixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPrefixInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPrefixInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPrefixInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixExprContext prefixExpr() throws RecognitionException {
		PrefixExprContext _localctx = new PrefixExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_prefixExpr);
		int _la;
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new PrefixMulDivContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				_la = _input.LA(1);
				if ( !(_la==MULT || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(40);
				match(T__0);
				setState(41);
				prefixExpr();
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(44); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(42);
						match(T__2);
						setState(43);
						prefixExpr();
						}
						}
						setState(46); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(50);
				match(T__1);
				}
				break;
			case 2:
				_localctx = new PrefixAddSubContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(53);
				match(T__0);
				setState(54);
				prefixExpr();
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(57); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(55);
						match(T__2);
						setState(56);
						prefixExpr();
						}
						}
						setState(59); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(63);
				match(T__1);
				}
				break;
			case 3:
				_localctx = new PrefixIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(65);
					match(MINUS);
					}
				}

				setState(68);
				match(NUM);
				}
				break;
			case 4:
				_localctx = new PrefixGroupedContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				match(T__0);
				setState(70);
				prefixExpr();
				setState(71);
				match(T__1);
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
	public static class PostfixExprContext extends ParserRuleContext {
		public PostfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpr; }
	 
		public PostfixExprContext() { }
		public void copyFrom(PostfixExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixIntegerContext extends PostfixExprContext {
		public TerminalNode NUM() { return getToken(ExpressionParserParser.NUM, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParserParser.MINUS, 0); }
		public PostfixIntegerContext(PostfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPostfixInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPostfixInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPostfixInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixAddSubContext extends PostfixExprContext {
		public List<PostfixExprContext> postfixExpr() {
			return getRuleContexts(PostfixExprContext.class);
		}
		public PostfixExprContext postfixExpr(int i) {
			return getRuleContext(PostfixExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ExpressionParserParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParserParser.MINUS, 0); }
		public PostfixAddSubContext(PostfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPostfixAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPostfixAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPostfixAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixMulDivContext extends PostfixExprContext {
		public List<PostfixExprContext> postfixExpr() {
			return getRuleContexts(PostfixExprContext.class);
		}
		public PostfixExprContext postfixExpr(int i) {
			return getRuleContext(PostfixExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(ExpressionParserParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExpressionParserParser.DIV, 0); }
		public PostfixMulDivContext(PostfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPostfixMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPostfixMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPostfixMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixGroupedContext extends PostfixExprContext {
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public PostfixGroupedContext(PostfixExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPostfixGrouped(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPostfixGrouped(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPostfixGrouped(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixExprContext postfixExpr() throws RecognitionException {
		PostfixExprContext _localctx = new PostfixExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_postfixExpr);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new PostfixMulDivContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(T__0);
				setState(76);
				postfixExpr();
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(79); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(77);
						match(T__2);
						setState(78);
						postfixExpr();
						}
						}
						setState(81); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(85);
				match(T__1);
				setState(86);
				_la = _input.LA(1);
				if ( !(_la==MULT || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				_localctx = new PostfixAddSubContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				match(T__0);
				setState(89);
				postfixExpr();
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(92); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(90);
						match(T__2);
						setState(91);
						postfixExpr();
						}
						}
						setState(94); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__2 );
					}
				}

				setState(98);
				match(T__1);
				setState(99);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				_localctx = new PostfixIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(101);
					match(MINUS);
					}
				}

				setState(104);
				match(NUM);
				}
				break;
			case 4:
				_localctx = new PostfixGroupedContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				match(T__0);
				setState(106);
				postfixExpr();
				setState(107);
				match(T__1);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return infixExpr_sempred((InfixExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean infixExpr_sempred(InfixExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\tp\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0010"+
		"\b\u0001\u0001\u0002\u0001\u0002\u0003\u0002\u0014\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u001b\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002#\b\u0002\n\u0002\f\u0002&\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003-\b\u0003\u000b\u0003"+
		"\f\u0003.\u0003\u00031\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003:\b\u0003\u000b"+
		"\u0003\f\u0003;\u0003\u0003>\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003C\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003J\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0004\u0004P\b\u0004\u000b\u0004\f\u0004Q\u0003\u0004T\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0004\u0004]\b\u0004\u000b\u0004\f\u0004^\u0003\u0004"+
		"a\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"g\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004n\b\u0004\u0001\u0004\u0000\u0001\u0004\u0005\u0000\u0002"+
		"\u0004\u0006\b\u0000\u0002\u0001\u0000\u0004\u0005\u0001\u0000\u0006\u0007"+
		"\u0080\u0000\n\u0001\u0000\u0000\u0000\u0002\u000f\u0001\u0000\u0000\u0000"+
		"\u0004\u001a\u0001\u0000\u0000\u0000\u0006I\u0001\u0000\u0000\u0000\b"+
		"m\u0001\u0000\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\u0001"+
		"\u0001\u0000\u0000\u0000\f\u0010\u0003\u0004\u0002\u0000\r\u0010\u0003"+
		"\u0006\u0003\u0000\u000e\u0010\u0003\b\u0004\u0000\u000f\f\u0001\u0000"+
		"\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f\u000e\u0001\u0000\u0000"+
		"\u0000\u0010\u0003\u0001\u0000\u0000\u0000\u0011\u0013\u0006\u0002\uffff"+
		"\uffff\u0000\u0012\u0014\u0005\u0007\u0000\u0000\u0013\u0012\u0001\u0000"+
		"\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000"+
		"\u0000\u0000\u0015\u001b\u0005\b\u0000\u0000\u0016\u0017\u0005\u0001\u0000"+
		"\u0000\u0017\u0018\u0003\u0004\u0002\u0000\u0018\u0019\u0005\u0002\u0000"+
		"\u0000\u0019\u001b\u0001\u0000\u0000\u0000\u001a\u0011\u0001\u0000\u0000"+
		"\u0000\u001a\u0016\u0001\u0000\u0000\u0000\u001b$\u0001\u0000\u0000\u0000"+
		"\u001c\u001d\n\u0004\u0000\u0000\u001d\u001e\u0007\u0000\u0000\u0000\u001e"+
		"#\u0003\u0004\u0002\u0005\u001f \n\u0003\u0000\u0000 !\u0007\u0001\u0000"+
		"\u0000!#\u0003\u0004\u0002\u0004\"\u001c\u0001\u0000\u0000\u0000\"\u001f"+
		"\u0001\u0000\u0000\u0000#&\u0001\u0000\u0000\u0000$\"\u0001\u0000\u0000"+
		"\u0000$%\u0001\u0000\u0000\u0000%\u0005\u0001\u0000\u0000\u0000&$\u0001"+
		"\u0000\u0000\u0000\'(\u0007\u0000\u0000\u0000()\u0005\u0001\u0000\u0000"+
		")0\u0003\u0006\u0003\u0000*+\u0005\u0003\u0000\u0000+-\u0003\u0006\u0003"+
		"\u0000,*\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.,\u0001\u0000"+
		"\u0000\u0000./\u0001\u0000\u0000\u0000/1\u0001\u0000\u0000\u00000,\u0001"+
		"\u0000\u0000\u000001\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u0000"+
		"23\u0005\u0002\u0000\u00003J\u0001\u0000\u0000\u000045\u0007\u0001\u0000"+
		"\u000056\u0005\u0001\u0000\u00006=\u0003\u0006\u0003\u000078\u0005\u0003"+
		"\u0000\u00008:\u0003\u0006\u0003\u000097\u0001\u0000\u0000\u0000:;\u0001"+
		"\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000"+
		"<>\u0001\u0000\u0000\u0000=9\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000"+
		"\u0000>?\u0001\u0000\u0000\u0000?@\u0005\u0002\u0000\u0000@J\u0001\u0000"+
		"\u0000\u0000AC\u0005\u0007\u0000\u0000BA\u0001\u0000\u0000\u0000BC\u0001"+
		"\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DJ\u0005\b\u0000\u0000EF\u0005"+
		"\u0001\u0000\u0000FG\u0003\u0006\u0003\u0000GH\u0005\u0002\u0000\u0000"+
		"HJ\u0001\u0000\u0000\u0000I\'\u0001\u0000\u0000\u0000I4\u0001\u0000\u0000"+
		"\u0000IB\u0001\u0000\u0000\u0000IE\u0001\u0000\u0000\u0000J\u0007\u0001"+
		"\u0000\u0000\u0000KL\u0005\u0001\u0000\u0000LS\u0003\b\u0004\u0000MN\u0005"+
		"\u0003\u0000\u0000NP\u0003\b\u0004\u0000OM\u0001\u0000\u0000\u0000PQ\u0001"+
		"\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000"+
		"RT\u0001\u0000\u0000\u0000SO\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000"+
		"\u0000TU\u0001\u0000\u0000\u0000UV\u0005\u0002\u0000\u0000VW\u0007\u0000"+
		"\u0000\u0000Wn\u0001\u0000\u0000\u0000XY\u0005\u0001\u0000\u0000Y`\u0003"+
		"\b\u0004\u0000Z[\u0005\u0003\u0000\u0000[]\u0003\b\u0004\u0000\\Z\u0001"+
		"\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000"+
		"^_\u0001\u0000\u0000\u0000_a\u0001\u0000\u0000\u0000`\\\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0005\u0002"+
		"\u0000\u0000cd\u0007\u0001\u0000\u0000dn\u0001\u0000\u0000\u0000eg\u0005"+
		"\u0007\u0000\u0000fe\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000"+
		"gh\u0001\u0000\u0000\u0000hn\u0005\b\u0000\u0000ij\u0005\u0001\u0000\u0000"+
		"jk\u0003\b\u0004\u0000kl\u0005\u0002\u0000\u0000ln\u0001\u0000\u0000\u0000"+
		"mK\u0001\u0000\u0000\u0000mX\u0001\u0000\u0000\u0000mf\u0001\u0000\u0000"+
		"\u0000mi\u0001\u0000\u0000\u0000n\t\u0001\u0000\u0000\u0000\u0011\u000f"+
		"\u0013\u001a\"$.0;=BIQS^`fm";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}