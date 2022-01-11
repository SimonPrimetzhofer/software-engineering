package airside.model;

import java.util.ArrayList;

import financial.model.Employee;
import landside.model.destination.ParkingSpot;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;
import overall.AirportSubsystem;

@Getter
@Setter
@Log
public class AircraftMarshaller extends Employee {

	ArrayList<Integer> strips = new ArrayList<>();
	ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();

	public AircraftMarshaller(@NonNull String firstName, @NonNull String lastName, @NonNull Integer salary,
			@NonNull AirportSubsystem department, ArrayList<Integer> strips, ArrayList<ParkingSpot> parkingSpots) {
		super(firstName, lastName, salary, department);
		this.strips = strips;
		this.parkingSpots = parkingSpots;
	}

	public boolean provideLandingClearance(String planeName, int landingStrip) {
		log.info("landing clearance for " + planeName + " on strip " + landingStrip + " provided");
		return true;
	}

	public boolean provideStartingClearance(String planeName, int landingStrip) {
		log.info("starting clearance for " + planeName + " on strip " + landingStrip + " provided");
		return true;
	}

	public ParkingSpot provideGateAndParking(String planeName) {
		ParkingSpot freeSpot = parkingSpots.stream().filter(s -> s.isUsed()).findFirst().get();
		log.info(planeName + " will park on spot " + freeSpot.getId());
		return freeSpot;
	}
}
