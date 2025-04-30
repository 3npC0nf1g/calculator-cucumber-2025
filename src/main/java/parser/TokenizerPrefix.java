package parser;

import calculator.values.NumericValue;
import calculator.values.RationalValue;
import calculator.values.RealValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * The TokenizerPrefix class is responsible for parsing and tokenizing
 * a prefix arithmetic expression into a list of individual tokens.
 *
 * This tokenizer supports:
 * - Numeric values: integers, decimals
 * - Complex numbers written in brackets, for example: [3+4i]
 * - Operators: +, -, *, /
 * - Function and expression grouping using parentheses
 *
 * Tokens are extracted as strings and can be navigated sequentially.
 *
 * Author: Hugue
 */

public class TokenizerPrefix {
    /** List of parsed tokens extracted from the input expression */
    private final List<String> tokens;

    /** Current reading position in the token list */
    private int pos = 0;


    /**
     * Constructs a TokenizerPrefix instance and tokenizes the given input expression.
     *
     * @param input the prefix expression to tokenize
     */
    public TokenizerPrefix(String input) {
        tokens = tokenize(input);
    }

    /**
     * Tokenizes the input string into a list of tokens including operators, numbers, and expressions.
     *
     * @param input the raw input expression
     * @return a list of tokens in string form
     */
    private List<String> tokenize(String input) {
        input=input.replaceAll(","," ");
        List<String> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        boolean insideBracket = false;

        for (char c : input.toCharArray()) {
            if (insideBracket) {
                buffer.append(c);
                if (c == ']') {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                    insideBracket = false;
                }
                continue;
            }

            if (Character.isWhitespace(c)) {
                if (buffer.length() > 0) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                }
                continue;
            }

            if (c == '[') {
                if (buffer.length() > 0) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                }
                buffer.append(c);
                insideBracket = true;
                continue;
            }

            if (c == '(' || c == ')' || c == ',' || c == '+' || c == '-' || c == '*' || c == '/') {
                if (buffer.length() > 0) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                }
                result.add(Character.toString(c));
            } else {
                buffer.append(c);
            }
        }

        if (buffer.length() > 0) {
            result.add(buffer.toString());
        }
        return result;
    }

    /**
     * Returns the full list of tokens parsed from the expression.
     *
     * @return list of tokens
     */
    public List<String> getTokens() {
        return tokens;
    }

    /**
     * Checks if there are more tokens to read.
     *
     * @return true if more tokens are available, false otherwise
     */
    public boolean hasNext() {
        return pos < tokens.size();
    }

    /**
     * Returns the next token and moves the position forward.
     *
     * @return the next token as a string
     */
    public String next() {
        return tokens.get(pos++);
    }

    /**
     * Returns the current token without advancing the position.
     *
     * @return the next token as a string
     */
    public String peek() {
        return tokens.get(pos);
    }

    /**
     * Applies the given operator to the list of numeric arguments.
     *
     * @param operator the operator to apply (+, -, *, /)
     * @param args the list of numeric arguments
     * @return the result of applying the operator
     * @throws RuntimeException if the operator is unknown or arguments are invalid
     */
    public static NumericValue applyOperator(String operator, List<NumericValue> args) {
        switch (operator) {
            case "+":
                NumericValue sum = new RealValue(0,10);
                for (NumericValue v : args) {
                    sum = sum.add(v);
                }
                return sum;
            case "-":
                if (args.isEmpty()) throw new RuntimeException("No arguments for subtraction");
                NumericValue result = args.get(0);
                for (int i = 1; i < args.size(); i++) {
                    result = result.subtract(args.get(i));
                }
                return result;
            case "*":
                NumericValue mult = new RealValue(1,10);
                for (NumericValue arg : args) {
                    //System.out.println("arg : "+arg);
                    mult = mult.multiply(arg);
                }
                return mult;
            case "/":
                if (args.isEmpty()) throw new RuntimeException("No arguments for division");

                result = args.get(0);

                if(ExpressionParser.getDisplay()== ExpressionParser.Display.FRACTION)
                {
                    for (int i = 1; i < args.size(); i++) {
                        result = new RationalValue(new BigInteger(result.toString()),new BigInteger(args.get(i).toString()));
                    }
                }
                else {
                    for (int i = 1; i < args.size(); i++) {
                        result = result.divide(args.get(i));
                    }                }

                return result;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}
