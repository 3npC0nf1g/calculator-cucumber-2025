package calculator.values;

/**
 * Interface representing a numeric value that supports arithmetic operations
 * such as addition, subtraction, multiplication, division, exponentiation,
 * and more advanced mathematical functions.
 *
 * Implementing classes can include integer, real, complex, or rational types.
 */
public interface NumericValue {

    /**
     * Adds this value to another numeric value.
     *
     * @param other the value to add
     * @return the result of the addition
     */
    NumericValue add(NumericValue other);

    /**
     * Subtracts another numeric value from this value.
     *
     * @param other the value to subtract
     * @return the result of the subtraction
     */
    NumericValue subtract(NumericValue other);

    /**
     * Multiplies this value by another numeric value.
     *
     * @param other the value to multiply by
     * @return the result of the multiplication
     */
    NumericValue multiply(NumericValue other);

    /**
     * Divides this value by another numeric value.
     *
     * @param other the divisor
     * @return the result of the division
     * @throws ArithmeticException if division is not defined (e.g., divide by zero)
     */
    NumericValue divide(NumericValue other) throws ArithmeticException;

    /**
     * Checks whether this value is equal to another object.
     *
     * @param other the object to compare
     * @return true if the values are equal, false otherwise
     */
    boolean equals(Object other);

    /**
     * Returns a hash code consistent with equals.
     *
     * @return the hash code
     */
    int hashCode();

    /**
     * Returns a string representation of the value.
     *
     * @return the string representation
     */
    String toString();

    /**
     * Returns an integer representation of the value,
     * possibly with precision loss for non-integer types.
     *
     * @return the integer approximation of the value
     */
    int getValueInt();

    /**
     * Raises this value to the power of the given exponent.
     *
     * @param exponent the exponent value
     * @return the result of the exponentiation
     */
    NumericValue pow(NumericValue exponent);

    /**
     * Computes the root of this value with respect to the given degree.
     *
     * @param degree the degree of the root
     * @return the result of the root operation
     */
    NumericValue root(NumericValue degree);

    /**
     * Computes the logarithm of this value with a given base.
     *
     * @param base the base of the logarithm
     * @return the result of the logarithm
     */
    NumericValue log(NumericValue base);

    /**
     * Computes the multiplicative inverse (1 / value) of this number.
     *
     * @return the inverse of this value
     */
    NumericValue inverse();

    /**
     * Computes the natural logarithm (base e) of this value.
     *
     * @return the natural logarithm
     */
    NumericValue ln();

    /**
     * Computes the exponential function of this value (e^value).
     *
     * @return the exponential of this value
     */
    NumericValue exp();
}
