package parser;

import calculator.Calculator;
import calculator.values.NumericValue;
import calculator.values.RealValue;
import calculator.values.ComplexValue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import static parser.Tokenizer.*;

public class MyPrefixParser {

    public static void main(String[] args) {
        String exprPrefixWithCommas = "(* 2, (+ [1+2i],[3-4i]), (+ (sin 30),(cos 60)))";
        String exprPrefixWithoutCommas = "* (+ 2 2)  3";
        ExpressionParser.mycalculator.setUseRadians(false);

        System.out.print(exprPrefixWithCommas + " = ");
        System.out.println(evaluate(exprPrefixWithCommas));
        System.out.print(exprPrefixWithoutCommas + " = ");
        System.out.println(evaluate(exprPrefixWithoutCommas));
    }

    public static NumericValue evaluate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        return parseExpression(tokenizer);
    }

    private static NumericValue parseExpression(Tokenizer tokenizer) {
        if (!tokenizer.hasNext()) throw new RuntimeException("Unexpected end of expression");

        String token = tokenizer.peek();
        //System.out.println("[DEBUG] peek token = " + token);

        if (token.equals("(")) {
            tokenizer.next(); // consume '('
            //System.out.println("[DEBUG] found '('");

            token = tokenizer.peek();
            if (isFunction(token)) {
                token = tokenizer.next();
                //System.out.println("[DEBUG] function = " + token);
                NumericValue arg = parseExpression(tokenizer);
                if (!tokenizer.peek().equals(")")) throw new RuntimeException("Expected ')' after function argument");
                tokenizer.next(); // consume ')'
                NumericValue result = applyFunction(token, arg);
                //System.out.println("[DEBUG] result of " + token + "(" + arg + ") = " + result);
                return result;
            }
            if (isOperator(token)) {
                token = tokenizer.next();
                //System.out.println("[DEBUG] operator = " + token);
                List<NumericValue> args = new ArrayList<>();
                while (!tokenizer.peek().equals(")")) {
                    args.add(parseExpression(tokenizer));
                    if (tokenizer.hasNext() && tokenizer.peek().equals(",")) {
                        tokenizer.next(); // skip ','
                        //System.out.println("[DEBUG] skip ','");
                    }
                }
                tokenizer.next(); // consume ')'
                NumericValue result = applyOperator(token, args);
                //System.out.println("[DEBUG] result of operator " + token + " on " + args + " = " + result);
                return result;
            }

            // Sinon, c'est juste une multiplication implicite entre plusieurs termes entre ()
            List<NumericValue> values = new ArrayList<>();
            while (!tokenizer.peek().equals(")")) {
                values.add(parseExpression(tokenizer));
                if (tokenizer.hasNext() && tokenizer.peek().equals(",")) {
                    tokenizer.next(); // skip ','
                    //System.out.println("[DEBUG] skip ','");
                }
            }
            tokenizer.next(); // consume ')'
            NumericValue product = new RealValue(1, 10);
            for (NumericValue v : values) {
                product = product.multiply(v);
            }
            //System.out.println("[DEBUG] product of values " + values + " = " + product);
            return product;
        }

        if (isOperator(token)) {
            token = tokenizer.next(); // consume operator
            //System.out.println("[DEBUG] operator standalone = " + token);

            List<NumericValue> args = new ArrayList<>();
            // Directement lire au moins 2 arguments
            args.add(parseExpression(tokenizer));
            args.add(parseExpression(tokenizer));
            // Lire en plus autant d'arguments supplémentaires que possible
            while (tokenizer.hasNext() && !tokenizer.peek().equals(")") && !isOperator(tokenizer.peek())) {
                args.add(parseExpression(tokenizer));
            }
            NumericValue result = applyOperator(token, args);
            //System.out.println("[DEBUG] result of operator " + token + " on " + args + " = " + result);
            return result;
        }

        if (isFunction(token)) {
            token = tokenizer.next();
            //System.out.println("[DEBUG] function standalone = " + token);
            NumericValue arg = parseExpression(tokenizer);
            NumericValue result = applyFunction(token, arg);
            //System.out.println("[DEBUG] result of function " + token + " on " + arg + " = " + result);
            return result;
        }

        // sinon, c'est un nombre ou un complexe
        token = tokenizer.next();
        //System.out.println("[DEBUG] raw token = " + token);

        if (token.startsWith("[")) {
            NumericValue complex = parseComplex(token);
            //System.out.println("[DEBUG] parsed complex = " + complex);
            return complex;
        }

        NumericValue real = new RealValue(new java.math.BigDecimal(Double.parseDouble(token)), 10);
        //System.out.println("[DEBUG] parsed real = " + real);
        return real;
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

    private static NumericValue applyOperator(String op, List<NumericValue> args) {
        if (args.isEmpty()) throw new RuntimeException("No arguments for operator: " + op);
        NumericValue result = args.get(0);
        for (int i = 1; i < args.size(); i++) {
            switch (op) {
                case "+": result = result.add(args.get(i)); break;
                case "-": result = result.subtract(args.get(i)); break;
                case "*": result = result.multiply(args.get(i)); break;
                case "/": result = result.divide(args.get(i)); break;
            }
        }
        return result;
    }

    private static NumericValue applyFunction(String func, NumericValue arg) {
        double value = Double.parseDouble(arg.toString());
        Calculator c=ExpressionParser.mycalculator;
        switch (func) {
            case "sin": return new RealValue(c.sin(value), 10);
            case "cos": return new RealValue(c.cos(value), 10);
            case "tan": return new RealValue(c.tan(value), 10);
            default: throw new RuntimeException("Unknown function: " + func);
        }
    }
}
