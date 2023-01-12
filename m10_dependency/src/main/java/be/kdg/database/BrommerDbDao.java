package be.kdg.database;

import be.kdg.data.Data;
import be.kdg.exceptions.BrommerException;
import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BrommerDbDao implements BrommerDao {
    private Connection connection;
    private static BrommerDbDao INSTANCE;
    private static final Logger logger = Logger.getLogger("be.kdg.model.Brommer");

    // Singleton stuff

    public static BrommerDbDao getInstance(String databasePath) {
        if (INSTANCE == null) {
            INSTANCE = new BrommerDbDao(databasePath);
        }

        return INSTANCE;
    }


    private BrommerDbDao(String databasePath) {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath, "sa", "");
            System.out.println("Connected to database");
            createTable();
        } catch (SQLException e) {
            System.err.println("Failed to connect to datbase: " + databasePath);
            System.exit(1);
        }
    }

    @Override
    public boolean insert(Brommer brommer) {
        try {

            PreparedStatement prepStatement = connection.prepareStatement(
                    "INSERT INTO brommersdb (id, model, chassisNummer, gewicht, aantalKeerOnderhoud, klasse, releaseDate, laatsteOnderhoud) " +
                            "VALUES (NULL, ?, ?, ?, ? ,?, ?, ?)");

            prepStatement.setString(1, brommer.getModel());
            prepStatement.setString(2, brommer.getChassisNummer());
            prepStatement.setDouble(3, brommer.getGewicht());
            prepStatement.setInt(4, brommer.getAantalKeerOnderhoud());
            prepStatement.setString(5, brommer.getKlasse().toString());
            prepStatement.setDate(6, Date.valueOf(brommer.getReleaseDate()));
            prepStatement.setDate(7, Date.valueOf(brommer.getLaatsteOnderhoud()));

            int rowsAffected = prepStatement.executeUpdate();

            boolean result = rowsAffected == 1;
            prepStatement.close();
            logger.info("Brommer inserted: " + result);
            return result;

        } catch (SQLException e) {
            logger.warning("Unexpected error while inserting into database: " + e.getMessage());
            throw new BrommerException(e);
        }
    }

    @Override
    public boolean delete(String chassisNummer) {
        try {
            Statement statement = connection.createStatement();
            boolean success = false;
            if (chassisNummer.equals("*")) {
                success = statement.execute("DELETE FROM brommersdb");
            } else {
                success = statement.execute("DELETE FROM brommersdb WHERE chassisNummer = '" + chassisNummer + "'");
            }
            statement.close();
            logger.info("Deleted " + chassisNummer + " from database");
            return success;
        } catch (SQLException e) {
            logger.warning("Unexpected error while deleting from database: " + e.getMessage());
            throw new BrommerException(e);
        }
    }

    @Override
    public boolean update(Brommer brommer) {
        try {
            Statement statement = connection.createStatement();
            PreparedStatement prepStatement = connection.prepareStatement(
                    "UPDATE brommersdb SET id=?, model=?, chassisNummer=?, gewicht=?, aantalKeerOnderhoud=?, klasse=?, releaseDate=?, laatsteOnderhoud=? WHERE id = ?");

            prepStatement.setInt(1, brommer.getId());
            prepStatement.setString(2, brommer.getModel());
            prepStatement.setString(3, brommer.getChassisNummer());
            prepStatement.setDouble(4, brommer.getGewicht());
            prepStatement.setInt(5, brommer.getAantalKeerOnderhoud());
            prepStatement.setString(6, brommer.getKlasse().toString());
            prepStatement.setDate(7, Date.valueOf(brommer.getReleaseDate()));
            prepStatement.setDate(8, Date.valueOf(brommer.getLaatsteOnderhoud()));
            prepStatement.setInt(9, brommer.getId());

            int rowsAffected = prepStatement.executeUpdate();
            prepStatement.close();
            logger.info("Updated " + rowsAffected + " rows");
            return (rowsAffected == 1);

        } catch (SQLException e) {
            logger.warning("Unexpected error while deleting from database: " + e.getMessage());
            throw new BrommerException(e);
        }
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE brommersdb IF EXISTS ");
            String createQuery = "CREATE TABLE brommersdb " +
                    "(id INTEGER NOT NULL IDENTITY," +
                    "model VARCHAR(30) NOT NULL, " +
                    "chassisNummer VARCHAR(30) NOT NULL," +
                    "gewicht FLOAT NOT NULL," +
                    "aantalKeerOnderhoud INTEGER NOT NULL," +
                    "klasse VARCHAR(30) NOT NULL," +
                    "releaseDate DATE NOT NULL," +
                    "laatsteOnderhoud DATE NOT NULL)";
            statement.execute(createQuery);
            statement.close();
            Data.getData().forEach(this::insert);
            logger.info("Tabel aangemaakt en gevuld met data");
        } catch (SQLException e) {
            logger.severe("Onverwachte fout bij aanmaken tabel: " + e.getMessage());
            throw new BrommerException(e);
        }
    }

    @Override
    public Brommer retrieve(String chassisNummer) {
        ResultSet resultSet = null;
        Brommer brommer = null;
        Statement statement = null;

        try {
            // Maak statement klaar
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM brommersdb WHERE chassisNummer = '" + chassisNummer + "'");

            while (resultSet.next()) {
                // Normaal is er maar 1 resultaat :-)
                brommer = new Brommer(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getString("chassisNummer"),
                        resultSet.getDouble("gewicht"),
                        resultSet.getInt("aantalKeerOnderhoud"),
                        BrommerKlasse.valueOf(resultSet.getString("klasse")),
                        resultSet.getDate("releaseDate").toLocalDate(),
                        resultSet.getDate("laatsteOnderhoud").toLocalDate()
                );
            }
            statement.close();
            return brommer;
        } catch (SQLException e) {
            logger.severe("Unexpected error while retrieving from database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Brommer> getAllBrommers() {
        return sortedOn("SELECT * FROM brommersdb");
    }

    @Override
    public List<Brommer> sortedOn(String query) {
        ResultSet resultSet = null;
        List<Brommer> brommers = new ArrayList<>();
        Statement statement = null;

        try {
            // Maak statement klaar
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // Normaal is er maar 1 resultaat :-)
                Brommer brommer = new Brommer(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getString("chassisNummer"),
                        resultSet.getDouble("gewicht"),
                        resultSet.getInt("aantalKeerOnderhoud"),
                        BrommerKlasse.valueOf(resultSet.getString("klasse")),
                        resultSet.getDate("releaseDate").toLocalDate(),
                        resultSet.getDate("laatsteOnderhoud").toLocalDate()
                );
                brommers.add(brommer);
            }
            statement.close();
        } catch (SQLException e) {
            logger.warning("Unexpected error while retrieving from database: " + e.getMessage());
            throw new BrommerException(e);
        }
        return brommers;
    }

    public List<Brommer> sortedOnGewicht() {
        return sortedOn("SELECT * FROM brommersdb ORDER BY gewicht");
    }

    public List<Brommer> sortedOnAantalKeerOnderhoud() {
        return sortedOn("SELECT * FROM brommersdb ORDER BY aantalKeerOnderhoud");
    }

    public List<Brommer> sortedOnReleaseDate() {
        return sortedOn("SELECT * FROM brommersdb ORDER BY releaseDate");
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.severe("Unexpected error while closing database connection: " + e.getMessage());
                throw new BrommerException(e);
            }
        }
    }
}
