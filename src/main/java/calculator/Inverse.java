package calculator;

import calculator.values.NumericValue;
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
        // Simply call the NumericValue API, which for IntegerValue returns a RationalValue(1,x),
        // for RealValue returns BigDecimal.ONE.divide(...), etc.
        return val.inverse();
    }
}
