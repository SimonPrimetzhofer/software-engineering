package terminal.model;

import lombok.*;
import terminal.helper.TravelReason;

import java.util.Date;

@AllArgsConstructor(staticName = "of")
@ToString
@Getter
@Setter
public class Visa {
    @NonNull
    private Integer visaNumber;
    @NonNull
    private Passenger issuedFor;
    @NonNull
    private TravelReason issueReason;
    @NonNull
    private Date validFromDate;
    @NonNull
    private Date validToDate;
}
