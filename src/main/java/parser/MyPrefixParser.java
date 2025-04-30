package parser;
import calculator.Calculator;
import calculator.values.NumericValue;
import calculator.values.RealValue;
import calculator.values.ComplexValue;
import java.math.BigDecimal;
import java.util.*;
import static parser.TokenizerPrefix.*;

/**
 * The MyPrefixParser class evaluates arithmetic expressions written in prefix notation.
 * It supports nested parentheses, complex numbers, arithmetic operators, and trigonometric functions.
 *
 * The parser processes the expression using a custom tokenizer and handles implicit multiplication,
 * grouping, and multi-argument operator support.
 *
 * Example:
 *   (+ (* (+ [2+3i] [1-1i]) (+ [4+0i] [2+2i])) (- [5+0i] [0+2i]))
 *
 * Author: Hugue
 */

public class MyPrefixParser {

    /** Default constructor */
    public MyPrefixParser() {}

    /** Token list parsed from input */
    List<String> tokens = new ArrayList<>();

    /** Stores last operator parsed (used for implicit operations) */
    private String lastOperator = "";

    /** Operators collected during parsing */
    List<String> operators = new ArrayList<>();

    /**
     * Evaluates a prefix expression string.
     * Converts input to tokens and parses recursively.
     *
     * @param expression the arithmetic expression
     * @return the result as a NumericValue
     */
    public NumericValue evaluate(String expression) {
        //System.out.println("prefix expr = "+expression);
        if(expression.charAt(0) != '(' || expression.charAt(expression.length()-1) != ')') {
            expression = "(" + expression + ")";
        }
        TokenizerPrefix tokenizerPrefix = new TokenizerPrefix(expression.toString().trim());
        tokens= tokenizerPrefix.getTokens();
        for (String token : tokens) {
            if (isOperator(token)) {
                operators.add(token);
            }
        }
        //System.out.println("[DEBUG] operator restant= " + operators);


        //System.out.println("Tokens: " + tokenizerPrefix.getTokens());

        NumericValue result = parseExpression(tokenizerPrefix);


        if (tokenizerPrefix.hasNext()) {
            throw new RuntimeException("Invalid expression: extra tokens remaining " + tokenizerPrefix.getTokens());
        }
        return result;
    }

    /**
     * Entry point to test several example prefix expressions.
     */
    public static void main(String[] args) {
        String exprPrefixWithCommas = "(+(4,5,6),+(7,/(5,2,7)),9)";;
        String exprPrefixWithoutCommas = "(+ (* (+ [2+3i] [1-1i]) (+ [4+0i] [2+2i])) (- [5+0i] [0+2i]))";
        ExpressionParser.mycalculator.setUseRadians(false);

        System.out.print(exprPrefixWithCommas + " = ");
        MyPrefixParser myPrefixParser = new MyPrefixParser();
        System.out.println(myPrefixParser.evaluate(exprPrefixWithCommas));
        System.out.println("\n");
        System.out.print(exprPrefixWithoutCommas + " = ");
        System.out.println(myPrefixParser.evaluate(exprPrefixWithoutCommas));
    }

