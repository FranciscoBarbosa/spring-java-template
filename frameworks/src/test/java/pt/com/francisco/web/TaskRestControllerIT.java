package pt.com.francisco.web;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRestControllerIT {
    @LocalServerPort
    int port;
    @Autowired
    TaskRestController taskRestController;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    @Test
    void shouldCreateTask(){
        Header contentTypeJson = new Header("content-type", "application/json");

        final TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("IntegrationTest");
        taskRequest.setDescription("test the task creation flow");
        taskRequest.setStatus(TaskRequest.StatusEnum.NOT_STARTED);

        final String taskRequestJson = mapToJson(taskRequest);

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
        Header contentTypeJson = new Header("content-type", "application/json");

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

    @SneakyThrows
    private String mapToJson(Object object){
        var jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(object);
    }
}