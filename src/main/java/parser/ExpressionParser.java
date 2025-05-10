package parser;
import calculator.Calculator;
import calculator.values.*;
import java.util.Arrays;
import static parser.MyInfixParser.buildTree;

/**
 * The ExpressionParser class is responsible for parsing and evaluating arithmetic expressions
 * written in various notations: Infix, Prefix, Postfix, or Auto-detected.
 *
 * It supports:
 * - Integers, decimals, fractions (a/b), and complex numbers [a+bi]
 * - Scientific notation via E(x) = 10^x
 * - Trigonometric functions: sin(x), cos(x), tan(x)
 * - Dynamic switching between radian and degree mode
 * - Remembering and reusing the last evaluated result
 *
 * Author: Hugue
 */

public class ExpressionParser {

    /** Angle mode: radians or degrees */
    public enum Mode {RADIANS, DEGREES}
    /** Notation mode: how the expression is written */
    public enum Notation {INFIX, PREFIX, POSTFIX, AUTO}
    /** Display mode: decimal or fraction output */
    public enum Display {FRACTION, DECIMAL}

    private static Mode mode = Mode.DEGREES;
    private static Notation notation = Notation.INFIX;
    private static Display display = Display.DECIMAL;

    /** Last evaluated result, stored globally */
    public static NumericValue last_result = new IntegerValue(0);

    /** Calculator instance used for evaluation */
    public static Calculator mycalculator = new Calculator();

    /**
     * Sets the angle mode and updates the calculator accordingly.
     * @param m Mode.DEGREES or Mode.RADIANS
     */
    public static void setMode(Mode m) {
        mode = m;
        if(mode == Mode.RADIANS)
            mycalculator.setUseRadians(false);
        else
            mycalculator.setUseRadians(true);
    }

    /**
     * Returns the current angle mode.
     */
    public static Mode getMode() {
        return mode;
    }

    /**
     * Sets the current notation mode.
     */
    public static void setNotation(Notation n) {
        notation = n;
    }

    /**
     * Returns the current notation mode.
     */
    public static Notation getNotation() {
        return notation;
    }

    /**
     * Sets the display output mode.
     */
    public static void setDisplay(Display p) {
        display =p;
    }

    /**
     * Returns the current display output mode.
     */
    public static Display getDisplay() {
        return display;
    }

    /**
     * Test/debug method to try different example expressions.
     */
    public static void main(String[] args) throws Exception {
        ExpressionParser e=new ExpressionParser();

        NumericValue expr = e.parse("((([2+3i],[1-1i]+),([4+0i],[2+2i]+)*),(([5+0i],[0+2i]-)) +)");
        expr = e.parse("((2 ([1+2i] [3-4i]+) (sin(30) cos(60)+)*))");
        expr = e.parse("((4,5,6)+,(7,(5,2,7)/)+,9)");

        expr = e.parse("(+ (* (+ [2+3i] [1-1i]) (+ [4+0i] [2+2i])) (- [5+0i] [0+2i]))");
        expr = e.parse("(+(4,5,6),+(7,/(5,2,7)),9)");

        expr = e.parse("((4 + 5 + 6) * (7 + (5 / 2 / 7)) * 9)");
        expr = e.parse("        (((1 + 2) * (3 + 4)) / (2 + 5))");
    }

    /**
     * Constructor: initializes the calculator in degree mode by default.
     */
    public ExpressionParser(){
        ExpressionParser.mycalculator.setUseRadians(false);
    }

    /**
     * Parses and evaluates a given input string based on the current notation mode.
     * Supports 'res' for last result and 'pi' as the PI constant.
     *
     * @param input the arithmetic expression to evaluate
     * @return a NumericValue result, or null if evaluation fails
     * @throws Exception if parsing fails internally
     */
    public NumericValue parse(String input) throws Exception {
        NumericValue result = null;

        if(input.contains("res"))
            input=input.replaceAll("res",last_result.toString());

        if(input.contains("pi"))
            input=input.replaceAll("pi",""+Math.PI);


        if(notation==Notation.INFIX)
        {
            result=InfixEvaluator(input);
        }
        else if(notation==Notation.PREFIX)
        {
            result=PrefixEvaluator(input);
        }
        else if(notation==Notation.POSTFIX)
        {
            result=PostfixEvaluator(input);
        }
        else {
            result=AutoEvaluator(input);
        }
        if(result!=null) {
            if (result.toString().equals("NaN"))
                last_result = new IntegerValue(0);
            else
                last_result = result;
        }
        return result;
    }

