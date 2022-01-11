package terminal;

import financial.model.Employee;
import landside.LandsideManagement;
import landside.model.Driver;
import landside.model.vehicle.Bus;
import landside.model.vehicle.Vehicle;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Log
public class ConductBusToGateTest {
    Vehicle bus;
    Employee busDriver;
    LandsideManagement lm;

    @BeforeEach
    void setUp() {
        lm = new LandsideManagement();
        bus = new Bus(150, 50);
        lm.vehicles.add(bus);

        busDriver = new Driver("Max", "Musterfahrer", 2500, lm, 1);
        lm.employees.add(busDriver);
    }

    @Test
    void testAssignPlane(){

    }
}
