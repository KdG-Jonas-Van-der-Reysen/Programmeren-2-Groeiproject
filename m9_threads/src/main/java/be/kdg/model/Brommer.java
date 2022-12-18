package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Brommer implements Comparable<Brommer> {
    // Properties
    private final String model;
    private final String chassisNummer;
    private final double gewicht;
    private final int aantalKeerOnderhoud;
    private final BrommerKlasse klasse;
    private final LocalDate releaseDate;
    private final LocalDate laatsteOnderhoud;

    public Brommer(String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate releaseDate, LocalDate laatsteOnderhoud) {
        this.model = model;
        this.chassisNummer = chassisNummer;
        this.gewicht = gewicht;
        this.aantalKeerOnderhoud = aantalKeerOnderhoud;
        this.klasse = klasse;
        this.releaseDate = releaseDate;
        this.laatsteOnderhoud = laatsteOnderhoud;
    }

    public Brommer() {
        this("Onbekend","38R9AFUISKJLILQKSJ2398US", 100, 0, BrommerKlasse.A, LocalDate.now(), LocalDate.now());
    }

    public String getModel() {
        return model;
    }

    public String getChassisNummer() {
        return chassisNummer;
    }

    public double getGewicht() {
        return gewicht;
    }

    public int getAantalKeerOnderhoud() {
        return aantalKeerOnderhoud;
    }

    public BrommerKlasse getKlasse() {
        return klasse;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalDate getLaatsteOnderhoud() {
        return laatsteOnderhoud;
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
