package calculator;

import visitor.Visitor;

/**
 * Expression is an abstract class that represents arithmetic expressions.
 * It has two concrete subclasses Operation and MyNumber.
 *
 * @see Operation
 * @see MyNumber
 */
public interface Expression {

   /**
    * accept is a method needed to implement the visitor design pattern
    *
    * @param v The visitor object being passed as a parameter
    */
   void accept(Visitor v);

   /**
    * Counts the depth of nested expressions in an arithmetic expression
    *
    * @return The depth of an arithmetic expression
    */
   int countDepth();

   /**
    * Counts the number of operations recursively contained in an arithmetic expression
    *
    * @return The number of operations contained in an arithmetic expression
    */
   int countOps();

   /**
    * Counts the number of values recursively contained in an arithmetic expression
    *
    * @return The number of values contained in an arithmetic expression
    */
   int countNbs();

   /**
    * Converts the expression into a string representation using the specified notation.
    *
    * <p>This method returns a string that represents the expression according to the given
    * notation style. The notation can be one of the following:
    * <ul>
    *   <li>{@code PREFIX}: The operator appears before its operands, e.g. {@code + (3, 4)}</li>
    *   <li>{@code INFIX}: The operator appears between its operands, e.g. {@code ( 3 + 4 )}</li>
    *   <li>{@code POSTFIX}: The operator appears after its operands, e.g. {@code (3, 4) +}</li>
    * </ul>
    * If the expression is composite, the specified notation is applied recursively to all sub-expressions.
    *
    * @param n the notation to be used for representing the expression
    * @return the string representation of the expression using the given notation
    */
   String toString(Notation n);

}
