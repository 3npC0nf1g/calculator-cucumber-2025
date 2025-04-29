package calculator;

import calculator.values.NumericValue;

public class InvalidNumber extends MyNumber {
    public  int val;
    public InvalidNumber(NumericValue value) {
        super(value);
    }

    /**
     * Convert a MyNotANumber into a String to allow it to be printed.
     *
     * @return	The String that is the result of the conversion.
     */
    @Override
    public String toString() {
        return "NaN";
    }
}
