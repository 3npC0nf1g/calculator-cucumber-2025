package calculator;

import static org.junit.jupiter.api.Assertions.*;

import calculator.values.IntegerValue;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class CalculatorSteps {

	private ArrayList<Expression> params;
	private Operation op;
	private Calculator c;
	private Operation compositeExpression;

	@Before
	public void resetMemoryBeforeEachScenario() {
		params = new ArrayList<>();
		op = null;
		compositeExpression = null;
	}

	@Given("I initialise a calculator")
	public void givenIInitialiseACalculator() {
		c = new Calculator();
	}

	@Given("an integer operation {string}")
	public void givenAnIntegerOperation(String s) {
		// Initialise l'opération en fonction de la chaîne de caractères (+, -, *, /)
		try {
			switch (s) {
				case "+" -> op = new Plus(params);
				case "-" -> op = new Minus(params);
				case "*" -> op = new Times(params);
				case "/" -> op = new Divides(params);
				default -> fail("Invalid operation: " + s);
			}
		} catch (IllegalConstruction e) {
			fail("Failed to construct operation: " + s);
		}
	}

	@Given("the following list of integer numbers")
	public void givenTheFollowingListOfNumbers(List<List<String>> numbers) {
		params = new ArrayList<>();
		// Convertir les chaînes en IntegerValue et les ajouter à la liste des paramètres
		numbers.get(0).forEach(n -> params.add(new MyNumber(new IntegerValue(Integer.parseInt(n)))));
	}

	@Given("^the sum of two numbers (\\d+) and (\\d+)$")
	public void givenTheSum(int n1, int n2) {
		// Créer une opération de somme avec les deux entiers, chaque entier est converti en IntegerValue
		params = new ArrayList<>();
		params.add(new MyNumber(new IntegerValue(n1)));
		params.add(new MyNumber(new IntegerValue(n2)));
		try {
			op = new Plus(params);
		} catch (IllegalConstruction e) {
			fail("Failed to create the sum operation");
		}
	}

	@Then("^its (.*) notation is (.*)$")
	public void thenItsNotationIs(String notation, String expected) {
		// Valider que la notation de l'opération correspond à la chaîne attendue
		try {
			if (notation.equals("PREFIX") || notation.equals("POSTFIX") || notation.equals("INFIX")) {
				op.notation = Notation.valueOf(notation);
				assertEquals(expected, op.toString());
			} else {
				fail(notation + " is not a correct notation!");
			}
		} catch (IllegalArgumentException e) {
			fail("Invalid notation provided: " + notation);
		}
	}

	@When("^I provide a (.*) number (\\d+)$")
	public void whenIProvideANumber(String s, int val) {
		// Ajouter un nouveau nombre à l'opération, chaque nombre est converti en IntegerValue
		params = new ArrayList<>();
		params.add(new MyNumber(new IntegerValue(val)));
		op.addMoreParams(params);
	}

	@Then("^the (.*) is (\\d+)$")
	public void thenTheOperationIs(String s, int val) {
		try {
			// En fonction de la chaîne, choisir l'opération appropriée
			switch (s) {
				case "sum" -> op = new Plus(params);
				case "product" -> op = new Times(params);
				case "quotient" -> op = new Divides(params);
				case "difference" -> op = new Minus(params);
				default -> fail("Invalid operation type: " + s);
			}
			int result = c.eval(op).getValueInt();  // Récupérer la valeur de l'objet IntegerValue
			assertEquals(val, result);  // Vérifier que le résultat de l'opération correspond à la valeur attendue
		} catch (IllegalConstruction e) {
			fail("Failed to construct the operation: " + s);
		}
	}

	@Then("the operation evaluates to {int}")
	public void thenTheOperationEvaluatesTo(int val) {
		int result = c.eval(op).getValueInt();  // Récupérer la valeur de l'objet IntegerValue
		assertEquals(val, result);  // Comparer la valeur obtenue à la valeur attendue
	}

	@Given("a composite expression consisting of the addition of {int} and {int} and multiplication by {int}")
	public void aCompositeExpressionConsistingOfTheAdditionOfAndAndMultiplicationBy(int arg0, int arg1, int arg2) throws IllegalConstruction {
		// Créer une expression composite : (arg0 + arg1) * arg2
		Expression addition = new Plus(List.of(new MyNumber(new IntegerValue(arg0)), new MyNumber(new IntegerValue(arg1))));
		compositeExpression = new Times(List.of(addition, new MyNumber(new IntegerValue(arg2))));
	}

	@When("I set the notation to {string}")
	public void iSetTheNotationToNotation(String notation) {
		if (compositeExpression != null) {
			try {
				compositeExpression.notation = Notation.valueOf(notation); // Définir la notation
			} catch (IllegalArgumentException e) {
				fail("Invalid notation: " + notation);
			}
		} else {
			fail("Composite expression is not initialized.");
		}
	}

	@Then("the expression displayed in {string} notation is {string}")
	public void theExpressionDisplayedInNotationNotationIsExpected(String notation, String expected) {
		if (compositeExpression != null) {
			try {
				compositeExpression.notation = Notation.valueOf(notation); // Définir la notation
				assertEquals(expected, compositeExpression.toString()); // Vérifier la sortie attendue
			} catch (IllegalArgumentException e) {
				fail("Invalid notation: " + notation);
			}
		} else {
			fail("Composite expression is not initialized.");
		}
	}
}
