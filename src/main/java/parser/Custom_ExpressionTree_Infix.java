package parser;

import calculator.values.NumericValue;
import calculator.values.RealValue;

import java.math.BigDecimal;
import java.util.*;

public class Custom_ExpressionTree_Infix {
    private static class Node {
        public String value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(String value) {
            this.value = value;
        }

        public void setLeft(Node child) {
            this.left = child;
            if (child != null) {
                child.parent = this;
            }
        }

        public void setRight(Node child) {
            this.right = child;
            if (child != null) {
                child.parent = this;
            }
        }
    }

    // Fonction pour construire l'arbre à partir d'une expression infixée
    public static Node buildTree(String expression) {
        Stack<Node> nodes = new Stack<>();
        Stack<Character> ops = new Stack<>();
        StringBuilder number = new StringBuilder();

        expression = expression.replaceAll("\\s+", ""); // Remove spaces

        int parenthesesBalance = 0; // pour vérifier les parenthèses

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    nodes.push(new Node(number.toString()));
                    number.setLength(0);
                }

                if (c == '(') {
                    ops.push(c);
                    parenthesesBalance++;
                } else if (c == ')') {
                    parenthesesBalance--;
                    if (parenthesesBalance < 0) {
                        throw new IllegalArgumentException("Parenthèse fermante sans ouvrante !");
                    }
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        buildSubTree(nodes, ops.pop());
                    }
                    if (ops.isEmpty()) {
                        throw new IllegalArgumentException("Parenthèse ouvrante non trouvée pour fermer.");
                    }
                    ops.pop(); // retirer '('
                } else if (isOperator(c)) {
                    while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                        buildSubTree(nodes, ops.pop());
                    }
                    ops.push(c);
                } else {
                    throw new IllegalArgumentException("Caractère invalide détecté : " + c);
                }
            }
        }

        if (number.length() > 0) {
            nodes.push(new Node(number.toString()));
        }

        while (!ops.isEmpty()) {
            char op = ops.pop();
            if (op == '(' || op == ')') {
                throw new IllegalArgumentException("Parenthèses non fermées !");
            }
            buildSubTree(nodes, op);
        }

        if (nodes.size() != 1) {
            throw new IllegalStateException("Expression invalide : trop ou pas assez de noeuds.");
        }

        if (parenthesesBalance != 0) {
            throw new IllegalArgumentException("Parenthèses non équilibrées !");
        }

        return nodes.pop();
    }

    private static void buildSubTree(Stack<Node> nodes, char op) {
        if (nodes.size() < 2) {
            throw new IllegalStateException("Pas assez de noeuds pour construire une opération '" + op + "'");
        }
        Node right = nodes.pop();
        Node left = nodes.pop();
        Node parent = new Node(Character.toString(op));
        parent.setLeft(left);
        parent.setRight(right);
        nodes.push(parent);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    // Évalue l'arbre
    public static NumericValue evaluate(Node root) {
        if (root.left == null && root.right == null) {
            return new RealValue(new BigDecimal(Double.parseDouble(root.value)),10);
        }

        NumericValue leftVal = evaluate(root.left);
        NumericValue rightVal = evaluate(root.right);

        switch (root.value) {
            case "+": return leftVal.add(rightVal);
            case "-": return leftVal.subtract(rightVal);
            case "*": return leftVal.multiply(rightVal);
            case "/": return leftVal.divide(rightVal);
            default: throw new IllegalArgumentException("Invalid operator: " + root.value);
        }
    }

    public static void main(String[] args) {
        String expr = "((4 + 5 + 6) * (7 + (5 / 2 / 7)) * 9)";
        //String expr = "2.5 +1";

        try {
            Node root = buildTree(expr);
            NumericValue result = evaluate(root);
            System.out.println("Résultat: " + result);
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }

}
