package calculator;

import calculator.util.AngleConverter;
import calculator.values.IntegerValue;
import visitor.Evaluator;
import calculator.values.NumericValue;

/**
 * This class represents the core logic of a Calculator.
 * It can be used to print and evaluate arithmetic expressions.
 *
 * (University of Mons - UMONS, Software Engineering Lab, Faculty of Sciences)
 *
 * @author tommens
 */
public class Calculator {

    private boolean useRadians = true;

    /**
     * Default constructor of the class.
     * Does nothing since the class does not have any variables that need to be initialised.
     */
    public Calculator() {}

    /*
     * For the moment the calculator only contains a print method and an eval method.
     * It would be useful to complete this with a read method, so that we could implement
     * a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R, and other languages.
     * To do so would require implementing a method with the following signature, converting an input string
     * into an arithmetic expression:
     * public Expression read(String s)
     */

    /**
     * Prints an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #printExpressionDetails(Expression)
     */
    public void print(Expression e) {
        System.out.println("The result of evaluating expression " + e);
        System.out.println("is: " + eval(e) + ".");
        System.out.println();
    }

    /**
     * Prints solution provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #printExpressionDetails(Expression)
     */
    public void simple_print(Expression e) {
        System.out.println(eval(e));
    }

    /**
     * Prints verbose details of an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #print(Expression)
     */
    public void printExpressionDetails(Expression e) {
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    /**
     * Evaluates an arithmetic expression and returns its result.
     * @param e the arithmetic Expression to be evaluated
     * @return The result of the evaluation as a NumericValue.
     */
    public NumericValue  eval(Expression e) {
        // Create a new visitor to evaluate expressions.
        Evaluator v = new Evaluator();
        // Ask the expression to accept this visitor to start the evaluation process.
        e.accept(v);
        // Return the result of the evaluation.
        return v.getResult();
    }

    /*
     * We could also have other methods, e.g. to verify whether an expression is syntactically correct
     * public Boolean validate(Expression e)
     * or to simplify some expression
     * public Expression simplify(Expression e)
     */

    public void setUseRadians(boolean useRadians) {
        this.useRadians = useRadians;
    }

    public boolean isUseRadians() {
        return useRadians;
    }

    public double sin(double angle) {
        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.sin(angle);
    }

    public double cos(double angle) {
        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.cos(angle);
    }

    public double tan(double angle) {
        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.tan(angle);
    }

}
