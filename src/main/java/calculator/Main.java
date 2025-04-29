package calculator;

import calculator.values.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			Expression real1 = new MyNumber(new RealValue(1.8E2, 5)); // 1.8 * 10^2
			c.print(real1);
			c.eval(real1);

			// Tests de fonctions trigonométriques
			c.setUseRadians(false); // Mode degré
			System.out.println("La valeur du sinus est " + c.sin(30));

			c.setUseRadians(true); // Mode radian
			System.out.println("La valeur du sinus est " + c.sin(Math.PI / 6));

			// Test avec des nombres rationnels
			Expression rational1 = new MyNumber(new RationalValue(6, 12)); // 6/12
			c.print(rational1);
			c.eval(rational1);

			// Test avec ComplexNumber
			Expression complex1 = new MyNumber(new ComplexValue(2, 3)); // 2 + 3i
			c.print(complex1);
			c.eval(complex1);

			// Tests pour les combinaisons de types
			List<Expression> mixRealComplex = new ArrayList<>();
			mixRealComplex.add(new MyNumber(new RealValue(2.5, 3)));
			mixRealComplex.add(new MyNumber(new ComplexValue(1, 2)));
			e = new Plus(mixRealComplex, Notation.INFIX);
			System.out.println("Test: Real + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage());
			}

			List<Expression> mixIntComplex = new ArrayList<>();
			mixIntComplex.add(new MyNumber(new IntegerValue(7)));
			mixIntComplex.add(new MyNumber(new ComplexValue(3, -1)));
			e = new Plus(mixIntComplex, Notation.INFIX);
			System.out.println("Test: Integer + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage());
			}

			List<Expression> mixComplexReal = new ArrayList<>();
			mixComplexReal.add(new MyNumber(new ComplexValue(5, 3)));
			mixComplexReal.add(new MyNumber(new RealValue(2.0, 3)));
			e = new Minus(mixComplexReal, Notation.INFIX);
			System.out.println("Test: Complex - Real");
			c.print(e);
			try {
				c.eval(e);
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage());
			}

			// Tests d'erreurs
			List<Expression> divByZero = new ArrayList<>();
			divByZero.add(new MyNumber(new IntegerValue(10)));
			divByZero.add(new MyNumber(new IntegerValue(0))); // Division par zéro
			e = new Divides(divByZero, Notation.INFIX);
			System.out.println("Test: Division par zéro (Entiers)");
			try {
				c.eval(e);
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() + "\n");
			}

			Expression sqrtNegative = new MyNumber(new RealValue(-1.0, 3)); // Négatif
			System.out.println("Test: Racine carrée d'un nombre négatif");
			try {
				c.eval(sqrtNegative);
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() + "\n");
			}

			Expression rational2 = new MyNumber(new RationalValue(25, 50));
			c.print(rational2);
			c.eval(rational2);

			Expression realDivByZero = new MyNumber(new RealValue(1.0, 3));
			List<Expression> paramsWithZero = new ArrayList<>();
			paramsWithZero.add(realDivByZero);
			paramsWithZero.add(new MyNumber(new RealValue(0.0, 3)));
			e = new Divides(paramsWithZero, Notation.INFIX);
			System.out.println("Test: Division par zéro (Réels)");
			try {
				c.eval(e);
			} catch (Exception ex) {
				System.out.println("Erreur d'évaluation: " + ex.getMessage() + "\n");
			}

		} catch (IllegalConstruction exception) {
			System.out.println("Impossible de créer des opérations sans paramètres\n");
		}
	}
}
