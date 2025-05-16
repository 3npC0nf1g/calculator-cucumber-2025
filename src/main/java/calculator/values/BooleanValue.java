package calculator.values;

/**
 * Represents a boolean value within a numeric context.
 * Logical operations like OR (add), AND (multiply), and NOT are supported.
 * Other numeric operations are not supported.
 */
public class BooleanValue implements NumericValue {
    private final boolean value;

    /**
     * Constructs a BooleanValue.
     *
     * @param value the boolean value
     */
    public BooleanValue(boolean value) {
        this.value = value;
    }

    /**
     * Returns the underlying boolean value.
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Returns "1" for true and "0" for false.
     */
    @Override
    public String toString() {
        return value ? "1" : "0";
    }

    /**
     * Returns the boolean as an int: 1 for true, 0 for false.
     */
    @Override
    public int getValueInt() {
        return value ? 1 : 0;
    }

    /**
     * Logical NOT of the value.
     */
    public NumericValue not() {
        return new BooleanValue(!this.value);
    }

    /**
     * Logical OR (||) as an "add" operation.
     */
    @Override
    public NumericValue add(NumericValue other) {
        if (other instanceof BooleanValue)
            return new BooleanValue(this.value || ((BooleanValue) other).value);
        throw new IllegalArgumentException("Unsupported operand type for add: " + other.getClass().getSimpleName());
    }

    /**
     * Logical AND (&amp;&amp;) as a "multiply" operation.
     */
    @Override
    public NumericValue multiply(NumericValue other) {
        if (other instanceof BooleanValue)
            return new BooleanValue(this.value && ((BooleanValue) other).value);
        throw new IllegalArgumentException("Unsupported operand type for multiply: " + other.getClass().getSimpleName());
    }

    // Unsupported numeric operations throw exceptions

    @Override
    public NumericValue subtract(NumericValue other) {
        throw new UnsupportedOperationException("Subtract not supported for BooleanValue.");
    }

    @Override
    public NumericValue divide(NumericValue other) {
        throw new UnsupportedOperationException("Divide not supported for BooleanValue.");
    }

    @Override
    public NumericValue pow(NumericValue exponent) {
        throw new UnsupportedOperationException("Power not supported for BooleanValue.");
    }

    @Override
    public NumericValue root(NumericValue degree) {
        throw new UnsupportedOperationException("Root not supported for BooleanValue.");
    }

    @Override
    public NumericValue log(NumericValue base) {
        throw new UnsupportedOperationException("Logarithm not supported for BooleanValue.");
    }

    @Override
    public NumericValue inverse() {
        throw new UnsupportedOperationException("Inverse not supported for BooleanValue.");
    }

    @Override
    public NumericValue ln() {
        throw new UnsupportedOperationException("Natural log not supported for BooleanValue.");
    }

    @Override
    public NumericValue exp() {
        throw new UnsupportedOperationException("Exponential not supported for BooleanValue.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BooleanValue)) return false;
        return this.value == ((BooleanValue) obj).value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }
}
