package parser;
import calculator.*;
import calculator.values.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static parser.MyInfixParser.Node.TWO_ARG_FUNCS;

/**
 * The MyInfixParser class provides functionality for parsing and evaluating
 * arithmetic expressions written in infix notation.
 *
 * Features:
 * - Supports integers, decimals, fractions (a/b), and complex numbers [a+bi]
 * - Recognizes trigonometric functions: sin(x), cos(x), tan(x), ...
 * - Allows scientific notation via E(x), equivalent to 10^x , exp(x)
 * - Supports fraction vs. decimal display modes
 * - Integrates with a shared Calculator instance for evaluation
 *
 * Author: Hugue
 */

public class MyInfixParser {

    public static Double toDouble(String value) {
        return Double.parseDouble(value);
    }

    /**
     * Represents a node in the expression tree.
     */
    public static class Node {
        public String value;
        public Node left;
        public Node right;
        public Node parent;
        public boolean isFunction = false;
        public boolean isComplex = false;
        public static final Set<String> TWO_ARG_FUNCS = Set.of(
                "root", "power", "log","ncr","npr"
        );


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

    /**
     * Builds an expression tree from an infix string expression.
     * @param expression the infix expression
     * @return root node of the expression tree
     */
    public static Node buildTree(String expression) {
        Stack<Node> nodes = new Stack<>();
        Stack<String> ops = new Stack<>();
        StringBuilder token = new StringBuilder();
        boolean inBracket = false;

        expression = expression.replaceAll("\\s+", "");
        int parenthesesBalance = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            boolean canBeUnaryMinus =
                           !inBracket
                                && c == '-'
                                && (i == 0 || "+-*/(,".indexOf(expression.charAt(i - 1)) >= 0)
                                && (i + 1 < expression.length())
                                && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.');
            if (canBeUnaryMinus) {
                token.append(c);
                continue;
            }
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

            }
            else if (c == ',') {
                if (token.length() > 0) {
                    String tok = token.toString();
                    if (isFunctionName(tok)) ops.push(tok);
                    else nodes.push(createNodeFromToken(tok));
                    token.setLength(0);
                }
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    buildSubTree(nodes, ops.pop());
                }

            } else if (c == '%') {
                if (token.length() > 0) {
                    nodes.push(createNodeFromToken(token.toString()));
                    token.setLength(0);
                }
                buildSubTree(nodes, "%");
            }
            else {
                if (token.length() > 0) {
                    String tok = token.toString();
                    if (isFunctionName(tok)) ops.push(tok);
                    else nodes.push(createNodeFromToken(tok));
                    token.setLength(0);
                }
                if (c == '(') {
                    ops.push("(");
                    parenthesesBalance++;
                } else if (c == ')') {
                    parenthesesBalance--;
                    if (parenthesesBalance < 0)
                        throw new IllegalArgumentException("Parenthèse fermante sans ouvrante !");

                    while (!ops.isEmpty() && !ops.peek().equals("("))
                        buildSubTree(nodes, ops.pop());
                    if (ops.isEmpty())
                        throw new IllegalArgumentException("Parenthèse ouvrante non trouvée pour fermer.");
                    ops.pop();

                    if (!ops.isEmpty() && isFunctionName(ops.peek()))
                        buildSubTree(nodes, ops.pop());

                } else if (isOperator(String.valueOf(c))) {
                    while (!ops.isEmpty()
                            && precedence(ops.peek()) >= precedence(String.valueOf(c)))
                        buildSubTree(nodes, ops.pop());
                    ops.push(String.valueOf(c));

                } else {
                    throw new IllegalArgumentException("Caractère invalide détecté : " + c);
                }
            }
        }
        if (token.length() > 0) {
            String tok = token.toString();
            if (isFunctionName(tok)) ops.push(tok);
            else nodes.push(createNodeFromToken(tok));
        }
        while (!ops.isEmpty()) {
            String op = ops.pop();
            if (op.equals("(") || op.equals(")"))
                throw new IllegalArgumentException("Parenthèses non fermées !");
            buildSubTree(nodes, op);
        }
        if (nodes.size() != 1)
            throw new IllegalStateException("Expression invalide : trop ou pas assez de noeuds.");
        if (parenthesesBalance != 0)
            throw new IllegalArgumentException("Parenthèses non équilibrées !");

        return nodes.pop();
    }

    /**
     * Constructs a subtree with the given operator and operands from the stack.
     * @param nodes stack of current nodes
     * @param op operator or function name
     */
    private static void buildSubTree(Stack<Node> nodes, String op) {
        Node parent = new Node(op);

        if (isFunctionName(op)) {
            parent.isFunction = true;

            if (TWO_ARG_FUNCS.contains(op)) {
                // fonctions à 2 arguments
                if (nodes.size() < 2)
                    throw new IllegalStateException("Not enough argument for" + op);
                Node right = nodes.pop();
                Node left  = nodes.pop();
                parent.setLeft(left);
                parent.setRight(right);

            } else {
                if (nodes.isEmpty())
                    throw new IllegalStateException("Pas d'argument pour la fonction 2" + op);
                Node child = nodes.pop();
                parent.setLeft(child);
            }

        } else {
            if (nodes.size() < 2)
                throw new IllegalStateException("Pas assez d'opérandes pour '" + op + "'");
            Node right = nodes.pop();
            Node left  = nodes.pop();
            parent.setLeft(left);
            parent.setRight(right);
        }

        nodes.push(parent);
    }


    /**
     * Checks whether the string is a supported operator.
     * @param s token to check
     * @return true if it's an operator, false otherwise
     */
    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    /**
     * Returns the precedence of the given operator.
     * @param op operator as a string
     * @return integer representing precedence level
     */
    private static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    /**
     * Determines if a token is a supported function.
     * @param token token string
     * @return true if token is a function name, false otherwise
     */
    private static boolean isFunctionName(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan") || token.equals("E")
                || token.equals("exp")|| token.equals("ln") || token.equals("log") || token.equals("sqrt")
                || token.equals("root") || token.equals("power") || token.equals("inv") || token.equals("%")
                || token.equals("asin") || token.equals("acos") || token.equals("atan")
                || token.equals("sinh") || token.equals("cosh") || token.equals("tanh") || token.equals("cot")
                || token.equals("asec") || token.equals("acsc") || token.equals("csc") || token.equals("sec")
                || token.equals("acosh") || token.equals("asinh") || token.equals("atanh") || token.equals("acot")
                || token.equals("npr") || token.equals("ncr");
    }

    /**
     * Creates a Node from a token, marking it as function or complex if appropriate.
     * @param token input token
     * @return a new Node
     */
    private static Node createNodeFromToken(String token) {
        Node node = new Node(token);
        if (isFunctionName(token)) {
            node.isFunction = true;
        } else if (token.startsWith("[") && token.endsWith("]")) {
            node.isComplex = true;
        }
        return node;
    }

    /**
     * Evaluates the expression tree.
     * @param root root of the tree
     * @return result as a NumericValue
     */
    public static NumericValue evaluate(Node root) throws IllegalConstruction {
        if (root == null) {
            throw new IllegalArgumentException("Nœud racine nul !");
        }

        //System.out.println("Évaluation du nœud : " + root.value);

        if (root.isFunction) {
                Calculator c = ExpressionParser.mycalculator;
                // binary
                if (TWO_ARG_FUNCS.contains(root.value)) {
                    NumericValue left = evaluate(root.left);
                    NumericValue right = evaluate(root.right);
                    switch (root.value) {
                        case "root":
                            Root r = new Root(List.of(new MyNumber(evaluate(root.left)), new MyNumber(evaluate(root.right))), Notation.INFIX);
                            return new RealValue(Double.parseDouble(c.eval(r).toString()), 10);

                        case "power":
                            Power p = new Power(List.of(new MyNumber(evaluate(root.left)), new MyNumber(evaluate(root.right))), Notation.INFIX);
                            return new RealValue(Double.parseDouble(c.eval(p).toString()), 10);

                        case "log":
                            Log log = new Log(List.of(new MyNumber(evaluate(root.left)), new MyNumber(evaluate(root.right))), Notation.INFIX);
                            return new RealValue(Double.parseDouble(c.eval(log).toString()), 10);
                        case "ncr":
                            List<Expression> combParams = new ArrayList<>();
                            combParams.add(new MyNumber(left));  // n = 5
                            combParams.add(new MyNumber(right));  // r = 3
                            Expression nCr = new NCr(combParams, Notation.INFIX);
                            return c.eval(nCr);
                        case "npr":
                            List<Expression> permParams = new ArrayList<>();
                            permParams.add(new MyNumber(left));  // n = 5
                            permParams.add(new MyNumber(right));  // r = 3
                            Expression nPr = new NPr(permParams, Notation.INFIX);
                            return c.eval(nPr);
                    }
                }
                // unary
                switch (root.value) {
                    // Fonctions trigonométriques & E déjà en place...
                    case "sin":
                        return new RealValue(c.sin(toDouble(evaluate(root.left).toString())), 10);
                    case "cos":
                        return new RealValue(c.cos(toDouble(evaluate(root.left).toString())), 10);
                    case "tan":
                        return new RealValue(c.tan(toDouble(evaluate(root.left).toString())), 10);
                    case "E":
                        return new RealValue(Math.pow(10, toDouble(evaluate(root.left).toString())), 10);

                    case "exp":
                        Exp exp = new Exp(List.of(new MyNumber(evaluate(root.left))), Notation.PREFIX);
                        NumericValue expResult = exp.op(new RealValue(Double.parseDouble(evaluate(root.left).toString()), 10));
                        return new RealValue(Double.parseDouble(expResult.toString()), 10);

                    // Fonctions unaires MyNumber
                    case "ln":
                        Ln ln = new Ln(List.of(new MyNumber(evaluate(root.left))), Notation.INFIX);
                        return new RealValue(Double.parseDouble(c.eval(ln).toString()), 10);

                    case "sqrt":
                        return new RealValue(Math.sqrt(Double.parseDouble(evaluate(root.left).toString())), 10);
                    case "inv":
                        Inverse inv = new Inverse(List.of(new MyNumber(evaluate(root.left))), Notation.INFIX);
                        return new RealValue(Double.parseDouble(c.eval(inv).toString()), 10);
                    // Pourcentage
                    case "%":
                        NumericValue pct = evaluate(root.left);
                        // diviser par 100 : équivalent à MyNumber.divide(100)
                        return pct.divide(new RealValue(BigDecimal.valueOf(100), 10));
                    case "asin":
                        return new RealValue(c.asin(toDouble(evaluate(root.left).toString())), 10);
                    case "acos":
                        return new RealValue(c.acos(toDouble(evaluate(root.left).toString())), 10);
                    case "atan":
                        return new RealValue(c.atan(toDouble(evaluate(root.left).toString())), 10);
                    case "sinh":
                        return new RealValue(c.sinh(toDouble(evaluate(root.left).toString())), 10);
                    case "cosh":
                        return new RealValue(c.cosh(toDouble(evaluate(root.left).toString())), 10);
                    case "tanh":
                        return new RealValue(c.tanh(toDouble(evaluate(root.left).toString())), 10);
                    case "cot":
                        return new RealValue(c.cot(toDouble(evaluate(root.left).toString())), 10);
                    case "asec":
                        return new RealValue(c.asec(toDouble(evaluate(root.left).toString())), 10);
                    case "acsc":
                        return new RealValue(c.acsc(toDouble(evaluate(root.left).toString())), 10);
                    case "csc":
                        return new RealValue(c.csc(toDouble(evaluate(root.left).toString())), 10);
                    case "sec":
                        return new RealValue(c.sec(toDouble(evaluate(root.left).toString())), 10);
                    case "acosh":
                        return new RealValue(c.acosh(toDouble(evaluate(root.left).toString())), 10);
                    case "asinh":
                        return new RealValue(c.asinh(toDouble(evaluate(root.left).toString())), 10);
                    case "atanh":
                        return new RealValue(c.atanh(toDouble(evaluate(root.left).toString())), 10);
                    case "acot":
                        return new RealValue(c.acot(toDouble(evaluate(root.left).toString())), 10);
                    default:
                        throw new IllegalArgumentException("Fonction inconnue: " + root.value);
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
                case "+":
                    return leftVal.add(rightVal);
                case "-":
                    return leftVal.subtract(rightVal);
                case "*":
                    return leftVal.multiply(rightVal);
                case "/": {
                    if (ExpressionParser.getDisplay() == ExpressionParser.Display.FRACTION)
                        return new RationalValue(new BigInteger(leftVal.toString()), new BigInteger(rightVal.toString()));
                    else
                        return leftVal.divide(rightVal);

                }
                default:
                    throw new IllegalArgumentException("Invalid operator: " + root.value);
            }

        }




    /** Parses a complex number literal of the form [a+bi] */
    private static NumericValue parseComplex(String value) {
        // on extrait l’intérieur des crochets
        String inner = value.substring(1, value.length() - 1);
        // détection de la présence d'un 'i'
        boolean hasImag = inner.contains("i");
        // on supprime tous les 'i' pour conserver les nombres
        String stripped = inner.replace("i", "");
        String realPart;
        String imagPart;

        if (!hasImag) {
            // pas de 'i' → nombre réel pur
            realPart = stripped;
            imagPart = "0";
        } else {
            // on cherche, à partir du second caractère, le signe qui sépare réel et imaginaire
            int splitIdx = -1;
            for (int i = 1; i < stripped.length(); i++) {
                char c = stripped.charAt(i);
                if (c == '+' || c == '-') {
                    splitIdx = i;
                    break;
                }
            }
            if (splitIdx > 0) {
                // on découpe autour de ce signe
                realPart = stripped.substring(0, splitIdx);
                imagPart = stripped.substring(splitIdx);
                // on retire un '+' initial éventuel pour l’imaginaire
                if (imagPart.startsWith("+")) {
                    imagPart = imagPart.substring(1);
                }
            } else {
                // pas de séparation → imaginaire pur
                realPart = "0";
                imagPart = stripped;
            }
        }

        double r = Double.parseDouble(realPart);
        double i = Double.parseDouble(imagPart);
        return new ComplexValue(r, i);
    }

}
