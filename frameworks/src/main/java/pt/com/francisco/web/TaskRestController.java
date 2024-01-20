package pt.com.francisco.web;

import lombok.RequiredArgsConstructor;
import org.openapi.spring.openapi_yml.api.TaskApi;
import org.openapi.spring.openapi_yml.api.TasksApi;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pt.com.francisco.interfaceadapters.controllers.TaskController;
import pt.com.francisco.web.mappers.TaskRequestMapper;
import pt.com.francisco.web.mappers.TaskResponseMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TaskRestController implements TaskApi, TasksApi {
    private final TaskController taskController;
    private final TaskRequestMapper taskRequestMapper;
    private final TaskResponseMapper taskResponseMapper;
    @Override
    public ResponseEntity<TaskResponse> createNewTask(TaskRequest taskRequest) {
        final TaskResponse createdTask = taskResponseMapper.map(
                taskController.createNewTask(
                        taskRequestMapper.map(taskRequest)
                )
        );

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TaskResponse> getTask(UUID taskId) {
        return null;
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(UUID taskId, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return null;
    }
}
