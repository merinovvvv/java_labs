import javax.swing.*;

public class LargeKeyDisplay implements Observer {

    JLabel label;

    LargeKeyDisplay(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(String pressedKey) {
        label.setText(pressedKey);
    }
}
