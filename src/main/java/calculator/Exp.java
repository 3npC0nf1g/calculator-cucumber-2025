package calculator;

import calculator.values.NumericValue;
import java.util.List;
/**
 * The {@code Exp} class represents the exponential operation (e^x)
 * applied to a single operand. It extends the {@link UnaryOperation}
 * class and overrides the {@code op} method to compute the exponential
 * of a {@link NumericValue}.
 *
 * <p>This class supports different notations and is intended to be
 * used in symbolic or numeric expression evaluation frameworks.</p>
 *
 * @see UnaryOperation
 * @see NumericValue#exp()
 */
public final class Exp extends UnaryOperation {


    /**
     * Constructs an {@code Exp} operation with the given list of expressions
     * and the specified notation.
     *
     * @param elist the list of expressions (should contain exactly one operand)
     * @param n the notation in which this operation is represented (e.g., INFIX, PREFIX)
     * @throws IllegalConstruction if the expression list is null or invalid
     */
    public Exp(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "exp";
    }


    /**
     * Applies the exponential function to the given {@code NumericValue}.
     *
     * @param val the operand to which the exponential function is applied
     * @return the result of applying {@code e^val}
     */
    @Override
    public NumericValue op(NumericValue val) {
        return val.exp();
    }
}
