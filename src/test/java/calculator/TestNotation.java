package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;


class TestNotation {

    /* This is an auxilary method to avoid code duplication.
     */
	void testNotation(String s,Operation o,Notation n) {
		assertEquals(s, o.toString(n));
		o.notation = n;
		assertEquals(s, o.toString());
	}

	/* This is an auxilary method to avoid code duplication.
     */
	void testNotations(String symbol,int value1,int value2,Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
	}


	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-"})
	void testOutput(String symbol) {
		int value1 = 8;
		int value2 = 6;
		Operation op = null;
		//List<Expression> params = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
		testNotations(symbol, value1, value2, op);
	}


	@ParameterizedTest
	@EnumSource(Notation.class)
	void testCompositeExpressionConsistentNotation(Notation notation) {
		// Create a composite expression: ( (3 + 4) * 5 )
		Expression inner = null;
		Operation outer = null;
		try {
			// inner: 3 + 4
			inner = new Plus(Arrays.asList(new MyNumber(3), new MyNumber(4)));
			// outer: (inner) * 5
			outer = new Times(Arrays.asList(inner, new MyNumber(5)));
		} catch (IllegalConstruction e) {
			fail("Failed to construct expression: " + e.getMessage());
		}

		// Set the top-level notation
        outer.notation = notation;


		String expectedInner;
		String expectedOuter;
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
		// This assertion should fail with the current bug if the inner expression is not
		// printed using the same notation as the outer expression.
		assertEquals(expectedOuter, outer.toString(),
				"Composite expression did not use a consistent notation: expected " + expectedOuter);
	}






}
