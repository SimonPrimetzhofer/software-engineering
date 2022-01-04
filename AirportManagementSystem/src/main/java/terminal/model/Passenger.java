package terminal.model;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Date;

/**
 * @author Simon Primetzhofer
 */

@AllArgsConstructor(staticName = "of")
public class Passenger {
    @NonNull
    private final String firstname;
    @NonNull
    private final String lastname;
    @NonNull
    private final Date birthdate;
}
