package app;

import view.MyApplication;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MyApplication myApplication = new MyApplication("Set");
        myApplication.setMinimumSize(new Dimension(500, 650));
        myApplication.setVisible(true);
    }
}