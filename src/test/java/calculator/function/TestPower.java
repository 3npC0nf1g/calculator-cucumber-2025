package calculator.function;
import calculator.*;


import calculator.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Power Operation Tests")
class TestPower {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        @Test
        @DisplayName("Should construct Power with valid parameters")
        void shouldConstructPowerWithValidParameters() {
            assertDoesNotThrow(() -> new Power(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(3))
            )));
        }

        @Test
        @DisplayName("Should construct Power with notation")
        void shouldConstructPowerWithNotation() {
            assertDoesNotThrow(() -> new Power(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(3))
            ), Notation.INFIX));
        }
    }

    @Nested
    @DisplayName("Integer Power Tests")
    class IntegerPowerTests {
        @Test
        @DisplayName("Should calculate positive integer power")
        void shouldCalculatePositiveIntegerPower() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(3))
            ));
            assertEquals(8, power.op(2, 3));
        }

        @Test
        @DisplayName("Should calculate power with base 0")
        void shouldCalculatePowerWithBaseZero() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new IntegerValue(0)),
                    new MyNumber(new IntegerValue(3))
            ));
            assertEquals(0, power.op(0, 3));
        }

        @Test
        @DisplayName("Should calculate power with exponent 0")
        void shouldCalculatePowerWithExponentZero() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new IntegerValue(5)),
                    new MyNumber(new IntegerValue(0))
            ));
            assertEquals(1, power.op(5, 0));
        }
    }

    @Nested
    @DisplayName("Real Power Tests")
    class RealPowerTests {
        @Test
        @DisplayName("Should calculate real number power")
        void shouldCalculateRealPower() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new RealValue(2.0, 5)),
                    new MyNumber(new RealValue(3.0, 5))
            ));
            NumericValue result = power.op(new RealValue(2.0, 5), new RealValue(3.0, 5));
            assertInstanceOf(RealValue.class, result);
            assertEquals("8" +
                    "", result.toString());
        }
    }

    @Nested
    @DisplayName("Complex Power Tests")
    class ComplexPowerTests {
        @Test
        @DisplayName("Should calculate complex number power")
        void shouldCalculateComplexPower() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new ComplexValue(1, 1)),
                    new MyNumber(new IntegerValue(2))
            ));
            NumericValue result = power.op(
                    new ComplexValue(1, 1),
                    new IntegerValue(2)
            );
            assertInstanceOf(ComplexValue.class, result);
        }
    }

    @Nested
    @DisplayName("Rational Power Tests")
    class RationalPowerTests {
        @Test
        @DisplayName("Should calculate rational number power")
        void shouldCalculateRationalPower() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new RationalValue(1, 2)),
                    new MyNumber(new IntegerValue(2))
            ));
            NumericValue result = power.op(
                    new RationalValue(1, 2),
                    new IntegerValue(2)
            );
            assertTrue(result instanceof RationalValue);
            assertEquals("1/4", result.toString());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {
        @Test
        @DisplayName("Should evaluate complete power expression")
        void shouldEvaluateCompletePowerExpression() throws IllegalConstruction {
            Expression powerExpr = new Power(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(3))
            ));
            NumericValue result = calculator.eval(powerExpr);
            assertEquals("8", result.toString());
        }

        @Test
        @DisplayName("Should handle chained power operations")
        void shouldHandleChainedPowerOperations() throws IllegalConstruction {
            Expression powerExpr = new Power(List.of(
                    new Power(List.of(
                            new MyNumber(new IntegerValue(2)),
                            new MyNumber(new IntegerValue(2))
                    )),
                    new MyNumber(new IntegerValue(2))
            ));
            NumericValue result = calculator.eval(powerExpr);
            assertEquals("16", result.toString());
        }
    }

    @Nested
    @DisplayName("Error Cases Tests")
    class ErrorCasesTests {
        @Test
        @DisplayName("Should handle negative exponents")
        void shouldHandleNegativeExponents() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new IntegerValue(2)),
                    new MyNumber(new IntegerValue(-2))
            ));
            NumericValue result = power.op(
                    new IntegerValue(2),
                    new IntegerValue(-2)
            );
            assertEquals("1/4", result.toString());
        }

        @Test
        @DisplayName("Should handle zero base with zero exponent")
        void shouldHandleZeroBaseWithZeroExponent() throws IllegalConstruction {
            Power power = new Power(List.of(
                    new MyNumber(new IntegerValue(0)),
                    new MyNumber(new IntegerValue(0))
            ));
            NumericValue result = power.op(
                    new IntegerValue(0),
                    new IntegerValue(0)
            );
            assertEquals("1", result.toString());
        }
    }
}