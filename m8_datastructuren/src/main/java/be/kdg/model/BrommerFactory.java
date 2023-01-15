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

    private static String generateString(int maxWordLength, int wordCount, boolean camelCase) {
        // 1 out of 3 chance on a vowel
        // 2 out of 3 chance on a consonant

        // Generate a random string
        StringBuilder sb = new StringBuilder();

        // For each word
        for (int i = 0; i < wordCount; i++) {

            // Decide how long it'll be
            int wordLength = (int) (Math.random() * maxWordLength) + 1;

            // Generate the letters
            for (int j = 0; j < wordLength; j++) {

                // Capitalize first letter if needed
                if (j == 0 && camelCase) {
                    sb.append((char) (Math.random() * 26 + 65));
                } else {
                    // Decide if it's a vowel or a consonant
                    if (Math.random() < 0.33) {
                        sb.append((char) (Math.random() * 5 + 97));
                    } else {
                        sb.append((char) (Math.random() * 21 + 98));
                    }
                }
            }
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    private static LocalDate generateRandomDate(int minYear, int maxYear) {
        return LocalDate.of(minYear + (int)(Math.random() * ((maxYear - minYear) + 1)), 1 + (int)(Math.random() * ((12 - 1) + 1)), 1 + (int)(Math.random() * ((28 - 1) + 1)));
    }

    private static int generateRandomInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static Brommer newRandomBrommer() {
        // Random string generator
        String randomString = java.util.UUID.randomUUID().toString().substring(0, 8);
        return new Brommer(
                generateString(10, 1, true),
                generateString(20, 1, false),
                generateRandomInt(100,200),
                generateRandomInt(0, 10),
                BrommerKlasse.values()[generateRandomInt(0,1)],
                generateRandomDate(2000, 2020),
                generateRandomDate(2000, 2020)
        );
    }
}
