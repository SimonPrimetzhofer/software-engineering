package airside;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airside.model.AircraftMarshaller;
import airside.model.CabinCrew;
import airside.model.Pilot;
import airside.model.Plane;
import financial.model.Employee;
import landside.model.destination.ParkingSpot;

public class TakeOffTest {
	AircraftMarshaller marshall;
	Pilot pilot;
	CabinCrew cabinCrew;
	AirsideManagement airside;
	ArrayList<Boolean> checklist;
	ArrayList<Boolean> strips;
	int startingStrip = 1;
	Plane plane;

	@BeforeEach
	void setUp() {
		airside = new AirsideManagement();
		var crew = new ArrayList<Employee>() {
			{
				add(new Employee("Max", "Mustermann", 10000, airside));
			}
		};
		var parkingSpots = new ArrayList<ParkingSpot>() {
			{
				add(new ParkingSpot(1));
			}
		};
		checklist = new ArrayList<Boolean>() {
			{
				add(new Boolean(false));
				add(new Boolean(false));
			}
		};
		strips = new ArrayList<Boolean>() {
			{
				add(false);
				add(false);
				add(false);
			}
		};
		pilot = new Pilot("Max", "Mustermann", 10000, airside, checklist);
		cabinCrew = new CabinCrew(crew);
		marshall = new AircraftMarshaller("Max", "Mustermann", 10000, airside, strips, parkingSpots);
		plane = new Plane(pilot, cabinCrew);
		pilot.setPlane(plane);
	}

	@Test
	void testDoChecklist() {
		pilot.doChecklist();
		pilot.getChecklist().forEach(x -> assertTrue(x.booleanValue()));
	}

	@Test
	void testRequestStartPermission() {
		int startingStrip = pilot.requestStartPermission(marshall);
		assertFalse(marshall.getStrips().get(startingStrip));
	}

	@Test
	void testStartTakeOff() {
		assertDoesNotThrow(() -> pilot.startTakeOff());
		assertTrue(pilot.startTakeOff());
	}

	@Test
	void testMoveToStrip() {
		assertDoesNotThrow(() -> pilot.moveToStrip(startingStrip));
		assertTrue(pilot.moveToStrip(startingStrip));
	}

	@Test
	void testTakeOff() {
		assertDoesNotThrow(() -> pilot.takeOff(startingStrip));
		assertTrue(pilot.takeOff(startingStrip));
	}
}
