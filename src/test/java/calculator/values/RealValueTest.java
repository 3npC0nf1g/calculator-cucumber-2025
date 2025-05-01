package calculator.values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@DisplayName("RealValue Tests")
class RealValueTest {
   private static void log(String message) {
      String timestamp = LocalDateTime.now(ZoneOffset.UTC)
              .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      System.out.println(timestamp + " - " + message);
   }

   @BeforeEach
   void setUp() {
      log("Starting test execution");
   }

   @Nested
   @DisplayName("Constructor and Basic Methods Tests")
   class BasicTests {
      @Test
      void testGetValue() {
         RealValue r = new RealValue(3.14, 3);
         MathContext mc = new MathContext(3);
         BigDecimal expected = new BigDecimal("3.14", mc);
         assertEquals(0, r.getValue().compareTo(expected),
                 "getValue() should return the expected BigDecimal");
      }

      @Test
      void testConstructorDouble() {
         RealValue r = new RealValue(3.14159, 2);
         assertEquals(0, r.getValue().compareTo(new BigDecimal("3.14")),
                 "Rounded double constructor");
      }

      @Test
      void testConstructorBigDecimal() {
         RealValue r = new RealValue(new BigDecimal("3.14159"), 1);
         assertEquals(0, r.getValue().compareTo(new BigDecimal("3.1")),
                 "Rounded BigDecimal constructor");
      }

      @Test
      void testGetPrecision() {
         RealValue r = new RealValue(1.2345, 4);
         assertEquals(4, r.getPrecision());
      }

      @Test
      void testGetValueInt() {
         RealValue r = new RealValue(4.99, 3);
         assertEquals(4, r.getValueInt());
      }

      @Test
      void testToString() {
         RealValue r = new RealValue(3.14159, 6);
         assertEquals("3.14159", r.toString(),
                 "toString() should return the plain string representation");
      }

      @Test
      void testEqualsAndHashCode() {
         RealValue r1 = new RealValue(2.718, 4);
         RealValue r2 = new RealValue(new BigDecimal("2.718"), 4);
         assertEquals(r1, r2, "RealValue objects with the same value should be equal");
         assertEquals(r1.hashCode(), r2.hashCode(),
                 "Equal RealValue objects should have the same hashCode");
      }
   }

