package calculator;

import calculator.util.AngleConverter;
import visitor.Evaluator;
import calculator.values.NumericValue;

/**
 * This class represents the core logic of a Calculator.
 * It can be used to print and evaluate arithmetic expressions.
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

    /**
     * Sets whether angles should be interpreted as radians.
     *
     * @param useRadians true if angles are in radians, false if in degrees
     */
    public void setUseRadians(boolean useRadians) {
        this.useRadians = useRadians;
    }

    /**
     * Returns whether angles are interpreted as radians.
     *
     * @return true if angles are in radians, false if in degrees
     */
    public boolean isUseRadians() {
        return useRadians;
    }

    /**
     * Sets whether to use the inverse (second) trigonometric function.
     * If true, methods like {@code sin}, {@code cos}, and {@code tan} will return their inverse values instead.
     *
     * @param useSecondFunction true to use inverse functions, false for standard trigonometric functions
     */
    public void setUseSecondFunction(boolean useSecondFunction) {
        this.useSecondFunction = useSecondFunction;
    }

    /**
     * Returns whether the inverse (second) trigonometric function is being used.
     *
     * @return true if using inverse trigonometric functions, false if using standard functions
     */
    public boolean isUseSecondFunction() {
        return useSecondFunction;
    }

    /**
     * Calculates the sine or arcsine (if second function is enabled) of the given angle.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the sine or arcsine of the angle
     */
    public double sin(double angle) {
        if (useSecondFunction) {
            return asin(angle);
        }


        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.sin(angle);
    }


    /**
     * Calculates the cosine or arccosine (if second function is enabled) of the given angle.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the cosine or arccosine of the angle
     */
    public double cos(double angle) {

        if (useSecondFunction) {
            return acos(angle);
        }
        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.cos(angle);
    }

    /**
     * Calculates the tangent or arctangent (if second function is enabled) of the given angle.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the tangent or arctangent of the angle
     */
    public double tan(double angle) {

        if (useSecondFunction) {
            return atan(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.tan(angle);
    }

    /**
     * Calculates the arcsine (inverse sine) of the given value.
     *
     * @param value the input value (must be in range [-1, 1])
     * @return the arcsine of the value, in degrees if useRadians is false
     */
    public double asin(double value) {
        return useRadians ? Math.asin(value) : AngleConverter.radiansToDegrees(Math.asin(value));
    }

    /**
     * Calculates the arccosine (inverse cosine) of the given value.
     *
     * @param value the input value (must be in range [-1, 1])
     * @return the arccosine of the value, in degrees if useRadians is false
     */
    public double acos(double value) {
        return useRadians ? Math.acos(value) : AngleConverter.radiansToDegrees(Math.acos(value));
    }


    /**
     * Calculates the arctangent (inverse tangent) of the given value.
     *
     * @param value the input value
     * @return the arctangent of the value, in degrees if useRadians is false
     */
    public double atan(double value) {
        return useRadians ? Math.atan(value) : AngleConverter.radiansToDegrees(Math.atan(value));
    }


    /**
     * Calculates the hyperbolic sine (sinh) or inverse hyperbolic sine (asinh) of the given angle.
     * If the second function flag is enabled, computes asinh instead of sinh.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the sinh or asinh of the input angle
     */
    public double sinh(double angle) {

        if (useSecondFunction) {
            return asinh(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.sinh(angle);
    }

    /**
     * Calculates the hyperbolic cosine (cosh) or inverse hyperbolic cosine (acosh) of the given angle.
     * If the second function flag is enabled, computes acosh instead of cosh.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the cosh or acosh of the input angle
     */
    public double cosh(double angle) {

        if (useSecondFunction) {
            return acosh(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.cosh(angle);
    }

    /**
     * Calculates the hyperbolic tangent (tanh) or inverse hyperbolic tangent (atanh) of the given angle.
     * If the second function flag is enabled, computes atanh instead of tanh.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the tanh or atanh of the input angle
     */
    public double tanh(double angle) {

        if (useSecondFunction) {
            return atanh(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return Math.tanh(angle);
    }

    /**
     * Calculates the hyperbolic cotangent (cot) or inverse cotangent (acot) of the given angle.
     * If the second function flag is enabled, computes acot instead of cot.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the cot or acot of the input angle
     * @throws IllegalArgumentException if value is 0 (undefined for acot)
     */
    public double cot(double angle) {

        if (useSecondFunction) {
            return acot(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return 1.0 / Math.tan(angle);
    }


    /**
     * Calculates the hyperbolic secant (sec) or inverse secant (asec) of the given angle.
     * If the second function flag is enabled, computes asec instead of sec.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the sec or asec of the input angle
     * @throws IllegalArgumentException if the absolute value of value is less than 1 (undefined for asec)
     */
    public double sec(double angle) {

        if (useSecondFunction) {
            return asec(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return 1.0 / Math.cos(angle);
    }

    /**
     * Calculates the hyperbolic cosecant (csc) or inverse cosecant (acsc) of the given angle.
     * If the second function flag is enabled, computes acsc instead of csc.
     *
     * @param angle the input angle in degrees or radians depending on the useRadians flag
     * @return the csc or acsc of the input angle
     * @throws IllegalArgumentException if  the absolute value of value is less than  1 (undefined for acsc)
     */
    public double csc(double angle) {

        if (useSecondFunction) {
            return acsc(angle);
        }

        if (!useRadians) {
            angle = AngleConverter.degreesToRadians(angle);
        }
        return 1.0 / Math.sin(angle);
    }


    /**
     * Calculates the inverse hyperbolic sine (asinh) of the given value.
     *
     * @param value the input value
     * @return the asinh of the value, in degrees if useRadians is false
     */
    public double asinh(double value) {
        // asinh est l'inverse de sinh
        double result = Math.log(value + Math.sqrt(value * value + 1));
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }


    /**
     * Calculates the inverse hyperbolic cosine (acosh) of the given value.
     *
     * @param value the input value; must be ≥ 1
     * @return the acosh of the value, in degrees if useRadians is false
     * @throws IllegalArgumentException if value &lt; 1
     */
    public double acosh(double value) {
        // acosh est défini seulement pour value >= 1
        if (value < 1) {
            throw new IllegalArgumentException("acosh is undefined for values < 1");
        }
        double result = Math.log(value + Math.sqrt(value * value - 1));
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    /**
     * Calculates the inverse hyperbolic tangent (atanh) of the given value.
     *
     * @param value the input value; must be between -1 and 1 (exclusive)
     * @return the atanh of the value, in degrees if useRadians is false
     * @throws IllegalArgumentException if the absolute value of value is  ≥ 1
     */
    public double atanh(double value) {
        // atanh est défini pour -1 < value < 1
        if (value <= -1 || value >= 1) {
            throw new IllegalArgumentException("atanh is undefined for the absolute value of value is >= 1");
        }
        double result = 0.5 * Math.log((1 + value) / (1 - value));
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    /**
     * Calculates the inverse cotangent (acot) of the given value.
     *
     * @param value the input value; must be non-zero
     * @return the acot of the value, in degrees if useRadians is false
     * @throws IllegalArgumentException if value == 0
     */
    public double acot(double value) {
        // acot(x) = arctan(1/x) pour x != 0
        if (value == 0) {
            throw new IllegalArgumentException("acot is undefined for value = 0");
        }
        double result = Math.atan(1.0 / value);
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    /**
     * Calculates the inverse secant (asec) of the given value.
     *
     * @param value the input value; must satisfy the absolute value of value is superior or equal of 1
     * @return the asec of the value, in degrees if useRadians is false
     * @throws IllegalArgumentException if the absolute value of value is less than 1
     */
    public double asec(double value) {
        // asec(x) = arccos(1/x) pour |x| >= 1
        if (Math.abs(value) < 1) {
            throw new IllegalArgumentException("asec is undefined for the absolute value of value is less than 1");
        }
        double result = Math.acos(1.0 / value);
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }

    /**
     * Calculates the inverse cosecant (acsc) of the given value.
     *
     * @param value the input value; must satisfy the absolute value of value is less than ≥ 1
     * @return the acsc of the value, in degrees if useRadians is false
     * @throws IllegalArgumentException if the absolute value of value is less than1
     */
    public double acsc(double value) {
        // acsc(x) = arcsin(1/x) pour |x| >= 1
        if (Math.abs(value) < 1) {
            throw new IllegalArgumentException("acsc is undefined for the absolute value of value is less than 1");
        }
        double result = Math.asin(1.0 / value);
        return useRadians ? result : AngleConverter.radiansToDegrees(result);
    }


}
