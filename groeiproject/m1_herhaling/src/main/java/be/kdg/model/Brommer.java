package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;

public class Brommer implements Comparable<Brommer> {
    // Properties
    private String model;

    private String chassisNummer;
    private double gewicht;
    private int aantalKeerOnderhoud;
    private BrommerKlasse klasse;
    private LocalDate relaseDate;
    private LocalDate laatsteOnderhoud;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model.isBlank() || model.isEmpty()) {
            throw new IllegalArgumentException("Model mag niet leeg zijn");
        }
        this.model = model;
    }

    public String getChassisNummer() {
        return chassisNummer;
    }

    public void setChassisNummer(String chassisNummer) {
        // Chassisnummer mag niet leeg zijn
        if(chassisNummer.isBlank() || chassisNummer.isEmpty()) {
            throw new IllegalArgumentException("Chassisnummer mag niet leeg zijn");
        }

        this.chassisNummer = chassisNummer;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        // Gewicht mag niet negatief of 0 zijn
        if(gewicht <= 0) {
            throw new IllegalArgumentException("Gewicht mag niet negatief of 0 zijn");
        }
        this.gewicht = gewicht;
    }

    public int getAantalKeerOnderhoud() {
        return aantalKeerOnderhoud;
    }

    public void setAantalKeerOnderhoud(int aantalKeerOnderhoud) {
        // Aantal keer onderhoud mag niet negatief zijn
        if(aantalKeerOnderhoud < 0) {
            throw new IllegalArgumentException("Aantal keer onderhoud mag niet negatief zijn");
        }
        this.aantalKeerOnderhoud = aantalKeerOnderhoud;
    }

    public BrommerKlasse getKlasse() {
        return klasse;
    }

    public void setKlasse(BrommerKlasse klasse) {
        // Klasse mag niet null zijn
        if(klasse == null) {
            throw new IllegalArgumentException("Klasse mag niet null zijn");
        }
        this.klasse = klasse;
    }

    public LocalDate getRelaseDate() {
        return relaseDate;
    }

    public void setRelaseDate(LocalDate relaseDate) {
        // Relase date mag niet null zijn en niet in de toekomst liggen
        if(relaseDate == null || relaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Relase date mag niet null zijn en niet in de toekomst liggen");
        }
        this.relaseDate = relaseDate;
    }

    public LocalDate getLaatsteOnderhoud() {
        return laatsteOnderhoud;
    }

    public void setLaatsteOnderhoud(LocalDate laatsteOnderhoud) {
        // Laatste onderhoud mag niet null zijn en niet in de toekomst liggen
        if(laatsteOnderhoud == null || laatsteOnderhoud.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Laatste onderhoud mag niet null zijn en niet in de toekomst liggen");
        }
        this.laatsteOnderhoud = laatsteOnderhoud;
    }

    public Brommer(String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate relaseDate, LocalDate laatsteOnderhoud) {
        this.setModel(model);
        this.setChassisNummer(chassisNummer);
        this.setGewicht(gewicht);
        this.setAantalKeerOnderhoud(aantalKeerOnderhoud);
        this.setKlasse(klasse);
        this.setRelaseDate(relaseDate);
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
        return String.format("%s (%s) %skg , aantal keer onderhoud: %s , klasse: %s %s, Laatste onderhoud: %s", this.getModel(), this.getChassisNummer(), this.getGewicht(), this.getAantalKeerOnderhoud(), this.getKlasse(), this.getRelaseDate(), this.getLaatsteOnderhoud());
    }
}
