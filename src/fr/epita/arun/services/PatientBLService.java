package fr.epita.arun.services;

import fr.epita.arun.dataModel.Patient;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientBLService {

    public int computeSeniority(Patient patient, LocalDate currentDate) {
        LocalDate subscriptionDate = patient.getPatSubscriptionDate();
        Period period = Period.between(subscriptionDate, currentDate);
        return period.getYears();
    }



    public Map<Long, Integer> computeSeniorityByPatient(List<Patient> patients, LocalDate currentDate) {
        Map<Long, Integer> seniorityMap = new HashMap<>();

        for (Patient patient : patients) {
            long patientId = patient.getPatNumHC();
            int seniority = computeSeniority(patient, currentDate);
            seniorityMap.put(patientId, seniority);
        }

        return seniorityMap;
    }
}
