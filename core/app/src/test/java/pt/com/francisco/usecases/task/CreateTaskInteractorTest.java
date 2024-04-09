package pt.com.francisco.usecases.task;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.dto.TaskRequest;
import pt.com.francisco.usecases.task.interactors.CreateTaskInteractor;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@ExtendWith(MockitoExtension.class)
class CreateTaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    @Mock private TaskResponseMapper taskResponseMapper;
    @Mock private TaskRequestMapper taskRequestMapper;
    @Mock private TaskRequest taskRequest;

    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary =
                new CreateTaskInteractor(taskGateway, taskResponseMapper, taskRequestMapper);
    }

    @Test
    void shouldCreateTask() {
        given(taskRequestMapper.map(taskRequest)).willReturn(task);

        taskInputBoundary.execute(taskRequest);

        Mockito.verify(taskGateway).create(task);
    }
}
