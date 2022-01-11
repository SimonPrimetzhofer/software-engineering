package landside;

import landside.model.Driver;
import landside.model.destination.Destination;
import landside.model.destination.Gate;
import landside.model.vehicle.Bus;
import landside.model.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestFuelStatusTest {
    Vehicle bus;
    Driver busDriver;
    LandsideManagement lm;
    Destination gate;

    @BeforeEach
    void setUp() {
        lm = new LandsideManagement();
        gate = new Gate(1, 500, 1);
        lm.destinations.add(gate);

        bus = new Bus(150, 69);
        lm.vehicles.add(bus);

        busDriver = new Driver("Max", "Musterfahrer", 2500, lm, 1);
        busDriver.setVehicle(bus);
        lm.employees.add(busDriver);
    }

    @Test
    void testRequestFuelStatus() {
        //lm.requestFuelStatus(busDriver);
        assertEquals(bus.getFuel(), 0.0);


    }
}
