package calculator;

import calculator.values.NumericValue;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import calculator.parser.expression.*;
import visitor.ExpressionParserVisitor;

/**
 * This class is the parser used by the calculator to evaluate the arithmetic expressions
 */
public class ExpressionParser {

    public final Calculator calculator;
    private final ParseTree tree;

    /**
     * This constructor creates a parser for the arithmetic expression
     * @param expression the expression to parse
     * @param c the calculator used to evaluate the expression
     */
    public ExpressionParser(String expression, Calculator c){
        this.calculator = c;
        CharStream input = CharStreams.fromString(expression);
        ExpressionParserLexer lexer = new ExpressionParserLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParserParser parser = new ExpressionParserParser(tokens);
        tree = parser.entry();
    }




    /**
     * This method evaluates the expression using the visitor design pattern
     * and returns its result
     * @return the result of the expression
     */
    public NumericValue evaluate(){
        ExpressionParserVisitor visitor = new ExpressionParserVisitor(calculator);
        Expression e = visitor.visit(tree);
        return calculator.eval(e);
    }
}