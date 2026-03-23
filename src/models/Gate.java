package models;

public class Gate {
    private String gateId;
    private int locationPoint;

    public Gate(String gateId, int locationPoint) {
        this.gateId = gateId;
        this.locationPoint = locationPoint;
    }

    public String getGateId() { return gateId; }
    public int getLocationPoint() { return locationPoint; }
}