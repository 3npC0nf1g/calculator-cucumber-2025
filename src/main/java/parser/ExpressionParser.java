package parser;

import calculator.Calculator;
import calculator.values.*;

public class ExpressionParser {

    public enum Mode { RAD, DEG }
    private static Mode mode = Mode.DEG;

    public static void setMode(Mode m) {
        mode = m;
    }

    public static Mode getMode() {
        return mode;
    }

    public static Calculator mycalculator = new Calculator();



    public static NumericValue parse(String input) throws Exception {
        // Enlever la première et la dernière parenthèse pour parser l'intérieu


        System.out.println("Parsing " + input);
        NumericValue result = null;
        if(input.charAt(0) == '(') {
            result = parenthesis(input);
        }

        return null;

    }

    public static NumericValue parenthesis(String input) throws Exception {
        NumericValue result = null;
        String help=input.substring(1);
        if(input.charAt(0) == '(') {
            result = parenthesis(input);
        }
        System.out.println("Parenthesis " + input);


        return result;
    }


}