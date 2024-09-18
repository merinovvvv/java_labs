public class Main {
    public static void main(String []args) {
        int[][] mtrx  = matrix(5);
        for (int i = 0; i < mtrx.length; i++) {
            for (int j = 0; j < mtrx[i].length; j++) {
                System.out.println(mtrx[i][j]);
            }
        }
    }

    static int[][] matrix(int n) {
        int[][]matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = new int[i + 1];
            matrix[i][0] = 1;
            matrix[i][i] = 1;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
            }
        }
        return matrix;
    }
}