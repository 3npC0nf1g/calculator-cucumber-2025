package calculator.values;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixCalculatorTest {

    @Test
    public void testAddition() throws Exception {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix B = Matrix.parse("[[5,6],[7,8]]");
        Matrix result = A.add(B);
        assertArrayEquals(new double[][]{{6,8},{10,12}}, result.getData());
    }

    @Test
    public void testSubtraction() throws Exception {
        Matrix A = Matrix.parse("[[5,6],[7,8]]");
        Matrix B = Matrix.parse("[[1,2],[3,4]]");
        Matrix result = A.subtract(B);
        assertArrayEquals(new double[][]{{4,4},{4,4}}, result.getData());
    }

    @Test
    public void testMultiplication() throws Exception {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix B = Matrix.parse("[[2,0],[1,2]]");
        Matrix result = A.multiply(B);
        assertArrayEquals(new double[][]{{4,4},{10,8}}, result.getData());
    }

    @Test
    public void testTranspose() throws Exception {
        Matrix A = Matrix.parse("[[1,2,3],[4,5,6]]");
        Matrix result = A.transpose();
        assertArrayEquals(new double[][]{{1,4},{2,5},{3,6}}, result.getData());
    }



    @Test
    public void testInverse() throws Exception {
        Matrix A = Matrix.parse("[[4,7],[2,6]]");
        Matrix inverse = A.inverse();

        // Expected inverse matrix
        double[][] expected = {{0.6, -0.7}, {-0.2, 0.4}};
        double[][] actual = inverse.getData();

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], actual[i][j], 1e-9);
            }
        }
    }

    @Test
    public void testParseInvalidMatrix() {
        assertThrows(Exception.class, () -> Matrix.parse("[[1,2],[3]]"));
    }

    @Test
    public void testInverseNonInvertibleMatrix() {
        assertThrows(Exception.class, () -> {
            Matrix A = Matrix.parse("[[1,2],[2,4]]"); // Determinant = 0
            A.inverse();
        });
    }

    @Test
    public void testInvalidOperationInCalculator() {
        // A placeholder for manual test, or mocking input for MatrixCalculator
        MatrixCalculator calc = new MatrixCalculator();

    }
}
