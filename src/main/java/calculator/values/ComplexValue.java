package calculator.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComplexValue implements NumericValue {
    private final BigDecimal realPart;
    private final BigDecimal imaginaryPart;

    public ComplexValue(double realPart, double imaginaryPart) {
        this.realPart = BigDecimal.valueOf(realPart);
        this.imaginaryPart = BigDecimal.valueOf(imaginaryPart);
    }

    public ComplexValue(BigDecimal realPart, BigDecimal imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public BigDecimal getReal() {
        return realPart;
    }

    public BigDecimal getImag() {
        return imaginaryPart;
    }

    @Override
    public NumericValue add(NumericValue other) {
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
        if (other instanceof ComplexValue c) {
            BigDecimal newReal = this.realPart.subtract(c.realPart);
            BigDecimal newImag = this.imaginaryPart.subtract(c.imaginaryPart);
            return new ComplexValue(newReal, newImag);
        } else if (other instanceof RealValue r) {
            return new ComplexValue(this.realPart.subtract(r.getValue()), this.imaginaryPart);
        } else if (other instanceof IntegerValue i) {
            return new ComplexValue(this.realPart.subtract(BigDecimal.valueOf(i.getValue())), this.imaginaryPart);
        }
        throw new UnsupportedOperationException("Unsupported subtraction between ComplexValue and " + other.getClass());
    }

    @Override
    public NumericValue multiply(NumericValue other) {
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
        String dividedZero = "Division by zero";
        if (other instanceof ComplexValue c) {
            BigDecimal denominator = c.realPart.multiply(c.realPart).add(c.imaginaryPart.multiply(c.imaginaryPart));
            if (denominator.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException(dividedZero);
            BigDecimal newReal = this.realPart.multiply(c.realPart).add(this.imaginaryPart.multiply(c.imaginaryPart)).divide(denominator, RoundingMode.HALF_UP);
            BigDecimal newImag = this.imaginaryPart.multiply(c.realPart).subtract(this.realPart.multiply(c.imaginaryPart)).divide(denominator, RoundingMode.HALF_UP);
            return new ComplexValue(newReal, newImag);
        } else if (other instanceof RealValue r) {
            BigDecimal divisor = r.getValue();
            if (divisor.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException(dividedZero);
            return new ComplexValue(this.realPart.divide(divisor, RoundingMode.HALF_UP), this.imaginaryPart.divide(divisor, RoundingMode.HALF_UP));
        } else if (other instanceof IntegerValue i) {
            if (i.getValue() == 0) throw new ArithmeticException(dividedZero);
            BigDecimal divisor = BigDecimal.valueOf(i.getValue());
            return new ComplexValue(this.realPart.divide(divisor, RoundingMode.HALF_UP), this.imaginaryPart.divide(divisor, RoundingMode.HALF_UP));
        }
        throw new UnsupportedOperationException("Unsupported division between ComplexValue and " + other.getClass());
    }

    @Override
    public String toString() {
        return realPart + " + " + imaginaryPart + "i";
    }

    @Override
    public int getValueInt() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ComplexValue other)) return false;
        return realPart.equals(other.realPart) && imaginaryPart.equals(other.imaginaryPart);
    }

    @Override
    public int hashCode() {
        return realPart.hashCode() ^ imaginaryPart.hashCode();
    }
}
