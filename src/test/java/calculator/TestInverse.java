package calculator;

import calculator.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Inverse Operation Tests")
class TestInverse {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        @Test
        @DisplayName("Should construct Inverse with valid parameter")
        void shouldConstructInverseWithValidParameter() {
            assertDoesNotThrow(() -> new Inverse(
                    List.of(new MyNumber(new IntegerValue(1))),
                    Notation.PREFIX
            ));
        }

        @Test
        @DisplayName("Should throw IllegalConstruction with null parameters")
        void shouldThrowIllegalConstructionWithNullParameters() {
            assertThrows(IllegalConstruction.class, () ->
                            new Inverse(null, Notation.PREFIX),
                    "Should throw IllegalConstruction when parameter list is null"
            );
        }


    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {
        @Test
        @DisplayName("Should calculate inverse of integer 1")
        void shouldCalculateInverseOfOne() throws IllegalConstruction {
            Inverse inv = new Inverse(List.of(new MyNumber(new IntegerValue(1))), Notation.PREFIX);
            NumericValue result = inv.op(new IntegerValue(1));
            assertEquals("1", result.toString());
        }

        @Test
        @DisplayName("Should calculate inverse of integer 2")
        void shouldCalculateInverseOfTwo() throws IllegalConstruction {
            Inverse inv = new Inverse(List.of(new MyNumber(new IntegerValue(2))), Notation.PREFIX);
            NumericValue result = inv.op(new IntegerValue(2));
            assertEquals("1/2", result.toString());
        }

        @Test
        @DisplayName("Should calculate inverse of real number")
        void shouldCalculateInverseOfRealNumber() throws IllegalConstruction {
            Inverse inv = new Inverse(List.of(new MyNumber(new RealValue(2.0, 5))), Notation.PREFIX);
            NumericValue result = inv.op(new RealValue(2.0, 5));
            assertTrue(result instanceof RealValue);
            RealValue realResult = (RealValue) result;
            assertEquals(0.5, realResult.getValue().doubleValue(), 0.00001);
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {
        @Test
        @DisplayName("Should throw exception for inverse of zero")
        void shouldThrowExceptionForZero() throws IllegalConstruction {
            Inverse inv = new Inverse(List.of(new MyNumber(new IntegerValue(0))), Notation.PREFIX);
            assertThrows(ArithmeticException.class, () ->
                            inv.op(new IntegerValue(0)),
                    "Should throw ArithmeticException when dividing by zero"
            );
        }

        @Test
        @DisplayName("Should handle inverse of negative numbers")
        void shouldHandleNegativeNumbers() throws IllegalConstruction {
            Inverse inv = new Inverse(List.of(new MyNumber(new IntegerValue(-2))), Notation.PREFIX);
            NumericValue result = inv.op(new IntegerValue(-2));
            assertEquals("-1/2", result.toString());
        }

        @Test
        @DisplayName("Should handle inverse of rational numbers")
        void shouldHandleRationalNumbers() throws IllegalConstruction {
            RationalValue rational = new RationalValue(1, 2); // 1/2
            Inverse inv = new Inverse(List.of(new MyNumber(rational)), Notation.PREFIX);
            NumericValue result = inv.op(rational);
            assertEquals("2", result.toString());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        @Test
        @DisplayName("Should evaluate complete inverse expression")
        void shouldEvaluateCompleteInverseExpression() throws IllegalConstruction {
            Expression invExpr = new Inverse(List.of(
                    new MyNumber(new IntegerValue(4))
            ), Notation.PREFIX);
            NumericValue result = calculator.eval(invExpr);
            assertEquals("1/4", result.toString());
        }

        @Test
        @DisplayName("Should handle nested inverse operations")
        void shouldHandleNestedInverseOperations() throws IllegalConstruction {
            // inv(inv(2)) should be 2
            Expression nestedInv = new Inverse(List.of(
                    new Inverse(List.of(
                            new MyNumber(new IntegerValue(2))
                    ), Notation.PREFIX)
            ), Notation.PREFIX);
            NumericValue result = calculator.eval(nestedInv);
            assertEquals("2", result.toString());
        }
    }
}