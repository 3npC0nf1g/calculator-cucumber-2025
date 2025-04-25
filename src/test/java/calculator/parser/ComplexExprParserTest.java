package calculator.parser;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;
import calculator.parser.complex.ComplexExprLexer;
import calculator.parser.complex.ComplexExprParser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.antlr.v4.runtime.tree.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ComplexExprParserTest {

    private void parse(String input) {
        ComplexExprLexer lexer = new ComplexExprLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ComplexExprParser parser = new ComplexExprParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.entry(); // Rule: entry
        System.out.println("Input: " + input);
        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }

    // ===== INFIX TESTS =====
    @Test public void testComboRootInfix() {
        assertDoesNotThrow(() -> parse("sqrt(3) + sqrt(4) i"));
    }

    @Test public void testArgCisInfix() {
        assertDoesNotThrow(() -> parse("2 * cis(1)"));
    }

    @Test public void testEulerExpInfix() {
        assertDoesNotThrow(() -> parse("2 * e(i * 3)"));
    }

    @Test public void testBinaryAddSubInfix() {
        assertDoesNotThrow(() -> parse("3 + 2 - 1"));
    }

    @Test public void testBinaryMulDivInfix() {
        assertDoesNotThrow(() -> parse("4 * 2 / 1"));
    }

    @Test public void testAbsValInfix() {
        assertDoesNotThrow(() -> parse("|5 + 2i|"));
    }

    @Test public void testCartesianInfix() {
        assertDoesNotThrow(() -> parse("asCoord(3 + 4i)"));
    }

    @Test public void testPolarInfix() {
        assertDoesNotThrow(() -> parse("asAngle(3 + 4i)"));
    }

    @Test public void testExpoInfix() {
        assertDoesNotThrow(() -> parse("asExp(e(i * 5))"));
    }

    @Test public void testImagInfix() {
        assertDoesNotThrow(() -> parse("-3i"));
    }

    @Test public void testNumInfix() {
        assertDoesNotThrow(() -> parse("-42"));
    }

    @Test public void testGroupInfix() {
        assertDoesNotThrow(() -> parse("(3 + 2i)"));
    }

    // ===== PREFIX TESTS =====
    @Test public void testComboRootPrefix() {
        assertDoesNotThrow(() -> parse("+(sqrt(3), sqrt(4) i)"));
    }

    @Test public void testArgCisPrefix() {
        assertDoesNotThrow(() -> parse("2 * cis(2)"));
    }

    @Test public void testEulerExpPrefix() {
        assertDoesNotThrow(() -> parse("e(i * 6)"));
    }

    @Test public void testMultiOpMulDivPrefix() {
        assertDoesNotThrow(() -> parse("*(2, 3, 4)"));
    }

    @Test public void testMultiOpAddSubPrefix() {
        assertDoesNotThrow(() -> parse("+(1, 2, 3)"));
    }

    @Test public void testAbsValPrefix() {
        assertDoesNotThrow(() -> parse("|4i|"));
    }

    @Test public void testGroupPrefix() {
        assertDoesNotThrow(() -> parse("(+(2,3))"));
    }

    @Test public void testImagPrefix() {
        assertDoesNotThrow(() -> parse("-2i"));
    }

    @Test public void testNumPrefix() {
        assertDoesNotThrow(() -> parse("123"));
    }

    @Test public void testCartesianPrefix() {
        assertDoesNotThrow(() -> parse("asCoord(3 + 2i)"));
    }

    @Test public void testPolarPrefix() {
        assertDoesNotThrow(() -> parse("asAngle(1)"));
    }

    @Test public void testExpoPrefix() {
        assertDoesNotThrow(() -> parse("asExp(e(i * 2))"));
    }

    // ===== POSTFIX TESTS =====
    @Test public void testComboRootPostfix() {
        assertDoesNotThrow(() -> parse("(sqrt(3), sqrt(4) i)+"));
    }

    @Test public void testArgCisPostfix() {
        assertDoesNotThrow(() -> parse("3 * cis(2)"));
    }

    @Test public void testEulerExpPostfix() {
        assertDoesNotThrow(() -> parse("2 * e(i * 1)"));
    }

    @Test public void testMultiOpMulDivPostfix() {
        assertDoesNotThrow(() -> parse("(2,3,4)*"));
    }

    @Test public void testMultiOpAddSubPostfix() {
        assertDoesNotThrow(() -> parse("(1,2,3)+"));
    }

    @Test public void testAbsValPostfix() {
        assertDoesNotThrow(() -> parse("|3 + 2i|"));
    }

    @Test public void testImagPostfix() {
        assertDoesNotThrow(() -> parse("-4i"));
    }

    @Test public void testNumPostfix() {
        assertDoesNotThrow(() -> parse("77"));
    }

    @Test public void testGroupPostfix() {
        assertDoesNotThrow(() -> parse("((2 + 3))"));
    }

    @Test public void testCartesianPostfix() {
        assertDoesNotThrow(() -> parse("asCoord(1 + 2i)"));
    }

    @Test public void testPolarPostfix() {
        assertDoesNotThrow(() -> parse("asAngle(3)"));
    }

    @Test public void testExpoPostfix() {
        assertDoesNotThrow(() -> parse("asExp(e(i * 3))"));
    }
}

