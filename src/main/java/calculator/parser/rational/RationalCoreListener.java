// Generated from ./RationalCore.g4 by ANTLR 4.13.1
package calculator.parser.rational;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RationalCoreParser}.
 */
public interface RationalCoreListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RationalCoreParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(RationalCoreParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationalCoreParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(RationalCoreParser.EntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationalCoreParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(RationalCoreParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationalCoreParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(RationalCoreParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WrappedInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void enterWrappedInfix(RationalCoreParser.WrappedInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WrappedInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void exitWrappedInfix(RationalCoreParser.WrappedInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void enterAddSubInfix(RationalCoreParser.AddSubInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void exitAddSubInfix(RationalCoreParser.AddSubInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinOpInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void enterBinOpInfix(RationalCoreParser.BinOpInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinOpInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void exitBinOpInfix(RationalCoreParser.BinOpInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomicInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void enterAtomicInfix(RationalCoreParser.AtomicInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomicInfix}
	 * labeled alternative in {@link RationalCoreParser#modeInfix}.
	 * @param ctx the parse tree
	 */
	void exitAtomicInfix(RationalCoreParser.AtomicInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinOpPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void enterBinOpPrefix(RationalCoreParser.BinOpPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinOpPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void exitBinOpPrefix(RationalCoreParser.BinOpPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void enterAddSubPrefix(RationalCoreParser.AddSubPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void exitAddSubPrefix(RationalCoreParser.AddSubPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomicPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void enterAtomicPrefix(RationalCoreParser.AtomicPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomicPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void exitAtomicPrefix(RationalCoreParser.AtomicPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WrappedPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void enterWrappedPrefix(RationalCoreParser.WrappedPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WrappedPrefix}
	 * labeled alternative in {@link RationalCoreParser#modePrefix}.
	 * @param ctx the parse tree
	 */
	void exitWrappedPrefix(RationalCoreParser.WrappedPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinOpPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void enterBinOpPosfix(RationalCoreParser.BinOpPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinOpPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void exitBinOpPosfix(RationalCoreParser.BinOpPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void enterAddSubPosfix(RationalCoreParser.AddSubPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void exitAddSubPosfix(RationalCoreParser.AddSubPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomicPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void enterAtomicPosfix(RationalCoreParser.AtomicPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomicPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void exitAtomicPosfix(RationalCoreParser.AtomicPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WrappedPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void enterWrappedPosfix(RationalCoreParser.WrappedPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WrappedPosfix}
	 * labeled alternative in {@link RationalCoreParser#modePosfix}.
	 * @param ctx the parse tree
	 */
	void exitWrappedPosfix(RationalCoreParser.WrappedPosfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationalCoreParser#grouping}.
	 * @param ctx the parse tree
	 */
	void enterGrouping(RationalCoreParser.GroupingContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationalCoreParser#grouping}.
	 * @param ctx the parse tree
	 */
	void exitGrouping(RationalCoreParser.GroupingContext ctx);
	/**
	 * Enter a parse tree produced by {@link RationalCoreParser#groupingList}.
	 * @param ctx the parse tree
	 */
	void enterGroupingList(RationalCoreParser.GroupingListContext ctx);
	/**
	 * Exit a parse tree produced by {@link RationalCoreParser#groupingList}.
	 * @param ctx the parse tree
	 */
	void exitGroupingList(RationalCoreParser.GroupingListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RationalUnit}
	 * labeled alternative in {@link RationalCoreParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterRationalUnit(RationalCoreParser.RationalUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RationalUnit}
	 * labeled alternative in {@link RationalCoreParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitRationalUnit(RationalCoreParser.RationalUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntUnit}
	 * labeled alternative in {@link RationalCoreParser#num}.
	 * @param ctx the parse tree
	 */
	void enterIntUnit(RationalCoreParser.IntUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntUnit}
	 * labeled alternative in {@link RationalCoreParser#num}.
	 * @param ctx the parse tree
	 */
	void exitIntUnit(RationalCoreParser.IntUnitContext ctx);
}