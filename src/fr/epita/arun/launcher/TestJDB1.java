package fr.epita.arun.launcher;

import fr.epita.arun.dataModel.Medication;
import fr.epita.arun.services.MedicationDAO;
import fr.epita.arun.services.MedicationReader;
import fr.epita.arun.services.PatientDAO;
import fr.epita.arun.services.PatientReader;
import fr.epita.arun.dataModel.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestJDB1 {

    public void test() {
        // JDBC URL for in-memory H2 database
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        try {
            // Create the PATIENTS table
            createTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
            Statement statement = connection.createStatement();

            // Create the PATIENTS table
            String createTableQuery = "CREATE TABLE PATIENTS (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "pat_num_HC BIGINT NOT NULL," +
                    "pat_last_name VARCHAR(255) NOT NULL," +
                    "pat_first_name VARCHAR(255) NOT NULL," +
                    "pat_address VARCHAR(255) NOT NULL," +
                    "pat_tel VARCHAR(20) NOT NULL," +
                    "pat_insurance_id INT NOT NULL," +
                    "pat_subscription_date DATE NOT NULL" +
                    ")";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table PATIENTS created successfully.");

            // Read data from CSV file
            PatientReader patientReader = new PatientReader();
            List<Patient> patients = patientReader.readAll();

            // Insert data into the database
            PatientDAO patientDAO = new PatientDAO();
            patientDAO.create(patients);




            MedicationReader medicationReader = new MedicationReader();
            List<Medication> medications = medicationReader.readAll();

            // Insert data into the MEDICATIONS table
            MedicationDAO medicationDAO = new MedicationDAO();
            medicationDAO.create(medications);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestJDB1 testJDB1 = new TestJDB1();
        testJDB1.test();
    }
}
