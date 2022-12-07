package parsing;

import be.kdg.model.Brommer;
import be.kdg.model.Brommers;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BrommersStaxParser {
    private Brommers brommers;
    private XMLStreamWriter streamWriter;

    public BrommersStaxParser(Brommers brommers, String xmlBestand) {
        this.brommers = brommers;

        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(xmlBestand)));

            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(printWriter);

            this.streamWriter = new IndentingXMLStreamWriter(streamWriter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public void staxWriteXML() throws XMLStreamException {
        streamWriter.writeStartDocument();
        streamWriter.writeStartElement("brommers");

        this.brommers.getBrommers().forEach(this::writeElement);

        streamWriter.writeEndElement();
        streamWriter.writeEndDocument();

        streamWriter.flush();
        streamWriter.close();
    }

    private void writeElement(Brommer brommer) {
        try {
            streamWriter.writeStartElement("brommer");
            streamWriter.writeAttribute("model", brommer.getModel());

            // chassisNummer
            streamWriter.writeStartElement("chassis-nummer");
            streamWriter.writeCharacters(brommer.getChassisNummer());
            streamWriter.writeEndElement();

            // gewicht
            streamWriter.writeStartElement("gewicht");
            streamWriter.writeCharacters(Double.toString(brommer.getGewicht()));
            streamWriter.writeEndElement();

            // aantalKeerOnderhoud
            streamWriter.writeStartElement("aantal-keer-onderhoud");
            streamWriter.writeCharacters(Integer.toString(brommer.getAantalKeerOnderhoud()));
            streamWriter.writeEndElement();

            // enum klasse
            streamWriter.writeStartElement("klasse");
            streamWriter.writeCharacters(brommer.getKlasse().toString());
            streamWriter.writeEndElement();

            // releaseDate
            streamWriter.writeStartElement("release-date");
            streamWriter.writeCharacters(brommer.getReleaseDate().toString());
            streamWriter.writeEndElement();

            // laatsteOnderhoud
            streamWriter.writeStartElement("laatste-onderhoud");
            streamWriter.writeCharacters(brommer.getLaatsteOnderhoud().toString());
            streamWriter.writeEndElement();

            streamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
