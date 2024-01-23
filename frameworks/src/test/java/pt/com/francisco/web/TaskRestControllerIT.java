package pt.com.francisco.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.model.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import pt.com.francisco.entities.Task;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRestControllerIT {
    @LocalServerPort
    int port;
    @Autowired
    TaskRestController taskRestController;
    final Header contentTypeJson = new Header("content-type", "application/json");
    ObjectMapper jsonMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        jsonMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        jsonMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    }
    @Test
    void shouldCreateTask(){
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        given().body(taskRequestJson).header(contentTypeJson)
                .when().post("/task")
                .then()
                    .assertThat()
                        .statusCode(201)
                    .and()
                        .contentType(ContentType.JSON)
                        .body("name", is(taskRequest.getName()),
                                "description", is(taskRequest.getDescription()),
                                        "status", is(taskRequest.getStatus().getValue()));
    }

    @Test
    void shouldNotCreateTaskWithInvalidModel(){
        final InvalidTaskRequest invalidTaskRequest = new InvalidTaskRequest();
        invalidTaskRequest.setInvalidName("invalid name");
        invalidTaskRequest.setInvalidDescription("test invalid request model");
        invalidTaskRequest.setInvalidStatus("invalid status");

        final String taskRequestJson = mapToJson(invalidTaskRequest);

        given().body(taskRequestJson).header(contentTypeJson)
                .when().post("/task")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void shouldGetExistentTask(){
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        UUID taskId = given().body(taskRequestJson)
            .header(contentTypeJson)
            .when().post("/task").then()
            .assertThat()
            .statusCode(201).extract().as(Task.class).getId();

        given().body(taskRequestJson)//.header(contentTypeJson)
                .when().get("/task/" + taskId)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @SneakyThrows
    private String mapToJson(Object object){

        return jsonMapper.writeValueAsString(object);
    }

    private TaskRequest createTaskRequest(){
        final TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("IntegrationTest");
        taskRequest.setDescription("test the task creation flow");
        taskRequest.setStatus(TaskRequest.StatusEnum.NOT_STARTED);

        return taskRequest;
    }
}