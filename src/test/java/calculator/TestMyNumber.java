package calculator;

import static org.junit.jupiter.api.Assertions.*;

import calculator.values.NumericValue;
import calculator.values.IntegerValue;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class TestMyNumber {

	private final NumericValue value = new IntegerValue(8); // Utilisation de IntegerValue qui implémente NumericValue
	private MyNumber number;  // La variable 'number' est maintenant de type MyNumber

	@BeforeEach
	void setUp() {
		// Création de l'objet MyNumber qui contient un NumericValue
		number = new MyNumber(value);
	}

	@Test
	void testEquals() {
		// Test de l'égalité entre deux MyNumber ayant la même valeur
		assertEquals(new MyNumber(value), number);

		// Test de l'égalité entre deux MyNumber ayant des valeurs différentes
		NumericValue otherValue = new IntegerValue(7);
		assertNotEquals(new MyNumber(otherValue), number);

		// Vérification d'identité (toujours vrai)
		assertEquals(number, number);

		// Vérification que MyNumber n'est pas égal à un int (un type différent)
		assertNotEquals(number, value); // MyNumber ne doit pas être égal à un IntegerValue

		// Test avec un autre type qui implémente NumericValue, ici une opération Times
		try {
			// Le type Times est un type d'Operation, pas un NumericValue
			assertNotEquals(new Times(new ArrayList<>()), number);
		} catch (IllegalConstruction e) {
			fail("Une exception imprévue s'est produite lors de l'égalité avec un type différent");
		}

		// Vérification de l'égalité avec null, ce qui devrait toujours renvoyer false
		assertNotEquals(number, null);

		// Test avec un objet de type Object, qui ne doit pas être égal à NumericValue
		assertNotEquals(number, new Object());  // NumericValue et Object ne doivent pas être égaux
	}

	@Test
	void testToString() {
		// Test de la méthode toString() pour s'assurer qu'elle renvoie la valeur sous forme de chaîne
		assertEquals(value.toString(), number.toString());
	}

	@Test
	void testHashCode() {
		// Vérification du hashCode : deux MyNumber avec les mêmes valeurs doivent avoir le même hashCode
		MyNumber other = new MyNumber(value);
		assertEquals(number.hashCode(), other.hashCode());

		// Vérification de l'unicité du hashCode pour des valeurs différentes
		NumericValue otherValue = new IntegerValue(7);
		MyNumber differentNumber = new MyNumber(otherValue);
		assertNotEquals(number.hashCode(), differentNumber.hashCode());
	}
}
