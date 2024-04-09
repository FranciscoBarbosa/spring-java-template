package pt.com.francisco.usecases.task.interactors;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.entities.exceptions.TaskNotFoundException;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.dto.TaskResponse;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@RequiredArgsConstructor
public class GetTaskInteractor implements TaskInputBoundary<TaskResponse, UUID> {
    private final TaskGateway taskGateway;
    private final TaskResponseMapper taskResponseMapper;

    @Override
    public TaskResponse execute(UUID taskId) {
        Optional<Task> task = taskGateway.get(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return taskResponseMapper.map(task.get());
    }
}
