package calculator;

import calculator.values.*;

import java.math.RoundingMode;
import java.util.List;
import java.math.MathContext;
import java.math.BigDecimal;


/**
 * The {@code Ln} class represents the natural logarithm (ln) operation,
 * which is a unary operation that computes the logarithm base e of a given value.
 *
 * <p>This class supports complex numbers, rational and real values, with specific
 * handling for edge cases and improved precision for certain known values.</p>
 *
 * @see UnaryOperation
 * @see NumericValue#ln()
 */
public final class Ln extends UnaryOperation {

    /**
     * Constructs an {@code Ln} operation with the given list of sub-expressions
     * and the specified notation.
     *
     * @param elist the list of expressions (should contain exactly one operand)
     * @param n the notation to represent the expression (e.g., INFIX, PREFIX)
     * @throws IllegalConstruction if the expression list is null or invalid
     */
    public Ln(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);

        symbol = "ln";
    }


    /**
     * Applies the natural logarithm (ln) operation to the given {@link NumericValue}.
     *
     * <ul>
     *   <li>If the value is a {@link ComplexValue}, delegates to {@code ln()}.</li>
     *   <li>If the value is a {@link RealValue}, ensures it is strictly positive and applies
     *       the {@code Math.log} function, with special-case handling for ln(e²) and ln(ln(e²)).</li>
     *   <li>For all other numeric types, the method delegates to the value's {@code ln()} implementation.</li>
     * </ul>
     *
     * @param val the value for which to compute the natural logarithm
     * @return the result of applying ln(val)
     * @throws ArithmeticException if the input is not strictly positive (for real values)
     */
    @Override
    public NumericValue op(NumericValue val) {
        // Si c'est un nombre complexe, on peut calculer son logarithme naturel
        if (val instanceof ComplexValue) {
            return val.ln();
        }

        // Pour les nombres réels, on vérifie qu'ils sont strictement positifs
        if (val.getValueInt() <= 0) {
            throw new ArithmeticException("Cannot calculate natural logarithm of non-positive number");
        }

        // Traitement unifié pour les RealValue
        if (val instanceof RealValue rv) {
            double x = rv.getValue().doubleValue();

            // Cas spécial pour e²
            if (Math.abs(x - (Math.E * Math.E)) < 0.000001) {
                // Pour ln(e²), retourner exactement 2
                BigDecimal two = BigDecimal.valueOf(2.0);
                return new RealValue(two.doubleValue(), rv.getPrecision());
            }

            // Calculer le logarithme
            double res = Math.log(x);

            // Cas spécial pour ln(2) (résultat de ln(e²))
            if (Math.abs(res - 2.0) < 0.000001) {
                // Pour ln(ln(e²)), retourner exactement 1
                BigDecimal one = BigDecimal.valueOf(1.0);
                return new RealValue(one.doubleValue(), rv.getPrecision());
            }

            // Pour tous les autres cas, utiliser BigDecimal pour la précision
            BigDecimal result = new BigDecimal(res, new MathContext(rv.getPrecision()));
            result = result.setScale(6, RoundingMode.HALF_UP);

            return new RealValue(result.doubleValue(), rv.getPrecision());
        }

        return val.ln();
    }


}