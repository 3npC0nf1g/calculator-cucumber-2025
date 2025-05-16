package calculator;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;

import java.util.List;

/**
 * The {@code NPr} class represents the permutation operation "nPr",
 * which calculates the number of ways to arrange {@code r} elements out of {@code n} distinct elements.
 *
 * <p>This is defined mathematically as:</p>
 * <pre>
 *     nPr = n! / (n - r)!
 * </pre>
 *
 * <p>This class requires two non-negative integer inputs {@code n} and {@code r}, where {@code r ≤ n}.</p>
 *
 * @see Operation
 */
public final class NPr extends Operation {

    /**
     * Constructs an {@code NPr} operation with the given list of expressions
     * using default notation (null).
     *
     * @param elist the list of exactly two expressions representing {@code n} and {@code r}
     * @throws IllegalConstruction if the list is null or malformed
     */
    public NPr(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Constructs an {@code NPr} operation with the given list of expressions and specified notation.
     *
     * @param elist the list of exactly two expressions representing {@code n} and {@code r}
     * @param n the notation for this operation (e.g., INFIX, PREFIX)
     * @throws IllegalConstruction if the list is null or malformed
     */
    public NPr(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "nPr";
        neutral = 1; // Not used but required
    }

    /**
     * Applies the permutation operation using the formula {@code nPr = n! / (n - r)!}.
     *
     * @param l the left operand representing {@code n}
     * @param r the right operand representing {@code r}
     * @return a {@link NumericValue} representing the result of {@code nPr}
     * @throws ArithmeticException if {@code n} or {@code r} are negative or {@code r > n}
     */
    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        int n = l.getValueInt();
        int k = r.getValueInt();

        if (n < 0 || k < 0 || k > n) {
            throw new ArithmeticException("Invalid input for nPr: n=" + n + ", r=" + k);
        }

        return new IntegerValue(permutation(n, k));
    }

    /**
     * Computes the permutation result directly with integers.
     *
     * @param n the total number of elements
     * @param k the number of elements to arrange
     * @return the result of {@code nPr}
     */
    public int op(int n, int k) {
        return permutation(n, k);
    }

    /**
     * Helper method to compute {@code nPr = n * (n - 1) * ... * (n - k + 1)}.
     *
     * @param n the total number of elements
     * @param k the number of elements to arrange
     * @return the computed permutation
     */
    private int permutation(int n, int k) {
        int result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
        }
        return result;
    }
}
