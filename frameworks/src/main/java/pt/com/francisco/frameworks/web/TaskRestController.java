package pt.com.francisco.frameworks.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.openapi.spring.openapi_yml.api.TaskApi;
import org.openapi.spring.openapi_yml.api.TasksApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pt.com.francisco.interfaceadapters.controllers.TaskController;
import pt.com.francisco.usecases.task.TaskRequest;
import pt.com.francisco.usecases.task.TaskResponse;

@RestController
@RequiredArgsConstructor
@CrossOrigin
// TODO: check api @ApiResponse codes for each endpoint
public class TaskRestController implements TaskApi, TasksApi {
    private final TaskController taskController;

    @Override
    public ResponseEntity<TaskResponse> createNewTask(TaskRequest taskRequest) {
        try {
            final TaskResponse createdTask = taskController.createNewTask(taskRequest);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: check this exception returned, should we have a custom one?
    @Override
    public ResponseEntity<TaskResponse> getTask(UUID taskId) {
        try {
            final TaskResponse task = taskController.getTask(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO: check if patch or put
    @Override
    public ResponseEntity<TaskResponse> updateTask(UUID taskId, TaskRequest taskRequest) {
        final TaskResponse taskResponse = taskController.updateTask(taskId, taskRequest);

        if (taskResponse.getId().equals(taskId)) {
            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> taskResponseList = new ArrayList<>(taskController.getAllTasks().get());
        return new ResponseEntity<>(taskResponseList, HttpStatus.OK);
    }
}
