package calculator;

import java.util.List;

public abstract class UnaryOperation extends Operation {

    public UnaryOperation(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        if (elist.size() != 1) {
            throw new IllegalArgumentException("Unary operations require exactly one argument.");
        }
    }

    /**
     * Applies the specific unary operation on a single operand.
     *
     * @param val The numeric value on which to apply the operation.
     * @return The result as a NumericValue.
     */
    public abstract calculator.values.NumericValue op(calculator.values.NumericValue val);

    @Override
    public calculator.values.NumericValue op(calculator.values.NumericValue l, calculator.values.NumericValue r) {
        // Not used in unary, but must override; throw to prevent misuse
        throw new UnsupportedOperationException("Unary operation does not support binary operands.");
    }

    public calculator.values.NumericValue computeResult(List<calculator.values.NumericValue> values) {
        return op(values.getFirst());
    }
}
