package visitor;
import calculator.*;
import calculator.logicOperator.*;
import calculator.parser.logic.LogicExprParserBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.ArrayList;
import java.util.List;
import calculator.parser.logic.LogicExprParserParser;
import java.util.Collections;


public class LogicParserVisitor extends LogicExprParserBaseVisitor<Expression>{
    private final Calculator calculator;

        public LogicParserVisitor(Calculator calculator) {
            this.calculator = calculator;
        }

        //-------------------- ENTRY ----------------------

        @Override
        public Expression visitEntry(LogicExprParserParser.EntryContext ctx) {
            return visit(ctx.logicExpr());
        }

        @Override
        public Expression visitLogicExpr(LogicExprParserParser.LogicExprContext ctx) {
            if (ctx.infixForm() != null) {
                return visit(ctx.infixForm());
            } else if (ctx.prefixForm() != null) {
                return visit(ctx.prefixForm());
            } else {
                return visit(ctx.postfixForm());
            }
        }

        //-------------------- INFIX ----------------------

        @Override
        public Expression visitInfixNegation(LogicExprParserParser.InfixNegationContext ctx) {
            Expression expr = visit(ctx.infixForm());
            List<Expression> params = Collections.singletonList(expr);
            try {
                return calculator.eval(new NotOperator(params));
            } catch (IllegalConstruction e) {
                return new InvalidNumber(0);
            }
        }

        @Override
        public Expression visitInfixAnd(LogicExprParserParser.InfixAndContext ctx) {
            return buildBinaryOperation(ctx.infixForm(0), ctx.infixForm(1), AndOperator::new);
        }

        @Override
        public Expression visitInfixOr(LogicExprParserParser.InfixOrContext ctx) {
            return buildBinaryOperation(ctx.infixForm(0), ctx.infixForm(1), OrOperator::new);
        }

        @Override
        public Expression visitInfixImplies(LogicExprParserParser.InfixImpliesContext ctx) {
            return buildBinaryOperation(ctx.infixForm(0), ctx.infixForm(1), ImplyOperator::new);
        }

        @Override
        public Expression visitInfixXor(LogicExprParserParser.InfixXorContext ctx) {
            return buildBinaryOperation(ctx.infixForm(0), ctx.infixForm(1), XorOperator::new);
        }

        @Override
        public Expression visitInfixEquiv(LogicExprParserParser.InfixEquivContext ctx) {
            return buildBinaryOperation(ctx.infixForm(0), ctx.infixForm(1), EqualsOperator::new);
        }

        @Override
        public Expression visitInfixLiteral(LogicExprParserParser.InfixLiteralContext ctx) {
            try {
                return new LogicOperators(Integer.parseInt(ctx.TRUTH().getText()));
            } catch (LogicOperators.InvalidNumberException e) {
                return new InvalidNumber(0);
            }
        }

        @Override
        public Expression visitInfixGroup(LogicExprParserParser.InfixGroupContext ctx) {
            return visit(ctx.infixForm());
        }

        //-------------------- PREFIX ----------------------

        @Override
        public Expression visitPrefixNegation(LogicExprParserParser.PrefixNegationContext ctx) {
            Expression expr = visit(ctx.prefixForm());
            List<Expression> params = Collections.singletonList(expr);
            try {
                return calculator.eval(new NotOperator(params));
            } catch (IllegalConstruction e) {
                return new InvalidNumber(0);
            }
        }

        @Override
        public Expression visitPrefixAnd(LogicExprParserParser.PrefixAndContext ctx) {
            return buildNaryOperation(ctx.prefixForm(), AndOperator::new);
        }

        @Override
        public Expression visitPrefixOr(LogicExprParserParser.PrefixOrContext ctx) {
            return buildNaryOperation(ctx.prefixForm(), OrOperator::new);
        }

        @Override
        public Expression visitPrefixImplies(LogicExprParserParser.PrefixImpliesContext ctx) {
            return buildNaryOperation(ctx.prefixForm(), ImplyOperator::new);
        }

