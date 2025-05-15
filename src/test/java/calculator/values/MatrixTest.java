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

    @Test
    public void test4x4Transpose() {
        Matrix A = Matrix.parse("[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]");
        Matrix expected = Matrix.parse("[[1,5,9,13],[2,6,10,14],[3,7,11,15],[4,8,12,16]]");
        assertEquals(expected, A.transpose());
    }
    @Test
    public void test5x5Addition() {
        String row = "[1,1,1,1,1]";
        Matrix A = Matrix.parse("[" + row + "," + row + "," + row + "," + row + "," + row + "]");
        Matrix B = Matrix.parse("[" + row + "," + row + "," + row + "," + row + "," + row + "]");
        Matrix expected = Matrix.parse("[" +
                "[2,2,2,2,2]," +
                "[2,2,2,2,2]," +
                "[2,2,2,2,2]," +
                "[2,2,2,2,2]," +
                "[2,2,2,2,2]" +
                "]");
        assertEquals(expected, A.add(B));
    }

    @Test
    public void testZeroMatrixMultiplication() {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix zero = Matrix.parse("[[0,0],[0,0]]");
        Matrix expected = zero;
        assertEquals(expected, A.multiply(zero));
    }

    @Test
    public void testIdentityMatrixMultiplication() {
        Matrix A = Matrix.parse("[[1,2],[3,4]]");
        Matrix identity = Matrix.parse("[[1,0],[0,1]]");
        assertEquals(A, A.multiply(identity));
    }

    @Test
    public void testLargeMatrixEquality() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < 10; i++) {
            builder.append("[");
            for (int j = 0; j < 10; j++) {
                builder.append("1");
                if (j < 9) builder.append(",");
            }
            builder.append("]");
            if (i < 9) builder.append(",");
        }
        builder.append("]");
        Matrix A = Matrix.parse(builder.toString());
        Matrix B = Matrix.parse(builder.toString());
        assertEquals(A, B);
    }


}
