package parser;

import calculator.Calculator;
import calculator.values.*;

import java.util.Arrays;

import static parser.MyInfixParser.buildTree;

public class ExpressionParser {

    public enum Mode { RAD, DEG }
    private static Mode mode = Mode.DEG;

    public static void setMode(Mode m) {
        mode = m;
    }

    public static Mode getMode() {
        return mode;
    }

    public static NumericValue last_result=new IntegerValue(0);

    public static Calculator mycalculator = new Calculator();

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

    public ExpressionParser(){
        ExpressionParser.mycalculator.setUseRadians(false);
    }

    public NumericValue parse(String input) throws Exception {
        NumericValue result = null;

        if(input.contains("res"))
            input=input.replaceAll("res",last_result.toString());

        if(input.contains("pi"))
            input=input.replaceAll("pi",""+Math.PI);

        if(input.contains("pi"))
            input=input.replaceAll("pi",""+Math.PI);

        if(isPrefix(input)) {
            //System.out.println("Prefix " + input);
            MyPrefixParser p = new MyPrefixParser();
            result=p.evaluate(input);
        }

        else if(isInfix(input)) {
            //System.out.println("Infix " + input);
            MyInfixParser.Node root = buildTree(input);
            result = MyInfixParser.evaluate(root);
        }
        else {
            //System.out.println("Postfix " + input);
            MyPostfixParser p = new MyPostfixParser();
            result = p.evaluate(input);
        }

        if(result.toString().equals("NaN"))
            last_result=new IntegerValue(0);
        else
            last_result=result;
        return result;
    }


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

    public Boolean isInfix(String input) {
        boolean res=false;
        //System.out.println("infix ? "+input);
        for(int i=0; i<input.length(); i++) {
            if(Character.isDigit(input.charAt(i))) {
                // i is a digit
                for(int j=i+1; j<input.length(); j++) {
                    // j must be a digit, an operator
                    if(Character.isDigit(input.charAt(j)) || (""+input.charAt(j)).equals("[")  ||(""+input.charAt(j)).equals("c") ||(""+input.charAt(j)).equals("s") ||(""+input.charAt(j)).equals("t"))
                        return false;
                    else if(isOperator(""+input.charAt(j))) {
                        return true;
                    }
                    else {}
                }
            }
            else if(isOperator(""+input.charAt(i))) {
                res=false;
                break;
            }
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
                //System.out.println(i+" : "+input.charAt(i));
                // Complex value before of function

                for(j=i+1; j<input.length(); j++) {
                    // j must be a digit, an operator
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

    private static boolean isOperator(String token) {
        return Arrays.asList("+", "-", "*", "/").contains(token);
    }


}