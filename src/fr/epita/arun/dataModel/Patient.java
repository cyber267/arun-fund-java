package fr.epita.arun.dataModel;

import java.time.LocalDate;

public class Patient {
    private long patNumHC;
    private String patLastName;
    private String patFirstName;
    private String patAddress;
    private String patTel;
    private int patInsuranceId;
    private LocalDate patSubscriptionDate;

    public Patient() {
        // Default constructor
    }

    public Patient(long patNumHC, String patLastName, String patFirstName, String patAddress, String patTel, int patInsuranceId, LocalDate patSubscriptionDate) {
        this.patNumHC = patNumHC;
        this.patLastName = patLastName;
        this.patFirstName = patFirstName;
        this.patAddress = patAddress;
        this.patTel = patTel;
        this.patInsuranceId = patInsuranceId;
        this.patSubscriptionDate = patSubscriptionDate;
    }

    // Add getters and setters

    public long getPatNumHC() {
        return patNumHC;
    }

    public void setPatNumHC(long patNumHC) {
        this.patNumHC = patNumHC;
    }

    public String getPatLastName() {
        return patLastName;
    }

    public void setPatLastName(String patLastName) {
        this.patLastName = patLastName;
    }

    public String getPatFirstName() {
        return patFirstName;
    }

    public void setPatFirstName(String patFirstName) {
        this.patFirstName = patFirstName;
    }

    public String getPatAddress() {
        return patAddress;
    }

    public void setPatAddress(String patAddress) {
        this.patAddress = patAddress;
    }

    public String getPatTel() {
        return patTel;
    }

    public void setPatTel(String patTel) {
        this.patTel = patTel;
    }

    public int getPatInsuranceId() {
        return patInsuranceId;
    }

    public void setPatInsuranceId(int patInsuranceId) {
        this.patInsuranceId = patInsuranceId;
    }

    public LocalDate getPatSubscriptionDate() {
        return patSubscriptionDate;
    }

    public void setPatSubscriptionDate(LocalDate patSubscriptionDate) {
        this.patSubscriptionDate = patSubscriptionDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patNumHC=" + patNumHC +
                ", patLastName='" + patLastName + '\'' +
                ", patFirstName='" + patFirstName + '\'' +
                ", patAddress='" + patAddress + '\'' +
                ", patTel='" + patTel + '\'' +
                ", patInsuranceId=" + patInsuranceId +
                ", patSubscriptionDate=" + patSubscriptionDate +
                '}';
    }
}