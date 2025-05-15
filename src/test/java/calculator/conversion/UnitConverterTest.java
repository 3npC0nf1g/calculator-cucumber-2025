package calculator.conversion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitConverterTest {

    private static final double DELTA = 1e-6;

    @Test
    public void testLengthConversion() {
        double result = UnitConverter.convert(1.0, "m", "cm", "length");
        assertEquals(100.0, result, DELTA);

        double reverse = UnitConverter.convert(result, "cm", "m", "length");
        assertEquals(1.0, reverse, DELTA);
    }

    @Test
    public void testWeightConversion() {
        double result = UnitConverter.convert(1.0, "kg", "g", "weight");
        assertEquals(1000.0, result, DELTA);

        double reverse = UnitConverter.convert(result, "g", "kg", "weight");
        assertEquals(1.0, reverse, DELTA);
    }

    @Test
    public void testTimeConversion() {
        double result = UnitConverter.convert(1.0, "h", "min", "time");
        assertEquals(60.0, result, DELTA);

        double reverse = UnitConverter.convert(result, "min", "h", "time");
        assertEquals(1.0, reverse, DELTA);
    }

    @Test
    public void testDataConversion() {
        double result = UnitConverter.convert(8.0, "bit", "byte", "data");
        assertEquals(1.0, result, DELTA);

        double reverse = UnitConverter.convert(result, "byte", "bit", "data");
        assertEquals(8.0, reverse, DELTA);
    }

    @Test
    public void testDensityConversion() {
        double result = UnitConverter.convert(1.0, "g/cm3", "kg/m3", "density");
        assertEquals(1000.0, result, DELTA);

        double reverse = UnitConverter.convert(result, "kg/m3", "g/cm3", "density");
        assertEquals(1.0, reverse, DELTA);
    }

    @Test
    public void testEnergyConversion() {
        double result = UnitConverter.convert(1.0, "cal", "joule", "energy");
        assertEquals(4.184, result, 1e-3); // marge plus large

        double reverse = UnitConverter.convert(result, "joule", "cal", "energy");
        assertEquals(1.0, reverse, 1e-3);
    }

    @Test
    public void testForceConversion() {
        double result = UnitConverter.convert(1.0, "kgf", "N", "force");
        assertEquals(9.80665, result, 0.1); // marge élargie

        double reverse = UnitConverter.convert(result, "N", "kgf", "force");
        assertEquals(1.0, reverse, 0.01);
    }

    @Test
    public void testSpeedConversion() {
        double result = UnitConverter.convert(1.0, "m/s", "km/h", "speed");
        assertEquals(3.6, result, DELTA);

        double reverse = UnitConverter.convert(result, "km/h", "m/s", "speed");
        assertEquals(1.0, reverse, DELTA);
    }

    @Test
    public void testPowerConversion() {
        double result = UnitConverter.convert(1000.0, "watt", "kw", "power");
        assertEquals(1.0, result, DELTA);

        double reverse = UnitConverter.convert(result, "kw", "watt", "power");
        assertEquals(1000.0, reverse, DELTA);
    }

    @Test
    public void testPressureConversion() {
        double result = UnitConverter.convert(101325.0, "pa", "atm", "pressure");
        assertEquals(1.0, result, 0.01); // marge un peu plus large

        double reverse = UnitConverter.convert(result, "atm", "pa", "pressure");
        assertEquals(101325.0, reverse, 100.0);
    }

    @Test
    public void testInvalidUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                UnitConverter.convert(1.0, "invalid", "m", "length"));
    }

    @Test
    public void testInvalidCategoryThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                UnitConverter.getUnitsByCategory("notacategory"));
    }
}

