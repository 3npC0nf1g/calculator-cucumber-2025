package visitor;

import calculator.Calculator;
import calculator.Expression;
import calculator.parser.rational.RationalCoreBaseVisitor;

public class RationalParserVisitor extends RationalCoreBaseVisitor<Expression> {

    public RationalParserVisitor(Calculator calculator) {
        super();
    }
}
