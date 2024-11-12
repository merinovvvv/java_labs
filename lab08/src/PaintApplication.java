import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class PaintApplication extends JFrame {
    public static class PaintPanel extends JPanel {

        private final List<List<Point>> lines = new ArrayList<>();
        private List<Point> currentLine;
        private final List<Color> colors = new ArrayList<>();
        private Color currentColor = Color.BLACK;

        public PaintPanel() {

            setPreferredSize(new Dimension(2000, 2000));

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    currentLine.add(e.getPoint());
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentLine = new ArrayList<>();
                    lines.add(currentLine);
                    colors.add(currentColor);
                }
            });
        }

        //TODO add saving and opening an image

        public void setCurrentColor(Color color) {
            this.currentColor = color;
        }

        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            for (int i = 0; i < lines.size(); i++) {
                List<Point> line = lines.get(i);
                graphics.setColor(colors.get(i));
                for (int j = 1; j < line.size(); j++) {
                    Point p1 = line.get(j - 1);
                    Point p2 = line.get(j);
                    graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    JPanel centralPanel;
    JButton redButton;
    JButton greenButton;
    JButton blueButton;
    JButton clearButton;

    PaintApplication(String string) {

        super(string);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        centralPanel = new PaintPanel();

        JScrollPane scrollPane = new JScrollPane(centralPanel);

        redButton = new JButton("red");
        redButton.setFont(largerFont);
        redButton.setForeground(Color.RED);
        redButton.setBackground(Color.BLACK);

        greenButton = new JButton("green");
        greenButton.setFont(largerFont);
        greenButton.setForeground(Color.GREEN);
        greenButton.setBackground(Color.BLACK);

        blueButton = new JButton("blue");
        blueButton.setFont(largerFont);
        blueButton.setForeground(Color.BLUE);
        blueButton.setBackground(Color.BLACK);

        clearButton = new JButton("clear");
        clearButton.setFont(largerFont);

        redButton.addActionListener(_ -> ((PaintPanel) centralPanel).setCurrentColor(Color.RED));

        greenButton.addActionListener(_ -> ((PaintPanel) centralPanel).setCurrentColor(Color.GREEN));

        blueButton.addActionListener(_ -> ((PaintPanel) centralPanel).setCurrentColor(Color.BLUE));

        clearButton.addActionListener(_ -> {
            ((PaintPanel) centralPanel).lines.clear();
            ((PaintPanel) centralPanel).colors.clear();
            centralPanel.repaint();
        });

        JPanel colorButtonPanel = new JPanel();
        colorButtonPanel.add(redButton);
        colorButtonPanel.add(greenButton);
        colorButtonPanel.add(blueButton);

        add(scrollPane, BorderLayout.CENTER);
        add(colorButtonPanel, BorderLayout.NORTH);
        add(clearButton, BorderLayout.SOUTH);
    }
}
