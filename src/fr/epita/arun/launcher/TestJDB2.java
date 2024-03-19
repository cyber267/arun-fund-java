package fr.epita.arun.launcher;

import fr.epita.arun.dataModel.Patient;
import fr.epita.arun.services.PatientDAO;
import fr.epita.arun.services.PatientReader;

import java.util.List;

public class TestJDB2 {


    public static void main(String[] args) {
        // Read the list of patients from CSV using PatientReader
        List<Patient> yourListOfPatients = new PatientReader().readAll();

        // Modify the path and filename accordingly
        PatientDAO patientDAO = new PatientDAO();

        // 1. Using the create() action to save the list of Patient instances in the database.
        patientDAO.create(yourListOfPatients);

        // Call the method from TestJDB1 to create the table
        new TestJDB1().createTable();

        // 2. Search for a patient by first name and last name
        Patient searchedPatient = patientDAO.search("Bernard", "Martin");
        if (searchedPatient != null) {
            System.out.println("Searched Patient: " + searchedPatient);
        } else {
            System.out.println("Patient not found.");
        }

        // 3. Update the found patient
    }
}
