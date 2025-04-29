package calculator;

import calculator.values.NumericValue;
import visitor.PrintVisitor;
import visitor.Visitor;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class MyNumber implements Expression {
    private final NumericValue value;

    /**
     * Constructor for MyNumber using a NumericValue.
     *
     * @param value The numeric value contained in the object
     */
    public MyNumber(NumericValue value) {
        this.value = value;
    }

    /**
     * Getter method to obtain the value contained in the object.
     *
     * @return The numeric value contained in the object
     */
    public NumericValue getValue() {
        return value;
    }

    /**
     * Convert to double if needed (for evaluation purposes).
     *
     * @return The double representation of the value
     */
    public double getDoubleValue() {
        return value.getValueInt();
    }

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v The visitor object
     */
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public int countDepth() {
        return 0;
    }

    @Override
    public int countOps() {
        return 0;
    }

    @Override
    public int countNbs() {
        return 1;
    }

    @Override
    public String toString(Notation n) {
        PrintVisitor pv = new PrintVisitor(n);
        this.accept(pv);
        return pv.getResult();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof MyNumber)) return false;
        return this.value.equals(((MyNumber) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
