package terminal;

import airside.AirsideManagement;
import landside.LandsideManagement;
import landside.model.Driver;
import landside.model.destination.Deposit;
import landside.model.destination.Destination;
import landside.model.destination.Gate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import overall.AirportSubsystem;
import terminal.model.FlightInformation;

import java.util.*;

/**
 * @author Simon Primetzhofer
 */

@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
@Log
@Setter
@Getter
public class TerminalManagement implements AirportSubsystem {

    private LandsideManagement landsideManagement;
    private AirsideManagement airsideManagement;
    private List<FlightInformation> flights = List.of(FlightInformation.of(6942023, "Eurowings", "Crete", new Date(), 150., new Gate(1, 300, 1), new Deposit(3, 4500, 2), 349.99, 149.99, 666.66),
            FlightInformation.of(654654645, "Deez nuts", "Kekistan", new Date(), 999.45, new Gate(2, 666, 3), new Deposit(1, 5000, 3), 349.99, 149.99, 666.66));

    public Destination requestPassengerTransport() {
        Optional<Driver> driver = landsideManagement.employees.stream().findAny();
        return landsideManagement.requestDestination(driver.orElseGet(() -> new Driver("Max", "Mustermann", 333, landsideManagement, 55431)));
    }

    public Destination requestBaggageTransport() {
        Optional<Driver> driver = landsideManagement.employees.stream().findAny();
        return landsideManagement.requestDestination(driver.orElseGet(() -> new Driver("Max", "Mustermann", 333, landsideManagement, 55431)));
    }

    public FlightInformation getFlightInformation(int flightNo) {
        return flights.stream().filter(flight -> flight.getFlightNumber() == flightNo).findFirst().orElse(null);
    }

    public void displayFlightInformation(FlightInformation flight) {
        log.info(flight.toString());
    }

    public void adjustTime(FlightInformation flight) {
        int delay = airsideManagement.provideDelays();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(flight.getDepartureTime());
        calendar.add(Calendar.HOUR_OF_DAY, delay);
        flight.setDepartureTime(calendar.getTime());
    }

    public Gate requestDestination(int flightNo) {
        return airsideManagement.provideParkingLocation(flightNo);
    }

    public void assignGate(FlightInformation flight, Gate gate) throws Exception {
        if (flight == null) {
            throw new Exception("Flight does not exist!");
        }
        if (gate == null) {
            throw new Exception("Gate does not exist!");
        }
        flight.setGate(gate);
    }

}
