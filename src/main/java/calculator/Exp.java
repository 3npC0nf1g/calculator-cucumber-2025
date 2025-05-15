package calculator;

import calculator.values.NumericValue;
import java.util.List;

public final class Exp extends UnaryOperation {

    public Exp(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "exp";
    }

    @Override
    public NumericValue op(NumericValue val) {
        return val.exp();
    }
}
