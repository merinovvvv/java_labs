package util;

import java.util.List;

public class InfoRetrieval {
    public static String formatInfo(List<String> exportCountries, int exportVolume) {
        if (exportCountries.isEmpty()) {
            return "There is no good with the provided name!";
        }
        String countriesString = String.join(", ", exportCountries);
        return "import countries: " + countriesString + "\n" + "total exports: " + exportVolume;
    }
}