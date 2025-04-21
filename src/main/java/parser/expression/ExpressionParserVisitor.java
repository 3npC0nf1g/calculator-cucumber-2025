// Generated from ExpressionParser.g4 by ANTLR 4.13.1
package parser.expression;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpressionParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpressionParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ExpressionParserParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ExpressionParserParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixInteger}
	 * labeled alternative in {@link ExpressionParserParser#infixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixInteger(ExpressionParserParser.InfixIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#infixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixMulDiv(ExpressionParserParser.InfixMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#infixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixAddSub(ExpressionParserParser.InfixAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#infixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixGrouped(ExpressionParserParser.InfixGroupedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixMulDiv(ExpressionParserParser.PrefixMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixAddSub(ExpressionParserParser.PrefixAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixInteger}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixInteger(ExpressionParserParser.PrefixIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#prefixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixGrouped(ExpressionParserParser.PrefixGroupedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixMulDiv}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixMulDiv(ExpressionParserParser.PostfixMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixAddSub}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixAddSub(ExpressionParserParser.PostfixAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixInteger}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixInteger(ExpressionParserParser.PostfixIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixGrouped}
	 * labeled alternative in {@link ExpressionParserParser#postfixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixGrouped(ExpressionParserParser.PostfixGroupedContext ctx);
}