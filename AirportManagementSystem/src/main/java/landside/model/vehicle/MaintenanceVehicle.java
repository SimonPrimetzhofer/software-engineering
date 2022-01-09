package landside.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * @author Stefan Haslhofer
 */
@Getter
@Setter
@Log
public class MaintenanceVehicle extends Vehicle {
    private boolean hatchClosed;
    private boolean fuelPumpActive;
    private boolean airPumpActive;
    private boolean compressorActive;
    private boolean pumpDefect;

    private final double airPumpThroughput;
    private final double fuelPumpThroughput;

    private int refuelTankCapacity;
    private int currentRefillFuel;

    public MaintenanceVehicle(double maxFuel, double airPumpThroughput, double fuelPumpThroughput) {
        super(maxFuel);
        this.airPumpThroughput = airPumpThroughput;
        this.fuelPumpThroughput = fuelPumpThroughput;
    }

    public void openPlaneFuelHatch() {
        // TODO parkingSpot and plane have to be implemented
        //if (this.destination instanceof ParkingSpot) {
        //
        //}
    }

    public void closePlaneFuelHatch() {

    }

    public void reportDefectPump() throws Exception {
        pumpDefect = true;
        throw new Exception("Pump defect");
    }

    public double reportFuelUsage() {
        log.info("Used " + (refuelTankCapacity - currentRefillFuel) + " litres of fuel");
        return refuelTankCapacity - currentRefillFuel;
    }

    public void openHatch() {
        hatchClosed = false;
        log.info("Hatch opened");
    }

    public void closeHatch() {
        hatchClosed = true;
        log.info("Hatch closed");
    }

    public void startFuelPump(int s) throws Exception {
        if (!pumpDefect) {
            fuelPumpActive = true;
            // decrease fuel by throughput times active seconds
            currentRefillFuel -= s * fuelPumpThroughput;
            log.info("Fuelpump started");
        } else {
            reportDefectPump();
        }
    }

    public void stopFuelPump() {
        fuelPumpActive = false;
        log.info("Fuelpump stopped");
    }

    public void startAirPump() throws Exception {
        if (!pumpDefect) {
            airPumpActive = true;
            log.info("Airpump started");
        } else {
            reportDefectPump();
        }
    }

    public void stopAirPump() {
        airPumpActive = false;
        log.info("Airpump stopped");
    }

    public void toggleCompressor() {
        compressorActive = !compressorActive;
        if (compressorActive) {
            log.info("Compressor started");
        } else {
            log.info("Compressor stopped");
        }
    }
}
