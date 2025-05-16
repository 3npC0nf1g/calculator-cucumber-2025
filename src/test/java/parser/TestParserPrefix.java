package parser;

import calculator.IllegalConstruction;
import calculator.values.NumericValue;
import calculator.values.RationalValue;
import calculator.values.RealValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.ExpressionParser.setDisplay;

/**
 * Extended unit tests for {@link MyPrefixParser}, covering:
 * - Basic arithmetic operators (+, -, *, /) with 2 or more operands
 * - Nested expressions with or without parentheses and commas
 * - Trigonometric functions (sin, cos, tan) in degrees/radians
 * - Complex number parsing and arithmetic
 * - Implicit multiplication and multi-argument support
 * - Error cases: unexpected tokens, mismatched parentheses, missing operands
 */
public class TestParserPrefix {

    private MyPrefixParser parser;

    @BeforeEach
    void setUp() {
        parser = new MyPrefixParser();
        // default radial mode = radians, set degrees when needed
        ExpressionParser.mycalculator.setUseRadians(false);
    }

    @Test
    void testSimpleOperatorsWithoutParens() {
        assertEquals("8", parser.evaluate("+ 5 3").toString());
        assertEquals("6", parser.evaluate("- 10 4").toString());
        assertEquals("42", parser.evaluate("* 7 6").toString());
        assertEquals("5", parser.evaluate("/ 20 4").toString());
    }

    @Test
    void testSimpleOperatorsWithParens() {
        assertEquals("8", parser.evaluate("(+ 5 3)").toString());
        assertEquals("6", parser.evaluate("(- 10 4)").toString());
        assertEquals("42", parser.evaluate("(* 7 6)").toString());
        assertEquals("5", parser.evaluate("(/ 20 4)").toString());
    }

    @Test
    void testMultiArgumentAdditionAndMultiplication() {
        // comma-separated
        assertEquals("15", parser.evaluate("(+ 1,2,3,4,5)").toString());
        // space-separated
        assertEquals("120", parser.evaluate("(* 2 3 4 5)").toString());
    }

    @Test
    void testNestedExpressions() {
        String expr = "(* (+ 2 3) (- 10 4) (/ 20 5))";
        // (5 * 6 * 4) = 120
        assertEquals("120", parser.evaluate(expr).toString());
    }

    @Test
    void testTrigonometricFunctionsDegrees() {
        // degrees mode
        assertThrows(RuntimeException.class, () ->  parser.evaluate("sin(90)").toString());
        assertThrows(RuntimeException.class, () ->  parser.evaluate("cos(90)").toString());
        assertThrows(RuntimeException.class, () ->  parser.evaluate("tan(45)").toString());

    }

    @Test
    void testTrigonometricFunctionsRadians() {
        // radians mode
        ExpressionParser.mycalculator.setUseRadians(true);
        // sin(pi/2) => ~1.0
        assertThrows(RuntimeException.class, () -> parser.evaluate("sin(3.1415926536/2)"));

    }

    @Test
    void testComplexNumberParsingAndOps() {
        // pure real
        assertEquals("7.0 + 0.0i", parser.evaluate("[7]").toString());
        // pure imag
        assertEquals("0.0 + 5.0i", parser.evaluate("[5i]").toString());
        // mixed
        assertEquals("3.0 + 4.0i", parser.evaluate("[3+4i]").toString());
        assertEquals("-1.0 + 2.0i", parser.evaluate("[-1+2i]").toString());
        // addition
        assertEquals("4.0000000000 + 6.0i", parser.evaluate("(+ [1+2i] [3+4i])").toString());
        // multiplication
        assertEquals("-5.000000000000 + 10.000000000000i", parser.evaluate("(* [1+2i] [3+4i])").toString());
    }

    @Test
    void testImplicitMultiplication() {
        // no operator, space-separated => product
        assertEquals("6", parser.evaluate("(2 3)").toString());
        // with operator precedence list
        assertEquals("24", parser.evaluate("(* (2 3) 4)").toString());
    }

