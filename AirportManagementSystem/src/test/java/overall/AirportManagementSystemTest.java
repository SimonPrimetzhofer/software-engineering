package overall;

import airside.AirsideManagement;
import financial.FinancialManagement;
import landside.LandsideManagement;
import landside.model.destination.Deposit;
import landside.model.destination.Gate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminal.TerminalManagement;
import terminal.model.FlightInformation;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AirportManagementSystemTest {

    private AirportManagementSystem airportManagementSystem;
    private TerminalManagement terminalManagement;
    private AirsideManagement airsideManagement;
    private LandsideManagement landsideManagement;
    private FinancialManagement financialManagement;

    @BeforeEach
    void setUp() {
        terminalManagement = TerminalManagement.of();
        airsideManagement = AirsideManagement.of();
        landsideManagement = LandsideManagement.of();
        financialManagement = FinancialManagement.of();

        terminalManagement.setAirsideManagement(airsideManagement);
        terminalManagement.setLandsideManagement(landsideManagement);

        airsideManagement.setTerminalManagement(terminalManagement);

        financialManagement.setAirsideManagement(airsideManagement);
        financialManagement.setLandsideManagement(landsideManagement);

        airportManagementSystem = AirportManagementSystem.of();
        airportManagementSystem.setAirsideManagement(airsideManagement);
        airportManagementSystem.setLandsideManagement(landsideManagement);
        airportManagementSystem.setFinancialManagement(financialManagement);
        airportManagementSystem.setTerminalManagement(terminalManagement);
    }

    @Test
    void reportCosts() {
        assertTrue(airportManagementSystem.reportCosts() > 0.);
    }

    @Test
    void provideGateInfoNoGate() {
        terminalManagement.getFlights().get(0).setGate(null);
        assertThrows(Exception.class, () -> airportManagementSystem.provideGateInfo(terminalManagement.getFlights().get(0).getFlightNumber()));
    }

    @Test
    void provideGateInfoInvalidFlight() {
        assertThrows(Exception.class, () -> airportManagementSystem.provideGateInfo(666666));
    }

    @Test
    void provideGateInfo() {
        assertDoesNotThrow(() -> airportManagementSystem.provideGateInfo(terminalManagement.getFlights().get(0).getFlightNumber()));
    }

    @Test
    void provideFlightInfo() {
        assertDoesNotThrow(airportManagementSystem::provideFlightInfo);
    }
}