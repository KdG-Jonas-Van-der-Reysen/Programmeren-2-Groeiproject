package be.kdg.view;

import be.kdg.model.Brommer;
import be.kdg.service.BrommersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.logging.Logger;

public class BrommersPresenter {
    private static final Logger logger = Logger.getLogger(BrommersPresenter.class.getName());
    BrommersView view;
    BrommersService service;

    public BrommersPresenter(BrommersView view, BrommersService service) {
        this.view = view;
        this.service = service;
        this.addEventHandlers();
        this.showData();
    }

    public void addEventHandlers() {
        view.getSave().setOnAction(event -> {
            try {
                service.addBrommer(
                        new Brommer(
                                view.getModelInput().getText(),
                                Integer.parseInt(view.getAantalKeerOnderhoudInput().getText()),
                                view.getReleaseDateInput().getValue()
                        )
                );
                showData();

            } catch (Exception e) {
                logger.warning("Failed to add brommer: " + e);
                new Alert(Alert.AlertType.ERROR, "Failed to add brommer:\n" + e.getMessage()).showAndWait();
            }
        });
    }

    public void showData() {
        try {
            view.getTableView().setItems(FXCollections.observableList(service.getAllBrommers()));
        } catch (Exception e) {
            logger.warning("Unable to load brommers: " + e);
            new Alert(Alert.AlertType.ERROR, "Unable to load brommers:\n" + e.getMessage()).showAndWait();
        }
    }
}
