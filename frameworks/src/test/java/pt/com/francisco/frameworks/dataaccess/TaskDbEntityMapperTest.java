package pt.com.francisco.frameworks.dataaccess;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.com.francisco.entities.Status;
import pt.com.francisco.entities.Task;

class TaskDbEntityMapperTest {
    TaskDbEntityMapper taskDbEntityMapper = new TaskDbEntityMapperImpl();

    @Test
    void shouldMaptoTaskDbEntityWhenTaskNotStarted() {
        final var task =
                new Task.TaskBuilder()
                        .name("name")
                        .description("description")
                        .status(Status.NOT_STARTED)
                        .build();
        TaskDbEntity taskDbEntity = taskDbEntityMapper.toTaskDbEntity(task);

        Assertions.assertThat(taskDbEntity.getDescription()).isEqualTo("description");
        Assertions.assertThat(taskDbEntity.getName()).isEqualTo("name");
        Assertions.assertThat(taskDbEntity.getStatus()).isEqualTo(Status.NOT_STARTED);
    }

    @Test
    void shouldMaptoTaskDbEntityWhenTaskDone() {
        final var task =
                new Task.TaskBuilder()
                        .name("name")
                        .description("description")
                        .status(Status.FINISHED)
                        .startDate(LocalDateTime.of(2023, 11, 23, 10, 2))
                        .finishedDate(LocalDateTime.of(2023, 11, 23, 12, 2))
                        .build();
        TaskDbEntity taskDbEntity = taskDbEntityMapper.toTaskDbEntity(task);

        Assertions.assertThat(taskDbEntity.getDescription()).isEqualTo("description");
        Assertions.assertThat(taskDbEntity.getName()).isEqualTo("name");
        Assertions.assertThat(taskDbEntity.getStatus()).isEqualTo(Status.FINISHED);
        Assertions.assertThat(taskDbEntity.getStartDate())
                .isEqualTo(LocalDateTime.of(2023, 11, 23, 10, 2));
        Assertions.assertThat(taskDbEntity.getFinishedDate())
                .isEqualTo(LocalDateTime.of(2023, 11, 23, 12, 2));
    }
}
