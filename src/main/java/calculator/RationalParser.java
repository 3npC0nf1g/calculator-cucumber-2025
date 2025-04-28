package calculator;

import calculator.parser.rational.RationalCoreLexer;
import calculator.parser.rational.RationalCoreParser;
import calculator.values.NumericValue;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitor.RationalParserVisitor;


public class RationalParser {

    public final Calculator calculator;
    private final ParseTree tree;

        /**
         * This constructor creates a parser for the arithmetic expression
         * @param expression the expression to parse
         * @param c the calculator used to evaluate the expression
         */
        public RationalParser(String expression, Calculator c){
            this.calculator = c;
            CharStream input = CharStreams.fromString(expression);
            RationalCoreLexer lexer = new RationalCoreLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RationalCoreParser parser = new RationalCoreParser(tokens);
            tree = parser.entry();
        }




        /**
         * This method evaluates the expression using the visitor design pattern
         * and returns its result
         * @return the result of the expression
         */
        public NumericValue evaluate(){
            RationalParserVisitor visitor = new RationalParserVisitor(calculator);
            Expression e = visitor.visit(tree);
            return calculator.eval(e);
        }

}