        @Override
        public Expression visitPrefixXor(LogicExprParserParser.PrefixXorContext ctx) {
            return buildNaryOperation(ctx.prefixForm(), XorOperator::new);
        }

        @Override
        public Expression visitPrefixEquiv(LogicExprParserParser.PrefixEquivContext ctx) {
            return buildNaryOperation(ctx.prefixForm(), EqualsOperator::new);
        }

        @Override
        public Expression visitPrefixLiteral(LogicExprParserParser.PrefixLiteralContext ctx) {
            try {
                return new LogicOperators(Integer.parseInt(ctx.TRUTH().getText()));
            } catch (LogicOperators.InvalidNumberException e) {
                return new InvalidNumber(0);
            }
        }

        @Override
        public Expression visitPrefixGroup(LogicExprParserParser.PrefixGroupContext ctx) {
            return visit(ctx.prefixForm());
        }

        //-------------------- POSTFIX ----------------------

        @Override
        public Expression visitPostfixNegation(LogicExprParserParser.PostfixNegationContext ctx) {
            Expression expr = visit(ctx.postfixForm());
            List<Expression> params = Collections.singletonList(expr);
            try {
                return calculator.eval(new NotOperator(params));
            } catch (IllegalConstruction e) {
                return new InvalidNumber(0);
            }
        }

        @Override
        public Expression visitPostfixAnd(LogicExprParserParser.PostfixAndContext ctx) {
            return buildNaryOperation(ctx.postfixForm(), AndOperator::new);
        }

        @Override
        public Expression visitPostfixOr(LogicExprParserParser.PostfixOrContext ctx) {
            return buildNaryOperation(ctx.postfixForm(), OrOperator::new);
        }

        @Override
        public Expression visitPostfixImplies(LogicExprParserParser.PostfixImpliesContext ctx) {
            return buildNaryOperation(ctx.postfixForm(), ImplyOperator::new);
        }

        @Override
        public Expression visitPostfixXor(LogicExprParserParser.PostfixXorContext ctx) {
            return buildNaryOperation(ctx.postfixForm(), XorOperator::new);
        }

        @Override
        public Expression visitPostfixEquiv(LogicExprParserParser.PostfixEquivContext ctx) {
            return buildNaryOperation(ctx.postfixForm(), EqualsOperator::new);
        }

        @Override
        public Expression visitPostfixLiteral(LogicExprParserParser.PostfixLiteralContext ctx) {
            try {
                return new LogicOperators(Integer.parseInt(ctx.TRUTH().getText()));
            } catch (LogicOperators.InvalidNumberException e) {
                return new InvalidNumber(0);
            }
        }

        @Override
        public Expression visitPostfixGroup(LogicExprParserParser.PostfixGroupContext ctx) {
            return visit(ctx.postfixForm());
        }

        //-------------------- UTILS ----------------------

        private Expression buildBinaryOperation(LogicExprParserParser.InfixFormContext left, LogicExprParserParser.InfixFormContext right,
                                                OperatorFactory factory) {
            Expression expr1 = visit(left);
            Expression expr2 = visit(right);
            List<Expression> params = List.of(expr1, expr2);
            try {
                return calculator.eval(factory.create(params));
            } catch (IllegalConstruction e) {
                return new InvalidNumber(0);
            }
        }

        private Expression buildNaryOperation(List<? extends ParseTree> children, OperatorFactory factory) {
            List<Expression> params = new ArrayList<>();
            for (org.antlr.v4.runtime.tree.ParseTree child : children) {
                params.add(visit(child));
            }
            try {
                return calculator.eval(factory.create(params));
            } catch (IllegalConstruction e) {
                return new InvalidNumber(0);
            }
        }

        @FunctionalInterface
        private interface OperatorFactory {
            Expression create(List<Expression> params) throws IllegalConstruction;
        }
    }
