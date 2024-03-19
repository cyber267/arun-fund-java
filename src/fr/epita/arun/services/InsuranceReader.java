package fr.epita.arun.services;

import fr.epita.arun.dataModel.Insurance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InsuranceReader {
    public List<Insurance> readAll() {
        Path currentFilePath = Path.of("resources/insurances.csv");
        List<String> lines;
        try {
            lines = Files.readAllLines(currentFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
            return new ArrayList<>(); // Return an empty list in case of an error
        }

        List<Insurance> insurances = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            Insurance insurance = extractInsurance(parts);
            insurances.add(insurance);
        }
        return insurances;
    }

    private static Insurance extractInsurance(String[] row) {
        if (row.length < 2) {
            throw new IllegalArgumentException("Invalid data format for insurance: " + String.join(",", row));
        }

        Insurance insurance = new Insurance();
        insurance.setInsuranceId(Integer.parseInt(row[0]));
        insurance.setInsuranceName(row[1]);
        // Assuming there is no subscription date in the provided CSV, adjust accordingly
        // LocalDate subscriptionDate = LocalDate.parse(row[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // insurance.setInsSubscriptionDate(subscriptionDate);
        return insurance;
    }
}
