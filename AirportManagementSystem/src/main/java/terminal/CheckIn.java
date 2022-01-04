package terminal;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Primetzhofer
 */

@RequiredArgsConstructor(staticName = "of")
@Getter
@Log
public class CheckIn {
    @NonNull
    private final Integer checkInCounter;
    @NonNull
    private final Date checkInDateTime;
    @NonNull
    private final Passenger passenger;
    @NonNull
    private final FlightInformation flightInformation;

    private Float paidFee;

    public String performCheckIn() throws Exception {
        final Passport passport = requestPassport();
        confirmIdentity(passport);
        final String ticket = printTicket();

        if (passenger.getBaggageItems().size() > 0) {
            log.info("Passenger has baggage items!");

        } else {
            log.fine("Check-In completed without baggage items...");
        }
        return ticket;
    }

    public Passport requestPassport() {
        log.info("Requesting passport of " + passenger.getFirstname() + " " + passenger.getLastname());
        return passenger.handOverPassport();
    }

    public void confirmIdentity(Passport passport) throws Exception {
        if (!passport.getIssuedFor().equals(String.format("%s %s", passenger.getFirstname(), passenger.getLastname()))) {
            throw new Exception("Passport does not match passenger!");
        }
        if (passport.getValidToDate().before(new Date())) {
            throw new Exception("Passport expired!");
        }
        log.fine("Identity confirmed...");
    }

    public String printTicket() {
        log.fine("Printing ticket for flight " + flightInformation.getFlightNumber());
        return flightInformation.toString();
    }

    public List<BaggageItem> askForBaggageItems() {
        return passenger.getBaggageItems();
    }

    public void weighBaggageItem() {

    }

    public void checkOverallWeightLimit() {

    }

    public void reportWeightLimitExceedance() {

    }

    public void printTag() {

    }

    public void tagBaggage(Tag tag) {

    }

    public void forwardBaggage(BaggageItem baggage) {

    }
}
