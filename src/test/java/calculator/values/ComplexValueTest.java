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
        assertEquals(new BigDecimal("3.0"), diff.getReal().setScale(1, RoundingMode.HALF_UP));
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
        assertThrows(ArithmeticException.class, () -> c1.divide(zero));
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
}



