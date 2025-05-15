package parser;
import calculator.values.NumericValue;
import java.util.List;

/**
 * The MyPostfixParser class provides parsing and evaluation of arithmetic expressions
 * written in a custom Postfix (Reverse Polish) notation.
 *
 * Postfix expressions are transformed into Prefix expressions and then evaluated
 * using MyPrefixParser.
 *
 * Supported features:
 * - Arithmetic operators: +, -, *, /, ^, %
 * - Functions like sin, cos, etc. via MyPrefixParser
 * - Complex numbers [a+bi] and nested expressions
 *
 * Example:
 *   ((4,5,6)+,(7,(5,2,7)/)+,9) will be transformed and evaluated properly.
 *
 * Author: Hugue
 */

public class MyPostfixParser {

    /**
     * Entry point to test several example postfix expressions.
     */
    public static void main(String[] args) {
        String expr1 = "((4,5,6)+,(7,(5,2,7)/)+,9)";
        String expr2 = "((2 ([1+2i] [3-4i]+) (sin(30) cos(60)+)*))";
        String expr3 = "((2,([1+2i],[3-4i]+),(sin(30)),(cos(60))+)*)";
        String expr4 = "((2 ([1+2i] [3-4i]+) sin(30) cos(60)+)*)";

        ExpressionParser.mycalculator.setUseRadians(false);
        MyPostfixParser myPostfixParser = new MyPostfixParser();

        System.out.print(expr1 + " = ");
        System.out.println(myPostfixParser.evaluate(expr1));
        System.out.print(expr2 + " = ");
        System.out.println(myPostfixParser.evaluate(expr2));
        System.out.print(expr3 + " = ");
        System.out.println(myPostfixParser.evaluate(expr3));
        System.out.println();
        System.out.print(expr4 + " = ");
        System.out.println(myPostfixParser.evaluate(expr4));
    }

    /**
     * Default constructor.
     */
    public MyPostfixParser() {};

    /**
     * Evaluates a postfix expression by converting it to prefix and delegating to MyPrefixParser.
     *
     * @param expression Postfix arithmetic expression
     * @return The result as a NumericValue
     */
    public NumericValue evaluate(String expression) {
        expression = expression.replaceAll(",", " ");
        if(expression.charAt(0) != '(' || expression.charAt(expression.length()-1) != ')') {
            expression = "(" + expression + ")";
        }
        String prefix = custompostfixToPrefix(expression);
        //System.out.println(prefix);
        MyPrefixParser myPrefixParser = new MyPrefixParser();
        NumericValue result = myPrefixParser.evaluate(prefix);

        return result;
    }


    /**
     * Converts a custom postfix expression into prefix format for evaluation.
     *
     * @param postfixExpression the original postfix string
     * @return a valid prefix-formatted expression
     */
    public String custompostfixToPrefix(String postfixExpression) {
        TokenizerPostfix tokenizer = new TokenizerPostfix(postfixExpression);
        String res="";
        List<String> tokens = tokenizer.getTokens();
        int last_parentheses = 0;
        for(int i=0; i<tokens.size(); i++) {
            if(tokens.get(i).equals("(")) {
                last_parentheses = i;
            }
            else if(isOperator(tokens.get(i))) {
                last_parentheses=getOpenParenthesisOf(tokens,i);
                tokens.add(last_parentheses, tokens.get(i));
                tokens.remove(i+1);
            }
        }
        for(int i=0; i<tokens.size(); i++) {
            res+=tokens.get(i)+" ";
        }
        return res;
    }

    /**
     * Finds the index of the matching opening parenthesis for a given token index.
     * Used during postfix-to-prefix transformation.
     *
     * @param list Token list
     * @param end Current index
     * @return Index of the matching opening parenthesis
     */
    private int getOpenParenthesisOf(List<String> list,int end)
    {
        int res=0;
        int dept_target=0;
        for(int i=0; i<end; i++) {
            if(list.get(i).equals("(")) {
                res=i;
                dept_target++;
            }
            else if(list.get(i).equals(")")) {
                dept_target--;
            }
        }
        int dept=0;
        int pos=end-1;

        if(!list.get(pos).equals(")"))
        {
            for(int i=0;i<end-1;i++)
            {
                if(list.get(i).equals("("))
                {
                    pos=i;
                }
            }
            pos=pos+1;
        }
        else {
            while (pos > 0) {

                if (list.get(pos).equals(")")) {
                    dept += 1;
                } else if (list.get(pos).equals("(")) {
                    dept -= 1;

                }

                if (dept == 0)
                    break;

                pos -= 1;


            }
        }
        return pos;
    }

    /**
     * Checks if the token is an arithmetic operator.
     *
     * @param token the token to check
     * @return true if operator, false otherwise
     */
    private  boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") ||
                token.equals("/") || token.equals("^") || token.equals("%");
    }

}
