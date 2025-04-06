package calculator;


import static org.junit.jupiter.api.Assertions.*;

import calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAddition() {
        String result = calculatorService.getRep("+", 2, 3);
        assertEquals("5", result, "2 + 3 should equal 5");
    }

    @Test
    void testSubtraction() {
        String result = calculatorService.getRep("-", 10, 4);
        assertEquals("6", result, "10 - 4 should equal 6");
    }

    @Test
     void testMultiplication() {
        String result = calculatorService.getRep("*", 3, 4);
        assertEquals("12", result, "3 * 4 should equal 12");
    }

    @Test
    void testDivision() {
        String result = calculatorService.getRep("/", 8, 2);
        assertEquals("4", result, "8 / 2 should equal 4");
    }

    @Test
    void testInvalidOperator() {
        String result = calculatorService.getRep("?", 8, 2);
        assertTrue(result.contains("Error: Invalid operator"), "Unsupported operator should return an error");
    }

    @Test
    void testDivisionByZero() {
        String result = calculatorService.getRep("/", 8, 0);
        assertTrue(result.contains("Error"), "Division by zero should return an error");
    }
}
