package pt.com.francisco.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import pt.com.francisco.entities.exceptions.TaskException;

@Data
public class Task {
    private final UUID
            id; // using long might expose other resources by increasing id for example getBy/1,//
    // getBy/2
    private String name;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime finishedDate;

    private Task(TaskBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.status = builder.status;
        this.startDate = builder.startDate;
        this.finishedDate = builder.finishedDate;
    }

    public void complete() {
        if (status.equals(Status.FINISHED)) {
            throw new TaskException("Can't complete an already finished task");
        }
        status = Status.FINISHED;
        finishedDate = LocalDateTime.now();
    }

    public static Task.TaskBuilder builder() {
        return new Task.TaskBuilder();
    }

    public static class TaskBuilder {
        private UUID id; // using long might expose other resources by increasing id for example
        // getBy/1,// getBy/2
        private String name;
        private String description;
        private Status status;
        private LocalDateTime startDate;
        private LocalDateTime finishedDate;

        public TaskBuilder() {}

        public TaskBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public TaskBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public TaskBuilder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public TaskBuilder finishedDate(LocalDateTime finishedDate) {
            this.finishedDate = finishedDate;
            return this;
        }

        public Task build() {
            validateInstantiationTaskParams();
            if (id == null) {
                id = UUID.randomUUID();
            }
            return new Task(this);
        }

        private void validateInstantiationTaskParams() {
            if (status == null) {
                throw new TaskException("Cannot create task without status");
            }
            if (status == Status.NOT_STARTED) {
                if (startDate != null) {
                    throw new TaskException("Not started tasks can't have a startDate");
                }
                if (finishedDate != null) {
                    throw new TaskException("Not started tasks can't have a finishedDate");
                }
            }
            if (status == Status.DOING) {
                if (startDate == null) {
                    throw new TaskException("Doing tasks must have a startDate");
                }
                if (finishedDate != null) {
                    throw new TaskException("Not finished tasks can't have a finishedDate");
                }
            }
            if (status == Status.FINISHED && (startDate == null || finishedDate == null)) {
                throw new TaskException("Finished tasks must have a start and finished date");
            }
        }
    }
}
