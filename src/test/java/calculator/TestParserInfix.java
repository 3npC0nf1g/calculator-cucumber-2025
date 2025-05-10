package calculator;

import calculator.values.NumericValue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyInfixParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.MyInfixParser.buildTree;

public class TestParserInfix {

    @Test
    void testAdditionMultiplication() throws IllegalConstruction {
        String expr = "((4 + 5 + 6) * (7 + (5 / 2 / 7)) * 9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("993.2142857085", res.toString());
    }

    @Test
    void testSimpleDivision() throws IllegalConstruction{
        String expr = "(10 / 2)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("5", res.toString());
    }

    @Test
    void testComplexNumberAddition() throws IllegalConstruction{
        String expr = "([3+2i] + [1+7i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("4.0 + 9.0i", res.toString());
    }

    @Test
    void testComplexNumberMultiplication()throws IllegalConstruction {
        String expr = "([1+2i] * [3+4i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("-5.00 + 10.00i", res.toString());
    }

    @Test
    void testSinInDegrees() throws IllegalConstruction{
        String expr = "sin(30)";
        ExpressionParser.mycalculator.setUseRadians(false); // important : sin en DEGRÉS
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("0.5", res.toString());
    }

    @Test
    void testCosInDegrees() throws IllegalConstruction{
        String expr = "cos(60)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("0.5", res.toString());
    }

    @Test
    void testTanInDegrees() throws IllegalConstruction{
        String expr = "tan(45)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("1", res.toString());
    }

    @Test
    void testDivisionByZero() throws IllegalConstruction{
        String expr = "(5 / 0)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("NaN", res.toString());
    }

    @Test
    void testComplexDivision()throws IllegalConstruction {
        String expr = "([4+2i] / [1-1i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        // (4+2i)/(1-1i) = (4+2i)*(1+1i)/(1+1) = ((4+4i)+(2i-2))/2 = ((2+6i)/2) = (1+3i)
        assertEquals("1.00 + 3.00i", res.toString());
    }

    @Test
    void testNestedExpressions() throws IllegalConstruction{
        String expr = "(((1 + 2) * (3 + 4)) / (2 + 5))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("3", res.toString());
    }

    @Test
    void testZeroTimesComplex() throws IllegalConstruction{
        String expr = "(0 * [5+8i])";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyInfixParser.Node root = buildTree(expr);
        NumericValue res = MyInfixParser.evaluate(root);
        assertEquals("0E-11 + 0E-11i", res.toString());
    }
}
