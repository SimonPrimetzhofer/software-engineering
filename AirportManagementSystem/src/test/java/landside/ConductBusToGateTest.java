package landside;

import landside.model.Driver;
import landside.model.destination.Destination;
import landside.model.destination.Gate;
import landside.model.vehicle.Bus;
import landside.model.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConductBusToGateTest {
    Vehicle bus;
    Driver busDriver;
    LandsideManagement lm;
    Destination gate;

    @BeforeEach
    void setUp() {
        lm = LandsideManagement.of();
        gate = new Gate(1, 500, 1);
        lm.destinations.add(gate);

        bus = new Bus(150, 50);
        lm.vehicles.add(bus);

        busDriver = new Driver("Max", "Musterfahrer", 2500, lm, 1);
        busDriver.setVehicle(bus);
        lm.employees.add(busDriver);
    }

    @Test
    void testAssignGate() {
        lm.assignGate(busDriver, (Gate) gate);
        assertEquals(gate, busDriver.getDest());
    }

    @Test
    void testDriveToDestination() {
        busDriver.driveToDestination(gate);
        assertEquals(busDriver.getVehicle().getDestination(), gate);
    }

    @Test
    void testOpenDoor() {
        assertTrue(busDriver.getVehicle() instanceof Bus);
        ((Bus) busDriver.getVehicle()).openDoor();
        assertFalse(((Bus) busDriver.getVehicle()).isDoorsClosed());
    }

    @Test
    void closeOpenDoor() {
        assertTrue(busDriver.getVehicle() instanceof Bus);
        ((Bus) busDriver.getVehicle()).openDoor();
        assertFalse(((Bus) busDriver.getVehicle()).isDoorsClosed());
    }

    @Test
    void testLoadPassengers() {
        ((Bus) busDriver.getVehicle()).closeDoor();
        assertThrows(Exception.class, () -> ((Bus) busDriver.getVehicle()).loadPassengers(20));

        ((Bus) busDriver.getVehicle()).openDoor();

        assertTrue(busDriver.getVehicle() instanceof Bus);
        assertDoesNotThrow(() -> ((Bus) busDriver.getVehicle()).loadPassengers(50));
        assertEquals(((Bus) busDriver.getVehicle()).getPassengersLoaded(), 50);
    }

    @Test
    void testUnloadPassengers() {
        ((Bus) busDriver.getVehicle()).closeDoor();
        assertThrows(Exception.class, () -> ((Bus) busDriver.getVehicle()).unloadPassengers());

        ((Bus) busDriver.getVehicle()).openDoor();

        assertTrue(busDriver.getVehicle() instanceof Bus);
        assertDoesNotThrow(() -> ((Bus) busDriver.getVehicle()).unloadPassengers());
        assertEquals(((Bus) busDriver.getVehicle()).getPassengersLoaded(), 0);
    }

    @Test
    void testGetPassengersLoaded() {
        assertEquals(((Bus) busDriver.getVehicle()).getPassengersLoaded(), 0);

        ((Bus) busDriver.getVehicle()).openDoor();
        assertDoesNotThrow(() -> ((Bus) busDriver.getVehicle()).loadPassengers(30));

        assertEquals(((Bus) busDriver.getVehicle()).getPassengersLoaded(), 30);
    }

    @Test
    void testDriveToGarage() {
        busDriver.driveToGarage();
        assertNull(busDriver.getDest());
        assertTrue(busDriver.getVehicle().isInGarage());
    }
}
