package pt.com.francisco.usecases.task.interactors;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskGateway;
import pt.com.francisco.usecases.task.TaskInputBoundary;

@RequiredArgsConstructor
public class CompleteTaskInteractor implements TaskInputBoundary<Void, UUID> {
    private final TaskGateway taskGateway;

    @Override
    public Void execute(UUID id) {
        final Task task = taskGateway.get(id).get();
        task.complete();
        return null;
    }
}
