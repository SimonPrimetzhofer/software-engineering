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
public class EmergencyVehicle extends Vehicle {
    private double extinguishingWater;
    private boolean waterPumpActive;
    @NonNull
    private final Double waterCapacity;
    @NonNull
    private final Double waterPumpThroughput;

    public EmergencyVehicle(double maxFuel, double waterCapacity, double waterPumpThroughput) {
        super(maxFuel);
        this.waterCapacity = waterCapacity;
        this.waterPumpThroughput = waterPumpThroughput;
    }

    /**
     * refills the water tank
     */
    public void refillWaterTank() {
        this.extinguishingWater = waterCapacity;
        log.info("Water tank refilled");
    }

    public double reportWaterUsage() {
        log.info("Used " + (waterCapacity - extinguishingWater) + " litres of fuel");
        return waterCapacity - extinguishingWater;
    }

    public void startWaterPump(int s) {
        waterPumpActive = true;
        // decrease water by throughput times active seconds
        extinguishingWater -= s * waterPumpThroughput;
        log.info("Water pump started");
    }

    public void stopWaterPump() {
        waterPumpActive = false;
        log.info("Water pump stopped");
    }
}
