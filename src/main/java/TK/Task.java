package TK;

import java.util.HashMap;
import java.util.Map;

public class Task {
    // Map isComplete to String status symbol
    Map<Boolean, String> completeToStatus = new HashMap<>(Map.of(false, "[]", true, "{X}"));

    public String status;
    public boolean isComplete;
    public String description;

    public Task(String description, boolean isComplete) {
        // initialize task properties and visual status
        this.isComplete = isComplete;
        this.status = completeToStatus.get(this.isComplete);
        this.description = description;
    }

    public void complete() {
        // mark task as completed
        this.isComplete = true;
        this.status = completeToStatus.get(true);

    }

    public void uncomplete() {
        // unmark task to uncompleted
        this.isComplete = false;
        this.status = completeToStatus.get(false);

    }

    @Override
    public String toString() {
        return status + ": " + description;
    }
}

