package parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import parser.rational.RationalCoreLexer;
import parser.rational.RationalCoreParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RationalCoreTest {

    private void parse(String input) {
        RationalCoreLexer lexer = new RationalCoreLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RationalCoreParser parser = new RationalCoreParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.entry(); // Entry point
        System.out.println("Input: " + input);
        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }

    // ==== INFIX TESTS ====

    @Test
    public void testBinOpInfix() {
        assertDoesNotThrow(() -> parse("3 * 4"));
    }

    @Test
    public void testAddSubInfix() {
        assertDoesNotThrow(() -> parse("7 + 2"));
    }

    @Test
    public void testAtomicInfix() {
        assertDoesNotThrow(() -> parse("8"));
    }

    @Test
    public void testWrappedInfix() {
        assertDoesNotThrow(() -> parse("-(5 + 2)"));
    }

    // ==== PREFIX TESTS ====

    @Test
    public void testBinOpPrefix() {
        assertDoesNotThrow(() -> parse("*(2, 3)"));
    }

    @Test
    public void testAddSubPrefix() {
        assertDoesNotThrow(() -> parse("+(1, 4)"));
    }

    @Test
    public void testAtomicPrefix() {
        assertDoesNotThrow(() -> parse("9"));
    }

    @Test
    public void testWrappedPrefix() {
        assertDoesNotThrow(() -> parse("-(+(2, 3))"));
    }

    // ==== POSTFIX TESTS ====

    @Test
    public void testBinOpPosfix() {
        assertDoesNotThrow(() -> parse("(3, 5) *"));
    }

    @Test
    public void testAddSubPosfix() {
        assertDoesNotThrow(() -> parse("(1, 6) +"));
    }

    @Test
    public void testAtomicPosfix() {
        assertDoesNotThrow(() -> parse("7"));
    }

    @Test
    public void testWrappedPosfix() {
        assertDoesNotThrow(() -> parse("((8))"));
    }

    // ==== RATIONAL UNIT TESTS ====

    @Test
    public void testRationalUnit() {
        assertDoesNotThrow(() -> parse("3÷4"));
    }

    @Test
    public void testIntUnitPositive() {
        assertDoesNotThrow(() -> parse("123"));
    }

    @Test
    public void testIntUnitNegative() {
        assertDoesNotThrow(() -> parse("-99"));
    }
}
