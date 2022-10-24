package be.kdg.model;

import be.kdg.reflection.CanRun;

import java.time.LocalDate;
import java.util.Objects;

public class Voertuig implements Comparable<Brommer> {
    // Properties
    private String model;
    private String chassisNummer;
    private double gewicht;
    private int aantalKeerOnderhoud;
    private LocalDate releaseDate;
    private LocalDate laatsteOnderhoud;

    public String getModel() {
        return model;
    }

    @CanRun("E-110S")
    public void setModel(String model) {
        if(model.isBlank() || model.isEmpty()) {
            throw new IllegalArgumentException("Model mag niet leeg zijn");
        }
        this.model = model;
    }

    public String getChassisNummer() {
        return chassisNummer;
    }

    @CanRun("1G4AW69N2DH524774")
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        // Relase date mag niet null zijn en niet in de toekomst liggen
        if(releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Relase date mag niet null zijn en niet in de toekomst liggen");
        }
        this.releaseDate = releaseDate;
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
        return String.format("%-26s (%s) %.2fkg \taantal keer onderhoud: %s , %s, Laatste onderhoud: %s, Release date: %s", this.getModel(), this.getChassisNummer(), this.getGewicht(), this.getAantalKeerOnderhoud(), this.getReleaseDate(), this.getLaatsteOnderhoud(), this.getReleaseDate());
    }
}
