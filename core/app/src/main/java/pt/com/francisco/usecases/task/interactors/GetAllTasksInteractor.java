package pt.com.francisco.usecases.task.interactors;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.dto.TaskResponse;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@RequiredArgsConstructor
public class GetAllTasksInteractor implements TaskInputBoundary<List<TaskResponse>, Void> {
    private final TaskGateway taskGateway;
    private final TaskResponseMapper taskResponseMapper;

    @Override
    public List<TaskResponse> execute(Void none) {
        return taskGateway.getAll().stream().map(taskResponseMapper::map).toList();
    }
}
