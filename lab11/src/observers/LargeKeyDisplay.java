package observers;

import javax.swing.*;

public class LargeKeyDisplay implements Observer {

    JLabel label;

    public LargeKeyDisplay(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(String pressedKey) {
        label.setText(pressedKey);
    }
}
