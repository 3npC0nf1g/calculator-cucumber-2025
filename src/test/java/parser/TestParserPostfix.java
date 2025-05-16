package parser;


import calculator.values.ComplexValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyPostfixParser;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParserPostfix {

    // PostFix
    @Test
    void testComplexPostFix() {
        String expr = "((2 ,([1+2i] [3-4i]+), (sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();

        assertThrows(RuntimeException.class, () ->  myPostfixParser.evaluate(expr));


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
        ComplexValue complexValue = (ComplexValue) res;
        assertEquals(complexValue.getReal().intValue(), new BigDecimal(-5).intValue());
        assertEquals(complexValue.getImag().intValue(), new BigDecimal(10).intValue());
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
        assertThrows(RuntimeException.class, () ->  myPostfixParser.evaluate(expr));

    }

    @Test
    void testMixedRealAndComplexWithComma() {
        String expr = "((2,([1+2i] [3-4i]+),(sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();

        assertThrows(RuntimeException.class, () ->  myPostfixParser.evaluate(expr));

    }



    @Test
    void testComplexPostFixComa() {
        String expr = "((2 ([1+2i] [3-4i]+) (sin(30) cos(60)+)*))";
        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();
        assertThrows(RuntimeException.class, () ->  myPostfixParser.evaluate(expr));

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
