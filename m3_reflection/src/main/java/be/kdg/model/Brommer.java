package be.kdg.model;

import java.time.LocalDate;
import java.util.Objects;

public class Brommer extends Voertuig {

    private BrommerKlasse klasse;

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

    public Brommer(String model, String chassisNummer, double gewicht, int aantalKeerOnderhoud, BrommerKlasse klasse, LocalDate releaseDate, LocalDate laatsteOnderhoud) {
        this.setModel(model);
        this.setChassisNummer(chassisNummer);
        this.setGewicht(gewicht);
        this.setAantalKeerOnderhoud(aantalKeerOnderhoud);
        this.setKlasse(klasse);
        this.setReleaseDate(releaseDate);
        this.setLaatsteOnderhoud(laatsteOnderhoud);
    }

    public Brommer() {
        this("Onbekend","38R9AFUISKJLILQKSJ2398US", 100, 0, BrommerKlasse.A, LocalDate.now(), LocalDate.now());
    }

    @Override
    public String toString() {
        // Format with fixed width
        return String.format("%s, klasse: %s", super.toString(), this.getKlasse());
    }

}
