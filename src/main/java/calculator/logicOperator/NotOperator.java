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
import java.util.logging.Logger;

public class NotOperator extends Operation {

    private final Map<String, Boolean> dictionary = new HashMap<>();

    /**
     * Class constructor specifying a number of Expressions to apply the NOT operation on,
     * as well as the notation used to represent the operation.
     *
     * @param expressionList The list of Expressions to divide
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter

     */
    public NotOperator(List<Expression> expressionList) throws IllegalConstruction {
        super(expressionList);
        dictionary.put("0", Boolean.TRUE);
        dictionary.put("1", Boolean.FALSE);
        symbol = "NOT";
        neutral = 0;
    }

    @Override
    public NumericValue op(NumericValue l, NumericValue r) {
        return null;
    }

    /**
     * The actual computation of the (unary) boolean NOT operation.
     * If the number is a InvalidNumber instance, the method returns a new InvalidNumber instance.
     * If the number is different from 0 or 1, the method throws and catch an InvalidNumberException and returns a new
     * InvalidNumber instance.
     *
     * @param l The number.
     * @return The result of the NOT operation, or a new InvalidNumber if the input number is a InvalidNumber or the
     * number is greater than 1.
     */
    @Override
    public MyNumber op(MyNumber l){
        if (l instanceof InvalidNumber)
            return new InvalidNumber(0);
        try {
            if (l.getNumber() > 1){
                throw new LogicOperators.InvalidNumberException("Invalid number entered. Number should be either 0 or 1.");
            }
            return new LogicOperators(dictionary.get(l.toString()) == Boolean.TRUE ? 1 : 0);
        }
        catch (LogicOperators.InvalidNumberException exception){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info(exception.getMessage());
            return new InvalidNumber(0);
        }
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("Not is an unary operator, keeping only the first number");
        return op(l);
    }
}
