import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MyApplication myApplication = new MyApplication("running button");
        myApplication.setMinimumSize(new Dimension(200, 200));
        myApplication.setSize(new Dimension(500, 500));
        myApplication.setVisible(true);
    }
}