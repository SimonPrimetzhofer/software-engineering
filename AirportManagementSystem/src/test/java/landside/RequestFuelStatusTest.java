package landside;

import landside.model.Driver;
import landside.model.destination.Destination;
import landside.model.destination.FuelDepot;
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
    FuelDepot depot;

    @BeforeEach
    void setUp() {
        lm = new LandsideManagement();
        gate = new Gate(1, 500, 1);
        lm.destinations.add(gate);
        depot = new FuelDepot(15000, 2);
        lm.destinations.add(depot);

        bus = new Bus(150, 69);
        lm.vehicles.add(bus);

        busDriver = new Driver("Max", "Musterfahrer", 2500, lm, 1);
        busDriver.setVehicle(bus);
        lm.employees.add(busDriver);
    }

    @Test
    void testRequestFuelStatusLowOnFuel() {
        bus.setFuel(10);
        assertDoesNotThrow(() -> lm.requestFuelStatus(busDriver));
        assertEquals(bus.getFuel(), bus.getMaxFuel());

        // we fully refuel the bus (150 litres)
        lm.operatingCost = 140 * Constants.FUEL_PRICE_PER_LITRE;
    }

    @Test
    void testRequestFuelStatusFull() {
        bus.setFuel(bus.getMaxFuel());
        assertDoesNotThrow(() -> lm.requestFuelStatus(busDriver));

        lm.operatingCost = 0;
    }

    @Test
    void testDriveToFuelDepot() {
        busDriver.driveToFuelDepot(depot);
        assertEquals(busDriver.getVehicle().getDestination(), depot);
    }

    @Test
    void testOpenFuelCap() {
        busDriver.getVehicle().openFuelCap();
        assertFalse(busDriver.getVehicle().isFuelCapClosed());
    }

    @Test
    void testCloseFuelCap() {
        busDriver.getVehicle().closeFuelCap();
        assertTrue(busDriver.getVehicle().isFuelCapClosed());
    }

    @Test
    void testRefuelVehicle() {
        bus.setFuel(120);
        double fuelConsumption = busDriver.refuelVehicle();
        assertEquals(fuelConsumption, 30);
    }
}
