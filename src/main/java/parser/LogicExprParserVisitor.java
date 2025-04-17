// Generated from LogicExprParser.g4 by ANTLR 4.13.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogicExprParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogicExprParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogicExprParserParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(LogicExprParserParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicExprParserParser#logicExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExpr(LogicExprParserParser.LogicExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixEquiv(LogicExprParserParser.InfixEquivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixNegation}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixNegation(LogicExprParserParser.InfixNegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixImplies}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixImplies(LogicExprParserParser.InfixImpliesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixAnd}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixAnd(LogicExprParserParser.InfixAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixLiteral(LogicExprParserParser.InfixLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixOr}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixOr(LogicExprParserParser.InfixOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixXor}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixXor(LogicExprParserParser.InfixXorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixGroup}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixGroup(LogicExprParserParser.InfixGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixNegation}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixNegation(LogicExprParserParser.PrefixNegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixAnd}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixAnd(LogicExprParserParser.PrefixAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixOr}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixOr(LogicExprParserParser.PrefixOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixImplies}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixImplies(LogicExprParserParser.PrefixImpliesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixXor}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixXor(LogicExprParserParser.PrefixXorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixEquiv(LogicExprParserParser.PrefixEquivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixLiteral(LogicExprParserParser.PrefixLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixGroup}
	 * labeled alternative in {@link LogicExprParserParser#prefixForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixGroup(LogicExprParserParser.PrefixGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixImplies}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixImplies(LogicExprParserParser.PostfixImpliesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixNegation}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixNegation(LogicExprParserParser.PostfixNegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixXor}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixXor(LogicExprParserParser.PostfixXorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixAnd}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixAnd(LogicExprParserParser.PostfixAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixOr}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixOr(LogicExprParserParser.PostfixOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixEquiv}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixEquiv(LogicExprParserParser.PostfixEquivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixLiteral}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixLiteral(LogicExprParserParser.PostfixLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixGroup}
	 * labeled alternative in {@link LogicExprParserParser#}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixGroup(LogicExprParserParser.PostfixGroupContext ctx);
}