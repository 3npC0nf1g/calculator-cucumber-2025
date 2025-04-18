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











    @Test void testConstructorDouble() {
       RealValue r = new RealValue(3.14159, 3);
       assertEquals(0, r.getValue().compareTo(new BigDecimal("3.14")), "Rounded double constructor");
    }

    @Test void testConstructorBigDecimal() {
       RealValue r = new RealValue(new BigDecimal("3.14159"), 2);
       assertEquals(0, r.getValue().compareTo(new BigDecimal("3.1")), "Rounded BigDecimal constructor");
    }

    @Test void testGetPrecision() {
       RealValue r = new RealValue(1.2345, 4);
       assertEquals(4, r.getPrecision());
    }

    @Test void testGetValueInt() {
       RealValue r = new RealValue(4.99, 3);
       assertEquals(4, r.getValueInt());
    }


    // === Arithmetic with RealValue ===

    @Test void testAddRealValue() {
       RealValue r1 = new RealValue(1.5, 3);
       RealValue r2 = new RealValue(2.5, 3);
       RealValue result = (RealValue) r1.add(r2);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("4.0")));
    }

    @Test void testSubtractRealValue() {
       RealValue r1 = new RealValue(5.0, 3);
       RealValue r2 = new RealValue(3.0, 3);
       RealValue result = (RealValue) r1.subtract(r2);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("2.0")));
    }

    @Test void testMultiplyRealValue() {
       RealValue r1 = new RealValue(3.0, 3);
       RealValue r2 = new RealValue(4.0, 3);
       RealValue result = (RealValue) r1.multiply(r2);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("12.0")));
    }

    @Test void testDivideRealValue() {
       RealValue r1 = new RealValue(10.0, 3);
       RealValue r2 = new RealValue(2.0, 3);
       RealValue result = (RealValue) r1.divide(r2);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("5.0")));
    }

    // === Arithmetic with IntegerValue ===

    @Test void testAddIntegerValue() {
       RealValue r = new RealValue(2.0, 3);
       IntegerValue i = new IntegerValue(3);
       RealValue result = (RealValue) r.add(i);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("5.0")));
    }

    @Test void testSubtractIntegerValue() {
       RealValue r = new RealValue(5.0, 3);
       IntegerValue i = new IntegerValue(2);
       RealValue result = (RealValue) r.subtract(i);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("3.0")));
    }

    // === Arithmetic with RationalValue ===

    @Test void testAddRationalValue() {
       RealValue r = new RealValue(1.5, 3);
       RationalValue rational = new RationalValue(1, 2);
       RealValue result = (RealValue) r.add(rational);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("2.0")));
    }

    @Test void testDivideRationalValue() {
       RealValue r = new RealValue(3.0, 3);
       RationalValue rational = new RationalValue(1, 2);
       RealValue result = (RealValue) r.divide(rational);
       assertEquals(0, result.getValue().compareTo(new BigDecimal("6.0")));
    }

    // === Arithmetic with ComplexValue ===

    @Test void testAddComplexReturnsComplex() {
       RealValue r = new RealValue(2.0, 3);
       ComplexValue c = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("1.0"));
       NumericValue result = r.add(c);
       assertTrue(result instanceof ComplexValue);
    }

    // === Division by zero ===

    @Test void testDivisionByZeroRealValue() {
       RealValue r = new RealValue(5.0, 3);
       RealValue zero = new RealValue(0.0, 3);
       assertThrows(ArithmeticException.class, () -> r.divide(zero));
    }

    @Test void testDivisionByZeroRationalValue() {
       RealValue r = new RealValue(5.0, 3);
       RationalValue zero = new RationalValue(0, 1);
       assertThrows(ArithmeticException.class, () -> r.divide(zero));
    }

    @Test void testDivisionByZeroIntegerValue() {
       RealValue r = new RealValue(5.0, 3);
       IntegerValue zero = new IntegerValue(0);
       assertThrows(ArithmeticException.class, () -> r.divide(zero));
    }

    // === Unsupported type ===

    @Test void testAddUnsupportedNumericTypeThrows() {
       RealValue r = new RealValue(1.0, 3);
       NumericValue unknown = new NumericValue() {
          @Override public NumericValue add(NumericValue other) { return null; }
          @Override public NumericValue subtract(NumericValue other) { return null; }
          @Override public NumericValue multiply(NumericValue other) { return null; }
          @Override public NumericValue divide(NumericValue other) { return null; }
          @Override public int getValueInt() { return 0; }
       };
       assertThrows(UnsupportedOperationException.class, () -> r.add(unknown));
    }

    // === Edge cases ===

    @Test void testNegativeRealValue() {
       RealValue r = new RealValue(-2.5, 3);
       assertEquals("-2.5", r.toString());
    }

    @Test void testVeryLargeNumber() {
       RealValue r = new RealValue(1e+100, 10);
       assertTrue(r.toString().startsWith("1"));
    }

    @Test void testVerySmallNumber() {
       RealValue r = new RealValue(1e-100, 10);
       assertTrue(r.toString().startsWith("0.000"));
    }

    @Test void testPrecisionLossAfterChainedOperations() {
       RealValue r = new RealValue(1.1111, 3);
       RealValue result = (RealValue) r.add(new RealValue(2.2222, 3)).subtract(new RealValue(1.1111, 3));
       assertEquals(0, result.getValue().compareTo(new BigDecimal("2.22").round(new MathContext(3))));
    }

    @Test void testImmutability() {
       RealValue r1 = new RealValue(2.0, 3);
       RealValue r2 = new RealValue(1.0, 3);
       RealValue r3 = (RealValue) r1.add(r2);
       assertEquals("2", r1.toString());
       assertEquals("1", r2.toString());
       assertEquals("3", r3.toString());
    }




}
