package parser;

import java.util.*;

import java.util.*;

public class PostfixParser {

    public static void main(String[] args) {
        String expr1 = "((4,5,6)+,(7,(5,2,7)/)+,9)";
        String expr2 = "((4 5 6)+ (7 (5 2 7)/)+ 9)";

        System.out.print(expr1+" = ");
        System.out.println(evaluate(expr1)); // Should print the result
        System.out.print(expr2+" = ");
        System.out.println(evaluate(expr2)); // Should print the result
    }

    public static double evaluate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        //System.out.println("Tokens: " + tokenizer.tokens);
        return parseExpression(tokenizer);
    }

    private static double parseExpression(Tokenizer tokenizer) {
        if (!tokenizer.hasNext()) throw new RuntimeException("Unexpected end of expression");

        String token = tokenizer.peek();
        //System.out.println("Parsing token: " + token);

        if (token.equals("(")) {
            tokenizer.next(); // consume '('
            List<Double> values = new ArrayList<>();
            while (!tokenizer.peek().equals(")")) {
                values.add(parseExpression(tokenizer));
                if (tokenizer.hasNext() && tokenizer.peek().equals(",")) tokenizer.next(); // Skip commas
            }
            tokenizer.next(); // consume ')'

            // Now check if there is an operator AFTER the parenthesis
            if (tokenizer.hasNext() && isOperator(tokenizer.peek())) {
                String operator = tokenizer.next(); // consume operator
                //System.out.println("Applying operator: " + operator + " on values: " + values);
                return applyOperator(operator, values);
            } else {
                // No operator found: MULTIPLY all results
                if (values.isEmpty()) throw new RuntimeException("Empty group without operator");
                double result = 1;
                for (double v : values) {
                    result *= v;
                }
                //System.out.println("Multiplying grouped values: " + values + " = " + result);
                return result;
            }
        }

        if (isOperator(token)) {
            throw new RuntimeException("Operator should come after operands in postfix notation: found " + token);
        }

        // Otherwise it must be a number
        token = tokenizer.next();
        //System.out.println("Parsed number: " + token);
        return Double.parseDouble(token);
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static double applyOperator(String operator, List<Double> args) {
        switch (operator) {
            case "+":
                return args.stream().mapToDouble(Double::doubleValue).sum();
            case "-":
                if (args.isEmpty()) throw new RuntimeException("No arguments for subtraction");
                double result = args.get(0);
                for (int i = 1; i < args.size(); i++) result -= args.get(i);
                return result;
            case "*":
                result = 1;
                for (double arg : args) result *= arg;
                return result;
            case "/":
                if (args.isEmpty()) throw new RuntimeException("No arguments for division");
                result = args.get(0);
                for (int i = 1; i < args.size(); i++) result /= argOrThrow(args.get(i));
                return result;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }

    private static double argOrThrow(double arg) {
        if (arg == 0.0) throw new ArithmeticException("Division by zero");
        return arg;
    }

    static class Tokenizer {
        private final List<String> tokens;
        private int pos = 0;

        public Tokenizer(String input) {
            tokens = tokenize(input);
        }

        private List<String> tokenize(String input) {
            List<String> result = new ArrayList<>();
            StringBuilder number = new StringBuilder();
            for (char c : input.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    if (number.length() > 0) {
                        result.add(number.toString());
                        number.setLength(0);
                    }
                    continue;
                }
                if (c == '(' || c == ')' || c == ',' || c == '+' || c == '-' || c == '*' || c == '/') {
                    if (number.length() > 0) {
                        result.add(number.toString());
                        number.setLength(0);
                    }
                    result.add(Character.toString(c));
                } else {
                    number.append(c);
                }
            }
            if (number.length() > 0) {
                result.add(number.toString());
            }
            return result;
        }

        public boolean hasNext() {
            return pos < tokens.size();
        }

        public String next() {
            return tokens.get(pos++);
        }

        public String peek() {
            return tokens.get(pos);
        }
    }
}
