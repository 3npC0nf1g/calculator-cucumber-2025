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
     * Getter method to obtain the value contained in the object
     *
     * @return The numeric value contained in the object
     */
    public NumericValue getValue() {
        return value;
    }

    /**
     * Constructor method
     *
     * @param v The numeric value to be contained in the object
     */
    public MyNumber(NumericValue v) {
        value = v;
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

    /**
     * The depth of a number expression is always 0
     *
     * @return The depth of a number expression
     */
    public int countDepth() {
        return 0;
    }

    /**
     * The number of operations contained in a number expression is always 0
     *
     * @return The number of operations contained in a number expression
     */
    public int countOps() {
        return 0;
    }

    /**
     * The number of numbers contained in a number expression is always 1
     *
     * @return The number of numbers contained in a number expression
     */
    public int countNbs() {
        return 1;
    }

    @Override
    public String toString(Notation n) {
        PrintVisitor pv = new PrintVisitor(n);
        this.accept(pv);
        return pv.getResult();
    }

    /**
     * Convert a number into a String to allow it to be printed.
     *
     * @return The String that is the result of the conversion.
     */
    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * Two MyNumber expressions are equal if the values they contain are equal
     *
     * @param o The object to compare to
     * @return A boolean representing the result of the equality test
     */
    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        // If the object is of another type then return false
        if (!(o instanceof MyNumber)) {
            return false;
        }

        return this.value.equals(((MyNumber) o).value);
    }

    /**
     * The method hashCode needs to be overridden if the equals method is overridden;
     * otherwise there may be problems when you use your object in hashed collections
     * such as HashMap, HashSet, LinkedHashSet.
     *
     * @return The result of computing the hash.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
