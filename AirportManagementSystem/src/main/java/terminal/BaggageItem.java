package terminal;

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@Getter
@ToString()
public class BaggageItem {
    @NonNull
    private final Double width;
    @NonNull
    private final Double height;
    @NonNull
    private final Double depth;
    @NonNull
    private final Double weight;

    private Tag tag;

    public void tagBaggage(Tag tag) {
        this.tag = tag;
    }
}
