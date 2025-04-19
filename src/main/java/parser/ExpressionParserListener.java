// Generated from ExpressionParser.g4 by ANTLR 4.13.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParserParser}.
 */
public interface ExpressionParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ExpressionParserParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ExpressionParserParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExpressionParserParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExpressionParserParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixInteger}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void enterInfixInteger(ExpressionParserParser.InfixIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixInteger}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void exitInfixInteger(ExpressionParserParser.InfixIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void enterInfixMulDiv(ExpressionParserParser.InfixMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void exitInfixMulDiv(ExpressionParserParser.InfixMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void enterInfixAddSub(ExpressionParserParser.InfixAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void exitInfixAddSub(ExpressionParserParser.InfixAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void enterInfixGrouped(ExpressionParserParser.InfixGroupedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#}.
	 * @param ctx the parse tree
	 */
	void exitInfixGrouped(ExpressionParserParser.InfixGroupedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixMulDiv(ExpressionParserParser.PrefixMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixMulDiv(ExpressionParserParser.PrefixMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixAddSub(ExpressionParserParser.PrefixAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixAddSub(ExpressionParserParser.PrefixAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixInteger}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixInteger(ExpressionParserParser.PrefixIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixInteger}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixInteger(ExpressionParserParser.PrefixIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixGrouped(ExpressionParserParser.PrefixGroupedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixGrouped(ExpressionParserParser.PrefixGroupedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixMulDiv(ExpressionParserParser.PostfixMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixMulDiv(ExpressionParserParser.PostfixMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixAddSub(ExpressionParserParser.PostfixAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixAddSub(ExpressionParserParser.PostfixAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixInteger}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixInteger(ExpressionParserParser.PostfixIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixInteger}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixInteger(ExpressionParserParser.PostfixIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixGrouped(ExpressionParserParser.PostfixGroupedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixGrouped(ExpressionParserParser.PostfixGroupedContext ctx);
}