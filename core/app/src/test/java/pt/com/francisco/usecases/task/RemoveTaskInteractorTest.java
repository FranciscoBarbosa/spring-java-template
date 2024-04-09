package pt.com.francisco.usecases.task;

import static org.mockito.BDDMockito.given;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.interactors.RemoveTaskInteractor;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@ExtendWith(MockitoExtension.class)
class RemoveTaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    @Mock private TaskResponseMapper taskResponseMapper;
    @Mock private TaskRequestMapper taskRequestMapper;
    @Mock private TaskRequest taskRequest;
    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new RemoveTaskInteractor(taskGateway, taskRequestMapper);
    }

    @Test
    void shouldUpdateTask() {
        UUID taskUuid = UUID.randomUUID();

        given(taskRequestMapper.map(taskRequest)).willReturn(task);
        given(task.getId()).willReturn(taskUuid);

        taskInputBoundary.execute(taskRequest);

        Mockito.verify(taskGateway).delete(task.getId());
    }
}
