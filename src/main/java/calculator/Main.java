package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import calculator.values.IntegerValue;
import calculator.values.RealValue;
import calculator.values.ComplexValue;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Software Engineering Lab
 * Faculty of Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * This is the main method of the application.
	 * It provides examples of how to use it to construct and evaluate arithmetic expressions.
	 *
	 * @param args Command-line parameters are not used in this version.
	 */
	public static void main(String[] args) {

		Expression e;
		Calculator c = new Calculator();

		try {
			// Example with MyNumber using an integer value (IntegerValue)
			Expression num1 = new MyNumber(new IntegerValue(8));
			c.print(num1);
			c.eval(num1);

			// Example with Plus operation (addition) using IntegerValue numbers
			List<Expression> params = new ArrayList<>();
			Collections.addAll(params,
					new MyNumber(new IntegerValue(3)),
					new MyNumber(new IntegerValue(4)),
					new MyNumber(new IntegerValue(5))
			);
			e = new Plus(params, Notation.PREFIX);
			c.printExpressionDetails(e);
			c.eval(e);

			// Example with Minus operation (subtraction) using IntegerValue numbers
			List<Expression> params2 = new ArrayList<>();
			Collections.addAll(params2,
					new MyNumber(new IntegerValue(5)),
					new MyNumber(new IntegerValue(3))
			);
			e = new Minus(params2, Notation.INFIX);
			c.print(e);
			c.eval(e);

			// Example with Times operation (multiplication) combining operations: Plus and Minus
			List<Expression> params3 = new ArrayList<>();
			Collections.addAll(params3,
					new Plus(params),
					new Minus(params2)
			);
			e = new Times(params3);
			c.printExpressionDetails(e);
			c.eval(e);

			// Example with Divides operation (division) using a mix of operations and IntegerValue numbers
			List<Expression> params4 = new ArrayList<>();
			Collections.addAll(params4,
					new Plus(params),
					new Minus(params2),
					new MyNumber(new IntegerValue(5))
			);
			e = new Divides(params4, Notation.POSTFIX);
			c.print(e);
			c.eval(e);

			// Example with RealNumber (BigDecimal)
			Expression real1 = new MyNumber(new RealValue(3.14159, 4));
			c.print(real1);
			c.eval(real1);

			// Example with ComplexNumber
			Expression complex1 = new MyNumber(new ComplexValue(2, 3)); // Complex number 2 + 3i
			c.print(complex1);
			c.eval(complex1);

			// ---- Tests pour les combinaisons de types ----

			// Test 1: Real + Complex
			List<Expression> mixRealComplex = new ArrayList<>();
			mixRealComplex.add(new MyNumber(new RealValue(2.5, 3)));     // RealValue with precision 3
			mixRealComplex.add(new MyNumber(new ComplexValue(1, 2)));      // ComplexValue: 1 + 2i
			e = new Plus(mixRealComplex, Notation.INFIX);
			System.out.println("Test: Real + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Evaluation error: " + ex.getMessage());
			}

			// Test 2: Integer + Complex
			List<Expression> mixIntComplex = new ArrayList<>();
			mixIntComplex.add(new MyNumber(new IntegerValue(7)));          // IntegerValue
			mixIntComplex.add(new MyNumber(new ComplexValue(3, -1)));        // ComplexValue: 3 - 1i
			e = new Plus(mixIntComplex, Notation.INFIX);
			System.out.println("Test: Integer + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Evaluation error: " + ex.getMessage());
			}

			// Test 3: Complex - Real
			List<Expression> mixComplexReal = new ArrayList<>();
			mixComplexReal.add(new MyNumber(new ComplexValue(5, 3)));      // ComplexValue: 5 + 3i
			mixComplexReal.add(new MyNumber(new RealValue(2.0, 3)));         // RealValue with precision 3
			e = new Minus(mixComplexReal, Notation.INFIX);
			System.out.println("Test: Complex - Real");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Evaluation error: " + ex.getMessage());
			}

		} catch (IllegalConstruction exception) {
			System.out.println("Cannot create operations without parameters");
		}
	}
}
