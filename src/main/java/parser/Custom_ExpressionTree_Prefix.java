package parser;

import calculator.values.NumericValue;
import calculator.values.RealValue;

import java.math.BigDecimal;
import java.util.*;

public class Custom_ExpressionTree_Prefix {

    public static void main(String[] args) {
        String expr1 = "(+(4,5,6),+(7,/(5,2,7)),9)";
        String expr2 = "(+(4 5 6) +(7 /(5 2 7)) 9)";

        System.out.print(expr1+" = "); // Should print the result
        System.out.println(evaluate(expr1)); // Should print the result
        System.out.print(expr2+" = "); // Should print the result
        System.out.println(evaluate(expr2)); // Should print the result
    }

    public static NumericValue evaluate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        return parseExpression(tokenizer);
    }

    private static NumericValue parseExpression(Tokenizer tokenizer) {
        if (!tokenizer.hasNext()) throw new RuntimeException("Unexpected end of expression");

        String token = tokenizer.peek();
        //System.out.println("Parsing token: " + token);

        if (token.equals("(")) {
            tokenizer.next(); // consume '('
            List<NumericValue> values = new ArrayList<>();
            while (!tokenizer.peek().equals(")")) {
                NumericValue val = parseExpression(tokenizer);
                //System.out.println("Parsed value inside (): " + val);
                values.add(val);
                if (tokenizer.hasNext() && tokenizer.peek().equals(",")) tokenizer.next(); // Skip commas
            }
            tokenizer.next(); // consume ')'
            NumericValue sum = new RealValue(1,5);
            for (NumericValue v : values) {
                sum=sum.multiply(v);
            }

            //System.out.println("Sum inside (): " + sum);
            return sum;
        }

        if (isOperator(token)) {
            token = tokenizer.next(); // consume operator
            //System.out.println("Applying operator: " + token);
            if (!tokenizer.hasNext() || !tokenizer.peek().equals("("))
                throw new RuntimeException("Expected '(' after operator: " + token);
            tokenizer.next(); // consume '('
            List<NumericValue> args = new ArrayList<>();
            while (!tokenizer.peek().equals(")")) {
                args.add(parseExpression(tokenizer));
                if (tokenizer.hasNext() && tokenizer.peek().equals(",")) tokenizer.next(); // Optional comma
            }
            tokenizer.next(); // consume ')'
            NumericValue result = applyOperator(token, args);
            //System.out.println("Result of operator " + token + ": " + result);
            return result;
        }

        // Otherwise it must be a number
        token = tokenizer.next(); // consume the number
        //System.out.println("Number parsed: " + token);
        return new RealValue(new BigDecimal(Double.parseDouble(token)),5);
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static NumericValue applyOperator(String operator, List<NumericValue> args) {
        switch (operator) {
            case "+":
                NumericValue sum = new RealValue(0,5);
                for (NumericValue v : args) {
                    sum=sum.add(v);
                }
                return sum;
            case "-":
                if (args.isEmpty()) throw new RuntimeException("No arguments for subtraction");
                NumericValue result = args.get(0);
                for (int i = 1; i < args.size(); i++) result=result.subtract(args.get(i));
                return result;
            case "*":
                NumericValue mult = new RealValue(1,5);
                for (NumericValue arg : args) mult=mult.multiply(arg);
                return mult;
            case "/":
                if (args.isEmpty()) throw new RuntimeException("No arguments for division");
                result = args.get(0);
                for (int i = 1; i < args.size(); i++) result=result.divide(args.get(i));
                return result;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
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
