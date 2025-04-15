package calculator.api.controller;

import calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculatorRestController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorRestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Evaluate an integer arithmetic operation.
     *
     * @param sign    the arithmetic operator as string (e.g., "+", "-", "*", "/")
     * @param number1 the first integer operand.
     * @param number2 the second integer operand.
     * @return the result of the operation.
     */
    @GetMapping("/evaluate")
    public String evaluate(
            @RequestParam("sign") String sign,
            @RequestParam("number1") int number1,
            @RequestParam("number2") int number2) {
        return calculatorService.getRep(sign, number1, number2);
    }

    /**
     * Evaluate a complex arithmetic operation.
     *
     * @param operator the arithmetic operator as string.
     * @param real1    the real part of the first complex number.
     * @param imag1    the imaginary part of the first complex number.
     * @param real2    the real part of the second complex number.
     * @param imag2    the imaginary part of the second complex number.
     * @return the result of the operation.
     */
    @GetMapping("/complex")
    public String computeComplexOperation(
            @RequestParam("operator") String operator,
            @RequestParam("real1") int real1,
            @RequestParam("imag1") int imag1,
            @RequestParam("real2") int real2,
            @RequestParam("imag2") int imag2) {
        return calculatorService.getComplexRep(operator, real1, imag1, real2, imag2);
    }

    /**
     * Evaluate a real arithmetic operation.
     *
     * @param sign  the arithmetic operator as string (e.g., "+", "-", "*", "/")
     * @param real1 the first real operand.
     * @param real2 the second real operand.
     * @return the result of the operation.
     */
    @GetMapping("/real")
    public String computeRealOperation(
            @RequestParam("sign") String sign,
            @RequestParam("real1") double real1,
            @RequestParam("real2") double real2) {
        return calculatorService.getRealRep(sign, real1, real2);
    }

    /**
     * Evaluate a rational arithmetic operation.
     *
     * @param sign the arithmetic operator as string (e.g., "+", "-", "*", "/")
     * @param num1 the numerator of the first operand.
     * @param den1 the denominator of the first operand.
     * @param num2 the numerator of the second operand.
     * @param den2 the denominator of the second operand.
     * @return the result of the operation.
     */
    @GetMapping("/rational")
    public String computeRationalOperation(
            @RequestParam("sign") String sign,
            @RequestParam("num1") int num1,
            @RequestParam("den1") int den1,
            @RequestParam("num2") int num2,
            @RequestParam("den2") int den2) {
        return calculatorService.getRationalRep(sign, num1, den1, num2, den2);
    }

    /**
     * Convert an angle from degrees to radians.
     *
     * @param degree the angle in degrees.
     * @return the angle converted to radians.
     */
    @GetMapping("/degToRad")
    public String convertDegreeToRadian(@RequestParam("degree") double degree) {
        return calculatorService.getFromDegreeToRadian(degree);
    }

    /**
     * Convert an angle from radians to degrees.
     *
     * @param radian the angle in radians.
     * @return the angle converted to degrees.
     */
    @GetMapping("/radToDeg")
    public String convertRadianToDegree(@RequestParam("radian") double radian) {
        return calculatorService.getFromRadianToDegree(radian);
    }
}
