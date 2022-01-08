package landside.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * @author Stefan Haslhofer
 */
@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@Log
public class MaintenanceVehicle extends Vehicle {
    private boolean hatchClosed;

    public void openHatch() {
        hatchClosed = false;
    }

    public void closeHatch() {
        hatchClosed = true;
    }
}
