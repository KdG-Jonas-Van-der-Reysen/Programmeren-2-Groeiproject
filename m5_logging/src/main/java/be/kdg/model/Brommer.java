package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

public class Brommer implements Comparable<Brommer> {

    // Logger
    private static final Logger logger = Logger.getLogger("be.kdg.model.Brommer");

    // Properties
    private String model;

    private String chassisNummer;
    private double gewicht;
    private int aantalKeerOnderhoud;
    private BrommerKlasse klasse;
    private LocalDate releaseDate;
    private LocalDate laatsteOnderhoud;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model.isBlank() || model.isEmpty()) {
            // Kan nog geen naam meegeven hier
            logger.severe("Model (" + model + ") is niet geldig, het mag niet leeg zijn.");
        }
        this.model = model;
    }

    public String getChassisNummer() {
        return chassisNummer;
    }

    public void setChassisNummer(String chassisNummer) {
        // Chassisnummer mag niet leeg zijn
        if(chassisNummer.isBlank() || chassisNummer.isEmpty()) {
            logger.severe("Chassisnummer (" + chassisNummer + ") mag niet leeg zijn voor brommer " + model);
        }

        this.chassisNummer = chassisNummer;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        // Gewicht mag niet negatief of 0 zijn
        if(gewicht <= 0) {
            logger.severe("Gewicht (" + gewicht + ") mag niet negatief of 0 zijn voor brommer " + model);
        }
        this.gewicht = gewicht;
    }

    public int getAantalKeerOnderhoud() {
        return aantalKeerOnderhoud;
    }

    public void setAantalKeerOnderhoud(int aantalKeerOnderhoud) {
        // Aantal keer onderhoud mag niet negatief zijn
        if(aantalKeerOnderhoud < 0) {
            logger.severe("Aantal keer onderhoud (" + aantalKeerOnderhoud + ") mag niet negatief zijn voor brommer " + model);

        }
        this.aantalKeerOnderhoud = aantalKeerOnderhoud;
    }

    public BrommerKlasse getKlasse() {
        return klasse;
    }

    public void setKlasse(BrommerKlasse klasse) {
        // Klasse mag niet null zijn
        if(klasse == null) {
            logger.severe("Klasse (" + klasse + ")mag niet null zijn voor brommer " + model);
        }
        this.klasse = klasse;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setRelaseDate(LocalDate releaseDate) {
        // Relase date mag niet null zijn en niet in de toekomst liggen
        if(releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            logger.severe("Relase date (" + releaseDate + ") mag niet null zijn en niet in de toekomst liggen voor brommer " + model);
        }
        this.releaseDate = releaseDate;
    }

    public LocalDate getLaatsteOnderhoud() {
        return laatsteOnderhoud;
    }

    public void setLaatsteOnderhoud(LocalDate laatsteOnderhoud) {
        // Laatste onderhoud mag niet null zijn en niet in de toekomst liggen
        if(laatsteOnderhoud == null || laatsteOnderhoud.isAfter(LocalDate.now())) {
            logger.severe("Laatste onderhoud (" + laatsteOnderhoud +") mag niet null zijn en niet in de toekomst liggen voor brommer " + model);
        }
        this.laatsteOnderhoud = laatsteOnderhoud;
    }

    public Brommer(String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate releaseDate, LocalDate laatsteOnderhoud) {
        this.setModel(model);
        this.setChassisNummer(chassisNummer);
        this.setGewicht(gewicht);
        this.setAantalKeerOnderhoud(aantalKeerOnderhoud);
        this.setKlasse(klasse);
        this.setRelaseDate(releaseDate);
        this.setLaatsteOnderhoud(laatsteOnderhoud);
    }

    public Brommer() {
        this("Onbekend","38R9AFUISKJLILQKSJ2398US", 100, 0, BrommerKlasse.A, LocalDate.now(), LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brommer brommer = (Brommer) o;
        return getChassisNummer().equals(brommer.getChassisNummer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChassisNummer());
    }


    @Override
    public int compareTo(Brommer o) {
        // Vergelijk chassisnummer alfabetisch
        return this.getChassisNummer().compareTo(o.getChassisNummer());
    }

    @Override
    public String toString() {
        // Format with fixed width
        return String.format("%-26s (%s) %.2fkg \taantal keer onderhoud: %s , klasse: %s, Laatste onderhoud: %s, Release date: %s", this.getModel(), this.getChassisNummer(), this.getGewicht(), this.getAantalKeerOnderhoud(), this.getKlasse(), this.getLaatsteOnderhoud(), this.getReleaseDate());
    }
}
