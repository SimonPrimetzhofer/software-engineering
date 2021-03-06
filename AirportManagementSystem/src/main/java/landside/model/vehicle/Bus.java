package landside.model.vehicle;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * @author Stefan Haslhofer
 */
@Getter
@Setter
@Log
public class Bus extends Vehicle {
    private int passengersLoaded;
    @NonNull
    private final Integer numOfSeats;
    private boolean doorsClosed;

    public Bus(double maxFuel, int numOfSeats) {
        super(maxFuel);
        this.numOfSeats = numOfSeats;
    }

    public void loadPassengers(int p) throws Exception {
        // only load as many passengers as there are seats only when the door is opened
        if (!doorsClosed) {
            if (p <= numOfSeats) {
                log.info("Loading passengers...");
                passengersLoaded = p;
            } else {
                passengersLoaded = numOfSeats;
                throw new Exception("Too few seats available");
            }
        } else {
            throw new Exception("Doors are still closed");
        }
    }

    public void unloadPassengers() throws Exception {
        if (!doorsClosed) {
            log.info("Unload passengers...");
            passengersLoaded = 0;
        } else {
            throw new Exception("Doors are still closed");
        }
    }

    public void openDoor() {
        doorsClosed = false;
        log.info("Doors opened");
    }

    public void closeDoor() {
        doorsClosed = true;
        log.info("Doors closed");
    }
}
