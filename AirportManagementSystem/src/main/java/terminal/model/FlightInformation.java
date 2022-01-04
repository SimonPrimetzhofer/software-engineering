package terminal.model;


import landside.model.Deposit;
import landside.model.Gate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
