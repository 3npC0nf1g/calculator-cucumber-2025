package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.UnaryOperation;
import calculator.values.NumericValue;

import java.util.ArrayList;

/**
 * Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

    /**
     * Default constructor of the class. Does not initialize anything.
     */
    public Evaluator() {}

    /**
     * The result of the evaluation will be stored in this private variable.
     */
    private NumericValue computedValue;

    /**
     * Getter method to obtain the result of the evaluation.
     *
     * @return a NumericValue object containing the result of the evaluation.
     */
    public NumericValue getResult() {
        return computedValue;
    }

    /**
     * Use the visitor design pattern to visit a number.
     *
     * @param n The number being visited.
     */
    @Override
    public void visit(MyNumber n) {
        // On récupère directement la valeur contenue dans MyNumber (de type NumericValue)
        computedValue = n.getValue();
    }

    /**
     * Use the visitor design pattern to visit an operation.
     *
     * @param o The operation being visited.
     */
    @Override
    public void visit(Operation o) {
        ArrayList<NumericValue> evaluatedArgs = new ArrayList<>();
        // 1) Evaluate each sub‐expression
        for (Expression a : o.getArgs()) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }

        // 2) If it's a unary operation, invoke its single‐arg op(val)
        if (o instanceof UnaryOperation && evaluatedArgs.size() == 1) {
            computedValue = ((UnaryOperation) o).op(evaluatedArgs.get(0));
            return;
        }

        // 3) Otherwise fold all args through the binary op(l,r)
        NumericValue temp = evaluatedArgs.get(0);
        for (int i = 1; i < evaluatedArgs.size(); i++) {
            temp = o.op(temp, evaluatedArgs.get(i));
        }
        computedValue = temp;
    }

}
