import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            LampApp myApplication = new LampApp("control work 1st variant");
            myApplication.setMinimumSize(new Dimension(600, 500));
            myApplication.setVisible(true);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}