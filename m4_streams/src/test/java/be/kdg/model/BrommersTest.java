package be.kdg.model;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrommersTest {
    private Brommer brommer1;
    private Brommer brommer2;
    private Brommer brommer3;
    private Brommer brommer4;
    private Brommer brommer5;

    private Brommers brommers;

    @BeforeEach
    public void beforeEach() {
        brommer1 = new Brommer("Segway E125S", "3GYFK66N83G231247", 3599, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2022, 8, 22));
        brommer2 = new Brommer("Segway E110SE", "1B3CC4FB6AN100405", 2499, 2, BrommerKlasse.B, LocalDate.of(2003, 5, 17), LocalDate.of(2022, 3, 21));
        brommer3 = new Brommer("Segway E110S", "2G4WS52J421173448", 2299, 1, BrommerKlasse.B, LocalDate.of(1927, 9, 29), LocalDate.of(2022, 5, 20));
        brommer4 = new Brommer("Segway B110S", "KMHTC6AD9DU186351", 1779, 0, BrommerKlasse.B, LocalDate.of(2013, 3, 18), LocalDate.of(2013, 4, 25));
        brommer5 = new Brommer("Brekr Model B", "1FTJW36F6TEB28864", 4749, 3, BrommerKlasse.B, LocalDate.of(2016, 5, 7), LocalDate.of(2015, 7, 25));

        brommers = new Brommers();
        brommers.add(brommer1);
        brommers.add(brommer2);
        brommers.add(brommer3);
        brommers.add(brommer4);
        brommers.add(brommer5);
    }

    @Test
    public void testAdd() {
        // Er worden al brommers toegevoegd in de beforeEach methode, dus hier moet dat niet meer.
        assertEquals(5, brommers.getSize(), "Het aantal brommers moet 5 zijn");

        // Probeer een dubbele toe te voegen
        brommers.add(brommer1);

        // Er mag maar 1 van beide brommers in de lijst zitten, dus de grootte moet nog steeds 5 zijn.
        assertEquals(5, brommers.getSize(), "Het aantal brommers moet 5 zijn. Dubbele toevoegen mag niet.");
    }

    @Test
    public void testRemove() {
        int sizeBeforeRemoval = brommers.getSize();
        brommers.remove(brommer1);

        assertEquals(4, brommers.getSize(), "Het aantal brommers moet 1 minder zijn na verwijderen, en dat is niet het geval.");

        // Probeer een brommer te verwijderen die niet in de lijst zit
        brommers.remove(new Brommer("Segway E125S", "3GYFK66N83G231FKEJLD247", 3599, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2022, 8, 22)));

        // De grootte moet nog steeds hetzelfde zijn, want de brommer die we proberen te verwijderen zit niet in de lijst.
        assertEquals(4, brommers.getSize(), "Het aantal brommers moet nog steeds 5 zijn, want de brommer die we proberen te verwijderen zit niet in de lijst.");
    }

    @Test
    public void testSortedOnGewicht() {
        List<Brommer> gesorteerdeBrommers = brommers.sortedOnGewicht();
        assertAll(
                () -> assertEquals(brommer4, gesorteerdeBrommers.get(0), "De eerste brommer in de gesorteerde lijst moet brommer3 zijn."),
                () -> assertEquals(brommer3, gesorteerdeBrommers.get(1), "De tweede brommer in de gesorteerde lijst moet brommer3 zijn."),
                () -> assertEquals(brommer2, gesorteerdeBrommers.get(2), "De derde brommer in de gesorteerde lijst moet brommer2 zijn."),
                () -> assertEquals(brommer1, gesorteerdeBrommers.get(3), "De vierde brommer in de gesorteerde lijst moet brommer1 zijn."),
                () -> assertEquals(brommer5, gesorteerdeBrommers.get(4), "De vijfde brommer in de gesorteerde lijst moet brommer5 zijn.")
        );
    }

    @Test
    public void testSortedOnLaatsteOnderhoud() {
        List<Brommer> gesorteerdeBrommers = brommers.sortedOnLaatsteOnderhoud();

        // Make a new array with the brommers in the order we expect them to be in.
        Brommer[] expected = new Brommer[]{brommer4, brommer5, brommer2, brommer3, brommer1};

        // Use assertArrayEquals to compare the expected array with the actual array.
        assertArrayEquals(expected, gesorteerdeBrommers.toArray(), "De op laatste onderhoud gesorteerde lijst is niet correct.");
    }
}