package airside;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import airside.model.AircraftMarshaller;
import airside.model.CabinCrew;
import airside.model.Pilot;
import financial.model.Employee;
import landside.model.destination.ParkingSpot;

public class TakeOffTest {
	AircraftMarshaller marshall;
	Pilot pilot;
	CabinCrew cabinCrew;
	AirsideManagement airside;
	ArrayList<Boolean> checklist = (ArrayList<Boolean>) List.of(false, false);
	ArrayList<Boolean> strips = (ArrayList<Boolean>) List.of(false, false, false);
	int startingStrip = 1;

	@BeforeEach
	void setUp() {
		airside = new AirsideManagement();
		pilot = new Pilot("Max", "Mustermann", 10000, airside, checklist);
		cabinCrew = new CabinCrew((ArrayList<Employee>) List.of(new Employee("Max", "Mustermann", 10000, airside)));
		marshall = new AircraftMarshaller("Max", "Mustermann", 10000, airside, strips,
				(ArrayList<ParkingSpot>) List.of(new ParkingSpot(1)));
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
