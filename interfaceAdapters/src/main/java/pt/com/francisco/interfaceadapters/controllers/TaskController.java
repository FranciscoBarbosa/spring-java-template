package pt.com.francisco.interfaceadapters.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.usecases.task.TaskInputBoundary;
import pt.com.francisco.usecases.task.TaskRequest;
import pt.com.francisco.usecases.task.TaskResponse;

@RequiredArgsConstructor
public class TaskController {
    private final TaskInputBoundary taskInputBoundary;

    public TaskResponse createNewTask(TaskRequest task) {
        return taskInputBoundary.createTask(task);
    }

    public TaskResponse getTask(UUID taskId) {
        return taskInputBoundary.getTask(taskId);
    }

    public TaskResponse updateTask(UUID taskId, TaskRequest task) {
        return taskInputBoundary.updateTask(taskId, task);
    }

    public Optional<List<TaskResponse>> getAllTasks() {
        return Optional.of(taskInputBoundary.getAllTasks().stream().toList());
    }
}
