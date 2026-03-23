import core.ParkingLot;
import enums.SlotType;
import enums.VehicleType;
import models.Gate;
import models.ParkingSpot;
import models.Ticket;
import models.Vehicle;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        Gate gate1 = new Gate("G1", 0);
        Gate gate2 = new Gate("G2", 100);
        lot.addGate(gate1);
        lot.addGate(gate2);

        lot.addSpot(new ParkingSpot("S1", SlotType.SMALL, 10));
        lot.addSpot(new ParkingSpot("M1", SlotType.MEDIUM, 20));
        lot.addSpot(new ParkingSpot("L1", SlotType.LARGE, 30));
        lot.addSpot(new ParkingSpot("L2", SlotType.LARGE, 110)); // Closer to Gate 2

        lot.status();

        Vehicle bike = new Vehicle("BIKE-123", VehicleType.TWO_WHEELER);
        Ticket t1 = lot.park(bike, 10, gate1); // entry at hour 10

        Vehicle bike2 = new Vehicle("BIKE-999", VehicleType.TWO_WHEELER);
        Ticket t2 = lot.park(bike2, 11, gate1);

        Vehicle bus = new Vehicle("BUS-555", VehicleType.BUS);
        Ticket t3 = lot.park(bus, 12, gate2);

        lot.status();

        lot.exit(t2, 14); // stayed 3 hours (14 - 11)

        lot.status();
    }
}