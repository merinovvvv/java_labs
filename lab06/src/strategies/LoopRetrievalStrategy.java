package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.InfoRetrieval.formatInfo;

public class LoopRetrievalStrategy implements InfoRetrievalStrategy {
    @Override
    public String getInfo(String goodName, Map<String, List<Object[]>> fileContentMap) {
        List<String> exportCountries = new ArrayList<>();
        int exportVolume = 0;

        for (Map.Entry<String, List<Object[]>> entry : fileContentMap.entrySet()) {
            if (entry.getKey().equals(goodName)) {
                for (Object[] data : entry.getValue()) {
                    exportCountries.add((String) data[0]);
                    exportVolume += (Integer) data[1];
                }
            }
        }

        return formatInfo(exportCountries, exportVolume);
    }
}