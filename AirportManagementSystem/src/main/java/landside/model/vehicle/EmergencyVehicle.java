package landside.model.vehicle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Stefan Haslhofer
 */
@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
public class EmergencyVehicle extends Vehicle {
    private double extinguishingWater;
    private boolean waterPumpActive;
    private final double WATER_CAPACITY;

    /**
     * refills the water tank
     *
     * @return litres of water refilled
     */
    public double refillWaterTank() {
        double amount = WATER_CAPACITY - extinguishingWater;
        this.extinguishingWater = WATER_CAPACITY;
        return amount;
    }

    public void startWaterPump() {
        waterPumpActive = true;
    }

    public void stopWaterPump() {
        waterPumpActive = false;
    }
}
