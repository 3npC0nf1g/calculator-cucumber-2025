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
     * @param expression    the arithmetic expression

     * @return the result of the operation.
     */
    @GetMapping("/evaluate")
    public String evaluate(
            @RequestParam("expression") String expression){
        return calculatorService.getRep(expression);
    }
}
