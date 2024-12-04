import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ObserverPatternApp extends JFrame {

    KeyPressSubject keyPressSubject;
    JLabel largeKeyLabel;

    DefaultListModel<String> logListModel;
    JList<String> logList;

    LargeKeyDisplay largeKeyDisplay;
    KeyLogDisplay keyLogDisplay;

    ObserverPatternApp(String string) {
        super(string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setFocusable(true);

        Font largeKeyFont = new Font("Arial", Font.BOLD, 64);
        Font logKeyFont = new Font("Arial", Font.BOLD, 24);

        keyPressSubject = new KeyPressSubject();

        largeKeyLabel = new JLabel("Press the key", SwingConstants.CENTER);
        largeKeyLabel.setFont(largeKeyFont);
        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(600, 0));
        westPanel.add(largeKeyLabel, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);

        logListModel = new DefaultListModel<>();
        logList = new JList<>(logListModel);
        logList.setFont(logKeyFont);
        JScrollPane scrollPane = new JScrollPane(logList);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        add(scrollPane, BorderLayout.EAST);

        largeKeyDisplay = new LargeKeyDisplay(largeKeyLabel);
        keyLogDisplay = new KeyLogDisplay(logListModel);

        keyPressSubject.attach(largeKeyDisplay);
        keyPressSubject.attach(keyLogDisplay);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                String key = KeyEvent.getKeyText(e.getKeyCode());
                keyPressSubject.notifyObservers(key);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}
