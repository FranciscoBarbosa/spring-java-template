package entities;

import entities.exceptions.TaskCreationException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Task {
    private String name;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime finishedDate;


    public Task(String name, String description, Status status) {
        validateInstantiationTaskParams(status, startDate, null);
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description, Status status, LocalDateTime startDate) {
        validateInstantiationTaskParams(status, startDate, null);
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
    }

    public Task(String name, String description, Status status, LocalDateTime startDate, LocalDateTime finishedDate) {
        validateInstantiationTaskParams(status, startDate, finishedDate);
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.finishedDate = finishedDate;
    }

    private void validateInstantiationTaskParams(Status status, LocalDateTime startDate, LocalDateTime finishedDate){
        if(status == Status.NOT_STARTED){
            if(startDate != null) {
                throw new TaskCreationException("Not started tasks can't have a startDate");
            }
            if(finishedDate != null){
                throw new TaskCreationException("Not started tasks can't have a finishedDate");
            }
        }
        if(status == Status.DOING){
            if(startDate == null) {
                throw new TaskCreationException("Doing tasks must have a startDate");
            }
            if(finishedDate != null) {
                throw new TaskCreationException("Not finished tasks can't have a finishedDate");
            }
        }
        if(status == Status.FINISHED){
            if(startDate == null || finishedDate == null){
                throw new TaskCreationException("Finished tasks must have a start and finished date");
            }
        }
    }

    private void validateMarkAsFinishedParams(){
        if(finishedDate.isBefore(startDate)){
            throw new TaskCreationException("Can't mark a task as finished with finishedDate previous to the start date");
        }
        if(status.equals(Status.NOT_STARTED)){
            throw new TaskCreationException("Can't mark a task as finished before it has started");
        }
    }
}
