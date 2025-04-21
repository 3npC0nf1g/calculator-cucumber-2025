// Generated from LogicExprParser.g4 by ANTLR 4.13.1
package parser.logic;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import parser.logic.LogicExprParserParser;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogicExprParserParser}.
 */
public interface LogicExprParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogicExprParserParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(LogicExprParserParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicExprParserParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(LogicExprParserParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicExprParserParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpr(LogicExprParserParser.LogicExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicExprParserParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpr(LogicExprParserParser.LogicExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixEquiv(LogicExprParserParser.InfixEquivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixEquiv(LogicExprParserParser.InfixEquivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixNegation}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixNegation(LogicExprParserParser.InfixNegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixNegation}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixNegation(LogicExprParserParser.InfixNegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixImplies}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixImplies(LogicExprParserParser.InfixImpliesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixImplies}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixImplies(LogicExprParserParser.InfixImpliesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixAnd}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixAnd(LogicExprParserParser.InfixAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixAnd}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixAnd(LogicExprParserParser.InfixAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixLiteral(LogicExprParserParser.InfixLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixLiteral(LogicExprParserParser.InfixLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixOr}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixOr(LogicExprParserParser.InfixOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixOr}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixOr(LogicExprParserParser.InfixOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixXor}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixXor(LogicExprParserParser.InfixXorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixXor}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixXor(LogicExprParserParser.InfixXorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixGroup}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void enterInfixGroup(LogicExprParserParser.InfixGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixGroup}
	 * labeled alternative in {@link LogicExprParserParser#infixForm}.
	 * @param ctx the parse tree
	 */
	void exitInfixGroup(LogicExprParserParser.InfixGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixNegation}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixNegation(LogicExprParserParser.PrefixNegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixNegation}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixNegation(LogicExprParserParser.PrefixNegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixAnd}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixAnd(LogicExprParserParser.PrefixAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixAnd}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixAnd(LogicExprParserParser.PrefixAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixOr}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOr(LogicExprParserParser.PrefixOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixOr}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOr(LogicExprParserParser.PrefixOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixImplies}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixImplies(LogicExprParserParser.PrefixImpliesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixImplies}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixImplies(LogicExprParserParser.PrefixImpliesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixXor}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixXor(LogicExprParserParser.PrefixXorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixXor}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixXor(LogicExprParserParser.PrefixXorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixEquiv(LogicExprParserParser.PrefixEquivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixEquiv(LogicExprParserParser.PrefixEquivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixLiteral(LogicExprParserParser.PrefixLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixLiteral(LogicExprParserParser.PrefixLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixGroup}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void enterPrefixGroup(LogicExprParserParser.PrefixGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixGroup}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 */
	void exitPrefixGroup(LogicExprParserParser.PrefixGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixImplies}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixImplies(LogicExprParserParser.PostfixImpliesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixImplies}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixImplies(LogicExprParserParser.PostfixImpliesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixNegation}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixNegation(LogicExprParserParser.PostfixNegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixNegation}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixNegation(LogicExprParserParser.PostfixNegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixXor}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixXor(LogicExprParserParser.PostfixXorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixXor}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixXor(LogicExprParserParser.PostfixXorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixAnd}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixAnd(LogicExprParserParser.PostfixAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixAnd}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixAnd(LogicExprParserParser.PostfixAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixOr}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixOr(LogicExprParserParser.PostfixOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixOr}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixOr(LogicExprParserParser.PostfixOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixEquiv(LogicExprParserParser.PostfixEquivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixEquiv(LogicExprParserParser.PostfixEquivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixLiteral(LogicExprParserParser.PostfixLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixLiteral(LogicExprParserParser.PostfixLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixGroup}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void enterPostfixGroup(LogicExprParserParser.PostfixGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixGroup}
	 * labeled alternative in {@link LogicExprParserParser#postfixForm}.
	 * @param ctx the parse tree
	 */
	void exitPostfixGroup(LogicExprParserParser.PostfixGroupContext ctx);
}