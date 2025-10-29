package TK;

import java.io.IOException;

public class Task {
    boolean status = false; // false == incomplete
    String description = "";
    public Task(String description, Boolean status) {
        this.status = status;
        this.description = description;

    }

    @Override
    public String toString() {
        return status + ":" + description;
    }


}
