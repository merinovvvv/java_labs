import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JButton closeFileButton;
    JTextArea infoTextArea;

    MyApplication(String string) {
        super(string);
        this.getContentPane().setBackground(Color.PINK);
        this.setLayout(new GridBagLayout());

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

        closeFileButton = new JButton("close file");
        closeFileButton.setFont(largerFont);

        goodNameLabel = new JLabel("good's name: ");
        goodNameLabel.setFont(largerFont);
        goodNameTextField = new JTextField(15);
        goodNameTextField.setFont(largerFont);
        showInfoButton = new JButton("show info");
        showInfoButton.setFont(largerFont);

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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
                            "Open file first!",
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
            }
        });

        closeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    fileContentTextArea.setText("");
        }});

        infoTextArea = new JTextArea(6, 15);
        infoTextArea.setFont(largerFont);
        infoTextArea.setEditable(false);
        infoTextArea.setForeground(Color.BLACK);
        JScrollPane scrollPaneInfo = new JScrollPane(infoTextArea);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(largerFont);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(largerFont);
        openItem.addActionListener(actionEvent -> FileParse.openFile(this));

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        this.add(scrollPaneFileContent, gbc);
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridy = 7;
        this.add(closeFileButton, gbc);
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        this.add(goodNameLabel, gbc);
        gbc.gridx = 1;
        this.add(goodNameTextField, gbc);
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        this.add(showInfoButton, gbc);
        gbc.gridy = 10;
        gbc.gridheight = 6;
        this.add(scrollPaneInfo, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
    }
}
