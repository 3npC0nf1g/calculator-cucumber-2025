import calculator.Calculator;
import calculator.Expression;

import java.util.Scanner;

public class CalculatorCLI {
    static double lastResult = 0.0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Calculator CLI!");
        System.out.println("Type 'help' for usage instructions or 'exit' to quit.");
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator(); // déjà existant
        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            if (input.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }
            try {
                if (input.contains("res")) {
                    input = input.replace("res", String.valueOf(lastResult));
                }
                Expression expr = ExpressionParser.parse(input);
                double result = calculator.eval(expr);
                if (!Double.isNaN(result))
                    lastResult = result;
                System.out.println("Result: " + formatDouble(result));
            } catch (Exception e) {
                System.out.println("Syntax error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printHelp() {
        System.out.println("""
        === Calculator CLI Help ===
        You can enter expressions using:
        - + for addition
        - - for subtraction
        - * for multiplication
        - / for division
       
        - res take the last result
        
        Use parentheses for grouping.
        Example: 5+(2*3)
        
        Special commands:
        - help : show this message
        - exit : quit the calculator
        ============================
        """);
    }

    private static String formatDouble(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value); // Ex: 5.0 -> "5"
        } else
            return ""+value;
    }

}
