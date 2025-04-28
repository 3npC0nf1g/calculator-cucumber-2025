package parser;

import calculator.values.NumericValue;
import calculator.values.RealValue;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final List<String> tokens;
    private int pos = 0;

    public Tokenizer(String input) {
        tokens = tokenize(input);
    }

    private List<String> tokenize(String input) {
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

    public List<String> getTokens() {
        return tokens;
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

    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

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
                    mult = mult.multiply(arg);
                }
                return mult;
            case "/":
                if (args.isEmpty()) throw new RuntimeException("No arguments for division");
                result = args.get(0);
                for (int i = 1; i < args.size(); i++) {
                    result = result.divide(args.get(i));
                }
                return result;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}
