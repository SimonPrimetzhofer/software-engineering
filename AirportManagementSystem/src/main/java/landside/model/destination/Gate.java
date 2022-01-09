package landside.model.destination;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Stefan Haslhofer
 */
@Getter
@Setter
public class Gate extends Destination {
    @NonNull
    private final int gateNr;
    @NonNull
    private final int passengerCapacity;

    private boolean isOpen;

    public Gate(@NonNull int gateNr, @NonNull int passengerCapacity, @NonNull int buildingNr) {
        super(buildingNr);
        this.gateNr = gateNr;
        this.passengerCapacity = passengerCapacity;
    }
}
