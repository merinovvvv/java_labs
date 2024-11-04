import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MyApplication myApplication = new MyApplication("mouse tracking");
        myApplication.setMinimumSize(new Dimension(500, 500));
        myApplication.setVisible(true);
    }
}