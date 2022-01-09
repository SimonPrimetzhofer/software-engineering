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
public class EmergencyVehicle extends Vehicle {
    private double extinguishingWater;
    private boolean waterPumpActive;
    private final double waterCapacity;
    private final double waterPumpThroughput;

    public EmergencyVehicle(double maxFuel, double waterCapacity, double waterPumpThroughput) {
        super(maxFuel);
        this.waterCapacity = waterCapacity;
        this.waterPumpThroughput = waterPumpThroughput;
    }

    /**
     * refills the water tank
     *
     * @return litres of water refilled
     */
    public double refillWaterTank() {
        double amount = waterCapacity - extinguishingWater;
        this.extinguishingWater = waterCapacity;
        return amount;
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
