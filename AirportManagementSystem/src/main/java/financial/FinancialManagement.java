package financial;

import airside.AirsideManagement;
import financial.model.Airline;
import financial.model.FinancialManagementEmployee;
import landside.LandsideManagement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import overall.AirportSubsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kaan Baylan
 */

@Getter
@Setter
@NoArgsConstructor (staticName = "of")
@Log
public class FinancialManagement implements AirportSubsystem {
    public List<FinancialManagementEmployee> fmes = new ArrayList<>();
    private List<Airline> airlines = List.of();

    private LandsideManagement landsideManagement;
    private AirsideManagement airsideManagement;

    public double requestAirsideCost() {
        return airsideManagement.provideAirsideCost();
    }

    public double requestLandsideCost() {
        return landsideManagement.provideLandsideCost();
    }

    public void billAirlines(double airsideCost, double landsideCost) {
        log.info(String.format("Billing %f for airside and %f for landside management", airsideCost, landsideCost));

        Optional<Airline> airline = airlines.stream().findAny();
        airline.ifPresent(a -> a.payPrice(airsideCost + landsideCost));
    }

    public void monitorPayments() {
        log.info("Monitoring payments...");
    }
}
