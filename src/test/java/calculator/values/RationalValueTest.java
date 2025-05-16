package calculator.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RationalValue Comprehensive Tests")
class RationalValueTest {

   @Nested @DisplayName("Construction & Accessors")
   class ConstructionTests {
      @Test void testSimplificationPositive() {
         RationalValue r = new RationalValue(6, 12);
         assertEquals(BigInteger.ONE, r.getNumerator());
         assertEquals(BigInteger.valueOf(2), r.getDenominator());
      }
      @Test void testSignNormalization() {
         RationalValue a = new RationalValue(-2, 4);
         assertEquals(BigInteger.valueOf(-1), a.getNumerator());
         assertEquals(BigInteger.valueOf(2), a.getDenominator());
         RationalValue b = new RationalValue(2, -4);
         assertEquals(BigInteger.valueOf(-1), b.getNumerator());
         assertEquals(BigInteger.valueOf(2), b.getDenominator());
         RationalValue c = new RationalValue(-2, -4);
         assertEquals(BigInteger.ONE, c.getNumerator());
         assertEquals(BigInteger.valueOf(2), c.getDenominator());
      }
      @Test void testZeroDenominatorIsNaN() {
         RationalValue r = new RationalValue(5, 0);
         assertTrue(r.isNaN());
         assertEquals("NaN", r.toString());
      }
   }

   @Nested @DisplayName("toString / equals / hashCode")
   class IdentityTests {
      @Test void testToStringFraction() {
         assertEquals("3/5", new RationalValue(3,5).toString());
      }
      @Test void testToStringInteger() {
         assertEquals("4", new RationalValue(4,1).toString());
      }
      @Test void testScientificNotationLarge() {
         RationalValue r = new RationalValue(BigInteger.valueOf(1_000_000), BigInteger.ONE);
         String s = r.toString();
         assertTrue(s.contains("E") || s.contains("e") || s.matches(".*10.*"));
      }
      @Test void testScientificNotationSmall() {
         RationalValue r = new RationalValue(BigInteger.ONE, BigInteger.valueOf(1_000_000));
         String s = r.toString();
         assertTrue(s.contains("E") || s.contains("e") || s.matches(".*10.*"));
      }
      @Test void testEqualsAndHashCode() {
         RationalValue a = new RationalValue(2,4);
         RationalValue b = new RationalValue(1,2);
         assertEquals(a, b);
         assertEquals(a.hashCode(), b.hashCode());
         assertNotEquals(a, null);
         assertNotEquals(a, "foo");
      }
   }

   @Nested @DisplayName("Basic Rational Arithmetic")
   class RationalArithmeticTests {
      @Test void testAddRational() {
         RationalValue r1 = new RationalValue(1,3);
         RationalValue r2 = new RationalValue(1,6);
         RationalValue sum = (RationalValue) r1.add(r2);
         assertEquals(BigInteger.ONE,   sum.getNumerator());
         assertEquals(BigInteger.valueOf(2), sum.getDenominator());
      }
      @Test void testSubtractRational() {
         RationalValue r1 = new RationalValue(3,4);
         RationalValue r2 = new RationalValue(1,4);
         RationalValue diff = (RationalValue) r1.subtract(r2);
         assertEquals(BigInteger.ONE,   diff.getNumerator());
         assertEquals(BigInteger.valueOf(2), diff.getDenominator());
      }
      @Test void testMultiplyRational() {
         RationalValue r1 = new RationalValue(2,3);
         RationalValue r2 = new RationalValue(3,4);
         RationalValue prod = (RationalValue) r1.multiply(r2);
         assertEquals(BigInteger.ONE,   prod.getNumerator());
         assertEquals(BigInteger.valueOf(2), prod.getDenominator());
      }
      @Test void testDivideRational() {
         RationalValue r1 = new RationalValue(1,2);
         RationalValue r2 = new RationalValue(3,4);
         RationalValue quo = (RationalValue) r1.divide(r2);
         assertEquals(BigInteger.valueOf(2), quo.getNumerator());
         assertEquals(BigInteger.valueOf(3), quo.getDenominator());
      }
      @Test void testDivideByZeroRational() {
         RationalValue r = new RationalValue(1,2);
         RationalValue zero = new RationalValue(0,5);
         assertEquals("NaN", r.divide(zero).toString());
      }
   }

