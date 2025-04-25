// Generated from ./ComplexExpr.g4 by ANTLR 4.13.1
package calculator.parser.complex;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ComplexExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ComplexExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ComplexExprParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry(ComplexExprParser.EntryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AbsValInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsValInfix(ComplexExprParser.AbsValInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CartesianInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCartesianInfix(ComplexExprParser.CartesianInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpoInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpoInfix(ComplexExprParser.ExpoInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumInfix(ComplexExprParser.NumInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArgCisInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgCisInfix(ComplexExprParser.ArgCisInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PolarInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolarInfix(ComplexExprParser.PolarInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryMulDivInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryMulDivInfix(ComplexExprParser.BinaryMulDivInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GroupInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupInfix(ComplexExprParser.GroupInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryAddSubInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryAddSubInfix(ComplexExprParser.BinaryAddSubInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImagInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImagInfix(ComplexExprParser.ImagInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComboRootInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComboRootInfix(ComplexExprParser.ComboRootInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EulerExpInfix}
	 * labeled alternative in {@link ComplexExprParser#formInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEulerExpInfix(ComplexExprParser.EulerExpInfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComboRootPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComboRootPrefix(ComplexExprParser.ComboRootPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArgCisPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgCisPrefix(ComplexExprParser.ArgCisPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EulerExpPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEulerExpPrefix(ComplexExprParser.EulerExpPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiOpMulDivPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiOpMulDivPrefix(ComplexExprParser.MultiOpMulDivPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiOpAddSubPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiOpAddSubPrefix(ComplexExprParser.MultiOpAddSubPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AbsValPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsValPrefix(ComplexExprParser.AbsValPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GroupPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupPrefix(ComplexExprParser.GroupPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImagPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImagPrefix(ComplexExprParser.ImagPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumPrefix(ComplexExprParser.NumPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CartesianPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCartesianPrefix(ComplexExprParser.CartesianPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PolarPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolarPrefix(ComplexExprParser.PolarPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpoPrefix}
	 * labeled alternative in {@link ComplexExprParser#formPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpoPrefix(ComplexExprParser.ExpoPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComboRootPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComboRootPosfix(ComplexExprParser.ComboRootPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArgCisPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgCisPosfix(ComplexExprParser.ArgCisPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EulerExpPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEulerExpPosfix(ComplexExprParser.EulerExpPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiOpMulDivPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiOpMulDivPosfix(ComplexExprParser.MultiOpMulDivPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiOpAddSubPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiOpAddSubPosfix(ComplexExprParser.MultiOpAddSubPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AbsValPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsValPosfix(ComplexExprParser.AbsValPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImagPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImagPosfix(ComplexExprParser.ImagPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumPosfix(ComplexExprParser.NumPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GroupPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupPosfix(ComplexExprParser.GroupPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CartesianPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCartesianPosfix(ComplexExprParser.CartesianPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PolarPosfix}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolarPosfix(ComplexExprParser.PolarPosfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpoC}
	 * labeled alternative in {@link ComplexExprParser#formPosfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpoC(ComplexExprParser.ExpoCContext ctx);
}