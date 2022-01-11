package landside.model.destination;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Stefan Haslhofer
 */
@RequiredArgsConstructor
@Getter
@Setter
public abstract class Destination {
    protected final int buildingNr;
}
