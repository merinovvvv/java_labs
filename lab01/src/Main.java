//var 7
public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        double res = calculateFunc(args);
        System.out.println(res);
    }

    private static double calculateFunc(String[] args) {
        double x = 0.1; //default value
        double accuracy = 1; //default value
        try {
            x = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for x: " + e.getMessage());
            System.out.println("x is set to default (0.1).");
        }

        try {
            accuracy = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for accuracy: " + e.getMessage());
            System.out.println("accuracy is set to default (1).");
        }

        double sum = 0;
        for (int k = 1; ; k++) {
            double current = Math.pow(x, k - 1)  / ((k + 1) * (k + 2));
            if (Math.abs(current) >= accuracy) {
                sum += current;
            } else {
                break;
            }
        }
        return sum;
    }
}