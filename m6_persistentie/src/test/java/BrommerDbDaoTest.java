import be.kdg.data.Data;
import be.kdg.model.Brommer;
import be.kdg.model.Brommers;
import org.junit.jupiter.api.*;
import persist.BrommerDbDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BrommerDbDaoTest {
    BrommerDbDao dao;
    @BeforeAll
    public void beforeAll() {
        dao = new BrommerDbDao("testdb/brommersdb");
    }

    @BeforeEach
    public void beforeEach() {
        Data brommerData = new Data();
        brommerData.getData().forEach(dao::insert);
    }

    @AfterEach
    public void afterEach() {
        dao.delete("*");
    }

    @Test
    public void testInsert() {
        Data brommerData = new Data();
        assertEquals(brommerData.getData().size(), dao.sortedOnGewicht().size(), "Het aantal rijen klopt niet");
    }

    @Test
    public void testRetrieveUpdate() {
        Brommer brommerToUpdate = dao.retrieve("KMHTC6AD9DU186351");
        assertNotNull(brommerToUpdate, "Brommer niet gevonden");

        brommerToUpdate.setModel("Iets compleet anders");
        dao.update(brommerToUpdate);

        Brommer updatedBrommer = dao.retrieve("KMHTC6AD9DU186351");
        assertEquals(brommerToUpdate, updatedBrommer, "Brommer niet geupdated");

    }

    @Test
    public void testDelete() {
        int sizeBeforeDelete = dao.sortedOnGewicht().size();
        dao.delete("KMHTC6AD9DU186351");

        int sizeAfterDelete = dao.sortedOnGewicht().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete, "Brommer niet verwijderd");
        assertFalse(dao.delete("KMHTC6AD9DU186351"), "Oeps, de brommer kan 2x verwijderd worden");
    }

    @Test
    public void testSort() {
        // Sorted from data class
        Data brommerData = new Data();
        Brommers brommerList = new Brommers();

        brommerData.getData().forEach(brommerList::add);

        List<Brommer> sortedOnGewichtMemory = brommerList.sortedOnGewicht();

        // Now, get database sorted list
        List<Brommer> sortedOnGewichtDb = dao.sortedOnGewicht();

        assertAll(
                () -> assertEquals(sortedOnGewichtMemory.get(1), sortedOnGewichtDb.get(1), "De sortering klopt niet"),
                () -> assertEquals(sortedOnGewichtMemory.get(2), sortedOnGewichtDb.get(2), "De sortering klopt niet"),
                () -> assertEquals(sortedOnGewichtMemory.get(3), sortedOnGewichtDb.get(3), "De sortering klopt niet")
                );
    }

    @AfterAll
    public void afterAll() {
        dao.close();
    }
}

