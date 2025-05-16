package calculator.values;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Represents a rational number as a numerator and denominator using {@link BigInteger}.
 * Implements the {@link NumericValue} interface and supports arithmetic, exponentiation,
 * logarithmic, and conversion operations with other numeric types.
 */
public class RationalValue implements NumericValue {
    private final BigInteger numerator;
    private final BigInteger denominator;
    private boolean isNaN = false;

    public static final RationalValue NaN = new RationalValue();

    /**
     * Constructs a new {@code RationalValue} with a specified numerator and denominator.
     * Automatically simplifies the fraction and normalizes the sign.
     *
     * @param numerator the numerator as a {@code BigInteger}
     * @param denominator the denominator as a {@code BigInteger}
     */
    public RationalValue(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            isNaN = true;
        }
        // Normalize sign
        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        // Simplify
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }


    /**
     * Constructs a new {@code RationalValue} from two integers.
     *
     * @param num the integer numerator
     * @param den the integer denominator
     */
    public RationalValue(int num, int den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    private RationalValue() {
        this.numerator = BigInteger.ZERO;
        this.denominator = BigInteger.ONE;
        this.isNaN = true;
    }

    /**
     * Checks whether this rational value is NaN.
     *
     * @return {@code true} if the value is NaN, otherwise {@code false}
     */
    public boolean isNaN() {
        return isNaN;
    }

    /**
     * Returns the numerator of the rational value.
     *
     * @return the numerator
     */
    public BigInteger getNumerator() { return numerator; }

    /**
     * Returns the denominator of the rational value.
     *
     * @return the denominator
     */
    public BigInteger getDenominator() { return denominator; }


    /**
     * Adds this rational value to another numeric value.
     * Supports addition with {@link RationalValue}, {@link IntegerValue}, {@link RealValue}, and {@link ComplexValue}.
     * If types differ, promotes this rational to the appropriate type before adding.
     * Returns {@link #NaN} if either operand is NaN.
     *
     * @param other the numeric value to add
     * @return the sum as a {@link NumericValue}
     * @throws UnsupportedOperationException if the other type is not supported
     */
    @Override
    public NumericValue add(NumericValue other) {
        if (this.isNaN) return NaN;

        if (other instanceof RationalValue rv && rv.isNaN()) return NaN;
        if (other instanceof RationalValue rationalValue) {
            BigInteger num = numerator.multiply(rationalValue.denominator).add(rationalValue.numerator.multiply(denominator));
            BigInteger den = denominator.multiply(rationalValue.denominator);
            return new RationalValue(num, den);
        } else if (other instanceof IntegerValue integerValue) {
            // Convert IntegerValue to RationalValue (denom = 1)
            return this.add(new RationalValue(integerValue.getValue(), 1));
        }  else if (other instanceof RealValue realValue) {
        // Promote this RationalValue to a RealValue and delegate the addition.
        BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
        int scale = Math.max(thisReal.scale(), realValue.getPrecision()); // combine précision des deux
        RealValue thisAsReal = new RealValue(thisReal.doubleValue(), scale);
        return thisAsReal.add(other);
    }
        else if (other instanceof ComplexValue) {
            // Promote this RationalValue to a ComplexValue (imaginary part = 0)
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            ComplexValue thisAsComplex = new ComplexValue(thisReal, BigDecimal.ZERO);
            return thisAsComplex.add(other);
        }
        throw new UnsupportedOperationException("Cannot add different numeric types.");
    }


    /**
     * Subtracts another numeric value from this rational value.
     * Supports subtraction with {@link RationalValue} directly.
     * For other types, converts subtraction into addition of the negation.
     * Returns {@link #NaN} if this value is NaN.
     *
     * @param other the numeric value to subtract
     * @return the difference as a {@link NumericValue}
     * @throws UnsupportedOperationException if the other type is not supported
     */
    @Override
    public NumericValue subtract(NumericValue other) {
        if (this.isNaN) return NaN;

        if (other instanceof RationalValue rationalValue) {
            BigInteger num = numerator.multiply(rationalValue.denominator).subtract(rationalValue.numerator.multiply(denominator));
            BigInteger den = denominator.multiply(rationalValue.denominator);
            return new RationalValue(num, den);
        } else {
            // Similar promotion que pour add()
            return this.add(other.multiply(new IntegerValue(-1)));
        }
    }


    /**
     * Multiplies this rational value by another numeric value.
     * Supports multiplication with {@link RationalValue}, {@link IntegerValue}, {@link RealValue}, and {@link ComplexValue}.
     * Returns {@link #NaN} if either operand is NaN.
     *
     * @param other the numeric value to multiply
     * @return the product as a {@link NumericValue}
     * @throws UnsupportedOperationException if the other type is not supported
     */
    @Override
    public NumericValue multiply(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof RationalValue rv && rv.isNaN()) return NaN;

        if (other instanceof RationalValue rationalValue) {
            return new RationalValue(numerator.multiply(rationalValue.numerator), denominator.multiply(rationalValue.denominator));
        } else if (other instanceof IntegerValue integerValue) {
            return this.multiply(new RationalValue(integerValue.getValue(), 1));
        } else if (other instanceof RealValue) {
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            RealValue thisAsReal = new RealValue(thisReal.doubleValue(), thisReal.precision());
            return thisAsReal.multiply(other);
        } else if (other instanceof ComplexValue) {
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            ComplexValue thisAsComplex = new ComplexValue(thisReal, BigDecimal.ZERO);
            return thisAsComplex.multiply(other);
        }
        throw new UnsupportedOperationException("Cannot multiply different numeric types.");
    }

    /**
     * Divides this rational value by another numeric value.
     * Supports division with {@link RationalValue}, {@link IntegerValue}, {@link RealValue}, and {@link ComplexValue}.
     * Returns {@link #NaN} if division by zero or NaN occurs.
     *
     * @param other the numeric value divisor
     * @return the quotient as a {@link NumericValue}
     * @throws UnsupportedOperationException if the other type is not supported
     */
    @Override
    public NumericValue divide(NumericValue other) {
        if (this.isNaN) return NaN;

        if (other instanceof RationalValue rationalValue) {
            if (rationalValue.getNumerator().equals(BigInteger.ZERO)) {
                return NaN;
            }
            return new RationalValue(numerator.multiply(rationalValue.denominator), denominator.multiply(rationalValue.numerator));
        } else if (other instanceof IntegerValue integerValue) {
            if (integerValue.getValue() == 0) return NaN;
            return this.divide(new RationalValue(integerValue.getValue(), 1));
        } else if (other instanceof RealValue realValue) {
            if (realValue.isNaN() || realValue.getValue().compareTo(BigDecimal.ZERO) == 0) {
                return RationalValue.NaN;
            }
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            RealValue thisAsReal = new RealValue(thisReal, realValue.getPrecision());
            return thisAsReal.divide(realValue);
        }else if (other instanceof ComplexValue complexValue) {
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            ComplexValue thisAsComplex = new ComplexValue(thisReal, BigDecimal.ZERO);
            NumericValue result = thisAsComplex.divide(complexValue);

            // ✅ Retourne bien un ComplexValue si attendu
            if (result instanceof ComplexValue) {
                return result;
            }

            // Fallback en cas d'erreur de division (NaN, etc.)
            return RationalValue.NaN;
        }

        throw new UnsupportedOperationException("Cannot divide different numeric types.");
    }

    /**
     * Returns a string representation of this rational value.
     * If the value is NaN, returns "NaN".
     * If the value is very large or very small, uses scientific notation.
     * If denominator is 1, returns the numerator as integer string.
     * Otherwise returns the fraction in canonical form "numerator/denominator".
     *
     * @return string representation of this rational value
     */
    @Override
    public String toString() {
        if (isNaN) return "NaN";

        // Convertir en BigDecimal pour l'affichage
        BigDecimal decimalValue = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
        BigDecimal absVal = decimalValue.abs();

        // Si la valeur est très grande ou très petite → notation scientifique
        if ((absVal.compareTo(BigDecimal.ONE.movePointRight(6)) > 0) ||
                (absVal.compareTo(BigDecimal.ONE.movePointLeft(6)) < 0 && absVal.compareTo(BigDecimal.ZERO) != 0)) {
            return decimalValue.stripTrailingZeros().toEngineeringString();
        }

        // Si la fraction est entière (dénominateur == 1)
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        }

        // Sinon, afficher sous forme de fraction canonique
        return numerator + "/" + denominator;
    }

    /**
     * Returns the integer value of this rational number.
     * Currently returns 0 as a placeholder (should be implemented properly).
     *
     * @return int value representation of this rational (currently always 0)
     */
    @Override
    public int getValueInt() {
        return 0;
    }


    /**
     * Checks equality between this {@link RationalValue} and another object.
     * Two rational values are equal if both are NaN or if their numerators and denominators
     * are equal after normalization.
     *
     * @param o the object to compare to
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RationalValue other)) return false;
        if (this.isNaN && other.isNaN) return true;
        return !this.isNaN && !other.isNaN &&
                numerator.equals(other.numerator) &&
                denominator.equals(other.denominator);
    }



    /**
     * Returns a hash code consistent with {@link #equals(Object)}.
     * If NaN, returns 0; otherwise XORs the hash codes of numerator and denominator.
     *
     * @return hash code for this rational value
     */
    @Override
    public int hashCode() {
        return isNaN ? 0 : numerator.hashCode() ^ denominator.hashCode();
    }





    /**
     * Raises this rational value to the power of the given exponent.
     * If exponent is an integer, computes (a/b)^n = a^n / b^n exactly.
     * Otherwise promotes this value and the exponent to {@link RealValue} and computes power.
     *
     * @param exponent the exponent value
     * @return the result of raising this value to the given power
     */
    @Override
    public NumericValue pow(NumericValue exponent) {
        // (a/b)^n = a^n / b^n for integer n, else promote to real
        if (exponent instanceof IntegerValue iv) {
            int exp = iv.getValue();
            BigInteger numPow = numerator.pow(exp);
            BigInteger denPow = denominator.pow(exp);
            return new RationalValue(numPow, denPow);
        } else {
            // promote to real for non-integer exponent
            BigDecimal realVal = new BigDecimal(numerator)
                    .divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            double base = realVal.doubleValue();
            double exp = ((RealValue) exponent).getValue().doubleValue();
            double result = Math.pow(base, exp);
            return new RealValue(result, ((RealValue) exponent).getPrecision());
        }
    }


    /**
     * Computes the nth root of this rational value.
     * If degree is an integer, computes a^(1/n) / b^(1/n) and returns a {@link RealValue}.
     * Otherwise promotes to real and computes root using floating point.
     *
     * @param degree the root degree
     * @return the nth root of this value
     */
    @Override
    public NumericValue root(NumericValue degree) {
        // nth root: (a/b)^(1/n) = a^(1/n) / b^(1/n) for integer n, else promote to real
        if (degree instanceof IntegerValue iv) {
            int n = iv.getValue();
            double numRoot = Math.pow(numerator.doubleValue(), 1.0 / n);
            double denRoot = Math.pow(denominator.doubleValue(), 1.0 / n);
            return new RealValue(numRoot / denRoot, 10);
        }
        // non-integer degree -> promote to real
        BigDecimal realVal = new BigDecimal(numerator)
                .divide(new BigDecimal(denominator), MathContext.DECIMAL128);
        double val = realVal.doubleValue();
        double deg = ((RealValue) degree).getValue().doubleValue();
        double result = Math.pow(val, 1.0 / deg);
        return new RealValue(result, ((RealValue) degree).getPrecision());
    }

    /**
     * Computes the logarithm of this rational value with respect to the given base.
     * Uses natural logarithms: log_base(x) = ln(x) / ln(base).
     *
     * @param base the logarithm base
     * @return the logarithm of this value in the given base
     */
    @Override
    public NumericValue log(NumericValue base) {
        // log_base(a/b) = ln(a/b) / ln(base)
        BigDecimal realVal = new BigDecimal(numerator)
                .divide(new BigDecimal(denominator), MathContext.DECIMAL128);
        double z = realVal.doubleValue();
        double b = (base instanceof IntegerValue iv)
                ? iv.getValue()
                : ((RealValue) base).getValue().doubleValue();
        double result = Math.log(z) / Math.log(b);
        return new RealValue(result, ((RealValue) base).getPrecision());
    }



    /**
     * Returns the multiplicative inverse of this rational value.
     * That is, 1/(a/b) = b/a.
     * Returns {@link #NaN} if numerator is zero (division by zero).
     *
     * @return the inverse of this rational value
     */
    @Override
    public NumericValue inverse() {
        // 1/(a/b) = b/a
        if (numerator.equals(BigInteger.ZERO)) {
            return NaN;
        }
        return new RationalValue(denominator, numerator);
    }

    /**
     * Computes the natural logarithm of this rational value.
     * Returns {@link RealValue#NaN} if the value is NaN or non-positive.
     *
     * @return the natural logarithm as a {@link RealValue}
     */
    @Override
    public NumericValue ln() {
        // ln(a/b) = ln(a) - ln(b)
        if (isNaN || numerator.signum() <= 0) {
            return RealValue.NaN;
        }
        double a = numerator.doubleValue();
        double b = denominator.doubleValue();
        double result = Math.log(a) - Math.log(b);
        return new RealValue(result, 10);
    }

    /**
     * Computes the exponential of this rational value.
     * That is, exp(a/b) = e^(a/b).
     *
     * @return the exponential of this value as a {@link RealValue}
     */
    @Override
    public NumericValue exp() {
        // exp(a/b) = exp(a/b) as real
        BigDecimal realVal = new BigDecimal(numerator)
                .divide(new BigDecimal(denominator), MathContext.DECIMAL128);
        double v = realVal.doubleValue();
        double result = Math.exp(v);
        return new RealValue(result, 10);
    }

}
