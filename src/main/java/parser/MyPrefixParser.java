package parser;

import calculator.values.NumericValue;
import calculator.values.RealValue;

import java.math.BigDecimal;
import java.util.*;

import static parser.Tokenizer.*;

public class MyPrefixParser {

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
            NumericValue sum = new RealValue(1,10);
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
        return new RealValue(new BigDecimal(Double.parseDouble(token)),10);
    }


}
