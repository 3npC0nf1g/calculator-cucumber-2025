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
    public int getValueInt() {
        return this.value;
    }
    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof IntegerValue integerValue) {
            return new IntegerValue(this.value + integerValue.value);
        } else if (other instanceof RealValue realValue) {
            // Promotion : convertit l'entier en réel
            return new RealValue(this.value + realValue.getValue().doubleValue(), realValue.getPrecision());
        } else if (other instanceof ComplexValue) {
            // Promotion : convertit l'entier en complexe (avec partie imaginaire nulle)
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).add(other);
        }
        throw new UnsupportedOperationException("Unsupported addition between IntegerValue and " + other.getClass());
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        if (other instanceof IntegerValue integerValue) {
            return new IntegerValue(this.value - integerValue.value);
        } else if (other instanceof RealValue realValue) {
            return new RealValue(this.value - realValue.getValue().doubleValue(), realValue.getPrecision());
        } else if (other instanceof ComplexValue) {
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).subtract(other);
        }
        throw new UnsupportedOperationException("Unsupported subtraction between IntegerValue and " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof IntegerValue integerValue) {
            return new IntegerValue(this.value * integerValue.value);
        } else if (other instanceof RealValue realValue) {
            return new RealValue(this.value * realValue.getValue().doubleValue(), realValue.getPrecision());
        } else if (other instanceof ComplexValue) {
            return new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).multiply(other);
        }
        throw new UnsupportedOperationException("Unsupported multiplication between IntegerValue and " + other.getClass());
    }

    @Override
    public NumericValue divide(NumericValue other) {
        if (other instanceof IntegerValue integerValue) {
            int divisor = integerValue.value;
            if (divisor == 0) throw new ArithmeticException("Division by zero");
            return new IntegerValue(this.value / divisor);
        } else if (other instanceof RealValue realValue) {
            double divisor = realValue.getValue().doubleValue();
            if (divisor == 0.0) throw new ArithmeticException("Division by zero");
            return new RealValue(this.value / divisor, realValue.getPrecision());
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
        // Vérifie si l'objet est le même (optimisation de performance)
        if (this == other) return true;

        // Vérifie si l'autre objet est null ou si ce n'est pas un IntegerValue
        if (other == null || getClass() != other.getClass()) return false;

        IntegerValue that = (IntegerValue) other;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }


}
