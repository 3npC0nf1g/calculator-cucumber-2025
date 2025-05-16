package parser;

import calculator.values.BooleanValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.BooleanExpressionEvaluator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BooleanExpressionTest {

    @Test
    public void testEvaluatePostfix() throws Exception {
        // Equivalent to: !(true && false || true) → !(false || true) → !true → false
        List<String> postfix = List.of("true", "false", "&&", "true", "||", "!");
        assertFalse(BooleanExpressionEvaluator.evaluatePostfix(postfix));
    }



    @Test
    public void testToPostfix() throws Exception {
        List<String> tokens = List.of("true", "&&", "false", "||", "!");
        List<String> postfix = BooleanExpressionEvaluator.toPostfix(tokens);
        assertEquals(List.of("true", "false", "&&", "!","||"), postfix);
    }


    @Test
    public void testToBoolean() throws Exception {
        assertTrue(BooleanExpressionEvaluator.toBoolean(true));         // Boolean true
        assertFalse(BooleanExpressionEvaluator.toBoolean(false));       // Boolean false
        assertTrue(BooleanExpressionEvaluator.toBoolean(1.0));          // Double != 0.0
        assertFalse(BooleanExpressionEvaluator.toBoolean(0.0));         // Double == 0.0
        assertThrows(Exception.class, () -> BooleanExpressionEvaluator.toBoolean("notABool")); // Invalid type
    }


    @Test
    public void testTokenize() {
        String expression = "(true AND false) OR true";
        List<String> tokens = BooleanExpressionEvaluator.tokenize(expression);
        assertEquals(List.of("(", "true", "&&", "false", ")", "||", "true"), tokens);
    }




    @Test
    public void testParseBooleanToken() throws Exception {
        assertTrue(BooleanExpressionEvaluator.parseBooleanToken("true"));
        assertFalse(BooleanExpressionEvaluator.parseBooleanToken("false"));
    }



    @Test
    public void testIsLogicalOperator() {
        assertTrue(BooleanExpressionEvaluator.isLogicalOperator("&&"));
        assertTrue(BooleanExpressionEvaluator.isLogicalOperator("||"));
        assertTrue(BooleanExpressionEvaluator.isLogicalOperator("!"));
    }

    @Test
    public void testIsComparisonOperator() {
        assertTrue(BooleanExpressionEvaluator.isComparisonOperator(">"));
        assertTrue(BooleanExpressionEvaluator.isComparisonOperator(">="));
        assertTrue(BooleanExpressionEvaluator.isComparisonOperator("=="));
        assertTrue(BooleanExpressionEvaluator.isComparisonOperator(">"));
        assertTrue(BooleanExpressionEvaluator.isComparisonOperator("<="));
        assertTrue(BooleanExpressionEvaluator.isComparisonOperator("!="));
    }

    @Test
    public void testIsBoolean() {
        assertTrue(BooleanExpressionEvaluator.isBoolean("true"));
        assertTrue(BooleanExpressionEvaluator.isBoolean("FALSE"));
        assertFalse(BooleanExpressionEvaluator.isBoolean("maybe"));
    }

    @Test
    public void testIsNumeric() {
        assertTrue(BooleanExpressionEvaluator.isNumeric("123"));
        assertTrue(BooleanExpressionEvaluator.isNumeric("3.14"));
        assertFalse(BooleanExpressionEvaluator.isNumeric("abc"));
    }
    @Test
    public void testEvaluateComparison() {
        assertTrue(BooleanExpressionEvaluator.evaluateComparison(5.0, 3.0, ">"));
        assertFalse(BooleanExpressionEvaluator.evaluateComparison(2.0, 2.0, "<"));
        assertTrue(BooleanExpressionEvaluator.evaluateComparison(2.0, 2.0, "=="));
        assertTrue(BooleanExpressionEvaluator.evaluateComparison(4.0, 2.0, ">="));
        assertTrue(BooleanExpressionEvaluator.evaluateComparison(2.0, 8.0, "<="));
        assertFalse(BooleanExpressionEvaluator.evaluateComparison(2.0, 2.0, "!="));
    }
    @Test
    public void testConstructor() {
        new BooleanExpressionEvaluator(); // Just to cover the default constructor
    }
}
