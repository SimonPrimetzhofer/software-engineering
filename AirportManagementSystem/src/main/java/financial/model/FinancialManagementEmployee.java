package financial.model;

import airside.AirsideManagement;
import airside.model.AircraftMarshaller;
import financial.FinancialManagement;
import landside.LandsideManagement;
import landside.model.Driver;
import lombok.*;
import lombok.extern.java.Log;
import overall.AirportSubsystem;
import terminal.model.FlightInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Kaan Baylan
 */

@Getter
@Setter
@Log
public class FinancialManagementEmployee extends Employee {
    private List<Maintenance> managedMaintenances = new ArrayList<>();
    private List<FlightInformation> managedFlights = new ArrayList<>();
    private List<Facility> managedFacilities = new ArrayList<>();

    private final LandsideManagement landsideManagement;
    private final AirsideManagement airsideManagement;
    private final FinancialManagement financialManagement;

    private List<Driver> landsideEmployees = new ArrayList<>();
    private List<AircraftMarshaller> airsideEmployees = new ArrayList<>();
    private List<FinancialManagementEmployee> financialEmployees = new ArrayList<>();

    private int numOfEmployees;

    public FinancialManagementEmployee(@NonNull String firstName, @NonNull String lastName, int salary, @NonNull AirportSubsystem dep, LandsideManagement landsideManagement, AirsideManagement airsideManagement, FinancialManagement financialManagement) {
        super(firstName, lastName, salary, dep);
        this.landsideManagement = landsideManagement;
        this.airsideManagement = airsideManagement;
        this.financialManagement = financialManagement;
    }

    public void getLists() {
        this.landsideEmployees = landsideManagement.employees;
        this.airsideEmployees = airsideManagement.marshaller;
        this.financialEmployees = financialManagement.fmes;
        numOfEmployees = this.landsideEmployees.size() + this.airsideEmployees.size() + this.financialEmployees.size();
    }

    public void hireEmployee(@NonNull String firstName, @NonNull String lastName, int salary, @NonNull AirportSubsystem dep, int licenceId) {
        if (numOfEmployees < this.landsideEmployees.size() + this.airsideEmployees.size() + this.financialEmployees.size()) {
            Employee e = signEmp(firstName, lastName, salary, dep);
            assignEmps(dep, e, licenceId);
            numOfEmployees++;
        }
    }

    /**
     * create new employee
     */
    public Employee signEmp(@NonNull String firstName, @NonNull String lastName, int salary, @NonNull AirportSubsystem dep) {
        return new Employee(firstName, lastName, salary, dep);
    }

    /**
     * assign employee to department
     */
    public void assignEmps(AirportSubsystem dep, Employee e, int licenseId) {
        if (dep instanceof LandsideManagement) {
            ((LandsideManagement) dep).employees.add(new Driver(e, 1));
        } else if (dep instanceof AirsideManagement) {
            ((AirsideManagement) dep).marshaller.add((AircraftMarshaller) e);
        } else if (dep instanceof FinancialManagement) {
            ((FinancialManagement) dep).fmes.add((FinancialManagementEmployee) e);
        }
    }

    public void scheduleFlight(FlightInformation flightInformation) throws Exception {
        if (!managedFlights.contains(flightInformation)) {
            log.severe("Cannot schedule not existing flight!");
            throw new Exception("Flight " + flightInformation + " does not exist!");
        }

        log.info("Rescheduling flight " + flightInformation);

        calcTicketPrices(flightInformation, new Random().nextInt(2));
        publishTicketPrices(flightInformation);
    }

    public void calcTicketPrices(FlightInformation flightInformation, int factor) {
        log.info("Updating ticket prices due to schedule change...");
        flightInformation.setEconomyClassPrice(flightInformation.getEconomyClassPrice() * factor);
        flightInformation.setFirstClassPrice(flightInformation.getFirstClassPrice() * factor);
        flightInformation.setBusinessClassPrice(flightInformation.getBusinessClassPrice() * factor);
    }

    public void publishTicketPrices(FlightInformation flightInformation) {
        log.info("New economy class price: " + flightInformation.getEconomyClassPrice());
        log.info("New first class price: " + flightInformation.getFirstClassPrice());
        log.info("New business class price " + flightInformation.getBusinessClassPrice());
    }
}
