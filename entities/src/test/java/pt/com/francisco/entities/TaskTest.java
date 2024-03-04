package pt.com.francisco.entities;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pt.com.francisco.entities.exceptions.TaskException;

class TaskTest {
    @Test
    void shouldCreateNotStartedTask() {
        final Task task =
                new Task.TaskBuilder()
                        .name("Buy Christmas gifts")
                        .description(
                                "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                        .status(Status.NOT_STARTED)
                        .build();

        Assertions.assertThat(task.getName()).isEqualTo("Buy Christmas gifts");
        Assertions.assertThat(task.getDescription())
                .isEqualTo("Buy a shirt to my brother, a hat to my mom and glasses to my gf");
        Assertions.assertThat(task.getStatus()).isEqualTo(Status.NOT_STARTED);
        Assertions.assertThat(task.getStartDate()).isNull();
        Assertions.assertThat(task.getFinishedDate()).isNull();
    }

    @Test
    void shouldNotCreateTaskWithoutStatus() {

        Exception ex =
                assertThrows(
                        TaskException.class,
                        () ->
                                new Task.TaskBuilder()
                                        .name("Buy Christmas gifts")
                                        .description(
                                                "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                                        .build());

        Assertions.assertThat(ex.getMessage()).isEqualTo("Cannot create task without status");
    }

    @Test
    void shouldCreateTaskOnDoingStatusWithStartDate() {
        final var task =
                new Task.TaskBuilder()
                        .name("Buy Christmas gifts")
                        .description(
                                "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                        .status(Status.DOING)
                        .startDate(LocalDateTime.of(2023, 11, 23, 10, 2))
                        .build();

        Assertions.assertThat(task.getName()).isEqualTo("Buy Christmas gifts");
        Assertions.assertThat(task.getDescription())
                .isEqualTo("Buy a shirt to my brother, a hat to my mom and glasses to my gf");
        Assertions.assertThat(task.getStatus()).isEqualTo(Status.DOING);
        Assertions.assertThat(task.getStartDate()).isEqualTo(LocalDateTime.of(2023, 11, 23, 10, 2));
        Assertions.assertThat(task.getFinishedDate()).isNull();
    }

    @Test
    void shouldCreateTaskOnFinishedStatusWithStartAndFinishedDate() {
        final var task =
                new Task.TaskBuilder()
                        .name("Buy Christmas gifts")
                        .description(
                                "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                        .status(Status.FINISHED)
                        .startDate(LocalDateTime.of(2023, 11, 23, 10, 2))
                        .finishedDate(LocalDateTime.of(2023, 12, 5, 19, 10))
                        .build();

        Assertions.assertThat(task.getName()).isEqualTo("Buy Christmas gifts");
        Assertions.assertThat(task.getDescription())
                .isEqualTo("Buy a shirt to my brother, a hat to my mom and glasses to my gf");
        Assertions.assertThat(task.getStatus()).isEqualTo(Status.FINISHED);
        Assertions.assertThat(task.getStartDate()).isEqualTo(LocalDateTime.of(2023, 11, 23, 10, 2));
        Assertions.assertThat(task.getFinishedDate())
                .isEqualTo(LocalDateTime.of(2023, 12, 5, 19, 10));
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnDoingStatusWithoutStartDate() {
        Exception ex =
                assertThrows(
                        TaskException.class,
                        () -> {
                            new Task.TaskBuilder()
                                    .name("Buy Christmas gifts")
                                    .description(
                                            "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                                    .status(Status.DOING)
                                    .build();
                        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Doing tasks must have a startDate");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnDoingStatusWithFinishedDate() {
        Exception ex =
                assertThrows(
                        TaskException.class,
                        () -> {
                            new Task.TaskBuilder()
                                    .name("Buy Christmas gifts")
                                    .description(
                                            "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                                    .status(Status.DOING)
                                    .build();
                        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Doing tasks must have a startDate");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnFinishedStatusWithoutStartedDate() {
        Exception ex =
                assertThrows(
                        TaskException.class,
                        () -> {
                            new Task.TaskBuilder()
                                    .name("Buy Christmas gifts")
                                    .description(
                                            "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                                    .status(Status.FINISHED)
                                    .build();
                        });

        Assertions.assertThat(ex.getMessage())
                .isEqualTo("Finished tasks must have a start and finished date");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnFinishedStatusWithoutFinishedDate() {
        Exception ex =
                assertThrows(
                        TaskException.class,
                        () -> {
                            new Task.TaskBuilder()
                                    .name("Buy Christmas gifts")
                                    .description(
                                            "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                                    .status(Status.FINISHED)
                                    .build();
                        });

        Assertions.assertThat(ex.getMessage())
                .isEqualTo("Finished tasks must have a start and finished date");
    }

    @Test
    void shouldCompleteTask() {
        final var task =
                new Task.TaskBuilder()
                        .name("Buy Christmas gifts")
                        .description(
                                "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                        .status(Status.DOING)
                        .startDate(LocalDateTime.of(2023, 11, 23, 10, 2))
                        .build();
        LocalDateTime currentLocalDate = LocalDateTime.of(2023, 11, 24, 1, 20);

        task.complete();

        Assertions.assertThat(task.getStatus()).isEqualTo(Status.FINISHED);
        try (MockedStatic<LocalDateTime> topDateTimeUtilMock =
                Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(LocalDateTime::now).thenReturn(currentLocalDate);
        }
    }

    @Test
    void shouldNotCompleteAlreadyFinishedTask() {

        Exception ex =
                assertThrows(
                        TaskException.class,
                        () -> {
                            final var task =
                                    new Task.TaskBuilder()
                                            .name("Buy Christmas gifts")
                                            .description(
                                                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf")
                                            .status(Status.FINISHED)
                                            .startDate(LocalDateTime.of(2023, 11, 23, 10, 2))
                                            .finishedDate(LocalDateTime.of(2023, 11, 24, 10, 2))
                                            .build();

                            task.complete();
                        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Can't complete an already finished task");
    }
}
