package parser;
import calculator.values.BooleanValue;
import parser.BooleanExpressionParser;

public class BooleanExpressionTest {

    public static void main(String[] args) {
        testSimpleComparison();
        testLogicalAnd();
        testLogicalOr();
        testLogicalNot();
        testComplexExpression();
    }

    // 1. Test des comparaisons simples (==, !=, >, <, >=, <=)
    public static void testSimpleComparison() {
        try {
            System.out.println("== Test: Comparaisons simples ==");
            System.out.println("1 == 1 → " + BooleanExpressionParser.parse("1 == 1").getValue()); // true
            System.out.println("2 != 3 → " + BooleanExpressionParser.parse("2 != 3").getValue()); // true
            System.out.println("4 > 2 → " + BooleanExpressionParser.parse("4 > 2").getValue());   // true
            System.out.println("3 < 1 → " + BooleanExpressionParser.parse("3 < 1").getValue());   // false
            System.out.println("3 >= 3 → " + BooleanExpressionParser.parse("3 >= 3").getValue()); // true
            System.out.println("2 <= 1 → " + BooleanExpressionParser.parse("2 <= 1").getValue()); // false
        } catch (Exception e) {
            System.out.println("Erreur (testSimpleComparison): " + e.getMessage());
        }
    }

    // 2. Test du ET logique (AND)
    public static void testLogicalAnd() {
        try {
            System.out.println("\n== Test: AND logique ==");
            System.out.println("true AND true → " + BooleanExpressionParser.parse("true AND true").getValue()); // true
            System.out.println("true AND false → " + BooleanExpressionParser.parse("true AND false").getValue()); // false
            System.out.println("1 == 1 AND 2 > 1 → " + BooleanExpressionParser.parse("1 == 1 AND 2 > 1").getValue()); // true
        } catch (Exception e) {
            System.out.println("Erreur (testLogicalAnd): " + e.getMessage());
        }
    }

    // 3. Test du OU logique (OR)
    public static void testLogicalOr() {
        try {
            System.out.println("\n== Test: OR logique ==");
            System.out.println("false OR true → " + BooleanExpressionParser.parse("false OR true").getValue()); // true
            System.out.println("false OR false → " + BooleanExpressionParser.parse("false OR false").getValue()); // false
            System.out.println("1 < 0 OR 5 == 5 → " + BooleanExpressionParser.parse("1 < 0 OR 5 == 5").getValue()); // true
        } catch (Exception e) {
            System.out.println("Erreur (testLogicalOr): " + e.getMessage());
        }
    }

    // 4. Test du NON logique (NOT)
    public static void testLogicalNot() {
        try {
            System.out.println("\n== Test: NOT logique ==");
            System.out.println("NOT true → " + BooleanExpressionParser.parse("NOT true").getValue()); // false
            System.out.println("NOT false → " + BooleanExpressionParser.parse("NOT false").getValue()); // true
            System.out.println("NOT (3 < 5) → " + BooleanExpressionParser.parse("NOT (3 < 5)").getValue()); // false
        } catch (Exception e) {
            System.out.println("Erreur (testLogicalNot): " + e.getMessage());
        }
    }

    // 5. Test des expressions complexes
    public static void testComplexExpression() {
        try {
            System.out.println("\n== Test: Expression complexe ==");
            System.out.println("(1 == 1 AND 2 > 1) OR false → " +
                    BooleanExpressionParser.parse("(1 == 1 AND 2 > 1) OR false").getValue()); // true

            System.out.println("NOT (2 != 2) AND (5 >= 3) → " +
                    BooleanExpressionParser.parse("NOT (2 != 2) AND (5 >= 3)").getValue()); // true
        } catch (Exception e) {
            System.out.println("Erreur (testComplexExpression): " + e.getMessage());
        }
    }
}
