import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyApplication extends JFrame {
    public static void main(String[] args) {
        MyApplication myApp = new MyApplication("Series");
        myApp.setSize(400, 500);
        myApp.setMinimumSize(new Dimension(400, 500));
        myApp.setVisible(true);
    }

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

    MyApplication(String str) {
        super(str);

        // Set the background color of the content pane
        this.getContentPane().setBackground(Color.BLACK);


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // margins for elements in the grid layout (top, left, bottom, right) 5px
        gbc.fill = GridBagConstraints.HORIZONTAL; // elements are stretched horizontally

        firstElemLabel = new JLabel("first element: ");
        firstElemLabel.setForeground(Color.GREEN);
        firstElemTextField = new JTextField(10);
        stepLabel = new JLabel("step: ");
        stepLabel.setForeground(Color.GREEN);
        stepTextField = new JTextField(10);
        numOfElementsLabel = new JLabel("number of elements: ");
        numOfElementsLabel.setForeground(Color.GREEN);
        numOfElementsTextField = new JTextField(10);
        linerRadioButton = new JRadioButton("Linear");
        linerRadioButton.setBackground(null);
        linerRadioButton.setForeground(Color.WHITE);
        exponentialRadioButton = new JRadioButton("Exponential");
        exponentialRadioButton.setBackground(null);
        exponentialRadioButton.setForeground(Color.WHITE);
        resSumTextField = new JTextField("sum result", 10);
        resSumTextField.setEnabled(false);
        calcSumButton = new JButton("calculate sum");
        jElemTextLabel = new JLabel("input j index:");
        jElemTextLabel.setForeground(Color.GREEN);
        jElemComboBox = new JComboBox<>();
        calcJElemButton = new JButton("calculate j element");
        resJElemTextField = new JTextField("j element result", 10);
        resJElemTextField.setEnabled(false);
        fileNameLabel = new JLabel("input file name:");
        fileNameLabel.setForeground(Color.GREEN);
        fileNameTextField = new JTextField(10);
        writeSumToFileButton = new JButton("write sum to file");
        writeJElemToFileButton = new JButton("write j element to file");
        fileContentsButton = new JButton("show file contents");
        fileContentsTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(fileContentsTextArea);
        fileContentsButton.setHorizontalAlignment(SwingConstants.CENTER);
        fileContentsTextArea.setEnabled(false);

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

        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(calcSumButton, gbc);
        gbc.gridx = 1;
        this.add(resSumTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(jElemTextLabel, gbc);
        gbc.gridx = 1;
        this.add(jElemComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(calcJElemButton, gbc);
        gbc.gridx = 1;
        this.add(resJElemTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(fileNameLabel, gbc);
        gbc.gridx = 1;
        this.add(fileNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(writeSumToFileButton, gbc);
        gbc.gridx = 1;
        this.add(writeJElemToFileButton, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(fileContentsButton, gbc);

        gbc.gridy = 10;
        this.add(scrollPane, gbc);

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

        numOfElementsTextField.addActionListener(new ActionListener() { //TODO working not only with Enter button
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int numOfElements = Integer.parseInt(numOfElementsTextField.getText());
                    fillJComboBox(numOfElements);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            MyApplication.this, // Parent component
                            "Please enter a valid number of elements.", // Message to display
                            "Invalid Input", // Title of the dialog
                            JOptionPane.ERROR_MESSAGE // Type of message
                    );
                }
            }
        });

        calcSumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


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
                                    e.getMessage(), // Message to display
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
            }
        });

        //TODO jElemCalcButton, writeSumToFileButton, writeJElemToFileButton, fileContentsButton
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    private void nullTheElements() {
//        firstElemTextField.setText("");
//        stepTextField.setText("");
//        numOfElementsTextField.setText("");
//        resSumTextField.setText("sum result");
//        resJElemTextField.setText("j element result");
//        fileNameTextField.setText("");
//        fileContentsTextArea.setText("");
//        linerRadioButton.setSelected(false);
//        exponentialRadioButton.setSelected(false);
//    }

    private void fillJComboBox(int numOfElements) {
        jElemComboBox.removeAllItems();
        for (int i = 1; i <= numOfElements; i++) {
            jElemComboBox.addItem(i);
        }
    }
}