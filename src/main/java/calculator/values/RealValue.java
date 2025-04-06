package calculator.values;

import java.math.BigDecimal;
import java.math.MathContext;

public class RealValue implements NumericValue {
    private final BigDecimal value;
    private final MathContext mathContext;

    public RealValue(double value, int precision) {
        this.mathContext = new MathContext(precision);
        this.value = new BigDecimal(value, mathContext);
    }

    public RealValue(BigDecimal value, int precision) {
        this.mathContext = new MathContext(precision);
        this.value = value.round(mathContext);
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getPrecision() {
        return mathContext.getPrecision();
    }

    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.add(realValue.value, mathContext), getPrecision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), mathContext);
            return new RealValue(this.value.add(rVal, mathContext), getPrecision());
        } else if (other instanceof IntegerValue integerValue) {
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.add(iVal, mathContext), getPrecision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.add(other);
        }
        throw new UnsupportedOperationException("Cannot add different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.subtract(realValue.value, mathContext), getPrecision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), mathContext);
            return new RealValue(this.value.subtract(rVal, mathContext), getPrecision());
        } else if (other instanceof IntegerValue integerValue) {
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.subtract(iVal, mathContext), getPrecision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.subtract(other);
        }
        throw new UnsupportedOperationException("Cannot subtract different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.multiply(realValue.value, mathContext), getPrecision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), mathContext);
            return new RealValue(this.value.multiply(rVal, mathContext), getPrecision());
        } else if (other instanceof IntegerValue integerValue) {
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.multiply(iVal, mathContext), getPrecision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.multiply(other);
        }
        throw new UnsupportedOperationException("Cannot multiply different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue divide(NumericValue other) {
        String dividedZero = "Division by zero";

        if (other instanceof RealValue realValue) {
            if (realValue.value.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException(dividedZero);
            return new RealValue(this.value.divide(realValue.value, mathContext), getPrecision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), mathContext);
            if (rVal.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException(dividedZero);
            return new RealValue(this.value.divide(rVal, mathContext), getPrecision());
        } else if (other instanceof IntegerValue integerValue) {
            if (integerValue.getValue() == 0) throw new ArithmeticException(dividedZero);
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.divide(iVal, mathContext), getPrecision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.divide(other);
        }
        throw new UnsupportedOperationException("Cannot divide different numeric types: " + other.getClass());
    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toPlainString();
    }

    @Override
    public int getValueInt() {
        return value.intValue();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RealValue realValue && this.value.compareTo(realValue.value) == 0;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
