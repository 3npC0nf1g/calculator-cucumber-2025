package parser;

import calculator.Notation;
import calculator.values.NumericValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static parser.ExpressionParser.*;

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
        setMode(ExpressionParser.Mode.DEGREES);
        setNotation(ExpressionParser.Notation.INFIX);
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
        setNotation(ExpressionParser.Notation.PREFIX);
        NumericValue result = parser.parse("+ 2 3");
        assertEquals("5", result.toString());
    }

    @Test
    void testPostfixAddition() throws Exception {
        setNotation(ExpressionParser.Notation.POSTFIX);
        NumericValue result = parser.parse("2 3 +");
        assertEquals("5", result.toString());
    }

    @Test
    void testAutoNotation() throws Exception {
        setNotation(ExpressionParser.Notation.AUTO);
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
        setMode(ExpressionParser.Mode.RADIANS);
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

    // 1) getters / setters
    @Test
    void testModeSetterGetter() {
        assertEquals(ExpressionParser.Mode.DEGREES, getMode());
        setMode(Mode.RADIANS);
        assertEquals(Mode.RADIANS, getMode());
    }

    @Test
    void testNotationSetterGetter() {
        assertEquals(ExpressionParser.Notation.INFIX, getNotation());
        setNotation(ExpressionParser.Notation.INFIX);
        assertEquals(ExpressionParser.Notation.INFIX, getNotation());
        setNotation(ExpressionParser.Notation.PREFIX);
        assertEquals(ExpressionParser.Notation.PREFIX, getNotation());
        setNotation(ExpressionParser.Notation.AUTO);
        assertEquals(ExpressionParser.Notation.AUTO, getNotation());
    }

    @Test
    void testDisplaySetterGetter() {
        assertEquals(Display.DECIMAL, getDisplay());
        setDisplay(Display.FRACTION);
        assertEquals(Display.FRACTION, getDisplay());
        setDisplay(Display.DECIMAL);
        assertEquals(Display.DECIMAL, getDisplay());
    }

    // 2) main() ne doit pas lever d’exception
    @Test
    void testMainDoesNotThrow() {
        assertDoesNotThrow(() -> ExpressionParser.main(new String[]{"foo", "bar"}));
    }

    // 3) préfixe / postfixe valides
    @Test
    void testValidPrefixExpression() throws Exception {
        setNotation(ExpressionParser.Notation.PREFIX);
        // (* (+ 1 2) 3)  → (1+2)*3 = 9
        NumericValue v = parser.parse("* + 1 2 3");
        assertEquals("9", v.toString());
    }

    @Test
    void testValidPostfixExpression() throws Exception {
        setNotation(ExpressionParser.Notation.POSTFIX);
        // 1 2 + 3 * → (1+2)*3 = 9
        NumericValue v = parser.parse("1 2 + 3 *");
        assertEquals("9", v.toString());
    }

    // 4) préfixe / postfixe INVALIDES → null
    @Test
    void testInvalidPrefixReturnsNull() {
        setNotation(ExpressionParser.Notation.PREFIX);
        try {
            assertNull(parser.parse("foo bar baz"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInvalidPostfixReturnsMult() throws Exception {
        setNotation(ExpressionParser.Notation.POSTFIX);
        // opérateur manquant
        assertEquals(parser.parse("1 2 3").toString(),"6");
    }

    // 5) notation AUTO → renvoie toujours null (branche actuelle)
    @Test
    void testAutoNotationAlwaysNull() throws Exception {
        setNotation(ExpressionParser.Notation.AUTO);
        assertNull(parser.parse("2+2"));
        assertNull(parser.parse("+ 1 1"));
        assertNull(parser.parse("1 1 +"));
    }

    // 6) dernier résultat « res » et constante « pi »
    @Test
    void testResAndPiInMixedExpression() throws Exception {
        // on stocke d’abord 2+3 = 5
        NumericValue first = parser.parse("2+3");
        assertEquals("5", first.toString());
        // on relit res et pi
        NumericValue v = parser.parse("res + pi");
        double d = Double.parseDouble(v.toString());
        assertTrue(d > 5 + 3.1415 && d < 5 + 3.142);
    }

    @Test
    void testPiOnly() throws Exception {
        NumericValue v = parser.parse("pi");
        double d = Double.parseDouble(v.toString());
        assertTrue(d > 3.141 && d < 3.142);
    }

    // 7) remise à zéro de last_result quand on obtient NaN
    @Test
    void testLastResultResetOnNaN() throws Exception {
        // ln(-1) doit produire NaN
        NumericValue nan = parser.parse("1/0");
        //assertThrows(ArithmeticException.class, () -> parser.parse("ln(-1)"));
        assertEquals("NaN", nan.toString());
        // last_result doit être égal à 0
        assertNotNull(last_result);
        assertEquals("0", last_result.toString());
    }

    // 8) pi et res en préfixe et postfixe
    @Test
    void testPrefixWithPiAndRes() throws Exception {
        setNotation(ExpressionParser.Notation.INFIX);
        parser.parse("2+2");                         // last_result = 4
        setNotation(ExpressionParser.Notation.PREFIX);
        NumericValue v = parser.parse("+ res pi");   // 4 + π
        double d = Double.parseDouble(v.toString());
        assertTrue(d > 7.1415 && d < 7.142);
    }

    @Test
    void testPostfixWithPiAndRes() throws Exception {
        setNotation(ExpressionParser.Notation.POSTFIX);
        parser.parse("3*3");                         // last_result = 9
        NumericValue v = parser.parse("res pi +");   // 9 + π
        double d = Double.parseDouble(v.toString());
        assertTrue(d > 12.1415 && d < 12.142);
    }
}
