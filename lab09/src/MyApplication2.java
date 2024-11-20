import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyApplication2 extends JFrame {

    Color buttonsFirstColor = Color.GREEN;
    Color buttonsSecondColor = Color.RED;

    class MyListener extends MouseAdapter {
        static final String CLICKED = "Clicked!";
        String buttonText;

        @Override
        public void mousePressed(MouseEvent e) {
            JButton pressedButton = (JButton) e.getSource();
            buttonText = pressedButton.getText();
            pressedButton.setText(CLICKED);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton pressedButton = (JButton) e.getSource();
            pressedButton.setText(buttonText);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton enteredButton = (JButton) e.getSource();
            enteredButton.setBackground(buttonsSecondColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton exitedButton = (JButton) e.getSource();
            exitedButton.setBackground(buttonsFirstColor);
        }
    }

    final static int CON = 5;

    public MyApplication2(String string) {
        super(string);
        setLayout(new GridLayout(CON, CON));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("Button.select", buttonsSecondColor);

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        MouseListener mouseListener = new MyListener();
        for (int i = 1; i <= CON * CON; i++) {
            JButton button = new JButton(Integer.toString(i));
            add(button);
            button.setBackground(buttonsFirstColor);
            button.setFont(largerFont);
            button.addMouseListener(mouseListener);
        }
    }
}
