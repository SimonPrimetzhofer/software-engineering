package airside.model;

import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plane {
	String name;
	String manufacturer;
	int numSeats;
	int maxFuelCapacity = ThreadLocalRandom.current().nextInt(0, 320000 + 1);
	int currentFuel = ThreadLocalRandom.current().nextInt(0, 320000 + 1);
	double tirePressure = ThreadLocalRandom.current().nextInt(0, 320000 + 1);

	Pilot pilot;
	CabinCrew cabinCrew;

	public Plane(Pilot pilot, CabinCrew cabinCrew) {
		// TODO remove random calculation
		maxFuelCapacity = ThreadLocalRandom.current().nextInt(0, 320000 + 1);
		currentFuel = ThreadLocalRandom.current().nextInt(0, maxFuelCapacity + 1);
		tirePressure = ThreadLocalRandom.current().nextDouble(0, 15 + 1);
		numSeats = ThreadLocalRandom.current().nextInt(25, 500 + 1);
		this.pilot = pilot;
		this.cabinCrew = cabinCrew;
	}

}