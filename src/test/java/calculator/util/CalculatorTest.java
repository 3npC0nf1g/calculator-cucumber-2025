package calculator.util;

import calculator.*;

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
        calculator.setUseSecondFunction(false);
        double result = calculator.sin(Math.PI / 2);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testSinInDegrees() {
        calculator.setUseRadians(false);
        calculator.setUseSecondFunction(false);
        double result = calculator.sin(90);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testSinInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double result = calculator.sin(1.0); // should call asin(1.0)
        assertEquals(Math.asin(1.0), result, 1e-9);
    }

    @Test
    void testCosInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double result = calculator.cos(0.0); // should call acos(0.0)
        assertEquals(Math.acos(0.0), result, 1e-9);
    }

    @Test
    void testTanInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double result = calculator.tan(1.0); // should call atan(1.0)
        assertEquals(Math.atan(1.0), result, 1e-9);
    }

    @Test
    void testSinhInRadians() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(false);
        double result = calculator.sinh(1.0);
        assertEquals(Math.sinh(1.0), result, 1e-9);
    }

    @Test
    void testSinhInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double result = calculator.sinh(1.0); // should call asinh(1.0)
        double expected = Math.log(1.0 + Math.sqrt(1.0*1.0 + 1));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testCoshInDegrees() {
        calculator.setUseRadians(false);
        calculator.setUseSecondFunction(false);
        double degrees = 0.0;
        double result = calculator.cosh(degrees);
        assertEquals(Math.cosh(0.0), result, 1e-9);
    }

    @Test
    void testCoshInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double val = 2.0;
        double result = calculator.cosh(val); // should call acosh(2.0)
        double expected = Math.log(val + Math.sqrt(val*val - 1));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testTanhInRadians() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(false);
        double result = calculator.tanh(1.0);
        assertEquals(Math.tanh(1.0), result, 1e-9);
    }

    @Test
    void testTanhInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double val = 0.5;
        double result = calculator.tanh(val); // should call atanh(0.5)
        double expected = 0.5 * Math.log((1 + val) / (1 - val));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testCotInDegrees() {
        calculator.setUseRadians(false);
        calculator.setUseSecondFunction(false);
        double result = calculator.cot(45);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testCotInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double val = 1.0;
        double result = calculator.cot(val); // should call acot(1.0)
        double expected = Math.atan(1.0);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testSecInRadians() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(false);
        double result = calculator.sec(0);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testSecInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double val = 2.0;
        double result = calculator.sec(val); // should call asec(2.0)
        double expected = Math.acos(1.0 / val);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testCscInDegrees() {
        calculator.setUseRadians(false);
        calculator.setUseSecondFunction(false);
        double result = calculator.csc(90);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testCscInverseWithSecondFunction() {
        calculator.setUseRadians(true);
        calculator.setUseSecondFunction(true);
        double val = 2.0;
        double result = calculator.csc(val); // should call acsc(2.0)
        double expected = Math.asin(1.0 / val);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testEvalWithBasicExpression() {
        Expression expr = new MyNumber(new IntegerValue(5));
        NumericValue result = calculator.eval(expr);
        assertEquals(new IntegerValue(5), result);
    }

}
