package calculator.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("IntegerValue Enhanced Tests")
class IntegerValueTest {

   @Nested
   @DisplayName("Basic getters and toString")
   class GetterTests {
      @Test
      void testGetValueAndToString() {
         IntegerValue v = new IntegerValue(42);
         assertEquals(42, v.getValue());
         assertEquals("42", v.toString());
      }
   }

   @Nested
   @DisplayName("Equality & HashCode")
   class EqualityTests {
      @Test
      void testEqualsSameValue() {
         IntegerValue a = new IntegerValue(5);
         IntegerValue b = new IntegerValue(5);
         assertEquals(a, b);
         assertEquals(a.hashCode(), b.hashCode());
      }
      @Test
      void testNotEqualsDifferentValueOrType() {
         IntegerValue a = new IntegerValue(5);
         assertNotEquals(a, new IntegerValue(6));
         assertNotEquals(null, a );
         assertNotEquals("5", a);
      }
   }

   @Nested
   @DisplayName("Addition")
   class AdditionTests {
      @Test
      void testAddInteger() {
         IntegerValue a = new IntegerValue(7);
         IntegerValue b = new IntegerValue(3);
         NumericValue r = a.add(b);
         assertInstanceOf(IntegerValue.class, r);
         assertEquals(10, ((IntegerValue) r).getValue());
      }
      @Test
      void testAddReal() {
         IntegerValue a = new IntegerValue(4);
         RealValue b = new RealValue(1.25, 2);
         NumericValue r = a.add(b);
         assertInstanceOf(RealValue.class, r);
         assertEquals(5.25, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testAddRational() {
         IntegerValue a = new IntegerValue(3);
         RationalValue q = new RationalValue(1, 2);  // 0.5
         NumericValue r = q.add(a);

         assertEquals("7/2", r.toString());
      }
      @Test
      void testAddComplex() {
         IntegerValue a = new IntegerValue(2);
         ComplexValue c = new ComplexValue(new BigDecimal("1"), new BigDecimal("2"));
         NumericValue r = a.add(c);
         assertInstanceOf(ComplexValue.class, r);
         assertEquals(new BigDecimal("3"), ((ComplexValue) r).getReal());
         assertEquals(new BigDecimal("2"), ((ComplexValue) r).getImag());
      }
   }

   @Nested
   @DisplayName("Subtraction")
   class SubtractionTests {
      @Test
      void testSubtractInteger() {
         IntegerValue a = new IntegerValue(8);
         IntegerValue b = new IntegerValue(3);
         NumericValue r = a.subtract(b);
         assertEquals(new IntegerValue(5), r);
      }
      @Test
      void testSubtractReal() {
         IntegerValue a = new IntegerValue(5);
         RealValue b = new RealValue(1.5, 2);
         NumericValue r = a.subtract(b);
         assertInstanceOf(RealValue.class, r);
         assertEquals(3.5, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testSubtractComplex() {
         IntegerValue a = new IntegerValue(3);
         ComplexValue c = new ComplexValue(new BigDecimal("1"), new BigDecimal("1"));
         NumericValue r = a.subtract(c);
         assertInstanceOf(ComplexValue.class, r);
      }
   }

   @Nested
   @DisplayName("Multiplication")
   class MultiplyTests {
      @Test
      void testMultiplyInteger() {
         assertEquals(new IntegerValue(12),
                 new IntegerValue(3).multiply(new IntegerValue(4)));
      }
      @Test
      void testMultiplyReal() {
         IntegerValue a = new IntegerValue(2);
         RealValue b = new RealValue(2.5, 2);
         NumericValue r = a.multiply(b);
         assertInstanceOf(RealValue.class, r);
         assertEquals(5.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testMultiplyComplex() {
         IntegerValue a = new IntegerValue(2);
         ComplexValue c = new ComplexValue(new BigDecimal("3"), new BigDecimal("4"));
         NumericValue r = a.multiply(c);
         assertInstanceOf(ComplexValue.class, r);
         assertEquals(new BigDecimal("6"), ((ComplexValue) r).getReal());
         assertEquals(new BigDecimal("8"), ((ComplexValue) r).getImag());
      }
   }

   @Nested
   @DisplayName("Division & Zero Handling")
   class DivisionTests {
      @Test
      void testDivideIntegerExact() {
         assertEquals(new IntegerValue(5),
                 new IntegerValue(10).divide(new IntegerValue(2)));
      }
      @Test
      void testDivideIntegerNonExact() {
         NumericValue r = new IntegerValue(7).divide(new IntegerValue(2));
         assertInstanceOf(RealValue.class, r);
         assertEquals(3.5, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testDivideReal() {
         NumericValue r = new IntegerValue(10).divide(new RealValue(4.0, 3));
         assertInstanceOf(RealValue.class, r);
         assertEquals(2.5, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testDivideRational() {
         NumericValue r = new IntegerValue(3).divide(new RationalValue(1, 6)); // 3 / (1/6) = 18
         assertInstanceOf(RealValue.class, r);
         assertEquals(18.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testDivideComplex() {
         NumericValue r = new IntegerValue(2).divide(new ComplexValue(new BigDecimal("1"), BigDecimal.ZERO));
         assertInstanceOf(ComplexValue.class, r);
      }
      @Test
      void testDivideByZeroInteger() {
         assertEquals("NaN",
                 new IntegerValue(5).divide(new IntegerValue(0)).toString());
      }
      @Test
      void testDivideByZeroReal() {
         assertEquals("NaN",
                 new IntegerValue(5).divide(new RealValue(0.0, 2)).toString());
      }
      @Test
      void testDivideByZeroRational() {
         assertEquals("NaN",
                 new IntegerValue(5).divide(new RationalValue(0, 1)).toString());
      }
      @Test
      void testDivideByZeroComplex() {
         ComplexValue c0 = new ComplexValue(BigDecimal.ZERO, BigDecimal.ZERO);
         assertEquals("NaN",
                 new IntegerValue(5).divide(c0).toString());
      }
   }

   @Nested
   @DisplayName("Power & Root")
   class PowerRootTests {
      @Test
      void testPowPositiveInteger() {
         NumericValue r = new IntegerValue(2).pow(new IntegerValue(3));
         assertEquals(new IntegerValue(8), r);
      }
      @Test
      void testPowNegativeInteger() {
         NumericValue r = new IntegerValue(2).pow(new IntegerValue(-2));
         assertInstanceOf(RationalValue.class, r);
         assertEquals("1/4", r.toString());
      }
      @Test
      void testPowReal() {
         NumericValue r = new IntegerValue(9).pow(new RealValue(0.5, 5));
         assertInstanceOf(RealValue.class, r);
         assertEquals(3.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testRootInteger() {
         NumericValue r = new IntegerValue(27).root(new IntegerValue(3));
         assertInstanceOf(RealValue.class, r);
         assertEquals(3.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testRootReal() {
         NumericValue r = new IntegerValue(16).root(new RealValue(4.0, 3));
         assertInstanceOf(RealValue.class, r);
         assertEquals(2.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
   }

   @Nested
   @DisplayName("Logarithm & Edge Cases")
   class LogTests {
      @Test
      void testLogIntegerBase() {
         NumericValue r = new IntegerValue(8).log(new IntegerValue(2));
         assertInstanceOf(RealValue.class, r);
         assertEquals(3.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testLogRealBase() {
         NumericValue r = new IntegerValue(9).log(new RealValue(3.0, 3));
         assertInstanceOf(RealValue.class, r);
         assertEquals(2.0, ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testLogComplexBase() {
         ComplexValue c = new ComplexValue(new BigDecimal("2"), BigDecimal.ZERO);
         NumericValue r = new IntegerValue(2).log(c);
         assertInstanceOf(ComplexValue.class, r);
      }
      @Test
      void testLogInvalidInputs() {
         // val <= 0, base <= 0, base == 1
         assertEquals("NaN", new IntegerValue(0).log(new IntegerValue(2)).toString());
         assertEquals("NaN", new IntegerValue(2).log(new IntegerValue(-1)).toString());
         assertEquals("NaN", new IntegerValue(2).log(new IntegerValue(1)).toString());
      }
   }

   @Nested
   @DisplayName("Inverse, ln, exp")
   class MiscTests {
      @Test
      void testInverseNonZero() {
         NumericValue r = new IntegerValue(4).inverse();
         assertInstanceOf(RationalValue.class, r);
         assertEquals("1/4", r.toString());
      }
      @Test
      void testInverseZero() {
         assertEquals("NaN", new IntegerValue(0).inverse().toString());
      }
      @Test
      void testLnPositive() {
         NumericValue r = new IntegerValue(5).ln();
         assertInstanceOf(RealValue.class, r);
         assertEquals(Math.log(5.0), ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
      @Test
      void testLnNonPositive() {
         assertEquals("NaN", new IntegerValue(0).ln().toString());
         assertEquals("NaN", new IntegerValue(-3).ln().toString());
      }
      @Test
      void testExp() {
         NumericValue r = new IntegerValue(3).exp();
         assertInstanceOf(RealValue.class, r);
         assertEquals(Math.exp(3.0), ((RealValue) r).getValue().doubleValue(), 1e-9);
      }
   }
}
