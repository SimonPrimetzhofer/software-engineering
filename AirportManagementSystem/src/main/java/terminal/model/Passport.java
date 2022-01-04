package terminal.model;

import lombok.*;

import java.util.Date;

@RequiredArgsConstructor(staticName = "of")
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

    private Visa visa;
}
