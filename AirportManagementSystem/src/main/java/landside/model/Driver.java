package landside.model;

import landside.model.destination.Destination;
import landside.model.destination.FuelDepot;
import landside.model.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Log
public class Driver {
    private int licenseId;
    private Vehicle vehicle;

    public void driveToDestination(Destination dest) {
        vehicle.setDestination(dest);
        log.info("Driving to destination...");
    }

    public void driveToGarage(){
        vehicle.setDestination(null);
        vehicle.setInGarage(true);
        log.info("Driving back to garage...");
    }

    public void reportStatus() {

    }

    public void driveToFuelDepot(FuelDepot depot) {
        vehicle.setDestination(depot);
        log.info("Driving to fuel depot...");
    }

    public void refuelVehicle() {
        vehicle.openFuelCap();

    }
}
