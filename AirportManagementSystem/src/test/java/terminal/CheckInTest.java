package terminal;

import landside.model.destination.Deposit;
import landside.model.destination.Gate;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminal.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        baggageItems = List.of(BaggageItem.of(50., 30., 20., 19.), BaggageItem.of(100., 10., 20., 8.));
        passport = Passport.of(123456789, "Max Mustermann", validTo, "Austria");
        passenger = Passenger.of("Max", "Mustermann", birthday, passport, baggageItems);
        flightInformation = FlightInformation.of(6942023, "Eurowings", "Crete", new Date(), 150., new Gate(1, 300, 1), new Deposit(3, 4500, 2), 349.99, 149.99, 666.66);
        checkIn = CheckIn.of(1, new Date(), passenger, flightInformation);
    }

    @Test
    void performCheckIn() {
        assertDoesNotThrow(() -> checkIn.performCheckIn());
        try {
            String ticket = checkIn.performCheckIn();
            assertEquals(checkIn.printTicket(), String.format("Ticket for flight %d to %s", flightInformation.getFlightNumber(), flightInformation.getDestination()));
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    void requestPassport() {
        assertNotNull(checkIn.requestPassport());
        assertSame(checkIn.requestPassport(), passenger.handOverPassport());
    }

    @Test
    void confirmIdentityValid() {
        assertDoesNotThrow(() -> checkIn.confirmIdentity(passport));
    }

    @Test
    void confirmIdentityNoMatch() {
        passport.setIssuedFor("Maria Mustermann");
        assertThrows(Exception.class, () -> checkIn.confirmIdentity(passport));
    }

    @Test
    void confirmIdentityExpired() {
        Date validTo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            validTo = sdf.parse("31.12.2021");
        } catch (ParseException ex) {
            log.severe("Could not parse Date");
            fail();
        }
        passport.setValidToDate(validTo);
        assertThrows(Exception.class, () -> checkIn.confirmIdentity(passport));
    }

    @Test
    void printTicket() {
        assertEquals(checkIn.printTicket(), String.format("Ticket for flight %d to %s", flightInformation.getFlightNumber(), flightInformation.getDestination()));
    }

    @Test
    void checkOverallWeightExceedance() {
        assertTrue(checkIn.checkOverallWeightLimit(20.1));
        assertFalse(checkIn.checkOverallWeightLimit(20));
    }

    @Test
    void checkOverallWeightNoExceedance() {
        assertFalse(checkIn.checkOverallWeightLimit(19.9));
    }

    @Test
    void getPassenger() {
        assertNotNull(checkIn.getPassenger());
        assertSame(checkIn.getPassenger(), passenger);
    }
}