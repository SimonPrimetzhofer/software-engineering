package landside;

import financial.model.Employee;
import landside.helper.Service;
import landside.model.Driver;
import landside.model.destination.*;
import landside.model.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import overall.AirportSubsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static landside.helper.Service.MAINTAIN;

/**
 * @author Stefan Haslhofer
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Log
public class LandsideManagement extends AirportSubsystem {
    public final List<Vehicle> vehicles = new ArrayList<>();
    public final List<Destination> destinations = new ArrayList<>();
    public final List<Driver> employees = new ArrayList<>();
    double maintenanceCost;
    double operatingCost;

    /**
     * @return cost of plane-fuel that needs to be refilled
     */
    public double gatherMaintenanceCost() {
        log.info("Current maintenance costs: " + maintenanceCost + "€");
        return maintenanceCost;
    }

    public double provideLandsideCost() {
        // the cost is the maintenance cost + the needed fuel
        log.info("Current overall costs: " + (operatingCost + maintenanceCost) + "€");
        return operatingCost + maintenanceCost;
    }

    public void assignGate(Driver driver, Gate destination) {
        log.info("Assigned gate to driver");
        driver.setDest(destination);
    }

    /**
     * a plane stays on a parking spot
     */
    public void assignPlane(Driver driver, ParkingSpot parkingSpot) {
        log.info("Assigned plane´s parking spot to driver");
        driver.setDest(parkingSpot);
    }

    public void assignBaggageDepot(Driver driver, Deposit destination) {
        log.info("Assigned baggage depot to driver");
        driver.setDest(destination);
    }

    public void communicateNeededServices(Driver driver, ParkingSpot parkingSpot, Service service) throws Exception {
        log.info("Communicating services...");
        assignPlane(driver, parkingSpot);
        communicateServices(driver, service);
    }

    public void communicateServices(Driver driver, Service service) throws Exception {
        if (service.equals(MAINTAIN)) {
            log.info("Maintenance task required");
            maintenanceCost += driver.maintainPlane() * Constants.FUEL_PRICE_PER_LITRE;
        } else {
            log.info("Emergency occured");
            maintenanceCost += driver.reactToEmergency() * Constants.WATER_PRICE_PER_LITRE;
        }
    }

    public void requestFuelStatus(Driver driver) throws Exception {
        // refuel vehicle if fuel drops below 20%
        if (driver.getVehicle().reportFuelConsumption() > driver.getVehicle().getMaxFuel() * 0.8) {
            log.info("Refueling vehicle...");
            operatingCost += driver.refuel(getFuelDepot()) * Constants.FUEL_PRICE_PER_LITRE;
            log.info("Finished refueling");
        }
    }

    public Destination requestDestination(Driver driver) {
        return driver.getDest();
    }

    public FuelDepot getFuelDepot() throws Exception {
        Optional<Destination> depot = destinations.stream().filter(d -> d instanceof FuelDepot).findFirst();
        if (depot.isPresent()) {
            return (FuelDepot) depot.get();
        } else {
            throw new Exception("Fuel depot missing");
        }
    }
}
