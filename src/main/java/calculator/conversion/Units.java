package calculator.conversion;

/**
 * Represents a unit of measurement within a specific category.
 * Each unit has a name, a category (e.g., "length", "weight"), and a conversion factor
 * to a base unit within that category.
 */
public class Units {
    /** The name or abbreviation of the unit (e.g., "m", "kg", "s"). */
    String name;

    /** The category this unit belongs to (e.g., "length", "weight"). */
    String category;

    /** The factor used to convert this unit to the base unit of its category. */
    double factorToBase;

    /**
     * Constructs a new unit with the given name, category, and conversion factor.
     *
     * @param name          the unit's name or abbreviation
     * @param category      the category of the unit
     * @param factorToBase  the conversion factor to the base unit (e.g., 100.0 for "cm" to "m")
     */
    public Units(String name, String category, double factorToBase) {
        this.name = name;
        this.category = category;
        this.factorToBase = factorToBase;
    }


    /**
     * Returns the name or abbreviation of this unit.
     *
     * @return the unit name
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the category this unit belongs to.
     *
     * @return the unit category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the conversion factor to the base unit of the category.
     *
     * @return the conversion factor to the base unit
     */
    public double getFactorToBase() {
        return factorToBase;
    }
}