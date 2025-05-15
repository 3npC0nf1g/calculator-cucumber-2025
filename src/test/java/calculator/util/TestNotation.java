package calculator.util;
import calculator.*;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

class TestNotation {

	// Méthode auxiliaire pour tester la notation
	void testNotation(String expected, Operation op, Notation notation) {
		assertEquals(expected, op.toString(notation));  // Vérifie que la notation donnée génère la chaîne attendue
		op.notation = notation;  // Met à jour la notation de l'opération
		assertEquals(expected, op.toString());  // Vérifie que la méthode toString() utilise la notation correcte
	}

	// Méthode auxiliaire pour tester les trois notations (prefixe, infix, postfix)
	void testNotations(String symbol, NumericValue v1, NumericValue v2, Operation op) {
		// Test pour la notation préfixe
		testNotation(symbol + " (" + v1.toString() + ", " + v2.toString() + ")", op, Notation.PREFIX);
		// Test pour la notation infixe
		testNotation("( " + v1.toString() + " " + symbol + " " + v2.toString() + " )", op, Notation.INFIX);
		// Test pour la notation postfixe
		testNotation("(" + v1.toString() + ", " + v2.toString() + ") " + symbol, op, Notation.POSTFIX);
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-"})
	void testOutput(String symbol) {
		// Initialisation des valeurs
		NumericValue value1 = new IntegerValue(8);
		NumericValue value2 = new IntegerValue(6);
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));

		try {
			// Construction de l'opération en fonction du symbole
			switch (symbol) {
				case "+" -> op = new Plus(params);
				case "-" -> op = new Minus(params);
				case "*" -> op = new Times(params);
				case "/" -> op = new Divides(params);
				default -> fail("Invalid symbol: " + symbol);  // Si un symbole invalide est passé, échouer
			}
		} catch (IllegalConstruction e) {
			fail("Operation construction failed for: " + symbol);  // Si la construction de l'opération échoue, échouer
		}

		// Test des différentes notations (préfixe, infixe, postfixe)
		testNotations(symbol, value1, value2, op);
	}

	@ParameterizedTest
	@EnumSource(Notation.class)
	void testCompositeExpressionConsistentNotation(Notation notation) {
		// Création de l'expression composite : ((3 + 4) * 5)
		Expression inner = null;
		Operation outer = null;

		try {
			// (3 + 4)
			inner = new Plus(Arrays.asList(
					new MyNumber(new IntegerValue(3)),
					new MyNumber(new IntegerValue(4))
			));

			// ((3 + 4) * 5)
			outer = new Times(Arrays.asList(
					inner,
					new MyNumber(new IntegerValue(5))
			));
		} catch (IllegalConstruction e) {
			fail("Expression construction failed");  // Si la construction échoue, échouer
		}

		outer.notation = notation;  // Appliquer la notation à l'expression externe

		String expectedInner, expectedOuter;

		// Déterminer l'expression attendue en fonction de la notation
		switch (notation) {
			case PREFIX -> {
				expectedInner = "+ (3, 4)";
				expectedOuter = "* (" + expectedInner + ", 5)";
			}
			case INFIX -> {
				expectedInner = "( 3 + 4 )";
				expectedOuter = "( " + expectedInner + " * 5 )";
			}
			case POSTFIX -> {
				expectedInner = "(3, 4) +";
				expectedOuter = "(" + expectedInner + ", 5) *";
			}
			default -> {
				expectedInner = "";
				expectedOuter = "";
			}
		}

		// Vérifier que l'expression composite utilise la notation correcte
		assertEquals(expectedOuter, outer.toString(),
				"Composite expression did not use a consistent notation: expected " + expectedOuter);
	}
}
