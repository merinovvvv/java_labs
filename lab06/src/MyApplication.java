import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class MyApplication extends JFrame {

    Map<String, List<Object[]>> fileContentMap;
    JTextArea fileContentTextArea;
    JLabel goodNameLabel;
    JTextField goodNameTextField;
    JButton showInfoButton;
    JButton showInfoButtonStreamApi;
    JButton addInfoButton;
    JButton clearButton;
    JTextArea infoTextArea;
    JMenuBar menuBar;

    ImportCountries importCountries;
    GoodsForExport goodsForExport;

    MyApplication(String string) {
        super(string);
        this.getContentPane().setBackground(Color.PINK);
        this.setLayout(new GridBagLayout());

        importCountries = new ImportCountries();
        goodsForExport = new GoodsForExport();

        UIManager.put("OptionPane.messageFont", new Font("Dialog", Font.BOLD, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Dialog", Font.PLAIN, 16));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        fileContentMap = new HashMap<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // margins for elements in the grid layout (top, left, bottom, right) 5px
        gbc.fill = GridBagConstraints.HORIZONTAL; // elements are stretched horizontally

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fileContentTextArea = new JTextArea(6, 15);
        fileContentTextArea.setFont(largerFont);
        fileContentTextArea.setForeground(Color.BLACK);
        fileContentTextArea.setEditable(false);
        JScrollPane scrollPaneFileContent = new JScrollPane(fileContentTextArea);

        clearButton = new JButton("clear");
        clearButton.setFont(largerFont);

        goodNameLabel = new JLabel("good's name: ");
        goodNameLabel.setFont(largerFont);
        goodNameTextField = new JTextField(15);
        goodNameTextField.setFont(largerFont);
        showInfoButton = new JButton("show info");
        showInfoButton.setFont(largerFont);

        showInfoButtonStreamApi = new JButton("show info stream api");
        showInfoButtonStreamApi.setFont(largerFont);

        addInfoButton = new JButton("add info");
        addInfoButton.setFont(largerFont);

        showInfoButton.addActionListener(actionEvent -> {
            if (goodNameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        MyApplication.this,
                        "Good's name field is empty!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                goodNameTextField.setText("");
                infoTextArea.setText("");
            } else if (fileContentTextArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        MyApplication.this,
                        "Open file or add info first!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                goodNameTextField.setText("");
                infoTextArea.setText("");
            } else {
                List<String> exportCountries = new ArrayList<>(List.of());
                int exportVolume = 0;
                boolean isFound = false;
                for (Map.Entry<String, List<Object[]>> entry : fileContentMap.entrySet()) {
                    if (entry.getKey().equals(goodNameTextField.getText())) {
                        isFound = true;
                        for (int i = 0; i < entry.getValue().size(); i++) {
                            exportCountries.add((String) entry.getValue().get(i)[0]);
                            exportVolume += (Integer) entry.getValue().get(i)[1];
                        }
                    }
                }
                if (!isFound) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this,
                            "There is no good with the provided name!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                    goodNameTextField.setText("");
                    infoTextArea.setText("");
                } else {
                    String countriesString = String.join(", ", exportCountries);
                    infoTextArea.setText("import countries: " + countriesString + "\n" + "total exports: " + exportVolume);
                }
            }
        });

        showInfoButtonStreamApi.addActionListener(actionEvent -> {
            if (goodNameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        MyApplication.this,
                        "Good's name field is empty!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                goodNameTextField.setText("");
                infoTextArea.setText("");
            } else if (fileContentTextArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        MyApplication.this,
                        "Open file or add info first!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                goodNameTextField.setText("");
                infoTextArea.setText("");
            } else {
                try {
                    List<String> exportCountries = new ArrayList<>();
                    int exportVolume = fileContentMap.entrySet().stream() //TODO with Stream API
                            .filter(entry -> entry.getKey().equals(goodNameTextField.getText()))
                            .flatMap(entry -> entry.getValue().stream()) //flattens the list of values for each matching entry into a single stream of values.
                            .peek(value -> exportCountries.add((String) value[0]))
                            .mapToInt(value -> (Integer) value[1])
                            .sum();
                    if (exportCountries.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                MyApplication.this,
                                "There is no good with the provided name!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE
                        );
                        goodNameTextField.setText("");
                        infoTextArea.setText("");
                    } else {
                        String countriesString = String.join(", ", exportCountries);
                        infoTextArea.setText("import countries: " + countriesString + "\n" + "total exports: " + exportVolume);
                    }
                } catch (ClassCastException | NullPointerException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error while casting the data: " + e.getMessage(),
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        addInfoButton.addActionListener(actionEvent -> {
                AddWindow addWindow = new AddWindow("Add info", fileContentTextArea, fileContentMap);
                addWindow.setMinimumSize(new Dimension(500, 200));
                addWindow.setVisible(true);
        });

        clearButton.addActionListener(actionEvent -> {
            fileContentTextArea.setText("");
            infoTextArea.setText("");
            fileContentMap.clear();
    });

        infoTextArea = new JTextArea(6, 15);
        infoTextArea.setFont(largerFont);
        infoTextArea.setEditable(false);
        infoTextArea.setForeground(Color.BLACK);
        JScrollPane scrollPaneInfo = new JScrollPane(infoTextArea);

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(largerFont);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(largerFont);
        openItem.addActionListener(actionEvent -> FileParse.openFile(fileContentTextArea, fileContentMap, importCountries, goodsForExport));

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        this.add(scrollPaneFileContent, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.gridy = 7;
        this.add(addInfoButton, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridy = 8;
        this.add(clearButton, gbc);
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        this.add(goodNameLabel, gbc);
        gbc.gridx = 1;
        this.add(goodNameTextField, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        this.add(showInfoButton, gbc);
        gbc.gridx = 1;
        this.add(showInfoButtonStreamApi, gbc);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        this.add(scrollPaneInfo, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
    }
}
