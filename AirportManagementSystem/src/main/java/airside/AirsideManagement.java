package airside;

import java.util.ArrayList;

import airside.model.AircraftMarshaller;
import airside.model.Plane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import overall.AirportSubsystem;

/**
 * @author Jonas Reichhardt
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AirsideManagement extends AirportSubsystem {
	ArrayList<Plane> planes = new ArrayList<>();
	ArrayList<AircraftMarshaller> marshaller = new ArrayList<>();
}
