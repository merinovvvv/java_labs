import java.io.File;
import java.util.Scanner;

public class matrixFile {
    public static int[][] getMatrix(String fileName) throws Exception {
        Scanner sc = new Scanner(new File(fileName));
        int d = sc.nextInt();
        int[][] matrix = new int[d][d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        if (sc.hasNext()) {
            throw new Exception("Too many elements.");
        }
        return matrix;
    }
}
