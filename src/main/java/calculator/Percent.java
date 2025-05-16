package calculator;

import calculator.values.NumericValue;
import java.util.List;

/**
 * The {@code Percent} class represents the percentage unary operation,
 * which divides a given numeric value by 100.
 * <p>
 * This operation is commonly used to convert a number to its percentage representation.
 * For example, inputting {@code 25} returns {@code 0.25}.
 * </p>
 *
 * @see UnaryOperation
 */
public final class Percent extends UnaryOperation {

    /**
     * Constructs a {@code Percent} operation with the given list of expressions and notation.
     *
     * @param elist the list of expressions containing a single operand
     * @param n the notation used for the operation (e.g., INFIX, PREFIX, POSTFIX)
     * @throws IllegalConstruction if the expression list is invalid or null
     */
    public Percent(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "%";
    }

    /**
     * Applies the percentage operation by dividing the input value by 100.
     *
     * @param val the input {@link NumericValue}
     * @return the result of {@code val / 100} as a {@link NumericValue}
     */
    @Override
    public NumericValue op(NumericValue val) {
        return val.divide(new calculator.values.RealValue(100, 1));
    }
}
