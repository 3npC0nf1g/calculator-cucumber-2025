package calculator.values;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

 class RationalValueTest {

    @Test
     void testSimplification() {
        // 6/12 doit être simplifié en 1/2
        RationalValue r = new RationalValue(6, 12);
        assertEquals(new BigInteger("1"), r.getNumerator());
        assertEquals(new BigInteger("2"), r.getDenominator());
    }

    @Test
     void testAddition() {
        // 1/3 + 1/6 = 1/2
        RationalValue r1 = new RationalValue(1, 3);
        RationalValue r2 = new RationalValue(1, 6);
        RationalValue result = (RationalValue) r1.add(r2);
        assertEquals(new BigInteger("1"), result.getNumerator());
        assertEquals(new BigInteger("2"), result.getDenominator());
    }

    @Test
     void testSubtraction() {
        // 3/4 - 1/4 = 2/4 qui se simplifie en 1/2
        RationalValue r1 = new RationalValue(3, 4);
        RationalValue r2 = new RationalValue(1, 4);
        RationalValue result = (RationalValue) r1.subtract(r2);
        assertEquals(new BigInteger("1"), result.getNumerator());
        assertEquals(new BigInteger("2"), result.getDenominator());
    }

    @Test
     void testMultiplication() {
        // 2/3 * 3/4 = 6/12 qui se simplifie en 1/2
        RationalValue r1 = new RationalValue(2, 3);
        RationalValue r2 = new RationalValue(3, 4);
        RationalValue result = (RationalValue) r1.multiply(r2);
        assertEquals(new BigInteger("1"), result.getNumerator());
        assertEquals(new BigInteger("2"), result.getDenominator());
    }

    @Test
     void testDivision() {
        // (1/2) / (3/4) = (1/2) * (4/3) = 4/6 qui se simplifie en 2/3
        RationalValue r1 = new RationalValue(1, 2);
        RationalValue r2 = new RationalValue(3, 4);
        RationalValue result = (RationalValue) r1.divide(r2);
        assertEquals(new BigInteger("2"), result.getNumerator());
        assertEquals(new BigInteger("3"), result.getDenominator());
    }

    @Test
     void testDivisionByZero() {
        RationalValue r1 = new RationalValue(1, 2);
        // Créer une fraction nulle (0/5) pour simuler une division par zéro
        RationalValue zero = new RationalValue(0, 5);
        assertThrows(ArithmeticException.class, () -> r1.divide(zero));
    }

    @Test
     void testToStringFraction() {
        // Si le dénominateur n'est pas 1, l'affichage doit être sous forme de fraction.
        RationalValue r = new RationalValue(3, 5);
        assertEquals("3/5", r.toString());
    }

    @Test
     void testToStringInteger() {
        // Si le dénominateur est 1, l'affichage doit être sous forme d'entier.
        RationalValue r = new RationalValue(4, 1);
        assertEquals("4", r.toString());
    }

    @Test
     void testEqualsAndHashCode() {
        // 2/4 doit être simplifié et égal à 1/2.
        RationalValue r1 = new RationalValue(2, 4);
        RationalValue r2 = new RationalValue(1, 2);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}
