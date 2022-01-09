package landside.model.destination;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Stefan Haslhofer
 */
@Getter
@Setter
public class Deposit extends Destination{
    @NonNull
    private final int depositNr;

    @NonNull
    private final int storageCapacity;

    private int storedCases;

    public Deposit(@NonNull int depositNr,@NonNull int storageCapacity,@NonNull int buildingNr) {
        super(buildingNr);
        this.depositNr = depositNr;
        this.storageCapacity = storageCapacity;
    }
}
