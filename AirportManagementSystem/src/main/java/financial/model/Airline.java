package financial.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import terminal.model.FlightInformation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Airline {
    @NonNull
    private String name;

    private List<Facility> bookedFacilities = new ArrayList<>();
    private List<FlightInformation> flights = new ArrayList<>();

    @NonNull
    private FinancialManagementEmployee contactPerson;
}
