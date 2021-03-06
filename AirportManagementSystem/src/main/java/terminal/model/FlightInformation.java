package terminal.model;


import landside.model.destination.Deposit;
import landside.model.destination.Gate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Simon Primetzhofer
 */

@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@ToString()
public class FlightInformation {
    private Integer flightNumber;
    private String airline;
    private String destination;
    private Date departureTime;
    private Double duration;
    private Gate gate;
    private Deposit baggageDeposit;
    private Double firstClassPrice;
    private Double economyClassPrice;
    private Double businessClassPrice;
}
