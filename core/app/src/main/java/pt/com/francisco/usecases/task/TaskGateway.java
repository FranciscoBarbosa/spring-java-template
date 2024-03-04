package pt.com.francisco.usecases.task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pt.com.francisco.entities.Task;

public interface TaskGateway {
    Task create(Task task);

    Optional<Task> get(UUID id);

    Task update(UUID taskId, Task task);

    void delete(UUID id);

    List<Task> getAll();
}
