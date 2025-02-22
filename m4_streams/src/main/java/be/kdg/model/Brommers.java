package be.kdg.model;

import java.util.*;
import java.util.function.Function;

public class Brommers {
    private TreeSet<Brommer> brommers;

    public Brommers() {
        this.brommers = new TreeSet<>();
    }

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
            return b1.getReleaseDate().compareTo(b2.getReleaseDate());
        }
    }

    public boolean add(Brommer brommer) {
        return brommers.add(brommer);
    }

    // Remove
    public boolean remove(Brommer brommer) {
        return brommers.removeIf(b -> b.equals(brommer));
    }

    // Search (op chassisnummer)
    public Brommer search(String chassisNummer) {
        return brommers.stream()
                .filter(b -> b.getChassisNummer().equals(chassisNummer))
                .findFirst()
                .orElse(null);
    }

    public List<Brommer> sortedBy(Function<Brommer, Comparable> function) {
        List<Brommer> brommers = new LinkedList<>(this.brommers);
        Collections.sort(brommers, Comparator.comparing(function));
        return brommers;
    }

    // Sorted on gewicht
    public List<Brommer> sortedOnGewicht() {
        // Convert to linkedlist
        List<Brommer> brommers = new LinkedList<>(this.brommers);
        Collections.sort(brommers, new GewichtComparator());
        return brommers;
    }

    // Sorted on laatste onderhoud
    public List<Brommer> sortedOnLaatsteOnderhoud() {
        // Convert to linkedlist
        List<Brommer> brommers = new LinkedList<>(this.brommers);
        Collections.sort(brommers, new LaatsteOnderhoudComparator());
        return brommers;
    }

    // Sorted on release date
    public List<Brommer> sortedOnReleaseDate() {
        // Convert to linkedlist
        List<Brommer> brommers = new LinkedList<>(this.brommers);
        Collections.sort(brommers, new ReleaseDateComparator());
        return brommers;
    }

    public int getSize() {
        return brommers.size();
    }

    // Function to return the treeset
    public TreeSet<Brommer> getBrommers() {
        return brommers;
    }
}
