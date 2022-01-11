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
    private final int depositNr;

    private final int storageCapacity;

    private int storedCases;

    public Deposit(int depositNr,int storageCapacity,int buildingNr) {
        super(buildingNr);
        this.depositNr = depositNr;
        this.storageCapacity = storageCapacity;
    }
}
