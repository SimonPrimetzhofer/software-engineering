package financial;

import airside.AirsideManagement;
import airside.model.AircraftMarshaller;
import financial.model.Employee;
import financial.model.FinancialManagementEmployee;
import landside.LandsideManagement;
import landside.model.Driver;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import overall.AirportSubsystem;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AssignEmployeeTest {
    FinancialManagementEmployee fme;
    FinancialManagement fm;
    LandsideManagement lm;
    AirsideManagement am;

    Driver d1;
    Driver d2;
    AircraftMarshaller airMarsh;

    @BeforeEach
    void setUp() {
        fm = new FinancialManagement();
        lm = new LandsideManagement();
        am = new AirsideManagement();

        d1 = new Driver("Maxine", "Verstappen", 10000, lm, 1);
        d2 = new Driver("Johny", "Musterfrau", 50000, lm, 2);
        airMarsh = new AircraftMarshaller("Simon", "von und zu Primetzhofer", 50000, am, new ArrayList<>(), new ArrayList<>());

        lm.employees.add(d1);
        lm.employees.add(d2);
        am.marshaller.add(airMarsh);

        fme = new FinancialManagementEmployee("Max", "Verstappen", 10000, fm, lm, am, fm);

        fm.fmes.add(fme);
    }

    @Test
    void testGetLists() {
        fme.getLists();

        assertEquals(fme.getLandsideEmployees().size(), 2);
        assertEquals(fme.getLandsideEmployees().get(0), d1);
        assertEquals(fme.getLandsideEmployees().get(1), d2);

        assertEquals(fme.getAirsideEmployees().size(), 1);
        assertEquals(fme.getAirsideEmployees().get(0), airMarsh);

        assertEquals(fme.getFinancialEmployees().size(), 1);
    }

    @Test
    void testHireEmployee() {
        fme.getLists();
        int numOfEmployees = fme.getNumOfEmployees();
        fme.setNumOfEmployees(numOfEmployees - 1);
        fme.hireEmployee("Markus", "Maximus", 10000, lm, 1);

        assertEquals(fme.getNumOfEmployees(), numOfEmployees);
    }

    @Test
    void testSignEmps() {
        Employee e = fme.signEmp("Markus", "Maximus", 10000, lm);
        assertEquals(e.getFirstName(), "Markus");
    }

    @Test
    void testAssignEmps() {
        fme.assignEmps(lm, new Employee("Markus", "Maximus", 10000, lm), 1);

        assertEquals(lm.employees.size(), 3);
        assertTrue(lm.employees.get(2) instanceof Driver);
        assertEquals(lm.employees.get(2).getFirstName(), "Markus");
    }
}
