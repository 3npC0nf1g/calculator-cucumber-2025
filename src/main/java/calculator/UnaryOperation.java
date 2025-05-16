package calculator;

import java.util.List;

/**
 * Abstract base class for unary operations,
 * which operate on a single operand.
 * <p>
 * Extends {@link Operation} and enforces exactly one argument in the expression list.
 * </p>
 */
public abstract class UnaryOperation extends Operation {

    /**
     * Constructs a unary operation with the given list of expressions and notation.
     *
     * @param elist the list of expressions; must contain exactly one operand
     * @param n the notation used for the operation (e.g., INFIX, PREFIX, POSTFIX)
     * @throws IllegalConstruction if the expression list is invalid or null, or size != 1
     */
    public UnaryOperation(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        if (elist.size() != 1) {
            throw new IllegalArgumentException("Unary operations require exactly one argument.");
        }
    }


    /**
     * Applies the specific unary operation on a single operand.
     *
     * @param val the numeric value on which to apply the operation
     * @return the result as a {@link calculator.values.NumericValue}
     */
    public abstract calculator.values.NumericValue op(calculator.values.NumericValue val);

    /**
     * Unary operations do not support two operands.
     * This method is overridden to throw an exception if mistakenly used.
     *
     * @param l left operand (unused)
     * @param r right operand (unused)
     * @throws UnsupportedOperationException always thrown to indicate misuse
     */
    @Override
    public calculator.values.NumericValue op(calculator.values.NumericValue l, calculator.values.NumericValue r) {
        // Not used in unary, but must override; throw to prevent misuse
        throw new UnsupportedOperationException("Unary operation does not support binary operands.");
    }

    /**
     * Computes the result by applying the unary operation on the first (and only) value in the list.
     *
     * @param values the list containing exactly one numeric value
     * @return the result of applying the unary operation
     */
    public calculator.values.NumericValue computeResult(List<calculator.values.NumericValue> values) {
        return op(values.getFirst());
    }
}
