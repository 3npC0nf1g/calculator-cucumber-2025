package calculator.visitor;

import calculator.*;
import calculator.logicOperator.AndOperator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AndOperatorTest {

    @Test
    void testAndWithBothFalse() throws IllegalConstruction {
        List<Expression> params = null;
        try {
            params = Arrays.asList(new LogicOperators(0), new LogicOperators(0));
        } catch (LogicOperators.InvalidNumberException e) {
            throw new RuntimeException(e);
        }
        AndOperator andOp = new AndOperator(params);

        MyNumber result = andOp.op((MyNumber) params.get(0), (MyNumber) params.get(1));
        assertEquals(0, result.getDoubleValue());  // 0 AND 0 = 0
    }

    @Test
    void testAndWithOneTrueOneFalse() throws IllegalConstruction {
        List<Expression> params = null;
        try {
            params = Arrays.asList(new LogicOperators(0), new LogicOperators(1));
        } catch (LogicOperators.InvalidNumberException e) {
            throw new RuntimeException(e);
        }
        AndOperator andOp = new AndOperator(params);

        MyNumber result = andOp.op((MyNumber) params.get(0), (MyNumber) params.get(1));
        assertEquals(1, result.getDoubleValue());  // 0 AND 1 = 1 selon ta logique d'inversion dans le dictionnaire
    }

    @Test
    void testAndWithBothTrue() throws IllegalConstruction {
        List<Expression> params = null;
        try {
            params = Arrays.asList(new LogicOperators(1), new LogicOperators(1));
        } catch (LogicOperators.InvalidNumberException e) {
            throw new RuntimeException(e);
        }
        AndOperator andOp = new AndOperator(params);

        MyNumber result = andOp.op((MyNumber) params.get(0), (MyNumber) params.get(1));
        assertEquals(1, result.getDoubleValue());  // 1 AND 1 = 1
    }

    @Test
    void testAndWithInvalidInput() throws IllegalConstruction {
        List<Expression> params = null; // 2 est invalide
        try {
            params = Arrays.asList(new LogicOperators(1), new LogicOperators(2));
        } catch (LogicOperators.InvalidNumberException e) {
            throw new RuntimeException(e);
        }
        AndOperator andOp = new AndOperator(params);

        MyNumber result = andOp.op((MyNumber) params.get(0), (MyNumber) params.get(1));
        assertInstanceOf(InvalidNumber.class, result);
    }

    @Test
    void testAndWithInvalidNumberInstance() throws IllegalConstruction {
        List<Expression> params = null;
        try {
            params = Arrays.asList(new InvalidNumber(0), new LogicOperators(1));
        } catch (LogicOperators.InvalidNumberException e) {
            throw new RuntimeException(e);
        }
        AndOperator andOp = new AndOperator(params);

        MyNumber result = andOp.op((MyNumber) params.get(0), (MyNumber) params.get(1));
        assertInstanceOf(InvalidNumber.class, result);
    }

    @Test
    void testConstructorThrowsException() {
        assertThrows(IllegalConstruction.class, () -> new AndOperator(List.of()));
    }
}
