package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

class TestOperation {

	private Operation o;
	private Operation o2;
	private final NumericValue value1 = new IntegerValue(3);
	private final NumericValue value2 = new IntegerValue(4);
	private final NumericValue value3 = new IntegerValue(5);
	private final NumericValue value4 = new IntegerValue(7);

	@BeforeEach
	void setUp() throws Exception {
		List<Expression> params1 = Arrays.asList(new MyNumber(value1), new MyNumber(value2), new MyNumber(value3));
		List<Expression> params2 = Arrays.asList(new MyNumber(value3), new MyNumber(value2));
		List<Expression> params3 = Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(value4));
		o = new Divides(params3);
		o2 = new Divides(params3);
	}

	@Test
	void testEquals() {
		assertEquals(o,o2);
	}

	@Test
	void testCountDepth() {
		assertEquals(2, o.countDepth());
	}

	@Test
	void testCountOps() {
		assertEquals(3, o.countOps());
	}

	@Test
	void testCountNbs() {
		assertEquals(Integer.valueOf(6), o.countNbs());
	}

}
