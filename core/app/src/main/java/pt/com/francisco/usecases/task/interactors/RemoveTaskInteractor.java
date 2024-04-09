package pt.com.francisco.usecases.task.interactors;

import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;

@RequiredArgsConstructor
public class RemoveTaskInteractor implements TaskInputBoundary<Void, TaskRequest> {
    private final TaskGateway taskGateway;
    private final TaskRequestMapper taskRequestMapper;

    @Override
    public Void execute(TaskRequest taskRequest) {
        Task task = taskRequestMapper.map(taskRequest);
        taskGateway.delete(task.getId());
        return null;
    }
}
