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


    @Test
    void testAdditionWithItself() {
       IntegerValue a = new IntegerValue(10);
       NumericValue result = a.add(a);  // Adding the same IntegerValue
       assertInstanceOf(IntegerValue.class, result);
       assertEquals(20, ((IntegerValue) result).getValue());
    }


    @Test
    void testSubtractionWithNegativeResult() {
       IntegerValue a = new IntegerValue(5);
       IntegerValue b = new IntegerValue(8);
       NumericValue result = a.subtract(b);
       assertInstanceOf(IntegerValue.class, result);
       assertEquals(-3, ((IntegerValue) result).getValue());  // Expecting a negative result
    }


    @Test
    void testMultiplicationByZero() {
       IntegerValue a = new IntegerValue(6);
       IntegerValue zero = new IntegerValue(0);
       NumericValue result = a.multiply(zero);
       assertInstanceOf(IntegerValue.class, result);
       assertEquals(0, ((IntegerValue) result).getValue());  // Multiplying by zero should give zero
    }

    @Test
    void testDivisionWithRealResult() {
       IntegerValue a = new IntegerValue(10);
       RealValue b = new RealValue(3.0, 2);  // Result should be a real value with non-integer division
       NumericValue result = a.divide(b);
       assertInstanceOf(RealValue.class, result);
       assertEquals(3.33, ((RealValue) result).getValue().doubleValue(), 1e-9);  // Expecting real result
    }

    @Test
    void testSubtractionWithZero() {
       IntegerValue a = new IntegerValue(10);
       IntegerValue zero = new IntegerValue(0);
       NumericValue result = a.subtract(zero);
       assertInstanceOf(IntegerValue.class, result);
       assertEquals(10, ((IntegerValue) result).getValue());  // Subtracting zero should return the same value
    }


    @Test
    void testEqualsWithDifferentTypes() {
       IntegerValue a = new IntegerValue(5);
       String b = "5";  // Different type (not IntegerValue)
       assertNotEquals(a, b);  // Should not be equal to a String
    }

    @Test
    void testAdditionWithRealDifferentPrecision() {
       IntegerValue a = new IntegerValue(3);
       RealValue b = new RealValue(new BigDecimal("1.2345"), 2); // Precision 3 (1.234)
       NumericValue result = a.add(b);
       assertInstanceOf(RealValue.class, result);
       assertEquals(new BigDecimal("4.23"), ((RealValue) result).getValue());  // Expected: 4.234
    }

    @Test
    void testMultiplicationByNegativeInteger() {
       IntegerValue a = new IntegerValue(6);
       IntegerValue negative = new IntegerValue(-2);
       NumericValue result = a.multiply(negative);
       assertInstanceOf(IntegerValue.class, result);
       assertEquals(-12, ((IntegerValue) result).getValue());  // Expecting negative result
    }


    @Test
    void testPowWithIntegerExponent() {
       IntegerValue base = new IntegerValue(2);
       NumericValue result = base.pow(new IntegerValue(3));
       assertInstanceOf(IntegerValue.class, result);
       assertEquals(8, ((IntegerValue) result).getValue());
    }

    @Test
    void testPowWithRealExponent() {
       IntegerValue base = new IntegerValue(9);
       NumericValue result = base.pow(new RealValue(0.5, 5));
       assertInstanceOf(RealValue.class, result);
       assertEquals(3.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testRootWithIntegerDegree() {
       IntegerValue val = new IntegerValue(27);
       NumericValue result = val.root(new IntegerValue(3));
       assertInstanceOf(RealValue.class, result);
       assertEquals(3.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testRootWithRealDegree() {
       IntegerValue val = new IntegerValue(16);
       NumericValue result = val.root(new RealValue(4.0, 5));
       assertInstanceOf(RealValue.class, result);
       assertEquals(2.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testLogWithIntegerBase() {
       IntegerValue val = new IntegerValue(8);
       NumericValue result = val.log(new IntegerValue(2));
       assertInstanceOf(RealValue.class, result);
       assertEquals(3.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testLogWithRealBase() {
       IntegerValue val = new IntegerValue(9);
       NumericValue result = val.log(new RealValue(3.0, 5));
       assertInstanceOf(RealValue.class, result);
       assertEquals(2.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testLogWithComplexBase() {
       IntegerValue val = new IntegerValue(2);
       ComplexValue base = new ComplexValue(2.0, 0.0);
       NumericValue result = val.log(base);
       assertInstanceOf(ComplexValue.class, result);
       ComplexValue c = (ComplexValue) result;
       // log₂(2) = 1 + 0i
       assertEquals(1.0, c.getReal().doubleValue(), 1e-9);
       assertEquals(0.0, c.getImag().doubleValue(), 1e-9);
    }

    @Test
    void testLogInvalidCasesReturnNaN() {
       IntegerValue val = new IntegerValue(2);
       // base <= 0
       assertEquals("NaN", val.log(new IntegerValue(0)).toString());
       assertEquals("NaN", new IntegerValue(-1).log(new IntegerValue(2)).toString());
       // base == 1
       assertEquals("NaN", val.log(new IntegerValue(1)).toString());
    }

    @Test
    void testInverseNonZero() {
       IntegerValue val = new IntegerValue(4);
       NumericValue inv = val.inverse();
       assertInstanceOf(RationalValue.class, inv);
       assertEquals("1/4", inv.toString());
    }

    @Test
    void testInverseZero() {
       IntegerValue zero = new IntegerValue(0);
       NumericValue inv = zero.inverse();
       assertInstanceOf(RealValue.class, inv);
       assertEquals("NaN", inv.toString());
    }

    @Test
    void testLnPositive() {
       IntegerValue val = new IntegerValue(5);
       NumericValue ln = val.ln();
       assertInstanceOf(RealValue.class, ln);
       assertEquals(Math.log(5.0), ((RealValue) ln).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testLnZeroOrNegative() {
       assertEquals("NaN", new IntegerValue(0).ln().toString());
       assertEquals("NaN", new IntegerValue(-3).ln().toString());
    }

    @Test
    void testExp() {
       IntegerValue val = new IntegerValue(3);
       NumericValue exp = val.exp();
       assertInstanceOf(RealValue.class, exp);
       assertEquals(Math.exp(3.0), ((RealValue) exp).getValue().doubleValue(), 1e-9);
    }

 }
