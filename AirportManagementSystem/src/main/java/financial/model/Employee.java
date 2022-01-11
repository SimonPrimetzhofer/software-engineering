package financial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import overall.AirportSubsystem;

/**
 * @author Kaan Baylan
 */

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @NonNull
    private Integer salary;

    @NonNull
    private AirportSubsystem department;
}
