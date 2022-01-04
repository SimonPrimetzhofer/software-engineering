package terminal.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * @author Simon Primetzhofer
 */

@RequiredArgsConstructor(staticName = "of")
public class CheckIn {
    @NonNull
    private final int checkInCounter;
    @NonNull
    private final Date checkInDateTime;
    private float paidFee;
}
