package pt.com.francisco.interfaceadapters.controllers;

import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskInputBoundary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskController {
    private final TaskInputBoundary taskInputBoundary;

    public Task createNewTask(Task task) {
        return taskInputBoundary.createTask(task);
    }

    public Task getTask(UUID taskId) {
        return taskInputBoundary.getTask(taskId);
    }

    public Task updateTask(UUID taskId, Task task) {
        return null;
    }

    public Optional<List<Task>> getAllTasks() {
        return Optional.of(taskInputBoundary.getAllTasks().stream().toList());
    }
}
