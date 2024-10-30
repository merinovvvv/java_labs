import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class GoodsForExport {
    private final Set<String> exportGoods;

    GoodsForExport() {
        exportGoods = new HashSet<>();
        exportGoods.add("Apples");
        exportGoods.add("Pears");
        exportGoods.add("Bananas");
        exportGoods.add("Televisions");
        exportGoods.add("Fridges");
        exportGoods.add("Washing machines");
        exportGoods.add("Cars");
        exportGoods.add("Planes");
        exportGoods.add("Motorcycles");
        exportGoods.add("Bicycles");
        exportGoods.add("Computers");
        exportGoods.add("Smartphones");
        exportGoods.add("Cameras");
        exportGoods.add("Headphones");
        exportGoods.add("Speakers");
        exportGoods.add("Microphones");
        exportGoods.add("Guitars");
        exportGoods.add("Pianos");
        exportGoods.add("Sofas");
        exportGoods.add("Tables");
    }

    Set<String> getExportGoods() {
        return exportGoods;
    }

    void addGood(String good) {
        exportGoods.add(good);
    }

    static void goodSetCheck(String[] parts, JTextArea fileContentTextArea, GoodsForExport goodsForExport) {
        try {
            Integer.parseInt(parts[0]);
            JOptionPane.showMessageDialog(
                    null,
                    "The file contains a number '" + parts[0] + "' that is not a good.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
            fileContentTextArea.setText("");
        } catch (NumberFormatException e) {
            boolean isInGoodsSet = goodsForExport.getExportGoods().stream() //TODO with Stream API
                    .anyMatch(good -> parts[0].equals(good));

            if (!isInGoodsSet) {
                Object[] options = {"YES", "NO"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "The file contains a good '" + parts[0] + "' that is not a good or not in 'goods for export'.\nDo you want to add a good for export?",
                        "good question",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == JOptionPane.YES_OPTION) {
                    goodsForExport.addGood(parts[0]);
                } else if (choice == JOptionPane.NO_OPTION) {
                    fileContentTextArea.setText("");
                }
            }
        }
    }
}
