package parser;

import calculator.values.NumericValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ExpressionParser} covering basic arithmetic,
 * notation modes (INFIX, PREFIX, POSTFIX, AUTO), angle modes,
 * keywords (res, pi), unary minus, percentage, and functions:
 * sin, cos, tan, E, exp, ln, sqrt, inv, root, power, log.
 */
public class TestExpressionParser {

    private ExpressionParser parser;

    @BeforeEach
    void setUp() {
        // Reset global parser state
        ExpressionParser.setMode(ExpressionParser.Mode.DEGREES);
        ExpressionParser.setNotation(ExpressionParser.Notation.INFIX);
        ExpressionParser.setDisplay(ExpressionParser.Display.DECIMAL);
        parser = new ExpressionParser();
        // Reset last_result
        ExpressionParser.last_result = null;
    }

    @Test
    void testInfixSimpleAddition() throws Exception {
        NumericValue result = parser.parse("2+3");
        assertEquals("5", result.toString());
    }

    @Test
    void testInfixFractionAndDisplayModes() throws Exception {
        ExpressionParser.setDisplay(ExpressionParser.Display.FRACTION);
        NumericValue frac = parser.parse("1/4");
        assertEquals("1/4", frac.toString());

        ExpressionParser.setDisplay(ExpressionParser.Display.DECIMAL);
        NumericValue dec = parser.parse("1/4");
        assertEquals("0.25", dec.toString());
    }

    @Test
    void testUnaryMinus() throws Exception {
        NumericValue result = parser.parse("-3+5");
        assertEquals("2", result.toString());
    }

    @Test
    void testPercentage() throws Exception {
        NumericValue result = parser.parse("50%");
        // 50% => 0.5
        assertEquals("0.5", result.toString());
    }

    @Test
    void testPrefixAddition() throws Exception {
        ExpressionParser.setNotation(ExpressionParser.Notation.PREFIX);
        NumericValue result = parser.parse("+ 2 3");
        assertEquals("5", result.toString());
    }

    @Test
    void testPostfixAddition() throws Exception {
        ExpressionParser.setNotation(ExpressionParser.Notation.POSTFIX);
        NumericValue result = parser.parse("2 3 +");
        assertEquals("5", result.toString());
    }

    @Test
    void testAutoNotation() throws Exception {
        ExpressionParser.setNotation(ExpressionParser.Notation.AUTO);
        // AUTO should currently not evaluate and return null
        assertNull(parser.parse("2+2"));
    }

    @Test
    void testTrigFunctionsInDegreesAndRadians() throws Exception {
        // Degrees
        NumericValue sinDeg = parser.parse("sin(30)");
        assertEquals("0.5", sinDeg.toString());

        NumericValue cosDeg = parser.parse("cos(60)");
        assertEquals("0.5", cosDeg.toString());

        NumericValue tanDeg = parser.parse("tan(45)");
        assertEquals("1", tanDeg.toString());

        // Radians
        ExpressionParser.setMode(ExpressionParser.Mode.RADIANS);
        NumericValue sinRad = parser.parse("sin(pi/2)");
        double val = Double.parseDouble(sinRad.toString());
        assertTrue(val > 0.99 && val < 1.01);
    }

    @Test
    void testResKeywordUsage() throws Exception {
        parser.parse("2+2");
        NumericValue result = parser.parse("res*2");
        assertEquals("8", result.toString());
    }

    @Test
    void testPiKeywordEvaluation() throws Exception {
        NumericValue result = parser.parse("pi");
        double piVal = Double.parseDouble(result.toString());
        assertTrue(piVal > 3.141 && piVal < 3.142);
    }

    @Test
    void testScientificE() throws Exception {
        NumericValue result = parser.parse("E(2)");
        assertEquals("100", result.toString());
    }

    @Test
    void testFunctionExpAndLn() throws Exception {
        NumericValue exp1 = parser.parse("exp(1)");
        double eVal = Double.parseDouble(exp1.toString());
        assertTrue(eVal > 2.718 && eVal < 2.720);

        NumericValue lnE = parser.parse("ln(2.718281828)");
        double lnVal = Double.parseDouble(lnE.toString());
        assertTrue(lnVal > 0.999 && lnVal < 1.001);
    }

    @Test
    void testSqrtInvRootPowerLog() throws Exception {
        NumericValue sqrt = parser.parse("sqrt(9)");
        assertEquals("3", sqrt.toString());

        NumericValue inv = parser.parse("inv(4)");
        assertEquals("0.25", inv.toString());

        NumericValue root = parser.parse("root(81,2)");
        assertEquals(1, root.getValueInt());

        NumericValue power = parser.parse("power(2,3)");
        assertEquals("8", power.toString());

        NumericValue log10 = parser.parse("log(100,10)");
        assertEquals("0.5", log10.toString());
    }

    @Test
    void testInvalidExpressionReturnsNull() throws Exception {
        // Missing operand
        assertNull(parser.parse("2+"));
    }
}
