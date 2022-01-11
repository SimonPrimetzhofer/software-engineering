package financial.model;

import airside.AirsideManagement;
import airside.model.AircraftMarshaller;
import financial.FinancialManagement;
import landside.LandsideManagement;
import landside.model.Driver;
import lombok.*;
import overall.AirportSubsystem;
import terminal.model.FlightInformation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FinancialManagementEmployee extends Employee {
    private List<Maintenance> managedMaintenances = new ArrayList<>();
    private List<FlightInformation> managedFlights = new ArrayList<>();
    private List<Facility> managedFacilities = new ArrayList<>();

    private final LandsideManagement landsideManagement;
    private final AirsideManagement airsideManagement;
    private final FinancialManagement financialManagement;

    private List<Driver> landsideEmployees;
    private List<AircraftMarshaller> airsideEmployees;
    private List<FinancialManagementEmployee> financialEmployees;

    private int numOfEmployees;

    public FinancialManagementEmployee(@NonNull String firstName, @NonNull String lastName, int salary, @NonNull AirportSubsystem dep, LandsideManagement landsideManagement, AirsideManagement airsideManagement, FinancialManagement financialManagement) {
        super(firstName, lastName, salary, dep);
        this.landsideManagement = landsideManagement;
        this.airsideManagement = airsideManagement;
        this.financialManagement = financialManagement;
    }

    private void getLists() {
        this.landsideEmployees = landsideManagement.employees;
        this.airsideEmployees = airsideManagement.marshaller;
        this.financialEmployees = financialManagement.fmes;
    }

    private void hireEmployee(@NonNull String firstName, @NonNull String lastName, int salary, @NonNull AirportSubsystem dep) {
        for (; numOfEmployees < this.landsideEmployees.size() + this.airsideEmployees.size() + this.financialEmployees.size(); numOfEmployees++) {
            Employee e = signEmp(firstName, lastName, salary, dep);
            assignEmps(dep, e);
        }
    }

    /**
     * create new employee
     */
    private Employee signEmp(@NonNull String firstName, @NonNull String lastName, int salary, @NonNull AirportSubsystem dep) {
        return new Employee(firstName, lastName, salary, dep);
    }

    /**
     * assign employee to department
     */
    private void assignEmps(AirportSubsystem dep, Employee e) {
        if (dep instanceof LandsideManagement) {
            ((LandsideManagement) dep).employees.add((Driver) e);
        } else if (dep instanceof AirsideManagement) {
            ((AirsideManagement) dep).marshaller.add((AircraftMarshaller) e);
        } else if (dep instanceof FinancialManagement) {
            ((FinancialManagement) dep).fmes.add((FinancialManagementEmployee) e);
        }
    }
}
