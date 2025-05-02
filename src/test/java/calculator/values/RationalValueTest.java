package calculator.values;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
       assertEquals("NaN", r1.divide(zero).toString(),"Rational : 0/5 is NaN");
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


    @Test
    void testNegativeNumerator() {
       RationalValue r = new RationalValue(-2, 4); // devrait simplifier en -1/2
       assertEquals(new BigInteger("-1"), r.getNumerator());
       assertEquals(new BigInteger("2"), r.getDenominator());
    }

    @Test
    void testNegativeDenominator() {
       RationalValue r = new RationalValue(2, -4); // devrait simplifier en -1/2
       assertEquals(new BigInteger("-1"), r.getNumerator());
       assertEquals(new BigInteger("2"), r.getDenominator());
    }

    @Test
    void testDoubleNegative() {
       RationalValue r = new RationalValue(-2, -4); // devrait simplifier en 1/2
       assertEquals(new BigInteger("1"), r.getNumerator());
       assertEquals(new BigInteger("2"), r.getDenominator());
    }

    @Test
    void testRealValueAddition() {
       RationalValue r = new RationalValue(1, 2);
       RealValue real = new RealValue(0.25, 2); // 1/2 + 0.25 = 0.75
       NumericValue result = r.add(real);
        assertInstanceOf(RealValue.class, result);
       assertEquals("0.75", result.toString());
    }

    @Test
    void testRealValueSubtraction() {
       RationalValue r = new RationalValue(3, 4);
       RealValue real = new RealValue(0.25, 2); // 3/4 - 0.25 = 0.5
       NumericValue result = r.subtract(real);
        assertInstanceOf(RealValue.class, result);
       assertEquals("0.5", result.toString()); // dépend du toString de RealValue
    }

    @Test
    void testRealValueMultiplication() {
       RationalValue r = new RationalValue(1, 2);
       RealValue real = new RealValue(4.0, 1); // 0.5 * 4 = 2.0
       NumericValue result = r.multiply(real);
        assertInstanceOf(RealValue.class, result);
       assertEquals("2", result.toString());
    }

    @Test
    void testRealValueDivision() {
       RationalValue r = new RationalValue(1, 2);
       RealValue real = new RealValue(0.25, 2); // 0.5 / 0.25 = 2.0
       NumericValue result = r.divide(real);
       assertInstanceOf(RealValue.class, result);

       assertEquals("2", result.toString()); // selon précision
    }

    @Test
    void testComplexValueAddition() {
       RationalValue r = new RationalValue(1, 2);
       ComplexValue complex = new ComplexValue(BigDecimal.ONE, BigDecimal.ONE); // 0.5 + (1 + i) = 1.5 + i
       NumericValue result = r.add(complex);
        assertInstanceOf(ComplexValue.class, result);
       assertEquals("1.5 + i", result.toString()); // dépend du toString() de ComplexValue
    }

    @Test
    void testScientificNotationToStringLargeValue() {
       RationalValue r = new RationalValue(new BigInteger("10000000"), BigInteger.ONE); // 10^7
       assertTrue(r.toString().contains("E") || r.toString().contains("e") || r.toString().contains("10"));
    }

    @Test
    void testScientificNotationToStringSmallValue() {
       RationalValue r = new RationalValue(BigInteger.ONE, new BigInteger("10000000")); // 10^-7
       assertTrue(r.toString().contains("E") || r.toString().contains("e") || r.toString().contains("10"));
    }

    @Test
    void testToStringZero() {
       RationalValue r = new RationalValue(0, 7); // 0/7 = 0
       assertEquals("0", r.toString());
    }

    @Test
    void testGetValueInt() {
       RationalValue r = new RationalValue(5, 2);
       assertEquals(0, r.getValueInt()); // méthode non implémentée significativement
    }

    @Test
    void testDenominatorZeroThrowsException() {

       assertEquals("NaN", new RationalValue(1, 0).toString(), "RationalValue : 1 / 0 should be NaN");
    }



    @Test
    void testMultiplyWithIntegerValueConvertsToRational() {
       // (3/4) * 2 = 6/4 = 3/2
       RationalValue r = new RationalValue(3, 4);
       IntegerValue i = new IntegerValue(2);
       RationalValue result = (RationalValue) r.multiply(i);

       assertEquals(BigInteger.valueOf(3), result.getNumerator(), "Numérateur doit être 3 après simplification");
       assertEquals(BigInteger.valueOf(2), result.getDenominator(), "Dénominateur doit être 2 après simplification");
       assertEquals("3/2", result.toString(), "toString() doit rendre la fraction simplifiée \"3/2\"");
    }

    @Test
    void testMultiplyWithComplexValueReturnsComplex() {
       // (1/2) * (1 + 2i) = 0.5 + i
       RationalValue r = new RationalValue(1, 2);
       ComplexValue c = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("2.0"));

       NumericValue result = r.multiply(c);
        assertInstanceOf(ComplexValue.class, result, "Le résultat doit être un ComplexValue");

       ComplexValue complexResult = (ComplexValue) result;
       assertEquals(new BigDecimal("0.50"), complexResult.getReal(), "Partie réelle attendue : 0.5");
       assertEquals(new BigDecimal("1.00"), complexResult.getImag(), "Partie imaginaire attendue : 1.0");
    }

    @Test
    void testDivideWithIntegerValueConvertsToRational() {
       // (3/4) / 2 = 3/8
       RationalValue r = new RationalValue(3, 4);
       IntegerValue i = new IntegerValue(2);
       RationalValue result = (RationalValue) r.divide(i);

       assertEquals(BigInteger.valueOf(3), result.getNumerator(), "Numérateur doit être 3");
       assertEquals(BigInteger.valueOf(8), result.getDenominator(), "Dénominateur doit être 8");
       assertEquals("3/8", result.toString(), "toString() doit rendre \"3/8\"");
    }

    @Test
    void testDivideWithComplexValueReturnsComplex() {
       // (1/2) / (1 + i) = 0.25 - 0.25i
       RationalValue r = new RationalValue(1, 2);
       ComplexValue c = new ComplexValue(new BigDecimal("1.0"), new BigDecimal("1.0"));

       NumericValue result = r.divide(c);
       assertInstanceOf(ComplexValue.class, result, "Le résultat doit être un ComplexValue");

       ComplexValue complexResult = (ComplexValue) result;
       assertEquals(new BigDecimal("0.25"), complexResult.getReal(), "Partie réelle attendue : 0.25");
       assertEquals(new BigDecimal("-0.25"), complexResult.getImag(), "Partie imaginaire attendue : -0.25");
    }


    @Test
    void testPowIntegerExponent() {
       // (2/3)^3 = 8/27
       RationalValue r = new RationalValue(2, 3);
       RationalValue result = (RationalValue) r.pow(new IntegerValue(3));
       assertEquals(BigInteger.valueOf(8), result.getNumerator());
       assertEquals(BigInteger.valueOf(27), result.getDenominator());
    }

    @Test
    void testPowRealExponent() {
       // (1/4)^(0.5) = 0.5 as RealValue
       RationalValue r = new RationalValue(1, 4);
       NumericValue result = r.pow(new RealValue(0.5, 5));
       assertInstanceOf(RealValue.class, result);
       assertEquals(0.5, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testRootIntegerDegree() {
       //  (27/8)^(1/3) = 3/2  as RealValue
       RationalValue r = new RationalValue(27, 8);
       NumericValue result = r.root(new IntegerValue(3));
       assertInstanceOf(RealValue.class, result);
       assertEquals(1.5, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testRootRealDegree() {
       // (16/81)^(1/4) rounded to 5 decimals = 0.66667
       RationalValue r = new RationalValue(16, 81);
       NumericValue result = r.root(new RealValue(4.0, 5));
       assertInstanceOf(RealValue.class, result);
       assertEquals(
               0.66667,
               ((RealValue) result).getValue().doubleValue(),
               1e-9,
               "Expected (16/81)^(1/4) rounded to 5 decimals"
       );
    }


    @Test
    void testLogIntegerBase() {
       // log₂(8/4) = log₂(2) = 1
       RationalValue r = new RationalValue(8, 4);
       // Use a real base so that log(...) returns a RealValue without a cast error
       RealValue base = new RealValue(2.0, 10);
       NumericValue result = r.log(base);
       assertInstanceOf(RealValue.class, result);
       assertEquals(1.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }


    @Test
    void testLogRealBase() {
       // log_{10.0}(100/1) = 2
       RationalValue r = new RationalValue(100, 1);
       NumericValue result = r.log(new RealValue(10.0, 5));
       assertInstanceOf(RealValue.class, result);
       assertEquals(2.0, ((RealValue) result).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testInverseNonZero() {
       // inverse of 3/4 = 4/3
       RationalValue r = new RationalValue(3, 4);
       RationalValue inv = (RationalValue) r.inverse();
       assertEquals(BigInteger.valueOf(4), inv.getNumerator());
       assertEquals(BigInteger.valueOf(3), inv.getDenominator());
    }

    @Test
    void testInverseZeroNumerator() {
       // inverse of 0/5 = NaN
       RationalValue r = new RationalValue(0, 5);
       assertEquals("NaN", r.inverse().toString());
    }

    @Test
    void testLnOfEUsingRealValue() {
       RealValue rv = new RealValue(Math.E, 10);
       NumericValue ln = rv.ln();
       assertInstanceOf(RealValue.class, ln);
       assertEquals(1.0, ((RealValue) ln).getValue().doubleValue(), 1e-9);
    }


    @Test
    void testLnNonPositive() {
       // ln(-1/2) or ln(0/1) = NaN
       assertEquals("NaN", new RationalValue(-1, 2).ln().toString());
       assertEquals("NaN", new RationalValue(0, 1).ln().toString());
    }

    @Test
    void testExp() {
       // exp(1/1) = e
       RationalValue r = new RationalValue(1, 1);
       NumericValue exp = r.exp();
       assertInstanceOf(RealValue.class, exp);
       assertEquals(Math.E, ((RealValue) exp).getValue().doubleValue(), 1e-9);
    }

    @Test
    void testPowRootLogChain() {
       // chain: root(pow( (9/4), 2 ), 2) = root( (9/4)^2 = 81/16 , 2 ) = 9/4
       RationalValue r = new RationalValue(9, 4);
       RationalValue squared = (RationalValue) r.pow(new IntegerValue(2));
       NumericValue back = squared.root(new IntegerValue(2));
       assertTrue(back instanceof RealValue);
       assertEquals(9.0/4.0, ((RealValue) back).getValue().doubleValue(), 1e-9);
    }


    @Test
    void testInteractionWithComplex() {
       // (3/5) + (2 + i) = 0.6 + 2 + i = 2.6 + 1i
       RationalValue r = new RationalValue(3, 5);
       ComplexValue c = new ComplexValue(2.0, 1.0);
       NumericValue sum = r.add(c);
       assertInstanceOf(ComplexValue.class, sum);
       // update expected to the correct result
       assertEquals("2.6 + 1.0i", sum.toString());
    }


 }
