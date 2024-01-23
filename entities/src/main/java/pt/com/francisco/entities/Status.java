package pt.com.francisco.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT) //TODO: can i do this? I need this to create a Task from a json string
// and to be able to  serialize status string to the enum
public enum Status {
    FINISHED("finished"),
    DOING("doing"),
    NOT_STARTED("not_started");

    private final String value;
    // Constructor to initialize the abbreviation for each enum constant
    Status(String value) {
        this.value = value;
    }

    @JsonValue
    String getValue(){
        return this.value;
    }
    @JsonCreator
    public static Status create(String val) {
        Status [] statuses = Status.values();
        for (Status status : statuses) {
            if (status.getValue().equals(val)) {
                return status;
            }
        }
        return NOT_STARTED;
    }

}
