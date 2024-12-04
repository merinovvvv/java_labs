package models;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ImportCountries {
    private final Set<String> importCountries;

    public ImportCountries() {
        importCountries = new HashSet<>();
        importCountries.add("USA");
        importCountries.add("Canada");
        importCountries.add("Mexico");
        importCountries.add("Russia");
        importCountries.add("Belarus");
        importCountries.add("Germany");
        importCountries.add("Italy");
        importCountries.add("France");
        importCountries.add("Spain");
        importCountries.add("Kazakhstan");
        importCountries.add("China");
        importCountries.add("Uzbekistan");
        importCountries.add("Japan");
        importCountries.add("India");
        importCountries.add("Pakistan");
        importCountries.add("Hong Kong");
        importCountries.add("South Korea");
        importCountries.add("Australia");
        importCountries.add("New Zealand");
        importCountries.add("Poland");
        importCountries.add("Ukraine");
        importCountries.add("Czech Republic");
        importCountries.add("Slovakia");
        importCountries.add("Hungary");
        importCountries.add("Romania");
        importCountries.add("Bulgaria");
        importCountries.add("Greece");
        importCountries.add("Turkey");
        importCountries.add("Egypt");
        importCountries.add("South Africa");
        importCountries.add("Brazil");
        importCountries.add("Argentina");
        importCountries.add("Chile");
        importCountries.add("Peru");
        importCountries.add("Colombia");
        importCountries.add("Venezuela");
        importCountries.add("Ecuador");
        importCountries.add("Bolivia");
        importCountries.add("Paraguay");
        importCountries.add("Uruguay");
    }

    Set<String> getImportCountries() {
        return importCountries;
    }

    void addCountry(String country) {
        importCountries.add(country);
    }

    public static void countrySetCheck(String[] parts, JTextArea fileContentTextArea, ImportCountries importCountries) {

        boolean isInCountrySet = importCountries.getImportCountries().stream()
                .anyMatch(country -> parts[1].equals(country));

        if (!isInCountrySet) {
            Object[] options = {"YES", "NO"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "The file contains a country '" + parts[1] + "' that doesn't exist or is not in 'import countries'.\nDo you want to add a country?",
                    "country question",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == JOptionPane.YES_OPTION) {
                importCountries.addCountry(parts[1]);
            } else if (choice == JOptionPane.NO_OPTION) {
                fileContentTextArea.setText("");
            }
        }
    }
}
