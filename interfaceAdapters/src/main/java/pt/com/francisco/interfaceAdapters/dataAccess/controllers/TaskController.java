package pt.com.francisco.interfaceAdapters.dataAccess.controllers;

import lombok.RequiredArgsConstructor;
import org.openapi.spring.openapi_yml.api.TaskApi;
import org.openapi.spring.openapi_yml.api.TasksApi;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import org.springframework.http.ResponseEntity;
import pt.com.francisco.interfaceAdapters.dataAccess.mappers.taskResponseMapper;
import pt.com.francisco.useCases.task.TaskInputBoundary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TaskController implements TaskApi, TasksApi {

    private final TaskInputBoundary taskInputBoundary;
    private final taskResponseMapper mapper;

    @Override
    public ResponseEntity<TaskResponse> createNewTask(TaskRequest taskRequest) {
        return ResponseEntity.ok(
                mapper.mapTaskToTaskResponse
                (taskInputBoundary
                    .createTask(mapper.mapTaskRequest(taskRequest))
                )
        );
    }

    @Override
    public ResponseEntity<TaskResponse> getTask(UUID taskId) {
        return ResponseEntity.ok(mapper.mapTaskToTaskResponse(taskInputBoundary.getTask(taskId)));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(UUID taskId, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.of(Optional.of(taskInputBoundary.getAllTasks().stream().map(mapper::mapTaskToTaskResponse).toList()));
    }
}
