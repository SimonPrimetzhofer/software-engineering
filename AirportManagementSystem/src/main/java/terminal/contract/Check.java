package terminal.contract;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class Check {
    protected boolean checkPassed = true;

    public boolean getCheckPassed() {
        return checkPassed;
    }
}
