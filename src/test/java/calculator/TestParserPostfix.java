package calculator;

import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyPostfixParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParserPostfix {

    // PostFix
    @Test
    void testComplexPostFix() {
        String expr = "((2 ,([1+2i] [3-4i]+), (sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();

        NumericValue res= myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"8.000000000000000000000000000000 + -4.000000000000000000000i");
    }

    @Test
    void testSimpleAdditionComplex() {
        String expr = "([1+2i],[3+4i]+)";
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        assertEquals(res.toString(), "4.0000000000 + 6.0i");
    }

    @Test
    void testSimpleSubtractionComplex() {
        String expr = "([5+5i],[2+3i]-)";
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        assertEquals(res.toString(), "3.0 + 2.0i");
    }

    @Test
    void testMultiplicationComplex() {
        String expr = "([1+2i],[3+4i]*)";
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        assertEquals(res.toString(), "-5.0000000000000000000000 + 10.0000000000000000000000i");
    }

    @Test
    void testDivisionComplex() {
        String expr = "([1+2i],[3+4i]/)";
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        assertEquals(res.toString(), "0.44 + 0.08i");
    }

    @Test
    void testMixedRealAndComplexNoComma() {
        String expr = "((2 ([1+2i] [3-4i]+) (sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        assertEquals(res.toString(), "8.000000000000000000000000000000 + -4.000000000000000000000i");
    }

    @Test
    void testMixedRealAndComplexWithComma() {
        String expr = "((2,([1+2i] [3-4i]+),(sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        assertEquals(res.toString(), "8.000000000000000000000000000000 + -4.000000000000000000000i","((2,([1+2i],[3-4i]+),(sin(30)),(cos(60))+)*))");
    }

    //
    @Test
    void testComplexChainOperations() {
        String expr = "((([2+3i],[1-1i]+),([4+0i],[2+2i]+)*),(([5+0i],[0+2i]-)) +)";
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res = myPostfixParser.evaluate(expr);
        // À toi d'ajuster ici selon ce que tu attends exactement comme résultat
        assertEquals(res.toString(), "4.0 + 6.0i");
        System.out.println("Résultat: " + res);
    }


    @Test
    void testComplexPostFixComa() {
        String expr = "((2 ([1+2i] [3-4i]+) (sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res= myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"8.000000000000000000000000000000 + -4.000000000000000000000i");
    }

    @Test
    void testEasyPostFix() {
        String expr = "3 2 *";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res= myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"6");
    }
    @Test
    void testEasyPostfix() {
        String expr = "2 3 *";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res= myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"6");
    }

    @Test
    void testEasyPostfix2() {
        String expr = "((4,5,6)+,(7,(5,2,7)/)+,9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res=myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"993.2142857085");
    }


    @Test
    void testExempleComaPostFix() {
        String expr = "((4,5,6)+,(7,(5,2,7)/)+,9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res= myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"993.2142857085");
    }

    @Test
    void testExemplePostFix() {
        String expr = "((4 5 6)+ (7 (5 2 7)/)+9)";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        NumericValue res= myPostfixParser.evaluate(expr);
        assertEquals(res.toString(),"993.2142857085");
    }
}
