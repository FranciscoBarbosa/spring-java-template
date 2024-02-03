package pt.com.francisco.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NonNull;
import pt.com.francisco.entities.exceptions.TaskException;

@Data
public class Task {
    private UUID
            id; // using long might expose other resources by increasing id for example getBy/1,
    // getBy/2
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private Status status;
    private LocalDateTime startDate;
    private LocalDateTime finishedDate;

    // TODO: replace these constructors by builder
    public Task() {
        this.id = UUID.randomUUID();
    }

    public Task(String name, String description, Status status) {
        validateInstantiationTaskParams(status, startDate, null);
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description, Status status, LocalDateTime startDate) {
        validateInstantiationTaskParams(status, startDate, null);
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
    }

    public Task(
            String name,
            String description,
            Status status,
            LocalDateTime startDate,
            LocalDateTime finishedDate) {
        validateInstantiationTaskParams(status, startDate, finishedDate);
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.finishedDate = finishedDate;
    }

    private void validateInstantiationTaskParams(
            Status status, LocalDateTime startDate, LocalDateTime finishedDate) {
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

    public void complete() {
        if (status.equals(Status.FINISHED)) {
            throw new TaskException("Can't complete an already finished task");
        }
        status = Status.FINISHED;
        finishedDate = LocalDateTime.now();
    }

    //    public void setName(String name){
    //        if(name == null){
    //            throw new TaskException("task name can't be null");
    //        }
    //    }
}
