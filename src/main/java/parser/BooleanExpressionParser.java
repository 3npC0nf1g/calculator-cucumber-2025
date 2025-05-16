package parser;

import calculator.values.BooleanValue;

/**
 * A parser and evaluator for boolean expressions that supports logical operators (AND, OR, NOT),
 * Numeric comparisons (`==`, `!=`, `&gt;`, `&gt;=`, `&lt;`, `&lt;=`), and constants (`true`, `false`, `1`, `0`).
 * This class uses a recursive descent parsing approach with minimal tokenization.
 *
 */
public class BooleanExpressionParser {

    /**
     * Parses a boolean expression string and evaluates it.
     *
     * @param expr the boolean expression to parse (e.g., "3 &gt;= 2 AND NOT false")
     * @return a BooleanValue representing the result of the expression
     * @throws Exception if the expression is invalid or cannot be evaluated
     */
    public static BooleanValue parse(String expr) throws Exception {
        expr = expr.trim();
        boolean result = parseExpr(expr);
        return new BooleanValue(result);
    }

    /**
     * Preprocesses and evaluates the cleaned boolean expression.
     *
     * @param expr the trimmed input expression
     * @return the boolean result of evaluation
     * @throws Exception if the expression is malformed
     */
    private static boolean parseExpr(String expr) throws Exception {
        expr = expr.replaceAll("(?i)AND", "&&")
                .replaceAll("(?i)OR", "||")
                .replaceAll("(?i)NOT", "!")
                .replaceAll("\\s+", "");
        return evaluate(expr);
    }

    /**
     * Recursively evaluates a boolean expression string.
     *
     * @param expr the boolean expression
     * @return the result of evaluation
     * @throws Exception if the expression contains errors or invalid syntax
     */
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

    /**
     * Parses and evaluates a comparison operation between two numeric values.
     *
     * @param expr the expression to evaluate
     * @param op   the comparison operator (e.g., "==", "!=", "<", etc.)
     * @return true if the comparison is valid
     */
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

    /**
     * Finds the index of a top-level logical operator (not nested in parentheses).
     *
     * @param expr the expression to search
     * @param op   the logical operator (e.g., "&&", "||")
     * @return index of the operator if found at top level, -1 otherwise
     */
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

    /**
     * Removes unnecessary outer parentheses from the expression.
     *
     * @param expr the input expression
     * @return the expression without redundant surrounding parentheses
     */
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
