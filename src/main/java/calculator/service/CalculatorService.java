package calculator.service;


import calculator.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculatorService {

    public String getRep(String sign,int number1,int number2) {
        Calculator c = new Calculator();
        Operation op;
        ArrayList<Expression> params = new ArrayList<>();
        try {
            // Create a list of expressions for the operands.
            params.add(new MyNumber(number1));
            params.add(new MyNumber(number2));
            // Create the operation based on the sign.
                switch (sign) {
                    case "+":
                        op = new Plus(params);
                        break;
                    case "-":
                        op = new Minus(params);
                        break;
                    case "*":
                        op = new Times(params);
                        System.out.println(op);
                        break;
                    case "/":
                        op = new Divides(params);
                        break;
                    default:
                        // Return an error message for an invalid operator.
                        return "Error: Invalid operator '" + sign + "'";
                }


            return String.valueOf(c.eval(op));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        }
}
