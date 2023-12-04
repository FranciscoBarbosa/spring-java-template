package pt.com.francisco.repository;

import pt.com.francisco.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void create(Task task);
    Optional<Task> get(int id);
    void update(int id, Task task);
    void delete(int id);
    List<Task> getAll();
}
