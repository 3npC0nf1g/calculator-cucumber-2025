package calculator;

import calculator.values.NumericValue;
import calculator.values.RealValue;
import java.util.List;

public final class Inverse extends UnaryOperation {

    public Inverse(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "inv";
    }

    @Override
    public NumericValue op(NumericValue val) {
        return new RealValue(1,0).divide(val);
    }
}