    /**
     * Parses a full expression or value recursively.
     * Handles grouping, operators, and functions.
     */
    private NumericValue parseExpression(TokenizerPrefix tokenizerPrefix) {
        if (!tokenizerPrefix.hasNext()) throw new RuntimeException("Unexpected end of expression");

        String token = tokenizerPrefix.peek();
        //System.out.println("[DEBUG] peek token = " + token);

        if (token.equals("(")) {
            tokenizerPrefix.next(); // consume '('
            //System.out.println("[DEBUG] found '('");

            if (!tokenizerPrefix.hasNext()) throw new RuntimeException("Unexpected end after '('");

            token = tokenizerPrefix.peek();
            if (isOperator(token)) {
                lastOperator = token;
                token = tokenizerPrefix.next(); // consume operator
                //System.out.println("[DEBUG] operator in group = " + token);
                List<NumericValue> args = parseArguments(tokenizerPrefix);
                //System.out.println("[DEBUG] args = " + args);
                NumericValue product =new RealValue(0, 10);
                //System.out.println("[DEBUG] operator in group = " + token);
                //System.out.println("[DEBUG] operator restant= " + operators);
                if(operators.size()==0)
                {
                    product = args.get(0);
                    for (int i = 1; i < args.size(); i++) {
                        product = product.multiply(args.get(i));
                    }
                    //System.out.println("[DEBUG] product of values last implicite" + args + " = " + product);

                }
                else {
                    product = applyOperator(token, args);
                    operators.remove(token);
                }

                //System.out.println("[DEBUG] result of operator " + token + " on " + args + " = " + product);
                return product;
            } else if (isFunction(token)) {
                token = tokenizerPrefix.next(); // consume function
                //System.out.println("[DEBUG] function in group = " + token);
                NumericValue arg = parseExpression(tokenizerPrefix);
                if (!tokenizerPrefix.peek().equals(")")) throw new RuntimeException("Expected ')' after function argument");
                tokenizerPrefix.next(); // consume ')'
                return applyFunction(token, arg);
            } else {
                // Multiplication implicite
                List<NumericValue> values = new ArrayList<>();
                while (tokenizerPrefix.hasNext()) {
                    String next = tokenizerPrefix.peek();
                    if (next.equals(")")) {
                        tokenizerPrefix.next(); // consume ')'
                        break;
                    }
                    if (next.equals(",")) {
                        tokenizerPrefix.next(); // skip comma
                        continue;
                    }
                    values.add(parseExpression(tokenizerPrefix));
                }
                if (values.isEmpty()) return new RealValue(0, 10);
                NumericValue product =new RealValue(0, 10);
                if(lastOperator.isBlank()) {
                    product = values.get(0);
                    for (int i = 1; i < values.size(); i++) {
                        product = product.multiply(values.get(i));
                    }
                    //System.out.println("[DEBUG] product of values last implicite" + values + " = " + product);
                }
                else
                {
                    //System.out.println("[DEBUG] lastOperator" + " = " + lastOperator);
                    //System.out.println("[DEBUG] values" + " = " + values);
                    product = applyOperator(lastOperator, values);
                    operators.remove(lastOperator);
                    lastOperator = "";
                    //System.out.println("[DEBUG] sum of values " + values + " = " + product);

                }
                return product;
            }
        }

        else if (token.equals(")")) {
            tokenizerPrefix.next();
            throw new RuntimeException("Unexpected ')'");
        }

        else if (isOperator(token)) {
            token = tokenizerPrefix.next();
            //System.out.println("[DEBUG] operator standalone = " + token);

            List<NumericValue> args = new ArrayList<>();
            if (tokenizerPrefix.hasNext() && tokenizerPrefix.peek().equals("(")) {
                tokenizerPrefix.next(); // consume '('
                args = parseArguments(tokenizerPrefix);
            } else {
                args.add(parseExpression(tokenizerPrefix));
                args.add(parseExpression(tokenizerPrefix));
            }

            if (args.isEmpty()) throw new RuntimeException("No arguments for operator: " + token);
            NumericValue result = applyOperator(token, args);
            //System.out.println("[DEBUG] result of operator " + token + " on " + args + " = " + result);
            operators.remove(token);
            return result;
        }

        else if (isFunction(token)) {
            token = tokenizerPrefix.next();
            //System.out.println("[DEBUG] function standalone = " + token);
            NumericValue arg = parseExpression(tokenizerPrefix);
            return applyFunction(token, arg);
        }

        else {
            token = tokenizerPrefix.next();
            // System.out.println("[DEBUG] raw token = " + token);

            if (token.startsWith("[")) {
                return parseComplex(token);
            }

            try {
                return new RealValue(new BigDecimal(token), 10);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format: " + token);
            }
        }
    }

    /** Parses a list of arguments within parentheses */
    private List<NumericValue> parseArguments(TokenizerPrefix tokenizerPrefix) {
        List<NumericValue> args = new ArrayList<>();
        while (tokenizerPrefix.hasNext()) {
            String next = tokenizerPrefix.peek();
            if (next.equals(")")) {
                tokenizerPrefix.next(); // consume ')'
                break;
            }
            if (next.equals(",")) {
                tokenizerPrefix.next(); // skip ','
                continue;
            }
            args.add(parseExpression(tokenizerPrefix));
        }
        return args;
    }

    /** Parses a complex number literal of the form [a+bi] */
    private NumericValue parseComplex(String token) {
        if (!token.startsWith("[") || !token.endsWith("]")) throw new RuntimeException("Invalid complex number format: " + token);
        token = token.substring(1, token.length() - 1);
        token = token.replace("-", "+-");
        String[] parts = token.split("\\+");
        double real = 0;
        double imag = 0;
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (part.endsWith("i")) {
                imag += Double.parseDouble(part.substring(0, part.length() - 1));
            } else {
                real += Double.parseDouble(part);
            }
        }
        return new ComplexValue(real, imag);
    }

    /** Checks whether a token is an operator */
    private static boolean isOperator(String token) {
        return Arrays.asList("+", "-", "*", "/").contains(token);
    }

    /** Checks whether a token is a function */
    private static boolean isFunction(String token) {
        return Arrays.asList("sin", "cos", "tan").contains(token);
    }

    /** Applies a function to the given argument */
    private NumericValue applyFunction(String func, NumericValue arg) {
        double value = Double.parseDouble(arg.toString());
        Calculator c = ExpressionParser.mycalculator;
        switch (func) {
            case "sin": return new RealValue(c.sin(value), 10);
            case "cos": return new RealValue(c.cos(value), 10);
            case "tan": return new RealValue(c.tan(value), 10);
            default: throw new RuntimeException("Unknown function: " + func);
        }
    }
}
