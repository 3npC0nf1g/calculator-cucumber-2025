package calculator.values;

import java.util.Scanner;

/**
 * A simple command-line matrix calculator allowing the user to perform
 * operations such as addition, subtraction, multiplication, transpose,
 * and inversion.
 *
 * <p>Input format for matrices must be: [[1,2],[3,4]]</p>
 */
public class MatrixCalculator {

    /**
     * Launches the interactive matrix calculator in the console.
     * Supported operations:
     * <ul>
     *     <li><b>add</b> - Adds two matrices</li>
     *     <li><b>subtract</b> - Subtracts the second matrix from the first</li>
     *     <li><b>multiply</b> - Multiplies two matrices</li>
     *     <li><b>transpose</b> - Computes the transpose of a matrix</li>
     *     <li><b>inverse</b> - Computes the inverse of a square matrix</li>
     *     <li><b>exit</b> - Exits the program</li>
     * </ul>
     *
     *
     * The user inputs matrices and operation commands directly via the console.
     */
    public void launchMatrixCalculator() {
        Matrix A, B = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Matrix computation ===");
        System.out.println("Expected format : [[1,2],[3,4]]");

        while (true) {
            System.out.print("\nOperation (add, subtract, multiply, transpose, inverse, exit): ");
            String operation = scanner.nextLine().trim();

            if (operation.equalsIgnoreCase("exit")) break;

            try {
                System.out.print("Enter the first matrix : ");
                A = Matrix.parse(scanner.nextLine());

                Matrix result;

                if (operation.equalsIgnoreCase("transpose")) {
                    result = A.transpose();
                } else if (operation.equalsIgnoreCase("inverse")) {
                    result = A.inverse();
                } else {
                    System.out.print("Enter the second matrix : ");
                    B = Matrix.parse(scanner.nextLine());

                    switch (operation.toLowerCase()) {
                        case "add":
                            result = A.add(B);
                            break;
                        case "subtract":
                            result = A.subtract(B);
                            break;
                        case "multiply":
                            result = A.multiply(B);
                            break;
                        default:
                            System.out.println("Unrecognized operation.");
                            continue;
                    }
                }

                System.out.println();
                A.print("Matrix A");
                if (!operation.equalsIgnoreCase("transpose") && !operation.equalsIgnoreCase("inverse")) {
                    assert B != null;
                    B.print("Matrix B");
                }
                result.print("Result");


            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

        scanner.close();
    }
}

