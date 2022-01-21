package overall;
import airside.AirsideManagement;
import financial.FinancialManagement;
import landside.LandsideManagement;
import landside.model.destination.Destination;
import landside.model.destination.Gate;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import terminal.TerminalManagement;
import terminal.model.FlightInformation;

import java.util.logging.Level;

/**
 * @author Simon Primetzhofer
 */

@Log
@NoArgsConstructor(staticName = "of")
public class AirportManagementSystem {

    private final AirsideManagement airsideManagement = AirsideManagement.of();
    private final LandsideManagement landsideManagement = LandsideManagement.of();
    private final TerminalManagement terminalManagement = TerminalManagement.of();
    private final FinancialManagement financialManagement = FinancialManagement.of();

    public double reportCosts() {
        double runWayCost = airsideManagement.gatherRunwayCost();

        double landsideCost = financialManagement.requestLandsideCost();
        double airsideCost = financialManagement.requestAirsideCost();

        financialManagement.billAirlines(landsideCost, airsideCost);
        financialManagement.monitorPayments();

        return runWayCost + landsideCost + airsideCost;
    }

    public void provideGateInfo(int flightNo) {
        Destination passengerDest = terminalManagement.requestPassengerTransport();
        Destination baggageDest = terminalManagement.requestBaggageTransport();

        Gate gate = terminalManagement.requestDestination(flightNo);
        terminalManagement.assignGate(terminalManagement.getFlightInformation(flightNo),gate);
        landsideManagement.conductBusToGate();
        landsideManagement.conductBaggageCartToDeposit();
    }

    public void provideFlightInfo() {
        terminalManagement.getFlights().stream().map(FlightInformation::getFlightNumber).forEach(flightNo -> {
            FlightInformation flight = airsideManagement.requestFlightInformation(flightNo);
            terminalManagement.adjustTime(flight);
            terminalManagement.displayFlightInformation(flight);
        });
    }
}
