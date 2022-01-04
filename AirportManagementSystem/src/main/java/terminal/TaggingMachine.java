package terminal;

import lombok.extern.java.Log;

@Log
public class TaggingMachine {
    public static Tag getTag(FlightInformation flightInformation) {
        log.info("Printing tag for flight " + flightInformation);
        return Tag.of("", flightInformation);
    }
}
