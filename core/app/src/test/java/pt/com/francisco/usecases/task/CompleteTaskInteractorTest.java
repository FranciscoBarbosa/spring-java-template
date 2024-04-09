package pt.com.francisco.usecases.task;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.interactors.CompleteTaskInteractor;

@ExtendWith(MockitoExtension.class)
class CompleteTaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new CompleteTaskInteractor(taskGateway);
    }

    @Test
    void shouldCompleteTask() {
        var taskId = UUID.randomUUID();
        given(taskGateway.get(taskId)).willReturn(Optional.of(task));

        taskInputBoundary.execute(taskId);

        verify(task).complete();
    }
}
