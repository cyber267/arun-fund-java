package fr.epita.arun.launcher;

import fr.epita.arun.dataModel.Patient;
import fr.epita.arun.services.PatientBLService;
import fr.epita.arun.services.PatientReader;

import java.time.LocalDate;
import java.util.List;

public class TestBLI1 {

    public static void main(String[] args) {
        // Read patients from CSV
        PatientReader patientReader = new PatientReader();
        List<Patient> patients = patientReader.readAll();

        // Select the 6th patient (reading order)
        Patient selectedPatient = patients.get(5); // Assuming there are at least 6 patients

        // Compute and display seniority
        LocalDate currentDate = LocalDate.now();
        PatientBLService patientBLService = new PatientBLService();
        int seniority = patientBLService.computeSeniority(selectedPatient, currentDate);

        System.out.println("Seniority of the selected patient: " + seniority + " years");
    }
}
