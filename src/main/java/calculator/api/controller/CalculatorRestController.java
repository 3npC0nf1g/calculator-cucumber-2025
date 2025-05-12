package calculator.api.controller;

import calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> evaluate(@RequestParam("expression") String expression) {
        try {
            String result = calculatorService.getRep(expression);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression: " + e.getMessage(), e);
        }
    }

    /**
     *  Toggles between radiant and degree mode
     * */
    @GetMapping("/toggle-angle-mode")
    public ResponseEntity<String> toggleAngleMode() {
        calculatorService.toggleAngleMode();
        return ResponseEntity.ok("Angle mode toggled.");
    }

    /**
     *  Toggles between fraction and decimal mode
     * */
    @GetMapping("/toggle-display")
    public ResponseEntity<String> toggleDisplayMode() {
        calculatorService.toggleDisplayMode();
        return ResponseEntity.ok("Display mode toggled.");
    }


}
