import javax.swing.*;

public class KeyLogDisplay implements Observer {

    private final DefaultListModel<String> jListModel;

    KeyLogDisplay(DefaultListModel<String> jListModel) {
        this.jListModel = jListModel;
    }

    @Override
    public void update(String pressedKey) {
        jListModel.addElement(pressedKey);
    }
}
