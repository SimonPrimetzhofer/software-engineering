package landside.model.destination;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpot extends Destination {
	boolean isUsed;
	String id;

	public ParkingSpot(int buildingNr) {
		super(buildingNr);
		isUsed = false;
		id = UUID.randomUUID().toString().substring(0, 4);
	}
}
