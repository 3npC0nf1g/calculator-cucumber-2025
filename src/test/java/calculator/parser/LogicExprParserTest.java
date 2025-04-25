package calculator.parser;

import org.antlr.v4.runtime.tree.*;
import calculator.parser.logic.LogicExprParserLexer;
import calculator.parser.logic.LogicExprParserParser;

import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LogicExprParserTest {

    private void parse(String input) {
        LogicExprParserLexer lexer = new LogicExprParserLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogicExprParserParser parser = new LogicExprParserParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.entry();
        System.out.println("Input: " + input);
        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }

    // === INFIX TESTS ===

    @Test
    public void testInfixNegation() {
        assertDoesNotThrow(() -> parse("NOT 1"));
    }

    @Test
    public void testInfixAnd() {
        assertDoesNotThrow(() -> parse("1 AND 0"));
    }

    @Test
    public void testInfixOr() {
        assertDoesNotThrow(() -> parse("0 OR 1"));
    }

    @Test
    public void testInfixImplies() {
        assertDoesNotThrow(() -> parse("1 IMPL 0"));
    }

    @Test
    public void testInfixXor() {
        assertDoesNotThrow(() -> parse("1 XOR 0"));
    }

    @Test
    public void testInfixEquiv() {
        assertDoesNotThrow(() -> parse("1 EQ 1"));
    }

    @Test
    public void testInfixLiteral() {
        assertDoesNotThrow(() -> parse("1"));
    }

    @Test
    public void testInfixGroup() {
        assertDoesNotThrow(() -> parse("(1 AND 0)"));
    }

    // === PREFIX TESTS ===

    @Test
    public void testPrefixNegation() {
        assertDoesNotThrow(() -> parse("NOT 0"));
    }

    @Test
    public void testPrefixAnd() {
        assertDoesNotThrow(() -> parse("AND(1, 0)"));
    }

    @Test
    public void testPrefixOr() {
        assertDoesNotThrow(() -> parse("OR(0, 1)"));
    }

    @Test
    public void testPrefixImplies() {
        assertDoesNotThrow(() -> parse("IMPL(1, 0)"));
    }

    @Test
    public void testPrefixXor() {
        assertDoesNotThrow(() -> parse("XOR(1, 0)"));
    }

    @Test
    public void testPrefixEquiv() {
        assertDoesNotThrow(() -> parse("EQ(1, 1)"));
    }

    @Test
    public void testPrefixLiteral() {
        assertDoesNotThrow(() -> parse("0"));
    }

    @Test
    public void testPrefixGroup() {
        assertDoesNotThrow(() -> parse("(NOT 1)"));
    }

    // === POSTFIX TESTS ===

    @Test
    public void testPostfixNegation() {
        assertDoesNotThrow(() -> parse("1 NOT"));
    }

    @Test
    public void testPostfixAnd() {
        assertDoesNotThrow(() -> parse("(1, 0) AND"));
    }

    @Test
    public void testPostfixOr() {
        assertDoesNotThrow(() -> parse("(0, 1) OR"));
    }

    @Test
    public void testPostfixImplies() {
        assertDoesNotThrow(() -> parse("(1, 0) IMPL"));
    }

    @Test
    public void testPostfixXor() {
        assertDoesNotThrow(() -> parse("(1, 0) XOR"));
    }

    @Test
    public void testPostfixEquiv() {
        assertDoesNotThrow(() -> parse("(1, 1) EQ"));
    }

    @Test
    public void testPostfixLiteral() {
        assertDoesNotThrow(() -> parse("1"));
    }

    @Test
    public void testPostfixGroup() {
        assertDoesNotThrow(() -> parse("(0)"));
    }
}
