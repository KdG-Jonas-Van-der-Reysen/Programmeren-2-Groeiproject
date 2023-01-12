package be.kdg.service;

import be.kdg.database.BrommerDao;
import be.kdg.model.Brommer;

import java.util.List;

public class BrommersServiceImpl implements BrommersService {
    private BrommerDao db;
    public BrommersServiceImpl(BrommerDao brommerDao) {
        this.db = brommerDao;
    }


    @Override
    public List<Brommer> getAllBrommers() {
        return db.getAllBrommers();
    }

    @Override
    public void addBrommer(Brommer brommer) {
        db.insert(brommer);
    }
}
