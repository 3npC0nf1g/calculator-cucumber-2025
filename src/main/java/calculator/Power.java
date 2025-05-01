package calculator;

import java.util.List;

public final class Power extends Operation {

    public Power(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public Power(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "^^"; // ou "^"
        neutral = 1;   // neutre pour multiplication répétée
    }

    @Override
    public calculator.values.NumericValue op(calculator.values.NumericValue l, calculator.values.NumericValue r) {
        return l.pow(r);
    }

    public int op(int l, int r) {
        return (int) Math.pow(l, r);
    }
}
