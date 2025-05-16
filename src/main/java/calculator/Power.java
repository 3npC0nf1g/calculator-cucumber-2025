package calculator;

import java.util.List;

/**
 * The {@code Power} class represents the exponentiation operation (l^r),
 * where the left operand is raised to the power of the right operand.
 * <p>
 * This class extends {@link Operation} and supports both integer and
 * {@link calculator.values.NumericValue} exponentiation.
 * </p>
 *
 * @see Operation
 */
public final class Power extends Operation {

    /**
     * Constructs a {@code Power} operation using infix notation by default.
     *
     * @param elist the list of expressions containing exactly two operands
     * @throws IllegalConstruction if the expression list is invalid or null
     */
    public Power(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }


    /**
     * Constructs a {@code Power} operation with a specified notation.
     *
     * @param elist the list of expressions containing exactly two operands
     * @param n the notation used for the operation (e.g., INFIX, PREFIX, POSTFIX)
     * @throws IllegalConstruction if the expression list is invalid or null
     */
    public Power(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "^"; // ou "^"
        neutral = 1;   // neutre pour multiplication répétée
    }

    /**
     * Performs exponentiation with {@link calculator.values.NumericValue} operands.
     *
     * @param l the base value
     * @param r the exponent value
     * @return the result of raising l to the power r
     */
    @Override
    public calculator.values.NumericValue op(calculator.values.NumericValue l, calculator.values.NumericValue r) {
        return l.pow(r);
    }

    /**
     * Performs exponentiation with integer operands.
     *
     * @param l the base integer
     * @param r the exponent integer
     * @return the integer result of l raised to the power r
     */
    public int op(int l, int r) {
        return (int) Math.pow(l, r);
    }
}
