package calculator.values;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ComplexValue implements NumericValue {
    private final BigDecimal realPart;
    private final BigDecimal imaginaryPart;
    private boolean isNaN=false;

    public static final ComplexValue NaN = new ComplexValue();

    public ComplexValue(double realPart, double imaginaryPart) {
        this.realPart = BigDecimal.valueOf(realPart);
        this.imaginaryPart = BigDecimal.valueOf(imaginaryPart);
    }

    private ComplexValue() {
        this.realPart = BigDecimal.ZERO;
        this.imaginaryPart = BigDecimal.ZERO;
        this.isNaN = true;
    }

    public ComplexValue(BigDecimal realPart, BigDecimal imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public boolean isNaN() {
        return isNaN;
    }

    public boolean isZero() {
        return !isNaN && realPart.compareTo(BigDecimal.ZERO) == 0 && imaginaryPart.compareTo(BigDecimal.ZERO) == 0;
    }

    public BigDecimal getReal() {
        return realPart;
    }

    public BigDecimal getImag() {
        return imaginaryPart;
    }

    @Override
    public NumericValue add(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof ComplexValue c && c.isNaN()) return NaN;

        if (other instanceof ComplexValue c) {
            BigDecimal newReal = this.realPart.add(c.realPart);
            BigDecimal newImag = this.imaginaryPart.add(c.imaginaryPart);
            return new ComplexValue(newReal, newImag);
        } else if (other instanceof RealValue r) {
            return new ComplexValue(this.realPart.add(r.getValue()), this.imaginaryPart);
        } else if (other instanceof IntegerValue i) {
            return new ComplexValue(this.realPart.add(BigDecimal.valueOf(i.getValue())), this.imaginaryPart);
        }
        throw new UnsupportedOperationException("Unsupported addition between ComplexValue and " + other.getClass());
    }



    @Override
    public NumericValue subtract(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof ComplexValue c && c.isNaN()) return NaN;

        if (other instanceof ComplexValue c) {
            BigDecimal newReal = this.realPart.subtract(c.realPart);
            BigDecimal newImag = this.imaginaryPart.subtract(c.imaginaryPart);
            return new ComplexValue(newReal.setScale(1, RoundingMode.HALF_UP), newImag.setScale(1, RoundingMode.HALF_UP));
        } else if (other instanceof RealValue r) {
            // Subtracting real part of RealValue from ComplexValue's real part
            BigDecimal newReal = this.realPart.subtract(r.getValue().setScale(r.getPrecision(), RoundingMode.HALF_UP));
            // Round the result to match the expected precision
            return new ComplexValue(newReal.setScale(1, RoundingMode.HALF_UP), this.imaginaryPart.setScale(1, RoundingMode.HALF_UP));
        } else if (other instanceof IntegerValue i) {
            BigDecimal newReal = this.realPart.subtract(BigDecimal.valueOf(i.getValue()));
            return new ComplexValue(newReal.setScale(1, RoundingMode.HALF_UP), this.imaginaryPart.setScale(1, RoundingMode.HALF_UP));
        }
        throw new UnsupportedOperationException("Unsupported subtraction between ComplexValue and " + other.getClass());
    }





    @Override
    public NumericValue multiply(NumericValue other) {
        if (this.isNaN) return NaN;
        if (other instanceof ComplexValue c && c.isNaN()) return NaN;

        if (other instanceof ComplexValue c) {
            BigDecimal newReal = this.realPart.multiply(c.realPart).subtract(this.imaginaryPart.multiply(c.imaginaryPart));
            BigDecimal newImag = this.realPart.multiply(c.imaginaryPart).add(this.imaginaryPart.multiply(c.realPart));
            return new ComplexValue(newReal, newImag);
        } else if (other instanceof RealValue r) {
            return new ComplexValue(this.realPart.multiply(r.getValue()), this.imaginaryPart.multiply(r.getValue()));
        } else if (other instanceof IntegerValue i) {
            return new ComplexValue(this.realPart.multiply(BigDecimal.valueOf(i.getValue())), this.imaginaryPart.multiply(BigDecimal.valueOf(i.getValue())));
        }
        throw new UnsupportedOperationException("Unsupported multiplication between ComplexValue and " + other.getClass());
    }

    @Override
    public NumericValue divide(NumericValue other) {
        if (this.isNaN) return NaN;

        // Handle division by complex number
        if (other instanceof ComplexValue c) {
            if (c.isNaN() || c.isZero()) return NaN;

            // Calculate denominator: real^2 + imaginary^2
            BigDecimal denominator = c.realPart.multiply(c.realPart).add(c.imaginaryPart.multiply(c.imaginaryPart));
            // Check for division by zero in complex number case

            // Calculate new real and imaginary parts
            BigDecimal newReal = this.realPart.multiply(c.realPart).add(this.imaginaryPart.multiply(c.imaginaryPart))
                    .divide(denominator, MathContext.DECIMAL128); // Set high precision for complex number division
            BigDecimal newImag = this.imaginaryPart.multiply(c.realPart).subtract(this.realPart.multiply(c.imaginaryPart))
                    .divide(denominator, MathContext.DECIMAL128);
            return new ComplexValue(newReal.setScale(2, RoundingMode.HALF_UP), newImag.setScale(2, RoundingMode.HALF_UP)); // Rounded to 2 decimal places
        }
        // Handle division by real number
        else if (other instanceof RealValue r) {
            if (r.getValue().compareTo(BigDecimal.ZERO) == 0) return NaN;
            // Perform division for both real and imaginary parts
            return new ComplexValue(this.realPart.divide(r.getValue(), MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP),
                    this.imaginaryPart.divide(r.getValue(), MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP));
        }
        // Handle division by integer value
        else if (other instanceof IntegerValue i) {
            if (i.getValue() == 0) return NaN;

            BigDecimal divisor = BigDecimal.valueOf(i.getValue());
            return new ComplexValue(this.realPart.divide(divisor, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP),
                    this.imaginaryPart.divide(divisor, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP));
        }

        // Handle unsupported division types
        throw new UnsupportedOperationException("Unsupported division between ComplexValue and " + other.getClass());
    }


    @Override
    public String toString() {
        if (isNaN) return "NaN";
        return realPart + " + " + imaginaryPart + "i";
    }

    @Override
    public int getValueInt() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ComplexValue other)) return false;
        if (this.isNaN && other.isNaN) return true;
        return !this.isNaN && !other.isNaN &&
                realPart.equals(other.realPart) &&
                imaginaryPart.equals(other.imaginaryPart);
    }

    @Override
    public int hashCode() {
        return isNaN ? 0 : realPart.hashCode() ^ imaginaryPart.hashCode();
    }















    // In ComplexValue.java

    @Override
    public NumericValue pow(NumericValue exponent) {
        // 1) Promote exponent to ComplexValue
        final ComplexValue w = switch (exponent) {
            case ComplexValue cv -> cv;
            case RealValue rv -> new ComplexValue(rv.getValue(), BigDecimal.ZERO);
            case IntegerValue iv -> new ComplexValue(BigDecimal.valueOf(iv.getValue()), BigDecimal.ZERO);
            default -> throw new IllegalArgumentException(
                    "Unsupported exponent type: " + exponent.getClass()
            );
        };

        // 2) Compute ln(z)
        BigDecimal re = realPart;
        BigDecimal       im = imaginaryPart;
        BigDecimal modulus = re.pow(2)
                .add(im.pow(2))
                .sqrt(MathContext.DECIMAL128);
        double r = modulus.doubleValue();
        double theta = Math.atan2(im.doubleValue(), re.doubleValue());
        ComplexValue lnZ = new ComplexValue(
                BigDecimal.valueOf(Math.log(r)),
                BigDecimal.valueOf(theta)
        );

        // 3) w * ln(z)
        NumericValue wLnZ = w.multiply(lnZ);

        // 4) exp(w * ln(z))
        return ((ComplexValue) wLnZ).exp();
    }


    @Override
    public NumericValue root(NumericValue degree) {
        // nth root: z^(1/n)
        if (degree instanceof IntegerValue iv) {
            double n = iv.getValue();
            // reuse pow implementation with exponent 1/n
            double invDeg = 1.0 / n;
            ComplexValue fractionalExp = new ComplexValue(
                    BigDecimal.valueOf(invDeg),
                    BigDecimal.ZERO
            );
            return this.pow(fractionalExp);
        }
        throw new UnsupportedOperationException(
                "Root for complex with non-integer degree not supported."
        );
    }

    @Override
    public NumericValue log(NumericValue base) {
        // log_base(z) = ln(z) / ln(base)
        if (!(base instanceof ComplexValue b)) {
            throw new IllegalArgumentException("Base must be a ComplexValue");
        }
        ComplexValue lnZ = (ComplexValue) this.ln();
        ComplexValue lnB = (ComplexValue) b.ln();
        return lnZ.divide(lnB);
    }


    @Override
    public NumericValue inverse() {
        // 1/z = conjugate(z) / |z|^2
        BigDecimal denom = realPart.pow(2).add(imaginaryPart.pow(2));
        if (denom.compareTo(BigDecimal.ZERO) == 0) return NaN;
        ComplexValue conj = new ComplexValue(realPart, imaginaryPart.negate());
        return conj.divide(new ComplexValue(denom.doubleValue(), 0));
    }

    @Override
    public NumericValue ln() {
        // ln(z) = ln|z| + i*arg(z)
        double r = realPart.pow(2).add(imaginaryPart.pow(2)).sqrt(MathContext.DECIMAL128).doubleValue();
        double theta = Math.atan2(imaginaryPart.doubleValue(), realPart.doubleValue());
        return new ComplexValue(BigDecimal.valueOf(Math.log(r)), BigDecimal.valueOf(theta));
    }

    @Override
    public NumericValue exp() {
        // exp(a + ib) = exp(a)*(cos(b) + i*sin(b))
        double a = realPart.doubleValue();
        double b = imaginaryPart.doubleValue();
        double expa = Math.exp(a);
        return new ComplexValue(BigDecimal.valueOf(expa * Math.cos(b)), BigDecimal.valueOf(expa * Math.sin(b)));
    }

    public double modulus() {
        if (isNaN) return Double.NaN;
        return realPart.pow(2)
                .add(imaginaryPart.pow(2))
                .sqrt(MathContext.DECIMAL128)
                .doubleValue();
    }

}
