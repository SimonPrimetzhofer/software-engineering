package terminal.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import terminal.contract.Check;

import java.util.Date;
import java.util.List;

/**
 * @author Simon Primetzhofer
 */

@RequiredArgsConstructor(staticName = "of")
@ToString
@Log
public class BorderPoliceCheck extends Check {

    // for simplicity, only some countries are listed here
    private static final List<String> VISA_COUNTRIES = List.of("Turkey", "USA", "United Kingdom", "Brazil", "Japan");

    @Setter
    private String reasonOfStay;
    @Setter
    private Integer durationOfStay;
    @NonNull
    private Passenger passenger;

    public void performCheck() throws Exception {
        log.info("Starting security check on " + passenger);
        final Passport passport = passenger.handOverPassport();
        checkNationality(passport);
        checkWarrants();
        checkFees();
        returnPassport();
    }

    public boolean checkVisaValidity(Passport passport) {
        if (passport.getVisa() == null) {
            log.severe("Passenger has no visa!");
            return false;
        }
        if (passport.getVisa().getIssuedFor() != passenger) {
            log.severe("Visa does not match passenger!");
            return false;
        }
        final Date today = new Date();
        if (passport.getVisa().getValidFromDate().after(today) || passport.getVisa().getValidToDate().before(today)) {
            log.severe("Visa time range exceeded!");
            return false;
        }

        log.fine("Visa is valid...");
        return true;
    }

    public void confirmIdentity(Passport passport) throws Exception {
        if (!passport.getIssuedFor().equals(String.format("%s %s", passenger.getFirstname(), passenger.getLastname()))) {
            log.severe("Fake passport, arresting passenger " + passenger);
            throw new Exception("Passport does not match passenger!");
        }
        if (passport.getValidToDate().before(new Date())) {
            throw new Exception("Passport expired!");
        }
        log.fine("Identity confirmed...");
    }

    public String getReasonAndDurationOfStay() {
        return passenger.reasonAndDurationOfStay();
    }

    public void returnPassport() {
        log.info("Passport returned to passenger...");
    }

    public void checkNationality(Passport passport) throws Exception {
        if (VISA_COUNTRIES.contains(passport.getNationality())) {
            log.info("Passenger requires visa");
            checkPassed = checkVisaValidity(passport);
            if (checkPassed) {
                confirmIdentity(passport);
                final String[] reasonAndDurationOfStay = getReasonAndDurationOfStay().split(" ");
                reasonOfStay = reasonAndDurationOfStay[0];
                durationOfStay = Integer.parseInt(reasonAndDurationOfStay[1]);
            } else {
                failCheck();
            }
        }
        log.fine("Nationality checked...");
    }

    public void checkWarrants() throws Exception {
        // only mocking some warrants here
        if (passenger.getLastname().equals("Burglar")) {
            log.severe("Passenger " + passenger + " gets arrested immediately");
            throw new Exception("Passenger " + passenger + " has an open warrant!");
        }
        log.info("No open warrants...");
    }

    public void checkFees() throws Exception {
        // only mocking some warrants here
        if (passenger.getLastname().equals("Speeder")) {
            log.severe("Passenger " + passenger + " has to pay fee immediately");
            throw new Exception("Passenger " + passenger + " has an open fee!");
        }
        log.info("No open fees...");
    }

    public void failCheck() throws Exception{
        log.severe("Visa is invalid!");
        throw new Exception("Visa is invalid");
    }
}
