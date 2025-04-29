package parser;

import calculator.values.NumericValue;

import java.util.ArrayList;
import java.util.List;

public class TokenizerPostfix {

    private final List<String> tokens;
    private int position;

    public TokenizerPostfix(String expression) {
        this.tokens = tokenize(expression);
        this.position = 0;
        System.out.println("[Tokenizer] Tokens: " + tokens); // pour debug
    }

    public boolean hasNext() {
        return position < tokens.size();
    }

    public List<String> getTokens() {
        return tokens;
    }

    public String next() {
        return tokens.get(position++);
    }

    public String peek() {
        return tokens.get(position);
    }

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

    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static NumericValue applyOperator(String operator, List<NumericValue> values) {
        if (values.isEmpty()) throw new RuntimeException("No values to apply operator");

        NumericValue result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            NumericValue v = values.get(i);
            switch (operator) {
                case "+" -> result = result.add(v);
                case "-" -> result = result.subtract(v);
                case "*" -> result = result.multiply(v);
                case "/" -> result = result.divide(v);
                default -> throw new RuntimeException("Unknown operator: " + operator);
            }
        }
        return result;
    }
}

