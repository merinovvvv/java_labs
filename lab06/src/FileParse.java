import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class FileParse {

    static ImportCountries importCountries = new ImportCountries();
    static GoodsForExport goodsForExport = new GoodsForExport();

    static void openFile(MyApplication myApplication) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(myApplication);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadFileContent(selectedFile, myApplication);
        }
    }

    static void loadFileContent(File file, MyApplication myApplication) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            myApplication.fileContentTextArea.setText(fileContent.toString());
            fillMap(fileContent, myApplication);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    myApplication,
                    "Error while reading file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    static void fillMap(StringBuilder fileContent, MyApplication myApplication) {
        myApplication.fileContentMap.clear();
        String[] lines = fileContent.toString().split("\n");
        for (String line : lines) {
            String[] parts = line.split(", ");
            if (parts.length == 3) {
                try {
                    ImportCountries.countrySetCheck(parts, myApplication, importCountries);
                    GoodsForExport.goodSetCheck(parts, myApplication, goodsForExport);

                    String key = parts[0];
                    String country = parts[1];
                    int volume = Integer.parseInt(parts[2]);
                    myApplication.fileContentMap.computeIfAbsent(key, k -> new ArrayList<>())
                            .add(new Object[]{country, volume});
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(
                            myApplication,
                            "Wrong type of arguments in the file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    myApplication.fileContentTextArea.setText("");
                }
            }
        }
    }
}
