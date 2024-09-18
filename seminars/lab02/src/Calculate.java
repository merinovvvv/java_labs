import java.util.StringTokenizer;

public class Calculate {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new MyException("Wrong number of arguments. 1 is required.");
            }
            int res = calc(args[0]);
            System.out.println("The result equals to " + res);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Not a number: " + e.getMessage());
        }
    }
    public static int calc(String arg) throws NumberFormatException {
        int res = 0;
        boolean operation = true; // true is +, false is -
        StringTokenizer st = new StringTokenizer(arg, "+-", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals("-")) {
                operation = false;
            } else if (token.equals("+")){
                operation = true;
            } else {
                int num = Integer.parseInt(token);
                if (operation) {
                    res += num;
                } else {
                    res -= num;
                }
            }
        }
        return res;
    }
}