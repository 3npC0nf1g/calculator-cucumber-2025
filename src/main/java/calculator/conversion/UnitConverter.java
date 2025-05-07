package calculator.conversion;

import java.util.ArrayList;
import java.util.List;

public class UnitConverter {

    private static final List<Units> lengthUnits = new ArrayList<>();
    private static final List<Units> weightUnits = new ArrayList<>();
    private static final List<Units> timeUnits = new ArrayList<>();
    private static final List<Units> dataUnits = new ArrayList<>();
    private static final List<Units> densityUnits = new ArrayList<>();
    private static final List<Units> energyUnits = new ArrayList<>();
    private static final List<Units> forceUnits = new ArrayList<>();
    private static final List<Units> speedUnits = new ArrayList<>();
    private static final List<Units> powerUnits = new ArrayList<>();
    private static final List<Units> pressureUnits = new ArrayList<>();

    static {
        // Length
        lengthUnits.add(new Units("m", "length", 1.0));
        lengthUnits.add(new Units("dm", "length", 10.0));
        lengthUnits.add(new Units("cm", "length", 100.0));
        lengthUnits.add(new Units("mm", "length", 1000.0));
        lengthUnits.add(new Units("km", "length", 0.001));
        lengthUnits.add(new Units("hm", "length", 0.01));
        lengthUnits.add(new Units("dam", "length", 0.1));
        lengthUnits.add(new Units("ft", "length", 3.28084));
        lengthUnits.add(new Units("inch", "length", 39.3701));

        // Weight
        weightUnits.add(new Units("mg", "weight", 1000.0));
        weightUnits.add(new Units("cg", "weight", 100.0));
        weightUnits.add(new Units("dg", "weight", 10.0));
        weightUnits.add(new Units("g", "weight", 1.0));
        weightUnits.add(new Units("dag", "weight", 0.1));
        weightUnits.add(new Units("hg", "weight", 0.01));
        weightUnits.add(new Units("kg", "weight", 0.001));
        weightUnits.add(new Units("lb", "weight", 0.00220462));

        // Time
        timeUnits.add(new Units("s", "time", 1.0));
        timeUnits.add(new Units("min", "time", 1.0 / 60.0));
        timeUnits.add(new Units("h", "time", 1.0 / 3600.0));

        // Data
        dataUnits.add(new Units("bit", "data", 1.0));
        dataUnits.add(new Units("byte", "data", 1.0 / 8.0));
        dataUnits.add(new Units("kb", "data", 1_000.0));
        dataUnits.add(new Units("kib", "data", 1024.0));
        dataUnits.add(new Units("mb", "data", 1_000_000.0));
        dataUnits.add(new Units("mib", "data", 1_048_576.0));

        // Density
        densityUnits.add(new Units("kg/m3", "density", 1.0));
        densityUnits.add(new Units("g/cm3", "density", 0.001));

        // Energy
        energyUnits.add(new Units("joule", "energy", 1.0));
        energyUnits.add(new Units("cal", "energy", 0.239006));
        energyUnits.add(new Units("kWh", "energy", 2.7778e-7));

        // Force
        forceUnits.add(new Units("N", "force", 1.0));
        forceUnits.add(new Units("dyne", "force", 100000.0));
        forceUnits.add(new Units("kgf", "force", 0.101972));

        // Speed
        speedUnits.add(new Units("m/s", "speed", 1.0));
        speedUnits.add(new Units("km/h", "speed", 3.6));
        speedUnits.add(new Units("mph", "speed", 2.23694));
        speedUnits.add(new Units("knot", "speed", 1.94384));

        // Power
        powerUnits.add(new Units("watt", "power", 1.0));
        powerUnits.add(new Units("kw", "power", 0.001));
        powerUnits.add(new Units("hp", "power", 0.00134102));

        // Pressure
        pressureUnits.add(new Units("pa", "pressure", 1.0));
        pressureUnits.add(new Units("bar", "pressure", 1e-5));
        pressureUnits.add(new Units("atm", "pressure", 9.8692e-6));
        pressureUnits.add(new Units("mmHg", "pressure", 0.00750062));
    }

    public static double convert(double value, String from, String to, String category) {
        List<Units> units = getUnitsByCategory(category);

        Units fromUnit = null, toUnit = null;
        for (Units u : units) {
            if (u.getName().equalsIgnoreCase(from)) fromUnit = u;
            if (u.getName().equalsIgnoreCase(to)) toUnit = u;
        }

        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Invalid units in category: " + category);
        }

        return (value / fromUnit.getFactorToBase()) * toUnit.getFactorToBase();
    }

    public static List<Units> getUnitsByCategory(String category) {
        return switch (category.toLowerCase()) {
            case "length" -> lengthUnits;
            case "weight" -> weightUnits;
            case "time" -> timeUnits;
            case "data" -> dataUnits;
            case "density" -> densityUnits;
            case "energy" -> energyUnits;
            case "force" -> forceUnits;
            case "speed" -> speedUnits;
            case "power" -> powerUnits;
            case "pressure" -> pressureUnits;
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        };
    }
}
