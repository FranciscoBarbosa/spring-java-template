package pt.com.francisco.controllers;

import lombok.RequiredArgsConstructor;
import org.openapi.spring.openapi_yml.api.TaskApi;
import org.openapi.spring.openapi_yml.api.TasksApi;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import org.springframework.http.ResponseEntity;
import pt.com.francisco.useCases.inputPort.TaskUseCase;

import java.util.List;

@RequiredArgsConstructor
public class TaskController implements TaskApi, TasksApi {

    private final TaskUseCase taskUseCase;

    @Override
    public ResponseEntity<List<TaskResponse>> createNewTask(TaskRequest taskRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getTask(List<String> taskId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateTask(List<String> taskId, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return null;
    }

}
