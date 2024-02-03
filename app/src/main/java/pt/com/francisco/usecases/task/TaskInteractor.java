package pt.com.francisco.usecases.task;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;

@RequiredArgsConstructor
public class TaskInteractor implements TaskInputBoundary {
    private final TaskGateway taskGateway;

    @Override
    public Task createTask(Task task) {
        return taskGateway.create(task);
    }

    @Override
    public Task updateTask(UUID taskId, Task task) {
        return taskGateway.update(taskId, task);
    }

    @Override
    public void completeTask(UUID id) {
        final Task task = taskGateway.get(id).get();
        task.complete();
    }

    @Override
    public Task getTask(UUID id) {
        return taskGateway.get(id).get();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskGateway.getAll();
    }

    @Override
    public void removeTask(UUID id) {
        taskGateway.delete(id);
    }
}
