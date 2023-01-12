package be.kdg.database;

import be.kdg.model.Brommer;

import java.util.List;

public interface BrommerDao {
    boolean insert(Brommer brommer);
    boolean delete(String naam);
    boolean update(Brommer brommer);
    Brommer retrieve(String naam);
    List<Brommer> getAllBrommers();
    List<Brommer> sortedOn(String query);
}
