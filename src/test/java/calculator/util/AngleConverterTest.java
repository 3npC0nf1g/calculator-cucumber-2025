
package calculator.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

 class AngleConverterTest {
//
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


     @Test
     void testNegativeDegreesToRadians() {
         assertEquals(-Math.PI, AngleConverter.degreesToRadians(-180), 1e-10);
         assertEquals(-Math.PI / 2, AngleConverter.degreesToRadians(-90), 1e-10);
     }

     @Test
     void testNegativeRadiansToDegrees() {
         assertEquals(-180, AngleConverter.radiansToDegrees(-Math.PI), 1e-10);
         assertEquals(-90, AngleConverter.radiansToDegrees(-Math.PI / 2), 1e-10);
     }

     @Test
     void testLargeValues() {
         assertEquals(Math.toRadians(Double.MAX_VALUE), AngleConverter.degreesToRadians(Double.MAX_VALUE), 1e-10);
         assertEquals(Math.toDegrees(Double.MAX_VALUE), AngleConverter.radiansToDegrees(Double.MAX_VALUE), 1e-10);
     }

     @Test
     void testUtilityClassCannotBeInstantiated() throws Exception {
         var constructor = AngleConverter.class.getDeclaredConstructor();
         constructor.setAccessible(true);

         InvocationTargetException thrown = assertThrows(
                 InvocationTargetException.class,
                 constructor::newInstance
         );

         // Vérifie que la "cause" de l'InvocationTargetException est bien une UnsupportedOperationException
         assertInstanceOf(UnsupportedOperationException.class, thrown.getCause());
         assertEquals("AngleConverter is a utility class and cannot be instantiated.", thrown.getCause().getMessage());
     }

 }
