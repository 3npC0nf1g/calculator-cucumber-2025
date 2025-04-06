package calculator.values;

import java.math.BigDecimal;
import java.math.MathContext;

public class RealValue implements NumericValue {
    private final BigDecimal value;

    public RealValue(double value, int precision) {
        // Construct a BigDecimal with the specified precision.
        this.value = new BigDecimal(value, new MathContext(precision));
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getPrecision() {
        return value.precision();
    }

    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof RealValue) {
            RealValue r = (RealValue) other;
            return new RealValue(this.value.add(r.value).doubleValue(), value.precision());
        } else if (other instanceof RationalValue) {
            // Convert the rational value to BigDecimal
            RationalValue r = (RationalValue) other;
            BigDecimal rVal = new BigDecimal(r.getNumerator()).divide(new BigDecimal(r.getDenominator()), MathContext.DECIMAL128);
            return new RealValue(this.value.add(rVal).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue) {
            // Promote IntegerValue to RealValue
            IntegerValue i = (IntegerValue) other;
            BigDecimal iVal = BigDecimal.valueOf(i.getValue());
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
        if (other instanceof RealValue) {
            RealValue r = (RealValue) other;
            return new RealValue(this.value.subtract(r.value).doubleValue(), value.precision());
        } else if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            BigDecimal rVal = new BigDecimal(r.getNumerator()).divide(new BigDecimal(r.getDenominator()), MathContext.DECIMAL128);
            return new RealValue(this.value.subtract(rVal).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue) {
            IntegerValue i = (IntegerValue) other;
            BigDecimal iVal = BigDecimal.valueOf(i.getValue());
            return new RealValue(this.value.subtract(iVal).doubleValue(), value.precision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.subtract(other);
        }
        throw new UnsupportedOperationException("Cannot subtract different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof RealValue) {
            RealValue r = (RealValue) other;
            return new RealValue(this.value.multiply(r.value).doubleValue(), value.precision());
        } else if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            BigDecimal rVal = new BigDecimal(r.getNumerator()).divide(new BigDecimal(r.getDenominator()), MathContext.DECIMAL128);
            return new RealValue(this.value.multiply(rVal).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue) {
            IntegerValue i = (IntegerValue) other;
            BigDecimal iVal = BigDecimal.valueOf(i.getValue());
            return new RealValue(this.value.multiply(iVal).doubleValue(), value.precision());
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.multiply(other);
        }
        throw new UnsupportedOperationException("Cannot multiply different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue divide(NumericValue other) {
        if (other instanceof RealValue) {
            RealValue r = (RealValue) other;
            if (r.value.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("Division by zero");
            return new RealValue(this.value.divide(r.value, MathContext.DECIMAL128).doubleValue(), value.precision());
        } else if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            BigDecimal rVal = new BigDecimal(r.getNumerator()).divide(new BigDecimal(r.getDenominator()), MathContext.DECIMAL128);
            if (rVal.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("Division by zero");
            return new RealValue(this.value.divide(rVal, MathContext.DECIMAL128).doubleValue(), value.precision());
        } else if (other instanceof IntegerValue) {
            IntegerValue i = (IntegerValue) other;
            if (i.getValue() == 0) throw new ArithmeticException("Division by zero");
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
    public boolean equals(Object o) {
        return o instanceof RealValue && this.value.equals(((RealValue) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
