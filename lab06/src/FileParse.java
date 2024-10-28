import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileParse {

    static ImportCountries importCountries = new ImportCountries(); //TODO make non static
    static GoodsForExport goodsForExport = new GoodsForExport(); //TODO make non static

    static void openFile(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                loadFileContent(selectedFile, fileContentTextArea, fileContentMap);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error while reading file: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    static void loadFileContent(File file, JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap) throws IOException{
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            fileContentTextArea.append(fileContent.toString());
            try {
                fillMap(fileContentTextArea, fileContentMap);
            }  catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "Wrong type of arguments in the file.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                fileContentTextArea.setText("");
            }
        }
    }

    static void fillMap(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap) throws NumberFormatException{ //TODO work if open file and then add info
        fileContentMap.clear();
        String[] lines = fileContentTextArea.getText().split("\n");
        for (String line : lines) {
            String[] parts = line.split(", ");
            if (parts.length == 3) {
                ImportCountries.countrySetCheck(parts, fileContentTextArea, importCountries);
                GoodsForExport.goodSetCheck(parts, fileContentTextArea, goodsForExport);

                String key = parts[0];
                String country = parts[1];
                int volume = Integer.parseInt(parts[2]);
                fileContentMap.computeIfAbsent(key, k -> new ArrayList<>())
                        .add(new Object[]{country, volume});
            }
        }
    }
}
