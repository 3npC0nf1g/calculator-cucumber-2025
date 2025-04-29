import calculator.*;
import java.util.Scanner;

public class CLI {

    public static void main(String[] args) {
        runCLI();
    }

    public static void runCLI() {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Welcome to the CLI Calculator!");
        System.out.println("Type 'help' for instructions, 'exit' to quit, or 'mode' to toggle radians/degrees.");

        while (true) {
            System.out.print(">>> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                System.out.println("Instructions:");
                System.out.println("- Enter an arithmetic expression (e.g., 3 + 4 * 2)");
                System.out.println("- Supported types: integers, decimals, fractions (a/b), complex (ai)");
                System.out.println("- Supported functions: sin(x), cos(x), tan(x)");
                System.out.println("- Type 'mode' to toggle between radians and degrees");
                System.out.println("- Type 'exit' to quit");
                continue;
            } else if (input.equalsIgnoreCase("mode")) {
                boolean current = calculator.isUseRadians();
                calculator.setUseRadians(!current);
                System.out.println("Mode set to " + (calculator.isUseRadians() ? "radians" : "degrees"));
                continue;
            }

            try {
                //Expression expr = ExpressionParser.parse(input);
                //calculator.printExpressionDetails(expr);
                //calculator.simple_print(expr);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
