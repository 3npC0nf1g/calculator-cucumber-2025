package calculator;

import calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestApi {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testSimpleAddition() {
        assertEquals("5", calculatorService.getRep("2 + 3"));
    }

    @Test
    void testSimpleSubtraction() {
        assertEquals("1", calculatorService.getRep("4 - 3"));
    }

    @Test
    void testSimpleMultiplication() {
        assertEquals("6", calculatorService.getRep("2 * 3"));
    }

    @Test
    void testSimpleDivision() {
        assertEquals("2", calculatorService.getRep("6 / 3"));
    }

    @Test
    void testParentheses() {
        assertEquals("14", calculatorService.getRep("2 * (3 + 4)"));
    }

//    @Test
//    void testModulus() {
//        assertEquals("1", calculatorService.getRep("7 % 2"));
//    }
//
//    @Test
//    void testExponentiation() {
//        assertEquals("9", calculatorService.getRep("3^2"));
//    }

    @Test
    void testNestedParentheses() {
        assertEquals("20", calculatorService.getRep("((2 + 3) * (4))"));
    }

    @Test
    void testDecimalHandling() {
        assertEquals("5.5", calculatorService.getRep("2.5 + 3"));
    }

    @Test
    void testNegativeNumbers() {

        assertEquals("-1", calculatorService.getRep("2 - 3"));

    }

    @Test
    void testInvalidExpression() {
        String result = calculatorService.getRep("2 + * 3");
        assertTrue(result.startsWith("Error in getRep"));
    }
}
