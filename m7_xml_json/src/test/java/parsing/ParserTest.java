package parsing;

import be.kdg.data.Data;
import be.kdg.model.Brommer;
import be.kdg.model.Brommers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Brommers brommers;
    @BeforeEach
    public void beforeEach() {
        brommers = new Brommers();
        Data.getData().forEach(brommers::add);
    }

    @Test
    public void testStaxDom() {
        BrommersStaxParser staxParser = new BrommersStaxParser(brommers, "datafiles/brommers.xml");
        assertDoesNotThrow(() -> staxParser.staxWriteXML());

        Brommers readFromXml = null;
        try {
            readFromXml = BrommersDomParser.domReadXML("datafiles/brommers.xml");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        List<Brommer> brommersList = brommers.getBrommers().stream().toList();
        List<Brommer> readFromXmlList = readFromXml.getBrommers().stream().toList();

        assertAll(
            () -> assertEquals(brommersList.get(0), readFromXmlList.get(0), "De brommers moeten gelijk zijn"),
            () -> assertEquals(brommersList.get(2), readFromXmlList.get(2), "De brommers moeten gelijk zijn"),
            () -> assertEquals(brommersList.get(5), readFromXmlList.get(5), "De brommers moeten gelijk zijn")
        );
    }

    @Test
    public void testJaxb() {
        BrommerssJaxbParser.JaxbWriteXml("datafiles/brommersJaxb.xml", brommers);
        Brommers readFromXml = BrommerssJaxbParser.JaxbReadXml("datafiles/brommersJaxb.xml", Brommers.class);

        List<Brommer> brommersList = brommers.getBrommers().stream().toList();
        List<Brommer> readFromXmlList = readFromXml.getBrommers().stream().toList();

        assertAll(
                () -> assertEquals(brommersList.get(0), readFromXmlList.get(0), "De brommers moeten gelijk zijn"),
                () -> assertEquals(brommersList.get(2), readFromXmlList.get(2), "De brommers moeten gelijk zijn"),
                () -> assertEquals(brommersList.get(5), readFromXmlList.get(5), "De brommers moeten gelijk zijn")
        );
    }

    @Test
    public void testGson() {
        BrommersGsonParser.writeJson(brommers, "datafiles/brommersGson.json");
        Brommers readFromJson = BrommersGsonParser.readJson("datafiles/brommersGson.json");

        List<Brommer> brommersList = brommers.getBrommers().stream().toList();
        List<Brommer> readFromXmlList = readFromJson.getBrommers().stream().toList();

        assertAll(
                () -> assertEquals(brommersList.get(0), readFromXmlList.get(0), "De brommers moeten gelijk zijn"),
                () -> assertEquals(brommersList.get(2), readFromXmlList.get(2), "De brommers moeten gelijk zijn"),
                () -> assertEquals(brommersList.get(5), readFromXmlList.get(5), "De brommers moeten gelijk zijn")
        );
    }
}