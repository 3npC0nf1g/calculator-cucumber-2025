package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.Notation;

public class PrintVisitor extends Visitor {

    private final Notation notation;
    private String result;

    /**
     * Constructor that initializes the visitor with the desired notation.
     *
     * @param notation the notation to use for displaying (PREFIX, INFIX, POSTFIX)
     */
    public PrintVisitor(Notation notation) {
        this.notation = notation;
        this.result = "";
    }

    /**
     * Returns the generated string.
     *
     * @return the string representation of the expression according to the notation
     */
    public String getResult() {
        return result;
    }

    @Override
    public void visit(MyNumber n) {
        result = Integer.toString(n.getValue());
    }

    @Override
    public void visit(Operation o) {
        StringBuilder sb = new StringBuilder();
        switch (notation) {
            case INFIX:
                sb.append("( ");
                boolean firstInfix = true;
                for (Expression e : o.getArgs()) {
                    if (!firstInfix) {
                        sb.append(" ").append(o.symbol).append(" ");
                    }
                    PrintVisitor pvInfix = new PrintVisitor(Notation.INFIX);
                    e.accept(pvInfix);
                    sb.append(pvInfix.getResult());
                    firstInfix = false;
                }
                sb.append(" )");
                break;
            case PREFIX:
                sb.append(o.symbol).append(" (");
                boolean firstPrefix = true;
                for (Expression e : o.getArgs()) {
                    if (!firstPrefix) {
                        sb.append(", ");
                    }
                    PrintVisitor pvPrefix = new PrintVisitor(Notation.PREFIX);
                    e.accept(pvPrefix);
                    sb.append(pvPrefix.getResult());
                    firstPrefix = false;
                }
                sb.append(")");
                break;
            case POSTFIX:
                sb.append("(");
                boolean firstPostfix = true;
                for (Expression e : o.getArgs()) {
                    if (!firstPostfix) {
                        sb.append(", ");
                    }
                    PrintVisitor pvPostfix = new PrintVisitor(Notation.POSTFIX);
                    e.accept(pvPostfix);
                    sb.append(pvPostfix.getResult());
                    firstPostfix = false;
                }
                sb.append(") ").append(o.symbol);
                break;
        }
        result = sb.toString();
    }
}
