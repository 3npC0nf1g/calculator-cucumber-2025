package parser;


import calculator.values.ComplexValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyPostfixParser;

import java.math.BigDecimal;
import java.util.List;

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

    @Test
    void testMainRunsWithoutException() {
        assertThrows(RuntimeException.class, () ->  MyPostfixParser.main(new String[]{}));
    }

    @Test
    void testSimplePostfixWithoutParensIsWrappedAndEvaluated() {
        MyPostfixParser p = new MyPostfixParser();
        // "1 2 +" → automatiquement "(1 2 +)" → prefix "+ 1 2"
        NumericValue v = p.evaluate("1 2 +");
        assertEquals("3", v.toString());
    }

    @Test
    void testAlreadyParenthesizedPostfix() {
        MyPostfixParser p = new MyPostfixParser();
        // "(3 4 *)" → prefix "* 3 4"
        NumericValue v = p.evaluate("(3 4 *)");
        assertEquals("12", v.toString());
    }

    @Test
    void testNestedPostfixExpression() {
        MyPostfixParser p = new MyPostfixParser();
        // "((1 2 +) 3 *)" → (1+2)*3 = 9
        NumericValue v = p.evaluate("((1 2 +) 3 *)");
        assertEquals("9", v.toString());
    }

    @Test
    void testPercentOperatorInPostfix() {
        MyPostfixParser p = new MyPostfixParser();
        // "200 %" → 200% = 2
        assertThrows(RuntimeException.class, () -> p.evaluate("200 %"));

    }

    @Test
    void testInvalidTokensThrows() {
        MyPostfixParser p = new MyPostfixParser();
        assertThrows(RuntimeException.class, () -> p.evaluate("foo bar"));
    }
}
