package be.kdg.model;

import java.time.LocalDate;

public class BrommerFactory {
    private BrommerFactory() {
    }

    public static Brommer newEmptyBrommer() {
        return new Brommer();
    }

    public static Brommer newFilledBrommer(String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate releaseDate, LocalDate laatsteOnderhoud) {
        return new Brommer(model, chassisNummer, gewicht, aantalKeerOnderhoud, klasse, releaseDate, laatsteOnderhoud);
    }

    public static Brommer newRandomBrommer() {
        // Random string generator
        String randomString = java.util.UUID.randomUUID().toString().substring(0, 8);
        return new Brommer(
                RandomStringGenerator.generateRandomString(10),
                RandomStringGenerator.generateRandomString(10),
                RandomNumberGenerator.generateRandomDouble(100, 200),
                RandomNumberGenerator.generateRandomInt(0, 10),
                RandomEnumGenerator.generateRandomEnum(BrommerKlasse.class),
                RandomDateGenerator.generateRandomDate(2000, 2020),
                RandomDateGenerator.generateRandomDate(2000, 2020)
        );
    }
}
