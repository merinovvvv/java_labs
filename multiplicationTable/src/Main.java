public class Main {
    public static void main(String[] args) {
        makeTable();
    }

    private static void makeTable() {
        System.out.println("  y | 1y | 2y | 3y | 4y | 5y ");
        System.out.println("----|----|----|----|----|----");


        for (int i = 1; i <= 10; i++) {
            System.out.printf("%3d ", i);
            for (int j = 1; j <= 5; j++) {
                System.out.printf("|%3d ", i*j);
            }
            System.out.println();
        }
    }
}