package safety.model;

public class Neighborhoods {
    private String MCPP;
    private Long Area;
    private String policeStation;
    private String policeStationAddress;
    private Double distanceToPoliceStation;
    private String fireStatin;
    private String fireStationAddress;
    private Double distanceToFireStation;

    public Neighborhoods() {
    }

    public Neighborhoods(String MCPP, Long Area, String policeStation, String policeStationAddress,
            Double distanceToPoliceStation, String fireStatin, String fireStationAddress,
            Double distanceToFireStation) {
        this.MCPP = MCPP;
        this.Area = Area;
        this.policeStation = policeStation;
        this.policeStationAddress = policeStationAddress;
        this.distanceToPoliceStation = distanceToPoliceStation;
        this.fireStatin = fireStatin;
        this.fireStationAddress = fireStationAddress;
        this.distanceToFireStation = distanceToFireStation;
    }

    public Neighborhoods(String MCPP) {
        this.MCPP = MCPP;
    }

    public String getMCPP() {
        return this.MCPP;
    }

    public void setMCPP(String MCPP) {
        this.MCPP = MCPP;
    }

    public Long getArea() {
        return this.Area;
    }

    public void setArea(Long Area) {
        this.Area = Area;
    }

    public String getPoliceStation() {
        return this.policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getPoliceStationAddress() {
        return this.policeStationAddress;
    }

    public void setPoliceStationAddress(String policeStationAddress) {
        this.policeStationAddress = policeStationAddress;
    }

    public Double getDistanceToPoliceStation() {
        return this.distanceToPoliceStation;
    }

    public void setDistanceToPoliceStation(Double distanceToPoliceStation) {
        this.distanceToPoliceStation = distanceToPoliceStation;
    }

    public String getFireStatin() {
        return this.fireStatin;
    }

    public void setFireStatin(String fireStatin) {
        this.fireStatin = fireStatin;
    }

    public String getFireStationAddress() {
        return this.fireStationAddress;
    }

    public void setFireStationAddress(String direStationAddress) {
        this.fireStationAddress = direStationAddress;
    }

    public Double getDistanceToFireStation() {
        return this.distanceToFireStation;
    }

    public void setDistanceToFireStation(Double distanceToFireStation) {
        this.distanceToFireStation = distanceToFireStation;
    }

}
