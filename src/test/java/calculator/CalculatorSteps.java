package calculator;

import static org.junit.jupiter.api.Assertions.*;

import calculator.values.ComplexValue;
import calculator.values.IntegerValue;
import calculator.values.NumericValue;
import calculator.values.RealValue;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorSteps {

	private ArrayList<Expression> params;
	private Operation op;
	private Calculator c;
	private Operation compositeExpression;


	// Déclaration des variables pour la conversion d'angles
	private double angleDegrees;
	private double angleRadians;


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
		numbers.getFirst().forEach(n -> params.add(new MyNumber(new IntegerValue(Integer.parseInt(n)))));
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

	// Nouvelle étape pour les opérations sur des réels
	@Given("a real operation {string}")
	public void givenARealOperation(String s) {
		params = new ArrayList<>();
		try {
			switch (s) {
				case "+" -> op = new Plus(params);
				case "-" -> op = new Minus(params);
				case "*" -> op = new Times(params);
				case "/" -> op = new Divides(params);
				default -> fail("Invalid real operation: " + s);
			}
		} catch (IllegalConstruction e) {
			fail("Failed to construct real operation: " + s);
		}
	}

	// Nouvelle étape pour les opérations sur les nombres complexes
	@Given("a complex operation {string}")
	public void givenAComplexOperation(String s) {
		params = new ArrayList<>();
		try {
			switch (s) {
				case "+" -> op = new Plus(params);
				case "-" -> op = new Minus(params);
				case "*" -> op = new Times(params);
				case "/" -> op = new Divides(params);
				default -> fail("Invalid complex operation: " + s);
			}
		} catch (IllegalConstruction e) {
			fail("Failed to construct complex operation: " + s);
		}
	}

	@When("I provide a first real number {double}")
	public void whenIProvideAFirstRealNumber(double val) {
		op.addMoreParams(List.of(new MyNumber(new RealValue(val, 6))));
	}

	@When("I provide a second real number {double}")
	public void whenIProvideASecondRealNumber(double val) {
		op.addMoreParams(List.of(new MyNumber(new RealValue(val, 6))));
	}

	private void provideComplexNumber(String complexStr) {
		ComplexValue cv = parseComplex(complexStr);
		op.addMoreParams(List.of(new MyNumber(cv)));
	}

	@When("I provide a first complex number {string}")
	public void whenIProvideAFirstComplexNumber(String complexStr) {
		provideComplexNumber(complexStr);
	}

	@When("I provide a second complex number {string}")
	public void whenIProvideASecondComplexNumber(String complexStr) {
		provideComplexNumber(complexStr);
	}

	@Then("the operation evaluates to {double}")
	public void thenTheOperationEvaluatesToDouble(double expected) {
		NumericValue result = c.eval(op);

		double actual;
		if (result instanceof RealValue) {
			actual = ((RealValue) result).getValue().doubleValue();
		} else if (result instanceof IntegerValue) {
			actual = ((IntegerValue) result).getValue();
		} else {
			fail("Expected numeric result but got: " + result.getClass().getSimpleName());
			return;
		}

		assertEquals(expected, actual, 0.0001); // correct usage with numeric delta
	}


	private ComplexValue parseComplex(String s) {
		// Nettoyage de la chaîne
		s = s.replaceAll("\\s+", "");
		// Normalisation des combinaisons de signes
		s = s.replace("+-", "-").replace("-+", "-").replace("++", "+");

		// Expression régulière pour capturer la partie réelle et imaginaire
		Pattern pattern = Pattern.compile("^([+-]?\\d+(?:\\.\\d+)?)([+-]\\d+(?:\\.\\d+)?)i$");
		Matcher matcher = pattern.matcher(s);
		if (!matcher.matches()) {
			fail("Invalid complex number format: " + s);
		}

		double realPart = Double.parseDouble(matcher.group(1));
		double imagPart = Double.parseDouble(matcher.group(2));

		return new ComplexValue(realPart, imagPart);
	}

	// Étapes pour la conversion d'angle
	@Given("I have an angle of {int} degrees")
	public void iHaveAnAngleOfDegrees(int degrees) {
		angleDegrees = degrees;
	}

	@When("I convert the angle to radians")
	public void iConvertTheAngleToRadians() {
		angleRadians = calculator.util.AngleConverter.degreesToRadians(angleDegrees);
	}

	@Then("the result should be {string}")
	public void thenTheResultShouldBeString(String expected) {
		// Comparaison par chaîne de caractères
		assertEquals(expected, Double.toString(angleRadians));
	}

	@Then("the result should be {double}")
	public void thenTheResultShouldBeDouble(double expected) {
		assertEquals(expected, angleRadians, 1e-9);
	}


	@Then("the operation evaluates to {string}")
	public void thenTheOperationEvaluatesToComplexString(String expected) {
		NumericValue result = c.eval(op);
		if (!(result instanceof ComplexValue)) {
			fail("Expected result to be a ComplexValue but got: " + result.getClass().getSimpleName());
		}

		// Remove spaces and normalize both expected and actual strings
		String actual = normalizeComplex(((ComplexValue) result).toString().replace(" ", ""));
		String normalizedExpected = normalizeComplex(expected.replace(" ", ""));

		assertEquals(normalizedExpected, actual);
	}

	private String normalizeComplex(String complex) {
		// Remove trailing zeroes and ".0" without causing backtracking issues
		return complex.replaceAll("(\\d+\\.\\d*?)0+(?=\\D|$)", "$1")
				.replaceAll("\\.0+(?=\\D|$)", "");
	}
}
