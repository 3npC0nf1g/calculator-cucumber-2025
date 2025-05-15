package calculator;

import calculator.util.AngleConverter;
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
    private boolean useSecondFunction = false;

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

    public void setUseSecondFunction(boolean useSecondFunction) {
        this.useSecondFunction = useSecondFunction;
    }

    public boolean isUseSecondFunction() {
        return useSecondFunction;
    }

    public double sin(double angle) {
        if (useSecondFunction) {
            return asin(angle);
        }


        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.sin(angle);
    }

    public double cos(double angle) {

        if (useSecondFunction) {
            return acos(angle);
        }
        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.cos(angle);
    }

    public double tan(double angle) {

        if (useSecondFunction) {
            return atan(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.tan(angle);
    }


    public double asin(double value) {
        return useRadians ? Math.asin(value) : AngleConverter.radiansToDegrees(Math.asin(value));
    }

    public double acos(double value) {
        return useRadians ? Math.acos(value) : AngleConverter.radiansToDegrees(Math.acos(value));
    }

    public double atan(double value) {
        return useRadians ? Math.atan(value) : AngleConverter.radiansToDegrees(Math.atan(value));
    }





    public double sinh(double angle) {

        if (useSecondFunction) {
            return asinh(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.sinh(angle);
    }

    public double cosh(double angle) {

        if (useSecondFunction) {
            return acosh(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.cosh(angle);
    }

    public double tanh(double angle) {

        if (useSecondFunction) {
            return atanh(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.tanh(angle);
    }

    public double cot(double angle) {

        if (useSecondFunction) {
            return acot(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return 1.0 / Math.tan(angle);
    }

    public double sec(double angle) {

        if (useSecondFunction) {
            return asec(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return 1.0 / Math.cos(angle);
    }

    public double csc(double angle) {

        if (useSecondFunction) {
            return acsc(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return 1.0 / Math.sin(angle);
    }


    public double asinh(double value) {
        // asinh est l'inverse de sinh
        double result = Math.log(value + Math.sqrt(value * value + 1));
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    public double acosh(double value) {
        // acosh est défini seulement pour value >= 1
        if (value < 1) {
            throw new IllegalArgumentException("acosh is undefined for values < 1");
        }
        double result = Math.log(value + Math.sqrt(value * value - 1));
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    public double atanh(double value) {
        // atanh est défini pour -1 < value < 1
        if (value <= -1 || value >= 1) {
            throw new IllegalArgumentException("atanh is undefined for |value| >= 1");
        }
        double result = 0.5 * Math.log((1 + value) / (1 - value));
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

// Fonctions inverses des réciproques trigonométriques

    public double acot(double value) {
        // acot(x) = arctan(1/x) pour x != 0
        if (value == 0) {
            throw new IllegalArgumentException("acot is undefined for value = 0");
        }
        double result = Math.atan(1.0 / value);
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    public double asec(double value) {
        // asec(x) = arccos(1/x) pour |x| >= 1
        if (Math.abs(value) < 1) {
            throw new IllegalArgumentException("asec is undefined for |value| < 1");
        }
        double result = Math.acos(1.0 / value);
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    public double acsc(double value) {
        // acsc(x) = arcsin(1/x) pour |x| >= 1
        if (Math.abs(value) < 1) {
            throw new IllegalArgumentException("acsc is undefined for |value| < 1");
        }
        double result = Math.asin(1.0 / value);
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }


}
