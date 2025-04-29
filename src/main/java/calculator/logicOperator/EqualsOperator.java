package calculator.logicOperator;


import calculator.IllegalConstruction;
import calculator.Operation;
import calculator.Expression;
import calculator.LogicOperators;
import calculator.InvalidNumber;
import calculator.MyNumber;
import calculator.values.NumericValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class EqualsOperator extends Operation {

    private final Map<String, Boolean> dictionary = new HashMap<>();

    /**
     * Class constructor specifying a number of Expressions to apply the EQ operation on,
     * as well as the notation used to represent the operation.
     *
     * @param expressionList The list of Expressions
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter

     */
    public EqualsOperator(List<Expression> expressionList) throws IllegalConstruction {
        super(expressionList);
        dictionary.put("0", Boolean.TRUE);
        dictionary.put("1", Boolean.FALSE);
        symbol = "EQ";
        neutral = 0;
    }

    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        return null;
    }

    @Override
    public MyNumber op(MyNumber l) {
        return null;
    }


    /**
     * The actual computation of the (binary) boolean EQUALS operation of two boolean.
     * If either of the numbers is a InvalidNumber instance, the method returns a new InvalidNumber instance.
     * If either of the numbers is different from 0 or 1, the method throws and catch an InvalidNumberException and returns a new
     * InvalidNumber instance.
     *
     * @param l The first number.
     * @param r The second number.
     * @return The result of the EQUALS operation, or a new InvalidNumber if either input number is a InvalidNumber or
     * either number is greater than 1.
     */
    @Override
    public MyNumber op(MyNumber l, MyNumber r) {
        if (l instanceof InvalidNumber || r instanceof InvalidNumber)
            return new InvalidNumber(0);
        try {
            if (l.getNumber() > 1 || r.getNumber() > 1){
                throw new LogicOperators.InvalidNumberException("Invalid number entered. Number should be either 0 or 1.");
            }
            boolean value = Objects.equals(dictionary.get(l.toString()), dictionary.get(r.toString()));
            return new LogicOperators(value ? 0 : 1);
        }
        catch (LogicOperators.InvalidNumberException exception){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info(exception.getMessage());
            return new InvalidNumber(0);
        }
    }
}
