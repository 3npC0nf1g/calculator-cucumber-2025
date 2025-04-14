
package calculator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class AngleConverterTest {

    @Test
     void testDegreesToRadians() {
        assertEquals(Math.PI, AngleConverter.degreesToRadians(180), 1e-10);
        assertEquals(Math.PI / 2, AngleConverter.degreesToRadians(90), 1e-10);
        assertEquals(0, AngleConverter.degreesToRadians(0), 1e-10);
    }

    @Test
     void testRadiansToDegrees() {
        assertEquals(180, AngleConverter.radiansToDegrees(Math.PI), 1e-10);
        assertEquals(90, AngleConverter.radiansToDegrees(Math.PI / 2), 1e-10);
        assertEquals(0, AngleConverter.radiansToDegrees(0), 1e-10);
    }

    @Test
     void testRoundTripConversion() {
        double degrees = 123.456;
        double radians = AngleConverter.degreesToRadians(degrees);
        double result = AngleConverter.radiansToDegrees(radians);
        assertEquals(degrees, result, 1e-10);
    }
}
