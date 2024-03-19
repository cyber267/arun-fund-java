package fr.epita.arun.launcher;

import fr.epita.arun.services.MedicationDAO;
import fr.epita.arun.services.MedicationReader;
import fr.epita.arun.dataModel.Medication;

import java.util.List;

public class TestJDB3 {

    public static void main(String[] args) {
        // Read the list of medications from CSV using MedicationReader
        List<Medication> yourListOfMedications = new MedicationReader().readAll();

        // Modify the path and filename accordingly
        MedicationDAO medicationDAO = new MedicationDAO();

        // 1. Using the create() action to save the list of Medication instances in the database.
        medicationDAO.create(yourListOfMedications);

        // 2. Search for a medication by code
        Medication searchedMedication = medicationDAO.search(1);
        if (searchedMedication != null) {
            System.out.println("Searched Medication: " + searchedMedication);
        } else {
            System.out.println("Medication not found.");
        }

        // 3. Update the found medication
    }
}
