package pt.com.francisco.useCases.interactorPort;

import org.springframework.stereotype.Service;
import pt.com.francisco.entities.Task;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.useCases.databaseGateways.TaskGateway;
import pt.com.francisco.useCases.exceptions.TaskNotFoundException;
import pt.com.francisco.useCases.inputPort.TaskUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskUseCaseImpl implements TaskUseCase {
    private final TaskGateway taskGateway;

    @Override
    public void createTask(Task task) {
        taskGateway.create(task);
    }

    @Override
    public void updateTask(Task task) {
        throwExceptionIfNotExistentTask(task.getId());
        taskGateway.update(task);
    }
    @Override
    public void completeTask(int id) {
        throwExceptionIfNotExistentTask(id);
        final Task task = taskGateway.get(id).get();
        task.complete();
    }
    @Override
    public Task getTask(int id) {
        throwExceptionIfNotExistentTask(id);
        return taskGateway.get(id).get();
    }
    @Override
    public List<Task> getAllTasks() {
        return taskGateway.getAll();
    }
    @Override
    public void removeTask(int id) {
        throwExceptionIfNotExistentTask(id);
        taskGateway.delete(id);
    }

    private void throwExceptionIfNotExistentTask(int id){
        if(taskGateway.get(id).isEmpty() ){
            throw new TaskNotFoundException();
        }
    }
}