   @Nested
   @DisplayName("RealValue Arithmetic Tests")
   class RealValueArithmeticTests {
      @Test
      void testAddition() {
         RealValue r1 = new RealValue(2.5, 3);
         RealValue r2 = new RealValue(1.5, 3);
         RealValue result = (RealValue) r1.add(r2);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("4.0")));
      }

      @Test
      void testSubtraction() {
         RealValue r1 = new RealValue(5.0, 3);
         RealValue r2 = new RealValue(2.5, 3);
         RealValue result = (RealValue) r1.subtract(r2);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("2.5")));
      }

      @Test
      void testMultiplication() {
         RealValue r1 = new RealValue(2.0, 3);
         RealValue r2 = new RealValue(3.0, 3);
         RealValue result = (RealValue) r1.multiply(r2);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("6.0")));
      }

      @Test
      void testDivision() {
         RealValue r1 = new RealValue(6.0, 3);
         RealValue r2 = new RealValue(3.0, 3);
         RealValue result = (RealValue) r1.divide(r2);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("2.0")));
      }
   }

   @Nested
   @DisplayName("IntegerValue Operation Tests")
   class IntegerValueOperationTests {
      @Test
      void testAddIntegerValue() {
         RealValue r = new RealValue(2.0, 3);
         IntegerValue i = new IntegerValue(3);
         RealValue result = (RealValue) r.add(i);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("5.0")));
      }

      @Test
      void testSubtractIntegerValue() {
         RealValue r = new RealValue(5.0, 3);
         IntegerValue i = new IntegerValue(2);
         RealValue result = (RealValue) r.subtract(i);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("3.0")));
      }

      @Test
      void testDivideIntegerValue() {
         RealValue r = new RealValue(10.0, 3);
         IntegerValue i = new IntegerValue(2);
         RealValue result = (RealValue) r.divide(i);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("5.0")));
      }
   }

   @Nested
   @DisplayName("RationalValue Operation Tests")
   class RationalValueOperationTests {
      @Test
      void testAddRationalValue() {
         RealValue r = new RealValue(1.5, 3);
         RationalValue rational = new RationalValue(1, 2);
         RealValue result = (RealValue) r.add(rational);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("2.0")));
      }

      @Test
      void testSubtractRationalValue() {
         RealValue r = new RealValue(3.0, 3);
         RationalValue rational = new RationalValue(1, 2);
         RealValue result = (RealValue) r.subtract(rational);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("2.5")));
      }

      @Test
      void testMultiplyRationalValue() {
         RealValue r = new RealValue(4.0, 3);
         RationalValue rational = new RationalValue(1, 2);
         RealValue result = (RealValue) r.multiply(rational);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("2.0")));
      }

      @Test
      void testDivideRationalValue() {
         RealValue r = new RealValue(3.0, 3);
         RationalValue rational = new RationalValue(1, 2);
         RealValue result = (RealValue) r.divide(rational);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("6.0")));
      }
   }

   @Nested
   @DisplayName("ComplexValue Operation Tests")
   class ComplexValueOperationTests {
      @Test
      void testAddComplexReturnsComplex() {
         RealValue r = new RealValue(2.0, 3);
         ComplexValue c = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("1.0"));
         NumericValue result = r.add(c);
         assertInstanceOf(ComplexValue.class, result);
      }

      @Test
      void testSubtractComplexReturnsComplex() {
         RealValue r = new RealValue(2.0, 3);
         ComplexValue c = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("1.0"));
         NumericValue result = r.subtract(c);
         assertInstanceOf(ComplexValue.class, result);
      }

      @Test
      void testMultiplyComplexReturnsComplex() {
         RealValue r = new RealValue(3.0, 3);
         ComplexValue c = new ComplexValue(new BigDecimal("0.0"), new BigDecimal("1.0"));
         NumericValue result = r.multiply(c);
         assertInstanceOf(ComplexValue.class, result);
      }

      @Test
      void testDivideComplexReturnsComplex() {
         RealValue r = new RealValue(6.0, 3);
         ComplexValue c = new ComplexValue(new BigDecimal("2.0"), new BigDecimal("2.0"));
         NumericValue result = r.divide(c);
         assertInstanceOf(ComplexValue.class, result);
      }
   }

   @Nested
   @DisplayName("Advanced Mathematical Operations Tests")
   class AdvancedOperationsTests {
      @Test
      void testPowRealValue() {
         RealValue base = new RealValue(2.0, 3);
         RealValue exponent = new RealValue(3.0, 3);
         RealValue result = (RealValue) base.pow(exponent);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("8.0")));
      }

      @Test
      void testPowIntegerValue() {
         RealValue base = new RealValue(2.0, 3);
         IntegerValue exponent = new IntegerValue(2);
         RealValue result = (RealValue) base.pow(exponent);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("4.0")));
      }

      @Test
      void testRootRealValue() {
         RealValue value = new RealValue(16.0, 3);
         RealValue degree = new RealValue(2.0, 3);
         RealValue result = (RealValue) value.root(degree);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("4.0")));
      }

      @Test
      void testRootNegativeValue() {
         RealValue value = new RealValue(-16.0, 3);
         RealValue degree = new RealValue(2.0, 3);
         NumericValue result = value.root(degree);
         assertInstanceOf(ComplexValue.class, result);
      }

      @Test
      void testLogRealValue() {
         RealValue value = new RealValue(8.0, 3);
         RealValue base = new RealValue(2.0, 3);
         RealValue result = (RealValue) value.log(base);
         assertEquals(0, result.getValue().compareTo(new BigDecimal("3.0")));
      }

      @Test
      void testLnValue() {
         RealValue value = new RealValue(Math.E, 3);
         RealValue result = (RealValue) value.ln();
         assertEquals(0, result.getValue().compareTo(new BigDecimal("1.0")));
      }

      @Test
      void testExpValue() {
         RealValue value = new RealValue(1.0, 3);
         RealValue result = (RealValue) value.exp();
         assertEquals(0, result.getValue().compareTo(
                 new BigDecimal(Math.E).round(new MathContext(3))));
      }
   }

   @Nested
   @DisplayName("Error Cases Tests")
   class ErrorCasesTests {
      @Test
      void testDivisionByZero() {
         RealValue r1 = new RealValue(5.0, 3);
         RealValue rZero = new RealValue(0.0, 3);
         assertEquals("NaN", r1.divide(rZero).toString());
      }

      @Test
      void testDivisionByZeroRationalValue() {
         RealValue r = new RealValue(5.0, 3);
         RationalValue zero = new RationalValue(0, 1);
         assertEquals("NaN", r.divide(zero).toString());
      }

      @Test
      void testDivisionByZeroIntegerValue() {
         RealValue r = new RealValue(5.0, 3);
         IntegerValue zero = new IntegerValue(0);
         assertEquals("NaN", r.divide(zero).toString());
      }

      @Test
      void testRootZeroDegree() {
         RealValue value = new RealValue(16.0, 3);
         RealValue degree = new RealValue(0.0, 3);
         assertThrows(ArithmeticException.class, () -> value.root(degree));
      }

      @Test
      void testLogNegativeValue() {
         RealValue value = new RealValue(-1.0, 3);
         RealValue base = new RealValue(2.0, 3);
         NumericValue result = value.log(base);
         assertInstanceOf(ComplexValue.class, result);
      }
   }

   @Nested
   @DisplayName("Edge Cases Tests")
   class EdgeCasesTests {
      @Test
      void testNegativeRealValue() {
         RealValue r = new RealValue(-2.5, 3);
         assertEquals("-2.5", r.toString());
      }

      @Test
      void testVeryLargeNumber() {
         RealValue r = new RealValue(1e+100, 10);
         assertTrue(r.toString().startsWith("1"));
      }

      @Test
      void testVerySmallNumber() {
         RealValue r = new RealValue(1e-1, 9);
         assertTrue(r.toString().startsWith("0.1"));
      }

      @Test
      void testPrecisionLossAfterChainedOperations() {
         RealValue r = new RealValue(1.1111, 2);
         RealValue result = (RealValue) r.add(new RealValue(2.2222, 3))
                 .subtract(new RealValue(1.1111, 3));
         assertEquals(0, result.getValue().compareTo(
                 new BigDecimal("2.22").round(new MathContext(3))));
      }

      @Test
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