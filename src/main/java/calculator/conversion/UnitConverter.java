package calculator.conversion;

import java.util.ArrayList;
import java.util.List;

public class UnitConverter {


    private static final List<Units> unitList = new ArrayList<>();

    static {
        // Length units (base: meter)
        unitList.add(new Units("m", "length", 1.0));
        unitList.add(new Units("dm", "length", 10.0));
        unitList.add(new Units("cm", "length", 100.0));
        unitList.add(new Units("mm", "length", 1000.0));
        unitList.add(new Units("km", "length", 0.001));
        unitList.add(new Units("hm", "length", 0.01));
        unitList.add(new Units("dam", "length", 0.1));
        unitList.add(new Units("ft", "length", 3.28084));
        unitList.add(new Units("inch", "length", 39.3701));

        // Weight units (base: kilogram)
        unitList.add(new Units("mg", "weight", 1000.0));
        unitList.add(new Units("cg", "weight", 100.0));
        unitList.add(new Units("dg", "weight", 10.0));
        unitList.add(new Units("g", "weight", 1.0));
        unitList.add(new Units("dag", "weight", 0.1));
        unitList.add(new Units("hg", "weight", 0.01));
        unitList.add(new Units("kg", "weight", 0.001));


        // Time units (base: second)
        unitList.add(new Units("s", "time", 1.0));
        unitList.add(new Units("min", "time", 1.0 / 60.0));
        unitList.add(new Units("h", "time", 1.0 / 3600.0));
    }

    public static double convert(double value, String from, String to, String category) {
        Units fromUnit = null, toUnit = null;

        for (Units u : unitList) {
            if (u.getCategory().equalsIgnoreCase(category)) {
                if (u.getName().equalsIgnoreCase(from)) fromUnit = u;
                if (u.getName().equalsIgnoreCase(to)) toUnit = u;
            }
        }

        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Invalid units in category: " + category);
        }

        return (value / fromUnit.getFactorToBase()) * toUnit.getFactorToBase();
    }

    // Demo main method
    public static void main(String[] args) {
        System.out.println("10 meters = " + convert(10, "m", "km", "length") + " feet");
        System.out.println("2 kilograms = " + convert(2, "kg", "lb", "weight") + " pounds");
        System.out.println("120 seconds = " + convert(120, "s", "min", "time") + " minutes");
    }
}

