import calculator.*;
import calculator.values.NumericValue;
import parser.ExpressionParser;

import java.util.Scanner;

public class CLI {

    public static void main(String[] args) {
        runCLI();
    }

    public static void runCLI() {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Welcome to the CLI Calculator!");
        System.out.println("Type 'help' for instructions");
        if (ExpressionParser.getMode().equals(ExpressionParser.Mode.DEG)) {
            System.out.println("- It's currently using degrees :");
        } else {
            System.out.println("- It's currently using radians :");
        }
        System.out.println("    Type 'mode' to toggle between radians and degrees");
        System.out.println("Type 'quit' to close the program");


        ExpressionParser.setMode(ExpressionParser.Mode.DEG);
        //parser.ExpressionParser.setNotation(Notation.INFIX);

        while (true) {
            System.out.print(">>> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                System.out.println("Instructions:");
                System.out.println("- Enter an arithmetic expression (e.g., 3 + 4 * 2)");
                System.out.println("- Supported types: integers, decimals, fractions (a/b), complex [a+bi])");
                System.out.println("- U can write 'res' to use your last result");
                System.out.println("- U can write 'pi' to use π");
                System.out.println("- Supported functions: sin(x), cos(x), tan(x)");
                if (ExpressionParser.getMode().equals(ExpressionParser.Mode.DEG)) {
                    System.out.println("- It's currently using degrees :");
                } else {
                    System.out.println("- It's currently using radians :");
                }
                System.out.println("    Type 'mode' to toggle between radians and degrees");

                System.out.println("- Type 'exit' to quit");
                continue;
            } else if (input.equalsIgnoreCase("mode")) {
                boolean current = calculator.isUseRadians();
                calculator.setUseRadians(!current);
                System.out.println("Mode set to " + (calculator.isUseRadians() ? "radians" : "degrees"));
                continue;
            }


            try {
                ExpressionParser e=new ExpressionParser();
                NumericValue expr = e.parse(input);
                System.out.println("= "+expr);

                //calculator.printExpressionDetails(expr);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
