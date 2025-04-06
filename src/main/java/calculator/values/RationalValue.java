package calculator.values;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;

public class RationalValue implements NumericValue {
    private final BigInteger numerator;
    private final BigInteger denominator;

    public RationalValue(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero.");
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

    public RationalValue(int num, int den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public BigInteger getNumerator() { return numerator; }
    public BigInteger getDenominator() { return denominator; }

    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            BigInteger num = numerator.multiply(r.denominator).add(r.numerator.multiply(denominator));
            BigInteger den = denominator.multiply(r.denominator);
            return new RationalValue(num, den);
        } else if (other instanceof IntegerValue) {
            // Convert IntegerValue to RationalValue (denom = 1)
            IntegerValue i = (IntegerValue) other;
            return this.add(new RationalValue(i.getValue(), 1));
        } else if (other instanceof RealValue) {
            // Promote this RationalValue to a RealValue and delegate the addition.
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            RealValue thisAsReal = new RealValue(thisReal.doubleValue(), thisReal.precision());
            return thisAsReal.add(other);
        } else if (other instanceof ComplexValue) {
            // Promote this RationalValue to a ComplexValue (imaginary part = 0)
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            ComplexValue thisAsComplex = new ComplexValue(thisReal, BigDecimal.ZERO);
            return thisAsComplex.add(other);
        }
        throw new UnsupportedOperationException("Cannot add different numeric types.");
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            BigInteger num = numerator.multiply(r.denominator).subtract(r.numerator.multiply(denominator));
            BigInteger den = denominator.multiply(r.denominator);
            return new RationalValue(num, den);
        } else {
            // Similar promotion que pour add()
            return this.add(other.multiply(new IntegerValue(-1)));
        }
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            return new RationalValue(numerator.multiply(r.numerator), denominator.multiply(r.denominator));
        } else if (other instanceof IntegerValue) {
            IntegerValue i = (IntegerValue) other;
            return this.multiply(new RationalValue(i.getValue(), 1));
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

    @Override
    public NumericValue divide(NumericValue other) {
        if (other instanceof RationalValue) {
            RationalValue r = (RationalValue) other;
            if (r.numerator.equals(BigInteger.ZERO)) throw new ArithmeticException("Division by zero");
            return new RationalValue(numerator.multiply(r.denominator), denominator.multiply(r.numerator));
        } else if (other instanceof IntegerValue) {
            IntegerValue i = (IntegerValue) other;
            return this.divide(new RationalValue(i.getValue(), 1));
        } else if (other instanceof RealValue) {
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            RealValue thisAsReal = new RealValue(thisReal.doubleValue(), thisReal.precision());
            return thisAsReal.divide(other);
        } else if (other instanceof ComplexValue) {
            BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
            ComplexValue thisAsComplex = new ComplexValue(thisReal, BigDecimal.ZERO);
            return thisAsComplex.divide(other);
        }
        throw new UnsupportedOperationException("Cannot divide different numeric types.");
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) return numerator.toString();
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RationalValue)) return false;
        RationalValue other = (RationalValue) o;
        return numerator.equals(other.numerator) && denominator.equals(other.denominator);
    }

    @Override
    public int hashCode() {
        return numerator.hashCode() ^ denominator.hashCode();
    }
}
