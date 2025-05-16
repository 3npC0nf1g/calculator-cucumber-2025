
package api;

import calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;

import static org.junit.jupiter.api.Assertions.*;

public class TestApi {

    private ExpressionParser parser;
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        // Reset global parser state
        calculatorService= new CalculatorService();

        ExpressionParser.setMode(ExpressionParser.Mode.DEGREES);
        ExpressionParser.setNotation(ExpressionParser.Notation.INFIX);
        ExpressionParser.setDisplay(ExpressionParser.Display.DECIMAL);
        parser = new ExpressionParser();
        // Reset last_result
        ExpressionParser.last_result = null;
    }

    // Basic arithmetic operations
    @Test public void testAddition() throws Exception {
        assertEquals("5", calculatorService.getRep("2 + 3"));
    }

    @Test public void testSubtraction() throws Exception {
        assertEquals("3", calculatorService.getRep("5 - 2"));
    }

    @Test public void testMultiplication() throws Exception {
        assertEquals("24", calculatorService.getRep("4 * 6"));
    }

    @Test public void testDivision() throws Exception {
        assertEquals("5", calculatorService.getRep("10 / 2"));
    }

    @Test public void testModulus() throws Exception {
        assertEquals("0.1", calculatorService.getRep("10%"));
    }

    @Test public void testPowerFunction() throws Exception {
        assertEquals("8", calculatorService.getRep("power(2,3)"));
    }

    @Test public void testSqrtFunction() throws Exception {
        assertEquals("4", calculatorService.getRep("sqrt(16)"));
    }

    @Test public void testLogFunction() throws Exception {
        assertEquals("3", calculatorService.getRep("log(10,1000)"));
    }

    @Test public void testParentheses() throws Exception {
        assertEquals("20", calculatorService.getRep("(2 + 3) * 4"));
    }

    @Test public void testDecimalAddition() throws Exception {
        assertEquals("5.6", calculatorService.getRep("2.5 + 3.1"));
    }

    @Test public void testInvalidExpression() {
        assertThrows(Exception.class, () -> {
            calculatorService.getRep("2 + )");
        });
    }

    // Angle and display mode toggle
    @Test public void testToggleAngleMode() {
        ExpressionParser.setMode(ExpressionParser.Mode.RADIANS);
        calculatorService.toggleAngleMode();
        assertEquals(ExpressionParser.Mode.DEGREES, ExpressionParser.getMode());

        calculatorService.toggleAngleMode();
        assertEquals(ExpressionParser.Mode.RADIANS, ExpressionParser.getMode());
    }

    @Test public void testToggleDisplayMode() {
        ExpressionParser.setDisplay(ExpressionParser.Display.DECIMAL);
        calculatorService.toggleDisplayMode();
        assertEquals(ExpressionParser.Display.FRACTION, ExpressionParser.getDisplay());

        calculatorService.toggleDisplayMode();
        assertEquals(ExpressionParser.Display.DECIMAL, ExpressionParser.getDisplay());
    }

    @Test public void testLnFunction() throws Exception {
        assertEquals("1", calculatorService.getRep("ln(e)"));
    }

    @Test public void testRootFunction() throws Exception {
        assertEquals("3", calculatorService.getRep("root(27,3)"));
    }

    @Test public void testPiUsage() throws Exception {
        assertEquals("3.141592653589793", calculatorService.getRep("pi"));
    }

    @Test public void testEUsage() throws Exception {
        assertEquals("2.718281828459045", calculatorService.getRep("e"));
    }

    @Test public void testLnOfEToPower3() throws Exception {
        assertEquals("3", calculatorService.getRep("ln(power(e,3))"));
    }

    @Test public void testSqrtOfPi() throws Exception {
        assertEquals("1.77245385091", calculatorService.getRep("sqrt(pi)").substring(0,12));
    }

    @Test public void testLogBase2Of8() throws Exception {
        assertEquals("3", calculatorService.getRep("log(2,8)"));
    }

    @Test public void testRootOfPi() throws Exception {
        assertEquals("1.77245385091", calculatorService.getRep("root(pi,2)").substring(0,12));
    }
}