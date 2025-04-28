package calculator;

import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyPrefixParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestParserPrefix {


    @Test
    void testComplexPrefix() {
        String expr = "(* 2 (+ [1+2i] [3-4i]) (+ (sin 30) (cos 60)))";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "[8 - 4i]");
    }

    @Test
    void testEasyPrefix() {
        String expr = "* (+ 2 2) 3";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "12");
    }

    @Test
    void testEasyPrefix2() {
        String expr = "(* (+ 2 2) 3)";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "12");
    }

    @Test
    void testExempleComaPrefix() {
        String expr = "(+(4,5,6),+(7,/(5,2,7)),9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "993.2142857085");
    }

    @Test
    void testExemplePrefix() {
        String expr = "(+(4 5 6) (+ 7 (/ (5 2 7))) 9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "993.2142857085");
    }

    // --- NOUVEAUX TESTS ---

    @Test
    void testSimpleAddition() {
        String expr = "+ 5 3";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "8");
    }

    @Test
    void testSimpleSubtraction() {
        String expr = "- 10 4";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "6");
    }

    @Test
    void testSimpleMultiplication() {
        String expr = "* 7 6";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "42");
    }

    @Test
    void testSimpleDivision() {
        String expr = "/ 20 4";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "5");
    }

    @Test
    void testSinusCosinusAddition() {
        String expr = "+ (sin 30) (cos 60)";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "1");
    }

    @Test
    void testComplexOperations() {
        String expr = "* (+ 2 [3+4i]) (- [5+6i] 1)";
        NumericValue res = MyPrefixParser.evaluate(expr);
        System.out.println("Résultat complexe : " + res);
    }

    @Test
    void testMultipleMultiplications() {
        String expr = "* 2 3 4 5";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "120");
    }

    @Test
    void testNestedComplexOperations() {
        String expr = "* (+ [2+2i] [1+1i]) (+ [3-3i] [1-1i])";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "[24]");
    }

    @Test
    void testDeepNestedFunctions() {
        String expr = "(* (sin (+ 30 60)) (cos (+ 45 45)))";
        ExpressionParser.mycalculator.setUseRadians(false);
        NumericValue res = MyPrefixParser.evaluate(expr);
        System.out.println("Résultat : " + res);
    }

    @Test
    void testCombinationComplexReal() {
        String expr = "+ 5 [2+3i]";
        NumericValue res = MyPrefixParser.evaluate(expr);
        assertEquals(res.toString(), "[7 + 3i]");
    }
}
