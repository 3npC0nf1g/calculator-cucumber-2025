package parser;

import calculator.values.BooleanValue;

public class BooleanExpressionParser {

    public static BooleanValue parse(String expr) throws Exception {
        expr = expr.trim();
        boolean result = parseExpr(expr);
        return new BooleanValue(result);
    }

    private static boolean parseExpr(String expr) throws Exception {
        expr = expr.replaceAll("(?i)AND", "&&")
                .replaceAll("(?i)OR", "||")
                .replaceAll("(?i)NOT", "!")
                .replaceAll("\\s+", "");

        return evaluate(expr);
    }

    private static boolean evaluate(String expr) throws Exception {
        expr = removeOuterParens(expr);

        // Handle OR
        int idx = findTopLevelOp(expr, "||");
        if (idx != -1)
            return evaluate(expr.substring(0, idx)) || evaluate(expr.substring(idx + 2));

        // Handle AND
        idx = findTopLevelOp(expr, "&&");
        if (idx != -1)
            return evaluate(expr.substring(0, idx)) && evaluate(expr.substring(idx + 2));

        // Handle NOT
        if (expr.startsWith("!"))
            return !evaluate(expr.substring(1));

        // Handle parentheses
        if (expr.startsWith("(") && expr.endsWith(")"))
            return evaluate(expr.substring(1, expr.length() - 1));

        // Handle comparisons
        if (expr.contains("==")) return compare(expr, "==");
        if (expr.contains("!=")) return compare(expr, "!=");
        if (expr.contains(">=")) return compare(expr, ">=");
        if (expr.contains("<=")) return compare(expr, "<=");
        if (expr.contains(">"))  return compare(expr, ">");
        if (expr.contains("<"))  return compare(expr, "<");

        // Handle constants
        if (expr.equals("true") || expr.equals("1")) return true;
        if (expr.equals("false") || expr.equals("0")) return false;

        throw new Exception("Invalid boolean expression: " + expr);
    }

    private static boolean compare(String expr, String op) {
        String[] parts = expr.split(java.util.regex.Pattern.quote(op));
        double left = Double.parseDouble(parts[0]);
        double right = Double.parseDouble(parts[1]);

        return switch (op) {
            case "==" -> left == right;
            case "!=" -> left != right;
            case ">"  -> left > right;
            case "<"  -> left < right;
            case ">=" -> left >= right;
            case "<=" -> left <= right;
            default   -> false;
        };
    }

    // Utilitaire : chercher un opérateur logique en dehors des parenthèses
    private static int findTopLevelOp(String expr, String op) {
        int depth = 0;
        for (int i = 0; i <= expr.length() - op.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') depth++;
            else if (c == ')') depth--;
            else if (depth == 0 && expr.startsWith(op, i))
                return i;
        }
        return -1;
    }

    // Enlève les parenthèses extérieures inutiles
    private static String removeOuterParens(String expr) {
        while (expr.startsWith("(") && expr.endsWith(")")) {
            int depth = 0;
            boolean removable = true;
            for (int i = 0; i < expr.length(); i++) {
                if (expr.charAt(i) == '(') depth++;
                else if (expr.charAt(i) == ')') depth--;
                if (depth == 0 && i < expr.length() - 1) {
                    removable = false;
                    break;
                }
            }
            if (removable)
                expr = expr.substring(1, expr.length() - 1);
            else
                break;
        }
        return expr;
    }
}
