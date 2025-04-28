package calculator;



import calculator.Expression;
import calculator.MyNumber;
import calculator.values.NumericValue;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import calculator.parser.logic.*;

import visitor.LogicParserVisitor;

public class LogicParser {

    public final Calculator calculator;
    private final ParseTree tree;

    /**
     * This is the constructor of the class
     * @param expression a string who represent the expression
     */
    public LogicParser(String expression, Calculator calculator) {
        this.calculator = calculator;
        CharStream input = CharStreams.fromString(expression);
        LogicExprParserLexer lexer = new LogicExprParserLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogicExprParserParser parser = new LogicExprParserParser(tokens);
        tree = parser.entry();
    }

    /**
     * This method evaluate the expression
     * @return the result of the expression
     */
    public NumericValue evaluate(){
        LogicParserVisitor visitor = new LogicParserVisitor(calculator);
        Expression e = visitor.visit(tree);
        return calculator.eval(e);
    }

}
