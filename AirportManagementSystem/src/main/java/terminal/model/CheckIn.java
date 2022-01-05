package terminal.model;

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
            List<BaggageItem> baggageItems = passenger.handOverBaggage();
            double overallWeight = 0;
            boolean exceeded = false;
            log.info("Now weighing baggage items...");
            // start weighing
            for (BaggageItem baggageItem : baggageItems) {
                overallWeight += weighBaggageItem(baggageItem);
                exceeded = checkOverallWeightLimit(overallWeight);
            }

            if (exceeded) {
                reportWeightLimitExceedance(overallWeight);
            }

            log.info("Now tagging and forwarding baggage items...");
            for (BaggageItem baggageItem : baggageItems) {
                Tag tag = printTag();
                baggageItem.tagBaggage(tag);
                forwardBaggage(baggageItem);
            }
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
        return String.format("Ticket for flight %d to %s", flightInformation.getFlightNumber(), flightInformation.getDestination());
    }

    public List<BaggageItem> askForBaggageItems() {
        return passenger.getBaggageItems();
    }

    public double weighBaggageItem(BaggageItem baggageItem) {
        return baggageItem.getWeight();
    }

    public boolean checkOverallWeightLimit(double overallWeight) {
        // every passenger is allowed to have 20 kg of baggage in total
        return overallWeight > 20.;
    }

    public void reportWeightLimitExceedance(double exceededAmount) {
        log.severe("Weight limit exceeded");
        // 15€ per exceeded kg
        passenger.payExceededFee((exceededAmount - 20.) * 15.);
    }

    public Tag printTag() {
        return TaggingMachine.getTag(flightInformation);
    }

    public void forwardBaggage(BaggageItem baggage) {
        // TODO: When Landside Management is ready, deposit baggage
        log.fine("Depositing baggage item to landside management...");
    }
}
