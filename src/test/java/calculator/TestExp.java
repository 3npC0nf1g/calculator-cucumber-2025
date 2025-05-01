package calculator;

import calculator.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exponential (Exp) Tests")
class TestExp {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        @Test
        @DisplayName("Should construct Exp with valid parameter")
        void shouldConstructExpWithValidParameter() {
            assertDoesNotThrow(() -> new Exp(
                    List.of(new MyNumber(new IntegerValue(1))),
                    Notation.PREFIX
            ));
        }

        @Test
        @DisplayName("Should throw IllegalConstruction with null parameters")
        void shouldThrowIllegalConstructionWithNullParameters() {
            assertThrows(IllegalConstruction.class, () ->
                            new Exp(null, Notation.PREFIX),
                    "Should throw IllegalConstruction when parameter list is null"
            );
        }
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {
        @Test
        @DisplayName("Should calculate exp(0)")
        void shouldCalculateExpOfZero() throws IllegalConstruction {
            Exp expOp = new Exp(List.of(new MyNumber(new IntegerValue(0))), Notation.PREFIX);
            NumericValue result = expOp.op(new IntegerValue(0));
            assertEquals("1", result.toString());
        }

        @Test
        @DisplayName("Should calculate exp(1)")
        void shouldCalculateExpOfOne() throws IllegalConstruction {
            Exp expOp = new Exp(List.of(new MyNumber(new IntegerValue(1))), Notation.PREFIX);
            NumericValue result = expOp.op(new IntegerValue(1));
            assertTrue(result instanceof RealValue);
            RealValue realResult = (RealValue) result;
            assertEquals(Math.exp(1.0), realResult.getValue().doubleValue(), 1e-10);
        }

        @Test
        @DisplayName("Should calculate exp of real number")
        void shouldCalculateExpOfReal() throws IllegalConstruction {
            double val = 2.0;
            int precision = 6;
            Exp expOp = new Exp(List.of(new MyNumber(new RealValue(val, precision))), Notation.PREFIX);
            NumericValue result = expOp.op(new RealValue(val, precision));
            assertTrue(result instanceof RealValue);
            RealValue realResult = (RealValue) result;
            assertEquals(Math.exp(val), realResult.getValue().doubleValue(), 1e-6);
        }

        @Test
        @DisplayName("Should calculate exp of complex number")
        void shouldCalculateExpOfComplex() throws IllegalConstruction {
            ComplexValue input = new ComplexValue(0.0, Math.PI);
            Exp expOp = new Exp(List.of(new MyNumber(input)), Notation.PREFIX);
            NumericValue result = expOp.op(input);
            assertTrue(result instanceof ComplexValue);
            ComplexValue complexResult = (ComplexValue) result;
            // exp(i*pi) = -1 + 0i
            assertEquals(-1.0, complexResult.getReal().doubleValue(), 1e-6);
            assertEquals(0.0, complexResult.getImag().doubleValue(), 1e-6);
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        @Test
        @DisplayName("Should evaluate complete exp expression")
        void shouldEvaluateCompleteExpExpression() throws IllegalConstruction {
            Expression expExpr = new Exp(
                    List.of(new MyNumber(new IntegerValue(2))),
                    Notation.PREFIX
            );
            NumericValue result = calculator.eval(expExpr);
            assertEquals(Math.exp(2.0), Double.parseDouble(result.toString()), 1e-10);
        }
    }

    @Nested
    @DisplayName("Special Values Tests")
    class SpecialValuesTests {
        @Test
        @DisplayName("Should calculate exp of negative number")
        void shouldCalculateExpOfNegative() throws IllegalConstruction {
            Exp expOp = new Exp(List.of(new MyNumber(new IntegerValue(-1))), Notation.PREFIX);
            NumericValue result = expOp.op(new IntegerValue(-1));
            assertTrue(result instanceof RealValue);
            RealValue realResult = (RealValue) result;
            assertEquals(Math.exp(-1.0), realResult.getValue().doubleValue(), 1e-10);
        }
    }
}