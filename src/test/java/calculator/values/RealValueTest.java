package calculator.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

@DisplayName("RealValue Extended Tests")
class RealValueTest {

   @Nested
   @DisplayName("Equality and Hashing")
   class EqualityHashTests {
      @Test
      void testEqualsAndHashCodeRealEquals() {
         RealValue r1 = new RealValue(2.50, 2);
         RealValue r2 = new RealValue(new BigDecimal("2.5"), 3);
         // r2 rounds to 2.500 => equals scaled r1
         assertEquals(r1, r2);
         assertEquals(r1.subtract(r2).toString(), "0");
      }

      @Test
      void testEqualsNaN() {
         RealValue nan1 = RealValue.NaN;
         RealValue nan2 = RealValue.NaN;
         assertTrue(nan1.equals(nan2));
         assertEquals(0, nan1.hashCode());
      }

      @Test
      void testNotEqualsDifferent() {
         RealValue r = new RealValue(1.23, 2);
         assertNotEquals(r, null);
         assertNotEquals(r, "string");
         RealValue other = new RealValue(1.24, 2);
         assertNotEquals(r, other);
      }
   }

   @Nested
   @DisplayName("Arithmetic with Mixed Types")
   class MixedTypeCoverage {
      RealValue base = new RealValue(3.0, 2);
      IntegerValue iv = new IntegerValue(2);
      RationalValue rq = new RationalValue(5, 2); // 2.5
      ComplexValue cv = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("-1.0"));

      @Test
      void testAddInteger() {
         NumericValue res = base.add(iv);
         assertTrue(res instanceof RealValue);
         assertEquals("5.00", ((RealValue)res).getValue().toPlainString());
      }

      @Test
      void testAddRational() {
         NumericValue res = base.add(rq);
         assertTrue(res instanceof RealValue);
         assertEquals(5.5, ((RealValue)res).getValue().doubleValue(), 1e-9);
      }

      @Test
      void testAddComplex() {
         NumericValue res = base.add(cv);
         assertTrue(res instanceof ComplexValue);
      }

      @Test
      void testSubtractInteger() {
         NumericValue res = base.subtract(iv);
         assertEquals("1.00", ((RealValue)res).getValue().toPlainString());
      }

      @Test
      void testSubtractRational() {
         NumericValue res = base.subtract(rq);
         assertEquals(0.5, ((RealValue)res).getValue().doubleValue(), 1e-9);
      }

      @Test
      void testSubtractComplex() {
         NumericValue res = base.subtract(cv);
         assertTrue(res instanceof ComplexValue);
      }

      @Test
      void testMultiplyInteger() {
         NumericValue res = base.multiply(iv);
         assertEquals("6.00", ((RealValue)res).getValue().toPlainString());
      }

      @Test
      void testMultiplyRational() {
         NumericValue res = base.multiply(rq);
         assertEquals(7.5, ((RealValue)res).getValue().doubleValue(), 1e-9);
      }

      @Test
      void testMultiplyComplex() {
         NumericValue res = base.multiply(cv);
         assertTrue(res instanceof ComplexValue);
      }

      @Test
      void testDivideInteger() {
         NumericValue res = base.divide(iv);
         assertEquals("1.50", ((RealValue)res).getValue().toPlainString());
      }

      @Test
      void testDivideRational() {
         NumericValue res = base.divide(rq);
         assertEquals(1.2, ((RealValue)res).getValue().doubleValue(), 1e-9);
      }

      @Test
      void testDivideComplex() {
         NumericValue res = base.divide(cv);
         assertTrue(res instanceof ComplexValue);
      }

      @Test
      void testDivideByZeroInteger() {
         IntegerValue zero = new IntegerValue(0);
         assertEquals("NaN", base.divide(zero).toString());
      }

      @Test
      void testDivideByZeroReal() {
         RealValue zero = new RealValue(0.0, 2);
         assertEquals("NaN", base.divide(zero).toString());
      }
   }

   @Nested
   @DisplayName("Power, Root, Log Edge Cases")
   class AdvancedCoverage {
      RealValue base = new RealValue(16.0, 3);

      @Test
      void testPowRealExponent() {
         RealValue exp = new RealValue(0.5, 3);
         RealValue result = (RealValue) base.pow(exp);
         assertEquals(4.0, result.getValue().doubleValue(), 1e-9);
      }

      @Test
      void testPowComplexExponent() {
         ComplexValue exp = new ComplexValue(BigDecimal.ONE, BigDecimal.ZERO);
         NumericValue result = base.pow(exp);
         assertTrue(result instanceof ComplexValue);
      }

      @Test
      void testRootRationalDegreeUnsupported() {
         RationalValue deg = new RationalValue(3, 2);
         assertThrows(UnsupportedOperationException.class, () -> base.root(deg));
      }

      @Test
      void testLogIntegerBase() {
         RealValue x = new RealValue(8.0, 3);
         IntegerValue two = new IntegerValue(2);
         NumericValue res = x.log(two);
         assertEquals("3.000", ((RealValue)res).getValue().toPlainString());
      }

      @Test
      void testLogRealBase() {
         RealValue x = new RealValue(27.0, 3);
         RealValue three = new RealValue(3.0, 3);
         NumericValue res = x.log(three);
         assertEquals(3.0, ((RealValue)res).getValue().doubleValue(), 1e-9);
      }

      @Test
      void testInverseNegative() {
         RealValue neg = new RealValue(-4.0, 2);
         NumericValue inv = neg.inverse();
         assertEquals("-0.25", inv.toString());
      }

      @Test
      void testLnNonPositive() {
         RealValue zero = new RealValue(0.0, 2);
         assertEquals("NaN", zero.ln().toString());
         RealValue neg = new RealValue(-1.0, 2);
         assertEquals("NaN", neg.ln().toString());
      }

      @Test
      void testExpLargeValue() {
         RealValue r = new RealValue(10.0, 2);
         NumericValue e = r.exp();
         assertTrue(((RealValue)e).getValue().doubleValue() > Math.exp(9));
      }
   }
}