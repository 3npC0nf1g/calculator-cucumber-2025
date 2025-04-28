package visitor;

import calculator.Calculator;
import calculator.Expression;
import calculator.parser.complex.ComplexExprBaseVisitor;

public class ComplexParserVisitor extends ComplexExprBaseVisitor<Expression> {
    public ComplexParserVisitor(Calculator calculator) {
        super();
    }
}
