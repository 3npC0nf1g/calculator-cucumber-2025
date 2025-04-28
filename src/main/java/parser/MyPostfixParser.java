package parser;

import calculator.values.NumericValue;
import calculator.values.RealValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static parser.Tokenizer.applyOperator;
import static parser.Tokenizer.isOperator;

public class MyPostfixParser {

    public static void main(String[] args) {
        String expr1 = "((4,5,6)+,(7,(5,2,7)/)+,9)";
        String expr2 = "((4 5 6)+ (7 (5 2 7)/)+ 9)";

        System.out.print(expr1+" = ");
        System.out.println(evaluate(expr1)); // Should print the result
        System.out.print(expr2+" = ");
        System.out.println(evaluate(expr2)); // Should print the result
    }

    public static NumericValue evaluate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        //System.out.println("Tokens: " + tokenizer.getTokens());
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
                NumericValue result = new RealValue(1,10);
                for (NumericValue v : values) {
                    result=result.multiply(v);
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
        return new RealValue(new BigDecimal(Double.parseDouble(token)),10);
    }

}