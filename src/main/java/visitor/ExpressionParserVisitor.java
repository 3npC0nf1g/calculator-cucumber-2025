package visitor;

import calculator.Calculator;
import calculator.Expression;
import calculator.parser.expression.ExpressionParserBaseVisitor;

public class ExpressionParserVisitor extends ExpressionParserBaseVisitor<Expression> {
    public ExpressionParserVisitor(Calculator calculator) {
        super();
    }
}
