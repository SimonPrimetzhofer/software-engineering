package terminal;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminal.helper.TravelReason;
import terminal.model.BorderPoliceCheck;
import terminal.model.Passenger;
import terminal.model.Passport;
import terminal.model.Visa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Log
class BorderPoliceCheckTest {
    private Passport passport;
    private Passport passport2;
    private Passenger passenger;
    private Passenger passenger2;
    private BorderPoliceCheck borderPoliceCheck;
    private Visa visa;

    @BeforeEach
    void setUp() {
        Date validTo = null;
        Date birthday = null;
        Date visaFrom = null;
        Date visaTo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            validTo = sdf.parse("31.12.2022");
            birthday = sdf.parse("01.01.1970");
            visaFrom = sdf.parse("01.01.2022");
            visaTo = sdf.parse("28.02.2022");
        } catch (ParseException ex) {
            log.severe("Could not parse Date");
            fail();
        }
        passport = Passport.of(123456789, "Max Mustermann", validTo, "Austria");
        passport2 = Passport.of(666666, "Maria Mustermann", validTo, "Brazil");
        passenger = Passenger.of("Max", "Mustermann", birthday, passport, null);
        passenger2 = Passenger.of("Maria", "Mustermann", birthday, passport, null);
        visa = Visa.of(1111, passenger2, TravelReason.TRAVELLING, visaFrom, visaTo);
        passport2.setVisa(visa);
        borderPoliceCheck = BorderPoliceCheck.of(passenger);
    }

    @Test
    void performCheck() {
        assertDoesNotThrow(borderPoliceCheck::performCheck);
    }

    @Test
    void checkVisaValidityNoVisa() {
        assertFalse(borderPoliceCheck.checkVisaValidity(passport));
    }

    @Test
    void checkVisaValidityNoMatch() {
        passport.setVisa(visa);
        assertFalse(borderPoliceCheck.checkVisaValidity(passport));
    }

    @Test
    void checkVisaValidityExceeded() {
        Date visaTo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            visaTo = sdf.parse("02.02.2022");
        } catch (ParseException ex) {
            log.severe("Could not parse Date");
            fail();
        }
        visa.setValidToDate(visaTo);
        assertFalse(borderPoliceCheck.checkVisaValidity(passport2));
    }

    @Test
    void checkVisaValidityValid() {
        borderPoliceCheck = BorderPoliceCheck.of(passenger2);
        assertTrue(borderPoliceCheck.checkVisaValidity(passport2));
    }

    @Test
    void confirmIdentityValid() {
        assertDoesNotThrow(() -> borderPoliceCheck.confirmIdentity(passport));
    }

    @Test
    void confirmIdentityNoMatch() {
        passport.setIssuedFor("Maria Mustermann");
        assertThrows(Exception.class, () -> borderPoliceCheck.confirmIdentity(passport));
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
        assertThrows(Exception.class, () -> borderPoliceCheck.confirmIdentity(passport));
    }

    @Test
    void checkNationalityNoVisaNeeded() {
        try {
            borderPoliceCheck.checkNationality(passport);
            assertTrue(borderPoliceCheck.getCheckPassed());
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    void checkNationalityVisaNeeded() {
        borderPoliceCheck = BorderPoliceCheck.of(passenger2);
        try {
            borderPoliceCheck.checkNationality(passport2);
            assertTrue(borderPoliceCheck.getCheckPassed());
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    void checkNationalityWithInvalidVisa() {
        passport.setVisa(visa);
        assertThrows(Exception.class, () -> borderPoliceCheck.checkNationality(passport2));
    }


    @Test
    void checkWarrant() {
        passenger.setLastname("Burglar");
        assertThrows(Exception.class, borderPoliceCheck::checkWarrants);
    }

    @Test
    void checkNoWarrant() {
        assertDoesNotThrow(borderPoliceCheck::checkWarrants);
    }

    @Test
    void checkNoFees() {
        assertDoesNotThrow(borderPoliceCheck::checkFees);
    }

    @Test
    void checkFees() {
        passenger.setLastname("Speeder");
        assertThrows(Exception.class, borderPoliceCheck::checkFees);
    }
}