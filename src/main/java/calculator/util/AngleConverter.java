package calculator.util;

public class AngleConverter {

    private AngleConverter() {
        throw new UnsupportedOperationException("AngleConverter is a utility class and cannot be instantiated.");
    }
    
    /**
     * Converts an angle in degrees to radians.
     *
     * @param degrees the angle in degrees
     * @return the angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    /**
     * Converts an angle in radians to degrees.
     *
     * @param radians the angle in radians
     * @return the angle in degrees
     */
    public static double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }
}
