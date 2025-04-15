package calculator.values;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;

 class RealValueTest {

    @Test
     void testGetValue() {
        RealValue r = new RealValue(3.14, 3);
        // Build an expected BigDecimal using the same MathContext
        MathContext mc = new MathContext(3);
        BigDecimal expected = new BigDecimal("3.14", mc);
        // Using compareTo to avoid scale issues
        assertEquals(0, r.getValue().compareTo(expected), "getValue() should return the expected BigDecimal");
    }

    @Test
     void testAddition() {
        RealValue r1 = new RealValue(2.5, 3);
        RealValue r2 = new RealValue(1.5, 3);
        RealValue result = (RealValue) r1.add(r2);
        BigDecimal expected = new BigDecimal("4.0");
        assertEquals(0, result.getValue().compareTo(expected), "2.5 + 1.5 should equal 4.0");
    }

    @Test
     void testSubtraction() {
        RealValue r1 = new RealValue(5.0, 3);
        RealValue r2 = new RealValue(2.5, 3);
        RealValue result = (RealValue) r1.subtract(r2);
        BigDecimal expected = new BigDecimal("2.5");
        assertEquals(0, result.getValue().compareTo(expected), "5.0 - 2.5 should equal 2.5");
    }

    @Test
     void testMultiplication() {
        RealValue r1 = new RealValue(2.0, 3);
        RealValue r2 = new RealValue(3.0, 3);
        RealValue result = (RealValue) r1.multiply(r2);
        BigDecimal expected = new BigDecimal("6.0");
        assertEquals(0, result.getValue().compareTo(expected), "2.0 * 3.0 should equal 6.0");
    }

    @Test
     void testDivision() {
        RealValue r1 = new RealValue(6.0, 3);
        RealValue r2 = new RealValue(3.0, 3);
        RealValue result = (RealValue) r1.divide(r2);
        BigDecimal expected = new BigDecimal("2.0");
        assertEquals(0, result.getValue().compareTo(expected), "6.0 / 3.0 should equal 2.0");
    }

    @Test
     void testDivisionByZero() {
        RealValue r1 = new RealValue(5.0, 3);
        RealValue rZero = new RealValue(0.0, 3);
        assertThrows(ArithmeticException.class, () -> r1.divide(rZero), "Division by zero should throw ArithmeticException");
    }

    @Test
     void testEqualsAndHashCode() {
        RealValue r1 = new RealValue(2.718, 4);
        RealValue r2 = new RealValue(new BigDecimal("2.718"), 4);
        assertEquals(r1, r2, "RealValue objects with the same value should be equal");
        assertEquals(r1.hashCode(), r2.hashCode(), "Equal RealValue objects should have the same hashCode");
    }

    @Test
     void testToString() {
        RealValue r = new RealValue(3.14159, 6);
        String str = r.toString();
        // Since toString() uses toPlainString() in our implementation, we expect a plain decimal representation.
        assertEquals("3.14159", str, "toString() should return the plain string representation");
    }
}
