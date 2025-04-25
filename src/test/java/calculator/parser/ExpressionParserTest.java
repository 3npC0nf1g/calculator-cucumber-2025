package calculator.parser;

import org.antlr.v4.runtime.tree.*;
import calculator.parser.expression.ExpressionParserParser;
import calculator.parser.expression.ExpressionParserLexer;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class ExpressionParserTest {

    private void parse(String input) {
        ExpressionParserLexer lexer = new ExpressionParserLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParserParser parser = new ExpressionParserParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.entry(); // Entry rule
        System.out.println("Input: " + input);
        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }

    // ===== INFIX TESTS =====

    @Test
    public void testInfixMulDiv() {
        assertDoesNotThrow(() -> parse("3 * 4 / 2"));
    }

    @Test
    public void testInfixAddSub() {
        assertDoesNotThrow(() -> parse("5 + 2 - 3"));
    }

    @Test
    public void testInfixIntegerPositive() {
        assertDoesNotThrow(() -> parse("42"));
    }

    @Test
    public void testInfixIntegerNegative() {
        assertDoesNotThrow(() -> parse("-7"));
    }

    @Test
    public void testInfixGrouped() {
        assertDoesNotThrow(() -> parse("(3 + 2)"));
    }

    // ===== PREFIX TESTS =====

    @Test
    public void testPrefixMulDiv() {
        assertDoesNotThrow(() -> parse("*(3, 4, 2)"));
    }

    @Test
    public void testPrefixAddSub() {
        assertDoesNotThrow(() -> parse("+(1, 2, 3)"));
    }

    @Test
    public void testPrefixIntegerPositive() {
        assertDoesNotThrow(() -> parse("10"));
    }

    @Test
    public void testPrefixIntegerNegative() {
        assertDoesNotThrow(() -> parse("-10"));
    }

    @Test
    public void testPrefixGrouped() {
        assertDoesNotThrow(() -> parse("(+(4, 5))"));
    }

    // ===== POSTFIX TESTS =====

    @Test
    public void testPostfixMulDiv() {
        assertDoesNotThrow(() -> parse("(3, 4, 5)*"));
    }

    @Test
    public void testPostfixAddSub() {
        assertDoesNotThrow(() -> parse("(2, 1, 0)+"));
    }

    @Test
    public void testPostfixIntegerPositive() {
        assertDoesNotThrow(() -> parse("100"));
    }

    @Test
    public void testPostfixIntegerNegative() {
        assertDoesNotThrow(() -> parse("-100"));
    }

    @Test
    public void testPostfixGrouped() {
        assertDoesNotThrow(() -> parse("((5 + 3))"));
    }
}
