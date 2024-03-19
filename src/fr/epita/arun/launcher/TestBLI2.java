package fr.epita.arun.launcher;

import fr.epita.arun.dataModel.Patient;
import fr.epita.arun.services.PatientBLService;
import fr.epita.arun.services.PatientReader;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TestBLI2 {

    public static void main(String[] args) {
        // Read patients from CSV
        PatientReader patientReader = new PatientReader();
        List<Patient> patients = patientReader.readAll();

        // Compute and display seniority by patient
        LocalDate currentDate = LocalDate.now();
        PatientBLService patientBLService = new PatientBLService();
        Map<Long, Integer> seniorityByPatient = patientBLService.computeSeniorityByPatient(patients, currentDate);

        // Display the results
        for (Map.Entry<Long, Integer> entry : seniorityByPatient.entrySet()) {
            System.out.println("Patient ID: " + entry.getKey() + ", Seniority: " + entry.getValue() + " years");
        }
    }
}
