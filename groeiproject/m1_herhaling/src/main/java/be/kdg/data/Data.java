package be.kdg.data;

import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public List<Brommer> getData() {
        ArrayList<Brommer> brommers = new ArrayList<>();

        // 15 fictieve verschillende brommers

        // Segway e125s

        /* Random VINs
        * 3GYFK66N83G231247
        * 1B3CC4FB6AN100405
        * 2G4WS52J421173448
        * KMHTC6AD9DU186351
        * 1FTJW36F6TEB28864
        * 1FTRW07602KE58452
        * 1GDGG31V141997219
        * JM1BK12F541168422
        * WBXPC93408WJ88698
        * 3LNHM26T77R671336
        * 1J4GW48SX2C184675
        * 5FNYF48589B069185
        * 4A4MN21S06E055275
        * 1GCGG25C081104356
        * 2GCEC13VX71140064
        * 1FAFP25195G101531
         * */

        /* Brommers
        PRODUCT;elektrisch;bruin;kunststof;Segway E125S;3599;elektrischeBrommers/E125S-thumb.png;NULL;1",
    "PRODUCT;elektrisch;zwart;kunststof;Segway E110SE;2499;elektrischeBrommers/E110SE-thumb.png;NULL;1",
    "PRODUCT;elektrisch;zwart;kunstleer;Segway E110S;2299;elektrischeBrommers/E110S-thumb.png;NULL;1",
    "PRODUCT;elektrisch;grijs;kunstleer;Segway B110S;1779;elektrischeBrommers/B110S-thumb.png;NULL;1",
    "PRODUCT;elektrisch;zwart;leer;Brekr Model B;4749;elektrischeBrommers/brekrModelB-thumb.png;NULL;1",
    "PRODUCT;elektrisch;zwart;kunstleer;Horwin EK1;2999;elektrischeBrommers/horwinEK1-thumb.png;NULL;0",
    "PRODUCT;elektrisch;zwart;kunststof;NIU NQi Sport;3374;elektrischeBrommers/NiuNQiSport-thumb.png;NULL;0",
    "PRODUCT;elektrisch;bruin;kunststof;Super Soco CUX;2998;elektrischeBrommers/SuperSocoCUX-thumb.png;NULL;0"

    "PRODUCT;benzine;zwart;leer;Sym Orbit 2;2100;benzineBrommers/orbit2-thumb.png;NULL;0",
    "PRODUCT;benzine;zwart;leer;Sym Orbit 3;2300;benzineBrommers/orbit3-thumb.png;NULL;0",
    "PRODUCT;benzine;grijs;kunststof;AGM VX50I;1775;benzineBrommers/agmVX50I-thumb.png;NULL;0",
    "PRODUCT;benzine;zwart;kunstleer;Peugeot Kisbee Active;2298;benzineBrommers/peugeotKisbeeActive-thumb.png;Peugeot Kisbee Act.;0",
    "PRODUCT;benzine;zwart;kunstleer;Peugeot Kisbee Streetline;2448;benzineBrommers/peugotKisbeeStreetline-thumb.png;Peugeot Kisbee Strt.;0",
    "PRODUCT;benzine;bruin;leer;Peugeot Django;3398;benzineBrommers/peugotDjango-thumb.png;NULL;0",
    "PRODUCT;benzine;zwart;kunstleer;Piaggio Liberty 50 S;2858;benzineBrommers/piaggioLiberty50S-thumb.png;NULL;0",
         */

        // Model, chassisNummer (random from list above), gewicht (from data above), aantalKeerOnderhoud (random number), klasse (from data above), relaseDate (from data above, 0 = A, 1 = B), laatsteOnderhoud (random date in the past)
        brommers.add(new Brommer("Segway E125S", "3GYFK66N83G231247", 3599, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Segway E110SE", "1B3CC4FB6AN100405", 2499, 2, BrommerKlasse.B, LocalDate.of(2003, 5, 17), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Segway E110S", "2G4WS52J421173448", 2299, 1, BrommerKlasse.B, LocalDate.of(1927, 9, 29), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Segway B110S", "KMHTC6AD9DU186351", 1779, 0, BrommerKlasse.B, LocalDate.of(2013, 3, 18), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Brekr Model B", "1FTJW36F6TEB28864", 4749, 3, BrommerKlasse.B, LocalDate.of(2016, 5, 7), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Horwin EK1", "1FTRW07602KE58452", 2999, 2, BrommerKlasse.A, LocalDate.of(2018, 2, 9), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("NIU NQi Sport", "1GDGG31V141997219", 3374, 1, BrommerKlasse.A, LocalDate.of(2007, 1, 16), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Super Soco CUX", "JM1BK12F541168422", 2998, 0, BrommerKlasse.A, LocalDate.of(2004, 7, 17), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Sym Orbit 2", "WBXPC93408WJ88698", 2100, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Sym Orbit 3", "3LNHM26T77R671336", 2300, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("AGM VX50I", "1J4GW48SX2C184675", 1775, 1, BrommerKlasse.B, LocalDate.of(1927, 9, 29), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Peugeot Kisbee Active", "5FNYF48589B069185", 2298, 0, BrommerKlasse.B, LocalDate.of(2013, 3, 18), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Peugeot Kisbee Streetline", "4A4MN21S06E055275", 2448, 3, BrommerKlasse.A, LocalDate.of(2016, 5, 7), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Peugeot Django", "2GCEC13VX71140064", 3398, 2, BrommerKlasse.B, LocalDate.of(2018, 2, 9), LocalDate.of(2022, 8, 25)));
        brommers.add(new Brommer("Piaggio Liberty 50 S", "1FAFP25195G101531", 2858, 1, BrommerKlasse.B, LocalDate.of(2007, 1, 16), LocalDate.of(2022, 8, 25)));

        return brommers;
    }
}
