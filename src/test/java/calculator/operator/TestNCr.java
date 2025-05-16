package calculator.operator;

import calculator.IllegalConstruction;
import calculator.NCr;
import calculator.MyNumber;
import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestNCr {

    private NCr createNCr() throws IllegalConstruction {
        // On fournit deux expressions dummy (MyNumber) pour satisfaire la taille de la liste
        return new NCr(List.of(
                new MyNumber(new IntegerValue(0)),
                new MyNumber(new IntegerValue(0))
        ));
    }

    @Test
    void testNCr_SmallValues() throws IllegalConstruction {
        NCr nCr = createNCr();
        NumericValue result = nCr.op(new IntegerValue(5), new IntegerValue(3));
        assertEquals(new IntegerValue(10), result);
    }

    @Test
    void testNCr_rZero() throws IllegalConstruction {
        NCr nCr = createNCr();
        NumericValue result = nCr.op(new IntegerValue(7), new IntegerValue(0));
        assertEquals(new IntegerValue(1), result);
    }

    @Test
    void testNCr_nEqualsR() throws IllegalConstruction {
        NCr nCr = createNCr();
        NumericValue result = nCr.op(new IntegerValue(4), new IntegerValue(4));
        assertEquals(new IntegerValue(1), result);
    }

    @Test
    void testNCr_kGreaterThanN_Throws() throws IllegalConstruction {
        NCr nCr = createNCr();
        assertThrows(ArithmeticException.class, () ->
                nCr.op(new IntegerValue(3), new IntegerValue(5))
        );
    }

    @Test
    void testNCr_negativeN_Throws() throws IllegalConstruction {
        NCr nCr = createNCr();
        assertThrows(ArithmeticException.class, () ->
                nCr.op(new IntegerValue(-1), new IntegerValue(2))
        );
    }

    @Test
    void testNCr_negativeR_Throws() throws IllegalConstruction {
        NCr nCr = createNCr();
        assertThrows(ArithmeticException.class, () ->
                nCr.op(new IntegerValue(5), new IntegerValue(-3))
        );
    }
}
