package app;

import view.View;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        View view = new View("Set");
        view.setMinimumSize(new Dimension(500, 650));
        view.setVisible(true);
    }
}