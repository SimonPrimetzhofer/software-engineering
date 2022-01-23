package airside;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import airside.model.AircraftMarshaller;
import airside.model.Plane;
import landside.model.destination.Destination;
import landside.model.destination.Gate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import overall.AirportSubsystem;
import terminal.TerminalManagement;
import terminal.model.FlightInformation;

/**
 * @author Jonas Reichhardt
 */
@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
@Getter
public class AirsideManagement implements AirportSubsystem {
	@Setter
	private TerminalManagement terminalManagement;
	ArrayList<Plane> planes = new ArrayList<>();
	public ArrayList<AircraftMarshaller> marshaller = new ArrayList<>();

	public double gatherRunwayCost() {
		Random rand = new Random();
		return rand.nextDouble() * 4000.;
	}

	public double provideAirsideCost() {
		Random rand = new Random();
		return rand.nextDouble() * 8000.;
	}

	public FlightInformation requestFlightInformation(int flightNo) {
		return terminalManagement.getFlightInformation(flightNo);
	}

	public int provideDelays() {
		Random rand = new Random();
		if (rand.nextInt(10000) % 5 == 0) {
			return rand.nextInt(10);
		}

		return 0;
	}

	public Gate provideParkingLocation(int flightNo) {
		Optional<FlightInformation> flightInformation = terminalManagement.getFlights().stream().filter(flight -> flight.getFlightNumber() == flightNo).findFirst();
		return flightInformation.map(FlightInformation::getGate).orElse(null);
	}
}
