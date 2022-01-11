package airside.model;

import java.util.ArrayList;

import financial.model.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

/**
 * @author Jonas Reichhardt
 */
@Log
@AllArgsConstructor
@NoArgsConstructor
public class CabinCrew {
	ArrayList<Employee> members = new ArrayList<>();

	public boolean informPassengers() {
		log.info("inform passengers to fasten their seatbelts");
		return true;
	}

	public boolean departPassengers() {
		log.info("depart passengers row-wise");
		return true;
	}
}
