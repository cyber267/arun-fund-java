package fr.epita.arun.services;

import fr.epita.arun.dataModel.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    private static final String DATABASE_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public void create(List<Patient> patients) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            for (Patient patient : patients) {
                String insertQuery = "INSERT INTO PATIENTS (pat_num_HC, pat_last_name, pat_first_name, pat_address, pat_tel, pat_insurance_id, pat_subscription_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    setPatientParameters(preparedStatement, patient);
                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Patients inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> searchAll() {
        createTable();

        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT * FROM PATIENTS";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectQuery)) {

                while (resultSet.next()) {
                    Patient patient = extractPatient(resultSet);
                    patients.add(patient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public Patient search(String firstName, String lastName) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT * FROM PATIENTS WHERE pat_first_name = ? AND pat_last_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractPatient(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Patient extractPatient(ResultSet resultSet) throws SQLException {
        long patNumHC = resultSet.getLong("pat_num_HC");
        String patLastName = resultSet.getString("pat_last_name");
        String patFirstName = resultSet.getString("pat_first_name");
        String patAddress = resultSet.getString("pat_address");
        String patTel = resultSet.getString("pat_tel");
        int patInsuranceId = resultSet.getInt("pat_insurance_id");
        Date patSubscriptionDate = resultSet.getDate("pat_subscription_date");

        return new Patient(patNumHC, patLastName, patFirstName, patAddress, patTel, patInsuranceId, patSubscriptionDate.toLocalDate());
    }

    private void setPatientParameters(PreparedStatement preparedStatement, Patient patient) throws SQLException {
        preparedStatement.setLong(1, patient.getPatNumHC());
        preparedStatement.setString(2, patient.getPatLastName());
        preparedStatement.setString(3, patient.getPatFirstName());
        preparedStatement.setString(4, patient.getPatAddress());
        preparedStatement.setString(5, patient.getPatTel());
        preparedStatement.setInt(6, patient.getPatInsuranceId());
        preparedStatement.setDate(7, Date.valueOf(patient.getPatSubscriptionDate()));
    }

    private void createTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            Statement statement = connection.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS PATIENTS (" +
                    "pat_num_HC BIGINT PRIMARY KEY," +
                    "pat_last_name VARCHAR(255) NOT NULL," +
                    "pat_first_name VARCHAR(255) NOT NULL," +
                    "pat_address VARCHAR(255) NOT NULL," +
                    "pat_tel VARCHAR(20) NOT NULL," +
                    "pat_insurance_id INT NOT NULL," +
                    "pat_subscription_date DATE NOT NULL" +
                    ")";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table PATIENTS created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
