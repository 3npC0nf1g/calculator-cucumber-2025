package calculator.values;

import java.util.Scanner;

public class MatrixCalculator {

    public void launchMatrixCalculator() {
        Matrix A, B = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Calculatrice de matrices ===");
        System.out.println("Format attendu : [[1,2],[3,4]]");

        while (true) {
            System.out.print("\nOpération (add, subtract, multiply, transpose, inverse, exit): ");
            String operation = scanner.nextLine().trim();

            if (operation.equalsIgnoreCase("exit")) break;

            try {
                System.out.print("Entrez la première matrice : ");
                A = Matrix.parse(scanner.nextLine());

                Matrix result;

                if (operation.equalsIgnoreCase("transpose")) {
                    result = A.transpose();
                } else if (operation.equalsIgnoreCase("inverse")) {
                    result = A.inverse();
                } else {
                    System.out.print("Entrez la deuxième matrice : ");
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
                            System.out.println("Opération non reconnue.");
                            continue;
                    }
                }

                System.out.println();
                A.print("Matrice A");
                if (!operation.equalsIgnoreCase("transpose") && !operation.equalsIgnoreCase("inverse")) {
                    assert B != null;
                    B.print("Matrice B");
                }
                result.print("Résultat");


            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        scanner.close();
    }
}

