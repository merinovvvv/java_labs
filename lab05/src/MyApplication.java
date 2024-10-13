import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.awt.event.*;

public class MyApplication extends JFrame {
    public static void main(String[] args) {
        MyApplication myApp = new MyApplication("Series");
        myApp.setMinimumSize(new Dimension(400, 700));
        myApp.setVisible(true);
    }

    LineBorder greenBorder = new LineBorder(Color.GREEN, 2);

    Series series;
    JLabel firstElemLabel;
    JTextField firstElemTextField;
    JLabel stepLabel;
    JTextField stepTextField;
    JLabel numOfElementsLabel;
    JTextField numOfElementsTextField;
    JButton calcSumButton;
    JRadioButton linerRadioButton;
    JRadioButton exponentialRadioButton;
    JLabel jElemTextLabel;
    JComboBox<Integer> jElemComboBox;
    JButton calcJElemButton;
    JTextField resSumTextField;
    JTextField resJElemTextField;
    JLabel fileNameLabel;
    JTextField fileNameTextField;
    JButton writeSumToFileButton;
    JButton writeJElemToFileButton;
    JTextArea fileContentsTextArea;
    JButton fileContentsButton;
    JButton showSeriesButton;
    JTextArea showSeriesTextArea;
    JButton writeSeriesToFileButton;
    JButton clearFileButton;

