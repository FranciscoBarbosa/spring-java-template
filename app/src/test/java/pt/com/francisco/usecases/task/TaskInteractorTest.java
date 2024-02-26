package pt.com.francisco.usecases.task;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.mappers.TaskRequestMapper;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@ExtendWith(MockitoExtension.class)
class TaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    @Mock private TaskResponseMapper taskResponseMapper;
    @Mock private TaskRequestMapper taskRequestMapper;
    @Mock private TaskRequest taskRequest;
    @Mock private TaskResponse taskResponse;

    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new TaskInteractor(taskGateway, taskResponseMapper, taskRequestMapper);
    }

    @Test
    void shouldCreateTask() {
        given(taskGateway.create(task)).willReturn(task);
        given(taskRequestMapper.map(taskRequest)).willReturn(task);
        taskInputBoundary.createTask(taskRequest);

        Mockito.verify(taskGateway).create(task);
    }
}
