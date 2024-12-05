package observers;

import javax.swing.*;

public class KeyLogDisplay implements Observer {

    private final DefaultListModel<String> jListModel;

    public KeyLogDisplay(DefaultListModel<String> jListModel) {
        this.jListModel = jListModel;
    }

    @Override
    public void update(String pressedKey) {
        jListModel.addElement(pressedKey);
    }
}
