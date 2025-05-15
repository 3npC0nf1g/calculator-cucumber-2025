package parser;
import calculator.values.BooleanValue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class BooleanExpressionTest {

    // Test comparaisons simples
    @Test
    public void testSimpleComparisons() {
        assertTrue(get("1 == 1"));
        assertTrue(get("2 != 3"));
        assertTrue(get("4 > 2"));
        assertFalse(get("3 < 1"));
        assertTrue(get("3 >= 3"));
        assertFalse(get("2 <= 1"));
    }

    // Test AND logique
    @Test
    public void testLogicalAnd() {
        assertTrue(get("true AND true"));
        assertFalse(get("true AND false"));
        assertTrue(get("1 == 1 AND 2 > 1"));
        assertFalse(get("1 == 2 AND 2 > 1"));
    }

    // Test OR logique
    @Test
    public void testLogicalOr() {
        assertTrue(get("true OR false"));
        assertFalse(get("false OR false"));
        assertTrue(get("1 < 0 OR 5 == 5"));
        assertFalse(get("1 < 0 OR 2 != 2"));
    }

    // Test NOT logique
    @Test
    public void testLogicalNot() {
        assertFalse(get("NOT true"));
        assertTrue(get("NOT false"));
        assertFalse(get("NOT (3 < 5)"));
        assertTrue(get("NOT (3 > 5)"));
    }

    // Test expressions complexes
    @Test
    public void testComplexExpressions() {
        assertTrue(get("(1 == 1 AND 2 > 1) OR false"));
        assertTrue(get("NOT (2 != 2) AND (5 >= 3)"));
        assertFalse(get("NOT (1 == 1 AND 2 > 1) OR false"));
    }

    // Helper pour parser et obtenir la valeur booléenne
    private boolean get(String expr) {
        try {
            return ((BooleanValue) BooleanExpressionParser.parse(expr)).getValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
