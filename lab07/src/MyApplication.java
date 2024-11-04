import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;

public class MyApplication extends JFrame {

    JButton button;
    JLabel statusBar;
    JPanel centerPanel;


    MyApplication(String string) {


        super(string);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        button = new JButton("button");
        button.setSize(100, 50);
        button.setLocation(200, 200);
        button.setFocusable(true);

        statusBar = new JLabel();

        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.add(button);

        centerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setButtonCoordinates(e);
            }
        });

        centerPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                changeStatusBar(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                changeStatusBar(e.getX(), e.getY());
            }
        });

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLetterOrDigit(keyChar)) {
                    button.setText(button.getText() + keyChar);
                } else if (keyChar == KeyEvent.VK_BACK_SPACE && !button.getText().isEmpty()) {
                    button.setText(button.getText().substring(0, button.getText().length() - 1));
                }
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) {
                    button.addMouseMotionListener(new MouseMotionAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            if (!e.isControlDown()) { //TODO work when ctrl is released
                                button.removeMouseMotionListener(this);
                            }
                            MouseEvent e2 = new MouseEvent(button, e.getID(), e.getWhen(), e.getModifiersEx(), e.getX() + button.getX(), e.getY() + button.getY(), e.getClickCount(), e.isPopupTrigger(), e.getButton()); //TODO understand
                            setButtonCoordinates(e2);
                            changeStatusBar(e2.getX(), e2.getY());
                        }
                    });
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseMotionListener[] listeners = button.getMouseMotionListeners();
                if (listeners.length > 0) {
                    button.removeMouseMotionListener(listeners[0]);
                }
            }
        });

        this.add(statusBar, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void changeStatusBar(double x, double y) {
        statusBar.setText("x: " + x + ", y: " + y);
    }

    private void setButtonCoordinates(MouseEvent e) { //TODO understand
        int newX = Math.min(Math.max(e.getX(), 0), centerPanel.getWidth() - button.getWidth());
        int newY = Math.min(Math.max(e.getY(), 0), centerPanel.getHeight() - button.getHeight());
        button.setLocation(newX, newY);
    }
}
