package calculator;
import calculator.values.ComplexValue;
import calculator.values.NumericValue;
import calculator.values.IntegerValue;

import java.util.List;

public final class Log extends Operation {
    public Log(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "log";
        neutral = 1;
    }

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