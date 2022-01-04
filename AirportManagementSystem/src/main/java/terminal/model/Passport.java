package terminal.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@ToString
public class Passport {
    @NonNull
    private final int passportNumber;
    @NonNull
    private final Date validToDate;
    @NonNull
    private String nationality;
}
