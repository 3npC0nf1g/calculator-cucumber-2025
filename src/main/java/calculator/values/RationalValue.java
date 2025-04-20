package calculator.values;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;

public class RationalValue implements NumericValue {
    private final BigInteger numerator;
    private final BigInteger denominator;
    private boolean isNaN = false;

    public static final RationalValue NaN = new RationalValue();

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

    public RationalValue(int num, int den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    private RationalValue() {
        this.numerator = BigInteger.ZERO;
        this.denominator = BigInteger.ONE;
        this.isNaN = true;
    }

    public boolean isNaN() {
        return isNaN;
    }

    public BigInteger getNumerator() { return numerator; }
    public BigInteger getDenominator() { return denominator; }

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
        }  else if (other instanceof RealValue) {
        // Promote this RationalValue to a RealValue and delegate the addition.
        BigDecimal thisReal = new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL128);
        int scale = Math.max(thisReal.scale(), ((RealValue) other).getPrecision()); // combine précision des deux
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



    @Override
    public int getValueInt() {
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RationalValue other)) return false;
        if (this.isNaN && other.isNaN) return true;
        return !this.isNaN && !other.isNaN &&
                numerator.equals(other.numerator) &&
                denominator.equals(other.denominator);
    }

    @Override
    public int hashCode() {
        return isNaN ? 0 : numerator.hashCode() ^ denominator.hashCode();
    }
}