    /**
     * Detects whether the expression is in prefix notation.
     */
    public Boolean isPrefix(String input) {
        boolean res=false;
        for(int i=0; i<input.length(); i++) {
            if(Character.isDigit(input.charAt(i)))
                break;
            else if(isOperator(""+input.charAt(i))) {
                res=true;
                break;
            }
            else {
            }
        }
        return res;
    }

    /**
     * Detects whether the expression is in infix notation.
     */
    public Boolean isInfix(String input) {
        boolean res=false;
        //System.out.println("infix ? "+input);
        for(int i=0; i<input.length(); i++) {
            if(Character.isDigit(input.charAt(i))) {
                // i is a digit
                for (int j = i + 1; j < input.length(); j++) {
                    // j must be a digit or an space
                    if (Character.isDigit(input.charAt(j)) || ("" + input.charAt(j)).equals(" ") ) {}
                    else if (("" + input.charAt(j)).equals("[") || ("" + input.charAt(j)).equals("c") || ("" + input.charAt(j)).equals("s") || ("" + input.charAt(j)).equals("t"))
                        return false;
                    else if (isOperator("" + input.charAt(j))) {
                        return true;
                    } else {
                    }
                }
            }
            //operator
            else if(isOperator(""+input.charAt(i))) {
                res=false;
                break;
            }
            // complex or function first
            else if((""+input.charAt(i)).equals("[") ||(""+input.charAt(i)).equals("c") ||(""+input.charAt(i)).equals("s") ||(""+input.charAt(i)).equals("t") ) {
                int j=i+1;
                if((""+input.charAt(i)).equals("["))
                {
                    for(j=i+1; j<input.length(); j++) {
                    if((""+input.charAt(j)).equals("]")) {
                        i = j;
                        break;
                    }
                    }
                }
                else
                {
                    for(j=i+1; j<input.length(); j++) {
                        if((""+input.charAt(j)).equals(")")) {
                            i = j;
                            break;
                        }
                    }
                }
                // Complex value before of function
                for(j=i+1; j<input.length(); j++) {
                    // j must space or an operator
                    if(Character.isDigit(input.charAt(j)) || (""+input.charAt(j)).equals("[")  ||(""+input.charAt(j)).equals("c") ||(""+input.charAt(j)).equals("s") ||(""+input.charAt(j)).equals("t"))
                        return false;
                    else if(isOperator(""+input.charAt(j))) {
                        return true;
                    }
                    else {}
                }
            }

            else {
            }
        }
        return res;
    }

    /**
     * Internal helper: checks if a string is an arithmetic operator.
     */
    private static boolean isOperator(String token) {
        return Arrays.asList("+", "-", "*", "/").contains(token);
    }

    /** Evaluates an infix expression. */
    private NumericValue InfixEvaluator(String input) {
        NumericValue result=null;
        try {
            MyInfixParser.Node root = buildTree(input);
            result = MyInfixParser.evaluate(root);
        } catch (Exception e) {
            System.err.println("Error during Infix evaluation:");
            System.err.println("    Please check your expression and notation");

            return null;
        }
        return result;
    }

    /** Evaluates a prefix expression. */
    private NumericValue PrefixEvaluator(String input) {
        NumericValue result=null;
        try {
            MyPrefixParser p = new MyPrefixParser();
            result=p.evaluate(input);
        } catch (Exception e) {
            System.err.println("Error during Prefix evaluation:");
            System.err.println("    Please check your expression and notation");

            return null;
        }
        return result;
    }

    /** Evaluates a postfix expression. */
    private NumericValue PostfixEvaluator(String input) {
        NumericValue result=null;
        try {
            MyPostfixParser p = new MyPostfixParser();
            result=p.evaluate(input);
        } catch (Exception e) {
            System.err.println("Error during Postfix evaluation:");
            System.err.println("    Please check your expression and notation");

            return null;
        }
        return result;
    }

    /** Automatically detects and evaluates the expression's notation. */
    private NumericValue AutoEvaluator(String input) {
        NumericValue result=null;
        if (isPrefix(input)) {
            System.out.print("Prefix : ");
            result =PrefixEvaluator(input);
        } else if (isInfix(input) || ("" + input.charAt(0)).equals("s") || ("" + input.charAt(0)).equals("c") || ("" + input.charAt(0)).equals("s") || ("" + input.charAt(0)).equals("t") || ("" + input.charAt(0)).equals("[")) {
            {
                System.out.print("Infix : ");
                result = InfixEvaluator(input);
            }
        } else {
            System.out.print("Postfix : ");
            result = PostfixEvaluator(input);
        }
        return result;
    }
}