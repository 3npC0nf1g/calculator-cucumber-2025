package calculator.values;

public interface NumericValue {
    NumericValue add(NumericValue other);
    NumericValue subtract(NumericValue other);
    NumericValue multiply(NumericValue other);
    NumericValue divide(NumericValue other) throws ArithmeticException;

    boolean equals(Object other);
    int hashCode();
    String toString();

    int getValueInt();

    NumericValue pow(NumericValue exponent);
    NumericValue root(NumericValue degree);
    NumericValue log(NumericValue base);
    NumericValue inverse();
    NumericValue ln();
    NumericValue exp();

}