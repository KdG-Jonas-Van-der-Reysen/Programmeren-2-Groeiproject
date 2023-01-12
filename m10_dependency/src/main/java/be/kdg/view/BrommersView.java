package be.kdg.view;

import be.kdg.model.Brommer;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.logging.Logger;

public class BrommersView extends BorderPane {
    private static final Logger logger = Logger.getLogger(BrommersView.class.getName());

    private TableView<Brommer> tableView;
    private TextField modelInput;
    private TextField aantalKeerOnderhoudInput;
    private DatePicker releaseDateInput;
    private Button save;

    public BrommersView() {
        logger.info("Constructing BrommersView");
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        tableView = new TableView<>();
        modelInput = new TextField();
        aantalKeerOnderhoudInput = new TextField();
        releaseDateInput= new DatePicker();
        save = new Button("Save");
    }

    private void layoutNodes() {
        super.setCenter(tableView);
        BorderPane.setMargin(tableView, new Insets(10));

        save.setMinWidth(Button.USE_PREF_SIZE);
        HBox hBox = new HBox(modelInput, aantalKeerOnderhoudInput, releaseDateInput, save);
        hBox.setSpacing(10);
        super.setBottom(hBox);
        BorderPane.setMargin(hBox, new Insets(10));

        modelInput.setPromptText("Model");
        aantalKeerOnderhoudInput.setPromptText("Aantal keer onderhoud");
        releaseDateInput.setPromptText("Release date");

        TableColumn<Brommer, String> col1 = new TableColumn<>("Model");
        col1.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Brommer, String> col2 = new TableColumn<>("Aantal keer onderhoud");
        col2.setCellValueFactory(new PropertyValueFactory<>("aantalKeerOnderhoud"));

        TableColumn<Brommer, LocalDate> col3 = new TableColumn<>("Release date");
        col3.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableView.getColumns().addAll(col1, col2, col3);



    }

    protected TableView<Brommer> getTableView() {
        return tableView;
    }

    protected TextField getModelInput() {
        return modelInput;
    }

    protected TextField getAantalKeerOnderhoudInput() {
        return aantalKeerOnderhoudInput;
    }

    protected DatePicker getReleaseDateInput() {
        return releaseDateInput;
    }

    protected Button getSave() {
        return save;
    }
}
