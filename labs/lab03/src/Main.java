import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        try {
            int[][] matrix = getMatrix();
            calcMatrix(matrix);
        } catch (MyException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static int[][] getMatrix() throws MyException, FileNotFoundException {
        Scanner sc = new Scanner(new File("matrix.txt"));
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!sc.hasNext()) {
                    throw new MyException("Not enough elements.");
                } else {
                    if (!sc.hasNextInt()) {
                        throw new MyException("Not a number in matrix.");
                    } else {
                        matrix[i][j] = sc.nextInt();
                    }
                }
            }
        }
        if (sc.hasNext()) {
            throw new MyException("Too many elements.");
        }
        return matrix;
    }

    static void calcMatrix(int[][] matrix) throws MyException{
        int headPos = 0;
        int k = 1;
        for (int i = 1; i < matrix.length; i++) {
            int head = matrix[i][headPos];
            if (matrix[i][i] == 0) {
                throw new MyException("Determinant equals to 0.");
            }
            for (int j = headPos; j < matrix[i].length; j++) {
                matrix[i][j] -= matrix[i - k][j] * (head / matrix[i - k][headPos]);
            }
            if (matrix[i][i - 1] != 0) {
                headPos++;
                k--;
                i--;
            } else {
                k++;
            }
        }

        int[] solution = new int[matrix.length];

        for (int i = matrix.length - 1; i >= 0; i--) {
            int sum = matrix[i][matrix.length];
            for (int j = i + 1; j < matrix.length; j++) {
                sum -= matrix[i][j] * solution[j];
            }
            if (matrix[i][i] == 0) {
                System.out.println("Нет решения или бесконечно много решений");
                return;
            }
            solution[i] = sum / matrix[i][i];
        }

        System.out.println("Решение системы:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }
}
