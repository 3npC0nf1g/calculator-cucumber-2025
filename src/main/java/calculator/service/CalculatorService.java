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



    /**
     * Processes a simple binary arithmetic operation using complex numbers.
     *
     * @param sign the arithmetic operator as string (e.g., "+", "-", "*", "/")
     * @param real1 the real part of the first complex number
     * @param complex1 the imaginary part fot the first complex number
     * @param real2 the real part of the second complex number
     * @param complex2 the imaginary part fot the second complex number
     * @return the result of the operation as a string or an error message in case of failure.
     */

    public String getComplexRep(String sign,int real1,int complex1,int real2,int complex2) {

        try {
            ComplexValue first =new ComplexValue(real1,complex1);
            ComplexValue second =new ComplexValue(real2,complex2);
            NumericValue result;

            switch (sign) {
                case "+":
                    result = first.add(second);
                    break;
                case "-":
                    result=first.subtract(second);
                    break;
                case "*":
                    result =first.multiply(second);
                    break;
                case "/":
                   result =  first.divide(second);
                    break;
                default:
                    logger.error("Invalid operator received for the complex operation: {}", sign);
                    // Return an error message for an invalid operator.
                    return "Error: Invalid operator in getComplexRep '" + sign + "'";
            }
            return result.toString();
        }catch (Exception e) {
            logger.error("Calculation failed in getComplexRep", e);
            return "Error in getComplexRep: " + e.getMessage();
        }
    }

    /**
     * Processes a simple binary arithmetic operation using real numbers.
     *
     * @param sign the arithmetic operator as string (e.g., "+", "-", "*", "/")
     * @param real1 the first real argument
     * @param real2 the second real argument
     * @return the result of the operation as a string or an error message in case of failure.
     */
    public String getRealRep(String sign,double real1,double real2) {
        try {
            RealValue first =new RealValue( real1,4);
            RealValue second =new RealValue(real2,4);
            NumericValue result;

            switch (sign) {
                case "+":
                    result = first.add(second);
                    break;
                case "-":
                    result=first.subtract(second);
                    break;
                case "*":
                    result =first.multiply(second);
                    break;
                case "/":
                    result =  first.divide(second);
                    break;
                default:
                    logger.error("Invalid operator received for the real operation: {}", sign);
                    // Return an error message for an invalid operator.
                    return "Error: Invalid operator '" + sign + "'";        }

            return result.toString();
        }
        catch (Exception e) {
        logger.error("Calculation failed", e);
        return "Error: " + e.getMessage();
        }
    }


    /**
     * Processes a simple binary arithmetic operation using rational numbers.
     *
     * @param sign the arithmetic operator as string (e.g., "+", "-", "*", "/")
     * @param num1 the numerator of the first argument
     * @param den1 the denominator of the first argument
     * @param num2 the numerator of the second argument
     * @param den2 the denominator of the second argument
     * @return the result of the operation as a string or an error message in case of failure.
     */
    public String getRationalRep(String sign,int num1,int den1,int num2,int den2) {

        try {
            RationalValue first =new RationalValue(num1,den1);
            RationalValue second = new RationalValue(num2,den2);
            NumericValue result;
            switch (sign) {
                case "+":
                    result = first.add(second);
                    break;
                case "-":
                    result=first.subtract(second);
                    break;
                case "*":
                    result =first.multiply(second);
                    break;
                case "/":
                    result =  first.divide(second);
                    break;
                default:
                    logger.error("Invalid operator received for the rational operation: {}", sign);
                    // Return an error message for an invalid operator.
                    return "Error: Invalid operator in getRationalRep '" + sign + "'";
            }
            return result.toString();
        }catch (Exception e) {
            logger.error("Calculation failed  in getRationalRep", e);
            return "Error:  in getRationalRep" + e.getMessage();
        }

    }

    /**
     * Processes conversion from degree to radians
     *
     * @param degree the degree to be converted in radians
     * @return the result of the conversion as a string or an error message in case of failure.
     */
    public String getFromDegreeToRadian(double degree){
        try{
            double response  = AngleConverter.degreesToRadians(degree);
            return String.valueOf(response);

        }catch (Exception e){
            logger.error("Calculation failed in getFromDegreeToRadian", e);
            return "Error in getFromDegreeToRadian" + e.getMessage();
        }

    }
    /**
     * Processes conversion from radians to degrees
     *
     * @param radian the degree to be converted in degrees
     * @return the result of the conversion as a string or an error message in case of failure.
     */
    public String getFromRadianToDegree(double radian){
        try{
            double response  = AngleConverter.radiansToDegrees(radian);
            return String.valueOf(response);

        }catch (Exception e){
            logger.error("Calculation failed in getFromRadianToDegree", e);
            return "Error in getFromRadianToDegree" + e.getMessage();
        }

    }
}
