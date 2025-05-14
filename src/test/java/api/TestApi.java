package api;

import calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;

import static org.junit.jupiter.api.Assertions.*;

public class TestApi {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
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
}
