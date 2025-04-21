// Generated from RationalCore.g4 by ANTLR 4.13.1
package parser.rational;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RationalCoreParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RationalCoreVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RationalCoreParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry(RationalCoreParser.EntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RationalCoreParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(RationalCoreParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WrappedInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrappedInfix(RationalCoreParser.WrappedInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubInfix(RationalCoreParser.AddSubInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinOpInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOpInfix(RationalCoreParser.BinOpInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomicInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicInfix(RationalCoreParser.AtomicInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinOpPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOpPrefix(RationalCoreParser.BinOpPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubPrefix(RationalCoreParser.AddSubPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomicPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicPrefix(RationalCoreParser.AtomicPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WrappedPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrappedPrefix(RationalCoreParser.WrappedPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinOpPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOpPosfix(RationalCoreParser.BinOpPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubPosfix(RationalCoreParser.AddSubPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomicPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicPosfix(RationalCoreParser.AtomicPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WrappedPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrappedPosfix(RationalCoreParser.WrappedPosfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link RationalCoreParser#grouping}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrouping(RationalCoreParser.GroupingContext ctx);
	/**
	 * Visit a parse tree produced by {@link RationalCoreParser#groupingList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupingList(RationalCoreParser.GroupingListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RationalUnit}
	 * labeled alternative in {@link RationalCoreParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRationalUnit(RationalCoreParser.RationalUnitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntUnit}
	 * labeled alternative in {@link RationalCoreParser#num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntUnit(RationalCoreParser.IntUnitContext ctx);
}