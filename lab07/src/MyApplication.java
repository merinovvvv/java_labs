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

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        button = new JButton("button");
        button.setSize(100, 50);
        button.setLocation(200, 200);
        button.setFont(largerFont);
        button.setFocusable(true);

        statusBar = new JLabel();
        statusBar.setFont(largerFont);

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

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                MouseEvent e2 = SwingUtilities.convertMouseEvent(button, e, centerPanel);
                changeStatusBar(e2.getX(), e2.getY());
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MouseEvent e2 = SwingUtilities.convertMouseEvent(button, e, centerPanel);
                changeStatusBar(e2.getX(), e2.getY());
                if (e2.isControlDown()) {
                    setButtonCoordinates(e2);
                }
            }
        });

        this.add(statusBar, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void changeStatusBar(double x, double y) {
        statusBar.setText("x: " + x + ", y: " + y);
    }

    private void setButtonCoordinates(MouseEvent e) {
        int newX = e.getXOnScreen() - centerPanel.getLocationOnScreen().x - button.getWidth() / 2;
        int newY = e.getYOnScreen() - centerPanel.getLocationOnScreen().y - button.getHeight() / 2;
        newX = Math.min(Math.max(newX, 0), centerPanel.getWidth() - button.getWidth());
        newY = Math.min(Math.max(newY, 0), centerPanel.getHeight() - button.getHeight());
        button.setLocation(newX, newY);
    }
}