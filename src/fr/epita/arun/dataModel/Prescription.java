package fr.epita.arun.dataModel;

public class Prescription {

    private int prescId;
    private String prescRefPat;
    private int prescCode;
    private int prescDays;

    public Prescription() {
        // Default constructor
    }

    public Prescription(int prescId, String prescRefPat, int prescCode, int prescDays) {
        this.prescId = prescId;
        this.prescRefPat = prescRefPat;
        this.prescCode = prescCode;
        this.prescDays = prescDays;
    }

    public int getPrescId() {
        return prescId;
    }

    public void setPrescId(int prescId) {
        this.prescId = prescId;
    }

    public String getPrescRefPat() {
        return prescRefPat;
    }

    public void setPrescRefPat(String prescRefPat) {
        this.prescRefPat = prescRefPat;
    }

    public int getPrescCode() {
        return prescCode;
    }

    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }

    public int getPrescDays() {
        return prescDays;
    }

    public void setPrescDays(int prescDays) {
        this.prescDays = prescDays;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescId=" + prescId +
                ", prescRefPat='" + prescRefPat + '\'' +
                ", prescCode=" + prescCode +
                ", prescDays=" + prescDays +
                '}';
    }
}
