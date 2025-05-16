package calculator.values;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Represents a real number value with fixed decimal precision (scale).
 * <p>
 * This class encapsulates a {@link BigDecimal} and ensures that the value
 * is rounded to a fixed number of decimal places.
 * It supports basic arithmetic operations, NaN handling,
 * and promotion to complex types when necessary.
 * </p>
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

    /**
     * Returns whether this value represents a NaN (Not a Number).
     *
     * @return true if the value is NaN, false otherwise
     */
    public boolean isNaN() {
        return isNaN;
    }

    /**
     * Adds another numeric value to this RealValue.
     * <p>
     * The behavior depends on the type of {@code other}:
     * <ul>
     *   <li>If {@code other} is a {@link RealValue}, their BigDecimal values are added.</li>
     *   <li>If {@code other} is a {@link RationalValue} or {@link IntegerValue}, it is converted to {@link BigDecimal} before addition.</li>
     *   <li>If {@code other} is a {@link ComplexValue}, this value is promoted to a {@link ComplexValue} with zero imaginary part.</li>
     *   <li>Otherwise, an {@link UnsupportedOperationException} is thrown.</li>
     * </ul>
     *
     * @param other the numeric value to add
     * @return the result of the addition as a {@link NumericValue}
     * @throws UnsupportedOperationException if {@code other} type is not supported
     */
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


    /**
     * Subtracts another numeric value from this RealValue.
     * <p>
     * The behavior is similar to {@link #add(NumericValue)} but performs subtraction.
     *
     * @param other the numeric value to subtract
     * @return the result of the subtraction as a {@link NumericValue}
     * @throws UnsupportedOperationException if {@code other} type is not supported
     */
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


    /**
     * Multiplies this RealValue by another numeric value.
     * <p>
     * The behavior is similar to {@link #add(NumericValue)} but performs multiplication.
     *
     * @param other the numeric value to multiply by
     * @return the result of the multiplication as a {@link NumericValue}
     * @throws UnsupportedOperationException if {@code other} type is not supported
     */
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

    /**
     * Divides this RealValue by another numeric value.
     * <p>
     * Behavior depends on the type of {@code other}:
     * <ul>
     *   <li>If {@code other} is a {@link RealValue}, division is performed on BigDecimal values,
     *       returning NaN if {@code other} is NaN or zero.</li>
     *   <li>If {@code other} is a {@link RationalValue} or {@link IntegerValue}, it is converted
     *       to BigDecimal first; division by zero returns NaN.</li>
     *   <li>If {@code other} is a {@link ComplexValue}, this value is promoted to {@link ComplexValue} and delegated.</li>
     *   <li>Throws {@link UnsupportedOperationException} for unsupported types.</li>
     * </ul>
     *
     * @param other the divisor value
     * @return the result of division as a {@link NumericValue}
     * @throws UnsupportedOperationException if the divisor type is not supported
     */
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


    /**
     * Checks equality between this RealValue and another object.
     * <p>
     * Two RealValues are equal if they are both NaN or their {@link BigDecimal} values are equal.
     *
     * @param o the object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RealValue other)) return false;
        if (this.isNaN && other.isNaN) return true;
        return !this.isNaN && !other.isNaN && this.value.compareTo(other.value) == 0;
    }

    /**
     * Computes the hash code for this RealValue.
     * <p>
     * NaN always returns 0; otherwise, the hash code of the BigDecimal value is returned.
     *
     * @return the hash code of this RealValue
     */
    @Override
    public int hashCode() {
        return isNaN ? 0 : value.hashCode();
    }


    /**
     * Raises this RealValue to the power of the given exponent.
     * <p>
     * If the exponent is:
     * <ul>
     *   <li>A {@link ComplexValue}, delegates to complex power method.</li>
     *   <li>An {@link IntegerValue}, uses BigDecimal.pow with specified precision.</li>
     *   <li>A {@link RealValue}, uses {@link Math#pow(double, double)}.</li>
     *   <li>Otherwise, throws {@link UnsupportedOperationException}.</li>
     * </ul>
     *
     * @param exponent the exponent to raise to
     * @return the result of exponentiation as a {@link NumericValue}
     * @throws UnsupportedOperationException if the exponent type is unsupported
     */
    @Override
    public NumericValue pow(NumericValue exponent) {
        // Delegate to ComplexValue if exponent is complex
        if (exponent instanceof ComplexValue) {
            ComplexValue baseC = new ComplexValue(this.value, BigDecimal.ZERO);
            return baseC.pow(exponent);
        }
        // Integer exponent
        if (exponent instanceof IntegerValue iv) {
            int n = iv.getValue();
            BigDecimal result = value.pow(n, MathContext.DECIMAL128)
                    .setScale(scale, RoundingMode.HALF_UP);
            return new RealValue(result, scale);
        }
        // Real exponent
        if (exponent instanceof RealValue rv) {
            double res = Math.pow(value.doubleValue(), rv.getValue().doubleValue());
            return new RealValue(res, scale);
        }
        throw new UnsupportedOperationException(
                "Unsupported exponent type: " + exponent.getClass());
    }

    /**
     * Computes the nth root of this RealValue.
     * <p>
     * Supports integer and real degrees. Throws {@link UnsupportedOperationException}
     * for unsupported degree types.
     *
     * @param degree the degree of the root
     * @return the nth root as a {@link NumericValue}
     * @throws UnsupportedOperationException if degree type is unsupported
     */
    @Override
    public NumericValue root(NumericValue degree) {
        // nth root: x^(1/n)
        if (degree instanceof IntegerValue iv) {
            int n = iv.getValue();
            double res = Math.pow(value.doubleValue(), 1.0 / n);
            return new RealValue(res, scale);
        } else if (degree instanceof RealValue rv) {
            double res = Math.pow(value.doubleValue(), 1.0 / rv.getValue().doubleValue());
            return new RealValue(res, scale);
        }
        throw new UnsupportedOperationException(
                "Unsupported root degree type: " + degree.getClass());
    }

    /**
     * Computes the logarithm of this RealValue with the given base.
     *
     * @param base the logarithm base
     * @return the logarithm value as a {@link NumericValue}
     */
    @Override
    public NumericValue log(NumericValue base) {
        // log_base(x) = ln(x) / ln(base)
        double x = value.doubleValue();
        double b = (base instanceof IntegerValue iv)
                ? iv.getValue()
                : ((RealValue) base).getValue().doubleValue();
        double res = Math.log(x) / Math.log(b);
        return new RealValue(res, scale);
    }

    /**
     * Computes the multiplicative inverse (reciprocal) of this RealValue.
     * <p>
     * Returns NaN if the value is zero.
     *
     * @return the inverse as a {@link NumericValue}
     */
    @Override
    public NumericValue inverse() {
        // 1 / x
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            return NaN;
        }
        BigDecimal inv = BigDecimal.ONE
                .divide(value, scale, RoundingMode.HALF_UP);
        return new RealValue(inv, scale);
    }

    /**
     * Computes the natural logarithm (ln) of this RealValue.
     * <p>
     * Returns NaN if the value is less than or equal to zero.
     *
     * @return the natural logarithm as a {@link NumericValue}
     */
    @Override
    public NumericValue ln() {
        // natural logarithm
        double x = value.doubleValue();
        if (x <= 0) {
            return NaN;
        }
        double res = Math.log(x);
        return new RealValue(res, scale);
    }


    /**
     * Computes the exponential (e^x) of this RealValue.
     *
     * @return the exponential as a {@link NumericValue}
     */
    @Override
    public NumericValue exp() {
        // exponential e^x
        double res = Math.exp(value.doubleValue());
        return new RealValue(res, scale);
    }

}
