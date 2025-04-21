package parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import parser.rational.RationalCoreLexer;
import parser.rational.RationalCoreParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RationalExprTest {

    @Test
    public void testMultipleRationalExpressions() {
        List<String> expressions = List.of(
                "(3,4)+",              // postfix add
                "+(3,4)",              // prefix add
                "3+4",                 // infix add
                "3÷4",                 // simple rational
                "5*(2+1)",             // infix with parens
                "+(1,2,3)",            // prefix add with multiple args
                "(1,2,3)*",            // postfix mul with multiple args
                "-(3÷4)",              // negative rational in prefix
                "((3÷2)+1)*5"          // nested expression
        );

        for (String input : expressions) {
            System.out.println("Testing: " + input);
            assertDoesNotThrow(() -> parseExpression(input), "Failed parsing: " + input);
        }
    }

    private void parseExpression(String input) {
        RationalCoreLexer lexer = new RationalCoreLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RationalCoreParser parser = new RationalCoreParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.entry(); // Assure-toi que 'entry' est bien la règle de départ
        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }
}

