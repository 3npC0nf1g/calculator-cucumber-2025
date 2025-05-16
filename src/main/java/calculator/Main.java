package calculator;

import calculator.values.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	private static void log(String message) {
		String timestamp = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(timestamp + " - " + message);
	}

	private static void logError(String message) {
		String timestamp = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.err.println(timestamp + " - ERROR: " + message);
	}

	public static void main(String[] args) {
		log("Current Date and Time (UTC - YYYY-MM-DD HH:MM:SS formatted): " +
				LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		log("Current User's Login: " + System.getProperty("user.name"));

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
			Expression real1 = new MyNumber(new RealValue(1.8E2, 5));
			c.print(real1);
			c.eval(real1);

			c.setUseRadians(true); // Mode degré
			log("La valeur du sinus est " + c.sin(30));
			log("La valeur du sinus est " + c.sin(Math.PI / 6));

			// Test avec des nombres rationnels
			Expression rational1 = new MyNumber(new RationalValue(6, 12)); // 6/12
			c.print(rational1);
			c.eval(rational1);

			// Test avec ComplexNumber
			Expression complex1 = new MyNumber(new ComplexValue(2, 3)); // 2 + 3i
			c.print(complex1);
			c.eval(complex1);

			// ---- Tests pour les combinaisons de types ----
			// Test 1: Real + Complex
			List<Expression> mixRealComplex = new ArrayList<>();
			mixRealComplex.add(new MyNumber(new RealValue(2.5, 3)));
			mixRealComplex.add(new MyNumber(new ComplexValue(1, 2)));
			e = new Plus(mixRealComplex, Notation.INFIX);
			log("Test: Real + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				logError("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test 2: Integer + Complex
			List<Expression> mixIntComplex = new ArrayList<>();
			mixIntComplex.add(new MyNumber(new IntegerValue(7)));
			mixIntComplex.add(new MyNumber(new ComplexValue(3, -1)));
			e = new Plus(mixIntComplex, Notation.INFIX);
			log("Test: Integer + Complex");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				logError("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test 3: Complex - Real
			List<Expression> mixComplexReal = new ArrayList<>();
			mixComplexReal.add(new MyNumber(new ComplexValue(5, 3)));
			mixComplexReal.add(new MyNumber(new RealValue(2.0, 3)));
			e = new Minus(mixComplexReal, Notation.INFIX);
			log("Test: Complex - Real");
			c.print(e);
			try {
				c.eval(e);
			} catch(Exception ex) {
				logError("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test de la division par zéro pour les entiers
			List<Expression> divByZero = new ArrayList<>();
			divByZero.add(new MyNumber(new IntegerValue(10)));
			divByZero.add(new MyNumber(new IntegerValue(0)));
			e = new Divides(divByZero, Notation.INFIX);
			log("Test: Division par zéro (Entiers)");
			try {
				c.eval(e);
			} catch(Exception ex) {
				logError("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test avec la racine carrée d'un nombre négatif
			Expression sqrtNegative = new MyNumber(new RealValue(-1.0, 3));
			log("Test: Racine carrée d'un nombre négatif");
			try {
				c.eval(sqrtNegative);
			} catch (Exception ex) {
				logError("Erreur d'évaluation: " + ex.getMessage());
			}

			// Test de la simplification d'une fraction rationnelle
			Expression rational2 = new MyNumber(new RationalValue(25, 50));
			c.print(rational2);
			c.eval(rational2);

			// Test de la division par zéro avec un nombre réel
			Expression realDivByZero = new MyNumber(new RealValue(1.0, 3));
			List<Expression> paramsWithZero = new ArrayList<>();
			paramsWithZero.add(realDivByZero);
			paramsWithZero.add(new MyNumber(new RealValue(0.0, 3)));
			e = new Divides(paramsWithZero, Notation.INFIX);
			log("Test: Division par zéro (Réels)");
			try {
				c.eval(e);
			} catch(Exception ex) {
				logError("Erreur d'évaluation: " + ex.getMessage());
			}

			// 1) Integer power: 2^10 = 1024
			Expression powInt = new Power(
					List.of(
							new MyNumber(new IntegerValue(2)),
							new MyNumber(new IntegerValue(10))
					),
					Notation.INFIX
			);
			log("Test: Integer Power (2^10)");
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
			log("Test: Real Power (9^0.5)");
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
			log("Test: Integer Root (³√27)");
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
			log("Test: Real Root (⁴√16.0)");
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
			log("Test: Logarithm (log₂(8))");
			c.print(logBase);
			c.eval(logBase);

			// 6) Natural log: ln(e) ≈ 1
			Expression lnTest = new Ln(
					List.of(new MyNumber(new RealValue(Math.E, 5))),
					Notation.INFIX
			);
			log("Test: Natural Log (ln(e))");
			c.print(lnTest);
			c.eval(lnTest);

			// 7) Exponential: exp(1) = e
			Expression expTest = new Exp(
					List.of(new MyNumber(new IntegerValue(1))),
					Notation.INFIX
			);
			log("Test: Exponential (exp(1))");
			c.print(expTest);
			c.eval(expTest);

			// 8) Inverse: inv(4) = 1/4
			Expression invTest = new Inverse(
					List.of(new MyNumber(new IntegerValue(4))),
					Notation.INFIX
			);
			log("Test: Inverse (1/4)");
			c.print(invTest);
			c.eval(invTest);

			// 9) Percentage: 50% = 0.5
			Expression pctTest = new Percent(
					List.of(new MyNumber(new RealValue(50.0, 2))),
					Notation.INFIX
			);
			log("Test: Percentage (50%)");
			c.print(pctTest);
			c.eval(pctTest);

			log("\n------ Advanced Complex/Real Tests ------");

			// 10) Complex Power: (2 + 3i) ^ 2
			Expression complexPow = new Power(
					List.of(
							new MyNumber(new ComplexValue(2, 3)),
							new MyNumber(new IntegerValue(2))
					),
					Notation.INFIX
			);
			log("Test: Complex Power ( (2 + 3i)^2 )");
			c.print(complexPow);
			c.eval(complexPow);

			// 11) Real ^ Complex: 2 ^ (1 + 1i)
			Expression realToComplexPow = new Power(
					List.of(
							new MyNumber(new RealValue(2.0, 5)),
							new MyNumber(new ComplexValue(1, 1))
					),
					Notation.INFIX
			);
			log("Test: Real to Complex Power ( 2 ^ (1 + 1i) )");
			c.print(realToComplexPow);
			c.eval(realToComplexPow);

			// 12) Complex Root: √2(4 + 0i)
			Expression complexRoot = new Root(
					List.of(
							new MyNumber(new IntegerValue(2)),
							new MyNumber(new ComplexValue(4, 0))
					),
					Notation.INFIX
			);
			log("Test: Complex Root ( √2(4 + 0i) )");
			c.print(complexRoot);
			c.eval(complexRoot);

			// 13) Root of Complex Number: ³√(8 + 27i)
			Expression rootOfComplex = new Root(
					List.of(
							new MyNumber(new IntegerValue(3)),
							new MyNumber(new ComplexValue(8, 27))
					),
					Notation.INFIX
			);
			log("Test: Root of Complex ( ³√(8 + 27i) )");
			c.print(rootOfComplex);
			c.eval(rootOfComplex);

			// 14) ln(1 + 1i)
			Expression lnComplex = new Ln(
					List.of(
							new MyNumber(new ComplexValue(1, 1))
					),
					Notation.INFIX
			);
			log("Test: Natural Log of Complex ( ln(1 + 1i) )");
			c.print(lnComplex);
			c.eval(lnComplex);

			// 15) log base Complex: (1 + 1i) log (4)
			Expression logBaseComplex = new Log(
					List.of(
							new MyNumber(new ComplexValue(1, 1)),
							new MyNumber(new IntegerValue(4))
					),
					Notation.INFIX
			);
			log("Test: Log base Complex ( (1 + 1i) log 4 )");
			c.print(logBaseComplex);
			c.eval(logBaseComplex);

			// 16) exp(1 + πi)
			Expression expComplex = new Exp(
					List.of(
							new MyNumber(new ComplexValue(1, Math.PI))
					),
					Notation.INFIX
			);
			log("Test: Exponential of Complex ( exp(1 + πi) )");
			c.print(expComplex);
			c.eval(expComplex);












			// … dans le bloc try { … } de main(), après les autres tests :

// ============ Tests pour les fonctions combinatoires ============

// Test de nCr (combinaison) : 5 choose 3 = 10
			List<Expression> combParams = new ArrayList<>();
			combParams.add(new MyNumber(new IntegerValue(5)));  // n = 5
			combParams.add(new MyNumber(new IntegerValue(3)));  // r = 3
			Expression nCrTest = new NCr(combParams, Notation.INFIX);
			log("Test nCr (5 choose 3): expected 10");
			c.print(nCrTest);
			c.eval(nCrTest);

// Test de nCr avec r = 0 : 7 choose 0 = 1
			combParams = new ArrayList<>();
			combParams.add(new MyNumber(new IntegerValue(7)));
			combParams.add(new MyNumber(new IntegerValue(0)));
			nCrTest = new NCr(combParams, Notation.INFIX);
			log("Test nCr (7 choose 0): expected 1");
			c.print(nCrTest);
			c.eval(nCrTest);

// Test d’erreur nCr avec r > n
			combParams = new ArrayList<>();
			combParams.add(new MyNumber(new IntegerValue(4)));
			combParams.add(new MyNumber(new IntegerValue(5)));
			nCrTest = new NCr(combParams, Notation.INFIX);
			log("Test nCr invalide (4 choose 5) => ArithmeticException attendue");
			try {
				c.eval(nCrTest);
			} catch (ArithmeticException ex) {
				logError("Erreur attendue : " + ex.getMessage());
			}

// -------------------------------------------------------------

// Test de nPr (permutation) : 5P3 = 5 × 4 × 3 = 60
			List<Expression> permParams = new ArrayList<>();
			permParams.add(new MyNumber(new IntegerValue(5)));  // n = 5
			permParams.add(new MyNumber(new IntegerValue(3)));  // r = 3
			Expression nPrTest = new NPr(permParams, Notation.INFIX);
			log("Test nPr (5 P 3): expected 60");
			c.print(nPrTest);
			c.eval(nPrTest);

// Test de nPr avec r = 0 : 7P0 = 1
			permParams = new ArrayList<>();
			permParams.add(new MyNumber(new IntegerValue(7)));
			permParams.add(new MyNumber(new IntegerValue(0)));
			nPrTest = new NPr(permParams, Notation.INFIX);
			log("Test nPr (7 P 0): expected 1");
			c.print(nPrTest);
			c.eval(nPrTest);

// Test d’erreur nPr avec r > n
			permParams = new ArrayList<>();
			permParams.add(new MyNumber(new IntegerValue(3)));
			permParams.add(new MyNumber(new IntegerValue(5)));
			nPrTest = new NPr(permParams, Notation.INFIX);
			log("Test nPr invalide (3 P 5) => ArithmeticException attendue");
			try {
				c.eval(nPrTest);
			} catch (ArithmeticException ex) {
				logError("Erreur attendue : " + ex.getMessage());
			}


		} catch (IllegalConstruction exception) {
			logError("Impossible de créer des opérations sans paramètres");
		}
	}
}