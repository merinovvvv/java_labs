public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new MyException("Wrong number of arguments: 1 is required.");
            }
            StringBuffer resStr = removeSpaces(args[0]);
            System.out.println(resStr);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public static StringBuffer removeSpaces(String arg) {
        StringBuffer str = new StringBuffer(arg);
        while (str.charAt(0) == ' ') {
            str.deleteCharAt(0);
        }

        for (int i = str.length() - 1; str.charAt(i) == ' '; i--) {
            str.deleteCharAt(i);
        }

        for (int i = 0; i < str.length(); ) {
            if (str.charAt(i) == ' ') {
                if (str.charAt(i + 1) == ' ' || i == 0) {
                    str.deleteCharAt(i);
                } else {
                    i++;
                }
            } else {
                if ((i == 0 || (str.charAt(i - 1)) == ' ') && (i == str.length() - 1 || str.charAt(i + 1) == ' ' )) {
                    str.deleteCharAt(i);
                    if (i != 0) {
                        str.deleteCharAt(i - 1);
                        i--;
                    }
                } else {
                    i++;
                }
            }
        }
        return str;
    }
}