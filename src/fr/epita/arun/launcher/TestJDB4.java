package fr.epita.arun.launcher;

import fr.epita.arun.dataModel.Insurance;
import fr.epita.arun.dataModel.Medication;
import fr.epita.arun.dataModel.Patient;
import fr.epita.arun.dataModel.Prescription;
import fr.epita.arun.services.*;

import java.util.List;

public class TestJDB4 {

    public static void main(String[] args) {
        // Read data from CSV files
        List<Patient> patients = new PatientReader().readAll();
        List<Insurance> insurances = new InsuranceReader().readAll();
        List<Medication> medications = new MedicationReader().readAll();
        List<Prescription> prescriptions = new PrescriptionReader().readAll();

        // Create DAO instances
        PatientDAO patientDAO = new PatientDAO();
        InsuranceDAO insuranceDAO = new InsuranceDAO();
        MedicationDAO medicationDAO = new MedicationDAO();
        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

        // Create tables and insert data
        patientDAO.create(patients);
        insuranceDAO.create(insurances);
        medicationDAO.create(medications);
        prescriptionDAO.create(prescriptions);

        // Display patient information with associated treatment details
        displayPatientTreatmentInfo(patientDAO, prescriptionDAO, medicationDAO);
    }

    private static void displayPatientTreatmentInfo(PatientDAO patientDAO, PrescriptionDAO prescriptionDAO, MedicationDAO medicationDAO) {
        List<Patient> patients = patientDAO.searchAll();
        for (Patient patient : patients) {
            System.out.println("Patient Name: " + patient.getPatFirstName() + " " + patient.getPatLastName());

            List<Prescription> prescriptions = prescriptionDAO.searchByPatientId(patient.getPatNumHC());
            for (Prescription prescription : prescriptions) {
                Medication medication = medicationDAO.search(prescription.getPrescCode());
                System.out.println("  Treatment: " + medication.getMedicationName() +
                        ", Days Valid: " + prescription.getPrescDays());
            }

            System.out.println();
        }
    }
}
