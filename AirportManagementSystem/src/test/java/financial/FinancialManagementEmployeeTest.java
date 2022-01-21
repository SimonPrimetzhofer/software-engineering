package financial;

import airside.AirsideManagement;
import financial.model.FinancialManagementEmployee;
import landside.LandsideManagement;
import landside.model.destination.Deposit;
import landside.model.destination.Gate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminal.model.FlightInformation;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kaan Baylan
 */

class FinancialManagementEmployeeTest {

    private List<FlightInformation> flights;
    private FinancialManagementEmployee fme;

    @BeforeEach
    void setUp() {
        flights = List.of(FlightInformation.of(6942023, "Eurowings", "Crete", new Date(), 150., new Gate(1, 300, 1), new Deposit(3, 4500, 2), 349.99, 149.99, 666.66), FlightInformation.of(236688, "Lufthansa", "Lissabon", new Date(), 360., new Gate(4, 600, 2), new Deposit(2, 4500, 2), 222.99, 199.99, 431.99));
        fme = new FinancialManagementEmployee("Max", "Mustermann", 4000, FinancialManagement.of(), LandsideManagement.of(), AirsideManagement.of(), FinancialManagement.of());
        fme.setManagedFlights(flights);
    }

    @Test
    void scheduleFlight() {
        assertDoesNotThrow(() -> fme.scheduleFlight(flights.get(0)));
    }

    @Test
    void scheduleInvalidFlight() {
        FlightInformation unmanagedFlight = FlightInformation.of(5555, "InvalidAirline", "Deez nuts", new Date(), 150., new Gate(1, 300, 1), new Deposit(3, 4500, 2), 349.99, 149.99, 666.66);
        assertThrows(Exception.class, () -> fme.scheduleFlight(unmanagedFlight));
    }
}