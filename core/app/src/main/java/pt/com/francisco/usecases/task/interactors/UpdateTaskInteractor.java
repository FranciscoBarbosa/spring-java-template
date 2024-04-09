package pt.com.francisco.usecases.task.interactors;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.dto.TaskResponse;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@RequiredArgsConstructor
public class UpdateTaskInteractor
        implements TaskInputBoundary<TaskResponse, Pair<UUID, TaskRequest>> {
    private final TaskGateway taskGateway;
    private final TaskResponseMapper taskResponseMapper;
    private final TaskRequestMapper taskRequestMapper;

    @Override
    public TaskResponse execute(Pair<UUID, TaskRequest> taskRequestTuple) {
        Task task = taskRequestMapper.map(taskRequestTuple.getValue());
        final UUID taskId = taskRequestTuple.getKey();
        if (taskGateway.get(taskId).isEmpty()) {
            task = taskGateway.create(task);
        } else {
            task = taskGateway.update(taskId, task);
        }
        return taskResponseMapper.map(task);
    }
}