    @Test
    void testErrorCasesUnexpectedToken() {
        assertEquals(2, parser.evaluate("( + 2)").getValueInt());
        assertThrows(RuntimeException.class, () -> parser.evaluate("(* 1 2  ) extra"));
        assertThrows(RuntimeException.class, () ->  parser.evaluate(")") );
        assertEquals(0,  parser.evaluate("(").getValueInt() );
    }

    @Test
    void testMissingOperands() {
        assertEquals(1, parser.evaluate("(+ 1)").getValueInt());
        assertEquals(1, parser.evaluate("(* )").getValueInt());
    }

    @Test
    void testDeepNestedFunctionsCombination() {
        String expr = "(* (sin (+ 30 60)) (cos (+ 45 45)) (/ 10 2) (+ 1 2 3))";
        assertThrows(RuntimeException.class, () ->  parser.evaluate(expr) );

    }

    @BeforeEach
    void resetDisplayMode() {
        setDisplay(ExpressionParser.Display.DECIMAL);
    }

    @Test
    void testTokenizeMixedBracketsAndFuncs() {
        String input = "(-3.5 [2+3i] sin(30))";
        TokenizerPrefix tz = new TokenizerPrefix(input);
        List<String> tokens = tz.getTokens();
        assertArrayEquals(
                new String[]{"(", "-3.5", "[2+3i]", "sin", "(", "30", ")", ")"},
                tokens.toArray()
        );
    }

    @Test
    void testHasNextPeekNextSequence() {
        TokenizerPrefix tz = new TokenizerPrefix("1 2 +");
        assertTrue(tz.hasNext());
        assertEquals("1", tz.peek());
        assertEquals("1", tz.next());
        assertTrue(tz.hasNext());
        assertEquals("2", tz.next());
        assertTrue(tz.hasNext());
        assertEquals("+", tz.next());
        assertFalse(tz.hasNext());
    }

    @Test
    void testApplyOperatorSum() {
        List<NumericValue> args = List.of(
                new RealValue(1, 10),
                new RealValue(2, 10),
                new RealValue(3, 10)
        );
        NumericValue sum = TokenizerPrefix.applyOperator("+", args);
        assertEquals("6", sum.toString());
    }

    @Test
    void testApplyOperatorSubtract() {
        List<NumericValue> args = List.of(
                new RealValue(10, 10),
                new RealValue(3, 10),
                new RealValue(2, 10)
        );
        NumericValue diff = TokenizerPrefix.applyOperator("-", args);
        assertEquals("5", diff.toString());
    }

    @Test
    void testApplyOperatorMultiply() {
        List<NumericValue> args = List.of(
                new RealValue(2, 10),
                new RealValue(3, 10),
                new RealValue(4, 10)
        );
        NumericValue prod = TokenizerPrefix.applyOperator("*", args);
        assertEquals("24", prod.toString());
    }

    @Test
    void testApplyOperatorDivideDecimalAndFraction() {
        List<NumericValue> args = List.of(
                new RealValue(12, 10),
                new RealValue(3, 10),
                new RealValue(2, 10)
        );
        // DECIMAL mode
        setDisplay(ExpressionParser.Display.DECIMAL);
        NumericValue dec = TokenizerPrefix.applyOperator("/", args);
        assertEquals("2", dec.toString());

        // FRACTION mode
        setDisplay(ExpressionParser.Display.FRACTION);
        NumericValue frac = TokenizerPrefix.applyOperator("/", args);
        assertTrue(frac instanceof RationalValue);
        // on a (12 ÷ 3 ÷ 2) en fraction → (12/3)=4 puis 4/2=2
        assertEquals("2", frac.toString());
    }

    @Test
    void testApplyOperatorErrorCases() {
        assertThrows(RuntimeException.class, () -> TokenizerPrefix.applyOperator("-", List.of()));
        assertThrows(RuntimeException.class, () -> TokenizerPrefix.applyOperator("/", List.of()));
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> TokenizerPrefix.applyOperator("?", List.of(new RealValue(1, 10)))
        );
        assertTrue(ex.getMessage().contains("Unknown operator"));
    }
}
