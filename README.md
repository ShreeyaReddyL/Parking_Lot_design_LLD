# Multilevel Parking Lot System

This repository contains a fully functional, Object-Oriented implementation of a Multilevel Parking Lot System in Java. It supports multiple vehicle types, slot sizes, multiple entry gates, and distance-based slot allocation.

## 1. Class Diagram
<img width="995" height="653" alt="Screenshot 2026-03-23 214032" src="https://github.com/user-attachments/assets/b253e6e8-3da2-4817-8073-5159de8a5787" />



Design Philosophy
This system is designed using a bottom-up Object-Oriented approach, focusing on high cohesion and loose coupling.

Encapsulation: Entities like ParkingSpot, Ticket, and Vehicle manage their own internal data. The ParkingLot class acts as the central Controller (Facade pattern) that orchestrates the interactions between these entities.

Extensibility: By separating VehicleType from SlotType, the system can easily scale to introduce new vehicles (e.g., Electric Vehicles) or specialized slots (e.g., EV_CHARGING) without rewriting the core allocation logic.

Solving the Core Requirements
Finding the Nearest Slot: Both Gate and ParkingSpot are assigned a hypothetical locationPoint (a 1D coordinate). Distance is calculated using absolute difference: |spot.location - gate.location|. The system scans available slots and picks the one with the minimum distance.

Compatible Slot Upgrades: The allocation logic uses a fallback mechanism based on VehicleType:

2-Wheeler: Searches SMALL -> MEDIUM -> LARGE.

Car: Searches MEDIUM -> LARGE.

Bus: Searches strictly for LARGE.

Billing by Slot Type: The Pricing Strategy links hourly rates strictly to the allocated SlotType, not the vehicle. When a ticket is processed at exit, the bill is calculated based on ticket.getSpot().getType().

3. Project Structure
The code is organized into standard Java packages for maintainability:

Plaintext
src/
├── enums/
│   ├── SlotType.java
│   └── VehicleType.java
├── models/
│   ├── Gate.java
│   ├── ParkingSpot.java
│   ├── Ticket.java
│   └── Vehicle.java
├── core/
│   └── ParkingLot.java
└── Main.java

