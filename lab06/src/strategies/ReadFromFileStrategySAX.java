package strategies;

import models.GoodsForExport;
import models.ImportCountries;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import util.FileParse;
import util.XmlWork;

import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ReadFromFileStrategySAX implements ReadFromFileStrategy {
    @Override
    public void readFile(JTextArea fileContentTextArea, Map<String, List<Object[]>> fileContentMap, ImportCountries importCountries, GoodsForExport goodsForExport) {
        File file = XmlWork.openXMLFile();
        if (file == null) {
            return;
        }
        String filename = file.getName();
        DefaultHandler handler = new DefaultHandler() {
            boolean isCountry = false;
            boolean isGood = false;
            boolean isVolume = false;
            String country = "";
            String good = "";
            String volume = "";

            @Override
            public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) throws SAXException {
                if (qualifiedName.equalsIgnoreCase("country")) {
                    isCountry = true;
                } else if (qualifiedName.equalsIgnoreCase("good")) {
                    isGood = true;
                } else if (qualifiedName.equalsIgnoreCase("volume")) {
                    isVolume = true;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (isCountry) {
                    country = new String(ch, start, length);
                    isCountry = false;
                } else if (isGood) {
                    good = new String(ch, start, length);
                    isGood = false;
                } else if (isVolume) {
                    volume = new String(ch, start, length);
                    isVolume = false;
                }
            }

            @Override
            public void endElement(String uri, String localName, String qualifiedName) throws SAXException {
                if (qualifiedName.equalsIgnoreCase("record")) {
                    fileContentTextArea.append(good + ", " + country + ", " + volume + "\n");
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(filename, handler);

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