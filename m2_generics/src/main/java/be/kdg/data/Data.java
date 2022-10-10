package be.kdg.data;

import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Brommer> getData() {
        ArrayList<Brommer> brommers = new ArrayList<>();

        // Model, chassisNummer (random from list above), gewicht (from data above), aantalKeerOnderhoud (random number), klasse (from data above), relaseDate (from data above, 0 = A, 1 = B), laatsteOnderhoud (random date in the past)
        brommers.add(new Brommer("Segway E125S", "3GYFK66N83G231247", 3599, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2022, 8, 22)));
        brommers.add(new Brommer("Segway E110SE", "1B3CC4FB6AN100405", 2499, 2, BrommerKlasse.B, LocalDate.of(2003, 5, 17), LocalDate.of(2022, 3, 21)));
        brommers.add(new Brommer("Segway E110S", "2G4WS52J421173448", 2299, 1, BrommerKlasse.B, LocalDate.of(1927, 9, 29), LocalDate.of(2022, 5, 20)));
        brommers.add(new Brommer("Segway B110S", "KMHTC6AD9DU186351", 1779, 0, BrommerKlasse.B, LocalDate.of(2013, 3, 18), LocalDate.of(2013, 4, 25)));
        brommers.add(new Brommer("Brekr Model B", "1FTJW36F6TEB28864", 4749, 3, BrommerKlasse.B, LocalDate.of(2016, 5, 7), LocalDate.of(2015, 7, 25)));
        brommers.add(new Brommer("Horwin EK1", "1FTRW07602KE58452", 2999, 2, BrommerKlasse.A, LocalDate.of(2018, 2, 9), LocalDate.of(1946, 6, 25)));
        brommers.add(new Brommer("NIU NQi Sport", "1GDGG31V141997219", 3374, 1, BrommerKlasse.A, LocalDate.of(2007, 1, 16), LocalDate.of(1965, 9, 25)));
        brommers.add(new Brommer("Super Soco CUX", "JM1BK12F541168422", 2998, 0, BrommerKlasse.A, LocalDate.of(2004, 7, 17), LocalDate.of(2018, 8, 25)));
        brommers.add(new Brommer("Sym Orbit 2", "WBXPC93408WJ88698", 2100, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2010, 12, 25)));
        brommers.add(new Brommer("Sym Orbit 3", "3LNHM26T77R671336", 2300, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25)));
        brommers.add(new Brommer("AGM VX50I", "1J4GW48SX2C184675", 1775, 1, BrommerKlasse.B, LocalDate.of(1927, 9, 29), LocalDate.of(1999, 3, 25)));
        brommers.add(new Brommer("Peugeot Kisbee Active", "5FNYF48589B069185", 2298, 0, BrommerKlasse.B, LocalDate.of(2013, 3, 18), LocalDate.of(1944, 1, 25)));
        brommers.add(new Brommer("Peugeot Kisbee Streetline", "4A4MN21S06E055275", 2448, 3, BrommerKlasse.A, LocalDate.of(2016, 5, 7), LocalDate.of(1984, 2, 25)));
        brommers.add(new Brommer("Peugeot Django", "2GCEC13VX71140064", 3398, 2, BrommerKlasse.B, LocalDate.of(2018, 2, 9), LocalDate.of(2019, 4, 25)));
        brommers.add(new Brommer("Piaggio Liberty 50 S", "1FAFP25195G101531", 2858, 1, BrommerKlasse.B, LocalDate.of(2007, 1, 16), LocalDate.of(1987, 8, 25)));

        return brommers;
    }
}
