package pt.com.francisco.services;

import pt.com.francisco.entities.Task;

public interface TaskService {
    void removeTask(int id);
    void completeTask(int id);
    void updateTask(Task task);
}
