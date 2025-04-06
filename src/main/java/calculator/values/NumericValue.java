package calculator.values;

public interface NumericValue {
    NumericValue add(NumericValue other);
    NumericValue subtract(NumericValue other);
    NumericValue multiply(NumericValue other);
    NumericValue divide(NumericValue other) throws ArithmeticException;

    boolean equals(Object other);
    int hashCode();
    String toString();

  //  String getValue();
}