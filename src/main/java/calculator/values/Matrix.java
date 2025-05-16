package calculator.values;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a 2D matrix of double values and provides operations
 * such as addition, subtraction, multiplication, inversion, transposition,
 * and approximate comparison.
 */
public class Matrix {

    private final double[][] data;
    /**
     * Constructs a matrix from a 2D array of double values.
     *
     * @param data the 2D array representing matrix values
     */
    public Matrix(double[][] data) {
        this.data = data;
    }

    /**
     * Returns the number of rows in the matrix.
     *
     * @return the row count
     */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the matrix.
     *
     * @return the column count
     */
    public int getColCount() {
        return data[0].length;
    }

    /**
     * Returns the underlying 2D array representing the matrix data.
     *
     * @return the matrix data
     */
    public double[][] getData() {
        return data;
    }

    /**
     * Parses a matrix from a string in the format: "[[1,2],[3,4]]".
     *
     * @param input the string representation of the matrix
     * @return a new {@code Matrix} object
     * @throws NumberFormatException if the string contains invalid numbers
     */
    public static Matrix parse(String input) {
        try {
            input = input.trim();
            input = input.replaceAll("\\[\\[", "");
            input = input.replaceAll("]]", "");
            String[] rowStrings = input.split("],\\s*\\[");

            int rows = rowStrings.length;
            int cols = -1;
            double[][] data = null;

            for (int i = 0; i < rows; i++) {
                // Supprimer les crochets restants si présents
                rowStrings[i] = rowStrings[i].replaceAll("[\\[\\]]", "").trim();
                String[] values = rowStrings[i].split("\\s*,\\s*");

                if (cols == -1) {
                    cols = values.length;
                    data = new double[rows][cols];
                } else if (values.length != cols) {
                    throw new IllegalArgumentException("All rows must have the same number of columns.");
                }

                for (int j = 0; j < cols; j++) {
                    data[i][j] = Double.parseDouble(values[j]);
                }
            }

            return new Matrix(data);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid matrix format: " + input, e);
        }
    }


    /**
     * Compares this matrix with another matrix, allowing a margin of error (epsilon)
     * for floating-point approximations.
     *
     * @param other   the matrix to compare with
     * @param epsilon the allowed difference between corresponding elements
     * @return {@code true} if the matrices are approximately equal, {@code false} otherwise
     */
    public boolean equalsApprox(Matrix other, double epsilon) {
        if (this.getRowCount() != other.getRowCount() || this.getColCount() != other.getColCount()) return false;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColCount(); j++) {
                if (Math.abs(this.data[i][j] - other.data[i][j]) > epsilon) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a formatted string representation of the matrix.
     *
     * @return the matrix as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append("| ");
            for (int j = 0; j < data[i].length; j++) {
                sb.append(String.format("%6.2f ", data[i][j]));
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    /**
     * Prints the matrix to the standard output with a title.
     *
     * @param title the title to display above the matrix
     */
    public void print(String title) {
        System.out.println("---- " + title + " ----");
        System.out.print(this.toString());
        System.out.println();
    }

    /**
     * Adds this matrix to another matrix.
     *
     * @param other the matrix to add
     * @return a new {@code Matrix} representing the result
     * @throws IllegalArgumentException if the dimensions of the matrices differ
     */
    public Matrix add(Matrix other) {
        if (getRowCount() != other.getRowCount() || getColCount() != other.getColCount()) {
            throw new IllegalArgumentException("\n" + "Incompatible dimensions for addition.");
        }
        double[][] result = new double[getRowCount()][getColCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++)
                result[i][j] = data[i][j] + other.data[i][j];
        return new Matrix(result);
    }

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param other the matrix to subtract
     * @return a new {@code Matrix} representing the result
     * @throws IllegalArgumentException if the dimensions of the matrices differ
     */
    public Matrix subtract(Matrix other) {
        if (getRowCount() != other.getRowCount() || getColCount() != other.getColCount()) {
            throw new IllegalArgumentException("\n" + "Incompatible dimensions for subtraction.");
        }
        double[][] result = new double[getRowCount()][getColCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++)
                result[i][j] = data[i][j] - other.data[i][j];
        return new Matrix(result);
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param other the matrix to multiply with
     * @return the resulting matrix
     * @throws IllegalArgumentException if the number of columns of this matrix
     *                                  is not equal to the number of rows of the other
     */
    public Matrix multiply(Matrix other) {
        if (getColCount() != other.getRowCount()) {
            throw new IllegalArgumentException("\n" +  "Incompatible dimensions for multiplication.");
        }
        double[][] result = new double[getRowCount()][other.getColCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < other.getColCount(); j++)
                for (int k = 0; k < getColCount(); k++)
                    result[i][j] += data[i][k] * other.data[k][j];
        return new Matrix(result);
    }


    /**
     * Returns the transpose of the matrix (rows become columns and vice versa).
     *
     * @return the transposed matrix
     */
    public Matrix transpose() {
        double[][] result = new double[getColCount()][getRowCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++)
                result[j][i] = data[i][j];
        return new Matrix(result);
    }

    /**
     * Computes and returns the inverse of the matrix using Gauss-Jordan elimination.
     * Only square matrices are invertible.
     *
     * @return the inverse matrix
     * @throws IllegalArgumentException if the matrix is not square
     * @throws ArithmeticException if the matrix is not invertible (zero pivot encountered)
     */
    public Matrix inverse() {
        int n = getRowCount();
        if (n != getColCount()) throw new IllegalArgumentException("\n" + "Only square matrices are invertible.");
        double[][] temp = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(data[i], 0, temp[i], 0, n);
            temp[i][i + n] = 1;
        }

        for (int i = 0; i < n; i++) {
            double pivot = temp[i][i];
            if (pivot == 0) throw new ArithmeticException("\n" + "Non-invertible matrix (null pivot)");
            for (int j = 0; j < 2 * n; j++) temp[i][j] /= pivot;
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = temp[k][i];
                    for (int j = 0; j < 2 * n; j++)
                        temp[k][j] -= factor * temp[i][j];
                }
            }
        }

        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(temp[i], n, result[i], 0, n);
        return new Matrix(result);
    }

    /**
     * Compares this matrix with another object for exact equality.
     *
     * @param obj the object to compare with
     * @return {@code true} if the other object is a matrix with equal dimensions and values
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Matrix other = (Matrix) obj;

        if (this.getRowCount() != other.getRowCount() || this.getColCount() != other.getColCount()) return false;

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColCount(); j++) {
                if (Double.compare(this.data[i][j], other.data[i][j]) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Computes the hash code of the matrix.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(getRowCount(), getColCount());
        for (int i = 0; i < getRowCount(); i++) {
            result = 31 * result + Arrays.hashCode(data[i]);
        }
        return result;
    }

}
