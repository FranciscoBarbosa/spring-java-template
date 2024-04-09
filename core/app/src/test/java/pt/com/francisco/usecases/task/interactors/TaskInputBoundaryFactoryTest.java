package pt.com.francisco.usecases.task.interactors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.francisco.usecases.task.TaskGateway;

@ExtendWith(MockitoExtension.class)
class TaskInputBoundaryFactoryTest {
    @Mock private TaskGateway taskGateway;
    private final TaskInputBoundaryFactory taskInputBoundaryFactory =
            new TaskInputBoundaryFactory(taskGateway);

    @Test
    void shouldBuildUseCases() {
        CreateTaskInteractor createTaskInteractor =
                (CreateTaskInteractor) taskInputBoundaryFactory.getCreateTaskInteractor();
        UpdateTaskInteractor updateTaskInteractor =
                (UpdateTaskInteractor) taskInputBoundaryFactory.getUpdateTaskInteractor();
        GetTaskInteractor getTaskInteractor =
                (GetTaskInteractor) taskInputBoundaryFactory.getGetTaskInteractor();
        GetAllTasksInteractor getAllTasksInteractor =
                (GetAllTasksInteractor) taskInputBoundaryFactory.getGetAllTasksInteractor();
        UpdateTaskInteractor updateTaskInteractor1 =
                (UpdateTaskInteractor) taskInputBoundaryFactory.getUpdateTaskInteractor();
        RemoveTaskInteractor removeTaskInteractor =
                (RemoveTaskInteractor) taskInputBoundaryFactory.getRemoveTaskInteractor();
        CompleteTaskInteractor completeTaskInteractor =
                (CompleteTaskInteractor) taskInputBoundaryFactory.getCompleteTaskInteractor();

        org.assertj.core.api.Assertions.assertThat(createTaskInteractor).isNotNull();
        org.assertj.core.api.Assertions.assertThat(updateTaskInteractor).isNotNull();
        org.assertj.core.api.Assertions.assertThat(getTaskInteractor).isNotNull();
        org.assertj.core.api.Assertions.assertThat(getAllTasksInteractor).isNotNull();
        org.assertj.core.api.Assertions.assertThat(updateTaskInteractor1).isNotNull();
        org.assertj.core.api.Assertions.assertThat(removeTaskInteractor).isNotNull();
        org.assertj.core.api.Assertions.assertThat(completeTaskInteractor).isNotNull();
    }
}
