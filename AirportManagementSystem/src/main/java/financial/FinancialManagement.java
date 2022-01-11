package financial;

import financial.model.Facility;
import financial.model.FinancialManagementEmployee;
import financial.model.Maintenance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import overall.AirportSubsystem;
import terminal.model.FlightInformation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FinancialManagement extends AirportSubsystem {
    public List<FinancialManagementEmployee> fmes = new ArrayList<>();
}
