package calculator;
import calculator.values.NumericValue;

import java.util.List;

public final class Log extends Operation {
    public Log(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "log";
        neutral = 1;
    }

    @Override
    public NumericValue op(NumericValue base, NumericValue x) {
        return x.log(base);
    }
}
