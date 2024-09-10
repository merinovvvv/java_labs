public class MultiplicationTable2 {
    public static void main(String[] args) {
        makeTable();
    }

    private static void makeTable() {
        System.out.println("  y | 1y | 2y | 3y | 4y | 5y ");
        System.out.println("----|----|----|----|----|----");

        for (int i = 1; i  <= 10; ++i){
            System.out.printf("%3d |%3d |%3d |%3d |%3d |%3d\n", i, i, i * 2, i * 3, i * 4, i * 5);
        }

    }
}