package services;

import entities.Task;

public interface TaskService {
    void removeTask(int id);
    void completeTask(int id);
    void updateTask(Task task);
}
