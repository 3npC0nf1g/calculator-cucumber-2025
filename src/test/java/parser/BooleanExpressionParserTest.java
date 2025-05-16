package parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BooleanExpressionParserTest {

    @Test
    public void testLiteralTrueFalse() throws Exception {
        assertTrue(BooleanExpressionParser.parse("true").getValue());
        assertFalse(BooleanExpressionParser.parse("false").getValue());
    }

    @Test
    public void testNumericConstants() throws Exception {
        assertTrue(BooleanExpressionParser.parse("1").getValue());
        assertFalse(BooleanExpressionParser.parse("0").getValue());
    }

    @Test
    public void testNotOperator() throws Exception {
        assertFalse(BooleanExpressionParser.parse("NOT true").getValue());
        assertTrue(BooleanExpressionParser.parse("!false").getValue());
    }

    @Test
    public void testAndOperator() throws Exception {
        assertTrue(BooleanExpressionParser.parse("true AND true").getValue());
        assertFalse(BooleanExpressionParser.parse("true AND false").getValue());
    }

    @Test
    public void testOrOperator() throws Exception {
        assertTrue(BooleanExpressionParser.parse("true OR false").getValue());
        assertFalse(BooleanExpressionParser.parse("false || false").getValue());
    }

    @Test
    public void testMixedLogicOperators() throws Exception {
        assertTrue(BooleanExpressionParser.parse("true AND false OR true").getValue());
        assertFalse(BooleanExpressionParser.parse("NOT (true OR true)").getValue());
    }

    @Test
    public void testParenthesesPrecedence() throws Exception {
        assertFalse(BooleanExpressionParser.parse("(true AND false) OR (false AND false)").getValue());
        assertTrue(BooleanExpressionParser.parse("(true AND true) OR false").getValue());
    }

    @Test
    public void testNestedParentheses() throws Exception {
        assertTrue(BooleanExpressionParser.parse("((true))").getValue());
        assertFalse(BooleanExpressionParser.parse("!(true && (true || false))").getValue());
    }

    @Test
    public void testEqualityComparison() throws Exception {
        assertTrue(BooleanExpressionParser.parse("3 == 3").getValue());
        assertFalse(BooleanExpressionParser.parse("3 == 4").getValue());
    }

    @Test
    public void testInequalityComparison() throws Exception {
        assertTrue(BooleanExpressionParser.parse("3 != 4").getValue());
        assertFalse(BooleanExpressionParser.parse("3 != 3").getValue());
    }

    @Test
    public void testRelationalComparisons() throws Exception {
        assertTrue(BooleanExpressionParser.parse("5 > 2").getValue());
        assertTrue(BooleanExpressionParser.parse("2 < 3").getValue());
        assertTrue(BooleanExpressionParser.parse("4 >= 4").getValue());
        assertFalse(BooleanExpressionParser.parse("1 >= 2").getValue());
        assertTrue(BooleanExpressionParser.parse("1 <= 1").getValue());
        assertFalse(BooleanExpressionParser.parse("2 < 1").getValue());
    }

    @Test
    public void testCombinedComparisonAndLogic() throws Exception {
        assertTrue(BooleanExpressionParser.parse("(3 < 4) AND (2 == 2)").getValue());
        assertFalse(BooleanExpressionParser.parse("(3 > 4) OR !(2 == 2)").getValue());
    }

    @Test
    public void testInvalidExpressionThrowsException() {
        assertThrows(Exception.class, () -> BooleanExpressionParser.parse("true AND"));
        assertThrows(Exception.class, () -> BooleanExpressionParser.parse("&& false"));
        assertThrows(Exception.class, () -> BooleanExpressionParser.parse("3 >< 4"));
        assertThrows(Exception.class, () -> BooleanExpressionParser.parse("((true OR false)")); // missing closing paren
        assertThrows(Exception.class, () -> BooleanExpressionParser.parse("notABoolean"));
    }
}
