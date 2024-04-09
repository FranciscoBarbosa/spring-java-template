package pt.com.francisco.usecases.task.interactors;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.entities.exceptions.TaskNotFoundException;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;

@RequiredArgsConstructor
public class RemoveTaskInteractor implements TaskInputBoundary<Void, UUID> {
    private final TaskGateway taskGateway;

    @Override
    public Void execute(UUID taskId) {
        Optional<Task> task = taskGateway.get(taskId);
        if (task.isEmpty()) {
            throw new TaskNotFoundException();
        }
        taskGateway.delete(taskId);
        return null;
    }
}
