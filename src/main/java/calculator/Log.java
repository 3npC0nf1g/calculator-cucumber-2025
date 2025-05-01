package calculator;
import calculator.values.NumericValue;
import calculator.values.IntegerValue;

import java.util.List;

public final class Log extends Operation {
    public Log(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "log";
        neutral = 1;
    }

    @Override
    public NumericValue op(NumericValue base, NumericValue x) {
        // Check if the argument is zero
        if (x.getValueInt() == 0) {
            throw new ArithmeticException("Cannot calculate logarithm of zero");
        }
        // Check if the argument is negative
        if (x.getValueInt() < 0) {
            throw new ArithmeticException("Cannot calculate logarithm of a negative number");
        }
        // Check if the base is negative or 1
        if (base.getValueInt() <= 0 || base.getValueInt() == 1) {
            throw new ArithmeticException("Logarithm base must be positive and not equal to 1");
        }
        return x.log(base);
    }
}