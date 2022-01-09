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
    private final double fueltankCapacity;

    public FuelDepot(@NonNull double fueltankCapacity, @NonNull int buildingNr) {
        super(buildingNr);
        this.fueltankCapacity = fueltankCapacity;
    }
}
