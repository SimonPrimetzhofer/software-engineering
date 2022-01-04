package terminal.model;

import lombok.*;
import lombok.extern.java.Log;
import terminal.helper.TravelReason;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Simon Primetzhofer
 */

@AllArgsConstructor(staticName = "of")
@Log
@Getter
@Setter
@ToString
public class Passenger {

    public static final List<String> TRAVEL_REASONS = List.of("Travelling", "Business", "Sport", "Study", "Secret");

    @NonNull
    private final String firstname;

    @NonNull
    private final String lastname;

    @NonNull
    private final Date birthdate;

    @NonNull
    private Passport passport;

    private List<BaggageItem> baggageItems;

    public Passport handOverPassport() {
        return passport;
    }

    public List<BaggageItem> handOverBaggage() {
        return getBaggageItems();
    }

    public void payExceededFee(double amount) {
        log.info("Paying fee of " + amount);
    }

    public String reasonAndDurationOfStay() {
        return TRAVEL_REASONS.stream().findAny().get() + " " + new Random().nextInt(TRAVEL_REASONS.size());
    }
}
