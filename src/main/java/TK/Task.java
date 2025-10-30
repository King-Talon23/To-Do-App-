package TK;

import java.io.IOException;

public class Task {
    String status = "";
    boolean isComplete = false;
    String description = "";
    public Task(String description, boolean isComplete) {
        this.status = "[]"; // always starts unmarked
        this.description = description;
        this.isComplete = isComplete;
        if (isComplete) {
            complete();
        }
    }

    public void complete() {
        this.status = "{X}";
        this.isComplete = true;
    }
    public void decomplete() {
        this.status = "[]";
        this.isComplete = false;
    }


    @Override
    public String toString() {
        return status + ": " + description;
    }




}
