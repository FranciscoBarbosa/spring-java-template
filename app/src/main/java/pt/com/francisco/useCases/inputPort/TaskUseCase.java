package pt.com.francisco.useCases.inputPort;

import pt.com.francisco.entities.Task;
import java.util.List;
import java.util.Optional;

public interface TaskUseCase {
    void createTask(Task task);
    void updateTask(Task task);
    void removeTask(int id);
    void completeTask(int id);
    Optional<Task> getTask(int id);
    List<Task> listTasks();
}
