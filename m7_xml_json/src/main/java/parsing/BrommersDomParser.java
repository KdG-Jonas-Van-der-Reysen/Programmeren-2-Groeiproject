package parsing;

import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;
import be.kdg.model.Brommers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class BrommersDomParser {

    private static String getPropertyValue(Element e, String propertyName ) {
        return ((Element) e.getElementsByTagName(propertyName).item(0)).getTextContent();
    }

    public static Brommers domReadXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Brommers brommers = new Brommers();

        // Beetje random stuff om een DOM te maken
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(fileName));
        Element rootElement = doc.getDocumentElement();

        // Alle brommers ophalen
        NodeList brommersList = rootElement.getChildNodes();
        for (int i = 0; i < brommersList.getLength(); i++) {
            // Per brommer de attributen ophalen
            if (brommersList.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            Element e = (Element) brommersList.item(i);

            brommers.add(new Brommer(
                    e.getAttribute("model"),
                    getPropertyValue(e, "chassis-nummer"),
                    Double.parseDouble(getPropertyValue(e, "gewicht")),
                    Integer.parseInt(getPropertyValue(e, "aantal-keer-onderhoud")),
                    BrommerKlasse.valueOf(getPropertyValue(e, "klasse")),
                    LocalDate.parse(getPropertyValue(e, "release-date")),
                    LocalDate.parse(getPropertyValue(e, "laatste-onderhoud"))
            ));
        }

        return brommers;
    }
}
