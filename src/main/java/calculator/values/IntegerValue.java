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
        // First check for zero divisor
        if (isDivisorZero(other)) {
            throw new ArithmeticException("Division by zero is not allowed");
        }

        return switch (other) {
            case IntegerValue iv -> {
                if (this.value % iv.value == 0) {
                    yield new IntegerValue(this.value / iv.value);
                }
                yield new RealValue((double) this.value / iv.value, 2);
            }
            case RealValue rv ->
                    new RealValue(this.value / rv.getValue().doubleValue(), rv.getPrecision());
            case ComplexValue a ->
                    new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).divide(other);
            default -> throw new UnsupportedOperationException(
                    "Unsupported division between IntegerValue and " + other.getClass()
            );
        };
    }

    // Helper method to check for zero divisor
    private boolean isDivisorZero(NumericValue divisor) {
        return switch (divisor) {
            case IntegerValue iv -> iv.value == 0;
            case RealValue rv -> rv.getValue().compareTo(BigDecimal.ZERO) == 0;
            case ComplexValue cv -> cv.isZero();
            default -> false;
        };
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













    @Override
    public NumericValue pow(NumericValue exponent) {
        if (exponent instanceof IntegerValue iv) {
            int exp = iv.getValue();
            int result = (int) Math.pow(this.value, exp);
            return new IntegerValue(result);
        } else {
            double base = this.value;
            double exp = ((RealValue) exponent).getValue().doubleValue();
            double res = Math.pow(base, exp);
            return new RealValue(res, 10);
        }
    }

    @Override
    public NumericValue root(NumericValue degree) {
        if (degree instanceof IntegerValue iv) {
            double res = Math.pow(this.value, 1.0 / iv.getValue());
            return new RealValue(res, 10);
        } else {
            double deg = ((RealValue) degree).getValue().doubleValue();
            double res = Math.pow(this.value, 1.0 / deg);
            return new RealValue(res, 10);
        }
    }

    @Override
    public NumericValue log(NumericValue base) {
        // Case 1: If base is complex, convert this integer to complex and use complex log
        if (base instanceof ComplexValue) {
            ComplexValue thisComplex = new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO);
            return thisComplex.log(base);
        }

        // Case 2: For real/integer base, use regular log formula
        double val = this.value;
        double b = switch (base) {
            case IntegerValue iv -> iv.getValue();
            case RealValue rv -> rv.getValue().doubleValue();
            default -> throw new UnsupportedOperationException("Unsupported base type for logarithm");
        };

        // Check for invalid inputs
        if (val <= 0 || b <= 0 || b == 1) {
            return RealValue.NaN;
        }

        double res = Math.log(val) / Math.log(b);
        return new RealValue(res, 10);
    }


    @Override
    public NumericValue inverse() {
        if (this.value == 0) {
            return RealValue.NaN;
        }
        return new RationalValue(1, this.value);
    }

    @Override
    public NumericValue ln() {
        if (this.value <= 0) {
            return RealValue.NaN;
        }
        double res = Math.log(this.value);
        return new RealValue(res, 10);
    }

    @Override
    public NumericValue exp() {
        double res = Math.exp(this.value);
        return new RealValue(res, 10);
    }
}
