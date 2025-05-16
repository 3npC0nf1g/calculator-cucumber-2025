package calculator.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ComplexValue Enhanced Tests")
class ComplexValueTest {

    @Nested @DisplayName("Construction & Accessors")
    class ConstructionTests {
        @Test void testConstructorDouble() {
            ComplexValue c = new ComplexValue(3.5, -1.2);
            assertEquals(new BigDecimal("3.5"), c.getReal());
            assertEquals(new BigDecimal("-1.2"), c.getImag());
            assertFalse(c.isNaN());
        }
        @Test void testConstructorBigDecimal() {
            ComplexValue c = new ComplexValue(new BigDecimal("2"), new BigDecimal("4"));
            assertEquals(new BigDecimal("2"), c.getReal());
            assertEquals(new BigDecimal("4"), c.getImag());
        }
        @Test void testNaNSingleton() {
            ComplexValue nan = ComplexValue.NaN;
            assertTrue(nan.isNaN());
            assertEquals("NaN", nan.toString());
        }
        @Test void testIsZero() {
            ComplexValue z = new ComplexValue(0.0, 0.0);
            assertTrue(z.isZero());
            assertFalse(new ComplexValue(1.0, 0.0).isZero());
        }
    }

    @Nested @DisplayName("toString / equals / hashCode")
    class IdentityTests {
        @Test void testToString() {
            ComplexValue c = new ComplexValue(1.23, -4.56);
            assertEquals("1.23 + -4.56i", c.toString());
        }
        @Test void testEqualsHashCode() {
            ComplexValue a = new ComplexValue(2.0, 3.0);
            ComplexValue b = new ComplexValue(BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0));
            assertEquals(a, b);
            assertEquals(a.hashCode(), b.hashCode());
            assertNotEquals(a, ComplexValue.NaN);
            assertNotEquals(a, null);
            assertNotEquals(a, "foo");
        }
    }

    @Nested @DisplayName("Basic Arithmetic with ComplexValue")
    class ArithmeticTests {
        @Test void testAddComplex() {
            ComplexValue a = new ComplexValue(2.0, 3.0);
            ComplexValue b = new ComplexValue(1.0, -1.0);
            ComplexValue r = (ComplexValue) a.add(b);
            assertEquals(new BigDecimal("3.0"), r.getReal().setScale(1, RoundingMode.HALF_UP));
            assertEquals(new BigDecimal("2.0"), r.getImag().setScale(1, RoundingMode.HALF_UP));
        }
        @Test void testSubtractComplex() {
            ComplexValue a = new ComplexValue(5.0, 4.0);
            ComplexValue b = new ComplexValue(2.0, 1.0);
            ComplexValue r = (ComplexValue) a.subtract(b);
            assertEquals(new BigDecimal("3.0"), r.getReal().setScale(1, RoundingMode.HALF_UP));
            assertEquals(new BigDecimal("3.0"), r.getImag().setScale(1, RoundingMode.HALF_UP));
        }
        @Test void testMultiplyComplex() {
            ComplexValue a = new ComplexValue(2.0, 3.0);
            ComplexValue b = new ComplexValue(4.0, -1.0);
            ComplexValue r = (ComplexValue) a.multiply(b);
            // (2·4−3·-1, 2·-1+3·4) = (11,10)
            assertEquals(new BigDecimal("11.0"), r.getReal().setScale(1, RoundingMode.HALF_UP));
            assertEquals(new BigDecimal("10.0"), r.getImag().setScale(1, RoundingMode.HALF_UP));
        }
        @Test void testDivideComplex() {
            ComplexValue a = new ComplexValue(5.0, 3.0);
            ComplexValue b = new ComplexValue(2.0, 1.0);
            ComplexValue r = (ComplexValue) a.divide(b);
            // (5+3i)/(2+i) = (13/5 + 1/5 i) = (2.6,0.2)
            assertEquals(new BigDecimal("2.60"), r.getReal().setScale(2, RoundingMode.HALF_UP));
            assertEquals(new BigDecimal("0.20"), r.getImag().setScale(2, RoundingMode.HALF_UP));
        }
        @Test void testDivideByZeroComplex() {
            ComplexValue a = new ComplexValue(1.0, 1.0);
            ComplexValue zero = new ComplexValue(0.0, 0.0);
            ComplexValue r = (ComplexValue) a.divide(zero);
            assertEquals("NaN", r.toString());
        }
    }

    @Nested @DisplayName("Mixed-type Arithmetic")
    class MixedTypeTests {
        @Test void testAddReal() {
            ComplexValue c = new ComplexValue(2.0, 3.0);
            RealValue r = new RealValue(1.5, 2);     // arrondi 1.5
            ComplexValue sum = (ComplexValue) c.add(r);
            assertEquals("3.50 + 3.0i", sum.toString());
        }
        @Test void testSubtractReal() {
            ComplexValue c = new ComplexValue(5.0, 2.0);
            RealValue r = new RealValue(new BigDecimal("1.2345"), 2); // 1.23
            ComplexValue diff = (ComplexValue) c.subtract(r);
            assertEquals("3.8 + 2.0i", diff.toString());
        }
        @Test void testMultiplyReal() {
            ComplexValue c = new ComplexValue(2.0, -2.0);
            RealValue r = new RealValue(0.66666, 3); // 0.667
            ComplexValue p = (ComplexValue) c.multiply(r);
            // 2·0.667=1.334, –2·0.667=–1.334
            assertEquals("1.3340", p.getReal().toString());
            assertEquals("-1.3340", p.getImag().toString());

        }
        @Test void testDivideReal() {
            ComplexValue c = new ComplexValue(4.0, 2.0);
            RealValue r = new RealValue(2.22222, 1); // 2.2
            ComplexValue d = (ComplexValue) c.divide(r);
            assertEquals(BigDecimal.valueOf(4.0/2.2).setScale(2, RoundingMode.HALF_UP), d.getReal());
            assertEquals(BigDecimal.valueOf(2.0/2.2).setScale(2, RoundingMode.HALF_UP), d.getImag());
        }
        @Test void testAddInteger() {
            ComplexValue c = new ComplexValue(BigDecimal.ONE, BigDecimal.ONE);
            IntegerValue i = new IntegerValue(2);
            ComplexValue sum = (ComplexValue) c.add(i);
            assertEquals("3 + 1i", sum.toString());
        }
        @Test void testSubtractInteger() {
            ComplexValue c = new ComplexValue(5.0, 3.0);
            IntegerValue i = new IntegerValue(2);
            ComplexValue d = (ComplexValue) c.subtract(i);
            assertEquals("3.0 + 3.0i", d.toString());
        }
        @Test void testMultiplyInteger() {
            ComplexValue c = new ComplexValue(2.0, 3.0);
            IntegerValue i = new IntegerValue(2);
            ComplexValue m = (ComplexValue) c.multiply(i);
            assertEquals("4.0 + 6.0i", m.toString());
        }
        @Test void testDivideInteger() {
            ComplexValue c = new ComplexValue(4.0, 2.0);
            IntegerValue i = new IntegerValue(2);
            ComplexValue d = (ComplexValue) c.divide(i);
            assertEquals("2.00 + 1.00i", d.toString());
        }
        @Test void testUnsupportedTypes() {
            ComplexValue c = new ComplexValue(1.0, 1.0);
            assertThrows(UnsupportedOperationException.class, () -> c.add(new RationalValue(1,2)));
            assertThrows(UnsupportedOperationException.class, () -> c.subtract(new RationalValue(1,2)));
            assertThrows(UnsupportedOperationException.class, () -> c.multiply(new RationalValue(1,2)));
            assertThrows(UnsupportedOperationException.class, () -> c.divide(new RationalValue(1,2)));
        }
    }

    @Nested @DisplayName("Modulus, Inverse, ln, exp")
    class AdvancedTests {
        @Test void testModulus() {
            ComplexValue c = new ComplexValue(3.0, 4.0);
            assertEquals(5.0, c.modulus(), 1e-10);
        }
        @Test void testInverse() {
            ComplexValue c = new ComplexValue(1.0, 1.0);
            ComplexValue inv = (ComplexValue) c.inverse();
            // (1−i)/(1+1) = 0.5−0.5i
            assertEquals(0.5, inv.getReal().doubleValue(), 1e-10);
            assertEquals(-0.5, inv.getImag().doubleValue(), 1e-10);
        }
        @Test void testLnRealAxis() {
            ComplexValue c = new ComplexValue(2.0, 0.0);
            ComplexValue ln = (ComplexValue) c.ln();
            assertEquals(Math.log(2.0), ln.getReal().doubleValue(), 1e-10);
            assertEquals(0.0, ln.getImag().doubleValue(), 1e-10);
        }
        @Test void testLnGeneral() {
            ComplexValue c = new ComplexValue(1.0, 1.0);
            ComplexValue ln = (ComplexValue) c.ln();
            assertEquals(Math.log(Math.sqrt(2)), ln.getReal().doubleValue(), 1e-10);
            assertEquals(Math.PI/4,           ln.getImag().doubleValue(), 1e-10);
        }
        @Test void testExpPureImaginary() {
            ComplexValue c = new ComplexValue(0.0, Math.PI);
            ComplexValue e = (ComplexValue) c.exp();
            // e^{iπ} = -1 + 0i
            assertEquals(-1.0, e.getReal().doubleValue(), 1e-10);
            assertEquals( 0.0, e.getImag().doubleValue(), 1e-10);
        }
    }

    @Nested @DisplayName("Power & Root")
    class PowerRootTests {
        @Test void testPowInteger() {
            ComplexValue c = new ComplexValue(2.0, 3.0);
            ComplexValue sq = (ComplexValue) c.pow(new IntegerValue(2));
            // (2+3i)^2 = -5 + 12i
            assertEquals(-5.0, sq.getReal().doubleValue(), 1e-10);
            assertEquals(12.0, sq.getImag().doubleValue(), 1e-10);
        }
        @Test void testPowReal() {
            ComplexValue c = new ComplexValue(4.0, 0.0);
            ComplexValue r = (ComplexValue) c.pow(new RealValue(0.5, 5));
            assertEquals(2.0, r.getReal().doubleValue(), 1e-9);
            assertEquals(0.0, r.getImag().doubleValue(), 1e-9);
        }
        @Test void testRootInteger() {
            ComplexValue c = new ComplexValue(8.0, 0.0);
            ComplexValue cr = (ComplexValue) c.root(new IntegerValue(3));
            assertEquals(2.0, cr.getReal().doubleValue(), 1e-9);
            assertEquals(0.0, cr.getImag().doubleValue(), 1e-9);
        }
        @Test void testRootUnsupportedDegree() {
            assertThrows(UnsupportedOperationException.class,
                    () -> ComplexValue.NaN.root(new RealValue(2.0, 2)));
        }
    }

    @Nested @DisplayName("Logarithm")
    class LogTests {
        @Test void testLogComplexBase() {
            ComplexValue z = new ComplexValue(1.0, 1.0);
            ComplexValue r = (ComplexValue) z.log(z);
            assertEquals(1.0, r.getReal().doubleValue(), 1e-10);
            assertEquals(0.0, r.getImag().doubleValue(), 1e-10);
        }
        @Test void testLogInvalidBaseType() {
            assertThrows(IllegalArgumentException.class,
                    () -> new ComplexValue(1,1).log(new RealValue(2.0,2)));
        }
    }
}
