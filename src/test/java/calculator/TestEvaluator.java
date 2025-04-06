package calculator;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;

// Import JUnit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

class TestEvaluator {

    private Calculator calc;
    private NumericValue value1, value2;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        value1 = new IntegerValue(8);
        value2 = new IntegerValue(6);
    }

    @Test
    void testEvaluatorMyNumber() {
        assertEquals(value1, calc.eval(new MyNumber(value1)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(
                new MyNumber(value1),
                new MyNumber(value2)
        );

        try {
            NumericValue expected = switch (symbol) {
                case "+" -> value1.add(value2);
                case "-" -> value1.subtract(value2);
                case "*" -> value1.multiply(value2);
                case "/" -> value1.divide(value2);
                default -> throw new IllegalArgumentException("Unsupported operation: " + symbol);
            };

            Expression op = switch (symbol) {
                case "+" -> new Plus(params);
                case "-" -> new Minus(params);
                case "*" -> new Times(params);
                case "/" -> new Divides(params);
                default -> throw new IllegalArgumentException("Unsupported operation: " + symbol);
            };

            assertEquals(expected, calc.eval(op), "Evaluation failed for operation: " + symbol);

        } catch (IllegalConstruction e) {
            fail("Unexpected IllegalConstruction: " + e.getMessage());
        }
    }
}
