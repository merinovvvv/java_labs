import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PaintApplication extends JFrame {
    public static class PaintPanel extends JPanel {

        private BufferedImage image;
        private Color currentColor = Color.BLACK;
        private Point lastPoint = null;

        public PaintPanel() {
            setPreferredSize(new Dimension(2000, 2000));

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (lastPoint != null) {
                        drawLineOnImageThenPanel(lastPoint, e.getPoint());
                    }
                    lastPoint = e.getPoint();
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    lastPoint = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    lastPoint = null;
                }
            });
        }

        public void setCurrentColor(Color color) {
            this.currentColor = color;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public void clearImage() {
            if (image != null) {
                Graphics g = image.createGraphics();
                g.setColor(getBackground());
                g.fillRect(0, 0, image.getWidth(), image.getHeight());
                g.dispose();

                Graphics g2 = getGraphics();
                if (g2 != null) {
                    g2.setColor(getBackground());
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.dispose();
                }
            }
        }

        private void drawLineOnImageThenPanel(Point start, Point end) {
            if (image == null) {
                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            Graphics g = image.createGraphics();
            g.setColor(currentColor);
            g.drawLine(start.x, start.y, end.x, end.y);
            g.dispose();

            Graphics g2 = getGraphics();
            if (g2 != null) {
                g2.setColor(currentColor);
                g2.drawLine(start.x, start.y, end.x, end.y);
                g2.dispose();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        }
    }

    JPanel centralPanel;
    JButton redButton;
    JButton greenButton;
    JButton blueButton;
    JButton clearButton;
    JMenuBar menuBar;

    PaintApplication(String title) {
        super(title);
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
            } catch (IOException e) {
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
            } catch (IOException e) {
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
        clearButton.addActionListener(_ -> ((PaintPanel) centralPanel).clearImage());

        JPanel colorButtonPanel = new JPanel();
        colorButtonPanel.add(redButton);
        colorButtonPanel.add(greenButton);
        colorButtonPanel.add(blueButton);

        add(scrollPane, BorderLayout.CENTER);
        add(colorButtonPanel, BorderLayout.NORTH);
        add(clearButton, BorderLayout.SOUTH);
    }
}
