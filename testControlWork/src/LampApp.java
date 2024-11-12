import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LampApp extends JFrame {

    JMenuBar menuBar;
    JTextArea fileDisplayArea;

    JButton sortByAscendingPriceButton;
    JButton sortByDescendingPriceAndPowerButton;
    JButton producersWithCButton;
    JTextArea resultDisplayArea;

    JLabel producerLabel;
    JTextField producerTextField;
    JButton calcAvgButton;
    JTextField avgPriceDisplayTextField;

    List<Lamp> lampList;


    LampApp(String string) {
        super(string);

        this.getContentPane().setBackground(Color.PINK);
        this.setLayout(new GridBagLayout());

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // margins for elements in the grid layout (top, left, bottom, right) 5px
        gbc.fill = GridBagConstraints.HORIZONTAL; // elements are stretched horizontally

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lampList = new ArrayList<>();

        fileDisplayArea = new JTextArea(6, 15);
        fileDisplayArea.setFont(largerFont);
        fileDisplayArea.setForeground(Color.BLACK);
        fileDisplayArea.setEditable(false);
        JScrollPane scrollPaneFileContent = new JScrollPane(fileDisplayArea);

        sortByAscendingPriceButton = new JButton("sort by ascending price");
        sortByAscendingPriceButton.setFont(largerFont);

        sortByDescendingPriceAndPowerButton = new JButton("sort by descending price/power");
        sortByDescendingPriceAndPowerButton.setFont(largerFont);

        producersWithCButton = new JButton("producers which names start with 'C'");
        producersWithCButton.setFont(largerFont);

        resultDisplayArea = new JTextArea(6, 10);
        resultDisplayArea.setFont(largerFont);
        resultDisplayArea.setForeground(Color.BLACK);
        resultDisplayArea.setEditable(false);
        JScrollPane scrollPaneResultArea = new JScrollPane(resultDisplayArea);

        producerLabel = new JLabel("input producer's name:");
        producerLabel.setFont(largerFont);

        producerTextField = new JTextField();
        producerTextField.setFont(largerFont);
        producerTextField.setPreferredSize(new Dimension(200, 30));

        calcAvgButton = new JButton("average producer's price");
        calcAvgButton.setFont(largerFont);

        avgPriceDisplayTextField = new JTextField();
        avgPriceDisplayTextField.setFont(largerFont);

        sortByAscendingPriceButton.addActionListener(actionEvent -> {
            List<Lamp> sortedLamps = LampUtils.sortByAscendingPrice(lampList);
            resultDisplayArea.setText("");
            sortedLamps.forEach(lamp -> resultDisplayArea.append(lamp.toString() + "\n"));
        });

        sortByDescendingPriceAndPowerButton.addActionListener(actionEvent -> {
            List<Lamp> sortedLamps = LampUtils.sortByDescendingPricePowerRatio(lampList);
            resultDisplayArea.setText("");
            sortedLamps.forEach(lamp -> resultDisplayArea.append(lamp.toString() + "\n"));
        });

        producersWithCButton.addActionListener(actionEvent -> {
            List<String> producers = LampUtils.getProducersStartingWithC(lampList);
            resultDisplayArea.setText("");
            producers.forEach(producer -> resultDisplayArea.append(producer + "\n"));
        });

        calcAvgButton.addActionListener(actionEvent -> {
            String producer = producerTextField.getText();
            double avgPrice = LampUtils.calculateAveragePriceByProducer(lampList, producer);
            avgPriceDisplayTextField.setText(String.valueOf(avgPrice));
        });

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(largerFont);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(largerFont);
        openItem.addActionListener(actionEvent -> openFile(lampList));

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 6;
        this.add(scrollPaneFileContent, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 7;
        this.add(sortByAscendingPriceButton, gbc);
        gbc.gridy = 8;
        this.add(sortByDescendingPriceAndPowerButton, gbc);
        gbc.gridy = 9;
        this.add(producersWithCButton, gbc);

        gbc.gridy = 7;
        gbc.gridx = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 3;
        this.add(scrollPaneResultArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(producerLabel, gbc);
        gbc.gridx = 1;
        this.add(producerTextField, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 11;
        this.add(calcAvgButton, gbc);

        gbc.gridy = 12;
        gbc.gridwidth = 2;
        this.add(avgPriceDisplayTextField, gbc);
    }

    private void openFile(List<Lamp> lampList) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                if (lampList == null) {
                    throw new IllegalArgumentException("Lamp list cannot be null");
                }
                lampList.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    Lamp lamp = parseLamp(line);
                    if (lamp == null) {
                        throw new IllegalArgumentException("Error parsing lamp from line: " + line);
                    }
                    lampList.add(lamp);
                    fileDisplayArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    private Lamp parseLamp(String line) {
        String[] parts = line.split(", ");
        if (parts.length == 5) {
            try {
                String type = parts[0];
                String producer = parts[1];
                int price = Integer.parseInt(parts[2]);
                double power = Double.parseDouble(parts[3]);
                int timeOrLedNum = Integer.parseInt(parts[4]);
                if (type.equalsIgnoreCase("LED")) {
                    return new LedLamp(producer, power, price, timeOrLedNum);
                } else if (type.equalsIgnoreCase("Incandescent")) {
                    return new IncandescentLamp(producer, power, price, timeOrLedNum);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error parsing line: " + line);
            }
        }
        return null;
    }
}
