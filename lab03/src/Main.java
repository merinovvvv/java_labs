import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        try {
            double[][] matrix = getMatrix();
            double[] solution = calcMatrixSystem(matrix);
            printSolution(solution);
        } catch (MyException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static double[][] getMatrix() throws MyException, FileNotFoundException {
        Scanner sc = new Scanner(new File("matrix.txt"));

        if (!sc.hasNext()) {
            throw new MyException("Not enough elements.");
        }
        if (!sc.hasNextInt()) {
            throw new MyException("Incorrect number for rows.");
        }
        int rows = sc.nextInt();
        if (rows == 0) {
            throw new MyException("Rows number is 0.");
        }

        if (!sc.hasNext()) {
            throw new MyException("Not enough elements.");
        }
        if (!sc.hasNextInt()) {
            throw new MyException("Incorrect number for cols.");
        }
        int cols = sc.nextInt();
        if (cols == 0) {
            throw new MyException("Cols number is 0.");
        }

        if (cols - rows != 1 || rows >= cols) {
            throw new MyException("The matrix is not square");
        }

        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!sc.hasNext()) {
                    throw new MyException("Not enough elements.");
                } else {
                    if (!sc.hasNextFloat()) {
                        throw new MyException("Not a number in matrix.");
                    } else {
                        matrix[i][j] = sc.nextFloat();
                    }
                }
            }
        }
        if (sc.hasNext()) {
            throw new MyException("Too many elements.");
        }
        return matrix;
    }

    static double[] calcMatrixSystem(double[][] matrix) throws MyException {
        int n = matrix.length;
        double epsilon = 1e-6;

        for (int k = 0; k < n; k++) {
            if (matrix[k][k] == 0) {

                boolean swapped = false;
                for (int i = k + 1; i < n; i++) {
                    if (matrix[i][k] != 0) {
                        double[] temp = matrix[k];
                        matrix[k] = matrix[i];
                        matrix[i] = temp;
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    throw new MyException("There is no solution or infinitely many solutions");
                }
            }

            for (int i = k + 1; i < n; i++) {
                if (matrix[i][k] != 0) {
                    double coeff = matrix[i][k] / matrix[k][k];
                    for (int j = k; j <= n; j++) {
                        matrix[i][j] -= coeff * matrix[k][j];
                        if (Math.abs(matrix[i][j]) < epsilon) {
                            matrix[i][j] = 0;
                        }
                    }
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = matrix[i][n];
            for (int j = i + 1; j < n; j++) {
                sum -= matrix[i][j] * solution[j];
            }
            if (matrix[i][i] == 0) {
                throw new MyException("There is no solution or infinitely many solutions");
            }
            solution[i] = Math.round((sum / matrix[i][i]) * 10000.0) / 10000.0;
        }
        return solution;
    }

    static void printSolution(double[] solution) {
        System.out.println("Решение системы:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }
}
