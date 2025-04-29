package calculator;

import java.util.List;

/**
 * This class represents the arithmetic operation "-" for subtraction.
 * The class extends the abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 */
public final class Minus extends Operation {

    /**
     * Class constructor specifying a number of Expressions to subtract.
     *
     * @param elist The list of Expressions to subtract.
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter.
     * @see #Minus(List<Expression>, Notation)
     */
    public Minus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to subtract,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to subtract.
     * @param n The Notation to be used to represent the operation.
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter.
     * @see #Minus(List<Expression>)
     * @see Operation#Operation(List<Expression>, Notation)
     */
    public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "-";
        neutral = 0;
    }

    /**
     * Performs the subtraction operation on two NumericValue objects.
     * This method leverages the subtract method defined in the NumericValue interface.
     *
     * @param l The first numeric value.
     * @param r The second numeric value.
     * @return The result of the subtraction as a NumericValue.
     */
    @Override
    public calculator.values.NumericValue op(calculator.values.NumericValue l, calculator.values.NumericValue r) {
        return l.subtract(r);
    }

    /**
     * The actual computation of the (binary) arithmetic subtraction of two integers.
     * This method is maintained for backward compatibility.
     *
     * @param l The first integer.
     * @param r The second integer that should be subtracted from the first.
     * @return The integer that is the result of the subtraction.
     */
    public int op(int l, int r) {
        return (l - r);
    }
}
