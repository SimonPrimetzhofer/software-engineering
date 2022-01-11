package airside.model;

import lombok.extern.java.Log;

@Log
public class CabinCrew {

	public boolean informPassengers() {
		log.info("inform passengers to fasten their seatbelts");
		return true;
	}

	public boolean departPassengers() {
		log.info("depart passengers row-wise");
		return true;
	}
}
