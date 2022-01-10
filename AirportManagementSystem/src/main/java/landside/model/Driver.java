package landside.model;

import landside.model.destination.Destination;
import landside.model.destination.FuelDepot;
import landside.model.vehicle.Bus;
import landside.model.vehicle.EmergencyVehicle;
import landside.model.vehicle.MaintenanceVehicle;
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
    private Destination dest;

    public void driveToDestination(Destination dest) {
        vehicle.setDestination(dest);
        log.info("Driving to destination...");
    }

    public void driveToGarage(){
        vehicle.setDestination(null);
        vehicle.setInGarage(true);
        log.info("Driving back to garage...");
    }

    public void driveToFuelDepot(FuelDepot depot) {
        vehicle.setDestination(depot);
        log.info("Driving to fuel depot...");
    }

    public double refuel(FuelDepot depot) {
        driveToFuelDepot(depot);
        vehicle.openFuelCap();
        double fuelConsumption = refuelVehicle();
        vehicle.closeFuelCap();

        return fuelConsumption;
    }

    public double refuelVehicle() {
        log.info("Refuel vehicle");
        double fuelConsumption = vehicle.reportFuelConsumption();
        vehicle.setFuel(vehicle.getMaxFuel());
        return fuelConsumption;
    }

    public double maintainPlane() throws Exception {
        if(!(vehicle instanceof MaintenanceVehicle)) {
            throw new Exception("No maintenance vehicle. Task cannot be executed.");
        }
        driveToDestination(dest);
        double fuelUsage = ((MaintenanceVehicle) vehicle).refuelPlane();
        driveToGarage();

        return fuelUsage;
    }

    public double reactToEmergency() throws Exception {
        if(!(vehicle instanceof EmergencyVehicle)) {
            throw new Exception("No emergency vehicle. Task cannot be executed.");
        }
        driveToDestination(dest);
        ((EmergencyVehicle) vehicle).startWaterPump(5);
        double waterUsage = ((EmergencyVehicle) vehicle).reportWaterUsage();
        driveToGarage();
        ((EmergencyVehicle) vehicle).refillWaterTank();

        return waterUsage;
    }
}
