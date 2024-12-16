package strategies;

import models.GoodsForExport;
import models.ImportCountries;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.FileParse;
import util.XmlWork;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ReadFromFileStrategyDOM implements ReadFromFileStrategy {
    @Override
    public void readFile(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport) {
        try {
            File inputFile = XmlWork.openXMLFile();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("record");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    String good = element.getElementsByTagName("good").item(0).getTextContent();
                    String volume = element.getElementsByTagName("volume").item(0).getTextContent();
                    fileContentTextArea.append(good + ", " + country + ", " + volume + "\n");
                }
            }

            FileParse.fillMapStreamApi(fileContentTextArea, fileContentMap, importCountries, goodsForExport);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Reading file error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}