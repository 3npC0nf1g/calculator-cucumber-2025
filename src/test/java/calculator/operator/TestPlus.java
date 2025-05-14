package calculator.operator;
import calculator.*;


import calculator.values.IntegerValue;
import calculator.values.NumericValue;

// Import JUnit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestPlus {

	private final NumericValue value1 = new IntegerValue(8);
	private final NumericValue value2 = new IntegerValue(6);
	private Plus op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		params = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			op = new Plus(params);
		} catch (IllegalConstruction e) {
			fail("Failed to construct Plus operation");
		}
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create a Plus expression with a null parameter list
		assertThrows(IllegalConstruction.class, () -> new Plus(null));
	}

	@Test
	void testConstructor2() {
		// A Times expression should not be the same as a Plus expression
		try {
			assertNotSame(op, new Times(new ArrayList<>()));
		} catch (IllegalConstruction e) {
			fail("Unexpected IllegalConstruction in Times");
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		List<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(op, e);
			assertEquals(e, e);
			assertNotEquals(e,
					new Plus(new ArrayList<>(Arrays.asList(new MyNumber(new IntegerValue(5)), new MyNumber(new IntegerValue(4)))), Notation.INFIX));
		} catch (IllegalConstruction e) {
			fail("Unexpected IllegalConstruction in testEquals");
		}
	}

	@Test
	void testNull() {
		assertDoesNotThrow(() -> op == null); // Just verifying that checking null doesn't throw
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		} catch (IllegalConstruction e) {
			fail("Unexpected IllegalConstruction in testHashCode");
		}
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> new Plus(params));
	}
}
