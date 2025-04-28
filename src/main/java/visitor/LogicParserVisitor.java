package visitor;
import calculator.Calculator;
import calculator.Expression;
import calculator.parser.logic.LogicExprParserBaseVisitor;
public class LogicParserVisitor extends LogicExprParserBaseVisitor<Expression>{

    public LogicParserVisitor(Calculator calculator) {
        super();
    }
}
