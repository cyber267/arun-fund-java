package fr.epita.arun.services;

import fr.epita.arun.dataModel.Medication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MedicationReader {
    public List<Medication> readAll() {
        Path currentFilePath = Path.of("resources/medications.csv");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(currentFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }

        List<Medication> medications = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            Medication medication = extractMedication(parts);
            medications.add(medication);
        }
        return medications;
    }

    private static Medication extractMedication(String[] row) {
        Medication medication = new Medication();
        medication.setMedicationCode(Integer.parseInt(row[0]));
        medication.setMedicationName(row[1]);
        medication.setMedicationComment(row[2]);
        return medication;
    }
}
