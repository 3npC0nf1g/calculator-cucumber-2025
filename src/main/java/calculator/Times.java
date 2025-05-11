package calculator;

import calculator.values.NumericValue;
import java.util.List;

/**
 * This class represents the arithmetic multiplication operation "*".
 * The class extends the abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Plus
 * @see Divides
 */
public final class Times extends Operation {

    /**
     * Class constructor specifying a number of Expressions to multiply.
     *
     * @param elist The list of Expressions to multiply.
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter.
     * @see #Times(List<Expression>, Notation)
     */
    public Times(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to multiply,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to multiply.
     * @param n The Notation to be used to represent the operation.
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter.
     * @see #Times(List<Expression>)
     * @see Operation#Operation(List<Expression>, Notation)
     */
    public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "*";
        neutral = 1;
    }

    /**
     * Performs the multiplication operation on two NumericValue objects.
     * This method leverages the multiply method defined in the NumericValue interface.
     *
     * @param l The first numeric value.
     * @param r The second numeric value.
     * @return The result of the multiplication as a NumericValue.
     */
    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        return l.multiply(r);
    }

    /**
     * Legacy method: Performs the multiplication operation on two integers.
     * Note: This method is maintained for backward compatibility and is not used in the new generic implementation.
     *
     * @param l The first integer.
     * @param r The second integer.
     * @return The result of the multiplication.
     */
    public int op(int l, int r) {
        return l * r;
    }
}
