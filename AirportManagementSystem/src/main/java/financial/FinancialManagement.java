package financial;

import financial.model.FinancialManagementEmployee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import overall.AirportSubsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaan Baylan
 */

@Getter
@Setter
@NoArgsConstructor
public class FinancialManagement extends AirportSubsystem {
    public List<FinancialManagementEmployee> fmes = new ArrayList<>();
}
