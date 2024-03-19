package fr.epita.arun.services;

import fr.epita.arun.dataModel.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    private static final String DATABASE_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public void create(List<Prescription> prescriptions) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            for (Prescription prescription : prescriptions) {
                String insertQuery = "INSERT INTO PRESCRIPTIONS (presc_id, presc_ref_pat, presc_code, presc_days) " +
                        "VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, prescription.getPrescId());
                    preparedStatement.setString(2, prescription.getPrescRefPat());
                    preparedStatement.setInt(3, prescription.getPrescCode());
                    preparedStatement.setInt(4, prescription.getPrescDays());

                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Prescriptions inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Prescription search(int prescId) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT * FROM PRESCRIPTIONS WHERE presc_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, prescId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractPrescription(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Prescription prescription) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String updateQuery = "UPDATE PRESCRIPTIONS SET presc_ref_pat = ?, presc_code = ?, presc_days = ? WHERE presc_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, prescription.getPrescRefPat());
                preparedStatement.setInt(2, prescription.getPrescCode());
                preparedStatement.setInt(3, prescription.getPrescDays());
                preparedStatement.setInt(4, prescription.getPrescId());

                preparedStatement.executeUpdate();
            }

            System.out.println("Prescription updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int prescId) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String deleteQuery = "DELETE FROM PRESCRIPTIONS WHERE presc_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, prescId);

                preparedStatement.executeUpdate();
            }

            System.out.println("Prescription deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Prescription extractPrescription(ResultSet resultSet) throws SQLException {
        int prescId = resultSet.getInt("presc_id");
        String prescRefPat = resultSet.getString("presc_ref_pat");
        int prescCode = resultSet.getInt("presc_code");
        int prescDays = resultSet.getInt("presc_days");

        return new Prescription(prescId, prescRefPat, prescCode, prescDays);
    }
    public List<Prescription> searchByPatientId(long patNumHC) {
        createTable();

        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT * FROM PRESCRIPTIONS WHERE presc_ref_pat = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setLong(1, patNumHC);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Prescription prescription = extractPrescription(resultSet);
                        prescriptions.add(prescription);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }
    private void createTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            Statement statement = connection.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS PRESCRIPTIONS (" +
                    "presc_id INT PRIMARY KEY," +
                    "presc_ref_pat VARCHAR(255) NOT NULL," +
                    "presc_code INT NOT NULL," +
                    "presc_days INT NOT NULL" +
                    ")";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table PRESCRIPTIONS created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
