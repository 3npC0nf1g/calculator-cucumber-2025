package calculator.values;

import java.math.BigDecimal;
import java.math.MathContext;

public class RealValue implements NumericValue {
    private final BigDecimal value;

    public RealValue(double value, int precision) {
        // Construct a BigDecimal with the specified precision.
        this.value = new BigDecimal(Double.toString(value), new MathContext(precision));

    }

    public BigDecimal getValue() {
        return value;
    }

    public int getPrecision() {
        return value.precision();
    }

    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.add(realValue.value).doubleValue(), value.precision());
        } else if (other instanceof RationalValue rationalValue) {
            // Convert the rational value to BigDecimal
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator()).divide(new BigDecimal(rationalValue.getDenominator()), MathContext.DECIMAL128);
            return new RealValue(this.value.add(rVal).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue integerValue) {
            // Promote IntegerValue to RealValue
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.add(iVal).doubleValue(), value.precision());
        } else if (other instanceof ComplexValue) {
            // Promotion: convert self to ComplexValue and delegate the addition.
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.add(other);
        }
        throw new UnsupportedOperationException("Cannot add different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.subtract(realValue.value).doubleValue(), value.precision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), MathContext.DECIMAL128);

            return new RealValue(this.value.subtract(rVal).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue integerValue) {
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.subtract(iVal).doubleValue(), value.precision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.subtract(other);
        }
        throw new UnsupportedOperationException("Cannot subtract different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.multiply(realValue.value).doubleValue(), value.precision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator()).divide(new BigDecimal(rationalValue.getDenominator()), MathContext.DECIMAL128);
            return new RealValue(this.value.multiply(rVal).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue integerValue) {
            BigDecimal iVal = BigDecimal.valueOf(integerValue.getValue());
            return new RealValue(this.value.multiply(iVal).doubleValue(), value.precision());
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
            return new RealValue(this.value.divide(realValue.value, MathContext.DECIMAL128).doubleValue(), value.precision());
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator()).divide(new BigDecimal(rationalValue.getDenominator()), MathContext.DECIMAL128);
            if (rVal.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException(dividedZero);
            return new RealValue(this.value.divide(rVal, MathContext.DECIMAL128).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue integerValue) {
            IntegerValue i = integerValue;
            if (i.getValue() == 0) throw new ArithmeticException(dividedZero);
            BigDecimal iVal = BigDecimal.valueOf(i.getValue());
            return new RealValue(this.value.divide(iVal, MathContext.DECIMAL128).doubleValue(), value.precision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.divide(other);
        }
        throw new UnsupportedOperationException("Cannot divide different numeric types: " + other.getClass());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int getValueInt() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RealValue realValue && this.value.equals(realValue.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
