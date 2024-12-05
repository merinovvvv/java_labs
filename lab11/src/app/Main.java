package app;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ObserverPatternApp observerPatternApp = new ObserverPatternApp("observer.Observer");
        observerPatternApp.setMinimumSize(new Dimension(800, 800));
        observerPatternApp.setVisible(true);
    }
}