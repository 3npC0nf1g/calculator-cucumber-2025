package calculator;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;

import java.util.List;

public final class NCr extends Operation {

    public NCr(List<Expression> elist) throws IllegalConstruction {
        this(elist, Notation.INFIX);
    }

    public NCr(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        if (elist.size() != 2) {
            throw new IllegalArgumentException("NCr requires exactly 2 arguments: n and r.");
        }
        symbol = "nCr";
    }

    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        int n = l.getValueInt();
        int k = r.getValueInt();
        if (n < 0 || k < 0 || k > n) {
            throw new ArithmeticException("Invalid inputs for nCr: n must be >= k and >= 0");
        }
        return new IntegerValue(combination(n, k));
    }

    private int combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private int factorial(int n) {
        if (n < 0) throw new ArithmeticException("Factorial of negative number not allowed");
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
