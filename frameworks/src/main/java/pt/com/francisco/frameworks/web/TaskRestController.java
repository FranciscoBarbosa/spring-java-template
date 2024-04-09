package pt.com.francisco.frameworks.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.openapi.spring.openapi_yml.api.TaskApi;
import org.openapi.spring.openapi_yml.api.TasksApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pt.com.francisco.entities.exceptions.TaskNotFoundException;
import pt.com.francisco.interfaceadapters.controllers.TaskController;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.dto.TaskResponse;

@RestController
@RequiredArgsConstructor
@CrossOrigin
// TODO: check api @ApiResponse codes for each endpoint
public class TaskRestController implements TaskApi, TasksApi {
    private final TaskController taskController;

    // TODO: can it return optional empty? how to deal with it?
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
        final Optional<TaskResponse> task = taskController.getTask(taskId);
        if (task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
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
    public ResponseEntity<Void> deleteTask(UUID id) {
        try {
            taskController.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> taskResponseList = new ArrayList<>(taskController.getAllTasks());
        return new ResponseEntity<>(taskResponseList, HttpStatus.OK);
    }
}
