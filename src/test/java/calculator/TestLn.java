package calculator;

import calculator.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Natural Logarithm (Ln) Tests")
class TestLn {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        @Test
        @DisplayName("Should construct Ln with valid parameter")
        void shouldConstructLnWithValidParameter() {
            assertDoesNotThrow(() -> new Ln(
                    List.of(new MyNumber(new IntegerValue(1))),
                    Notation.PREFIX
            ));
        }

        @Test
        @DisplayName("Should throw IllegalConstruction with null parameters")
        void shouldThrowIllegalConstructionWithNullParameters() {
            assertThrows(IllegalConstruction.class, () ->
                            new Ln(null, Notation.PREFIX),
                    "Should throw IllegalConstruction when parameter list is null"
            );
        }
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {
        @Test
        @DisplayName("Should calculate ln(1)")
        void shouldCalculateLnOfOne() throws IllegalConstruction {
            Ln ln = new Ln(List.of(new MyNumber(new IntegerValue(1))), Notation.PREFIX);
            NumericValue result = ln.op(new IntegerValue(1));
            assertEquals("0", result.toString());
        }

        @Test
        @DisplayName("Should calculate ln(e)")
        void shouldCalculateLnOfE() throws IllegalConstruction {
            Ln ln = new Ln(List.of(new MyNumber(new RealValue(Math.E, 5))), Notation.PREFIX);
            NumericValue result = ln.op(new RealValue(Math.E, 5));
            assertEquals("1", result.toString());
        }

        @Test
        @DisplayName("Should calculate ln of real number")
        void shouldCalculateLnOfRealNumber() throws IllegalConstruction {
            Ln ln = new Ln(List.of(new MyNumber(new RealValue(2.0, 6))), Notation.PREFIX);
            NumericValue result = ln.op(new RealValue(2.0, 6));
            assertTrue(result instanceof RealValue);
            RealValue realResult = (RealValue) result;
            assertEquals(0.693147, realResult.getValue().doubleValue(), 0.00001);
        }

        @Test
        @DisplayName("Should maintain precision for different real numbers")
        void shouldMaintainPrecisionForDifferentRealNumbers() throws IllegalConstruction {
            double[] testValues = {2.0, 3.0, 4.0, 5.0, 10.0};
            int precision = 6;

            for (double value : testValues) {
                Ln ln = new Ln(List.of(new MyNumber(new RealValue(value, precision))), Notation.PREFIX);
                NumericValue result = ln.op(new RealValue(value, precision));
                assertTrue(result instanceof RealValue);
                RealValue realResult = (RealValue) result;
                assertEquals(Math.log(value), realResult.getValue().doubleValue(), 0.00001,
                        String.format("Failed for ln(%f)", value));
            }
        }
    }

    @Nested
    @DisplayName("Error Cases Tests")
    class ErrorCasesTests {
        @Test
        @DisplayName("Should throw exception for negative number")
        void shouldThrowExceptionForNegativeNumber() throws IllegalConstruction {
            Ln ln = new Ln(List.of(new MyNumber(new IntegerValue(-1))), Notation.PREFIX);
            assertThrows(ArithmeticException.class, () ->
                            ln.op(new IntegerValue(-1)),
                    "Should throw ArithmeticException for negative numbers"
            );
        }

        @Test
        @DisplayName("Should throw exception for zero")
        void shouldThrowExceptionForZero() throws IllegalConstruction {
            Ln ln = new Ln(List.of(new MyNumber(new IntegerValue(0))), Notation.PREFIX);
            assertThrows(ArithmeticException.class, () ->
                            ln.op(new IntegerValue(0)),
                    "Should throw ArithmeticException for zero"
            );
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        @Test
        @DisplayName("Should evaluate complete ln expression")
        void shouldEvaluateCompleteLnExpression() throws IllegalConstruction {
            Expression lnExpr = new Ln(List.of(
                    new MyNumber(new IntegerValue(1))
            ), Notation.PREFIX);
            NumericValue result = calculator.eval(lnExpr);
            assertEquals("0", result.toString());
        }




    }

    @Nested
    @DisplayName("Special Values Tests")
    class SpecialValuesTests {
        @Test
        @DisplayName("Should calculate ln of large number")
        void shouldCalculateLnOfLargeNumber() throws IllegalConstruction {
            Ln ln = new Ln(List.of(
                    new MyNumber(new RealValue(1000.0, 6))
            ), Notation.PREFIX);
            NumericValue result = ln.op(new RealValue(1000.0, 6));
            assertTrue(result instanceof RealValue);
            RealValue realResult = (RealValue) result;
            assertEquals(6.907755, realResult.getValue().doubleValue(), 0.00001);
        }
    }
}