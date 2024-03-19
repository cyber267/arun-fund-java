package fr.epita.arun.dataModel;

public class Medication {
    private int medicationCode;
    private String medicationName;
    private String medicationComment;

    // Add getters and setters

    public Medication() {

    }

    public Medication(int medicationCode, String medicationName, String medicationComment) {
        this.medicationCode = medicationCode;
        this.medicationName = medicationName;
        this.medicationComment = medicationComment;
    }
    public int getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(int medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationComment() {
        return medicationComment;
    }

    public void setMedicationComment(String medicationComment) {
        this.medicationComment = medicationComment;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medicationCode=" + medicationCode +
                "medicationCode=" + medicationCode +
                ", medicationName='" + medicationName + '\'' +
                ", medicationComment='" + medicationComment + '\'' +
                '}';
    }
}
