package parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import parser.complex.ComplexExprLexer;
import parser.complex.ComplexExprParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ComplexExprTest {

    @Test
    public void testMultipleComplexExpressions() {
        List<String> expressions = List.of(
                "sqrt(3) + sqrt(4) j",
                "3 * cis(2)",
                "e(i * 3)",
                "intoCartesian(sqrt(9))",
                "(5 + 2) * (3 - 1)",
                "|3 + 4j|",
                "sqrt(sqrt(16)) + sqrt(1) j",
                "-4j",
                "intoPolar(e(i * 1))"
        );

        for (String input : expressions) {
            System.out.println("Testing: " + input);
            assertDoesNotThrow(() -> parseExpression(input));
        }
    }

    private void parseExpression(String input) {
        ComplexExprLexer lexer = new ComplexExprLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ComplexExprParser parser = new ComplexExprParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.prog();
        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }
}

