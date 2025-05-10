package calculator;

import calculator.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Log Operation Tests")
class TestLog {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        @Test
        @DisplayName("Should construct Log with valid parameters")
        void shouldConstructLogWithValidParameters() {
            assertDoesNotThrow(() -> new Log(List.of(
                    new MyNumber(new IntegerValue(2)),  // base
                    new MyNumber(new IntegerValue(8))   // number
            ), Notation.INFIX));
        }


    }

    @Nested
    @DisplayName("Integer Logarithm Tests")
    class IntegerLogarithmTests {
        @Test
        @DisplayName("Should calculate log base 2")
        void shouldCalculateLogBase2() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(8))
            ), Notation.INFIX);
            NumericValue result = log.op(new IntegerValue(2), new IntegerValue(8));
            assertEquals("3", result.toString());
        }

        @Test
        @DisplayName("Should calculate log base 10")
        void shouldCalculateLogBase10() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(10)),
                    new MyNumber(new IntegerValue(1000))
            ), Notation.INFIX);
            NumericValue result = log.op(new IntegerValue(10), new IntegerValue(1000));
            assertEquals("3", result.toString());
        }

        @Test
        @DisplayName("Should handle log of 1")
        void shouldHandleLogOf1() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(10)),
                    new MyNumber(new IntegerValue(1))
            ), Notation.INFIX);
            NumericValue result = log.op(new IntegerValue(10), new IntegerValue(1));
            assertEquals("0", result.toString());
        }
    }

    @Nested
    @DisplayName("Real Logarithm Tests")
    class RealLogarithmTests {
        @Test
        @DisplayName("Should calculate real number logarithm")
        void shouldCalculateRealLogarithm() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new RealValue(2.0, 5)),
                    new MyNumber(new RealValue(4.0, 5))
            ), Notation.INFIX);
            NumericValue result = log.op(new RealValue(2.0, 5), new RealValue(4.0, 5));
            assertEquals("2", result.toString());
        }
    }

    @Nested
    @DisplayName("Error Cases Tests")
    class ErrorCasesTests {
        @Test
        @DisplayName("Should handle logarithm of negative numbers")
        void shouldHandleLogarithmOfNegativeNumbers() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(-4))
            ), Notation.INFIX);

            assertThrows(ArithmeticException.class, () ->
                    log.op(new IntegerValue(2), new IntegerValue(-4)));
        }

        @Test
        @DisplayName("Should handle logarithm with negative base")
        void shouldHandleLogarithmWithNegativeBase() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(-2)),
                    new MyNumber(new IntegerValue(4))
            ), Notation.INFIX);

            assertThrows(ArithmeticException.class, () ->
                    log.op(new IntegerValue(-2), new IntegerValue(4)));
        }

        @Test
        @DisplayName("Should handle logarithm of zero")
        void shouldHandleLogarithmOfZero() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(0))
            ), Notation.INFIX);

            assertThrows(ArithmeticException.class, () ->
                    log.op(new IntegerValue(2), new IntegerValue(0)));
        }

        @Test
        @DisplayName("Should handle logarithm with base 1")
        void shouldHandleLogarithmWithBase1() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(1)),
                    new MyNumber(new IntegerValue(4))
            ), Notation.INFIX);

            assertThrows(ArithmeticException.class, () ->
                    log.op(new IntegerValue(1), new IntegerValue(4)));
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        @Test
        @DisplayName("Should evaluate complete logarithm expression")
        void shouldEvaluateCompleteLogarithmExpression() throws IllegalConstruction {
            Expression logExpr = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(8))
            ), Notation.INFIX);
            NumericValue result = calculator.eval(logExpr);
            assertEquals("3", result.toString());
        }

        @Test
        @DisplayName("Should handle chained logarithm operations")
        void shouldHandleChainedLogarithmOperations() throws IllegalConstruction {
            Expression logExpr = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new Log(List.of(
                            new MyNumber(new IntegerValue(2)),
                            new MyNumber(new IntegerValue(16))
                    ), Notation.INFIX)
            ), Notation.INFIX);
            NumericValue result = calculator.eval(logExpr);
            assertEquals("2", result.toString());
        }
    }

    @Nested
    @DisplayName("Special Cases Tests")
    class SpecialCasesTests {
        @Test
        @DisplayName("Should calculate log of base to itself")
        void shouldCalculateLogOfBaseToItself() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(2))
            ), Notation.INFIX);
            NumericValue result = log.op(new IntegerValue(2), new IntegerValue(2));
            assertEquals("1", result.toString());
        }

        @Test
        @DisplayName("Should handle large numbers")
        void shouldHandleLargeNumbers() throws IllegalConstruction {
            Log log = new Log(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(1024))
            ), Notation.INFIX);
            NumericValue result = log.op(new IntegerValue(2), new IntegerValue(1024));
            assertEquals("10", result.toString());
        }
    }
}