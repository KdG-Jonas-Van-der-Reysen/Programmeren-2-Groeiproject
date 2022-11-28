package be.kdg.model;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BrommerTest {
    private Brommer brommer1;
    private Brommer brommer2;
    @BeforeEach
    void setUp() {
        brommer1 = new Brommer("Sym Orbit 3", "3LNHM26T77R671336", 2300.12, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25));
        brommer2 = new Brommer("Segway E125S", "2MELM75W7RX637925", 112, 5, BrommerKlasse.B, LocalDate.of(2015, 7, 23), LocalDate.of(2021, 9, 1));
    }
    @Test
    public void testEquals() {
        // Make sure brommer 1 is not equal to brommer 2
        assertNotEquals(brommer1, brommer2, "Brommer 1 is gelijk aan brommer 2. Dit mag niet.");
        Brommer brommer3 = new Brommer("Sym orbit 200","3LNHM26T77R671336", 2190, 3, BrommerKlasse.B, LocalDate.of(2016, 7, 12), LocalDate.of(2018, 3, 12));
        assertEquals(brommer1, brommer3, "De chassisnummers van beide brommers zijn niet gelijk: " + brommer1.getChassisNummer() + " en " + brommer3.getChassisNummer());
    }

    @Test
    public void testIllegalModel() {
        assertThrows(IllegalArgumentException.class, () -> new Brommer("", "3LNHM26T77R671336", 2300, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25)), "IllegalArgumentException wordt niet gegooid bij een lege modelnaam");
    }

    @Test
    public void testLegalModel() {
        assertDoesNotThrow(() -> new Brommer("Sym Orbit 3", "3LNHM26T77R671336", 2300, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25)), "IllegalArgumentException wordt gegooid bij een correcte modelnaam");
    }

    @Test
    public void testCompareTo() {
        Brommer brommer3 = new Brommer("Sym orbit 200","3LNHM26T77R671336", 2190, 3, BrommerKlasse.B, LocalDate.of(2016, 7, 12), LocalDate.of(2018, 3, 12));

        assertEquals(1, brommer1.compareTo(brommer2), "Brommer 1 is niet groter dan brommer 2, dat kan niet.");
        assertEquals(0, brommer1.compareTo(brommer3), "De compareTo methode geeft een verkeerde waarde terug op brommer 1 en brommer 3");

    }

    @Test
    public void testGewicht() {
        assertEquals(2300.12, brommer1.getGewicht(), 0.01, "Het gewicht van brommer 1 is niet correct");
    }
}