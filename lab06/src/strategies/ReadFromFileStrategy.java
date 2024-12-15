package strategies;

import models.GoodsForExport;
import models.ImportCountries;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface ReadFromFileStrategy {
    void readFile(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport);
}
