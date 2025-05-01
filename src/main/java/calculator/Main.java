package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import calculator.values.IntegerValue;
import calculator.values.RealValue;
import calculator.values.ComplexValue;
import calculator.values.RationalValue; // Assurez-vous d'importer la classe pour les rationnels

public class Main {

	public static void main(String[] args) {

		Expression e;
		Calculator c = new Calculator();

		try {
			// Tests avec des nombres entiers
			Expression num1 = new MyNumber(new IntegerValue(8));
			c.print(num1);
			c.eval(num1);

			// Tests avec des opérations sur des entiers
			List<Expression> params = new ArrayList<>();
			Collections.addAll(params,
					new MyNumber(new IntegerValue(3)),
					new MyNumber(new IntegerValue(4)),
					new MyNumber(new IntegerValue(5))
			);
			e = new Plus(params, Notation.PREFIX);
			c.printExpressionDetails(e);
			c.eval(e);

			// Test avec RealNumber (BigDecimal) et notation scientifique
			Expression real1 = new MyNumber(new RealValue(1.8E2, 5)); // Exemple avec une grande valeur en notation scientifique
			c.print(real1);
			c.eval(real1);


			c.setUseRadians(true); // Mode degré
			System.out.println("La valeur du sinus est " + c.sin(30));

		//	c.setUseRadians(true); // Mode radian
			System.out.println("La valeur du sinus est " + c.sin(Math.PI / 6));


			// Test avec des nombres rationnels
			Expression rational1 = new MyNumber(new RationalValue(6, 12)); // 6/12
			c.print(rational1);
			c.eval(rational1);  // Devrait simplifier à 1/2

			// Test avec ComplexNumber
			Expression complex1 = new MyNumber(new ComplexValue(2, 3)); // 2 + 3i
			c.print(complex1);
			c.eval(complex1);

			// ---- Tests pour les combinaisons de types ----

			// Test 1: Real + Complex
			List<Expression> mixRealComplex = new ArrayList<>();
			mixRealComplex.add(new MyNumber(new RealValue(2.5, 3)));     // RealValue avec précision 3
			mixRealComplex.add(new MyNumber(new ComplexValue(1, 2)));     // ComplexValue: 1 + 2i
			e = new Plus(mixRealComplex, Notation.INFIX);
			System.out.println("Test: Real + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test 2: Integer + Complex
			List<Expression> mixIntComplex = new ArrayList<>();
			mixIntComplex.add(new MyNumber(new IntegerValue(7)));          // IntegerValue
			mixIntComplex.add(new MyNumber(new ComplexValue(3, -1)));      // ComplexValue: 3 - 1i
			e = new Plus(mixIntComplex, Notation.INFIX);
			System.out.println("Test: Integer + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test 3: Complex - Real
			List<Expression> mixComplexReal = new ArrayList<>();
			mixComplexReal.add(new MyNumber(new ComplexValue(5, 3)));      // ComplexValue: 5 + 3i
			mixComplexReal.add(new MyNumber(new RealValue(2.0, 3)));       // RealValue avec précision 3
			e = new Minus(mixComplexReal, Notation.INFIX);
			System.out.println("Test: Complex - Real");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() );
			}

			// ---- Tests d'erreurs ----

			// Test de la division par zéro pour les entiers
			List<Expression> divByZero = new ArrayList<>();
			divByZero.add(new MyNumber(new IntegerValue(10)));
			divByZero.add(new MyNumber(new IntegerValue(0)));  // Division par zéro
			e = new Divides(divByZero, Notation.INFIX);
			System.out.println("Test: Division par zéro (Entiers)");
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() +"\n" ); // Devrait attraper l'exception ArithmeticException
			}

