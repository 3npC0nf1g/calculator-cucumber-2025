package calculator;

import calculator.values.NumericValue;
import calculator.values.RationalValue;

import java.math.BigInteger;
import java.util.List;

/**
 * The {@code Inverse} class represents the unary inverse operation (1/x).
 * It extends the {@link UnaryOperation} class and overrides the {@code op}
 * method to compute the reciprocal of a given {@link NumericValue}.
 *
 * <p>The operation handles both rational values (with special handling to avoid
 * zero division) and general numeric values by delegating to their respective
 * {@code inverse()} methods.</p>
 *
 * @see UnaryOperation
 * @see NumericValue
 * @see RationalValue
 */
public final class Inverse extends UnaryOperation {


    /**
     * Constructs an {@code Inverse} operation with a given list of sub-expressions
     * and the specified notation.
     *
     * @param elist the list of expressions (must contain exactly one operand)
     * @param n the notation in which the operation is to be represented (e.g., INFIX, PREFIX)
     * @throws IllegalConstruction if the expression list is null or invalid
     */
    public Inverse(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "inv";
    }

    /**
     * Applies the inverse (reciprocal) operation to the given {@code NumericValue}.
     *
     * <p>If the operand is a {@link RationalValue}, the method swaps numerator and denominator.
     * If the operand is zero, an {@code ArithmeticException} is thrown to prevent division by zero.</p>
     *
     * @param val the numeric value to invert
     * @return the result of computing {@code 1/val}
     * @throws ArithmeticException if {@code val} is zero
     */
    @Override
    public NumericValue op(NumericValue val) {
        // Traitement spécial pour les nombres rationnels
        if (val instanceof RationalValue rv) {
            // Vérifier si le numérateur est zéro en utilisant compareTo
            if (rv.getNumerator().compareTo(BigInteger.ZERO) == 0) {
                throw new ArithmeticException("Cannot compute inverse of zero (division by zero)");
            }
            // Pour un nombre rationnel a/b, son inverse est b/a
            return new RationalValue(rv.getDenominator(), rv.getNumerator());
        }

        // Pour les autres types, vérifier la division par zéro
        if (val.getValueInt() == 0) {
            throw new ArithmeticException("Cannot compute inverse of zero (division by zero)");
        }

        // Simply call the NumericValue API, which for IntegerValue returns a RationalValue(1,x),
        // for RealValue returns BigDecimal.ONE.divide(...), etc.
        return val.inverse();
    }
}
