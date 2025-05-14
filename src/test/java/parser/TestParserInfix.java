package parser;

import calculator.IllegalConstruction;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static parser.MyInfixParser.buildTree;
import static parser.MyInfixParser.evaluate;

/**
 * Unit tests for {@link MyInfixParser}. Covers tree building, evaluation of operators,
 * functions (sin, cos, tan, E, exp, ln, sqrt, inv, root, power, log),
 * percentage, unary minus, complex parsing, and error conditions.
 */
public class TestParserInfix {

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
        NumericValue sinVal = evaluate(buildTree("sin(3.1415926535/2)"));
        double sv = Double.parseDouble(sinVal.toString());
        assertTrue(sv > 0.999 && sv < 1.001);

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
