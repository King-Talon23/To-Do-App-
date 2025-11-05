package TK;

import java.util.HashMap;
import java.util.Map;

public class Task {
    // Map completion state to visual status symbol for display
    Map<Boolean, String> completeToStatus = new HashMap<>(Map.of(false, "[]", true, "{X}"));

    String status;
    boolean isComplete;
    String description;

    public Task(String description, boolean isComplete) {
        // initialize task properties and visual status
        this.isComplete = isComplete;
        this.status = completeToStatus.get(this.isComplete);
        this.description = description;
    }

    public void complete() {
        // mark task as complete
        this.isComplete = true;
        this.status = completeToStatus.get(true);

    }

    public void decomplete() {
        // unmark task to uncomplete
        this.isComplete = false;
        this.status = completeToStatus.get(false);

    }

    @Override
    public String toString() {
        return status + ": " + description;
    }
}

