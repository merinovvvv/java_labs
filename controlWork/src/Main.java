import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            PupilApp pupilApp = new PupilApp("control work 2nd variant");
            pupilApp.setMinimumSize(new Dimension(1200, 1000));
            pupilApp.setVisible(true);
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