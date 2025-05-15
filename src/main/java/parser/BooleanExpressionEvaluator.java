package parser;
import calculator.values.BooleanValue;
import calculator.values.NumericValue;

import java.util.*;

public class BooleanExpressionEvaluator {

    private static final Map<String, Integer> PRECEDENCE = Map.of(
            "!", 3,
            "&&", 2,
            "||", 1
    );

    public static NumericValue parseBooleanExpression(String expr) throws Exception {
        List<String> tokens = tokenize(expr);
        List<String> postfix = toPostfix(tokens);
        return new BooleanValue(evaluatePostfix(postfix));
    }

    private static List<String> tokenize(String expr) {
        expr = expr.replaceAll("(?i)\\bAND\\b", "&&")
                .replaceAll("(?i)\\bOR\\b", "||")
                .replaceAll("(?i)\\bNOT\\b", "!")
                .replaceAll("(?<=[^\\w.])(?=\\w)|(?<=\\w)(?=[^\\w.])", " ")  // spacing symbols
                .replaceAll("\\s+", " ");

        String[] parts = expr.trim().split(" ");
        return Arrays.asList(parts);
    }

    private static List<String> toPostfix(List<String> tokens) throws Exception {
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

    private static boolean evaluatePostfix(List<String> postfix) throws Exception {
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

    private static boolean toBoolean(Object val) throws Exception {
        if (val instanceof Boolean) return (Boolean) val;
        if (val instanceof Double) return ((Double) val) != 0.0;
        throw new Exception("Cannot convert to boolean: " + val);
    }


    // --- Helper methods ---

    private static boolean parseBooleanToken(String token) throws Exception {
        token = token.toLowerCase();
        return switch (token) {
            case "true", "1" -> true;
            case "false", "0" -> false;
            default -> throw new Exception("Invalid boolean token: " + token);
        };
    }

    private static double parseDouble(boolean b) {
        return b ? 1.0 : 0.0;
    }

    private static boolean isBoolean(String token) {
        return token.equalsIgnoreCase("true") || token.equalsIgnoreCase("false");
    }

    private static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private static boolean isLogicalOperator(String token) {
        return PRECEDENCE.containsKey(token);
    }

    private static boolean isComparisonOperator(String token) {
        return token.matches("==|!=|<=|>=|<|>");
    }

    private static boolean evaluateComparison(double a, double b, String op) {
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
