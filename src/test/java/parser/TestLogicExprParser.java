package parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.Arrays;
import java.util.List;

public class TestLogicExprParser {

    public static void main(String[] args) {
        List<String> expressions = Arrays.asList(
                "NOT 1",
                "1 AND 0",
                "OR(1, NOT 0)",
                "((1,0) AND) NOT",
                "EQ(1, IMPL(1, 0))"
        );

        for (String expr : expressions) {
            System.out.println("\n⮞ Testing: " + expr);
            test(expr);
        }
    }

    public static void test(String input) {
        CharStream cs = CharStreams.fromString(input);
        LogicExprParserLexer lexer = new LogicExprParserLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogicExprParserParser parser = new LogicExprParserParser(tokens);

        // optional: display tokens
        tokens.getTokens().forEach(System.out::println);

        ParseTree tree = parser.start(); // entry rule
        System.out.println("✅ Parsed tree: " + tree.toStringTree(parser));
    }
}
