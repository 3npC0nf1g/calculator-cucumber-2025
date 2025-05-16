package calculator;
import calculator.values.NumericValue;

import java.util.List;


/**
 * The {@code Root} class represents the root operation,
 * where a value is taken to the 1/degree power (degree-th root).
 * <p>
 * This class extends {@link Operation} and supports both {@link NumericValue}
 * and integer operands.
 * </p>
 *
 * @see Operation
 */
public final class Root extends Operation {

    /**
     * Constructs a {@code Root} operation with the specified notation.
     *
     * @param elist the list of expressions, expected to have two operands: degree and value
     * @param n the notation used for the operation (e.g., INFIX, PREFIX, POSTFIX)
     * @throws IllegalConstruction if the expression list is invalid or null
     */
    public Root(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "√";
        neutral = 1;
    }

    /**
     * Calculates the degree-th root of a given value.
     *
     * @param degree the root degree (e.g., 2 for square root)
     * @param value the value to take the root of
     * @return the degree-th root of the value
     */
    @Override
    public NumericValue op(NumericValue degree, NumericValue value) {
        return value.root(degree);
    }

    /**
     * Calculates the degree-th root of an integer value.
     *
     * @param d the root degree
     * @param x the value to take the root of
     * @return the integer rounded result of the degree-th root of x
     */
    public int op(int d, int x) {
        return (int) Math.round(Math.pow(x, 1.0 / d));
    }
}
