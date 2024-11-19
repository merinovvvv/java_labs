import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PupilApp extends JFrame {

    JMenuBar menuBar;
    JTextArea fileDisplayArea;

    JButton sortByAscendingEducationalInstitution;

    //JLabel educationalInstitutionLabel;
    JTextField educationalInstitutionTextField;
    JButton numOfPupilsInEducationalInstitutionButton;
    JTextArea resultTextArea;

    //TODO binarySearch
    JLabel searchLabel;
    JTextField searchTextField;
    JButton binarySearchButton;

    JButton sortByAscendingEducationalPupilRatingAndEducationalInstitutionRating;
    JButton pupilsWithBSurname;
    JTextArea nameWithBSurnameTextArea;

    List<Pupil> pupils;


    PupilApp(String string) {
        super(string);

        this.getContentPane().setBackground(Color.PINK);
        this.setLayout(new GridBagLayout());

        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // margins for elements in the grid layout (top, left, bottom, right) 5px
        gbc.fill = GridBagConstraints.HORIZONTAL; // elements are stretched horizontally

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pupils = new ArrayList<>();

        fileDisplayArea = new JTextArea(6, 15);
        fileDisplayArea.setFont(largerFont);
        fileDisplayArea.setForeground(Color.BLACK);
        fileDisplayArea.setEditable(false);
        JScrollPane scrollPaneFileContent = new JScrollPane(fileDisplayArea);

        sortByAscendingEducationalInstitution = new JButton("sort by ascending institution");
        sortByAscendingEducationalInstitution.setFont(largerFont);

        sortByAscendingEducationalPupilRatingAndEducationalInstitutionRating = new JButton("sort by ascending rating/institution rating");
        sortByAscendingEducationalPupilRatingAndEducationalInstitutionRating.setFont(largerFont);

        searchLabel = new JLabel("input the item to search:");
        searchLabel.setFont(largerFont);

        searchTextField = new JTextField();
        searchTextField.setFont(largerFont);

        binarySearchButton = new JButton("binary search");
        binarySearchButton.setFont(largerFont);

        resultTextArea = new JTextArea(6, 10);
        resultTextArea.setFont(largerFont);
        resultTextArea.setForeground(Color.BLACK);
        resultTextArea.setEditable(false);
        JScrollPane scrollPaneResultArea = new JScrollPane(resultTextArea);

        numOfPupilsInEducationalInstitutionButton = new JButton("number of pupils");
        numOfPupilsInEducationalInstitutionButton.setFont(largerFont);

        pupilsWithBSurname = new JButton("pupils which names start with 'B'");
        pupilsWithBSurname.setFont(largerFont);

        nameWithBSurnameTextArea = new JTextArea(6, 15);
        nameWithBSurnameTextArea.setFont(largerFont);
        nameWithBSurnameTextArea.setForeground(Color.BLACK);
        nameWithBSurnameTextArea.setEditable(false);
        JScrollPane scrollPaneBArea = new JScrollPane(nameWithBSurnameTextArea);

//        educationalInstitutionLabel = new JLabel("input ed. institution's name:");
//        educationalInstitutionLabel.setFont(largerFont);

        educationalInstitutionTextField = new JTextField("input ed. institution's name:");
        educationalInstitutionTextField.setFont(largerFont);
        educationalInstitutionTextField.setPreferredSize(new Dimension(200, 30));

        sortByAscendingEducationalInstitution.addActionListener(actionEvent -> {
            List<Pupil> sortedPupils = PupilUtils.sortByInstitutionAndSurname(pupils);
            resultTextArea.setText("");
            sortedPupils.forEach(pupil -> resultTextArea.append(pupil.toString() + "\n"));
        });

        numOfPupilsInEducationalInstitutionButton.addActionListener(actionEvent -> {
            String institution = educationalInstitutionTextField.getText();
            long count = PupilUtils.countPupilsInInstitution(pupils, institution);
            resultTextArea.setText("Number of pupils in " + institution + ": " + count);
        });

        binarySearchButton.addActionListener(actionEvent -> {
            String searchText = searchTextField.getText();
            // Assuming searchText is a comma-separated string of Pupil fields
            String[] parts = searchText.split(", ");
            if (parts.length == 5) {
                try {
                    String type = parts[0];
                    String surname = parts[1];
                    String educationalInstitution = parts[2];
                    double educationalInstitutionRating = Double.parseDouble(parts[3]);
                    double avgGrade = Double.parseDouble(parts[4]);
                    Pupil target = null;
                    if (type.equalsIgnoreCase("Student")) {
                        int courseNumber = Integer.parseInt(parts[5]);
                        target = new Student(surname, educationalInstitution, educationalInstitutionRating, avgGrade, courseNumber);
                    } else if (type.equalsIgnoreCase("Schoolchild")) {
                        int grade = Integer.parseInt(parts[5]);
                        int behaviour = Integer.parseInt(parts[6]);
                        target = new Schoolchild(surname, educationalInstitution, educationalInstitutionRating, avgGrade, grade, behaviour);
                    }
                    Optional<Pupil> result = PupilUtils.binarySearch(pupils, target);
                    resultTextArea.setText(result.map(Pupil::toString).orElse("Pupil not found"));
                } catch (NumberFormatException e) {
                    resultTextArea.setText("Error parsing search text");
                }
            } else {
                resultTextArea.setText("Invalid search text format");
            }
        });

        sortByAscendingEducationalPupilRatingAndEducationalInstitutionRating.addActionListener(actionEvent -> {
            List<Pupil> sortedPupils = PupilUtils.sortByRatingRatio(pupils);
            resultTextArea.setText("");
            sortedPupils.forEach(pupil -> resultTextArea.append(pupil.toString() + "\n"));
        });

        pupilsWithBSurname.addActionListener(actionEvent -> {
            List<String> surnames = PupilUtils.getSurnamesStartingWithB(pupils);
            nameWithBSurnameTextArea.setText("");
            surnames.forEach(surname -> nameWithBSurnameTextArea.append(surname + "\n"));
        });

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(largerFont);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(largerFont);
        openItem.addActionListener(actionEvent -> openFile(pupils));

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 6;
        this.add(scrollPaneFileContent, gbc);

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 7;
        this.add(sortByAscendingEducationalInstitution, gbc);
        gbc.gridy = 8;
        //this.add(educationalInstitutionLabel, gbc);

        this.add(educationalInstitutionTextField, gbc);
        gbc.gridy = 9;
        this.add(numOfPupilsInEducationalInstitutionButton, gbc);
        gbc.gridy = 10;
        this.add(sortByAscendingEducationalPupilRatingAndEducationalInstitutionRating, gbc);
        gbc.gridx = 0;
        gbc.gridy = 11;
        this.add(searchLabel, gbc);
        gbc.gridy = 12;
        gbc.gridx = 0;
        this.add(searchTextField, gbc);
        gbc.gridy = 13;
        this.add(binarySearchButton, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.gridheight = 8;
        gbc.gridwidth = 3;
        this.add(scrollPaneResultArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.add(pupilsWithBSurname, gbc);
        gbc.gridy = 15;
        this.add(nameWithBSurnameTextArea, gbc);
    }

    private void openFile(List<Pupil> pupils) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                if (pupils == null) {
                    throw new IllegalArgumentException("Lamp list cannot be null");
                }
                pupils.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    Pupil pupil = parsePupil(line);
                    if (pupil == null) {
                        throw new IllegalArgumentException("Error parsing lamp from line: " + line);
                    }
                    pupils.add(pupil);
                    fileDisplayArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    private Pupil parsePupil(String line) {
        String[] parts = line.split(", ");
        try {
            String type = parts[0];
            String surname = parts[1];
            String educationalInstitution = parts[2];
            double educationalInstitutionRating = Double.parseDouble(parts[3]);
            double avgGrade = Double.parseDouble(parts[4]);
            if (type.equalsIgnoreCase("Student") && parts.length == 6) {
                int courseNumber = Integer.parseInt(parts[5]);
                return new Student(surname, educationalInstitution, educationalInstitutionRating, avgGrade, courseNumber);
            } else if (type.equalsIgnoreCase("Schoolchild") && parts.length == 7) {
                int grade = Integer.parseInt(parts[5]);
                int behaviour = Integer.parseInt(parts[6]);
                return new Schoolchild(surname, educationalInstitution, educationalInstitutionRating, avgGrade, grade, behaviour);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error parsing line: " + line);
        }
        return null;
    }
}
