package calculator.values;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Represents an integer numeric value in the calculator.
 * Implements the {@link NumericValue} interface and supports arithmetic
 * operations with other {@code NumericValue} types, including promotion
 * to {@code RealValue} or {@code ComplexValue} when necessary.
 */
public class IntegerValue implements NumericValue {
    private final int value;

    /**
     * Constructs an {@code IntegerValue} with the specified integer.
     *
     * @param value the integer value to wrap
     */
    public IntegerValue(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value stored in this object.
     *
     * @return the integer value
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the integer representation of this value.
     *
     * @return the integer value (same as {@link #getValue()})
     */
    @Override
    public int getValueInt() {
        return this.value;
    }

    /**
     * Adds this IntegerValue to another NumericValue.
     * Supports addition with IntegerValue, RealValue, and ComplexValue.
     * Performs type promotion where appropriate.
     *
     * @param other the value to add
     * @return a new NumericValue representing the result
     */
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
        else return other.add(this);
    }


    /**
     * Subtracts another NumericValue from this IntegerValue.
     * Supports subtraction with IntegerValue, RealValue, and ComplexValue.
     *
     * @param other the value to subtract
     * @return a new NumericValue representing the result
     * @throws UnsupportedOperationException if the other type is unsupported
     */
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


    /**
     * Multiplies this IntegerValue with another NumericValue.
     * Supports multiplication with IntegerValue, RealValue, and ComplexValue.
     *
     * @param other the value to multiply by
     * @return a new NumericValue representing the result
     * @throws UnsupportedOperationException if the other type is unsupported
     */
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

    /**
     * Divides this IntegerValue by another NumericValue.
     * Supports division with IntegerValue, RealValue, ComplexValue, and RationalValue.
     * Returns a NaN RealValue when dividing by zero.
     *
     * @param other the divisor
     * @return a new NumericValue representing the result
     */
    @Override
    public NumericValue divide(NumericValue other) {
        // First check for zero divisor
        if (isDivisorZero(other)) {
            // return NaN instead of throwing
            return RealValue.NaN;
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
            case ComplexValue cv ->
                    new ComplexValue(BigDecimal.valueOf(this.value), BigDecimal.ZERO).divide(other);
            case RationalValue rv ->  this.multiply(new RealValue(rv.getDenominator().doubleValue(),10)).divide(new RealValue(rv.getNumerator().doubleValue(),10));
            default -> new RationalValue(new BigInteger(this.toString()), new BigInteger(other.toString()));
        };
    }

    /**
     * Helper method that checks if a given NumericValue is a zero divisor.
     *
     * @param divisor the value to check
     * @return true if the divisor represents zero, false otherwise
     */
    private boolean isDivisorZero(NumericValue divisor) {
        return switch (divisor) {
            case IntegerValue iv -> iv.value == 0;
            case RealValue rv -> rv.getValue().compareTo(BigDecimal.ZERO) == 0;
            case ComplexValue cv -> cv.isZero();
            default -> false;
        };
    }

    /**
     * Returns a string representation of the integer value.
     *
     * @return the integer as a string
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }


    /**
     * Compares this IntegerValue to another object for equality.
     *
     * @param other the object to compare with
     * @return true if the other object is an IntegerValue with the same value
     */
    @Override
    public boolean equals(Object other) {
        // Vérifie si l'objet est le même (optimisation de performance)
        if (this == other) return true;

        // Vérifie si l'autre objet est null ou si ce n'est pas un IntegerValue
        if (other == null || getClass() != other.getClass()) return false;

        IntegerValue that = (IntegerValue) other;
        return this.value == that.value;
    }

    /**
     * Returns the hash code of this IntegerValue.
     *
     * @return the hash code computed from the integer value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     * Raises this IntegerValue to the power of the given exponent.
     * Supports integer and real exponents. For negative integer exponents,
     * returns a RationalValue representing the reciprocal.
     *
     * @param exponent the exponent value
     * @return the result of the exponentiation as a NumericValue
     */
    @Override
    public NumericValue pow(NumericValue exponent) {
        if (exponent instanceof IntegerValue iv) {
            int exp = iv.getValue();
            if (exp < 0) {
                // a^(–n) = 1 / (a^n)
                int denom = (int) Math.pow(this.value, -exp);
                return new RationalValue(1, denom);
            }
            // non‐negative exponent as before
            int result = (int) Math.pow(this.value, exp);
            return new IntegerValue(result);
        } else {
            double base = this.value;
            double exp  = ((RealValue) exponent).getValue().doubleValue();
            double res  = Math.pow(base, exp);
            return new RealValue(res, 10);
        }
    }


    /**
     * Computes the n-th root of this IntegerValue.
     * Supports integer and real roots. Always returns a RealValue.
     *
     * @param degree the degree of the root
     * @return the n-th root as a RealValue
     */
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


    /**
     * Computes the logarithm of this IntegerValue with a given base.
     * Supports real, integer, and complex bases. Returns NaN for invalid cases.
     *
     * @param base the base of the logarithm
     * @return the logarithm as a NumericValue
     * @throws UnsupportedOperationException if base is of unsupported type
     */
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

    /**
     * Returns the multiplicative inverse of this IntegerValue.
     * For value zero, returns NaN.
     *
     * @return the inverse as a RationalValue or NaN if not defined
     */
    @Override
    public NumericValue inverse() {
        if (this.value == 0) {
            return RealValue.NaN;
        }
        return new RationalValue(1, this.value);
    }

    /**
     * Computes the natural logarithm (ln) of this IntegerValue.
     * Returns NaN if the value is not strictly positive.
     *
     * @return the natural logarithm as a RealValue
     */
    @Override
    public NumericValue ln() {
        if (this.value <= 0) {
            return RealValue.NaN;
        }
        double res = Math.log(this.value);
        return new RealValue(res, 10);
    }

    /**
     * Computes the exponential function of this IntegerValue.
     * Equivalent to e raised to the power of the integer value.
     *
     * @return the exponential result as a RealValue
     */
    @Override
    public NumericValue exp() {
        double res = Math.exp(this.value);
        return new RealValue(res, 10);
    }
}
