// Generated from ComplexExpr.g4 by ANTLR 4.13.1
package parser.complex;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import parser.complex.ComplexExprParser;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ComplexExprParser}.
 */
public interface ComplexExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ComplexExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ComplexExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ComplexExprParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RootOnlyInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterRootOnlyInfix(ComplexExprParser.RootOnlyInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RootOnlyInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitRootOnlyInfix(ComplexExprParser.RootOnlyInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CartesianInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterCartesianInfix(ComplexExprParser.CartesianInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CartesianInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitCartesianInfix(ComplexExprParser.CartesianInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpoInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterExpoInfix(ComplexExprParser.ExpoInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpoInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitExpoInfix(ComplexExprParser.ExpoInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterNumInfix(ComplexExprParser.NumInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitNumInfix(ComplexExprParser.NumInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PolarInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterPolarInfix(ComplexExprParser.PolarInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PolarInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitPolarInfix(ComplexExprParser.PolarInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryMulDivInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterBinaryMulDivInfix(ComplexExprParser.BinaryMulDivInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryMulDivInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitBinaryMulDivInfix(ComplexExprParser.BinaryMulDivInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComboRootInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterComboRootInfix(ComplexExprParser.ComboRootInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComboRootInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitComboRootInfix(ComplexExprParser.ComboRootInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsValInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterAbsValInfix(ComplexExprParser.AbsValInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsValInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitAbsValInfix(ComplexExprParser.AbsValInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArgCisInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterArgCisInfix(ComplexExprParser.ArgCisInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArgCisInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitArgCisInfix(ComplexExprParser.ArgCisInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GroupInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterGroupInfix(ComplexExprParser.GroupInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GroupInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitGroupInfix(ComplexExprParser.GroupInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryAddSubInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterBinaryAddSubInfix(ComplexExprParser.BinaryAddSubInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryAddSubInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitBinaryAddSubInfix(ComplexExprParser.BinaryAddSubInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImagInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterImagInfix(ComplexExprParser.ImagInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImagInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitImagInfix(ComplexExprParser.ImagInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EulerExpInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void enterEulerExpInfix(ComplexExprParser.EulerExpInfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EulerExpInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 */
	void exitEulerExpInfix(ComplexExprParser.EulerExpInfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComboRootPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterComboRootPrefix(ComplexExprParser.ComboRootPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComboRootPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitComboRootPrefix(ComplexExprParser.ComboRootPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RootOnlyPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterRootOnlyPrefix(ComplexExprParser.RootOnlyPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RootOnlyPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitRootOnlyPrefix(ComplexExprParser.RootOnlyPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArgCisPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterArgCisPrefix(ComplexExprParser.ArgCisPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArgCisPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitArgCisPrefix(ComplexExprParser.ArgCisPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EulerExpPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterEulerExpPrefix(ComplexExprParser.EulerExpPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EulerExpPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitEulerExpPrefix(ComplexExprParser.EulerExpPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiOpMulDivPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterMultiOpMulDivPrefix(ComplexExprParser.MultiOpMulDivPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiOpMulDivPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitMultiOpMulDivPrefix(ComplexExprParser.MultiOpMulDivPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiOpAddSubPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterMultiOpAddSubPrefix(ComplexExprParser.MultiOpAddSubPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiOpAddSubPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitMultiOpAddSubPrefix(ComplexExprParser.MultiOpAddSubPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsValPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterAbsValPrefix(ComplexExprParser.AbsValPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsValPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitAbsValPrefix(ComplexExprParser.AbsValPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImagPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterImagPrefix(ComplexExprParser.ImagPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImagPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitImagPrefix(ComplexExprParser.ImagPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterNumPrefix(ComplexExprParser.NumPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitNumPrefix(ComplexExprParser.NumPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GroupPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterGroupPrefix(ComplexExprParser.GroupPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GroupPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitGroupPrefix(ComplexExprParser.GroupPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CartesianPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterCartesianPrefix(ComplexExprParser.CartesianPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CartesianPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitCartesianPrefix(ComplexExprParser.CartesianPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PolarPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterPolarPrefix(ComplexExprParser.PolarPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PolarPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitPolarPrefix(ComplexExprParser.PolarPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpoPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void enterExpoPrefix(ComplexExprParser.ExpoPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpoPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 */
	void exitExpoPrefix(ComplexExprParser.ExpoPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComboRootPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterComboRootPosfix(ComplexExprParser.ComboRootPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComboRootPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitComboRootPosfix(ComplexExprParser.ComboRootPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RootMaybeIPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterRootMaybeIPosfix(ComplexExprParser.RootMaybeIPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RootMaybeIPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitRootMaybeIPosfix(ComplexExprParser.RootMaybeIPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArgCisPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterArgCisPosfix(ComplexExprParser.ArgCisPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArgCisPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitArgCisPosfix(ComplexExprParser.ArgCisPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EulerExpPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterEulerExpPosfix(ComplexExprParser.EulerExpPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EulerExpPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitEulerExpPosfix(ComplexExprParser.EulerExpPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiOpMulDivPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterMultiOpMulDivPosfix(ComplexExprParser.MultiOpMulDivPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiOpMulDivPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitMultiOpMulDivPosfix(ComplexExprParser.MultiOpMulDivPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiOpAddSubPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterMultiOpAddSubPosfix(ComplexExprParser.MultiOpAddSubPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiOpAddSubPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitMultiOpAddSubPosfix(ComplexExprParser.MultiOpAddSubPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsValPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterAbsValPosfix(ComplexExprParser.AbsValPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsValPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitAbsValPosfix(ComplexExprParser.AbsValPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImagPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterImagPosfix(ComplexExprParser.ImagPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImagPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitImagPosfix(ComplexExprParser.ImagPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterNumPosfix(ComplexExprParser.NumPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitNumPosfix(ComplexExprParser.NumPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GroupPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterGroupPosfix(ComplexExprParser.GroupPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GroupPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitGroupPosfix(ComplexExprParser.GroupPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CartesianPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterCartesianPosfix(ComplexExprParser.CartesianPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CartesianPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitCartesianPosfix(ComplexExprParser.CartesianPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PolarPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterPolarPosfix(ComplexExprParser.PolarPosfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PolarPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitPolarPosfix(ComplexExprParser.PolarPosfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpoC}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void enterExpoC(ComplexExprParser.ExpoCContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpoC}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 */
	void exitExpoC(ComplexExprParser.ExpoCContext ctx);
}