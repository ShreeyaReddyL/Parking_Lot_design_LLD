package core;

import enums.SlotType;
import enums.VehicleType;
import models.Gate;
import models.ParkingSpot;
import models.Ticket;
import models.Vehicle;

import java.util.*;

public class ParkingLot {
    private List<ParkingSpot> spots = new ArrayList<>();
    private List<Gate> gates = new ArrayList<>();
    private Map<SlotType, Double> hourlyRates = new HashMap<>();

    public ParkingLot() {
        hourlyRates.put(SlotType.SMALL, 20.0);
        hourlyRates.put(SlotType.MEDIUM, 50.0);
        hourlyRates.put(SlotType.LARGE, 100.0);
    }

    public void addSpot(ParkingSpot spot) { spots.add(spot); }
    public void addGate(Gate gate) { gates.add(gate); }

    public Ticket park(Vehicle vehicle, long entryTime, Gate entryGate) {
        ParkingSpot allocatedSpot = findNearestCompatibleSpot(vehicle.getType(), entryGate);

        if (allocatedSpot == null) {
            System.out.println("Error: Parking Lot Full for " + vehicle.getType());
            return null;
        }

        allocatedSpot.setAvailable(false);
        Ticket ticket = new Ticket(vehicle, allocatedSpot, entryTime);
        System.out.println("Success: Parked " + vehicle.getLicensePlate() + " in " + allocatedSpot.getType() + " slot (" + allocatedSpot.getId() + ")");
        return ticket;
    }

    public double exit(Ticket ticket, long exitTime) {
        ticket.getSpot().setAvailable(true); // Free up the slot

        long hoursParked = Math.max(1, exitTime - ticket.getEntryTime()); 
        
        double rate = hourlyRates.get(ticket.getSpot().getType());
        double totalBill = hoursParked * rate;

        System.out.println("Checkout " + ticket.getVehicle().getLicensePlate() + " | Slot: " + ticket.getSpot().getType() + " | Duration: " + hoursParked + " hrs | Total: $" + totalBill);
        return totalBill;
    }

    public void status() {
        int small = 0, medium = 0, large = 0;
        for (ParkingSpot s : spots) {
            if (s.isAvailable()) {
                if (s.getType() == SlotType.SMALL) small++;
                else if (s.getType() == SlotType.MEDIUM) medium++;
                else if (s.getType() == SlotType.LARGE) large++;
            }
        }
        System.out.println("--- Parking Status ---");
        System.out.println("Available SMALL: " + small + " | MEDIUM: " + medium + " | LARGE: " + large);
        System.out.println("----------------------");
    }

    private ParkingSpot findNearestCompatibleSpot(VehicleType vType, Gate gate) {
        List<SlotType> allowedTypes = getAllowedSlotTypes(vType);
        
        ParkingSpot bestSpot = null;
        int minDistance = Integer.MAX_VALUE;

        for (SlotType targetType : allowedTypes) {
            for (ParkingSpot spot : spots) {
                if (spot.isAvailable() && spot.getType() == targetType) {
                    int distance = Math.abs(spot.getLocationPoint() - gate.getLocationPoint());
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestSpot = spot;
                    }
                }
            }
            if (bestSpot != null) break; // Found a spot, stop searching larger types
        }
        return bestSpot;
    }

    private List<SlotType> getAllowedSlotTypes(VehicleType vType) {
        switch (vType) {
            case TWO_WHEELER: return Arrays.asList(SlotType.SMALL, SlotType.MEDIUM, SlotType.LARGE);
            case CAR: return Arrays.asList(SlotType.MEDIUM, SlotType.LARGE);
            case BUS: return Arrays.asList(SlotType.LARGE);
            default: return Collections.emptyList();
        }
    }
}