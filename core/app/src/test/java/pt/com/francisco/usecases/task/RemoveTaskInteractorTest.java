package pt.com.francisco.usecases.task;

import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.interactors.RemoveTaskInteractor;

@ExtendWith(MockitoExtension.class)
class RemoveTaskInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new RemoveTaskInteractor(taskGateway);
    }

    @Test
    void shouldRemoveTask() {
        UUID taskUuid = UUID.randomUUID();

        given(taskGateway.get(taskUuid)).willReturn(Optional.of(task));

        taskInputBoundary.execute(taskUuid);

        Mockito.verify(taskGateway).delete(taskUuid);
    }
}
