package parser;

import calculator.Calculator;
import calculator.values.NumericValue;
import calculator.values.RealValue;
import calculator.values.ComplexValue;

import java.math.BigDecimal;
import java.util.*;

public class MyInfixParser {
    public static class Node {
        public String value;
        public Node left;
        public Node right;
        public Node parent;
        public boolean isFunction = false;
        public boolean isComplex = false;

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

    public static Node buildTree(String expression) {
        Stack<Node> nodes = new Stack<>();
        Stack<String> ops = new Stack<>();
        StringBuilder token = new StringBuilder();
        boolean inBracket = false;

        expression = expression.replaceAll("\\s+", "");
        int parenthesesBalance = 0;

        //System.out.println("Expression nettoyée : " + expression);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '[') {
                inBracket = true;
                token.append(c);
            } else if (c == ']') {
                token.append(c);
                inBracket = false;
            } else if (inBracket) {
                token.append(c);
            } else if (Character.isDigit(c) || c == '.' || Character.isLetter(c)) {
                token.append(c);
            } else {
                if (token.length() > 0) {
                    String tok = token.toString();
                    //System.out.println("Token capturé : " + tok);
                    if (isFunctionName(tok)) {
                        ops.push(tok);
                    } else {
                        nodes.push(createNodeFromToken(tok));
                    }
                    token.setLength(0);
                }

                if (c == '(') {
                    ops.push("(");
                    parenthesesBalance++;
                    //System.out.println("Parenthèse ouvrante '('");
                } else if (c == ')') {
                    parenthesesBalance--;
                    //System.out.println("Parenthèse fermante ')'");
                    if (parenthesesBalance < 0) {
                        throw new IllegalArgumentException("Parenthèse fermante sans ouvrante !");
                    }
                    while (!ops.isEmpty() && !ops.peek().equals("(")) {
                        String op = ops.pop();
                        //System.out.println("Construction de sous-arbre avec opérateur : " + op);
                        buildSubTree(nodes, op);
                    }
                    if (ops.isEmpty()) {
                        throw new IllegalArgumentException("Parenthèse ouvrante non trouvée pour fermer.");
                    }
                    ops.pop(); // consume '('

                    // Si une fonction est sur le dessus, construire immédiatement
                    if (!ops.isEmpty() && isFunctionName(ops.peek())) {
                        String func = ops.pop();
                        //System.out.println("Construction d'une fonction : " + func);
                        buildSubTree(nodes, func);
                    }
                } else if (isOperator(String.valueOf(c))) {
                    //System.out.println("Opérateur rencontré : " + c);
                    while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(String.valueOf(c))) {
                        String op = ops.pop();
                        //System.out.println("Construction de sous-arbre avec opérateur : " + op);
                        buildSubTree(nodes, op);
                    }
                    ops.push(String.valueOf(c));
                } else {
                    throw new IllegalArgumentException("Caractère invalide détecté : " + c);
                }
            }
        }

        if (token.length() > 0) {
            String tok = token.toString();
            //System.out.println("Token capturé en fin : " + tok);
            if (isFunctionName(tok)) {
                ops.push(tok);
            } else {
                nodes.push(createNodeFromToken(tok));
            }
        }

        while (!ops.isEmpty()) {
            String op = ops.pop();
            if (op.equals("(") || op.equals(")")) {
                throw new IllegalArgumentException("Parenthèses non fermées !");
            }
            //System.out.println("Construction de sous-arbre final avec opérateur : " + op);
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

    private static void buildSubTree(Stack<Node> nodes, String op) {
        Node parent = new Node(op);

        if (isFunctionName(op)) {
            parent.isFunction = true; // Marquer explicitement comme fonction
            if (nodes.isEmpty()) {
                throw new IllegalStateException("Pas d'argument pour la fonction " + op);
            }
            Node child = nodes.pop();
            parent.setLeft(child);  // Connecter l'argument à la fonction
        } else {
            if (nodes.size() < 2) {
                throw new IllegalStateException("Pas assez d'opérandes pour '" + op + "'");
            }
            Node right = nodes.pop();
            Node left = nodes.pop();
            parent.setLeft(left);
            parent.setRight(right);
        }
        nodes.push(parent);
    }



    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    private static boolean isFunctionName(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan");
    }

    private static Node createNodeFromToken(String token) {
        Node node = new Node(token);
        if (isFunctionName(token)) {
            node.isFunction = true;
        } else if (token.startsWith("[") && token.endsWith("]")) {
            node.isComplex = true;
        }
        return node;
    }

    public static NumericValue evaluate(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Nœud racine nul !");
        }

        //System.out.println("Évaluation du nœud : " + root.value);

        if (root.isFunction) {
            if (root.left == null) {
                throw new IllegalArgumentException("Argument manquant pour la fonction " + root.value);
            }
            NumericValue argument = evaluate(root.left);  // left contient l'argument de sin, cos, etc.
            Calculator c = ExpressionParser.mycalculator;
            switch (root.value) {
                case "sin": return new RealValue(c.sin(Double.parseDouble(argument.toString())), 10);
                case "cos": return new RealValue(c.cos(Double.parseDouble(argument.toString())), 10);
                case "tan": return new RealValue(c.tan(Double.parseDouble(argument.toString())), 10);
                default: throw new IllegalArgumentException("Fonction inconnue: " + root.value);
            }
        }

        if (root.isComplex) {
            return parseComplex(root.value);
        }

        if (root.left == null && root.right == null) {
            return new RealValue(new BigDecimal(root.value), 10);
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



    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }


    private static NumericValue parseComplex(String value) {
        String inside = value.substring(1, value.length() - 1);
        inside = inside.replace("i", "");
        String realPart = "0", imagPart = "0";
        if (inside.contains("+")) {
            String[] parts = inside.split("\\+");
            realPart = parts[0];
            imagPart = parts[1];
        } else if (inside.contains("-")) {
            int idx = inside.lastIndexOf('-');
            if (idx == 0) {
                inside = inside.substring(1);
                idx = inside.indexOf('-');
                if (idx == -1) {
                    realPart = "-" + inside;
                    imagPart = "0";
                } else {
                    realPart = "-" + inside.substring(0, idx);
                    imagPart = inside.substring(idx);
                }
            } else {
                realPart = inside.substring(0, idx);
                imagPart = inside.substring(idx);
            }
        } else {
            imagPart = inside;
        }
        return new ComplexValue(Double.parseDouble(realPart), Double.parseDouble(imagPart));
    }


    // (la partie evaluate() et parseComplex() reste bonne, pas besoin de modifier)

    public static void main(String[] args) {
        ExpressionParser.mycalculator.setUseRadians(false);
        String expr = "((4 + 5 + 6) * (7 + (5 / 2 / 7)) * 9)";

        try {
            Node root = buildTree(expr);
            NumericValue result = evaluate(root);
            System.out.println("Résultat: " + result);
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }
}
