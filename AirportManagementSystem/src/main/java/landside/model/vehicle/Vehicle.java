package landside.model.vehicle;

import landside.model.destination.Destination;
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
public class Vehicle {
    protected Destination destination;
    protected boolean inGarage;
    protected boolean isReady;
    protected boolean fuelCapClosed;
    protected double fuel;
    protected final double maxFuel;

    public Vehicle(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public double reportFuelConsumption() {
        log.info("Used " + (maxFuel - fuel) + " litres of fuel");
        return maxFuel - fuel;
    }

    public void openFuelCap() {
        fuelCapClosed = false;
        log.info("Fuel-cap opened");
    }

    public void closeFuelCap() {
        fuelCapClosed = true;
        log.info("Fuel-cap closed");
    }
}
