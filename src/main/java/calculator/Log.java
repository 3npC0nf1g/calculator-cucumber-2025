package calculator;
import calculator.values.ComplexValue;
import calculator.values.NumericValue;

import java.util.List;

/**
 * The {@code Log} class represents a binary logarithmic operation with a specified base,
 * i.e., {@code log_base(x)}.
 *
 * <p>It supports both real and complex numbers and enforces domain constraints
 * such as positive bases (not equal to 1) and positive arguments for real values.
 * For complex bases, a modulus check is included to avoid undefined behavior.</p>
 *
 * @see Operation
 * @see NumericValue#log(NumericValue)
 */
public final class Log extends Operation {

    /**
     * Constructs a {@code Log} operation with the given list of expressions and notation.
     *
     * @param elist the list of two expressions: base and argument
     * @param n the notation used to represent the operation (e.g., INFIX, PREFIX)
     * @throws IllegalConstruction if the expression list is null or malformed
     */
    public Log(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "log";
        neutral = 1;
    }

    /**
     * Applies the logarithm operation {@code log_base(x)}.
     *
     * <p>For real values, the base must be positive and not equal to 1,
     * and the argument must be positive. For complex bases, the method
     * checks that the modulus is not 1 to avoid mathematical indeterminacies.</p>
     *
     * @param base the base of the logarithm
     * @param x the argument for which to compute the logarithm
     * @return the result of {@code log_base(x)}
     * @throws ArithmeticException if the base is invalid or the argument is non-positive (real case)
     */
    @Override
    public NumericValue op(NumericValue base, NumericValue x) {
        // Vérification pour les nombres complexes
        if (base instanceof ComplexValue complexBase) {
            // Pour un nombre complexe comme base, on calcule d'abord son module
            double mod = complexBase.getReal().pow(2)
                    .add(complexBase.getImag().pow(2))
                    .sqrt(java.math.MathContext.DECIMAL128)
                    .doubleValue();

            if (Math.abs(mod - 1.0) < 1e-10) {
                throw new ArithmeticException("Complex logarithm base cannot have modulus 1");
            }
        } else {
            // Pour les nombres réels, on garde la vérification standard
            if (base.getValueInt() <= 0 || base.getValueInt() == 1) {
                throw new ArithmeticException("Logarithm base must be positive and not equal to 1");
            }
        }

        // Vérification de l'argument
        if (!(x instanceof ComplexValue) && x.getValueInt() <= 0) {
            throw new ArithmeticException("Cannot calculate logarithm of non-positive number");
        }

        return x.log(base);
    }
}