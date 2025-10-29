package TK;

public class Task {
    boolean status = false; // false == incomplete
    String description = "";
    public Task(String description) {
        this.status = false;
        this.description = description;
    }
}