   @Nested @DisplayName("Mixed-Type Promotion")
   class PromotionTests {
      @Test void testAddWithInteger() {
         RationalValue r = new RationalValue(3,4);
         IntegerValue i = new IntegerValue(1);
         RationalValue sum = (RationalValue) r.add(i);
         assertEquals("7/4", sum.toString());
      }
      @Test void testMultiplyWithInteger() {
         RationalValue r = new RationalValue(3,4);
         IntegerValue i = new IntegerValue(2);
         RationalValue prod = (RationalValue) r.multiply(i);
         assertEquals("3/2", prod.toString());
      }
      @Test void testDivideWithInteger() {
         RationalValue r = new RationalValue(3,4);
         IntegerValue i = new IntegerValue(2);
         RationalValue quo = (RationalValue) r.divide(i);
         assertEquals("3/8", quo.toString());
      }
      @Test void testAddWithReal() {
         RationalValue r = new RationalValue(1,2);
         RealValue real = new RealValue(0.25, 2);
         NumericValue sum = r.add(real);
         assertInstanceOf(RealValue.class, sum);
         assertEquals("0.75", sum.toString());
      }
      @Test void testMultiplyWithReal() {
         RationalValue r = new RationalValue(1,2);
         RealValue real = new RealValue(4.0,1);
         NumericValue prod = r.multiply(real);
         assertInstanceOf(RealValue.class, prod);
         assertEquals("2", prod.toString());
      }
      @Test void testAddWithComplex() {
         RationalValue r = new RationalValue(1,2);
         ComplexValue c = new ComplexValue(BigDecimal.ONE, BigDecimal.ONE);
         NumericValue sum = r.add(c);
         assertInstanceOf(ComplexValue.class, sum);
         assertEquals("1.5 + 1i", sum.toString());
      }
   }

   @Nested @DisplayName("Advanced Functions")
   class AdvancedTests {
      @Test void testPowInteger() {
         RationalValue r = new RationalValue(2,3);
         RationalValue p = (RationalValue) r.pow(new IntegerValue(3));
         assertEquals("8/27", p.toString());
      }
      @Test void testPowReal() {
         RationalValue r = new RationalValue(1,4);
         NumericValue p = r.pow(new RealValue(0.5, 5));
         assertInstanceOf(RealValue.class, p);
         assertEquals(0.5, ((RealValue) p).getValue().doubleValue(), 1e-9);
      }
      @Test void testRootInteger() {
         RationalValue r = new RationalValue(27,8);
         NumericValue rt = r.root(new IntegerValue(3));
         assertInstanceOf(RealValue.class, rt);
         assertEquals(1.5, ((RealValue) rt).getValue().doubleValue(), 1e-9);
      }
      @Test void testLogRealBase() {
         RationalValue r = new RationalValue(100,1);
         RealValue base = new RealValue(10.0, 5);
         NumericValue lg = r.log(base);
         assertInstanceOf(RealValue.class, lg);
         assertEquals(2.0, ((RealValue) lg).getValue().doubleValue(), 1e-9);
      }
      @Test void testInverseNonZero() {
         RationalValue r = new RationalValue(3,4);
         RationalValue inv = (RationalValue) r.inverse();
         assertEquals("4/3", inv.toString());
      }
      @Test void testInverseZero() {
         RationalValue r = new RationalValue(0,5);
         assertEquals("NaN", r.inverse().toString());
      }
      @Test void testLnPositive() {
         RationalValue r = new RationalValue(5,2);
         NumericValue ln = r.ln();
         assertInstanceOf(RealValue.class, ln);
         // ln(2.5) ≈ 0.91629
         assertEquals(Math.log(2.5), ((RealValue) ln).getValue().doubleValue(), 1e-9);
      }
      @Test void testLnNonPositive() {
         assertEquals("NaN", new RationalValue(0,1).ln().toString());
         assertEquals("NaN", new RationalValue(-1,2).ln().toString());
      }
      @Test void testExp() {
         RationalValue r = new RationalValue(1,1);
         NumericValue ex = r.exp();
         assertInstanceOf(RealValue.class, ex);
         assertEquals(Math.E, ((RealValue) ex).getValue().doubleValue(), 1e-9);
      }
   }
}
