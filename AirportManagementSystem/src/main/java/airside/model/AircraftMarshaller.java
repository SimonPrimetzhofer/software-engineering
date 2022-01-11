package airside.model;

import java.util.ArrayList;

import financial.model.Employee;
import landside.model.destination.ParkingSpot;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;
import overall.AirportSubsystem;

/**
 * @author Jonas Reichhardt
 */
@Getter
@Setter
@Log
public class AircraftMarshaller extends Employee {

	ArrayList<Boolean> strips = new ArrayList<>();
	ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();

	public AircraftMarshaller(@NonNull String firstName, @NonNull String lastName, @NonNull Integer salary,
			@NonNull AirportSubsystem department, ArrayList<Boolean> strips, ArrayList<ParkingSpot> parkingSpots) {
		super(firstName, lastName, salary, department);
		this.strips = strips;
		this.parkingSpots = parkingSpots;
	}

	public int grantPermission(String pilotName) {
		int stripNr = strips.indexOf(strips.stream().anyMatch(s -> s));
		log.info("starting clearance for " + pilotName + " on strip " + stripNr + " provided");
		return stripNr;
	}
}
