package util;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWork {

    public static File openXMLFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public static void saveToXML(JTextArea infoTextArea) {
        try {
            String[] lines = infoTextArea.getText().split("\n");
            if (lines.length < 2) {
                JOptionPane.showMessageDialog(null, "Invalid data format in infoTextArea", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String importCountries = lines[0].replace("import countries: ", "").trim();
            String exportVolume = lines[1].replace("total exports: ", "").trim();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("ExportData");
            document.appendChild(rootElement);

            Element importCountriesElement = document.createElement("ImportCountries");
            importCountriesElement.appendChild(document.createTextNode(importCountries));
            rootElement.appendChild(importCountriesElement);

            Element exportVolumeElement = document.createElement("ExportVolume");
            exportVolumeElement.appendChild(document.createTextNode(exportVolume));
            rootElement.appendChild(exportVolumeElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("info.xml"));

            transformer.transform(domSource, streamResult);

            JOptionPane.showMessageDialog(null, "Data saved to XML file successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving data to XML: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