    MyApplication(String str) {
        super(str);

        UIManager.put("OptionPane.messageFont", new Font("Dialog", Font.BOLD, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Dialog", Font.PLAIN, 16));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        // Set the background color of the content pane
        this.getContentPane().setBackground(Color.BLACK);


        this.setLayout(new GridBagLayout());
        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        JLabel label = new JLabel();
        Font defaultFont = label.getFont();
        System.out.println("Default Font: " + defaultFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // margins for elements in the grid layout (top, left, bottom, right) 5px
        gbc.fill = GridBagConstraints.HORIZONTAL; // elements are stretched horizontally

        firstElemLabel = new JLabel("first element: ");
        firstElemLabel.setForeground(Color.GREEN);
        firstElemLabel.setFont(largerFont);
        firstElemTextField = new JTextField(10);
        firstElemTextField.setFont(largerFont);
        stepLabel = new JLabel("step: ");
        stepLabel.setForeground(Color.GREEN);
        stepLabel.setFont(largerFont);
        stepTextField = new JTextField(10);
        stepTextField.setFont(largerFont);
        numOfElementsLabel = new JLabel("number of elements: ");
        numOfElementsLabel.setForeground(Color.GREEN);
        numOfElementsLabel.setFont(largerFont);
        numOfElementsTextField = new JTextField(10);
        numOfElementsTextField.setFont(largerFont);
        numOfElementsLabel.setFont(largerFont);
        linerRadioButton = new JRadioButton("Linear");
        linerRadioButton.setBackground(null);
        linerRadioButton.setForeground(Color.WHITE);
        linerRadioButton.setFocusPainted(false);
        linerRadioButton.setFont(largerFont);
        exponentialRadioButton = new JRadioButton("Exponential");
        exponentialRadioButton.setBackground(null);
        exponentialRadioButton.setForeground(Color.WHITE);
        exponentialRadioButton.setFocusPainted(false);
        exponentialRadioButton.setFont(largerFont);
        resSumTextField = new JTextField("sum result", 10);
        resSumTextField.setEnabled(false);
        resSumTextField.setFont(largerFont);
        calcSumButton = new JButton("calculate sum");
        calcSumButton.setFocusPainted(false);
        calcSumButton.setBorder(greenBorder);
        calcSumButton.setFont(largerFont);
        clearFileButton = new JButton("clear file");
        clearFileButton.setFocusPainted(false);
        clearFileButton.setBorder(greenBorder);
        clearFileButton.setFont(largerFont);

        calcSumButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                calcSumButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                calcSumButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        clearFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                clearFileButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                clearFileButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        jElemTextLabel = new JLabel("input j index:");
        jElemTextLabel.setForeground(Color.GREEN);
        jElemTextLabel.setFont(largerFont);
        jElemComboBox = new JComboBox<>();
        jElemComboBox.setFont(largerFont);
        calcJElemButton = new JButton("calculate j element");
        calcJElemButton.setFocusPainted(false);
        calcJElemButton.setBorder(greenBorder);
        calcJElemButton.setFont(largerFont);

        //TODO hide focus on warnings and errors buttons

        calcJElemButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                calcJElemButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                calcJElemButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        resJElemTextField = new JTextField("j element result", 10);
        resJElemTextField.setEnabled(false);
        resJElemTextField.setFont(largerFont);
        fileNameLabel = new JLabel("input file name:");
        fileNameLabel.setForeground(Color.GREEN);
        fileNameLabel.setFont(largerFont);
        fileNameTextField = new JTextField(10);
        fileNameTextField.setFont(largerFont);
        writeSumToFileButton = new JButton("write sum to file");
        writeSumToFileButton.setFocusPainted(false);
        writeSumToFileButton.setBorder(greenBorder);
        writeSumToFileButton.setFont(largerFont);

        writeSumToFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                writeSumToFileButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                writeSumToFileButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        writeJElemToFileButton = new JButton("write j element to file");
        writeJElemToFileButton.setFocusPainted(false);
        writeJElemToFileButton.setBorder(greenBorder);
        writeJElemToFileButton.setFont(largerFont);

        writeJElemToFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                writeJElemToFileButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                writeJElemToFileButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        fileContentsButton = new JButton("show file contents");
        fileContentsButton.setFocusPainted(false);
        fileContentsButton.setBorder(greenBorder);
        fileContentsButton.setFont(largerFont);

        fileContentsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                fileContentsButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                fileContentsButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        fileContentsTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(fileContentsTextArea);
        fileContentsButton.setHorizontalAlignment(SwingConstants.CENTER);
        fileContentsTextArea.setEnabled(false);
        fileContentsTextArea.setFont(largerFont);
        showSeriesButton = new JButton("create series");
        fileContentsButton.setHorizontalAlignment(SwingConstants.CENTER);
        showSeriesButton.setFocusPainted(false);
        showSeriesButton.setBorder(greenBorder);
        showSeriesButton.setFont(largerFont);

        showSeriesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                showSeriesButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                showSeriesButton.setBackground(UIManager.getColor("Button.background"));
            }
        });

        showSeriesTextArea = new JTextArea(2, 20);
        showSeriesTextArea.setLineWrap(false);
        showSeriesTextArea.setWrapStyleWord(false);
        showSeriesTextArea.setEnabled(false);
        showSeriesTextArea.setFont(largerFont);
        JScrollPane showSeriesScrollPane = new JScrollPane(showSeriesTextArea);
//        showSeriesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        showSeriesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        writeSeriesToFileButton = new JButton("write series to file");
        writeSeriesToFileButton.setFocusPainted(false);
        writeSeriesToFileButton.setBorder(greenBorder);
        writeSeriesToFileButton.setFont(largerFont);

        writeSeriesToFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                writeSeriesToFileButton.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                writeSeriesToFileButton.setBackground(UIManager.getColor("Button.background"));
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(firstElemLabel, gbc);
        gbc.gridx = 1;
        this.add(firstElemTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(stepLabel, gbc);
        gbc.gridx = 1;
        this.add(stepTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(numOfElementsLabel, gbc);
        gbc.gridx = 1;
        this.add(numOfElementsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(linerRadioButton, gbc);
        gbc.gridx = 1;
        this.add(exponentialRadioButton, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 4;


        this.add(showSeriesButton, gbc);
        //gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(showSeriesScrollPane, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(calcSumButton, gbc);
        gbc.gridx = 1;
        this.add(resSumTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(jElemTextLabel, gbc);
        gbc.gridx = 1;
        this.add(jElemComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(calcJElemButton, gbc);
        gbc.gridx = 1;
        this.add(resJElemTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(fileNameLabel, gbc);
        gbc.gridx = 1;
        this.add(fileNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        this.add(writeSumToFileButton, gbc);
        gbc.gridx = 1;
        this.add(writeJElemToFileButton, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 11;
        this.add(writeSeriesToFileButton, gbc);

        gbc.gridy = 12;
        this.add(fileContentsButton, gbc);

        gbc.gridy = 13;
        this.add(scrollPane, gbc);

        gbc.gridy = 18;
        this.add(clearFileButton, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;


        linerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (linerRadioButton.isSelected()) {
                    exponentialRadioButton.setSelected(false);
                }
            }
        });

        exponentialRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (exponentialRadioButton.isSelected()) {
                    linerRadioButton.setSelected(false);
                }
            }
        });

        clearFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileNameTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                MyApplication.this,
                                "File name is not entered!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE
                        );
                    } else {
                        series.clearFile(fileNameTextField.getText());
                        fileContentsTextArea.setText("");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this,
                            "An error occurred while clearing the file. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        numOfElementsTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent focusEvent) {
                try {
                    int numOfElements = Integer.parseInt(numOfElementsTextField.getText());
                    fillJComboBox(numOfElements);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "Please enter a valid number of elements.", // Message to display
                            "Invalid Input", // Title of the dialog
                            JOptionPane.WARNING_MESSAGE // Type of message
                    );
                    numOfElementsTextField.setText("");
                    fillJComboBox(0);

                }
            }
        });

        showSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonsExceptionWork();
                if (series != null) {
                    showSeriesTextArea.setText(series.toString());
                    //fileContentsTextArea.setCaretPosition(0);
                }
            }
        });

        calcSumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                buttonsExceptionWork();

                if (series != null) {
                    try {
                        resSumTextField.setText(String.valueOf(series.sumOfSeries()));
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(
                                MyApplication.this, // Parent component
                                e.getMessage(), // Message to display
                                "Warning", // Title of the dialog
                                JOptionPane.WARNING_MESSAGE // Type of message
                        );
                    }
                }
            }
        });

        fileContentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fileContentsTextArea.setText("");
                    if (fileNameTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                MyApplication.this, // Parent component
                                "File name is not entered!", // Message to display
                                "Warning", // Title of the dialog
                                JOptionPane.WARNING_MESSAGE // Type of message
                        );
                    } else {
                        fileContentsTextArea.setText(Series.readFile(fileNameTextField.getText()));
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "An error occurred while reading the file. Please try again.", // Message to display
                            "Error", // Title of the dialog
                            JOptionPane.ERROR_MESSAGE // Type of message
                    );
                }
            }
        });

        writeSumToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (series != null) {
                        series.writeSumToFile(fileNameTextField.getText());
                    } else {
                        JOptionPane.showMessageDialog(
                                MyApplication.this, // Parent component
                                "Series is not initialized!", // Message to display
                                "Warning", // Title of the dialog
                                JOptionPane.WARNING_MESSAGE // Type of message
                        );
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "An error occurred while writing to the file. Please try again.", // Message to display
                            "Error", // Title of the dialog
                            JOptionPane.ERROR_MESSAGE // Type of message
                    );
                }
            }
        });

        writeSeriesToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (series != null) {
                        series.writeToFile(fileNameTextField.getText());
                    } else {
                        JOptionPane.showMessageDialog(
                                MyApplication.this, // Parent component
                                "Series is not initialized!", // Message to display
                                "Warning", // Title of the dialog
                                JOptionPane.WARNING_MESSAGE // Type of message
                        );
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "An error occurred while writing to the file. Please try again.", // Message to display
                            "Error", // Title of the dialog
                            JOptionPane.ERROR_MESSAGE // Type of message
                    );
                }
            }
        });

        writeJElemToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer selectedIndex = (Integer) jElemComboBox.getSelectedItem();
                    if (selectedIndex == null) {
                        JOptionPane.showMessageDialog(
                                MyApplication.this, // Parent component
                                "Index is not selected", // Message to display
                                "Warning", // Title of the dialog
                                JOptionPane.WARNING_MESSAGE // Type of message
                        );
                    } else {
                        if (series != null) {
                            series.writeJElemToFile(fileNameTextField.getText(), selectedIndex);
                        } else {
                            JOptionPane.showMessageDialog(
                                    MyApplication.this, // Parent component
                                    "Series is not initialized!", // Message to display
                                    "Warning", // Title of the dialog
                                    JOptionPane.WARNING_MESSAGE // Type of message
                            );
                        }
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "An error occurred while writing to the file. Please try again.", // Message to display
                            "Error", // Title of the dialog
                            JOptionPane.ERROR_MESSAGE // Type of message
                    );
                }
            }
        });

        calcJElemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                Integer selectedIndex = (Integer) jElemComboBox.getSelectedItem();
                if (selectedIndex == null) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "Index is not selected", // Message to display
                            "Warning", // Title of the dialog
                            JOptionPane.WARNING_MESSAGE // Type of message
                    );

                }

                buttonsExceptionWork();

                    if (series != null) {
                        try {
                            if (selectedIndex != null) {
                                resJElemTextField.setText(String.valueOf(series.jElemCalc(selectedIndex)));
                            }
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(
                                    MyApplication.this, // Parent component
                                    e.getMessage(), // Message to display
                                    "Warning", // Title of the dialog
                                    JOptionPane.WARNING_MESSAGE // Type of message
                            );
                        }
                    }
                }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void fillJComboBox(int numOfElements) {
        jElemComboBox.removeAllItems();
        for (int i = 1; i <= numOfElements; i++) {
            jElemComboBox.addItem(i);
        }
    }

    private void buttonsExceptionWork() {
        if (firstElemTextField.getText().isEmpty() || stepTextField.getText().isEmpty() || numOfElementsTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    MyApplication.this, // Parent component
                    "At least one of the fields \"first number\", \"step\" or \"number of elements\" is empty!", // Message to display
                    "Warning", // Title of the dialog
                    JOptionPane.WARNING_MESSAGE // Type of message
            );
        } else if (!linerRadioButton.isSelected() && !exponentialRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(
                    MyApplication.this, // Parent component
                    "Series is not initialized!", // Message to display
                    "Warning", // Title of the dialog
                    JOptionPane.WARNING_MESSAGE // Type of message
            );
        } else {
            if (linerRadioButton.isSelected()) {
                try {
                    series = new Linear(Double.parseDouble(firstElemTextField.getText()), Double.parseDouble(stepTextField.getText()), Integer.parseInt(numOfElementsTextField.getText()));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "At least one of the fields \"first number\", \"step\" or \"number of elements\" has invalid type!", // Message to display
                            "Warning", // Title of the dialog
                            JOptionPane.WARNING_MESSAGE // Type of message
                    );
                }
            } else {
                try {
                    series = new Exponential(Double.parseDouble(firstElemTextField.getText()), Double.parseDouble(stepTextField.getText()), Integer.parseInt(numOfElementsTextField.getText()));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            e.getMessage(), // Message to display
                            "Warning", // Title of the dialog
                            JOptionPane.WARNING_MESSAGE // Type of message
                    );
                }

            }
        }
    }
}