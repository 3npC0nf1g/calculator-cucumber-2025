package calculator.values;

import java.math.BigDecimal;

public class IntegerValue implements NumericValue {
    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof IntegerValue) {
            return new IntegerValue(this.value + ((IntegerValue) other).value);
        } else if (other instanceof RealValue) {
            // Promotion : convertit l'entier en réel
            return new RealValue(this.value + ((RealValue) other).getValue().doubleValue(), ((RealValue) other).getPrecision());
        } else if (other instanceof ComplexValue) {
            // Promotion : convertit l'entier en complexe (avec partie imaginaire nulle)
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).add(other);
        }
        throw new UnsupportedOperationException("Unsupported addition between IntegerValue and " + other.getClass());
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        if (other instanceof IntegerValue) {
            return new IntegerValue(this.value - ((IntegerValue) other).value);
        } else if (other instanceof RealValue) {
            return new RealValue(this.value - ((RealValue) other).getValue().doubleValue(), ((RealValue) other).getPrecision());
        } else if (other instanceof ComplexValue) {
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).subtract(other);
        }
        throw new UnsupportedOperationException("Unsupported subtraction between IntegerValue and " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof IntegerValue) {
            return new IntegerValue(this.value * ((IntegerValue) other).value);
        } else if (other instanceof RealValue) {
            return new RealValue(this.value * ((RealValue) other).getValue().doubleValue(), ((RealValue) other).getPrecision());
        } else if (other instanceof ComplexValue) {
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).multiply(other);
        }
        throw new UnsupportedOperationException("Unsupported multiplication between IntegerValue and " + other.getClass());
    }

    @Override
    public NumericValue divide(NumericValue other) {
        if (other instanceof IntegerValue) {
            int divisor = ((IntegerValue) other).value;
            if (divisor == 0) throw new ArithmeticException("Division by zero");
            return new IntegerValue(this.value / divisor);
        } else if (other instanceof RealValue) {
            double divisor = ((RealValue) other).getValue().doubleValue();
            if (divisor == 0.0) throw new ArithmeticException("Division by zero");
            return new RealValue(this.value / divisor, ((RealValue) other).getPrecision());
        } else if (other instanceof ComplexValue) {
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).divide(other);
        }
        throw new UnsupportedOperationException("Unsupported division between IntegerValue and " + other.getClass());
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof IntegerValue && this.value == ((IntegerValue) other).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
