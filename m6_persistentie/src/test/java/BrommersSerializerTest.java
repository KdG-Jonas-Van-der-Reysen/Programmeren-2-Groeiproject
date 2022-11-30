import be.kdg.data.Data;
import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;
import be.kdg.model.Brommers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persist.BrommersSerializer;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrommersSerializerTest {
    private Brommer brommer1;
    private Brommer brommer2;
    private Brommer brommer3;
    private Brommer brommer4;
    private Brommer brommer5;

    private Brommers brommers;

    @BeforeEach
    public void beforeEach() {
        brommers = new Brommers();

        List<Brommer> brommerList = Data.getData();
        brommerList.forEach(brommers::add);
    }

    @Test
    public void testSerialize() {
        BrommersSerializer brommersSerializer = new BrommersSerializer("brommers.ser");
        assertDoesNotThrow(() -> brommersSerializer.serialize(brommers), "Serialize should not throw an exception");
    }

    @Test
    public void testDeserialize() {
        BrommersSerializer brommersSerializer = new BrommersSerializer("brommers.ser");
        assertDoesNotThrow(() -> {
            Brommers brommersFromFile = brommersSerializer.deserialize();
            assertEquals(brommers, brommersFromFile, "Brommers should be equal before and after serialization");
        });
        assertNotNull(brommers, "Deserialize should not return null");
    }
}
