package models;
import enums.SlotType;

public class ParkingSpot {
    private String id;
    private SlotType type;
    private boolean isAvailable;
    private int locationPoint;

    public ParkingSpot(String id, SlotType type, int locationPoint) {
        this.id = id;
        this.type = type;
        this.locationPoint = locationPoint;
        this.isAvailable = true; 
    }

    public String getId() { return id; }
    public SlotType getType() { return type; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public int getLocationPoint() { return locationPoint; }
}