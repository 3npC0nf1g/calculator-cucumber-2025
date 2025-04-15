package calculator.values;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

 class IntegerValueTest {

    @Test
     void testGetValue() {
        IntegerValue val = new IntegerValue(42);
        assertEquals(42, val.getValue());
    }

    @Test
     void testAdditionWithInteger() {
        IntegerValue a = new IntegerValue(10);
        IntegerValue b = new IntegerValue(5);
        NumericValue result = a.add(b);
        assertInstanceOf(IntegerValue.class, result);
        assertEquals(15, ((IntegerValue) result).getValue());
    }

    @Test
     void testAdditionWithReal() {
        IntegerValue a = new IntegerValue(3);
        RealValue b = new RealValue(2.5, 3);
        NumericValue result = a.add(b);
        assertInstanceOf(RealValue.class, result);
        assertEquals(5.5, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
     void testAdditionWithComplex() {
        IntegerValue a = new IntegerValue(4);
        ComplexValue b = new ComplexValue(1.0, 2.0);
        NumericValue result = a.add(b);
        assertInstanceOf(ComplexValue.class, result);
        ComplexValue r = (ComplexValue) result;
        assertEquals(new BigDecimal("5.0"), r.getReal());
        assertEquals(new BigDecimal("2.0"), r.getImag());
    }

    @Test
     void testSubtractionWithInteger() {
        IntegerValue a = new IntegerValue(8);
        IntegerValue b = new IntegerValue(3);
        NumericValue result = a.subtract(b);
        assertEquals(new IntegerValue(5), result);
    }

    @Test
     void testMultiplicationWithInteger() {
        IntegerValue a = new IntegerValue(6);
        IntegerValue b = new IntegerValue(7);
        NumericValue result = a.multiply(b);
        assertEquals(new IntegerValue(42), result);
    }

    @Test
     void testDivisionWithInteger() {
        IntegerValue a = new IntegerValue(10);
        IntegerValue b = new IntegerValue(2);
        NumericValue result = a.divide(b);
        assertEquals(new IntegerValue(5), result);
    }

    @Test
     void testDivisionByZeroInteger() {
        IntegerValue a = new IntegerValue(10);
        IntegerValue zero = new IntegerValue(0);
        assertThrows(ArithmeticException.class, () -> a.divide(zero));
    }

    @Test
     void testToString() {
        IntegerValue a = new IntegerValue(123);
        assertEquals("123", a.toString());
    }

    @Test
     void testEqualsAndHashCode() {
        IntegerValue a = new IntegerValue(5);
        IntegerValue b = new IntegerValue(5);
        IntegerValue c = new IntegerValue(10);

        assertEquals(a, b);
        assertNotEquals(a, c);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
     void testDivideWithReal() {
        IntegerValue a = new IntegerValue(10);
        RealValue b = new RealValue(2.0, 3);
        NumericValue result = a.divide(b);
        assertInstanceOf(RealValue.class, result);
        assertEquals(5.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
     void testMultiplyWithComplex() {
        IntegerValue a = new IntegerValue(2);
        ComplexValue b = new ComplexValue(3.0, 4.0);
        NumericValue result = a.multiply(b);
        assertInstanceOf(ComplexValue.class, result);
        assertEquals(new BigDecimal("6.0"), ((ComplexValue) result).getReal());
        assertEquals(new BigDecimal("8.0"), ((ComplexValue) result).getImag());
    }
}
