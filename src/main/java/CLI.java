import calculator.*;
import calculator.values.NumericValue;
import parser.ExpressionParser;

import java.util.Scanner;

public class CLI {

    public static void main(String[] args) {
        runCLI();
    }

    public static void print_init()
    {
        System.out.println("Welcome to the CLI Calculator!");
        System.out.println("- It's currently using Infix notation :");
        System.out.println("    Type 'Prefix' to use Prefix notation");
        System.out.println("    Type 'Postfix' to use Postfix notation");
        System.out.println("    Type 'Auto' to use Automatic notation detector");
        System.out.println("- It's currently using degrees :");
        System.out.println("    Type 'mode' to toggle between radians and degrees");
        System.out.println("- Type 'help' for instructions");
        System.out.println("- Type 'quit' to close the program");
    }

    public static void print_menu()
    {
        System.out.println("\nNotation : ");
        System.out.println("- It's currently using "+ExpressionParser.getNotation()+" :");
        if(ExpressionParser.getNotation()!= ExpressionParser.Notation.Infix);
        System.out.println("    Type 'Infix' to use Infix notation");
        if(ExpressionParser.getNotation()!= ExpressionParser.Notation.Prefix);
            System.out.println("    Type 'Prefix' to use Prefix notation");
        if(ExpressionParser.getNotation()!= ExpressionParser.Notation.Postfix);
            System.out.println("    Type 'Postfix' to use Postfix notation");
        if(ExpressionParser.getNotation()!= ExpressionParser.Notation.Auto);
            System.out.println("    Type 'Auto' to use Automatic notation detector");

        System.out.println("\nInstructions:");
        if(ExpressionParser.getNotation()== ExpressionParser.Notation.Infix);
            System.out.println("- Enter an Infix arithmetic expression (e.g., 3 + 4 * 2)");
        if(ExpressionParser.getNotation()== ExpressionParser.Notation.Prefix);
            System.out.println("- Enter a Prefix arithmetic expression (e.g., +3 (* 4 2)");
        if(ExpressionParser.getNotation()== ExpressionParser.Notation.Postfix);
            System.out.println("- Enter a Postfix arithmetic expression (e.g., (4 2 *) 3 +");
        if(ExpressionParser.getNotation()== ExpressionParser.Notation.Auto);
        {
            System.out.println("- Enter an arithmetic expression :");
            System.out.println("    Infix arithmetic expression (e.g., 3 + 4 * 2)");
            System.out.println("    Prefix arithmetic expression (e.g., +3 (* 4 2)");
            System.out.println("    Postfix arithmetic expression (e.g., (4 2 *) 3 +");
        }
        System.out.println();
        System.out.println("- Supported types: integers, decimals, fractions (a/b), complex [a+bi]");
        System.out.println("- U can write 'res' to use your last result");
        System.out.println("- U can write 'pi' to use π");
        System.out.println("- Supported functions: sin(x), cos(x), tan(x)");

        System.out.println("- It's currently using "+ExpressionParser.getMode()+" :");
        System.out.println("    Type 'mode' to toggle between radians and degrees");
        System.out.println("- Type 'quit' to close the program");
    }

    static boolean first_use=true;

    static NumericValue expr = null;

    public static void runCLI() {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        ExpressionParser.setMode(ExpressionParser.Mode.DEG);
        //parser.ExpressionParser.setNotation(Notation.INFIX);
        print_init();

        while (true) {
            if(!first_use) {
                try {
                    Thread.sleep(500); // 1000 millisecondes = 1 seconde
                    first_use=false;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }



            System.err.print(">>> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                print_menu();
                continue;
            } else if (input.equalsIgnoreCase("mode")) {
                if(ExpressionParser.getMode()== ExpressionParser.Mode.DEG) {
                    ExpressionParser.setMode(ExpressionParser.Mode.RAD);
                    System.out.println("Mode set to radians");
                }
                else
                {
                    ExpressionParser.setMode(ExpressionParser.Mode.DEG);
                    System.out.println("Mode set to degrees");
                }
                continue;
            }
            else if (input.equalsIgnoreCase("Infix")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.Infix);
                System.out.println("Notation set to " + ExpressionParser.getNotation());
                continue;
            }
            else if (input.equalsIgnoreCase("Prefix")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.Prefix);
                System.out.println("Notation set to " + ExpressionParser.getNotation());
                continue;
            }
            else if (input.equalsIgnoreCase("Postfix")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.Postfix);
                System.out.println("Notation set to " + ExpressionParser.getNotation());
                continue;
            }
            else if (input.equalsIgnoreCase("Auto")) {
                ExpressionParser.setNotation(ExpressionParser.Notation.Auto);
                System.out.println("Notation set to " + ExpressionParser.getNotation());
                continue;
            }


            try {
                ExpressionParser e=new ExpressionParser();
                expr = e.parse(input);
                if(expr!=null)
                {
                    System.err.println("\u001B[97m= " + expr + "\u001B[0m");
                    //System.err.println("= "+expr);
                }


                //calculator.printExpressionDetails(expr);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
