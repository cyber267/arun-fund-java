package fr.epita.arun.launcher;

import fr.epita.arun.dataModel.Insurance;
import fr.epita.arun.dataModel.Patient;
import fr.epita.arun.services.InsuranceReader;
import fr.epita.arun.services.PatientReader;

import java.util.List;

public class TestOOP2 {
    public static void main(String[] args) {
        PatientReader patientReader = new PatientReader();
        InsuranceReader insuranceReader = new InsuranceReader();

        List<Patient> patients = patientReader.readAll();
        List<Insurance> insurances = insuranceReader.readAll();

        System.out.println("Patients:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        System.out.println("\nInsurances:");
        for (Insurance insurance : insurances) {
            System.out.println(insurance);
        }

    }
}