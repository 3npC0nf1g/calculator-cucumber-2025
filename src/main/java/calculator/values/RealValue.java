package calculator.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a real number value with fixed decimal precision (scale).
 */
public class RealValue implements NumericValue {
    private final BigDecimal value;
    private final int scale;
    private  boolean isNaN=false;

    public static final RealValue NaN = new RealValue();

    // Constructs a RealValue from NaN
    private RealValue() {
        this.value = BigDecimal.ZERO;
        this.scale = 0;
        this.isNaN = true;
    }

    /**
     * Constructs a RealValue from a double with specified decimal precision.
     *
     * @param value the double value
     * @param scale the number of decimal places to retain
     */
    public RealValue(double value, int scale) {
        this.scale = scale;
        this.value = BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * Constructs a RealValue from a BigDecimal with specified decimal precision.
     *
     * @param value the BigDecimal value
     * @param scale the number of decimal places to retain
     */
    public RealValue(BigDecimal value, int scale) {
        this.scale = scale;
        this.value = value.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * Gets the BigDecimal value.
     *
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Gets the number of decimal places used for rounding.
     *
     * @return the scale
     */
    public int getPrecision() {
        return scale;
    }

    public boolean isNaN() {
        return isNaN;
    }

    @Override
    public NumericValue add(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof RealValue realValue && realValue.isNaN()) return NaN;

        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.add(realValue.value), scale);
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), scale, RoundingMode.HALF_UP);
            return new RealValue(this.value.add(rVal), scale);
        } else if (other instanceof IntegerValue integerValue) {
            return new RealValue(this.value.add(BigDecimal.valueOf(integerValue.getValue())), scale);
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.add(other);
        }
        throw new UnsupportedOperationException("Cannot add different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof RealValue realValue && realValue.isNaN()) return NaN;

        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.subtract(realValue.value), scale);
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), scale, RoundingMode.HALF_UP);
            return new RealValue(this.value.subtract(rVal), scale);
        } else if (other instanceof IntegerValue integerValue) {
            return new RealValue(this.value.subtract(BigDecimal.valueOf(integerValue.getValue())), scale);
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.subtract(other);
        }
        throw new UnsupportedOperationException("Cannot subtract different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof RealValue realValue && realValue.isNaN()) return NaN;

        if (other instanceof RealValue realValue) {
            return new RealValue(this.value.multiply(realValue.value).setScale(scale, RoundingMode.HALF_UP), scale);
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), scale, RoundingMode.HALF_UP);
            return new RealValue(this.value.multiply(rVal).setScale(scale, RoundingMode.HALF_UP), scale);
        } else if (other instanceof IntegerValue integerValue) {
            return new RealValue(this.value.multiply(BigDecimal.valueOf(integerValue.getValue())).setScale(scale, RoundingMode.HALF_UP), scale);
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.multiply(other);
        }
        throw new UnsupportedOperationException("Cannot multiply different numeric types: " + other.getClass());
    }

    @Override
    public NumericValue divide(NumericValue other) {
        if (this.isNaN) return NaN;

        if (other instanceof RealValue realValue) {
            if (realValue.isNaN() || realValue.value.compareTo(BigDecimal.ZERO) == 0) return NaN;
            return new RealValue(this.value.divide(realValue.value, scale, RoundingMode.HALF_UP), scale);
        } else if (other instanceof RationalValue rationalValue) {
            BigDecimal rVal = new BigDecimal(rationalValue.getNumerator())
                    .divide(new BigDecimal(rationalValue.getDenominator()), scale, RoundingMode.HALF_UP);
            if (rVal.compareTo(BigDecimal.ZERO) == 0) return NaN;
            return new RealValue(this.value.divide(rVal, scale, RoundingMode.HALF_UP), scale);
        } else if (other instanceof IntegerValue integerValue) {
            if (integerValue.getValue() == 0) return NaN;
            return new RealValue(this.value.divide(BigDecimal.valueOf(integerValue.getValue()), scale, RoundingMode.HALF_UP), scale);
        } else if (other instanceof ComplexValue) {
            ComplexValue c = new ComplexValue(this.value, BigDecimal.ZERO);
            return c.divide(other);
        }
        throw new UnsupportedOperationException("Cannot divide different numeric types: " + other.getClass());
    }

    /**
     * Returns a string representation of the value with no scientific notation.
     *
     * @return plain string version of the number
     */
   @Override
   public String toString() {
       return isNaN ? "NaN" : value.stripTrailingZeros().toPlainString();
   }




    /**
     * Gets the integer value of this real number (truncated).
     *
     * @return integer part of the number
     */
    @Override
    public int getValueInt() {
        return isNaN ? 0 : value.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RealValue other)) return false;
        if (this.isNaN && other.isNaN) return true;
        return !this.isNaN && !other.isNaN && this.value.compareTo(other.value) == 0;
    }

    @Override
    public int hashCode() {
        return isNaN ? 0 : value.hashCode();
    }
}
