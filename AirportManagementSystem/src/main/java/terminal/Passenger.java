package terminal;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Primetzhofer
 */

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Passenger {
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

    public void payExceededFee(int amount) {

    }
}
