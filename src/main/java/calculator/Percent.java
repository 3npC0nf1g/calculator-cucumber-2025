package calculator;

import calculator.values.NumericValue;
import java.util.List;

public final class Percent extends UnaryOperation {

    public Percent(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "%";
    }

    @Override
    public NumericValue op(NumericValue val) {
        return val.divide(new calculator.values.RealValue(100, 1));
    }
}
