package services.impl;

import Repository.TaskRepository;
import entities.Status;
import entities.Task;
import services.TaskService;
import services.exceptions.InexistentTaskException;
import entities.exceptions.TaskException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public void removeTask(int id){
        throwExceptionIfNotExistentTask(id);
        taskRepository.delete(id);
    }

    public void completeTask(int id){
        throwExceptionIfNotExistentTask(id);
        final Task task = taskRepository.get(id).get();
        if(cannotBeFinished(task)){
            throw new TaskException("task cannot be completed because it is already complete");
        }
        task.setStatus(Status.FINISHED);
        task.setFinishedDate(LocalDateTime.now());
        taskRepository.update(id, task);
    }

    public void updateTask(Task task){
        throwExceptionIfNotExistentTask(task.getId());
        taskRepository.update(task.getId(), task);
    }

    private void throwExceptionIfNotExistentTask(int id){
        if(taskRepository.get(id).isEmpty() ){
            throw new InexistentTaskException(id);
        }
    }

    private boolean cannotBeFinished(Task task){
        if(task.getStatus().equals(Status.FINISHED)){
            return false;
        }
        return true;
    }
}
