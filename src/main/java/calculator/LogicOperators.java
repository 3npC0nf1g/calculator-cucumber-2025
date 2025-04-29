package calculator;

import calculator.values.IntegerValue;

/**
 * LogicOperators represents a number that can only be 0 (true) or 1 (false) in logical operations.
 */
public class LogicOperators extends MyNumber {

    /**
     * Constructor method for LogicOperators.
     * Accepts only 0 or 1, otherwise throws an InvalidNumberException.
     *
     * @param v The integer value to be contained in the object
     * @throws InvalidNumberException if v is not 0 or 1
     */
    public LogicOperators(int v) throws InvalidNumberException {
        super(new IntegerValue(v)); // Create with a proper NumericValue
        if (v != 0 && v != 1) {
            throw new InvalidNumberException("Unexpected value: must be 0 or 1.");
        }
    }

    public static class InvalidNumberException extends Exception {
        public InvalidNumberException(String m) {
            super(m);
        }
    }
}
