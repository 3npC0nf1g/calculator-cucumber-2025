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
    @Test
     void testGetComplexRepAddition() {
        String result = calculatorService.getComplexRep("+", 2, 3, 4, 5);
        // Adjust the expected value string based on the output format of ComplexValue.toString()
        assertEquals("6.0 + 8.0i", result, "Complex addition should return 6.00+8.00i");
    }
    @Test
     void testGetComplexRepDivision() {
        String result = calculatorService.getComplexRep("/", 10, 5, 2, 1);
        // Adjust the expected string if your ComplexValue.toString() method formats differently
        assertEquals("5.00 + 0.00i", result, "Complex division should return 5.00+0.00i");
    }
    @Test
    void testGetComplexRepSubtraction() {
        String result = calculatorService.getComplexRep("-", 6, 3, 2, 1);
        // Adjust the expected value string based on the output format of ComplexValue.toString()
        assertEquals("4.0 + 2.0i", result, "Complex addition should return 4.0+2.0i");
    }

    @Test
    void testGetRealRepSubtraction() {
        String result = calculatorService.getRealRep("-", 6.0, 3.2);
        assertEquals("2.8", result, "Real subtraction should return 2.8");
    }
    @Test
    void testGetRealRepAddition() {
        String result = calculatorService.getRealRep("+", 6.0, 3.2);
        assertEquals("9.2", result, "Real addition should return 9.2");
    }
    @Test
    void testGetRealMultiplication() {
        String result = calculatorService.getRealRep("*", 6.0, 3.2);
        assertEquals("19.2", result, "Real multiplication should return 19.2");
    }
    @Test
    void testGetRealDivision() {
        String result = calculatorService.getRealRep("/", 2.5, 5);
        assertEquals("0.5", result, "Real division should return 0.5");
    }

    @Test
    void testGetRationalSubtraction() {
        String result = calculatorService.getRationalRep("-", 3, 2,3,4);
        assertEquals("3/4", result, "Real subtraction should return 3/4");
    }
    @Test
    void testGetRationalAddition() {
        String result = calculatorService.getRationalRep("+", 3, 2,3,4);
        assertEquals("9/4", result, "Real addition should return 9/4");
    }
    @Test
    void testGetRationalMultiplication() {
        String result = calculatorService.getRationalRep("*", 3, 2,3,4);
        assertEquals("9/8", result, "Real multiplication should return 9/8");
    }

    @Test
    void testGetRationalDivision() {
        String result = calculatorService.getRationalRep("/", 3, 2,3,4);
        assertEquals("2", result, "Real multiplication should return 2");
    }


    @Test
    void testDegreeToRadian() {
        String result = calculatorService.getFromDegreeToRadian(90.0);
        assertEquals("1.5707963267948966", result, "Real multiplication should return 1.5707963267948966");
    }
    @Test
    void testRadianToDegree() {
        String result = calculatorService.getFromRadianToDegree(Math.PI);
        assertEquals("180.0", result, "Real multiplication should return 180.0");
    }


}
