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
import landside.model.Driver;
import landside.model.destination.Destination;
import landside.model.destination.ParkingSpot;
import landside.model.vehicle.MaintenanceVehicle;
import landside.model.vehicle.Vehicle;

public class RefuelAircraftTest {
	Pilot pilot;
	CabinCrew cabinCrew;
	AirsideManagement airside;
	LandsideManagement landside;
	ArrayList<Boolean> checklist;
	ParkingSpot spot;
	Plane plane;

	public final List<Vehicle> vehicles = new ArrayList<>();
	public final List<Destination> destinations = new ArrayList<>();
	public final List<Driver> drivers = new ArrayList<>();

	@BeforeEach
	void setUp() {
		airside = new AirsideManagement();
		landside = new LandsideManagement();

		checklist = new ArrayList<Boolean>() {
			{
				add(false);
				add(false);
			}
		};

		Vehicle v = new MaintenanceVehicle(1, 2, 3);
		Driver d = new Driver("Max", "Mustermann", 10000, landside, 1);
		d.setVehicle(v);
		vehicles.add(v);
		v.setInGarage(true);
		d.setVehicle(v);
		drivers.add(d);
		landside.employees = drivers;

		pilot = new Pilot("Max", "Mustermann", 10000, airside, checklist);
		cabinCrew = new CabinCrew(new ArrayList<Employee>() {
			{
				add(new Employee("Max", "Mustermann", 10000, airside));
			}
		});
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
