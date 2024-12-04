package strategies;

import java.util.List;
import java.util.Map;

public interface InfoRetrievalStrategy {
    String getInfo(String goodName, Map<String, List<Object[]>> fileContentMap);
}
