package financial.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Maintenance {
    @NonNull
    private String type;
    private int cost;
    private Date startDate;
    private Date endDate;
}
