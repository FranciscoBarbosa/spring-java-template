package pt.com.francisco.usecases.task;

import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.entities.exceptions.TaskNotFoundException;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.interactors.GetTaskInteractor;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@ExtendWith(MockitoExtension.class)
class GetTaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    @Mock private TaskResponseMapper taskResponseMapper;
    @Mock private TaskRequest taskRequest;
    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new GetTaskInteractor(taskGateway, taskResponseMapper);
    }

    @Test
    void shouldGetTask() {
        UUID taskUuid = UUID.randomUUID();
        given(taskGateway.get(taskUuid)).willReturn(Optional.of(task));

        taskInputBoundary.execute(taskUuid);

        Mockito.verify(taskGateway).get(taskUuid);
    }

    @Test
    void shouldNotGetTask() {
        UUID taskUuid = UUID.randomUUID();
        given(taskGateway.get(taskUuid)).willReturn(Optional.empty());

        Assertions.assertThrows(
                TaskNotFoundException.class, () -> taskInputBoundary.execute(taskUuid));

        Mockito.verify(taskGateway).get(taskUuid);
    }
}
