package TK;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Task {
    public Status status;
    public String description;

    public Task(String description, Status isComplete) {
        // initialize Task properties
        this.status = isComplete;
        this.description = description;
    }

    public boolean isComplete() {
        return Objects.requireNonNull(this.status) == Status.Completed;
    }

    @Override
    // []: I am a Task
    // {X}: I am not a Task
    public String toString() {
        return status + ": " + description;
    }
}

