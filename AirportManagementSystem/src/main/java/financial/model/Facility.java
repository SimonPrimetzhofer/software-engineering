package financial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Kaan Baylan
 */

@Getter
@Setter
@AllArgsConstructor
public class Facility {
    private String name;

    private boolean fullyBooked;

    private int capacity;
}
