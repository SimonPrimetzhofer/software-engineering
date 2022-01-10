package landside;

import landside.model.destination.Destination;
import landside.model.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan Haslhofer
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Log
public class LandsideManagement {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Destination> destinations = new ArrayList<>();
}
