package financial;

import airside.AirsideManagement;
import financial.model.Airline;
import financial.model.FinancialManagementEmployee;
import landside.LandsideManagement;
import landside.model.destination.Deposit;
import landside.model.destination.Gate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminal.model.FlightInformation;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kaan Baylan
 */

class AirlineTest {

    private Airline airline;
    private FinancialManagementEmployee fme;
    private FlightInformation flightInformation;

    @BeforeEach
    void setUp() {
        flightInformation = FlightInformation.of(6942023, "Eurowings", "Crete", new Date(), 150., new Gate(1, 300, 1), new Deposit(3, 4500, 2), 349.99, 149.99, 666.66);
        fme = new FinancialManagementEmployee("Max", "Mustermann", 4000, new FinancialManagement(), new LandsideManagement(), new AirsideManagement(), new FinancialManagement());
        fme.getManagedFlights().add(flightInformation);
        airline = Airline.of("Eurowings", fme);
    }

    @Test
    void changeFlightInfoValid() {
        assertDoesNotThrow(() -> airline.changeFlightInfo(flightInformation, "Brazil", flightInformation.getDepartureTime(), 420.));
    }

    @Test
    void changeFlightInfoInvalidDestination() {
        assertThrows(Exception.class, () -> airline.changeFlightInfo(flightInformation, "", flightInformation.getDepartureTime(), 420.));
    }

    @Test
    void changeFLightInfoInvalidInvalidDuration() {
        assertThrows(Exception.class, () -> airline.changeFlightInfo(flightInformation, "Mongolia", flightInformation.getDepartureTime(), -420.));
    }
}