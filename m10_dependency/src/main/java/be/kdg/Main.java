package be.kdg;

import be.kdg.database.BrommerDao;
import be.kdg.database.BrommerDbDao;
import be.kdg.service.BrommersService;
import be.kdg.service.BrommersServiceImpl;
import be.kdg.view.BrommersPresenter;
import be.kdg.view.BrommersView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BrommerDao brommerDao = BrommerDbDao.getInstance("db/myDatabase");
        BrommersService brommersService = new BrommersServiceImpl(brommerDao);

        BrommersView brommersView = new BrommersView();
        BrommersPresenter brommersPresenter = new BrommersPresenter(brommersView, brommersService);

        primaryStage.setTitle("Brommer management system");
        primaryStage.setScene(new Scene(brommersView));
        primaryStage.show();
    }
}
