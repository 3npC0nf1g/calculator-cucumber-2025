package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.Notation;
import calculator.values.ComplexValue;
import calculator.values.IntegerValue;
import calculator.values.RealValue;

import java.util.stream.Collectors;

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
        result = switch (n.getValue()) {
            case IntegerValue iv -> Integer.toString(iv.getValue());
            case RealValue rv -> rv.getValue().toString();
            case ComplexValue cv -> cv.toString();
            default -> throw new IllegalArgumentException("Unsupported NumericValue type");
        };
    }


    @Override
    public void visit(Operation o) {
        result = (o.getArgs().size() == 1)
                ? formatUnaryOperation(o)
                : formatNAryOperation(o);
    }

    private String formatUnaryOperation(Operation o) {
        String argResult = getFormattedArg(o.getArgs().getFirst());

        return switch (notation) {
            case INFIX -> String.format("( %s %s )", o.symbol, argResult);
            case PREFIX -> String.format("%s(%s)", o.symbol, argResult);
            case POSTFIX -> String.format("(%s) %s", argResult, o.symbol);
        };
    }

    private String formatNAryOperation(Operation o) {
        return switch (notation) {
            case INFIX -> formatInfixOperation(o);
            case PREFIX -> formatPrefixOperation(o);
            case POSTFIX -> formatPostfixOperation(o);
        };
    }

    private String formatInfixOperation(Operation o) {
        return String.format("( %s )",
                o.getArgs().stream()
                        .map(this::getFormattedArg)
                        .collect(Collectors.joining(" " + o.symbol + " "))
        );
    }

    private String formatPrefixOperation(Operation o) {
        return String.format("%s (%s)",
                o.symbol,
                o.getArgs().stream()
                        .map(this::getFormattedArg)
                        .collect(Collectors.joining(", "))
        );
    }

    private String formatPostfixOperation(Operation o) {
        return String.format("(%s) %s",
                o.getArgs().stream()
                        .map(this::getFormattedArg)
                        .collect(Collectors.joining(", ")),
                o.symbol
        );
    }

    private String getFormattedArg(Expression arg) {
        PrintVisitor subVisitor = new PrintVisitor(notation);
        arg.accept(subVisitor);
        return subVisitor.getResult();
    }
}
