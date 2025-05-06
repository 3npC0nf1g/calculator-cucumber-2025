package calculator.conversion;

public class Units {
    String name;
    String category;
    double factorToBase;

    public Units(String name, String category, double factorToBase) {
        this.name = name;
        this.category = category;
        this.factorToBase = factorToBase;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getFactorToBase() {
        return factorToBase;
    }
}
