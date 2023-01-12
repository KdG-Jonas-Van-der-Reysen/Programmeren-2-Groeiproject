package be.kdg.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Brommer implements Comparable<Brommer>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Properties
    private int id;
    private String model;

    private String chassisNummer;
    private double gewicht;
    private transient int aantalKeerOnderhoud;
    private BrommerKlasse klasse;
    private transient LocalDate releaseDate;
    private transient LocalDate laatsteOnderhoud;

    public Brommer(String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate releaseDate, LocalDate laatsteOnderhoud) {
        this(-1, model, chassisNummer, gewicht, aantalKeerOnderhoud, klasse, releaseDate, laatsteOnderhoud);
    }

    public Brommer(String model, int aantalKeerOnderhoud, LocalDate releaseDate) {
        this(-1, model, "", 0, aantalKeerOnderhoud, BrommerKlasse.A, releaseDate, LocalDate.now());
    }

    public Brommer(int id, String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate releaseDate, LocalDate laatsteOnderhoud) {
        this.setId(id);
        this.setModel(model);
        this.setChassisNummer(chassisNummer);
        this.setGewicht(gewicht);
        this.setAantalKeerOnderhoud(aantalKeerOnderhoud);
        this.setKlasse(klasse);
        this.setReleaseDate(releaseDate);
        this.setLaatsteOnderhoud(laatsteOnderhoud);
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
