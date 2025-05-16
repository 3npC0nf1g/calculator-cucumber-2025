package calculator.service;


import calculator.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import parser.ExpressionParser;


@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    /**
     * Processes a simple binary arithmetic operation using integers.
     *
     * @param expression arithmatic operation to evaluate

     * @return the result of the operation as a string or an error message in case of failure.
     */
    public String getRep(String expression) throws Exception {
        ExpressionParser e = new ExpressionParser();
        if (logger.isDebugEnabled()) {
            logger.info(expression.trim());
        }
        NumericValue expr = e.parse(expression.trim());
        return expr.toString();
    }

    /**
     * Toggles between Radiants and angles
     */
    public void toggleAngleMode() {
        ExpressionParser.Mode current = ExpressionParser.getMode();
        ExpressionParser.Mode newMode = (current == ExpressionParser.Mode.RADIANS)
                ? ExpressionParser.Mode.DEGREES
                : ExpressionParser.Mode.RADIANS;

        ExpressionParser.setMode(newMode);
        logger.info("Angle mode toggled to: {}", newMode);
    }

     /**
     * Toggles between displaying fractions or decimals
     */
    public void toggleDisplayMode() {
        ExpressionParser.Display current = ExpressionParser.getDisplay();
        ExpressionParser.Display newMode = (current == ExpressionParser.Display.FRACTION)
                ? ExpressionParser.Display.DECIMAL
                : ExpressionParser.Display.FRACTION;

        ExpressionParser.setDisplay(newMode);
        logger.info("Display mode toggled to: {}", newMode);
    }


}
