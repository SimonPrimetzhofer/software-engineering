package terminal;

import lombok.*;

import java.util.Date;

@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@ToString
public class Passport {
    @NonNull
    private final Integer passportNumber;
    @NonNull
    private final String issuedFor;
    @NonNull
    private final Date validToDate;
    @NonNull
    private String nationality;
}
