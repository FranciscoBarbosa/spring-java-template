package pt.com.francisco.interfaceadapters.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import pt.com.francisco.entities.exceptions.TaskNotFoundException;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.dto.TaskResponse;
import pt.com.francisco.usecases.task.interactors.TaskInputBoundaryFactory;

@RequiredArgsConstructor
public class TaskController {
    private final TaskInputBoundaryFactory taskInputBoundaryFactory;

    public TaskResponse createNewTask(TaskRequest task) {
        return taskInputBoundaryFactory.getCreateTaskInteractor().execute(task);
    }

    public Optional<TaskResponse> getTask(UUID taskId) {
        try {
            return Optional.of(taskInputBoundaryFactory.getGetTaskInteractor().execute(taskId));
        } catch (TaskNotFoundException e) {
            return Optional.empty();
        }
    }

    public TaskResponse updateTask(UUID taskId, TaskRequest task) {
        return taskInputBoundaryFactory.getUpdateTaskInteractor().execute(Pair.of(taskId, task));
    }

    public List<TaskResponse> getAllTasks() {
        return taskInputBoundaryFactory.getGetAllTasksInteractor().execute(null);
    }
}
