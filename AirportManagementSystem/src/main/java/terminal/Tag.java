package terminal;

import lombok.*;

/**
 * @author Simon Primetzhofer
 */

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Tag {
    @NonNull
    private String baggageType;

    @NonNull
    private final FlightInformation flightInformation;
}
