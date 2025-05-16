package calculator.operator;
import calculator.*;


//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.values.ComplexValue;
import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import calculator.values.RealValue;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestDivides {

	private final NumericValue value1 = new IntegerValue(8);
	private final NumericValue value2 = new IntegerValue(6);
	private Divides op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		  params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		  try {
		  	op = new Divides(params);
			op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
		  }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testConstructor2() {
		// A Times expression should not be the same as a Divides expression
		try {
			assertNotSame(op, new Times(new ArrayList<>()));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Divides d = new Divides(p, Notation.INFIX);
			assertEquals(op, d);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@SuppressWarnings("ConstantConditions")
	@Test
	void testNull() {
		assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Divides e = new Divides(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Divides(params));
	}

	@Test
	void testDivisionByZeroRational() {
		try {
			Divides division = new Divides(Arrays.asList(new MyNumber(new IntegerValue(78)),new MyNumber(new IntegerValue(0))));
			Calculator calculator = new Calculator();
			NumericValue result = calculator.eval(division);
            assertEquals("NaN", result.toString(),"Inter : 78 / 0 should be NaN");
		} catch (IllegalConstruction e) {
			fail("Should not throw IllegalConstruction");
		}
	}

	@Test
	void testDivisionByZeroComplex() {
			ComplexValue c1 = new ComplexValue(5.0, 3.0);
			ComplexValue quotient = (ComplexValue) c1.divide(new IntegerValue(0));
			assertEquals("NaN", quotient.toString(), "Complex : (5+3i) / 0 should be NaN");
	}

	@Test
	void testDivisionByZeroReal() {
		RealValue r1 = new RealValue(6.0, 3);
		RealValue r2 = new RealValue(0.0, 3);
		RealValue result = (RealValue) r1.divide(r2);

		assertEquals("NaN", result.toString(),"Real : 6.0 / 0.0 should be NaN");
	}

	@Test
	void testComplexPostfixDivisionByZero() {
		try {
			Expression left = new Plus(Arrays.asList(new MyNumber(new IntegerValue(8)), new MyNumber(new IntegerValue(2))));
			Expression right = new Minus(Arrays.asList(new MyNumber(new IntegerValue(3)), new MyNumber(new IntegerValue(3))));
			Divides division = new Divides(Arrays.asList(left, right));
			division.notation = Notation.POSTFIX;

			Calculator calculator = new Calculator();
			NumericValue result = calculator.eval(division);
			assertEquals("NaN", result.toString(), "Postfix: (8 + 2) / (3 - 3) should return NaN");
		} catch (Exception e) {
			fail("Should not throw exception in postfix complex division by zero");
		}
	}

	@Test
	void testComplexPrefixDivisionByZero() {
		try {
			Expression left = new MyNumber(new IntegerValue(10));
			Expression right = new Minus(Arrays.asList(new MyNumber(new IntegerValue(2)), new MyNumber(new IntegerValue(2))));
			Divides division = new Divides(Arrays.asList(left, right));
			division.notation = Notation.PREFIX;

			Calculator calculator = new Calculator();
			NumericValue result = calculator.eval(division);
			assertEquals("NaN", result.toString(), "Prefix: 10 / (2 - 2) should return NaN");
		} catch (Exception e) {
			fail("Should not throw exception in prefix complex division by zero");
		}
	}
}
