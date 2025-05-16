package calculator.operator;
import calculator.*;


import calculator.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DisplayName("Percent Operation Tests")
class TestPercent {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        @Test
        @DisplayName("Should construct Percent with valid parameters")
        void shouldConstructPercentWithValidParameters() {
            assertDoesNotThrow(() -> new Percent(
                    List.of(new MyNumber(new IntegerValue(50))),
                    Notation.POSTFIX
            ));
        }


        @Test
        @DisplayName("Should throw IllegalConstruction with null parameters")
        void shouldThrowIllegalConstructionWithNullParameters() {
            assertThrows(IllegalConstruction.class, () ->
                    new Percent(null, Notation.POSTFIX));
        }
    }

    @Nested
    @DisplayName("Integer Value Tests")
    class IntegerValueTests {
        @Test
        @DisplayName("Should calculate percentage of integer")
        void shouldCalculatePercentageOfInteger() throws IllegalConstruction {
            Percent percent = new Percent(
                    List.of(new MyNumber(new IntegerValue(50))),
                    Notation.POSTFIX
            );
            NumericValue result = percent.op(new IntegerValue(50));
            assertEquals("0.5", result.toString());
        }

        @Nested
        @DisplayName("Zero Value Tests")
        class ZeroValueTests {
            @Test
            @DisplayName("Should handle zero percent")
            void shouldHandleZeroPercent() throws IllegalConstruction {
                Percent percent = new Percent(
                        List.of(new MyNumber(new IntegerValue(100))),
                        Notation.POSTFIX
                );
                NumericValue result = percent.op(new IntegerValue(0));
                assertEquals("0", result.toString(),
                        "0% of any number should be 0");

                // Also test zero divided by 100
                NumericValue result2 = percent.op(new IntegerValue(0));
                assertEquals("0", result2.toString(),
                        "0 divided by 100 should be 0");
            }
        }
    }

    @Nested
    @DisplayName("Real Value Tests")
    class RealValueTests {
        @Test
        @DisplayName("Should calculate percentage of real number")
        void shouldCalculatePercentageOfRealNumber() throws IllegalConstruction {
            Percent percent = new Percent(
                    List.of(new MyNumber(new RealValue(75.5, 3))),
                    Notation.POSTFIX
            );
            NumericValue result = percent.op(new RealValue(75.5, 3));
            assertEquals("0.755", result.toString());
        }

        @Test
        @DisplayName("Should handle real number with high precision")
        void shouldHandleRealNumberWithHighPrecision() throws IllegalConstruction {
            Percent percent = new Percent(
                    List.of(new MyNumber(new RealValue(33.333, 5))),
                    Notation.POSTFIX
            );
            NumericValue result = percent.op(new RealValue(33.333, 5));
            assertInstanceOf(RealValue.class, result);
            assertEquals("0.33333", result.toString());
        }
    }


    @Nested
    @DisplayName("Complex Value Tests")
    class ComplexValueTests {
        @Test
        @DisplayName("Should calculate percentage of complex number")
        void shouldCalculatePercentageOfComplexNumber() throws IllegalConstruction {
            Percent percent = new Percent(
                    List.of(new MyNumber(new ComplexValue(2, 2))),
                    Notation.POSTFIX
            );
            NumericValue result = percent.op(new ComplexValue(2, 2));
            assertInstanceOf(ComplexValue.class, result);
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        @Test
        @DisplayName("Should evaluate complete percent expression")
        void shouldEvaluateCompletePercentExpression() throws IllegalConstruction {
            Expression percentExpr = new Percent(
                    List.of(new MyNumber(new IntegerValue(50))),
                    Notation.POSTFIX
            );
            NumericValue result = calculator.eval(percentExpr);
            assertEquals("0.5", result.toString());
        }

        @Test
        @DisplayName("Should handle chained operations")
        void shouldHandleChainedOperations() throws IllegalConstruction {
            Expression plusExpr = new Plus(List.of(
                    new Percent(
                            List.of(new MyNumber(new IntegerValue(50))),
                            Notation.POSTFIX
                    ),
                    new MyNumber(new IntegerValue(1))
            ));
            NumericValue result = calculator.eval(plusExpr);
            assertEquals("1.5", result.toString());
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {
        @Test
        @DisplayName("Should handle very large numbers")
        void shouldHandleVeryLargeNumbers() throws IllegalConstruction {
            Percent percent = new Percent(
                    List.of(new MyNumber(new IntegerValue(1000000))),
                    Notation.POSTFIX
            );
            NumericValue result = percent.op(new IntegerValue(1000000));
            assertEquals("10000", result.toString());
        }

        @Test
        @DisplayName("Should handle very small numbers")
        void shouldHandleVerySmallNumbers() throws IllegalConstruction {
            Percent percent = new Percent(
                    List.of(new MyNumber(new RealValue(0.0001, 4))),
                    Notation.POSTFIX
            );
            NumericValue result = percent.op(new RealValue(0.0001, 4));
            assertInstanceOf(RealValue.class, result);
            assertTrue(Double.parseDouble(result.toString()) < 0.000001);
        }
    }
}