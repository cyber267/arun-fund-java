package fr.epita.arun.dataModel;

public class Insurance {
    private int insuranceId;
    private String insuranceName;

    public Insurance() {
        // Default constructor
    }

    public Insurance(int insuranceId, String insuranceName) {
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
    }

    // Add getters and setters

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insuranceId=" + insuranceId +
                ", insuranceName='" + insuranceName + '\'' +
                '}';
    }
}
