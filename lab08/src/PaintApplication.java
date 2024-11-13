import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaintApplication extends JFrame {
    public static class PaintPanel extends JPanel {

        private final List<List<Point>> lines = new ArrayList<>();
        private List<Point> currentLine;
        private final List<Color> colors = new ArrayList<>();
        private Color currentColor = Color.BLACK;
        private BufferedImage image;

        public PaintPanel() {
            setPreferredSize(new Dimension(2000, 2000));

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    currentLine.add(e.getPoint());
                    updatePanel();
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

        public void clearLinesAndColors() {
            lines.clear();
            colors.clear();
            image = null;
        }

        public void setCurrentColor(Color color) {
            this.currentColor = color;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            if (image != null) {
                graphics.drawImage(image, 0, 0, this);
            }
        }

        public void updatePanel() {

            if (this.image == null) {
                this.image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            Graphics g = image.createGraphics();

            for (int i = 0; i < lines.size(); i++) {
                List<Point> line = lines.get(i);
                g.setColor(colors.get(i));
                for (int j = 1; j < line.size(); j++) {
                    Point p1 = line.get(j - 1);
                    Point p2 = line.get(j);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }

            g.dispose();

            Graphics graphics = getGraphics();
            graphics.drawImage(image, 0, 0, this);
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }

    JPanel centralPanel;
    JButton redButton;
    JButton greenButton;
    JButton blueButton;
    JButton clearButton;
    JMenuBar menuBar;

    PaintApplication(String string) {

        super(string);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(largerFont);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(largerFont);
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setFont(largerFont);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        saveItem.addActionListener(_ -> {
            try {
                PaintUtil.saveImage((PaintPanel) centralPanel);
            }  catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error while saving an image",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        openItem.addActionListener(_ -> {
            try {
                PaintUtil.openImage((PaintPanel) centralPanel);
            }  catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error while opening an image",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));


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
            ((PaintPanel) centralPanel).image = null;
            repaint();
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
