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
        hatchClosed = false;
        log.info("Plane fuel hatch opened");
    }

    public void closePlaneFuelHatch() {
        hatchClosed = true;
        log.info("Plane fuel hatch closed");
    }

    public void reportDefectPump() throws Exception {
        pumpDefect = true;
        throw new Exception("Pump defect");
    }

    public double reportFuelUsage() {
        log.info("Used " + (refuelTankCapacity - currentRefillFuel) + " litres of fuel");
        return refuelTankCapacity - currentRefillFuel;
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

    public double refuelPlane() throws Exception {
        log.info("Refuel plane");
        openPlaneFuelHatch();
        startFuelPump(5);
        if(pumpDefect) {
            reportDefectPump();
        }
        stopFuelPump();
        closePlaneFuelHatch();

        return reportFuelUsage();
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

    public void checkTirePressure() throws Exception {
        toggleCompressor();
        startAirPump();
        stopAirPump();
        toggleCompressor();
    }
}
