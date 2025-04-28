package parser;

import calculator.Calculator;
import calculator.values.NumericValue;
import calculator.values.RealValue;
import calculator.values.ComplexValue;

import java.math.BigDecimal;
import java.util.*;

import static parser.Tokenizer.*;

public class MyPrefixParser {

    public static void main(String[] args) {
        String exprPrefixWithCommas = "(+(4,5,6),+(7,/(5,2,7)),9)";;
        String exprPrefixWithoutCommas = "(* (+(4,5,6)) (+(7,/(5,2,7))) 9)";
        ExpressionParser.mycalculator.setUseRadians(false);

        System.out.print(exprPrefixWithCommas + " = ");
        System.out.println(evaluate(exprPrefixWithCommas));
        System.out.println("\n");
        System.out.print(exprPrefixWithoutCommas + " = ");
        System.out.println(evaluate(exprPrefixWithoutCommas));
    }

    static List<String> tokens = new ArrayList<>();

    public static NumericValue evaluate(String expression) {
        if(expression.charAt(0) != '(') {
            expression = "(" + expression + ")";
        }
        Tokenizer tokenizer = new Tokenizer(expression);
        tokens=tokenizer.getTokens();
        for (String token : tokens) {
            if (isOperator(token)) {
                operators.add(token);
            }
        }
        System.out.println("[DEBUG] operator restant= " + operators);


        System.out.println("Tokens: " + tokenizer.getTokens());

        NumericValue result = parseExpression(tokenizer);

        if (tokenizer.hasNext()) {
            throw new RuntimeException("Invalid expression: extra tokens remaining " + tokenizer.getTokens());
        }
        return result;
    }
    private static String lastOperator="";
    static List<String> operators = new ArrayList<>();


    private static NumericValue parseExpression(Tokenizer tokenizer) {
        if (!tokenizer.hasNext()) throw new RuntimeException("Unexpected end of expression");

        String token = tokenizer.peek();
        System.out.println("[DEBUG] peek token = " + token);

        if (token.equals("(")) {
            tokenizer.next(); // consume '('
            System.out.println("[DEBUG] found '('");

            if (!tokenizer.hasNext()) throw new RuntimeException("Unexpected end after '('");

            token = tokenizer.peek();
            if (isOperator(token)) {
                lastOperator = token;
                token = tokenizer.next(); // consume operator
                System.out.println("[DEBUG] operator in group = " + token);
                List<NumericValue> args = parseArguments(tokenizer);
                System.out.println("[DEBUG] args = " + args);
                NumericValue product =new RealValue(0, 10);
                System.out.println("[DEBUG] operator in group = " + token);
                System.out.println("[DEBUG] operator restant= " + operators);
                if(operators.size()==0)
                {
                    product = args.get(0);
                    for (int i = 1; i < args.size(); i++) {
                        product = product.multiply(args.get(i));
                    }
                    System.out.println("[DEBUG] product of values last implicite" + args + " = " + product);

                }
                else {
                    product = applyOperator(token, args);
                    operators.remove(token);
                }

                System.out.println("[DEBUG] result of operator " + token + " on " + args + " = " + product);
                return product;
            } else if (isFunction(token)) {
                token = tokenizer.next(); // consume function
                System.out.println("[DEBUG] function in group = " + token);
                NumericValue arg = parseExpression(tokenizer);
                if (!tokenizer.peek().equals(")")) throw new RuntimeException("Expected ')' after function argument");
                tokenizer.next(); // consume ')'
                return applyFunction(token, arg);
            } else {
                // Multiplication implicite
                List<NumericValue> values = new ArrayList<>();
                while (tokenizer.hasNext()) {
                    String next = tokenizer.peek();
                    if (next.equals(")")) {
                        tokenizer.next(); // consume ')'
                        break;
                    }
                    if (next.equals(",")) {
                        tokenizer.next(); // skip comma
                        continue;
                    }
                    values.add(parseExpression(tokenizer));
                }
                if (values.isEmpty()) return new RealValue(0, 10);
                NumericValue product =new RealValue(0, 10);
                if(lastOperator.isBlank()) {
                    product = values.get(0);
                    for (int i = 1; i < values.size(); i++) {
                        product = product.multiply(values.get(i));
                    }
                    System.out.println("[DEBUG] product of values last implicite" + values + " = " + product);
                }
                else
                {
                    System.out.println("[DEBUG] lastOperator" + " = " + lastOperator);
                    System.out.println("[DEBUG] values" + " = " + values);
                    product = applyOperator(lastOperator, values);
                    operators.remove(lastOperator);
                    lastOperator = "";
                    System.out.println("[DEBUG] sum of values " + values + " = " + product);

                }
                return product;
            }
        }

        else if (token.equals(")")) {
            tokenizer.next();
            throw new RuntimeException("Unexpected ')'");
        }

        else if (isOperator(token)) {
            token = tokenizer.next();
            System.out.println("[DEBUG] operator standalone = " + token);

            List<NumericValue> args = new ArrayList<>();
            if (tokenizer.hasNext() && tokenizer.peek().equals("(")) {
                tokenizer.next(); // consume '('
                args = parseArguments(tokenizer);
            } else {
                args.add(parseExpression(tokenizer));
                args.add(parseExpression(tokenizer));
            }

            if (args.isEmpty()) throw new RuntimeException("No arguments for operator: " + token);
            NumericValue result = applyOperator(token, args);
            System.out.println("[DEBUG] result of operator " + token + " on " + args + " = " + result);
            operators.remove(token);
            return result;
        }

        else if (isFunction(token)) {
            token = tokenizer.next();
            System.out.println("[DEBUG] function standalone = " + token);
            NumericValue arg = parseExpression(tokenizer);
            return applyFunction(token, arg);
        }

        else {
            token = tokenizer.next();
            System.out.println("[DEBUG] raw token = " + token);

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

    private static List<NumericValue> parseArguments(Tokenizer tokenizer) {
        List<NumericValue> args = new ArrayList<>();
        while (tokenizer.hasNext()) {
            String next = tokenizer.peek();
            if (next.equals(")")) {
                tokenizer.next(); // consume ')'
                break;
            }
            if (next.equals(",")) {
                tokenizer.next(); // skip ','
                continue;
            }
            args.add(parseExpression(tokenizer));
        }
        return args;
    }

    private static NumericValue parseComplex(String token) {
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

    private static boolean isOperator(String token) {
        return Arrays.asList("+", "-", "*", "/").contains(token);
    }

    private static boolean isFunction(String token) {
        return Arrays.asList("sin", "cos", "tan").contains(token);
    }

    private static NumericValue applyFunction(String func, NumericValue arg) {
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
