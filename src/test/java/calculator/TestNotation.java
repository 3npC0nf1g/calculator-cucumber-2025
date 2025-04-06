package calculator;

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
		assertEquals(expected, op.toString(notation));
		op.notation = notation;
		assertEquals(expected, op.toString());
	}

	// Méthode auxiliaire pour tester les trois notations
	void testNotations(String symbol, NumericValue v1, NumericValue v2, Operation op) {
		testNotation(symbol + " (" + v1.getValue() + ", " + v2.getValue() + ")", op, Notation.PREFIX);
		testNotation("( " + v1.getValue() + " " + symbol + " " + v2.getValue() + " )", op, Notation.INFIX);
		testNotation("(" + v1.getValue() + ", " + v2.getValue() + ") " + symbol, op, Notation.POSTFIX);
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-"})
	void testOutput(String symbol) {
		NumericValue value1 = new IntegerValue(8);
		NumericValue value2 = new IntegerValue(6);
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));

		try {
			switch (symbol) {
				case "+" -> op = new Plus(params);
				case "-" -> op = new Minus(params);
				case "*" -> op = new Times(params);
				case "/" -> op = new Divides(params);
				default -> fail("Invalid symbol: " + symbol);
			}
		} catch (IllegalConstruction e) {
			fail("Operation construction failed for: " + symbol);
		}

		testNotations(symbol, value1, value2, op);
	}

	@ParameterizedTest
	@EnumSource(Notation.class)
	void testCompositeExpressionConsistentNotation(Notation notation) {
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
			fail("Expression construction failed");
		}

		outer.notation = notation;

		String expectedInner, expectedOuter;

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

		assertEquals(expectedOuter, outer.toString(),
				"Composite expression did not use a consistent notation: expected " + expectedOuter);
	}
}
