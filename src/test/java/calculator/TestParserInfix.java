package calculator;

import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyInfixParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.MyInfixParser.buildTree;

public class TestParserInfix {

    @Test
    void testAdditionMultiplication() {
        String expr = "((4 + 5 + 6) * (7 + (5 / 2 / 7)) * 9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "993.2142857085");
    }

    @Test
    void testSimpleDivision() {
        String expr = "(10 / 2)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "5");
    }

    @Test
    void testComplexNumberAddition() {
        String expr = "([3+2i] + [1+7i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "[4 + 9i]");
    }

    @Test
    void testComplexNumberMultiplication() {
        String expr = "([1+2i] * [3+4i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "[-5 + 10i]");
    }

    @Test
    void testSinInDegrees() {
        String expr = "sin(30)";
        ExpressionParser.mycalculator.setUseRadians(false); // important : sin en DEGRÉS
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "0.5");
    }

    @Test
    void testCosInDegrees() {
        String expr = "cos(60)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "0.5");
    }

    @Test
    void testTanInDegrees() {
        String expr = "tan(45)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "1");
    }

    @Test
    void testDivisionByZero() {
        String expr = "(5 / 0)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "NaN");
    }

    @Test
    void testComplexDivision() {
        String expr = "([4+2i] / [1-1i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        // (4+2i)/(1-1i) = (4+2i)*(1+1i)/(1+1) = ((4+4i)+(2i-2))/2 = ((2+6i)/2) = (1+3i)
        assertEquals(res.toString(), "[1 + 3i]");
    }

    @Test
    void testNestedExpressions() {
        String expr = "(((1 + 2) * (3 + 4)) / (2 + 5))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "3");
    }

    @Test
    void testZeroTimesComplex() {
        String expr = "(0 * [5+8i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals(res.toString(), "[0]");
    }
}
