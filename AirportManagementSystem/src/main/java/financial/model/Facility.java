package financial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Facility {
    @NonNull
    private String name;

    private boolean fullyBooked;

    @NonNull
    private int capacity;
}
