package calculator;

import java.util.List;

/**
 * This class represents the arithmetic sum operation "+".
 * The class extends the abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Divides
 */
public final class Plus extends Operation {

    /**
     * Class constructor specifying a number of Expressions to add.
     *
     * @param elist The list of Expressions to add
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter
     * @see #Plus(List<Expression>, Notation)
     */
    public Plus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to add,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to add
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction If an empty list of expressions is passed as parameter
     * @see #Plus(List<Expression>)
     * @see Operation#Operation(List<Expression>, Notation)
     */
    public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "+";
        neutral = 0;
    }

    /**
     * Performs the addition operation on two NumericValue objects.
     * This method leverages the add method defined in the NumericValue interface.
     *
     * @param l The first numeric value.
     * @param r The second numeric value.
     * @return The result of the addition as a NumericValue.
     */
    @Override
    public calculator.values.NumericValue op(calculator.values.NumericValue l, calculator.values.NumericValue r) {
        return l.add(r);
    }

    /**
     * The actual computation of the (binary) arithmetic addition of two integers.
     * This method is maintained for backward compatibility.
     *
     * @param l The first integer.
     * @param r The second integer that should be added to the first.
     * @return The integer that is the result of the addition.
     */
    public int op(int l, int r) {
        return (l + r);
    }
}
