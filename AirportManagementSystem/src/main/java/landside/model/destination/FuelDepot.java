package landside.model.destination;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Stefan Haslhofer
 */
@Getter
@Setter
public class FuelDepot extends Destination{
    @NonNull
    private final Double fueltankCapacity;

    public FuelDepot(double fueltankCapacity, int buildingNr) {
        super(buildingNr);
        this.fueltankCapacity = fueltankCapacity;
    }
}
