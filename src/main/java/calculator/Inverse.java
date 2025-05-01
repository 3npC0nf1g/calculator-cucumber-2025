package calculator;

import calculator.values.NumericValue;
import calculator.values.RationalValue;

import java.math.BigInteger;
import java.util.List;

/**
 * Unary inverse operation: 1/x.
 */
public final class Inverse extends UnaryOperation {

    public Inverse(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "inv";
    }

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
