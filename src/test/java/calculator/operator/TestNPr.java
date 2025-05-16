package calculator.operator;

import calculator.IllegalConstruction;
import calculator.NPr;
import calculator.MyNumber;
import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestNPr {

    /**
     * Fournit toujours deux MyNumber d’argument pour passer le constructeur.
     */
    private NPr createNPr() throws IllegalConstruction {
        return new NPr(List.of(
                new MyNumber(new IntegerValue(0)),
                new MyNumber(new IntegerValue(0))
        ));
    }

    @Test
    void testNPr_SmallValues() throws IllegalConstruction {
        NPr nPr = createNPr();
        NumericValue result = nPr.op(new IntegerValue(5), new IntegerValue(3));
        // 5P3 = 5 × 4 × 3 = 60
        assertEquals(new IntegerValue(60), result);
    }

    @Test
    void testNPr_rZero() throws IllegalConstruction {
        NPr nPr = createNPr();
        NumericValue result = nPr.op(new IntegerValue(7), new IntegerValue(0));
        // nP0 est toujours 1
        assertEquals(new IntegerValue(1), result);
    }

    @Test
    void testNPr_nEqualsR() throws IllegalConstruction {
        NPr nPr = createNPr();
        NumericValue result = nPr.op(new IntegerValue(4), new IntegerValue(4));
        // 4P4 = 4 × 3 × 2 × 1 = 24
        assertEquals(new IntegerValue(24), result);
    }

    @Test
    void testNPr_kGreaterThanN_Throws() throws IllegalConstruction {
        NPr nPr = createNPr();
        assertThrows(ArithmeticException.class, () ->
                nPr.op(new IntegerValue(3), new IntegerValue(5))
        );
    }

    @Test
    void testNPr_negativeN_Throws() throws IllegalConstruction {
        NPr nPr = createNPr();
        assertThrows(ArithmeticException.class, () ->
                nPr.op(new IntegerValue(-1), new IntegerValue(2))
        );
    }

    @Test
    void testNPr_negativeR_Throws() throws IllegalConstruction {
        NPr nPr = createNPr();
        assertThrows(ArithmeticException.class, () ->
                nPr.op(new IntegerValue(5), new IntegerValue(-3))
        );
    }
}
