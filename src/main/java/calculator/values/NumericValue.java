package calculator.values;

import calculator.Expression;

public interface NumericValue extends Expression {
    NumericValue add(NumericValue other);
    NumericValue subtract(NumericValue other);
    NumericValue multiply(NumericValue other);
    NumericValue divide(NumericValue other) throws ArithmeticException;

    boolean equals(Object other);
    int hashCode();
    String toString();

    int getValueInt();


}