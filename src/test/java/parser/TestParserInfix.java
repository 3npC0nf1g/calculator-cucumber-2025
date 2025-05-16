package parser;

import calculator.IllegalConstruction;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static parser.MyInfixParser.*;

/**
 * Unit tests for {@link MyInfixParser}. Covers tree building, evaluation of operators,
 * functions (sin, cos, tan, E, exp, ln, sqrt, inv, root, power, log),
 * percentage, unary minus, complex parsing, and error conditions.
 */
public class TestParserInfix {

    // -------------------------------
    // 1. Tests basiques de literals
    // -------------------------------
    @Test
    void testSimpleLiteral() throws IllegalConstruction {
        NumericValue v = evaluate(buildTree("42"));
        assertEquals("42", v.toString());
    }

    @Test
    void testNegativeLiteral() throws IllegalConstruction {
        NumericValue v = evaluate(buildTree("-5"));
        assertEquals("-5", v.toString());
    }

    @Test
    void testDecimalLiteral() throws IllegalConstruction {
        NumericValue v = evaluate(buildTree("123.456"));
        assertEquals("123.456", v.toString());
    }

    @Test
    void testScientificLiteralBigDecimal() throws IllegalConstruction {
        // BigDecimal accepte la notation scientifique
        NumericValue v = evaluate(buildTree("1E3"));
        // Selon BigDecimal#toString() on peut obtenir "1E+3"
        assertTrue(v.toString().matches("1E\\+?3|1000"));
    }

    @Test
    void testToDoubleValidAndInvalid() {
        assertEquals(3.14, toDouble("3.14"));
        assertThrows(NumberFormatException.class, () -> toDouble("abc"));
    }

    // -------------------------------------
    // 2. Tests opérateurs et associativité
    // -------------------------------------
    @Test
    void testAllBasicOperators() throws IllegalConstruction {
        assertEquals("7",  evaluate(buildTree("3+4")).toString());
        assertEquals("-1", evaluate(buildTree("3-4")).toString());
        assertEquals("12", evaluate(buildTree("3*4")).toString());
        assertEquals("0.75", evaluate(buildTree("3/4")).toString());
    }

    @Test
    void testOperatorPrecedenceAndAssociativity() throws IllegalConstruction {
        // 2+3*4 = 14
        assertEquals("14", evaluate(buildTree("2+3*4")).toString());
        // (2+3)*4 = 20
        assertEquals("20", evaluate(buildTree("(2+3)*4")).toString());
        // gauche-associatif pour "-" et "+"
        assertEquals("-5", evaluate(buildTree("2-3-4")).toString());
    }

    @Test
    void testDivisionFractionMode() throws IllegalConstruction {
        // bascule en mode fraction
        ExpressionParser.setDisplay(ExpressionParser.Display.FRACTION);
        NumericValue frac = evaluate(buildTree("2/4"));
        assertEquals("1/2", frac.toString());
        ExpressionParser.setDisplay(ExpressionParser.Display.DECIMAL);
    }

    // ---------------------------
    // 3. Tests pour le pourcentage
    // ---------------------------
    @Test
    void testPercentageAloneAndChained() throws IllegalConstruction {
        assertEquals("0.5", evaluate(buildTree("50%")).toString());
        // 200% * 5 = 2 * 5 = 10
        assertEquals("10", evaluate(buildTree("200%*5")).toString());

    }


    // -------------------------------------------------
    // 5. Tests exhaustifs des fonctions binaires (TWO_ARG_FUNCS)
    // -------------------------------------------------
    @Test
    void testRootPowerLogNCrNPr() throws IllegalConstruction {
        assertEquals("3",   evaluate(buildTree("root(3,27)")).toString());
        assertEquals("32",  evaluate(buildTree("power(2,5)")).toString());
        assertEquals("3",   evaluate(buildTree("log(2,8)")).toString());
        assertEquals("10",  evaluate(buildTree("ncr(5,3)")).toString());
        assertEquals("60",  evaluate(buildTree("npr(5,3)")).toString());
    }

    @Test
    void testBinaryFunctionMissingArgsThrows() {
        assertThrows(IllegalStateException.class, () -> buildTree("root()"));
        assertThrows(IllegalStateException.class, () -> buildTree("ncr(5)"));
    }

    // -----------------------------------------------------
    // 6. Tests complexes : parsing et opérations sur [a+bi]
    // -----------------------------------------------------
    @Test
    void testComplexPureRealAndImag() throws IllegalConstruction {
        NumericValue pureReal = evaluate(buildTree("[7]"));
        assertEquals("7.0 + 0.0i", pureReal.toString());

        NumericValue pureImag = evaluate(buildTree("[5i]"));
        assertEquals("0.0 + 5.0i", pureImag.toString());
    }

    @Test
    void testComplexAdditionAndMultiplication() throws IllegalConstruction {
        NumericValue sum = evaluate(buildTree("([3+2i] + [1-1i])"));
        assertEquals("4.0 + 1.0i", sum.toString());

        NumericValue prod = evaluate(buildTree("([1+2i] * [3+4i])"));
        // (1+2i)*(3+4i) = -5 + 10i
        assertEquals("-5.00 + 10.00i", prod.toString());
    }

    @Test
    void testComplexMalformedImagThrows() {
        // "[i]" ➔ Double.parseDouble("") dans parseComplex
        assertThrows(NumberFormatException.class, () -> evaluate(buildTree("[i]")));
    }

    // ------------------------------------------------
    // 7. Tests d’erreurs de parsing (parenthèses, char)
    // ------------------------------------------------
    @Test
    void testInvalidCharacter2Throws() {
        assertThrows(IllegalArgumentException.class, () -> buildTree("2 $ 3"));
        assertThrows(IllegalArgumentException.class, () -> buildTree("2^3"));  // ^ n’est pas un opérateur supporté
    }

