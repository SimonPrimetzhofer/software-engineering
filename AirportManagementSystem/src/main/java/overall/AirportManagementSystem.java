package overall;
import airside.AirsideManagement;
import financial.FinancialManagement;
import landside.LandsideManagement;
import landside.model.destination.Destination;
import landside.model.destination.Gate;
import lombok.*;
import lombok.extern.java.Log;
import terminal.TerminalManagement;
import terminal.model.FlightInformation;

import java.util.Iterator;
import java.util.logging.Level;

/**
 * @author Simon Primetzhofer
 */

@Log
@NoArgsConstructor(staticName = "of")
@Setter
public class AirportManagementSystem {

    private AirsideManagement airsideManagement = AirsideManagement.of();
    private LandsideManagement landsideManagement = LandsideManagement.of();
    private TerminalManagement terminalManagement = TerminalManagement.of();
    private FinancialManagement financialManagement = FinancialManagement.of();

    public double reportCosts() {
        double runWayCost = airsideManagement.gatherRunwayCost();

        double landsideCost = financialManagement.requestLandsideCost();
        double airsideCost = financialManagement.requestAirsideCost();

        financialManagement.billAirlines(airsideCost, landsideCost);
        financialManagement.monitorPayments();

        return runWayCost + landsideCost + airsideCost;
    }

    public Gate provideGateInfo(int flightNo) throws Exception {
        Destination passengerDest = terminalManagement.requestPassengerTransport();
        Destination baggageDest = terminalManagement.requestBaggageTransport();

        Gate gate = terminalManagement.requestDestination(flightNo);
        terminalManagement.assignGate(terminalManagement.getFlightInformation(flightNo),gate);
        landsideManagement.conductBusToGate(passengerDest);
        landsideManagement.conductBaggageCartToDeposit(baggageDest);

        return gate;
    }

    public void provideFlightInfo() throws Exception{
        for (Iterator<Integer> it = terminalManagement.getFlights().stream().map(FlightInformation::getFlightNumber).iterator(); it.hasNext(); ) {
            Integer flightNo = it.next();
            FlightInformation flight = airsideManagement.requestFlightInformation(flightNo);
            if (flight == null) {
                throw new Exception("Flight does not exist!");
            }
            terminalManagement.adjustTime(flight);
            terminalManagement.displayFlightInformation(flight);
        }
    }
}
