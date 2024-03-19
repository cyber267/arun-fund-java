package fr.epita.arun.services;

import fr.epita.arun.dataModel.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PatientReader {
    public List<Patient> readAll() {
        Path currentFilePath = Path.of("resources/patients.csv");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(currentFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }

        List<Patient> patients = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            Patient patient = extractPatient(parts);
            patients.add(patient);
        }
        return patients;
    }

    private static Patient extractPatient(String[] row) {
        Patient patient = new Patient();
        patient.setPatNumHC(Long.parseLong(row[0].replaceAll("\"", "")));
        patient.setPatLastName(row[1]);
        patient.setPatFirstName(row[2]);
        patient.setPatAddress(row[3]);
        patient.setPatTel(row[4]);
        patient.setPatInsuranceId(Integer.parseInt(row[5]));
        LocalDate subscriptionDate = LocalDate.parse(row[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        patient.setPatSubscriptionDate(subscriptionDate);
        return patient;
    }
}