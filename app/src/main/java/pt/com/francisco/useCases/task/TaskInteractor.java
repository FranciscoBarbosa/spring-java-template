package pt.com.francisco.useCases.task;

import pt.com.francisco.entities.Task;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskInteractor implements TaskInputBoundary {
    private final TaskGateway taskGateway;

    @Override
    public Task createTask(Task task) {
        return taskGateway.create(task);
    }

    @Override
    public void updateTask(Task task) {
        throwExceptionIfNotExistentTask(task.getId());
        taskGateway.update(task);
    }
    @Override
    public void completeTask(UUID id) {
        throwExceptionIfNotExistentTask(id);
        final Task task = taskGateway.get(id).get();
        task.complete();
    }
    @Override
    public Task getTask(UUID id) {
        throwExceptionIfNotExistentTask(id);
        return taskGateway.get(id).get();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskGateway.getAll();
    }

    @Override
    public void removeTask(UUID id) {
        throwExceptionIfNotExistentTask(id);
        taskGateway.delete(id);
    }

    private void throwExceptionIfNotExistentTask(UUID id){
        if(taskGateway.get(id).isEmpty() ){
            throw new TaskNotFoundException();
        }
    }
}
