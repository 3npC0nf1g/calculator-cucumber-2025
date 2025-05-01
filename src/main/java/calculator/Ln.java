package calculator;

import calculator.values.NumericValue;
import java.util.List;

public final class Ln extends UnaryOperation {

    public Ln(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "ln";
    }

    @Override
    public NumericValue op(NumericValue val) {
        return val.ln();
    }
}
