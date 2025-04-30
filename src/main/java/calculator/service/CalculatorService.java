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

    /**
     * Processes a simple binary arithmetic operation using integers.
     *
     * @param expression    the arithmetic expression as string (e.g., "+", "-", "*", "/")

     * @return the result of the operation as a string or an error message in case of failure.
     */
    public String getRep(String expression) {
        try {
            ExpressionParser e=new ExpressionParser();
            logger.info(expression.trim());
            NumericValue expr = e.parse(expression.trim());
            return expr.toString();

        } catch (Exception e) {
            logger.error("Calculation failed in getRep", e);
            return "Error in getRep: " + e.getMessage();
        }
        }


}
