package be.kdg.service;

import be.kdg.model.Brommer;

import java.util.List;

public interface BrommersService {
    List<Brommer> getAllBrommers();
    void addBrommer(Brommer brommer);
}
