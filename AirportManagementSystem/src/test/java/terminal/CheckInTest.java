package terminal;

import landside.model.Deposit;
import landside.model.Gate;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@Log
class CheckInTest {

    private FlightInformation flightInformation;
    private CheckIn checkIn;
    private Passport passport;
    private Passenger passenger;
    private List<BaggageItem> baggageItems;

    @BeforeEach
    void setUp() {
        Date validTo = null;
        Date birthday = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            validTo = sdf.parse("31.12.2022");
            birthday = sdf.parse("01.01.1970");
        } catch (ParseException ex) {
            log.severe("Could not parse Date");
            fail();
        }

        baggageItems = List.of(BaggageItem.of(50.,30.,20., 19.), BaggageItem.of(100., 10., 20., 8.));
        passport = Passport.of(123456789, "Max Mustermann", validTo, "Austria");
        passenger = Passenger.of("Max", "Mustermann", birthday, passport, baggageItems);
        flightInformation = FlightInformation.of(6942023, "Eurowings", "Crete", new Date(), 150., Gate.of(), Deposit.of(), 349.99, 149.99, 666.66);
        checkIn = CheckIn.of(1, new Date(), passenger, flightInformation);
    }

    @Test
    void performCheckIn() throws Exception {
        checkIn.performCheckIn();
    }

    @Test
    void requestPassport() {
    }

    @Test
    void confirmIdentity() {
    }

    @Test
    void printTickets() {
    }

    @Test
    void getCheckInCounter() {
    }

    @Test
    void getCheckInDateTime() {
    }

    @Test
    void getPassenger() {
    }

    @Test
    void getPaidFee() {
    }
}