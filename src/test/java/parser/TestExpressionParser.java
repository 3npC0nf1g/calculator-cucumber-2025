package parser;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import parser.expression.ExpressionParserParser;
import parser.expression.ExpressionParserLexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestExpressionParser {
    public static void main(String[] args) {
        String inputFile = "src/test/java/parser/input.txt"; // Ton fichier avec des expressions

        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFile)));

            // Création du lexer et du parser
            CharStream charStream = CharStreams.fromString(input);
            ExpressionParserLexer lexer = new ExpressionParserLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParserParser parser = new ExpressionParserParser(tokens);

            // Appel du point d'entrée "start"
            ParseTree tree = parser.start();

            // Affichage de l'arbre syntaxique
            System.out.println(tree.toStringTree(parser));

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}
