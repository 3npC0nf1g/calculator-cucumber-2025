package calculator.values;
import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private final double[][] data;

    public Matrix(double[][] data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.length;
    }

    public int getColCount() {
        return data[0].length;
    }

    public double[][] getData() {
        return data;
    }

    public static Matrix parse(String input) {
        input = input.replaceAll("\\[\\[", "").replaceAll("]]", "");
        String[] rows = input.split("],\\[");
        int rowCount = rows.length;
        int colCount = rows[0].split(",").length;
        double[][] matrix = new double[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            String[] cols = rows[i].split(",");
            for (int j = 0; j < colCount; j++) {
                matrix[i][j] = Double.parseDouble(cols[j]);
            }
        }
        return new Matrix(matrix);
    }

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

    public void print(String title) {
        System.out.println("---- " + title + " ----");
        System.out.print(this.toString());
        System.out.println();
    }


    public Matrix add(Matrix other) {
        if (getRowCount() != other.getRowCount() || getColCount() != other.getColCount()) {
            throw new IllegalArgumentException("Dimensions incompatibles pour l'addition.");
        }
        double[][] result = new double[getRowCount()][getColCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++)
                result[i][j] = data[i][j] + other.data[i][j];
        return new Matrix(result);
    }

    public Matrix subtract(Matrix other) {
        if (getRowCount() != other.getRowCount() || getColCount() != other.getColCount()) {
            throw new IllegalArgumentException("Dimensions incompatibles pour la soustraction.");
        }
        double[][] result = new double[getRowCount()][getColCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++)
                result[i][j] = data[i][j] - other.data[i][j];
        return new Matrix(result);
    }

    public Matrix multiply(Matrix other) {
        if (getColCount() != other.getRowCount()) {
            throw new IllegalArgumentException("Dimensions incompatibles pour la multiplication.");
        }
        double[][] result = new double[getRowCount()][other.getColCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < other.getColCount(); j++)
                for (int k = 0; k < getColCount(); k++)
                    result[i][j] += data[i][k] * other.data[k][j];
        return new Matrix(result);
    }

    public Matrix transpose() {
        double[][] result = new double[getColCount()][getRowCount()];
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColCount(); j++)
                result[j][i] = data[i][j];
        return new Matrix(result);
    }

    public Matrix inverse() {
        int n = getRowCount();
        if (n != getColCount()) throw new IllegalArgumentException("Seulement les matrices carrées sont inversibles.");
        double[][] temp = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(data[i], 0, temp[i], 0, n);
            temp[i][i + n] = 1;
        }

        for (int i = 0; i < n; i++) {
            double pivot = temp[i][i];
            if (pivot == 0) throw new ArithmeticException("Matrice non inversible (pivot nul).");
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

    @Override
    public int hashCode() {
        int result = Objects.hash(getRowCount(), getColCount());
        for (int i = 0; i < getRowCount(); i++) {
            result = 31 * result + Arrays.hashCode(data[i]);
        }
        return result;
    }

}
