package fr.epita.arun.services;

import fr.epita.arun.dataModel.Prescription;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionReader {
    public List<Prescription> readAll() {
        Path currentFilePath = Path.of("resources/prescriptions.csv");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(currentFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }

        List<Prescription> prescriptions = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            Prescription prescription = extractPrescription(parts);
            prescriptions.add(prescription);
        }
        return prescriptions;
    }

    private static Prescription extractPrescription(String[] row) {
        Prescription prescription = new Prescription();
        prescription.setPrescId(Integer.parseInt(row[0]));
        prescription.setPrescRefPat(row[1]);
        prescription.setPrescCode(Integer.parseInt(row[2]));
        prescription.setPrescDays(Integer.parseInt(row[3]));
        return prescription;
    }
}
