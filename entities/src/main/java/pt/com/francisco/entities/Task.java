package pt.com.francisco.entities;

import pt.com.francisco.entities.exceptions.TaskException;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private int id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime finishedDate;


    public Task(int id, String name, String description, Status status) {
        validateInstantiationTaskParams(status, startDate, null);
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(int id, String name, String description, Status status, LocalDateTime startDate) {
        validateInstantiationTaskParams(status, startDate, null);
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
    }

    public Task(int id, String name, String description, Status status, LocalDateTime startDate, LocalDateTime finishedDate) {
        validateInstantiationTaskParams(status, startDate, finishedDate);
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.finishedDate = finishedDate;
    }

    private void validateInstantiationTaskParams(Status status, LocalDateTime startDate, LocalDateTime finishedDate){
        if(status == Status.NOT_STARTED){
            if(startDate != null) {
                throw new TaskException("Not started tasks can't have a startDate");
            }
            if(finishedDate != null){
                throw new TaskException("Not started tasks can't have a finishedDate");
            }
        }
        if(status == Status.DOING){
            if(startDate == null) {
                throw new TaskException("Doing tasks must have a startDate");
            }
            if(finishedDate != null) {
                throw new TaskException("Not finished tasks can't have a finishedDate");
            }
        }
        if(status == Status.FINISHED){
            if(startDate == null || finishedDate == null){
                throw new TaskException("Finished tasks must have a start and finished date");
            }
        }
    }

    public void complete(){
        if(status.equals(Status.FINISHED)){
            throw new TaskException("Can't complete an already finished task");
        }
        status = Status.FINISHED;
        finishedDate = LocalDateTime.now();
    }
}
