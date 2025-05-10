
package parser;

import calculator.values.NumericValue;

import java.util.ArrayList;
import java.util.List;

/**
 * The MyPostfixParser class provides parsing and evaluation of arithmetic expressions
 * written in a custom Postfix (Reverse Polish) notation.
 *
 * Postfix expressions are transformed into Prefix expressions and then evaluated
 * using MyPrefixParser.
 *
 * Supported features:
 * - Arithmetic operators: +, -, *, /, ^, %
 * - Functions like sin, cos, etc. via MyPrefixParser
 * - Complex numbers [a+bi] and nested expressions
 *
 * Example:
 *   ((4,5,6)+,(7,(5,2,7)/)+,9) will be transformed and evaluated properly.
 *
 * Author: Hugue
 */

public class TokenizerPostfix {

    /** List of parsed tokens extracted from the input expression */
    private final List<String> tokens;

    /** Current read position in the token list */
    private int position;


    /**
     * Constructs a TokenizerPostfix instance and tokenizes the given expression.
     *
     * @param expression the postfix arithmetic expression to tokenize
     */
    public TokenizerPostfix(String expression) {
        this.tokens = tokenize(expression);
        this.position = 0;
        //System.out.println("[Tokenizer] Tokens: " + tokens); // pour debug
    }

    /**
     * Retrieves the list of all tokens parsed from the expression.
     *
     * @return the list of tokens
     */
    public List<String> getTokens() {
        return tokens;
    }

    /**
     * Tokenizes a given arithmetic expression string into individual tokens.
     * This method supports numbers, complex numbers, operators, and functions.
     *
     * @param expression the input expression to tokenize
     * @return a list of string tokens
     * @throws RuntimeException if an unknown character is encountered
     */
    private List<String> tokenize(String expression) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (c == '(' || c == ')' || c == ',' || c == '+' || c == '-' || c == '*' || c == '/') {
                result.add(String.valueOf(c));
                i++;
            }
            else if (c == '[') { // nombre complexe
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                i++;
                while (i < expression.length() && expression.charAt(i) != ']') {
                    sb.append(expression.charAt(i));
                    i++;
                }
                if (i < expression.length()) {
                    sb.append(']');
                    i++;
                }
                result.add(sb.toString());
            }
            else if (Character.isDigit(c) || c == '.') { // nombre réel
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                result.add(sb.toString());
            }
            else if (Character.isLetter(c)) { // fonction type sin, cos, tan
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                // Maintenant on attend une parenthèse immédiatement après
                if (i < expression.length() && expression.charAt(i) == '(') {
                    sb.append('(');
                    i++;
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char current = expression.charAt(i);
                        if (current == '(') parenCount++;
                        else if (current == ')') parenCount--;
                        sb.append(current);
                        i++;
                    }
                }
                result.add(sb.toString());
            }
            else {
                throw new RuntimeException("Unknown character: " + c);
            }
        }
        return result;
    }
}

