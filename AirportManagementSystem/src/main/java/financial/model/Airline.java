package financial.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import terminal.model.FlightInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kaan Baylan
 */

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class Airline {
    @NonNull
    private String name;

    private List<Facility> bookedFacilities = new ArrayList<>();
    private List<FlightInformation> flights = new ArrayList<>();

    @NonNull
    private FinancialManagementEmployee contactPerson;

    public void changeFlightInfo(FlightInformation flightInformation, String destination, Date departureTime, Double duration) throws Exception {

        if (destination.length() == 0) {
            throw new Exception("Invalid destination entered!");
        }

        if (duration < 0) {
            throw new Exception("Cannot set a negative duration!");
        }

        flightInformation.setDestination(destination);
        flightInformation.setDepartureTime(departureTime);
        flightInformation.setDuration(duration);

        sendFlightInfo(flightInformation);
    }

    public void sendFlightInfo(FlightInformation flightInformation) throws Exception {
        contactPerson.scheduleFlight(flightInformation);
    }
}
