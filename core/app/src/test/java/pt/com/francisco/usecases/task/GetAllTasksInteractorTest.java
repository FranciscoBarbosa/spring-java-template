package pt.com.francisco.usecases.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.interactors.GetAllTasksInteractor;
import pt.com.francisco.usecases.task.mappers.TaskResponseMapper;

@ExtendWith(MockitoExtension.class)
class GetAllTasksInteractorTest {
    @Mock private Task task;
    @Mock private TaskGateway taskGateway;
    @Mock private TaskResponseMapper taskResponseMapper;
    private TaskInputBoundary taskInputBoundary;

    @BeforeEach
    void setup() {
        taskInputBoundary = new GetAllTasksInteractor(taskGateway, taskResponseMapper);
    }

    @Test
    void shouldGetAllTasks() {
        taskInputBoundary.execute(null);

        Mockito.verify(taskGateway).getAll();
    }
}
