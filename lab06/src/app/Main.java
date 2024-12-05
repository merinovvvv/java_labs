package app;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MyApplication myApplication = new MyApplication("Goods");
        myApplication.setMinimumSize(new Dimension(500, 600));
        myApplication.setVisible(true);
    }
}