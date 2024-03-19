package fr.epita.arun.services;

import fr.epita.arun.dataModel.Medication;

import java.sql.*;
import java.util.List;

public class MedicationDAO {

    private static final String DATABASE_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public void create(List<Medication> medications) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            for (Medication medication : medications) {
                String insertQuery = "INSERT INTO MEDICATIONS (medication_code, medication_name, medication_comment) " +
                        "VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, medication.getMedicationCode());
                    preparedStatement.setString(2, medication.getMedicationName());
                    preparedStatement.setString(3, medication.getMedicationComment());

                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Medications inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medication search(int medicationCode) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT * FROM MEDICATIONS WHERE medication_code = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, medicationCode);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractMedication(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Medication medication) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String updateQuery = "UPDATE MEDICATIONS SET medication_name = ?, medication_comment = ? WHERE medication_code = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, medication.getMedicationName());
                preparedStatement.setString(2, medication.getMedicationComment());
                preparedStatement.setInt(3, medication.getMedicationCode());

                preparedStatement.executeUpdate();
            }

            System.out.println("Medication updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int medicationCode) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String deleteQuery = "DELETE FROM MEDICATIONS WHERE medication_code = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, medicationCode);

                preparedStatement.executeUpdate();
            }

            System.out.println("Medication deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Medication extractMedication(ResultSet resultSet) throws SQLException {
        int medicationCode = resultSet.getInt("medication_code");
        String medicationName = resultSet.getString("medication_name");
        String medicationComment = resultSet.getString("medication_comment");

        return new Medication(medicationCode, medicationName, medicationComment);
    }

    private void createTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            Statement statement = connection.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS MEDICATIONS (" +
                    "medication_code INT PRIMARY KEY," +
                    "medication_name VARCHAR(255) NOT NULL," +
                    "medication_comment VARCHAR(255)" +
                    ")";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table MEDICATIONS created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
