package landside.model.vehicle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * @author Stefan Haslhofer
 */
@Getter
@Setter
@Log
public class BaggageCart extends Vehicle {
    private int storedCases;
    private final int numOfCasesStoreable;
    private boolean hatchClosed;

    public BaggageCart(double maxFuel, int numOfCasesStoreable) {
        super(maxFuel);
        this.numOfCasesStoreable = numOfCasesStoreable;
    }

    public void loadPassengers(int p) throws Exception {
        if(!hatchClosed) {
            log.info("Loading baggage...");
            if (p <= numOfCasesStoreable) {
                storedCases = p;
            } else {
                storedCases = numOfCasesStoreable;
                throw new Exception("More room needed");
            }
        }
    }

    public void unloadPassengers() {
        if(!hatchClosed) {
            storedCases = 0;
        }
    }

    public void openHatch() {
        hatchClosed = false;
    }

    public void closeHatch() {
        hatchClosed = true;
    }
}
