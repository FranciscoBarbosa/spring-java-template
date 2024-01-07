package pt.com.francisco.usecases.task;

import pt.com.francisco.entities.Task;
import java.util.List;
import java.util.UUID;

public interface TaskInputBoundary {
    Task createTask(Task task);
    void updateTask(Task task);
    void removeTask(UUID id);
    void completeTask(UUID id);
    Task getTask(UUID id);

    List<Task> getAllTasks();

}
