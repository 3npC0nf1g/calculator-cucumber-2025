package calculator;

import java.util.List;
import calculator.values.NumericValue;

/**
 * This class represents the arithmetic division operation "/".
 * The class extends the abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation {

    /**
     * Class constructor specifying a number of Expressions to divide.
     *
     * @param elist The list of Expressions to divide.
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter.
     * @see #Divides(List<Expression>, Notation)
     */
    public Divides(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to divide,
     * as well as the notation used to represent the operation.
     *
     * @param elist The list of Expressions to divide.
     * @param n The Notation to be used to represent the operation.
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter.
     * @see #Divides(List<Expression>)
     * @see Operation#Operation(List<Expression>, Notation)
     */
    public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "/";
        neutral = 1;
    }

    /**
     * Performs the division operation on two NumericValue objects.
     * This method leverages the divide method defined in the NumericValue interface.
     *
     * @param l The first numeric value.
     * @param r The second numeric value, which should divide the first.
     * @return The result of the division as a NumericValue.
     */
    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        return l.divide(r);
    }

    /**
     * The actual computation of the (binary) arithmetic division of two integers.
     * This method is maintained for backward compatibility.
     *
     * @param l The first integer.
     * @param r The second integer that should divide the first.
     * @return The integer that is the result of the division.
     */
    public int op(int l, int r) {
        return (l / r);
    }
}
