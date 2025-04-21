// Generated from RationalCore.g4 by ANTLR 4.13.1
package parser.rational;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import parser.rational.RationalCoreVisitor;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class RationalCoreParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, MUL=4, DIV=5, ADD=6, SUB=7, FRAC=8, DIGIT=9, WS=10;
	public static final int
		RULE_entry = 0, RULE_form = 1, RULE_modeInfix = 2, RULE_modePrefix = 3, 
		RULE_modePosfix = 4, RULE_grouping = 5, RULE_groupingList = 6, RULE_unit = 7, 
		RULE_num = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"entry", "form", "modeInfix", "modePrefix", "modePosfix", "grouping", 
			"groupingList", "unit", "num"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'*'", "'/'", "'+'", "'-'", "'\\u00F7'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "MUL", "DIV", "ADD", "SUB", "FRAC", "DIGIT", 
			"WS"
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
	public String getGrammarFileName() { return "RationalCore.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RationalCoreParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EntryContext extends ParserRuleContext {
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor) return ((RationalCoreVisitor<? extends T>)visitor).visitEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			form();
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
	public static class FormContext extends ParserRuleContext {
		public ModeInfixContext modeInfix() {
			return getRuleContext(ModeInfixContext.class,0);
		}
		public ModePrefixContext modePrefix() {
			return getRuleContext(ModePrefixContext.class,0);
		}
		public ModePosfixContext modePosfix() {
			return getRuleContext(ModePosfixContext.class,0);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_form);
		try {
			setState(23);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				modeInfix(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				modePrefix();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(22);
				modePosfix();
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
	public static class ModeInfixContext extends ParserRuleContext {
		public ModeInfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modeInfix; }
	 
		public ModeInfixContext() { }
		public void copyFrom(ModeInfixContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WrappedInfixContext extends ModeInfixContext {
		public GroupingContext grouping() {
			return getRuleContext(GroupingContext.class,0);
		}
		public ModeInfixContext modeInfix() {
			return getRuleContext(ModeInfixContext.class,0);
		}
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public WrappedInfixContext(ModeInfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterWrappedInfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitWrappedInfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitWrappedInfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubInfixContext extends ModeInfixContext {
		public Token sym;
		public List<ModeInfixContext> modeInfix() {
			return getRuleContexts(ModeInfixContext.class);
		}
		public ModeInfixContext modeInfix(int i) {
			return getRuleContext(ModeInfixContext.class,i);
		}
		public TerminalNode ADD() { return getToken(RationalCoreParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public AddSubInfixContext(ModeInfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterAddSubInfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitAddSubInfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitAddSubInfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinOpInfixContext extends ModeInfixContext {
		public Token sym;
		public List<ModeInfixContext> modeInfix() {
			return getRuleContexts(ModeInfixContext.class);
		}
		public ModeInfixContext modeInfix(int i) {
			return getRuleContext(ModeInfixContext.class,i);
		}
		public TerminalNode MUL() { return getToken(RationalCoreParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(RationalCoreParser.DIV, 0); }
		public BinOpInfixContext(ModeInfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterBinOpInfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitBinOpInfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitBinOpInfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomicInfixContext extends ModeInfixContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public AtomicInfixContext(ModeInfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterAtomicInfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitAtomicInfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitAtomicInfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModeInfixContext modeInfix() throws RecognitionException {
		return modeInfix(0);
	}

	private ModeInfixContext modeInfix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ModeInfixContext _localctx = new ModeInfixContext(_ctx, _parentState);
		ModeInfixContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_modeInfix, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				_localctx = new AtomicInfixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(26);
				unit();
				}
				break;
			case 2:
				{
				_localctx = new WrappedInfixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(27);
					match(SUB);
					}
				}

				setState(30);
				grouping();
				{
				setState(31);
				modeInfix(0);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(43);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(41);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new BinOpInfixContext(new ModeInfixContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_modeInfix);
						setState(35);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(36);
						((BinOpInfixContext)_localctx).sym = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((BinOpInfixContext)_localctx).sym = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(37);
						modeInfix(5);
						}
						break;
					case 2:
						{
						_localctx = new AddSubInfixContext(new ModeInfixContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_modeInfix);
						setState(38);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(39);
						((AddSubInfixContext)_localctx).sym = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubInfixContext)_localctx).sym = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(40);
						modeInfix(4);
						}
						break;
					}
					} 
				}
				setState(45);
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
	public static class ModePrefixContext extends ParserRuleContext {
		public ModePrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modePrefix; }
	 
		public ModePrefixContext() { }
		public void copyFrom(ModePrefixContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinOpPrefixContext extends ModePrefixContext {
		public Token sym;
		public List<ModePrefixContext> modePrefix() {
			return getRuleContexts(ModePrefixContext.class);
		}
		public ModePrefixContext modePrefix(int i) {
			return getRuleContext(ModePrefixContext.class,i);
		}
		public TerminalNode MUL() { return getToken(RationalCoreParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(RationalCoreParser.DIV, 0); }
		public BinOpPrefixContext(ModePrefixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterBinOpPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitBinOpPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitBinOpPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubPrefixContext extends ModePrefixContext {
		public Token sym;
		public List<ModePrefixContext> modePrefix() {
			return getRuleContexts(ModePrefixContext.class);
		}
		public ModePrefixContext modePrefix(int i) {
			return getRuleContext(ModePrefixContext.class,i);
		}
		public TerminalNode ADD() { return getToken(RationalCoreParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public AddSubPrefixContext(ModePrefixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterAddSubPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitAddSubPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitAddSubPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomicPrefixContext extends ModePrefixContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public AtomicPrefixContext(ModePrefixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterAtomicPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitAtomicPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitAtomicPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WrappedPrefixContext extends ModePrefixContext {
		public GroupingContext grouping() {
			return getRuleContext(GroupingContext.class,0);
		}
		public ModePrefixContext modePrefix() {
			return getRuleContext(ModePrefixContext.class,0);
		}
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public WrappedPrefixContext(ModePrefixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterWrappedPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitWrappedPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitWrappedPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModePrefixContext modePrefix() throws RecognitionException {
		ModePrefixContext _localctx = new ModePrefixContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_modePrefix);
		int _la;
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new BinOpPrefixContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				((BinOpPrefixContext)_localctx).sym = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
					((BinOpPrefixContext)_localctx).sym = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(47);
				match(T__0);
				setState(48);
				modePrefix();
				{
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(49);
					match(T__1);
					setState(50);
					modePrefix();
					}
					}
					setState(53); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__1 );
				}
				setState(55);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new AddSubPrefixContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				((AddSubPrefixContext)_localctx).sym = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((AddSubPrefixContext)_localctx).sym = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(58);
				match(T__0);
				setState(59);
				modePrefix();
				{
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(60);
					match(T__1);
					setState(61);
					modePrefix();
					}
					}
					setState(64); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__1 );
				}
				setState(66);
				match(T__2);
				}
				break;
			case 3:
				_localctx = new AtomicPrefixContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				unit();
				}
				break;
			case 4:
				_localctx = new WrappedPrefixContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(69);
					match(SUB);
					}
				}

				setState(72);
				grouping();
				{
				setState(73);
				modePrefix();
				}
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
	public static class ModePosfixContext extends ParserRuleContext {
		public ModePosfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modePosfix; }
	 
		public ModePosfixContext() { }
		public void copyFrom(ModePosfixContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinOpPosfixContext extends ModePosfixContext {
		public Token sym;
		public GroupingListContext groupingList() {
			return getRuleContext(GroupingListContext.class,0);
		}
		public ModePosfixContext modePosfix() {
			return getRuleContext(ModePosfixContext.class,0);
		}
		public TerminalNode MUL() { return getToken(RationalCoreParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(RationalCoreParser.DIV, 0); }
		public BinOpPosfixContext(ModePosfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterBinOpPosfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitBinOpPosfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitBinOpPosfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubPosfixContext extends ModePosfixContext {
		public Token sym;
		public GroupingListContext groupingList() {
			return getRuleContext(GroupingListContext.class,0);
		}
		public ModePosfixContext modePosfix() {
			return getRuleContext(ModePosfixContext.class,0);
		}
		public TerminalNode ADD() { return getToken(RationalCoreParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public AddSubPosfixContext(ModePosfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterAddSubPosfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitAddSubPosfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitAddSubPosfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomicPosfixContext extends ModePosfixContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public AtomicPosfixContext(ModePosfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterAtomicPosfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitAtomicPosfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitAtomicPosfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WrappedPosfixContext extends ModePosfixContext {
		public GroupingContext grouping() {
			return getRuleContext(GroupingContext.class,0);
		}
		public ModePosfixContext modePosfix() {
			return getRuleContext(ModePosfixContext.class,0);
		}
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public WrappedPosfixContext(ModePosfixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterWrappedPosfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitWrappedPosfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitWrappedPosfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModePosfixContext modePosfix() throws RecognitionException {
		ModePosfixContext _localctx = new ModePosfixContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_modePosfix);
		int _la;
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new BinOpPosfixContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				groupingList();
				{
				setState(78);
				modePosfix();
				}
				setState(79);
				((BinOpPosfixContext)_localctx).sym = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
					((BinOpPosfixContext)_localctx).sym = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				_localctx = new AddSubPosfixContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				groupingList();
				{
				setState(82);
				modePosfix();
				}
				setState(83);
				((AddSubPosfixContext)_localctx).sym = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((AddSubPosfixContext)_localctx).sym = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				_localctx = new AtomicPosfixContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				unit();
				}
				break;
			case 4:
				_localctx = new WrappedPosfixContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(86);
				grouping();
				{
				setState(87);
				modePosfix();
				}
				setState(89);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(88);
					match(SUB);
					}
					break;
				}
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
	public static class GroupingContext extends ParserRuleContext {
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public GroupingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grouping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterGrouping(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitGrouping(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitGrouping(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupingContext grouping() throws RecognitionException {
		GroupingContext _localctx = new GroupingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_grouping);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__0);
			setState(94);
			form();
			setState(95);
			match(T__2);
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
	public static class GroupingListContext extends ParserRuleContext {
		public List<FormContext> form() {
			return getRuleContexts(FormContext.class);
		}
		public FormContext form(int i) {
			return getRuleContext(FormContext.class,i);
		}
		public GroupingListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupingList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterGroupingList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitGroupingList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitGroupingList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupingListContext groupingList() throws RecognitionException {
		GroupingListContext _localctx = new GroupingListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_groupingList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(T__0);
			setState(98);
			form();
			{
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(99);
				match(T__1);
				setState(100);
				form();
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
			}
			setState(105);
			match(T__2);
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
	public static class UnitContext extends ParserRuleContext {
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
	 
		public UnitContext() { }
		public void copyFrom(UnitContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RationalUnitContext extends UnitContext {
		public List<NumContext> num() {
			return getRuleContexts(NumContext.class);
		}
		public NumContext num(int i) {
			return getRuleContext(NumContext.class,i);
		}
		public TerminalNode FRAC() { return getToken(RationalCoreParser.FRAC, 0); }
		public RationalUnitContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterRationalUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitRationalUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitRationalUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unit);
		try {
			_localctx = new RationalUnitContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			num();
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(108);
				match(FRAC);
				setState(109);
				num();
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
	public static class NumContext extends ParserRuleContext {
		public NumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num; }
	 
		public NumContext() { }
		public void copyFrom(NumContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntUnitContext extends NumContext {
		public TerminalNode SUB() { return getToken(RationalCoreParser.SUB, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(RationalCoreParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(RationalCoreParser.DIGIT, i);
		}
		public IntUnitContext(NumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).enterIntUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RationalCoreListener ) ((RationalCoreListener)listener).exitIntUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RationalCoreVisitor ) return ((RationalCoreVisitor<? extends T>)visitor).visitIntUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumContext num() throws RecognitionException {
		NumContext _localctx = new NumContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_num);
		int _la;
		try {
			int _alt;
			_localctx = new IntUnitContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB) {
				{
				setState(112);
				match(SUB);
				}
			}

			setState(116); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(115);
					match(DIGIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(118); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
			return modeInfix_sempred((ModeInfixContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean modeInfix_sempred(ModeInfixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\ny\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u0018\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"\u001d\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\"\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002*\b\u0002\n\u0002\f\u0002-\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u00034\b\u0003\u000b\u0003"+
		"\f\u00035\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0004\u0003?\b\u0003\u000b\u0003\f\u0003@\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003G\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003L\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004Z\b"+
		"\u0004\u0003\u0004\\\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006f\b"+
		"\u0006\u000b\u0006\f\u0006g\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007o\b\u0007\u0001\b\u0003\br\b\b\u0001\b\u0004"+
		"\bu\b\b\u000b\b\f\bv\u0001\b\u0000\u0001\u0004\t\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0000\u0002\u0001\u0000\u0004\u0005\u0001\u0000\u0006"+
		"\u0007\u0083\u0000\u0012\u0001\u0000\u0000\u0000\u0002\u0017\u0001\u0000"+
		"\u0000\u0000\u0004!\u0001\u0000\u0000\u0000\u0006K\u0001\u0000\u0000\u0000"+
		"\b[\u0001\u0000\u0000\u0000\n]\u0001\u0000\u0000\u0000\fa\u0001\u0000"+
		"\u0000\u0000\u000ek\u0001\u0000\u0000\u0000\u0010q\u0001\u0000\u0000\u0000"+
		"\u0012\u0013\u0003\u0002\u0001\u0000\u0013\u0001\u0001\u0000\u0000\u0000"+
		"\u0014\u0018\u0003\u0004\u0002\u0000\u0015\u0018\u0003\u0006\u0003\u0000"+
		"\u0016\u0018\u0003\b\u0004\u0000\u0017\u0014\u0001\u0000\u0000\u0000\u0017"+
		"\u0015\u0001\u0000\u0000\u0000\u0017\u0016\u0001\u0000\u0000\u0000\u0018"+
		"\u0003\u0001\u0000\u0000\u0000\u0019\u001a\u0006\u0002\uffff\uffff\u0000"+
		"\u001a\"\u0003\u000e\u0007\u0000\u001b\u001d\u0005\u0007\u0000\u0000\u001c"+
		"\u001b\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d"+
		"\u001e\u0001\u0000\u0000\u0000\u001e\u001f\u0003\n\u0005\u0000\u001f "+
		"\u0003\u0004\u0002\u0000 \"\u0001\u0000\u0000\u0000!\u0019\u0001\u0000"+
		"\u0000\u0000!\u001c\u0001\u0000\u0000\u0000\"+\u0001\u0000\u0000\u0000"+
		"#$\n\u0004\u0000\u0000$%\u0007\u0000\u0000\u0000%*\u0003\u0004\u0002\u0005"+
		"&\'\n\u0003\u0000\u0000\'(\u0007\u0001\u0000\u0000(*\u0003\u0004\u0002"+
		"\u0004)#\u0001\u0000\u0000\u0000)&\u0001\u0000\u0000\u0000*-\u0001\u0000"+
		"\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000\u0000,\u0005"+
		"\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000./\u0007\u0000\u0000"+
		"\u0000/0\u0005\u0001\u0000\u000003\u0003\u0006\u0003\u000012\u0005\u0002"+
		"\u0000\u000024\u0003\u0006\u0003\u000031\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u0000"+
		"67\u0001\u0000\u0000\u000078\u0005\u0003\u0000\u00008L\u0001\u0000\u0000"+
		"\u00009:\u0007\u0001\u0000\u0000:;\u0005\u0001\u0000\u0000;>\u0003\u0006"+
		"\u0003\u0000<=\u0005\u0002\u0000\u0000=?\u0003\u0006\u0003\u0000><\u0001"+
		"\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000"+
		"@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0005\u0003\u0000"+
		"\u0000CL\u0001\u0000\u0000\u0000DL\u0003\u000e\u0007\u0000EG\u0005\u0007"+
		"\u0000\u0000FE\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000HI\u0003\n\u0005\u0000IJ\u0003\u0006\u0003\u0000JL\u0001"+
		"\u0000\u0000\u0000K.\u0001\u0000\u0000\u0000K9\u0001\u0000\u0000\u0000"+
		"KD\u0001\u0000\u0000\u0000KF\u0001\u0000\u0000\u0000L\u0007\u0001\u0000"+
		"\u0000\u0000MN\u0003\f\u0006\u0000NO\u0003\b\u0004\u0000OP\u0007\u0000"+
		"\u0000\u0000P\\\u0001\u0000\u0000\u0000QR\u0003\f\u0006\u0000RS\u0003"+
		"\b\u0004\u0000ST\u0007\u0001\u0000\u0000T\\\u0001\u0000\u0000\u0000U\\"+
		"\u0003\u000e\u0007\u0000VW\u0003\n\u0005\u0000WY\u0003\b\u0004\u0000X"+
		"Z\u0005\u0007\u0000\u0000YX\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000"+
		"\u0000Z\\\u0001\u0000\u0000\u0000[M\u0001\u0000\u0000\u0000[Q\u0001\u0000"+
		"\u0000\u0000[U\u0001\u0000\u0000\u0000[V\u0001\u0000\u0000\u0000\\\t\u0001"+
		"\u0000\u0000\u0000]^\u0005\u0001\u0000\u0000^_\u0003\u0002\u0001\u0000"+
		"_`\u0005\u0003\u0000\u0000`\u000b\u0001\u0000\u0000\u0000ab\u0005\u0001"+
		"\u0000\u0000be\u0003\u0002\u0001\u0000cd\u0005\u0002\u0000\u0000df\u0003"+
		"\u0002\u0001\u0000ec\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000ij\u0005\u0003\u0000\u0000j\r\u0001\u0000\u0000\u0000kn\u0003\u0010"+
		"\b\u0000lm\u0005\b\u0000\u0000mo\u0003\u0010\b\u0000nl\u0001\u0000\u0000"+
		"\u0000no\u0001\u0000\u0000\u0000o\u000f\u0001\u0000\u0000\u0000pr\u0005"+
		"\u0007\u0000\u0000qp\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"rt\u0001\u0000\u0000\u0000su\u0005\t\u0000\u0000ts\u0001\u0000\u0000\u0000"+
		"uv\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000"+
		"\u0000w\u0011\u0001\u0000\u0000\u0000\u000f\u0017\u001c!)+5@FKY[gnqv";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}