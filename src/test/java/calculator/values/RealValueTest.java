package calculator.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RealValue Tests")
class RealValueTest {

   @Nested
   @DisplayName("Construction & Accessor Tests")
   class ConstructionTests {
      @Test @DisplayName("Constructor(double, scale) rounds correctly")
      void testConstructorDouble() {
         RealValue r = new RealValue(3.14159, 2);
         assertEquals(0,
                 r.getValue().compareTo(new BigDecimal("3.14")),
                 "Expected 3.14159 → scale 2 → 3.14"
         );
      }

      @Test @DisplayName("Constructor(BigDecimal, scale) respects scale")
      void testConstructorBigDecimal() {
         RealValue r = new RealValue(new BigDecimal("3.14159"), 3);
         assertEquals(0,
                 r.getValue().compareTo(new BigDecimal("3.142")),
                 "Expected 3.14159 → scale 3 → 3.142"
         );
      }

      @Test @DisplayName("getPrecision() returns scale")
      void testGetPrecision() {
         RealValue r = new RealValue(1.2345, 4);
         assertEquals(4, r.getPrecision());
      }

      @Test @DisplayName("getValueInt() truncates correctly")
      void testGetValueInt() {
         RealValue r = new RealValue(4.99, 3);
         assertEquals(4, r.getValueInt());
      }

      @Test @DisplayName("NaN singleton behaves as expected")
      void testNaN() {
         RealValue nan = RealValue.NaN;
         assertTrue(nan.isNaN());
         assertEquals("NaN", nan.toString());
      }
   }

   @Nested
   @DisplayName("Basic Arithmetic (Real ∘ Real)")
   class ArithmeticTests {
      @ParameterizedTest(name = "{0} + {1} = {2}")
      @CsvSource({
              "1.5, 2.5, 4.0",
              "5.0, -1.0, 4.0"
      })
      void testAddReal(double a, double b, double expected) {
         RealValue r1 = new RealValue(a, 3);
         RealValue r2 = new RealValue(b, 3);
         RealValue res = (RealValue) r1.add(r2);
         assertEquals(expected, res.getValue().doubleValue(), 1e-9);
      }

      @ParameterizedTest(name = "{0} - {1} = {2}")
      @CsvSource({
              "5.0, 2.5, 2.5",
              "2.0, 3.0, -1.0"
      })
      void testSubtractReal(double a, double b, double expected) {
         RealValue r1 = new RealValue(a, 3);
         RealValue r2 = new RealValue(b, 3);
         RealValue res = (RealValue) r1.subtract(r2);
         assertEquals(expected, res.getValue().doubleValue(), 1e-9);
      }

      @ParameterizedTest(name = "{0} * {1} = {2}")
      @CsvSource({
              "2.0, 3.0, 6.0",
              "-1.5, 2.0, -3.0"
      })
      void testMultiplyReal(double a, double b, double expected) {
         RealValue r1 = new RealValue(a, 3);
         RealValue r2 = new RealValue(b, 3);
         RealValue res = (RealValue) r1.multiply(r2);
         assertEquals(expected, res.getValue().doubleValue(), 1e-9);
      }

      @ParameterizedTest(name = "{0} / {1} = {2}")
      @CsvSource({
              "6.0, 3.0, 2.0",
              "7.5, 2.5, 3.0"
      })
      void testDivideReal(double a, double b, double expected) {
         RealValue r1 = new RealValue(a, 3);
         RealValue r2 = new RealValue(b, 3);
         RealValue res = (RealValue) r1.divide(r2);
         assertEquals(expected, res.getValue().doubleValue(), 1e-9);
      }

      @Test @DisplayName("Division by zero yields NaN")
      void testDivisionByZero() {
         RealValue r = new RealValue(5.0, 3);
         RealValue zero = new RealValue(0.0, 3);
         assertEquals("NaN", r.divide(zero).toString());
      }
   }

   @Nested
   @DisplayName("Mixed-Type Arithmetic")
   class MixedTypeTests {

      @Test @DisplayName("Real + Integer → Real")
      void testAddInteger() {
         RealValue r = new RealValue(2.0, 3);
         IntegerValue i = new IntegerValue(3);
         RealValue res = (RealValue) r.add(i);
         assertEquals(5.0, res.getValue().doubleValue(), 1e-9);
      }

      @Test @DisplayName("Real + Rational → Real")
      void testAddRational() {
         RealValue r = new RealValue(1.5, 3);
         RationalValue q = new RationalValue(1, 2);  // 0.5
         RealValue res = (RealValue) r.add(q);
         assertEquals(2.0, res.getValue().doubleValue(), 1e-9);
      }

      @Test @DisplayName("Real + Complex → Complex")
      void testAddComplex() {
         RealValue r = new RealValue(2.0, 3);
         ComplexValue c = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("1.0"));
         NumericValue res = r.add(c);
         assertInstanceOf(ComplexValue.class, res);
      }

      @Test @DisplayName("Division by integer zero yields NaN")
      void testDivideIntegerZero() {
         RealValue r = new RealValue(5.0, 3);
         IntegerValue zero = new IntegerValue(0);
         assertEquals("NaN", r.divide(zero).toString());
      }
   }

   @Nested
   @DisplayName("Advanced Functions")
   class AdvancedTests {
      @Test @DisplayName("pow with integer exponent")
      void testPowInteger() {
         RealValue r = new RealValue(2.0, 3);
         RealValue res = (RealValue) r.pow(new IntegerValue(3));
         assertEquals(8.0, res.getValue().doubleValue(), 1e-9);
      }

      @Test @DisplayName("root with real degree")
      void testRootReal() {
         RealValue r = new RealValue(16.0, 3);
         RealValue res = (RealValue) r.root(new RealValue(4.0, 5));
         // 16^(1/4) = 2.0
         assertEquals(2.0, res.getValue().doubleValue(), 1e-9);
      }

      @Test
      @DisplayName("ln and exp round-trip (within rounding error)")
      void testLnExpRoundTrip() {
         RealValue r  = new RealValue(5.0, 5);
         RealValue ln = (RealValue) r.ln();
         RealValue exp = (RealValue) ln.exp();
         // Because we round ln to 5 decimals before re-exponentiating,
         // exp(ln(5)) will be ≈5 but ends up 5.00001 — allow a small tolerance.
         assertEquals(
                 5.0,
                 exp.getValue().doubleValue(),
                 1e-4,
                 "exp(ln(5)) should be within 0.0001 of 5.0"
         );
      }


      @Test @DisplayName("inverse of non-zero")
      void testInverse() {
         RealValue r = new RealValue(4.0, 3);
         RealValue inv = (RealValue) r.inverse();
         assertEquals(0.25, inv.getValue().doubleValue(), 1e-9);
      }
   }

   @Nested
   @DisplayName("Edge Cases & Immutability")
   class EdgeCaseTests {
      @Test @DisplayName("Very large numbers use scientific notation internally")
      void testVeryLargeNumber() {
         RealValue r = new RealValue(1e+100, 5);
         String s = r.toString();
         assertTrue(s.length() > 5 && s.startsWith("1"), "Expect large numbers to still start with '1'");
      }

      @Test @DisplayName("Chained operations preserve immutability")
      void testImmutability() {
         RealValue r1 = new RealValue(2.0, 3);
         RealValue r2 = new RealValue(1.0, 3);
         RealValue r3 = (RealValue) r1.add(r2);
         assertEquals("2", r1.toString());
         assertEquals("1", r2.toString());
         assertEquals("3", r3.toString());
      }
   }
}
