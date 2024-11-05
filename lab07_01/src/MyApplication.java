import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MyApplication extends JFrame {

    JPanel panel;
    JLabel javaLikeLabel;
    JButton yesButton;
    JButton noButton;
    Random random;

    MyApplication(String str) {
        super(str);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        random = new Random();

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("OptionPane.messageFont", new Font("Dialog", Font.BOLD, 20));
        UIManager.put("OptionPane.buttonFont", new Font("Dialog", Font.PLAIN, 20));

        Font largerFont = new Font("Dialog", Font.BOLD, 20);

        javaLikeLabel = new JLabel("Do you like java?");
        javaLikeLabel.setFont(largerFont);

        yesButton = new JButton("YES");
        yesButton.setSize(100, 50);
        yesButton.setLocation(120, 100);

        noButton = new JButton("NO");
        noButton.setSize(100, 50);
        noButton.setLocation(250, 250);

        panel = new JPanel();
        panel.add(yesButton);
        panel.add(noButton);
        panel.setLayout(null);

        yesButton.addActionListener(actionEvent -> showCorrectAnswer());

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                teleportButton(noButton);
            }
        });

        noButton.addActionListener(actionEvent -> {
            noButton.setText("YES");
            Timer timer = new Timer(300, action -> showCorrectAnswer());
            timer.setRepeats(false);
            timer.start();
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (noButton.getX() + noButton.getWidth() > getWidth() || noButton.getY() + noButton.getHeight() + javaLikeLabel.getHeight() > getHeight()) {
                    teleportButton(noButton);
                }
                if (yesButton.getX() + yesButton.getWidth() > getWidth() || yesButton.getY() + yesButton.getHeight() + javaLikeLabel.getHeight() > getHeight()) {
                    teleportButton(yesButton);
                }

            }
        });

        this.add(javaLikeLabel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }

    private void teleportButton(JButton button) {
        int maxX = MyApplication.this.getWidth() - button.getWidth();
        int maxY = MyApplication.this.getHeight() - button.getHeight() - javaLikeLabel.getHeight();
        button.setLocation(random.nextInt(maxX), random.nextInt(maxY));
    }

    private void showCorrectAnswer() {
        JOptionPane.showMessageDialog(
                this,
                "Correct answer!",
                "java is cool!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
