import calculator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    public static Expression parse(String input) throws Exception {
        try {
            List<String> tokens = tokenize(input);
            return parseExpression(new ExpressionParser.TokenStream(tokens));
        } catch (Exception e) {
            throw new Exception("Invalid expression syntax");
        }
    }

    private static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        String regex = "\\d+\\.\\d+|\\d+|[()+\\-*/]";
        Matcher matcher = Pattern.compile(regex).matcher(input.replaceAll("\\s+", ""));
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }


    private static Expression parseExpression(ExpressionParser.TokenStream tokens) throws Exception {
        Expression left = parseTerm(tokens);
        while (tokens.hasNext() && (tokens.peek().equals("+") || tokens.peek().equals("-"))) {
            String op = tokens.next();
            Expression right = parseTerm(tokens);
            if (op.equals("+")) {
                left = new Plus(Arrays.asList(left, right));
            } else {
                left = new Minus(Arrays.asList(left, right));
            }
        }
        return left;
    }

    private static Expression parseTerm(ExpressionParser.TokenStream tokens) throws Exception {
        Expression left = parseFactor(tokens);
        while (tokens.hasNext() && (tokens.peek().equals("*") || tokens.peek().equals("/"))) {
            String op = tokens.next();
            Expression right = parseFactor(tokens);
            if (op.equals("*")) {
                left = new Times(Arrays.asList(left, right));
            } else {
                left = new Divides(Arrays.asList(left, right));
            }
        }
        return left;
    }

    private static Expression parseFactor(ExpressionParser.TokenStream tokens) throws Exception {
        String token = tokens.next();
        if (token.equals("(")) {
            Expression expr = parseExpression(tokens);
            if (!tokens.next().equals(")")) {
                throw new Exception("Missing closing parenthesis");
            }
            return expr;
        } else {
            try {
                double value = Double.parseDouble(token); // ✅
                return new MyNumber(value);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid number: " + token);
            }
        }
    }

    private static class TokenStream {
        private final List<String> tokens;
        private int position = 0;

        public TokenStream(List<String> tokens) {
            this.tokens = tokens;
        }

        public boolean hasNext() {
            return position < tokens.size();
        }

        public String next() {
            return hasNext() ? tokens.get(position++) : null;
        }

        public String peek() {
            return hasNext() ? tokens.get(position) : null;
        }
    }
}