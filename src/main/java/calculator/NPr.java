package calculator;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;

import java.util.List;

/**
 * Class for the permutation operation "nPr"
 */
public final class NPr extends Operation {

    public NPr(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public NPr(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "nPr";
        neutral = 1; // Not used but required
    }

    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        int n = l.getValueInt();
        int k = r.getValueInt();

        if (n < 0 || k < 0 || k > n) {
            throw new ArithmeticException("Invalid input for nPr: n=" + n + ", r=" + k);
        }

        return new IntegerValue(permutation(n, k));
    }

    public int op(int n, int k) {
        return permutation(n, k);
    }

    private int permutation(int n, int k) {
        int result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
        }
        return result;
    }
}
