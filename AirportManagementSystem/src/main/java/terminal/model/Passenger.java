package terminal.model;

import lombok.*;

import java.util.Date;

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

    public Passport handOverPassport() {
        return passport;
    }
}
