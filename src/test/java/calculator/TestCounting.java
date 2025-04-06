package calculator;

import calculator.values.IntegerValue;

// Import JUnit5 libraries for unit testing:
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class TestCounting {

    private MyNumber value1, value2;
    private Expression e;

    @BeforeEach
    void setUp() {
        value1 = new MyNumber(new IntegerValue(8));
        value2 = new MyNumber(new IntegerValue(6));
        e = null;
    }

    @Test
    void testNumberCounting() {
        e = value1  ;
        // test whether a number has zero depth (i.e. no nested expressions)
        assertEquals(0, e.countDepth(), "Depth of a single number should be 0");
        // test whether a number contains zero operations
        assertEquals(0, e.countOps(), "Number should contain 0 operations");
        // test whether a number contains 1 number
        assertEquals(1, e.countNbs(), "Number should contain 1 number");
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testOperationCounting(String symbol) {
        List<Expression> params = Arrays.asList(value1, value2);
        try {
            // Construct the operation based on the symbol
            switch (symbol) {
                case "+" -> e = new Plus(params);
                case "-" -> e = new Minus(params);
                case "*" -> e = new Times(params);
                case "/" -> e = new Divides(params);
                default -> fail("Invalid operation symbol: " + symbol);
            }
        } catch (IllegalConstruction ex) {
            fail("IllegalConstruction thrown unexpectedly");
        }

        // Test whether a binary operation has depth 1
        assertEquals(1, e.countDepth(), "Depth of a binary operation should be 1");
        // Test whether a binary operation contains 1 operation
        assertEquals(1, e.countOps(), "Binary operation should count as 1 operation");
        // Test whether a binary operation contains 2 numbers
        assertEquals(2, e.countNbs(), "Binary operation should contain 2 numbers");
    }
}