    @Test
    void testUnmatchedParentheses2Throws() {
        assertThrows(IllegalArgumentException.class, () -> buildTree("(1+2"));
        assertThrows(IllegalArgumentException.class, () -> buildTree("1+2)"));
    }

    @Test
    void testExtraCommaOrMissingOperandThrows() {
        assertThrows(IllegalArgumentException.class, () -> buildTree("sin(,2"));
        assertThrows(IllegalStateException.class,     () -> buildTree("1+"));
        assertThrows(IllegalStateException.class,     () -> buildTree("2,3"));
    }

    // --------------------------------------------------
    // 8. Test d’une expression imbriquée mixte complexe
    // --------------------------------------------------
    @Test
    void testComplexNestedExpression() throws IllegalConstruction {
        // sin(0)=0, log₂(8)=3 → 3^2 = 9
        NumericValue v = evaluate(buildTree("power(sin(0) + log(2,8), 2)"));
        assertEquals("9", v.toString());
    }

    @Test
    void testBuildTreeSimpleExpression() {
        MyInfixParser.Node root = buildTree("2+3*4");
        assertEquals("+", root.value);
        assertEquals("2", root.left.value);
        assertEquals("*", root.right.value);
        assertEquals("3", root.right.left.value);
        assertEquals("4", root.right.right.value);
    }

    @Test
    void testBuildTreeUnaryMinus() {
        MyInfixParser.Node root = buildTree("-5+3");
        assertEquals("+", root.value);
        assertEquals("-5", root.left.value);
        assertEquals("3", root.right.value);
    }

    @Test
    void testInvalidCharacterThrows() {
        assertThrows(IllegalArgumentException.class, () -> buildTree("2 @ 3"));
    }

    @Test
    void testUnmatchedParenthesesThrows() {
        assertThrows(IllegalArgumentException.class, () -> buildTree("(1+2"));
        assertThrows(IllegalArgumentException.class, () -> buildTree("1+2)"));
    }

    @Test
    void testMissingOperandThrows() {
        assertThrows(IllegalStateException.class, () -> buildTree("1+"));
    }

    @Test
    void testEvaluateBasicOperators() throws IllegalConstruction {
        MyInfixParser.Node root = buildTree("(10 - 2) * 3 / 4");
        NumericValue result = evaluate(root);
        assertEquals("6", result.toString()); // (8*3)/4
    }

    @Test
    void testDivisionDecimal() throws IllegalConstruction {
        NumericValue result = evaluate(buildTree("10/4"));
        assertEquals("2.5", result.toString());
    }

    @Test
    void testFractionalResult() throws IllegalConstruction {
        NumericValue result = evaluate(buildTree("1/3"));
        // approx 0.3333333...
        double val = Double.parseDouble(result.toString());
        assertTrue(val > 0.3333 && val < 0.334);
    }

    @Test
    void testTrigonometricFunctions() throws IllegalConstruction {
        // default calculator mode is radians
        
        NumericValue cosVal = evaluate(buildTree("cos(0)"));
        assertEquals("1", cosVal.toString());

        NumericValue tanVal = evaluate(buildTree("tan(0)"));
        assertEquals("0", tanVal.toString());
    }

    @Test
    void testScientificE() throws IllegalConstruction {
        NumericValue result = evaluate(buildTree("E(3)"));
        assertEquals("1000", result.toString());
    }

    @Test
    void testExpAndLn() throws IllegalConstruction {
        NumericValue expVal = evaluate(buildTree("exp(1)"));
        double ev = Double.parseDouble(expVal.toString());
        assertTrue(ev > 2.718 && ev < 2.720);

        NumericValue lnVal = evaluate(buildTree("ln(10)"));
        double lv = Double.parseDouble(lnVal.toString());
        assertTrue(lv > 2.302 && lv < 2.304);
    }

    @Test
    void testSqrtInv() throws IllegalConstruction {
        NumericValue sqrtVal = evaluate(buildTree("sqrt(16)"));
        assertEquals("4", sqrtVal.toString());

        NumericValue invVal = evaluate(buildTree("inv(8)"));
        assertEquals("0.125", invVal.toString());
    }

    @Test
    void testRootPowerLog() throws IllegalConstruction {
        NumericValue rootVal = evaluate(buildTree("root(27,3)"));
        assertEquals(1, rootVal.getValueInt());

        NumericValue powVal = evaluate(buildTree("power(2,5)"));
        assertEquals("32", powVal.toString());

        NumericValue logVal = evaluate(buildTree("log(2,8)"));
        assertEquals("3", logVal.toString());
    }

    @Test
    void testPercentage() throws IllegalConstruction {
        NumericValue pctVal = evaluate(buildTree("50%"));
        assertEquals("0.5", pctVal.toString());
    }

    @Test
    void testComplexParsingAndArithmetic() throws IllegalConstruction {
        // pure imaginary
        NumericValue imm = evaluate(buildTree("[5i]"));
        assertEquals("0.0 + 5.0i", imm.toString());

        // pure real in brackets
        NumericValue real = evaluate(buildTree("[7]"));
        assertEquals("7.0 + 0.0i", real.toString());

        // addition
        NumericValue add = evaluate(buildTree("([3+2i] + [1-1i])"));
        assertEquals("4.0 + 1.0i", add.toString());

        // multiplication
        NumericValue mul = evaluate(buildTree("([1+2i] * [3+4i])"));
        assertEquals("-5.00 + 10.00i", mul.toString());
    }

    @Test
    void testNestedExpressionEvaluation() throws IllegalConstruction {
        NumericValue result = evaluate(buildTree("((1+2)*(3+4))/(2+5)"));
        assertEquals("3", result.toString());
    }

}
