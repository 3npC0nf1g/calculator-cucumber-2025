package calculator.values;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void testAddition() {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix B = Matrix.parse("[[5,6],[7,8]]");
        Matrix expected = Matrix.parse("[[6,8],[10,12]]");
        assertTrue(A.add(B).equals(expected));
    }

    @Test
    public void testSubtraction() {
        Matrix A = Matrix.parse("[[9,8],[7,6]]");
        Matrix B = Matrix.parse("[[1,2],[3,4]]");
        Matrix expected = Matrix.parse("[[8,6],[4,2]]");
        assertTrue(A.subtract(B).equals(expected));
    }

    @Test
    public void testMultiplication() {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix B = Matrix.parse("[[2,0],[1,2]]");
        Matrix expected = Matrix.parse("[[4,4],[10,8]]");
        assertTrue(A.multiply(B).equals(expected));
    }

    @Test
    public void testTranspose() {
        Matrix A = Matrix.parse("[[1,2,3],[4,5,6]]");
        Matrix expected = Matrix.parse("[[1,4],[2,5],[3,6]]");
        assertTrue(A.transpose().equals(expected));
    }

    @Test
    public void testInverse() {
        Matrix A = Matrix.parse("[[4,7],[2,6]]");
        Matrix expected = Matrix.parse("[[0.6,-0.7],[-0.2,0.4]]");
        Matrix inverse = A.inverse();

        assertTrue(inverse.equalsApprox(expected, 0.0001));
    }

    @Test
    public void testFromStringInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.parse("[[1,2], [3]]"); // ligne irrégulière
        });
    }

    @Test
    public void testAddDimensionMismatch() {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix B = Matrix.parse("[[1,2]]");
        assertThrows(IllegalArgumentException.class, () -> A.add(B));
    }

    @Test
    public void testMultiplyDimensionMismatch() {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix B = Matrix.parse("[[1,2,3]]");
        assertThrows(IllegalArgumentException.class, () -> A.multiply(B));
    }

    @Test
    public void testInverseSingularMatrix() {
        Matrix singular = Matrix.parse("[[1,2],[2,4]]"); // ligne 2 = ligne 1 × 2
        assertThrows(ArithmeticException.class, singular::inverse);
    }

}
