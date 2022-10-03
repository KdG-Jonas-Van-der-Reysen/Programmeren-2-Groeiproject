package be.kdg.model;

import java.util.Comparator;
import java.util.TreeSet;

public class Brommers {
    private TreeSet<Brommer> brommers;

    private class GewichtComparator implements Comparator<Brommer> {
        @Override
        public int compare(Brommer b1, Brommer b2) {
            return b1.getGewicht() - b2.getGewicht() > 0 ? 1 : -1;
        }
    }

    private class LaatsteOnderhoudComparator implements Comparator<Brommer> {
        @Override
        public int compare(Brommer b1, Brommer b2) {
            return b1.getLaatsteOnderhoud().compareTo(b2.getLaatsteOnderhoud());
        }
    }

    private class ReleaseDateComparator implements Comparator<Brommer> {
        @Override
        public int compare(Brommer b1, Brommer b2) {
            return b1.getRelaseDate().compareTo(b2.getRelaseDate());
        }
    }

    public boolean add(Brommer brommer) {
        return brommers.add(brommer);
    }

    // Remove
    public boolean remove(Brommer brommer) {
        return brommers.remove(brommer);
    }

    // Search (op chassisnummer)
    public Brommer search(String chassisNummer) {
        for (Brommer brommer : brommers) {
            if (brommer.getChassisNummer().equals(chassisNummer)) {
                return brommer;
            }
        }
        return null;
    }

    public int getSize() {
        return brommers.size();
    }
}
