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
    private final Integer gateNr;
    @NonNull
    private final Integer passengerCapacity;

    private boolean isOpen;

    public Gate(int gateNr, int passengerCapacity, int buildingNr) {
        super(buildingNr);
        this.gateNr = gateNr;
        this.passengerCapacity = passengerCapacity;
    }
}
