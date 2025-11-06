package TK;

import java.util.HashMap;
import java.util.Map;

public class Task {
    // Map isComplete to String status symbol
    Map<Boolean, String> completeToStatus = new HashMap<>(Map.of(false, "[]", true, "{X}")); // false = uncompleted & true = complete

    public String status;
    public boolean isComplete;
    public String description;

    public Task(String description, boolean isComplete) {
        // initialize Task properties
        this.isComplete = isComplete;
        this.status = completeToStatus.get(this.isComplete);
        this.description = description;
    }

    public void complete() {
        // mark task completed
        this.isComplete = true;
        this.status = completeToStatus.get(true);

    }

    public void uncomplete() {
        // mark task uncompleted
        this.isComplete = false;
        this.status = completeToStatus.get(false);

    }

    @Override
    // []: I am a Task
    // {X}: I am not a Task
    public String toString() {
        return status + ": " + description;
    }
}

