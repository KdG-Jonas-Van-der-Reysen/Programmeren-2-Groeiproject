import be.kdg.data.Data;
import be.kdg.model.Brommers;
import parsing.BrommersStaxParser;

import javax.xml.stream.XMLStreamException;

public class Demo_7 {
    public static void main(String[] args) throws XMLStreamException {
        System.out.println("Demo_7");
        Brommers brommers = new Brommers();
        Data.getData().forEach(brommers::add);

        BrommersStaxParser staxParser = new BrommersStaxParser(brommers, "datafiles/brommers.xml");
        staxParser.staxWriteXML();
    }
}