			// Test avec la racine carrée d'un nombre négatif pour un nombre réel
			Expression sqrtNegative = new MyNumber(new RealValue(-1.0, 3));  // Nombre négatif
			System.out.println("Test: Racine carrée d'un nombre négatif");
			try {
				c.eval(sqrtNegative);  // Devrait échouer ou générer NaN
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() +"\n" );
			}

			// Test de la simplification d'une fraction rationnelle (12/18)
			Expression rational2 = new MyNumber(new RationalValue(25, 50)); // 12/18
			c.print(rational2);  // Devrait simplifier à 2/3
			c.eval(rational2);

			// Test de la division par zéro avec un nombre réel
			Expression realDivByZero = new MyNumber(new RealValue(1.0, 3));  // 1/0.0
			List<Expression> paramsWithZero = new ArrayList<>();
			paramsWithZero.add(realDivByZero);
			paramsWithZero.add(new MyNumber(new RealValue(0.0, 3))); // Division par zéro
			e = new Divides(paramsWithZero, Notation.INFIX);
			System.out.println("Test: Division par zéro (Réels)");
			try {
				c.eval(e);
			} catch(Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() +"\n" ); // Devrait générer une exception pour NaN
			}






			// 1) Integer power: 2^10 = 1024
			Expression powInt = new Power(
					List.of(
							new MyNumber(new IntegerValue(2)),
							new MyNumber(new IntegerValue(10))
					),
					Notation.INFIX
			);
			System.out.println("Test: Integer Power (2^10)");
			c.print(powInt);
			c.eval(powInt);

			// 2) Real power: 9^(0.5) = 3.0
			Expression powReal = new Power(
					List.of(
							new MyNumber(new RealValue(9.0, 5)),
							new MyNumber(new RealValue(0.5, 5))
					),
					Notation.INFIX
			);
			System.out.println("Test: Real Power (9^0.5)");
			c.print(powReal);
			c.eval(powReal);

			// 3) Integer root: ³√27 = 3
			Expression rootInt = new Root(
					List.of(
							new MyNumber(new IntegerValue(3)),
							new MyNumber(new IntegerValue(27))
					),
					Notation.INFIX
			);
			System.out.println("Test: Integer Root (³√27)");
			c.print(rootInt);
			c.eval(rootInt);

			// 4) Real root: ⁴√16.0 = 2.0
			Expression rootReal = new Root(
					List.of(
							new MyNumber(new IntegerValue(4)),
							new MyNumber(new RealValue(16.0, 5))
					),
					Notation.INFIX
			);
			System.out.println("Test: Real Root (⁴√16.0)");
			c.print(rootReal);
			c.eval(rootReal);

			// 5) Logarithm base 2 of 8: log₂(8) = 3
			Expression logBase = new Log(
					List.of(
							new MyNumber(new IntegerValue(2)),
							new MyNumber(new IntegerValue(8))
					),
					Notation.INFIX
			);
			System.out.println("Test: Logarithm (log₂(8))");
			c.print(logBase);
			c.eval(logBase);

			// 6) Natural log: ln(e) ≈ 1
			Expression lnTest = new Ln(
					List.of(new MyNumber(new RealValue(Math.E, 5))),
					Notation.INFIX
			);
			System.out.println("Test: Natural Log (ln(e))");
			c.print(lnTest);
			c.eval(lnTest);

			// 7) Exponential: exp(1) = e
			Expression expTest = new Exp(
					List.of(new MyNumber(new IntegerValue(1))),
					Notation.INFIX
			);
			System.out.println("Test: Exponential (exp(1))");
			c.print(expTest);
			c.eval(expTest);

			// 8) Inverse: inv(4) = 1/4
			Expression invTest = new Inverse(
					List.of(new MyNumber(new IntegerValue(4))),
					Notation.INFIX
			);
			System.out.println("Test: Inverse (1/4)");
			c.print(invTest);
			c.eval(invTest);

			// 9) Percentage: 50% = 0.5
			Expression pctTest = new Percent(
					List.of(new MyNumber(new RealValue(50.0, 2))),
					Notation.INFIX
			);
			System.out.println("Test: Percentage (50%)");
			c.print(pctTest);
			c.eval(pctTest);



		} catch (IllegalConstruction exception) {
			System.out.println("Impossible de créer des opérations sans paramètres" +"\n" );
		}
	}
}
