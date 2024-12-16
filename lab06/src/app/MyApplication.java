package app;

import models.GoodsForExport;
import models.ImportCountries;
import strategies.*;
import util.FileParse;
import util.XmlWork;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MyApplication extends JFrame {

    Map<String, List<Object[]>> fileContentMap;
    JTextArea fileContentTextArea;
    JLabel goodNameLabel;
    JTextField goodNameTextField;
    JButton showInfoButton;
    JButton showInfoButtonStreamApi;
    JButton addInfoButton;
    JButton openFileSaxButton;
    JButton openFileDomButton;
    JButton saveButton;
    JButton clearButton;
    JTextArea infoTextArea;
    JMenuBar menuBar;

    ImportCountries importCountries;
    GoodsForExport goodsForExport;

    InfoRetrievalStrategy infoRetrievalStrategy;
    ReadFromFileStrategy readFromFileStrategy;

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

        openFileSaxButton = new JButton("open file SAX");
        openFileSaxButton.setFont(largerFont);

        openFileDomButton = new JButton("open file DOM");
        openFileDomButton.setFont(largerFont);

        saveButton = new JButton("save info");
        saveButton.setFont(largerFont);

        openFileSaxButton.addActionListener(_ -> {
            readFromFileStrategy = new ReadFromFileStrategySAX();
            readFileCommon();
        });

        openFileDomButton.addActionListener(_ -> {
            readFromFileStrategy = new ReadFromFileStrategyDOM();
            readFileCommon();
        });

        showInfoButton.addActionListener(_ -> {
            infoRetrievalStrategy = new LoopRetrievalStrategy();
            setShowInfoButtonCommon();
        });

        showInfoButtonStreamApi.addActionListener(_ -> {
            infoRetrievalStrategy = new StreamApiRetrievalStrategy();
            setShowInfoButtonCommon();
        });

        addInfoButton.addActionListener(_ -> {
                AddWindow addWindow = new AddWindow("Add info", fileContentTextArea, fileContentMap);
                addWindow.setMinimumSize(new Dimension(500, 200));
                addWindow.setVisible(true);
        });

        saveButton.addActionListener(_ -> XmlWork.saveToXML(infoTextArea));

        clearButton.addActionListener(_ -> {
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
        openItem.addActionListener(_ -> FileParse.openFile(fileContentTextArea, fileContentMap, importCountries, goodsForExport));

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(openFileSaxButton, gbc);
        gbc.gridx = 1;
        this.add(openFileDomButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        this.add(scrollPaneFileContent, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.gridy = 8;
        this.add(addInfoButton, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridy = 9;
        this.add(clearButton, gbc);

        gbc.gridy = 10;
        gbc.gridwidth = 1;
        this.add(goodNameLabel, gbc);
        gbc.gridx = 1;
        this.add(goodNameTextField, gbc);

        gbc.gridy = 11;
        gbc.gridx = 0;
        this.add(showInfoButton, gbc);
        gbc.gridx = 1;
        this.add(showInfoButtonStreamApi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        this.add(scrollPaneInfo, gbc);
        gbc.gridheight = 1;

        gbc.gridy = 18;
        this.add(saveButton, gbc);
    }

    private void readFileCommon() {
        readFromFileStrategy.readFile(fileContentTextArea, fileContentMap, importCountries, goodsForExport);
    }

    private void setShowInfoButtonCommon() {
        try {
            String goodName = goodNameTextField.getText();
            if (goodName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MyApplication.this,
                        "Good's name field is empty!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                goodNameTextField.setText("");
                infoTextArea.setText("");
                return;
            }

            if (fileContentMap.isEmpty()) {
                JOptionPane.showMessageDialog(
                        MyApplication.this,
                        "Open file or add info first!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                goodNameTextField.setText("");
                infoTextArea.setText("");
                return;
            }

            String result = infoRetrievalStrategy.getInfo(goodName, fileContentMap);
            infoTextArea.setText(result);
        } catch (ClassCastException | NullPointerException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error while casting the data: " + e.getMessage(),
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
