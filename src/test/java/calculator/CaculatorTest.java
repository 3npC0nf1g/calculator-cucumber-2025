package calculator;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testDefaultUseRadiansIsTrue() {
        assertTrue(calculator.isUseRadians());
    }

    @Test
    void testSetUseRadians() {
        calculator.setUseRadians(false);
        assertFalse(calculator.isUseRadians());

        calculator.setUseRadians(true);
        assertTrue(calculator.isUseRadians());
    }

    @Test
    void testSinInRadians() {
        calculator.setUseRadians(true);
        double result = calculator.sin(Math.PI / 2);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testSinInDegrees() {
        calculator.setUseRadians(false);
        double result = calculator.sin(90);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testCosInDegrees() {
        calculator.setUseRadians(false);
        double result = calculator.cos(0);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testTanInDegrees() {
        calculator.setUseRadians(false);
        double result = calculator.tan(45);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testCosInRadians() {
        calculator.setUseRadians(true);
        double result = calculator.cos(Math.PI);
        assertEquals(-1.0, result, 1e-9);
    }

    @Test
    void testTanInRadians() {
        calculator.setUseRadians(true);
        double result = calculator.tan(Math.PI / 4);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testEvalWithBasicExpression() {
        Expression expr = new MyNumber(new IntegerValue(5));
        NumericValue result = calculator.eval(expr);
        assertEquals(new IntegerValue(5), result);
    }
}
