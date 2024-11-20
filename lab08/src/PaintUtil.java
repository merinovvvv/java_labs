import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintUtil {

    public static void saveImage(PaintApplication.PaintPanel paintPanel) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            BufferedImage image = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.createGraphics();
            paintPanel.paint(g);
            g.dispose();

            ImageIO.write(image, "png", file);
        }
    }

    public static void openImage(PaintApplication.PaintPanel paintPanel) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedImage image = ImageIO.read(file);

            paintPanel.setImage(image);
            Graphics g = paintPanel.getGraphics();
            if (g != null) {
                g.drawImage(image, 0, 0, paintPanel);
                g.dispose();
            }
        }
    }
}
