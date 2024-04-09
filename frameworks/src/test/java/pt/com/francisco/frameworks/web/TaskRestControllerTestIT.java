package pt.com.francisco.frameworks.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import pt.com.francisco.entities.Status;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.dto.TaskResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TaskRestControllerTestIT {
    @LocalServerPort int port;
    @Autowired TaskRestController taskRestController;
    final Header contentTypeJson = new Header("content-type", "application/json");
    ObjectMapper jsonMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        jsonMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        jsonMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    }

    @Test
    void shouldCreateTask() {
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        given().auth()
                .basic("user", "pass")
                .body(taskRequestJson)
                .header(contentTypeJson)
                .when()
                .post("/task")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON)
                .body(
                        "name",
                        is("IntegrationTest"),
                        "description",
                        is("test the task creation flow"),
                        "status",
                        is(Status.NOT_STARTED.getValue()));
    }

    @Test
    void shouldNotCreateTaskWithInvalidModel() {
        final InvalidTaskRequest invalidTaskRequest = new InvalidTaskRequest();
        invalidTaskRequest.setInvalidName("invalid name");
        invalidTaskRequest.setInvalidDescription("test invalid request model");
        invalidTaskRequest.setInvalidStatus("invalid status");

        final String taskRequestJson = mapToJson(invalidTaskRequest);

        given().auth()
                .preemptive()
                .basic("user", "pass")
                .body(taskRequestJson)
                .header(contentTypeJson)
                .when()
                .post("/task")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void shouldGetExistentTask() {
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        UUID taskId =
                given().auth()
                        .basic("user", "pass")
                        .body(taskRequestJson)
                        .header(contentTypeJson)
                        .when()
                        .post("/task")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .as(TaskResponse.class)
                        .getId();

        given().auth()
                .basic("user", "pass")
                .when()
                .get("/task/" + taskId)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldNotGetInexistentTask() {
        UUID taskId = UUID.randomUUID();

        given().auth()
                .basic("user", "pass")
                .when()
                .get("/task/" + taskId)
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void shouldUpdateNewTask() {
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);
        UUID taskId = UUID.randomUUID();

        given().auth()
                .basic("user", "pass")
                .body(taskRequestJson)
                .header(contentTypeJson)
                .when()
                .put("/task/" + taskId)
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void shouldUpdateExistentTask() {
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        UUID taskId =
                given().auth()
                        .basic("user", "pass")
                        .body(taskRequestJson)
                        .header(contentTypeJson)
                        .when()
                        .post("/task")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .as(TaskResponse.class)
                        .getId();

        taskRequest.setName("new name");
        String taskRequestUpdatedJson = mapToJson(taskRequest);

        given().auth()
                .basic("user", "pass")
                .body(taskRequestUpdatedJson)
                .header(contentTypeJson)
                .when()
                .put("/task/" + taskId)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("name", is("new name"), "id", is(taskId.toString()));
    }

    @Test
    void shouldNotDeleteInexistentTask() {
        given().auth()
                .basic("user", "pass")
                .header(contentTypeJson)
                .when()
                .delete("/task/" + UUID.randomUUID())
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void shouldDeleteTask() {
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        UUID taskId =
                given().auth()
                        .basic("user", "pass")
                        .body(taskRequestJson)
                        .header(contentTypeJson)
                        .when()
                        .post("/task")
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .extract()
                        .as(TaskResponse.class)
                        .getId();

        given().auth()
                .basic("user", "pass")
                .header(contentTypeJson)
                .when()
                .delete("/task/" + taskId)
                .then()
                .assertThat()
                .statusCode(204);
    }

    @Test
    void shouldGetNoTasks() {

        given().auth()
                .basic("user", "pass")
                .header(contentTypeJson)
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("size()", is(0));
    }

    @Test
    void shouldGetTwoTasks() {
        TaskRequest taskRequest = createTaskRequest();
        String taskRequestJson = mapToJson(taskRequest);

        given().auth()
                .basic("user", "pass")
                .body(taskRequestJson)
                .header(contentTypeJson)
                .when()
                .post("/task")
                .then()
                .assertThat()
                .statusCode(201);

        TaskRequest secondTaskRequest = createTaskRequest();
        String secondTaskRequestJson = mapToJson(secondTaskRequest);

        given().auth()
                .basic("user", "pass")
                .body(secondTaskRequestJson)
                .header(contentTypeJson)
                .when()
                .post("/task")
                .then()
                .assertThat()
                .statusCode(201);

        given().auth()
                .basic("user", "pass")
                .header(contentTypeJson)
                .when()
                .get("/tasks")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("size()", is(2));
    }

    @SneakyThrows
    private String mapToJson(Object object) {

        return jsonMapper.writeValueAsString(object);
    }

    private TaskRequest createTaskRequest() {
        final TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("IntegrationTest");
        taskRequest.setDescription("test the task creation flow");
        taskRequest.setStatus(TaskRequest.StatusEnum.NOT_STARTED);

        return taskRequest;
    }
}
