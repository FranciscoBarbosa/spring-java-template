package pt.com.francisco.usecases.task;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@RequiredArgsConstructor
public class TaskInteractor implements TaskInputBoundary {
    private final TaskGateway taskGateway;
    private final TaskResponseMapper taskResponseMapper;
    private final TaskRequestMapper taskRequestMapper;

    @Override
    public TaskResponse createTask(TaskRequest task) {
        return taskResponseMapper.map(taskGateway.create(taskRequestMapper.map(task)));
    }

    @Override
    public TaskResponse updateTask(UUID taskId, TaskRequest task) {
        return taskResponseMapper.map(taskGateway.update(taskId, taskRequestMapper.map(task)));
    }

    @Override
    public void completeTask(UUID id) {
        final Task task = taskGateway.get(id).get();

        task.complete();
    }

    @Override
    public TaskResponse getTask(UUID id) {
        return taskResponseMapper.map(taskGateway.get(id).get());
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskGateway.getAll().stream().map(taskResponseMapper::map).toList();
    }

    @Override
    public void removeTask(UUID id) {
        taskGateway.delete(id);
    }
}
