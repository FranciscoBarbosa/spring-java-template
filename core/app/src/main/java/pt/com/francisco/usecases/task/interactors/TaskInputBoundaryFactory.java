package pt.com.francisco.usecases.task.interactors;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.dto.TaskResponse;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapperImpl;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapperImpl;

@Getter
public class TaskInputBoundaryFactory {
    private final TaskGateway taskGateway;
    private final TaskInputBoundary<Void, UUID> completeTaskInteractor;
    private final TaskInputBoundary<TaskResponse, TaskRequest> createTaskInteractor;
    private final TaskInputBoundary<List<TaskResponse>, Void> getAllTasksInteractor;
    private final TaskInputBoundary<TaskResponse, UUID> getTaskInteractor;
    private final TaskInputBoundary<Void, UUID> removeTaskInteractor;
    private final TaskInputBoundary<TaskResponse, Pair<UUID, TaskRequest>> updateTaskInteractor;
    private final TaskResponseMapper taskResponseMapper = new TaskResponseMapperImpl();
    private final TaskRequestMapper taskRequestMapper = new TaskRequestMapperImpl();

    public TaskInputBoundaryFactory(TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
        this.completeTaskInteractor = new CompleteTaskInteractor(taskGateway);
        this.createTaskInteractor =
                new CreateTaskInteractor(taskGateway, taskResponseMapper, taskRequestMapper);
        this.getAllTasksInteractor = new GetAllTasksInteractor(taskGateway, taskResponseMapper);
        this.getTaskInteractor = new GetTaskInteractor(taskGateway, taskResponseMapper);
        this.removeTaskInteractor = new RemoveTaskInteractor(taskGateway);
        this.updateTaskInteractor =
                new UpdateTaskInteractor(taskGateway, taskResponseMapper, taskRequestMapper);
    }
}
