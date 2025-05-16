package calculator.conversion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitConverterTest {

    private static final double DELTA = 1e-6;

    @Test
    public void testLengthConversions() {
        assertEquals(100.0, UnitConverter.convert(1.0, "m", "cm", "length"), DELTA);
        assertEquals(1.0, UnitConverter.convert(100.0, "cm", "m", "length"), DELTA);
        assertEquals(1000.0, UnitConverter.convert(1.0, "m", "mm", "length"), DELTA);
        assertEquals(0.001, UnitConverter.convert(1.0, "mm", "m", "length"), DELTA);
        assertEquals(3.28084, UnitConverter.convert(1.0, "m", "ft", "length"), DELTA);
        assertEquals(1.0, UnitConverter.convert(3.28084, "ft", "m", "length"), 1e-5);
    }

    @Test
    public void testWeightConversions() {
        assertEquals(1000.0, UnitConverter.convert(1.0, "kg", "g", "weight"), DELTA);
        assertEquals(1.0, UnitConverter.convert(1000.0, "g", "kg", "weight"), DELTA);
        assertEquals(1_000_000.0, UnitConverter.convert(1.0, "kg", "mg", "weight"), DELTA);
        assertEquals(0.453592, UnitConverter.convert(1.0, "lb", "kg", "weight"), 1e-5);
        assertEquals(2.20462, UnitConverter.convert(1.0, "kg", "lb", "weight"), 1e-5);
    }

    @Test
    public void testTimeConversions() {
        assertEquals(60.0, UnitConverter.convert(1.0, "h", "min", "time"), DELTA);
        assertEquals(3600.0, UnitConverter.convert(1.0, "h", "s", "time"), DELTA);
        assertEquals(1.0, UnitConverter.convert(60.0, "min", "h", "time"), DELTA);
        assertEquals(0.0166667, UnitConverter.convert(1.0, "s", "min", "time"), 1e-4);
    }

    @Test
    public void testDataConversions() {
        assertEquals(1.0, UnitConverter.convert(8.0, "bit", "byte", "data"), DELTA);
        assertEquals(0.001, UnitConverter.convert(1.0, "kb", "bit", "data"), DELTA);
        assertEquals(1.25E-7, UnitConverter.convert(1.0, "mb", "byte", "data"), DELTA);
        assertEquals(1.1920928955078125E-7, UnitConverter.convert(1.0, "mib", "byte", "data"), DELTA);
    }

    @Test
    public void testDensityConversions() {
        assertEquals(1000.0, UnitConverter.convert(1.0, "g/cm3", "kg/m3", "density"), DELTA);
        assertEquals(1.0, UnitConverter.convert(1000.0, "kg/m3", "g/cm3", "density"), DELTA);
    }

    @Test
    public void testEnergyConversions() {
        assertEquals(4.184, UnitConverter.convert(1.0, "cal", "joule", "energy"), 1e-3);
        assertEquals(1.0, UnitConverter.convert(4.184, "joule", "cal", "energy"), 1e-2);
        assertEquals(3.6e6, UnitConverter.convert(1.0, "kWh", "joule", "energy"), 1e3);
    }

    @Test
    public void testForceConversions() {
        assertEquals(9.80665, UnitConverter.convert(1.0, "kgf", "N", "force"), 0.1);
        assertEquals(1.0, UnitConverter.convert(9.80665, "N", "kgf", "force"), 0.05);
        assertEquals(0.00001, UnitConverter.convert(1.0, "dyne", "N", "force"), 1e-8);
        assertEquals(100000.0, UnitConverter.convert(1.0, "N", "dyne", "force"), DELTA);
    }

    @Test
    public void testSpeedConversions() {
        assertEquals(3.6, UnitConverter.convert(1.0, "m/s", "km/h", "speed"), DELTA);
        assertEquals(1.0, UnitConverter.convert(3.6, "km/h", "m/s", "speed"), DELTA);
        assertEquals(2.23694, UnitConverter.convert(1.0, "m/s", "mph", "speed"), 1e-5);
        assertEquals(1.94384, UnitConverter.convert(1.0, "m/s", "knot", "speed"), 1e-5);
    }

    @Test
    public void testPowerConversions() {
        assertEquals(1.0, UnitConverter.convert(1000.0, "watt", "kw", "power"), DELTA);
        assertEquals(745.7, UnitConverter.convert(1.0, "hp", "watt", "power"), 1.0);
        assertEquals(1.34102, UnitConverter.convert(1.0, "kw", "hp", "power"), 1e-5);
    }

    @Test
    public void testPressureConversions() {
        assertEquals(1.0, UnitConverter.convert(101325.0, "pa", "atm", "pressure"), 0.01);
        assertEquals(101325.0, UnitConverter.convert(1.0, "atm", "pa", "pressure"), 100.0);
        assertEquals(760.0028371093908, UnitConverter.convert(1.0, "atm", "mmHg", "pressure"), 0.5);
        assertEquals(0.98692, UnitConverter.convert(750.062, "mmHg", "atm", "pressure"), 0.02);
    }

    @Test
    public void testNegativeAndZeroValues() {
        assertEquals(0.0, UnitConverter.convert(0.0, "m", "cm", "length"), DELTA);
        assertEquals(-100.0, UnitConverter.convert(-1.0, "m", "cm", "length"), DELTA);
    }

    @Test
    public void testExceptions() {
        assertThrows(IllegalArgumentException.class, () ->
                UnitConverter.convert(1.0, "invalid", "m", "length"));
        assertThrows(IllegalArgumentException.class, () ->
                UnitConverter.convert(1.0, "m", "invalid", "length"));
        assertThrows(IllegalArgumentException.class, () ->
                UnitConverter.getUnitsByCategory("nonexistent"));
    }
}


