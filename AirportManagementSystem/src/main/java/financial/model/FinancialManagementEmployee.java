package financial.model;

import airside.AirsideManagement;
import financial.FinancialManagement;
import landside.LandsideManagement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import terminal.model.FlightInformation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class FinancialManagementEmployee {
    private List<Maintenance> managedMaintenances = new ArrayList<>();
    private List<FlightInformation> managedFlights = new ArrayList<>();
    private List<Facility> managedFacilities = new ArrayList<>();

    private final LandsideManagement landsideManagement;

    private void getLists() {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(landsideManagement.getEmployees());
    }
}
