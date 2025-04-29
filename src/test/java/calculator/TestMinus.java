package calculator;

// Import JUnit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

class TestMinus {


	private final NumericValue value1 = new IntegerValue(8);
	private final NumericValue value2 = new IntegerValue(6);
	private Minus op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		// Initialize the parameters and the operation
		params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			op = new Minus(params);  // Attempt to construct the Minus operation
		} catch (IllegalConstruction e) {
			fail("Failed to construct Minus operation");
		}
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create a Minus expression with a null parameter list
		assertThrows(IllegalConstruction.class, () -> new Minus(null));
	}

	@Test
	void testConstructor2() {
		// A Times expression should not be the same as a Minus expression
		try {
			assertNotSame(op, new Times(Arrays.asList(new MyNumber(value1), new MyNumber(value2))));
		} catch (IllegalConstruction e) {
			fail("Unexpected IllegalConstruction in Times");
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Minus e = new Minus(p, Notation.INFIX);  // Construct another Minus operation with the same parameters
			assertEquals(op, e);  // They should be equal since they have the same parameters and type
		} catch (IllegalConstruction e) {
			fail("Unexpected IllegalConstruction in testEquals");
		}
	}

	@Test
	void testNull() {
		// Verifying that op is not null, as it should have been properly initialized in setUp()
		assertNotNull(op, "The 'Minus' operation should not be null.");
	}


	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Minus e = new Minus(p, Notation.INFIX);  // Construct another Minus operation with the same parameters
			assertEquals(e.hashCode(), op.hashCode());  // Hash codes should be equal for equal operations
		} catch (IllegalConstruction e) {
			fail("Unexpected IllegalConstruction in testHashCode");
		}
	}

	@Test
	void testNullParamList() {
		// If the parameter list is null, the construction should throw an IllegalConstruction exception
		params = null;
		assertThrows(IllegalConstruction.class, () -> new Minus(params));  // Expecting an exception when passing null
	}

}
