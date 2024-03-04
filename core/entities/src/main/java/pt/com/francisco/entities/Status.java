package pt.com.francisco.entities;

public enum Status {
    FINISHED("finished"),
    DOING("doing"),
    NOT_STARTED("not_started");

    private final String value;
    // Constructor to initialize the abbreviation for each enum constant
    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Status create(String val) {
        Status[] statuses = Status.values();
        for (Status status : statuses) {
            if (status.getValue().equals(val)) {
                return status;
            }
        }
        return NOT_STARTED;
    }
}
