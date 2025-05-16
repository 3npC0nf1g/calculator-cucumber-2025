package calculator.service;


import calculator.*;
import calculator.util.AngleConverter;
import calculator.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import parser.ExpressionParser;

import java.util.ArrayList;

@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    ExpressionParser parser = new ExpressionParser();

    /**
     * Processes a simple binary arithmetic operation using integers.
     *
     * @param expression The arithmatic operation to evaluate

     * @return the result of the operation as a string or an error message in case of failure.
     */
    public String getRep(String expression) throws Exception {

        logger.info(expression.trim());
        NumericValue result = parser.parse(expression.trim());
        return result.toString();

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
