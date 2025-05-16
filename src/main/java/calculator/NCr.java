package calculator;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;

import java.util.List;

/**
 * The {@code NCr} class represents the binomial coefficient operation ("n choose r"),
 * which computes the number of combinations of {@code r} elements from a set of {@code n} elements.
 *
 * <p>This is a binary operation that only accepts non-negative integers
 * where {@code 0 ≤ r ≤ n}.</p>
 *
 * <p>Mathematically, this is defined as:</p>
 * <pre>
 *     nCr = n! / (r! * (n - r)!)
 * </pre>
 *
 * @see Operation
 */

public final class NCr extends Operation {

    /**
     * Constructs an {@code NCr} operation with the given list of expressions and default infix notation.
     *
     * @param elist the list of exactly two expressions representing n and r
     * @throws IllegalConstruction if the expression list is null or malformed
     */
    public NCr(List<Expression> elist) throws IllegalConstruction {
        this(elist, Notation.INFIX);
    }

    /**
     * Constructs an {@code NCr} operation with the given list of expressions and specified notation.
     *
     * @param elist the list of exactly two expressions representing n and r
     * @param n the notation used to represent the operation (e.g., INFIX)
     * @throws IllegalConstruction if the expression list is null or malformed
     * @throws IllegalArgumentException if the list does not contain exactly 2 elements
     */
    public NCr(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        if (elist.size() != 2) {
            throw new IllegalArgumentException("NCr requires exactly 2 arguments: n and r.");
        }
        symbol = "nCr";
    }

    /**
     * Applies the binomial coefficient formula {@code nCr = n! / (r!(n - r)!)}.
     *
     * @param l the left operand representing n
     * @param r the right operand representing r
     * @return a {@link NumericValue} representing the result of nCr
     * @throws ArithmeticException if n or r are negative, or if r > n
     */
    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        int n = l.getValueInt();
        int k = r.getValueInt();
        if (n < 0 || k < 0 || k > n) {
            throw new ArithmeticException("Invalid inputs for nCr: n must be >= k and >= 0");
        }
        return new IntegerValue(combination(n, k));
    }

    /**
     * Computes the binomial coefficient using the factorial formula.
     *
     * @param n the total number of items
     * @param k the number of items to choose
     * @return the result of nCr
     */
    private int combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }


    /**
     * Computes the factorial of a non-negative integer.
     *
     * @param n the integer
     * @return the factorial of n
     * @throws ArithmeticException if n is negative
     */
    private int factorial(int n) {
        if (n < 0) throw new ArithmeticException("Factorial of negative number not allowed");
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
