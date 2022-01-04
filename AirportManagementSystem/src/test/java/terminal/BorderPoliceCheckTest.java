package terminal;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import terminal.model.BaggageItem;
import terminal.model.BorderPoliceCheck;
import terminal.model.Passenger;
import terminal.model.Passport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log
class BorderPoliceCheckTest {
    private Passport passport;
    private Passenger passenger;
    private BorderPoliceCheck borderPoliceCheck;

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
        passport = Passport.of(123456789, "Max Mustermann", validTo, "Austria");
        passenger = Passenger.of("Max", "Mustermann", birthday, passport, null);

        borderPoliceCheck = BorderPoliceCheck.of(passenger);
    }

    @Test
    void performCheck() {
        assertDoesNotThrow(() -> borderPoliceCheck.performCheck());
    }

    @Test
    void checkVisaValidity() {
    }

    @Test
    void confirmIdentity() {
    }

    @Test
    void checkNationality() {
    }

    @Test
    void checkNoWarrant() {
    }

    @Test
    void checkWarrant() {

    }

    @Test
    void checkNoFees() {
    }

    @Test
    void checkFees() {

    }
}