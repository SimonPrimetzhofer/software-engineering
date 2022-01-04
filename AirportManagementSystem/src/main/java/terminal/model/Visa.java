package terminal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import terminal.helper.TravelReason;

import java.util.Date;

@AllArgsConstructor(staticName = "of")
@ToString
@Getter
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
