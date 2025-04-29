package calculator;

import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;

import static org.junit.jupiter.api.Assertions.*;

public class TestNotationParser {

    private final ExpressionParser parser = new ExpressionParser();

    private void assertNotation(String expectedNotation, String input) throws Exception {
        String cleanedInput = input.trim().replaceAll(",", " ");
        //System.out.println("\nTesting: " + cleanedInput);

        if (parser.isPrefix(cleanedInput)) {
            //System.out.println("Detected: Prefix");
            parser.parse(cleanedInput);
            assertEquals(expectedNotation, "Prefix");
        } else if (parser.isInfix(cleanedInput)) {
            //System.out.println("Detected: Infix");
            parser.parse(cleanedInput);
            assertEquals(expectedNotation, "Infix");
        } else {
            //System.out.println("Detected: Postfix");
            parser.parse(cleanedInput);
            assertEquals(expectedNotation, "Postfix");
        }
    }

    @Test
    public void testPostfix1() throws Exception {
        assertNotation("Postfix", "((([2+3i],[1-1i]+),([4+0i],[2+2i]+)*),(([5+0i],[0+2i]-)) +)");
    }

    @Test
    public void testPostfix2() throws Exception {
        assertNotation("Postfix", "((2 ([1+2i] [3-4i]+) (sin(30) cos(60)+)*))");
    }

    @Test
    public void testPostfix3() throws Exception {
        assertNotation("Postfix", "((4,5,6)+,(7,(5,2,7)/)+,9)");
    }

    @Test
    public void testPrefix1() throws Exception {
        assertNotation("Prefix", "(+ (* (+ [2+3i] [1-1i]) (+ [4+0i] [2+2i])) (- [5+0i] [0+2i]))");
    }

    @Test
    public void testPrefix2() throws Exception {
        assertNotation("Prefix", "(+(4,5,6),+(7,/(5,2,7)),9)");
    }

    @Test
    public void testInfix1() throws Exception {
        assertNotation("Infix", "((4 + 5 + 6) * (7 + (5 / 2 / 7)) * 9)");
    }

    @Test
    public void testInfix2() throws Exception {
        assertNotation("Infix", "(((1 + 2) * (3 + 4)) / (2 + 5))");
    }

    @Test
    public void testPostfix4() throws Exception {
        assertNotation("Postfix", "((3,4,5)+,2*)");
    }

    @Test
    public void testPostfix5() throws Exception {
        assertNotation("Postfix", "(sin(30),cos(60)+,(5,2)/");
    }

    @Test
    public void testPostfix6() throws Exception {
        assertNotation("Postfix", "((1,2)+,(3,4)+)*");
    }

    @Test
    public void testPostfix7() throws Exception {
        assertNotation("Postfix", "((([5+3i],[2-2i]+),([1+0i],[3+4i]+)*)*)");
    }

    @Test
    public void testPrefix3() throws Exception {
        assertNotation("Prefix", "(+ 3 4 5)");
    }

    @Test
    public void testPrefix4() throws Exception {
        assertNotation("Prefix", "(* (+ sin(30) cos(60)) (/ 5 2))");
    }

    @Test
    public void testPrefix5() throws Exception {
        assertNotation("Prefix", "(* (+ 1 2) (+ 3 4))");
    }

    @Test
    public void testPrefix6() throws Exception {
        assertNotation("Prefix", "(* (* (+ [5+3i] [2-2i]) (+ [1+0i] [3+4i])) (+ [2+2i] [1-1i]))");
    }

    @Test
    public void testInfix3() throws Exception {
        assertNotation("Infix", "((3 + 4 + 5) * 2)");
    }

    @Test
    public void testInfix4() throws Exception {
        assertNotation("Infix", "(sin(30) + cos(60) + (5 / 2))");
    }

    @Test
    public void testInfix5() throws Exception {
        assertNotation("Infix", "((1 + 2) * (3 + 4))");
    }

    @Test
    public void testInfix6() throws Exception {
        assertNotation("Infix", "([5+3i] + [2-2i]) * ([1+0i] + [3+4i])");
    }
}
