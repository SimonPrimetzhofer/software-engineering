package terminal.model;

import lombok.*;

import java.util.Date;

/**
 * @author Simon Primetzhofer
 */

@RequiredArgsConstructor(staticName = "of")
@Setter
@Getter
@ToString
public class Passport {
    @NonNull
    private Integer passportNumber;
    @NonNull
    private String issuedFor;
    @NonNull
    private Date validToDate;
    @NonNull
    private String nationality;

    private Visa visa;
}
