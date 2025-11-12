package TK;

public enum Status {
    Completed,
    Uncompleted;

    @Override
    public String toString() {
        if (this == Uncompleted) {
            return "[]";
        } else {
            return "{X}";
        }
    }

}


