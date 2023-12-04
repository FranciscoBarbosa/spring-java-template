package pt.com.francisco.useCases.inputPort;

import pt.com.francisco.entities.Task;
import java.util.List;

public interface TaskUseCase {
    void createTask(Task task);
    void updateTask(Task task);
    void removeTask(int id);
    void completeTask(int id);
    Task getTask(int id);
    List<Task> getAllTasks();
}
