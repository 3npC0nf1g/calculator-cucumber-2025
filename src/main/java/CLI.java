/**
 * Command Line Interface Calculator.
 *
 * This class provides an interactive terminal calculator.
 * It supports arithmetic expressions in Infix, Prefix, Postfix notations.
 *
 * Features:
 * - Supports decimals, fractions (a/b), and complex numbers [a+bi]
 * - Accepts scientific notation using E(x), e.g., 1.8 * E(2) = 180
 * - Supports trigonometric functions: sin(x), cos(x), tan(x)
 * - Accepts constants: pi, res (last result)
 *
 * Available commands:
 * - infix, prefix, postfix : change input notation
 * - mode : toggle between radians and degrees
 * - display : toggle between decimal and fraction display modes
 * - last : re-execute the previous expression
 * - help : show usage instructions
 * - quit / exit : exit the program
 *
 * Author: Hugue
 */

import calculator.*;
import calculator.values.NumericValue;
import parser.ExpressionParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CLI {

    /** ANSI escape code for cyan text */
    private static final String CYAN = "\u001B[36m";
    /** ANSI escape code to reset color */
    private static final String RESET = "\u001B[0m";
    /** ANSI escape code for white text */
    private static final String WHITE = "\u001B[97m";

    /** Whether it's the first use of the CLI, used to delay the prompt. */
    static boolean first_use = true;

    /** Last evaluated numeric result. */
    static NumericValue expr = null;

    /** Last successfully entered input (expression) */
    static String lastInput = null;

    /**
     * Entry point of the CLI calculator.
     *
     * @param args Unused command-line arguments
     */

    public static void main(String[] args) {
        runCLI();
    }

    /**
     * Prints the welcome message and current settings.
     */
    public static void print_init() {
        System.out.println(CYAN + "Welcome to the CLI Calculator!" + RESET);

        System.out.println(CYAN + "-" + WHITE + " It's currently using " + CYAN + capitalize("infix") + WHITE + " notation" + CYAN + " :" + WHITE);
        System.out.println(" ".repeat(5) + WHITE + "Tap '" + CYAN + "prefix" + WHITE + "' to use Prefix notation");
        System.out.println(" ".repeat(5)+ WHITE + "Tap '" + CYAN + "postfix" + WHITE + "' to use Postfix notation");

        System.out.println(CYAN + "-" + WHITE + " It's currently using " + CYAN + capitalize("degrees") + CYAN + " :" + WHITE);
        System.out.println(" ".repeat(5) + WHITE + "Tap '" + CYAN + "mode" + WHITE + "' to toggle between Radians and Degrees");

        System.out.println(CYAN + "-" + WHITE + " It's currently using " + CYAN + capitalize("Decimal") + WHITE + " mode" + CYAN + " :" + WHITE);
        System.out.println(" ".repeat(5) + WHITE + "Tap '" + CYAN + "display" + WHITE + "' to toggle between Fraction and Decimal mode");

        System.out.println(CYAN + "-" + WHITE + " Tap '" + CYAN + "help" + WHITE + "' for instructions");
        System.out.println(CYAN + "-" + WHITE + " Tap '" + CYAN + "quit" + WHITE + "' to close the program" + RESET);
    }


    /**
     * Displays the list of supported commands and instructions.
     */
    public static void print_menu() {
        System.err.println(CYAN + "Instructions :" + WHITE);
        if (ExpressionParser.getNotation() == ExpressionParser.Notation.INFIX)
            System.err.println(CYAN + "-" + WHITE + " Enter an Infix arithmetic expression" + CYAN + " (e.g.:" + WHITE + " 3 + 4 * 2" + CYAN + ")");
        if (ExpressionParser.getNotation() == ExpressionParser.Notation.PREFIX)
            System.err.println(CYAN + "-" + WHITE + " Enter a Prefix arithmetic expression" + CYAN + " (e.g.:" + WHITE + " + 3 (* 4 2)" + CYAN + ")");
        if (ExpressionParser.getNotation() == ExpressionParser.Notation.POSTFIX)
            System.err.println(CYAN + "-" + WHITE + " Enter a Postfix arithmetic expression" + CYAN + " (e.g.:" + WHITE + " (4 2 *) 3 +" + CYAN + ")");


        System.err.println(CYAN + "Information :" + WHITE);
        System.err.println(CYAN + "-" + WHITE + " It's currently using " + CYAN + capitalize(ExpressionParser.getNotation().toString()) + WHITE + " notation" + CYAN + " :" + WHITE);

        if (ExpressionParser.getNotation() != ExpressionParser.Notation.INFIX)
            System.err.println(" ".repeat(5) + WHITE + "Tap '" + CYAN + "infix" + WHITE + "' to use Infix notation");
        if (ExpressionParser.getNotation() != ExpressionParser.Notation.PREFIX)
            System.err.println(" ".repeat(5) + WHITE + "Tap '" + CYAN + "prefix" + WHITE + "' to use Prefix notation");
        if (ExpressionParser.getNotation() != ExpressionParser.Notation.POSTFIX)
            System.err.println(" ".repeat(5) + WHITE + "Tap '" + CYAN + "postfix" + WHITE + "' to use Postfix notation");

        System.err.println(CYAN + "-" + WHITE + " Supported types" + CYAN + ":" + WHITE + " integers, decimals, fractions (" + CYAN + "a/b" + WHITE + "), complex " + CYAN + "[a+bi]" + WHITE);
        System.err.println(CYAN + "-" + WHITE + " Scientific notation supported" + CYAN + " (e.g.:" + WHITE + " 1.8 * " + CYAN + "E" + WHITE + "(X) = 1.8*10^X" + CYAN + ")");

        System.err.println(CYAN + "-" + WHITE + " You can write '" + CYAN + "res" + WHITE + "' to use your last result");
        System.err.println(CYAN + "-" + WHITE + " You can write '" + CYAN + "pi" + WHITE + "' to use π");
        System.err.println(CYAN + "-" + WHITE + " You can write '" + CYAN + "last" + WHITE + "' to run again the last operation");
        System.err.println(CYAN + "-" + WHITE + " Supported functions in "+ CYAN + capitalize("infix") + WHITE + CYAN + ":\n"
                +CYAN +" ".repeat(5)+" sin"+WHITE+"(x),"+CYAN+" cos"+WHITE+"(x), "+CYAN+"tan"+WHITE+"(x)"+CYAN +", ln"+WHITE+"(x)\n"
                +CYAN +" ".repeat(5)+" power"+WHITE+"(X,Y) ≡ X^Y ,"+CYAN+" root"+WHITE+"(X,Y) ≡ Y^(1/X)\n"
                +CYAN +" ".repeat(5)+" log"+WHITE+"(X,Y) ≡ logX(Y), "+CYAN+" sqrt"+WHITE+"(x)," +CYAN +" inv"+WHITE+"(x)"
        );

        System.err.println(CYAN + "-" + WHITE + " It's currently using " + CYAN + capitalize(ExpressionParser.getDisplay().toString()) + WHITE + " mode" + CYAN + " :" + WHITE);
        System.err.println(" ".repeat(5)+ WHITE + " Tap '" + CYAN + "display" + WHITE + "' to toggle between Fraction and Decimal mode");

        System.err.println(CYAN + "-" + WHITE + " It's currently using " + CYAN + capitalize(ExpressionParser.getMode().toString()) + WHITE + " mode" + CYAN + " :" + WHITE);
        System.err.println(" ".repeat(5)+ WHITE + " Tap '" + CYAN + "mode" + WHITE + "' to toggle between radians and degrees");
        System.err.println(CYAN + "-" + WHITE + " Tap '" + CYAN + "quit" + WHITE + "' to close the program" + RESET);
    }


    /**
     * Capitalizes the first character of the given string.
     *
     * @param text The string to capitalize
     * @return The capitalized string
     */
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    /**
     * Starts the interactive CLI calculator loop.
     * Handles user input, commands, and expression evaluation.
     */
    public static void runCLI() {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser exp=new ExpressionParser();
        print_init();

        Set<String> commands = new HashSet<>(Arrays.asList(
                "infix", "prefix", "postfix", "mode", "display", "help", "exit"
        ));

        while (true) {
            if(!first_use) {
                try {
                    Thread.sleep(500); // 1000 millisecondes = 1 seconde
                    first_use=false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.err.print(WHITE+">>> "+RESET);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                print_menu();
                continue;
            } else if (input.equalsIgnoreCase("mode")) {
                if(ExpressionParser.getMode()== ExpressionParser.Mode.DEGREES) {
                    ExpressionParser.setMode(ExpressionParser.Mode.RADIANS);
                    String txt="Mode set to Radians";
                    System.err.println("\u001B[97m " + txt + "\u001B[0m");
                }
                else
                {
                    ExpressionParser.setMode(ExpressionParser.Mode.DEGREES);
                    String txt="Mode set to Degrees";
                    System.err.println("\u001B[97m " + txt + "\u001B[0m");
                }
                continue;
            }
            else if (input.equalsIgnoreCase("Infix")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.INFIX);
                String txt="Notation set to " + CYAN+capitalize(ExpressionParser.getNotation().toString().toLowerCase());
                System.err.println("\u001B[97m " + txt + "\u001B[0m");
            }
            else if (input.equalsIgnoreCase("Prefix")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.PREFIX);
                String txt="Notation set to " + CYAN+capitalize(ExpressionParser.getNotation().toString().toLowerCase());
                System.err.println("\u001B[97m " + txt + "\u001B[0m");
            }
            else if (input.equalsIgnoreCase("Postfix")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.POSTFIX);
                String txt="Notation set to " + CYAN+capitalize(ExpressionParser.getNotation().toString().toLowerCase());
                System.err.println("\u001B[97m " + txt + "\u001B[0m");
                continue;
            }

            else if (input.equalsIgnoreCase("display")) {
                if(ExpressionParser.getDisplay()== ExpressionParser.Display.FRACTION)
                {
                    ExpressionParser.setDisplay(ExpressionParser.Display.DECIMAL);
                }
                else
                {
                    ExpressionParser.setDisplay(ExpressionParser.Display.FRACTION);
                }
                String txt="Display mode set to " + CYAN+capitalize(ExpressionParser.getDisplay().toString())+WHITE;
                System.err.println("\u001B[97m " + txt + "\u001B[0m");
                continue;
            }
            if (input.equalsIgnoreCase("last")) {
                if (lastInput == null) {
                    System.err.println(WHITE + "No previous expression to repeat." + RESET);
                    continue;
                }
                input = lastInput;
                System.err.println(WHITE +">>> "+ input + RESET);
            }

            if (commands.contains(input.toLowerCase())) {
                continue;
            }
            try {
                expr = exp.parse(input);
                lastInput = input;
                if(expr!=null)
                {
                    System.err.println(WHITE + expr + "\u001B[0m");
                    //System.err.println("= "+expr);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
