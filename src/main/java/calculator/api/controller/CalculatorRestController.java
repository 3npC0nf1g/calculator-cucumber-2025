package calculator.api.controller;
import calculator.*;
import calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class CalculatorRestController {
    // Use dependency injection if possible; here we instantiate directly for simplicity.

    private final CalculatorService calculatorService;
    @Autowired
    public CalculatorRestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
     
    }

    /**
     * Evaluate an arithmetic expression.
     *
     * @param sign The expression in infix notation (e.g., "3+4-5").
     * @return The result of evaluating the expression.
     */
    @GetMapping("/evaluate")
    public String evaluate(
            @RequestParam("sign") String sign,
            @RequestParam("number1") int number1,
            @RequestParam("number2") int number2) {

       return calculatorService.getRep(sign, number1, number2);
    }
}
