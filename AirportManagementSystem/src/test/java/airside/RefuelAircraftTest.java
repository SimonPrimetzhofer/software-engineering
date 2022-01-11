package airside;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airside.model.CabinCrew;
import airside.model.Pilot;
import airside.model.Plane;
import financial.model.Employee;
import landside.LandsideManagement;
import landside.model.destination.ParkingSpot;

public class RefuelAircraftTest {
	Pilot pilot;
	CabinCrew cabinCrew;
	AirsideManagement airside;
	LandsideManagement landside;
	ArrayList<Boolean> checklist = (ArrayList<Boolean>) List.of(false, false);
	ParkingSpot spot;
	Plane plane;

	@BeforeEach
	void setUp() {
		airside = new AirsideManagement();
		landside = new LandsideManagement();
		pilot = new Pilot("Max", "Mustermann", 10000, airside, checklist);
		cabinCrew = new CabinCrew((ArrayList<Employee>) List.of(new Employee("Max", "Mustermann", 10000, airside)));
		spot = new ParkingSpot(1);
		plane = new Plane(pilot, cabinCrew);
	}

	@Test
	void testrequestRefuel() {
		assertDoesNotThrow(() -> pilot.requestRefuel(landside, plane, spot));
		try {
			assertTrue(pilot.requestRefuel(landside, plane, spot));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
