package pt.com.francisco.usecases.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;

@ExtendWith(MockitoExtension.class)
class TaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new TaskInteractor(taskGateway);
    }

    @Test
    void shouldCreateTask() {
        Mockito.when(taskGateway.create(task)).thenReturn(task);

        taskInputBoundary.createTask(task);

        Mockito.verify(taskGateway).create(task);
    }
}
