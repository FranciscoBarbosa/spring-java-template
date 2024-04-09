package pt.com.francisco.usecases.task.interactors;

import lombok.RequiredArgsConstructor;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.dto.TaskResponse;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@RequiredArgsConstructor
public class CreateTaskInteractor implements TaskInputBoundary<TaskResponse, TaskRequest> {
    private final TaskGateway taskGateway;
    private final TaskResponseMapper taskResponseMapper;
    private final TaskRequestMapper taskRequestMapper;

    @Override
    public TaskResponse execute(TaskRequest task) {
        return taskResponseMapper.map(taskGateway.create(taskRequestMapper.map(task)));
    }
}
