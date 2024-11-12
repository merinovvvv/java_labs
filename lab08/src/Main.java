import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PaintApplication paintApplication = new PaintApplication("Paint app");
        paintApplication.setMinimumSize(new Dimension(500, 500));
        paintApplication.setVisible(true);
    }
}