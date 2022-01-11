package terminal.model;

import lombok.extern.java.Log;

/**
 * @author Simon Primetzhofer
 */

@Log
public class TaggingMachine {
    public static Tag getTag(FlightInformation flightInformation) {
        log.info("Printing tag for flight " + flightInformation);
        return Tag.of("", flightInformation);
    }
}
