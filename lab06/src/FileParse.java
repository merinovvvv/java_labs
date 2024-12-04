import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileParse {

    static void openFile(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                loadFileContent(selectedFile, fileContentTextArea, fileContentMap, importCountries, goodsForExport);
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

    static void loadFileContent(File file, JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport) throws IOException{
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            fileContentTextArea.append(fileContent.toString());
            try {
                fillMapStreamApi(fileContentTextArea, fileContentMap, importCountries, goodsForExport);
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

    static void fillMapStreamApi(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport) throws NumberFormatException{
        fileContentMap.clear();
        String[] lines = fileContentTextArea.getText().split("\n");
        Arrays.stream(lines)
                .map(line -> line.split(", "))
                .filter(parts -> parts.length == 3)
                .forEach(parts -> fillMapCommon(fileContentTextArea, fileContentMap, importCountries, goodsForExport, parts));
    }

    private static void fillMapCommon(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport, String[] parts) {
        ImportCountries.countrySetCheck(parts, fileContentTextArea, importCountries);
        GoodsForExport.goodSetCheck(parts, fileContentTextArea, goodsForExport);

        String key = parts[0];
        String country = parts[1];
        int volume = Integer.parseInt(parts[2]);
        fileContentMap.computeIfAbsent(key, _ -> new ArrayList<>())
                .add(new Object[]{country, volume});
    }
}
