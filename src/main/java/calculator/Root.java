package calculator;
import calculator.values.NumericValue;

import java.util.List;


public final class Root extends Operation {
    public Root(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "√";
        neutral = 1;
    }

    @Override
    public NumericValue op(NumericValue degree, NumericValue value) {
        return value.root(degree);
    }

    public int op(int d, int x) {
        return (int) Math.round(Math.pow(x, 1.0 / d));
    }
}
