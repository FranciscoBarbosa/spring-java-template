package pt.com.francisco.usecases.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import lombok.Data;

@Data
public class TaskResponse {

    private UUID id;

    private String name;

    private String description;

    /** Gets or Sets status */
    public enum StatusEnum {
        FINISHED("finished"),

        DOING("doing"),

        NOT_STARTED("not_started");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private StatusEnum status;

    private String startDate;

    private String finishedDate;
}
