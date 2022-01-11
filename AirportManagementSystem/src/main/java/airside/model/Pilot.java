package airside.model;

import java.util.ArrayList;
import java.util.Collections;

import financial.model.Employee;
import landside.LandsideManagement;
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
public class Pilot extends Employee {
	ArrayList<Boolean> checklist = new ArrayList<>();
	Plane plane;

	public Pilot(@NonNull String firstName, @NonNull String lastName, @NonNull Integer salary,
			@NonNull AirportSubsystem department, ArrayList<Boolean> checklist) {
		super(firstName, lastName, salary, department);
		this.checklist = checklist;
	}

	public boolean doChecklist() {
		log.info("start going through take off checklist");
		Collections.fill(checklist, true);
		log.info("checklist done");
		return true;
	}

	public int requestStartPermission(AircraftMarshaller marshaller) {
		log.info("requesting starting permission from " + marshaller.getFullName());
		return marshaller.grantPermission(this.getFullName());
	}

	public boolean startTakeOff() {
		log.info("informing cabin crew that take off has started");
		return plane.cabinCrew.informPassengers();
	}

	public boolean moveToStrip(int strip) {
		log.info("moving to strip " + strip);
		return true;
	}

	public boolean takeOff(int strip) {
		log.info("taking off " + strip);
		return true;
	}

	public boolean requestRefuel(LandsideManagement landside, Plane plane, ParkingSpot spot) throws Exception {
		log.info(this.getFullName() + " requests refuel from landside management");
		return landside.requestRefuel(plane, spot);
	}

}
