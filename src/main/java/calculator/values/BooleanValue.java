package calculator.values;

public class BooleanValue implements NumericValue {
    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value ? "1" : "0";
    }

    @Override
    public int getValueInt() {
        return 0;
    }

    @Override
    public NumericValue pow(NumericValue exponent) {
        return null;
    }

    @Override
    public NumericValue root(NumericValue degree) {
        return null;
    }

    @Override
    public NumericValue log(NumericValue base) {
        return null;
    }

    @Override
    public NumericValue inverse() {
        return null;
    }

    @Override
    public NumericValue ln() {
        return null;
    }

    @Override
    public NumericValue exp() {
        return null;
    }

    @Override
    public NumericValue add(NumericValue other) {
        return new BooleanValue(this.value || ((BooleanValue) other).value);
    }

    @Override
    public NumericValue subtract(NumericValue other) {
        return null;
    }

    @Override
    public NumericValue multiply(NumericValue other) {
        return new BooleanValue(this.value && ((BooleanValue) other).value);
    }

    @Override
    public NumericValue divide(NumericValue other) throws ArithmeticException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public NumericValue not() {
        return new BooleanValue(!this.value);
    }

}
