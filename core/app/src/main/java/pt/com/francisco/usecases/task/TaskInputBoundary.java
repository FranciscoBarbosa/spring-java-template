package pt.com.francisco.usecases.task;

import java.util.List;
import java.util.UUID;

public interface TaskInputBoundary {

    TaskResponse createTask(TaskRequest task);

    TaskResponse updateTask(UUID taskId, TaskRequest task);

    void removeTask(UUID id);

    void completeTask(UUID id);

    TaskResponse getTask(UUID id);

    List<TaskResponse> getAllTasks();
}
