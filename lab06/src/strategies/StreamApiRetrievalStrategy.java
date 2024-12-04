package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.InfoRetrieval.formatInfo;

public class StreamApiRetrievalStrategy implements InfoRetrievalStrategy {
    @Override
    public String getInfo(String goodName, Map<String, List<Object[]>> fileContentMap) {
        List<String> exportCountries = new ArrayList<>();
        int exportVolume = fileContentMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(goodName))
                .flatMap(entry -> entry.getValue().stream())
                .peek(value -> exportCountries.add((String) value[0]))
                .mapToInt(value -> (Integer) value[1])
                .sum();

        return formatInfo(exportCountries, exportVolume);
    }
}
