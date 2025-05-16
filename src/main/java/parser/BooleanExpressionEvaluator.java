package parser;
import calculator.values.BooleanValue;
import calculator.values.NumericValue;

import java.util.*;

/**
 * Evaluates boolean expressions with support for logical operators (AND, OR, NOT),
 * comparison operators (==, !=, <, <=, >, >=), and boolean/numeric literals.
 * <p>
 * Example usage:
 * <pre>
 *     NumericValue result = BooleanExpressionEvaluator.parseBooleanExpression("3 > 2 AND true");
 * </pre>
 * </p>
 */
public class BooleanExpressionEvaluator {
    /**
     * Operator precedence mapping for logical operators.
     */
    public static final Map<String, Integer> PRECEDENCE = Map.of(
            "!", 3,
            "&&", 2,
            "||", 1
    );

    /**
     * Parses and evaluates a boolean expression string.
     *
     * @param expr the expression string to evaluate (e.g., "true && false" or "3 > 2 || false")
     * @return a BooleanValue representing the evaluated result
     * @throws Exception if the expression is malformed or contains unsupported tokens
     */
    public static NumericValue parseBooleanExpression(String expr) throws Exception {
        List<String> tokens = tokenize(expr);
        List<String> postfix = toPostfix(tokens);
        return new BooleanValue(evaluatePostfix(postfix));
    }

    /**
     * Converts a raw input string into a list of tokens.
     * Handles logical aliases (AND, OR, NOT) and adds spacing for operators and parentheses.
     *
     * @param expr the input expression
     * @return list of string tokens
     */
    public static List<String> tokenize(String expr) {
        expr = expr.replaceAll("(?i)\\bAND\\b", "&&")
                .replaceAll("(?i)\\bOR\\b", "||")
                .replaceAll("(?i)\\bNOT\\b", "!")
                .replaceAll("(?<=[^\\w.])(?=\\w)|(?<=\\w)(?=[^\\w.])", " ")  // spacing symbols
                .replaceAll("\\s+", " ");

        String[] parts = expr.trim().split(" ");
        return Arrays.asList(parts);
    }

    /**
     * Converts a list of tokens from infix to postfix notation using the Shunting Yard algorithm.
     *
     * @param tokens the infix token list
     * @return postfix token list
     * @throws Exception for mismatched parentheses or unknown tokens
     */
    public static List<String> toPostfix(List<String> tokens) throws Exception {
        List<String> output = new ArrayList<>();
        Deque<String> operators = new ArrayDeque<>();

        for (String token : tokens) {
            if (isBoolean(token) || isNumeric(token)) {
                output.add(token);
            } else if (isComparisonOperator(token)) {
                output.add(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (operators.isEmpty() || !operators.pop().equals("(")) {
                    throw new Exception("Mismatched parentheses");
                }
            } else if (isLogicalOperator(token)) {
                while (!operators.isEmpty() && isLogicalOperator(operators.peek())
                        && PRECEDENCE.get(token) <= PRECEDENCE.get(operators.peek())) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else {
                throw new Exception("Unknown token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            String op = operators.pop();
            if (op.equals("(") || op.equals(")")) {
                throw new Exception("Mismatched parentheses");
            }
            output.add(op);
        }

        return output;
    }

    /**
     * Evaluates a boolean expression in postfix notation.
     *
     * @param postfix the postfix expression tokens
     * @return boolean result of the evaluation
     * @throws Exception for invalid syntax or type mismatches
     */
    public static boolean evaluatePostfix(List<String> postfix) throws Exception {
        Deque<Object> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isBoolean(token)) {
                stack.push(parseBooleanToken(token));
            } else if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isComparisonOperator(token)) {
                Object b = stack.pop();
                Object a = stack.pop();

                if (!(a instanceof Double) || !(b instanceof Double)) {
                    throw new Exception("Invalid comparison operands");
                }

                double left = (double) a;
                double right = (double) b;
                stack.push(evaluateComparison(left, right, token));
            } else if (isLogicalOperator(token)) {
                if (token.equals("!")) {
                    Object operand = stack.pop();
                    stack.push(!toBoolean(operand));
                } else {
                    Object b = stack.pop();
                    Object a = stack.pop();
                    boolean left = toBoolean(a);
                    boolean right = toBoolean(b);
                    if (token.equals("&&")) stack.push(left && right);
                    else if (token.equals("||")) stack.push(left || right);
                }
            } else {
                throw new Exception("Invalid token in postfix: " + token);
            }
        }

        if (stack.size() != 1) throw new Exception("Invalid expression result");

        Object result = stack.pop();
        return toBoolean(result);
    }


    /**
     * Converts an object to a boolean value.
     *
     * @param val the object (Boolean or Double)
     * @return true if the value is logically true
     * @throws Exception if conversion is not possible
     */
    public static boolean toBoolean(Object val) throws Exception {
        if (val instanceof Boolean) return (Boolean) val;
        if (val instanceof Double) return ((Double) val) != 0.0;
        throw new Exception("Cannot convert to boolean: " + val);
    }


    /**
     * Parses a string token into a boolean.
     *
     * @param token the string token ("true", "false", "1", "0")
     * @return corresponding boolean value
     * @throws Exception if the token is invalid
     */
    public static boolean parseBooleanToken(String token) throws Exception {
        token = token.toLowerCase();
        return switch (token) {
            case "true", "1" -> true;
            case "false", "0" -> false;
            default -> throw new Exception("Invalid boolean token: " + token);
        };
    }

    /**
     * Converts a boolean to its numeric (double) equivalent.
     *
     * @param b the boolean value
     * @return 1.0 for true, 0.0 for false
     */
    public static double parseDouble(boolean b) {
        return b ? 1.0 : 0.0;
    }

    /**
     * Checks if a token represents a boolean value.
     *
     * @param token the input token
     * @return true if it's "true", "false", "1", or "0"
     */
    public static boolean isBoolean(String token) {
        return token.equalsIgnoreCase("true") || token.equalsIgnoreCase("false");
    }

    /**
     * Checks if a token represents a numeric value.
     *
     * @param token the input token
     * @return true if the token can be parsed as a double
     */
    public static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    /**
     * Checks if a token is a logical operator (!, &&, ||).
     *
     * @param token the input token
     * @return true if the token is a recognized logical operator
     */
    public static boolean isLogicalOperator(String token) {
        return PRECEDENCE.containsKey(token);
    }

    /**
     * Checks if a token is a comparison operator.
     *
     * @param token the input token
     * @return true if the token is one of ==, !=, <, <=, >, >=
     */
    public static boolean isComparisonOperator(String token) {
        return token.matches("==|!=|<=|>=|<|>");
    }

    /**
     * Evaluates a comparison between two numeric values.
     *
     * @param a  left operand
     * @param b  right operand
     * @param op the comparison operator
     * @return result of the comparison
     */
    public static boolean evaluateComparison(double a, double b, String op) {
        return switch (op) {
            case "==" -> a == b;
            case "!=" -> a != b;
            case "<"  -> a < b;
            case "<=" -> a <= b;
            case ">"  -> a > b;
            case ">=" -> a >= b;
            default -> false;
        };
    }
}
