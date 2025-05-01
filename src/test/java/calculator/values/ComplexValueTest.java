package calculator.values;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

 class ComplexValueTest {

    @Test
     void testAddition() {
        ComplexValue c1 = new ComplexValue(2.0, 3.0);
        ComplexValue c2 = new ComplexValue(1.0, -1.0);
        ComplexValue sum = (ComplexValue) c1.add(c2);
        // Expected: (2+1, 3+(-1)) = (3, 2)
        assertEquals(new BigDecimal("3.0"), sum.getReal().setScale(1, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("2.0"), sum.getImag().setScale(1, RoundingMode.HALF_UP));
    }

    @Test
     void testSubtraction() {
        ComplexValue c1 = new ComplexValue(5.0, 4.0);
        ComplexValue c2 = new ComplexValue(2.0, 1.0);
        ComplexValue diff = (ComplexValue) c1.subtract(c2);
        // Expected: (5-2, 4-1) = (3, 3)
        assertEquals(new BigDecimal("3"), diff.getReal().setScale(0, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("3.0"), diff.getImag().setScale(1, RoundingMode.HALF_UP));
    }

    @Test
     void testMultiplication() {
        ComplexValue c1 = new ComplexValue(2.0, 3.0);
        ComplexValue c2 = new ComplexValue(4.0, -1.0);
        ComplexValue product = (ComplexValue) c1.multiply(c2);
        // product = (2*4 - 3*(-1), 2*(-1) + 3*4) = (8 + 3, -2 + 12) = (11, 10)
        assertEquals(new BigDecimal("11.0"), product.getReal().setScale(1, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("10.0"), product.getImag().setScale(1, RoundingMode.HALF_UP));
    }


    @Test
     void testDivision() {
        ComplexValue c1 = new ComplexValue(5.0, 3.0);
        ComplexValue c2 = new ComplexValue(2.0, 1.0);
        ComplexValue quotient = (ComplexValue) c1.divide(c2);
        // Calcul attendu :
        // Denom = 2^2 + 1^2 = 4 + 1 = 5
        // newReal = (5*2 + 3*1) / 5 = (10 + 3)/5 = 13/5 = 2.6
        // newImag = (3*2 - 5*1) / 5 = (6 - 5)/5 = 1/5 = 0.2
        BigDecimal expectedReal = new BigDecimal("2.6").setScale(1, RoundingMode.HALF_UP);
        BigDecimal expectedImag = new BigDecimal("0.2").setScale(1, RoundingMode.HALF_UP);

        assertEquals(expectedReal, quotient.getReal().setScale(1, RoundingMode.HALF_UP));
        assertEquals(expectedImag, quotient.getImag().setScale(1, RoundingMode.HALF_UP));
    }


    @Test
     void testDivisionByZeroComplex() {
        ComplexValue c1 = new ComplexValue(1.0, 1.0);
        ComplexValue zero = new ComplexValue(0.0, 0.0);
        ComplexValue quotient = (ComplexValue) c1.divide(zero);

        assertEquals("NaN", quotient.toString(), "Complex : (1+1i) / (O+0i) should be NaN");
    }

    @Test
     void testEqualsAndHashCode() {
        ComplexValue c1 = new ComplexValue(3.0, 4.0);
        ComplexValue c2 = new ComplexValue(BigDecimal.valueOf(3.0), BigDecimal.valueOf(4.0));
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
     void testToString() {
        ComplexValue c = new ComplexValue(1.23, -4.56);
        assertEquals("1.23 + -4.56i", c.toString());
    }

    @Test
    void testAddWithRealValuePrecision() {
       ComplexValue c = new ComplexValue(2.0, 3.0);
       RealValue r = new RealValue(1.4567, 2); // precision 2 → arrondi à 1.5
       ComplexValue result = (ComplexValue) c.add(r);
       assertEquals(new BigDecimal("3.5"), result.getReal().setScale(1, RoundingMode.HALF_UP));
       assertEquals(new BigDecimal("3.0"), result.getImag().setScale(1, RoundingMode.HALF_UP));
    }

  @Test
    void testSubtractWithRealValuePrecision() {
       ComplexValue c = new ComplexValue(5.0, 2.0);
       RealValue r = new RealValue(new BigDecimal("1.2345"), 2); // arrondi à 1.2
       ComplexValue result = (ComplexValue) c.subtract(r);
       assertEquals(new BigDecimal("3.8"), result.getReal().setScale(1, RoundingMode.UNNECESSARY)); // Résultat attendu : 3.8
       assertEquals(new BigDecimal("2.0"), result.getImag().setScale(1, RoundingMode.UNNECESSARY));
    }

    @Test
    void testMultiplyWithRealValuePrecision() {
       ComplexValue c = new ComplexValue(2.0, -2.0);
       RealValue r = new RealValue(0.66666, 3); // arrondi à 0.667
       ComplexValue result = (ComplexValue) c.multiply(r);
       BigDecimal expectedReal = BigDecimal.valueOf(2.0 * 0.667).setScale(3, RoundingMode.HALF_UP);
       BigDecimal expectedImag = BigDecimal.valueOf(-2.0 * 0.667).setScale(3, RoundingMode.HALF_UP);
       assertEquals(expectedReal, result.getReal().setScale(3, RoundingMode.HALF_UP));
       assertEquals(expectedImag, result.getImag().setScale(3, RoundingMode.HALF_UP));
    }

    @Test
    void testDivideWithRealValuePrecision() {
       ComplexValue c = new ComplexValue(4.0, 2.0);
       RealValue r = new RealValue(2.22222, 1); // arrondi à 2.2

       // Perform the division
       ComplexValue result = (ComplexValue) c.divide(r);

       // Calculate the expected real and imaginary parts, rounded to 2 decimal places
       BigDecimal expectedReal = BigDecimal.valueOf(4.0 / 2.2).setScale(2, RoundingMode.HALF_UP);
       BigDecimal expectedImag = BigDecimal.valueOf(2.0 / 2.2).setScale(2, RoundingMode.HALF_UP);

       // Ensure that both the real and imaginary parts are rounded correctly for comparison
       assertEquals(expectedReal, result.getReal().setScale(2, RoundingMode.HALF_UP));
       assertEquals(expectedImag, result.getImag().setScale(2, RoundingMode.HALF_UP));
    }


    @Test
    void testAdditionWithIntegerValue() {
       ComplexValue complex = new ComplexValue(BigDecimal.ONE, BigDecimal.ONE); // 1 + i
       IntegerValue integer = new IntegerValue(2); // +2
       NumericValue result = complex.add(integer);
        assertInstanceOf(ComplexValue.class, result);
       assertEquals("3 + 1i", result.toString());
    }

     @Test
     void testUnsupportedAdditionTypeThrowsException() {
         ComplexValue complex = new ComplexValue(BigDecimal.ONE, BigDecimal.ONE);
         RationalValue rational = new RationalValue(1, 2);

         assertThrows(UnsupportedOperationException.class, () -> complex.add(rational));
     }




     @Test
    void testSubtractionWithRealValue() {
       ComplexValue complex = new ComplexValue(BigDecimal.valueOf(1.5), BigDecimal.valueOf(2.0));
       RealValue real = new RealValue(0.5, 1);
       NumericValue result = complex.subtract(real);
        assertInstanceOf(ComplexValue.class, result);
       assertEquals("1.0 + 2.0i", result.toString()); // arrondi à 1 décimale
    }

    @Test
    void testSubtractionWithIntegerValue() {
       ComplexValue complex = new ComplexValue(BigDecimal.valueOf(5.0), BigDecimal.valueOf(3.0));
       IntegerValue integer = new IntegerValue(2);
       NumericValue result = complex.subtract(integer);
        assertInstanceOf(ComplexValue.class, result);
       assertEquals("3.0 + 3.0i", result.toString());
    }

     @Test
     void testUnsupportedSubtractionTypeThrowsException() {
         ComplexValue complex = new ComplexValue(BigDecimal.TEN, BigDecimal.ONE);
         RationalValue rational = new RationalValue(1, 2);

         assertThrows(UnsupportedOperationException.class, () -> complex.subtract(rational));
     }

     @Test
    void testMultiplicationWithIntegerValue() {
       ComplexValue complex = new ComplexValue(BigDecimal.valueOf(2), BigDecimal.valueOf(3)); // 2 + 3i
       IntegerValue integer = new IntegerValue(2);
       NumericValue result = complex.multiply(integer); // 4 + 6i
        assertInstanceOf(ComplexValue.class, result);
       assertEquals("4 + 6i", result.toString());
    }

     @Test
     void testUnsupportedMultiplicationTypeThrowsException() {
         ComplexValue complex = new ComplexValue(BigDecimal.valueOf(1), BigDecimal.valueOf(2));
         RationalValue rational = new RationalValue(1, 3);

         assertThrows(UnsupportedOperationException.class, () -> complex.multiply(rational));
     }

     @Test
    void testDivisionByZeroThrowsArithmeticException() {
       ComplexValue complex = new ComplexValue(BigDecimal.ONE, BigDecimal.ONE);
       IntegerValue zero = new IntegerValue(0);
         assertEquals("NaN", complex.divide(zero).toString(), "Complex : (1+1i) / (0) should be NaN");
    }

    @Test
    void testDivisionWithIntegerValue() {
       ComplexValue complex = new ComplexValue(BigDecimal.valueOf(4), BigDecimal.valueOf(2)); // 4 + 2i
       IntegerValue divisor = new IntegerValue(2);
       NumericValue result = complex.divide(divisor); // 2 + 1i
        assertInstanceOf(ComplexValue.class, result);
       assertEquals("2.00 + 1.00i", result.toString());
    }

     @Test
     void testUnsupportedDivisionTypeThrowsException() {
         ComplexValue complex = new ComplexValue(BigDecimal.valueOf(3), BigDecimal.valueOf(1));
         RationalValue rational = new RationalValue(1, 2);

         assertThrows(UnsupportedOperationException.class, () -> complex.divide(rational));
     }

     @Test
    void testGetValueIntReturnsZero() {
       ComplexValue complex = new ComplexValue(BigDecimal.TEN, BigDecimal.ONE);
       assertEquals(0, complex.getValueInt());
    }


     @Test
     void testModulus() {
         ComplexValue c = new ComplexValue(3.0, 4.0);
         assertEquals(5.0, c.modulus(), 1e-10, "Modulus of 3+4i should be 5");
     }

     @Test
     void testInverse() {
         ComplexValue c = new ComplexValue(1.0, 1.0);
         ComplexValue inv = (ComplexValue) c.inverse();
         // Inverse of 1+i is (1 - i)/2
         assertEquals(0.5, inv.getReal().doubleValue(), 1e-10);
         assertEquals(-0.5, inv.getImag().doubleValue(), 1e-10);
     }

     @Test
     void testLnReal() {
         ComplexValue c = new ComplexValue(2.0, 0.0);
         ComplexValue ln = (ComplexValue) c.ln();
         // ln(2) + i*0
         assertEquals(Math.log(2.0), ln.getReal().doubleValue(), 1e-10);
         assertEquals(0.0, ln.getImag().doubleValue(), 1e-10);
     }

     @Test
     void testLnComplex() {
         ComplexValue c = new ComplexValue(1.0, 1.0);
         ComplexValue ln = (ComplexValue) c.ln();
         // Expected: ln(sqrt(2)) + i*(pi/4)
         assertEquals(Math.log(Math.sqrt(2)), ln.getReal().doubleValue(), 1e-10);
         assertEquals(Math.PI / 4, ln.getImag().doubleValue(), 1e-10);
     }

     @Test
     void testExpComplex() {
         ComplexValue c = new ComplexValue(0.0, Math.PI);
         ComplexValue exp = (ComplexValue) c.exp();
         // exp(i*pi) = -1 + 0i
         assertEquals(-1.0, exp.getReal().doubleValue(), 1e-10);
         assertEquals(0.0, exp.getImag().doubleValue(), 1e-10);
     }

     @Test
     void testPowIntegerExponent() {
         ComplexValue c = new ComplexValue(2.0, 3.0);
         ComplexValue squared = (ComplexValue) c.pow(new IntegerValue(2));
         // (2+3i)^2 = -5 + 12i
         assertEquals(-5.0, squared.getReal().doubleValue(), 1e-10);
         assertEquals(12.0, squared.getImag().doubleValue(), 1e-10);
     }

     @Test
     void testRootIntegerDegree() {
         ComplexValue c = new ComplexValue(8.0, 0.0);
         ComplexValue cubeRoot = (ComplexValue) c.root(new IntegerValue(3));
         // ³√8 = 2 + 0i
         assertEquals(2.0, cubeRoot.getReal().doubleValue(), 1e-10);
         assertEquals(0.0, cubeRoot.getImag().doubleValue(), 1e-10);
     }

     @Test
     void testLogBaseComplexSelf() {
         ComplexValue b = new ComplexValue(1.0, 1.0);
         ComplexValue result = (ComplexValue) b.log(b);
         // log_b(b) = 1 + 0i
         assertEquals(1.0, result.getReal().doubleValue(), 1e-10);
         assertEquals(0.0, result.getImag().doubleValue(), 1e-10);
     }


 }



