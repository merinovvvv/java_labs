package app;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddWindow extends JFrame {

    JLabel goodLabel;
    JTextField goodNameTextField;
    JLabel countryLabel;
    JTextField countryNameTextField;
    JLabel exportVolumeLabel;
    JTextField exportVolumeTextField;
    JButton addButton;
    boolean goodHasText;
    boolean countryHasText;
    boolean exportHasText;

    AddWindow(String string, JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap) {
        super(string);
        this.getContentPane().setBackground(Color.PINK);
        this.setLayout(new GridBagLayout());

        goodHasText = false;
        countryHasText = false;
        exportHasText = false;

        UIManager.put("OptionPane.messageFont", new Font("Dialog", Font.BOLD, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Dialog", Font.PLAIN, 16));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        goodLabel = new JLabel("good's name:");
        goodLabel.setFont(largerFont);

        goodNameTextField = new JTextField(15);
        goodNameTextField.setFont(largerFont);

        countryLabel = new JLabel("country name:");
        countryLabel.setFont(largerFont);

        countryNameTextField = new JTextField(15);
        countryNameTextField.setFont(largerFont);

        exportVolumeLabel = new JLabel("export volume:");
        exportVolumeLabel.setFont(largerFont);

        exportVolumeTextField = new JTextField(15);
        exportVolumeTextField.setFont(largerFont);

        addButton = new JButton("add info");
        addButton.setFont(largerFont);
        addButton.setEnabled(false);

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        this.add(goodLabel, gbc);
        gbc.gridx = 1;
        this.add(goodNameTextField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        this.add(countryLabel, gbc);
        gbc.gridx = 1;
        this.add(countryNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(exportVolumeLabel, gbc);
        gbc.gridx = 1;
        this.add(exportVolumeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 3;
        this.add(addButton, gbc);

        goodNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            private void updateState() {
                goodHasText = !goodNameTextField.getText().isEmpty();
                checkAndEnableButton();
            }
        });

        countryNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            private void updateState() {
                countryHasText = !countryNameTextField.getText().isEmpty();
                checkAndEnableButton();
            }
        });

        exportVolumeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                updateState();
            }

            private void updateState() {
                exportHasText = !exportVolumeTextField.getText().isEmpty();
                checkAndEnableButton();
            }
        });

        addButton.addActionListener(_ -> {
            try {
                fileContentMap.computeIfAbsent(goodNameTextField.getText(), _ -> new ArrayList<>())
                        .add(new Object[]{countryNameTextField.getText(), Integer.parseInt(exportVolumeTextField.getText())});
                fileContentTextArea.append(goodNameTextField.getText() + ", " + countryNameTextField.getText() +
                            ", " + exportVolumeTextField.getText() + "\n");
                dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Inappropriate type in 'export volume' field.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                exportVolumeTextField.setText("");
            }
        });
    }

    private void checkAndEnableButton() {
        addButton.setEnabled(goodHasText && countryHasText && exportHasText);
    }
}
