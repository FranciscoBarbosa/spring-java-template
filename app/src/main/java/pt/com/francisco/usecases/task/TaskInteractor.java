package pt.com.francisco.usecases.task;

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
        taskGateway.update(task);
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
