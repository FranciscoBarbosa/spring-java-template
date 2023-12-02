import entities.Status;
import entities.Task;
import entities.exceptions.TaskCreationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    void shouldCreateNotStartedTask(){
        final var task = new Task(
                "Buy Christmas gifts",
                "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                Status.NOT_STARTED
        );

        Assertions.assertThat(task.getName()).isEqualTo("Buy Christmas gifts");
        Assertions.assertThat(task.getDescription()).isEqualTo("Buy a shirt to my brother, a hat to my mom and glasses to my gf");
        Assertions.assertThat(task.getStatus()).isEqualTo(Status.NOT_STARTED);
        Assertions.assertThat(task.getStartDate()).isNull();
        Assertions.assertThat(task.getFinishedDate()).isNull();
    }

    @Test
    void shouldCreateTaskOnDoingStatusWithStartDate(){
        final var task = new Task(
                "Buy Christmas gifts",
                "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                Status.DOING,
                LocalDateTime.of(2023,11,23,10,2)
        );

        Assertions.assertThat(task.getName()).isEqualTo("Buy Christmas gifts");
        Assertions.assertThat(task.getDescription()).isEqualTo("Buy a shirt to my brother, a hat to my mom and glasses to my gf");
        Assertions.assertThat(task.getStatus()).isEqualTo(Status.DOING);
        Assertions.assertThat(task.getStartDate()).isEqualTo(LocalDateTime.of(2023,11,23,10,2));
        Assertions.assertThat(task.getFinishedDate()).isNull();
    }

    @Test
    void shouldCreateTaskOnFinishedStatusWithStartAndFinishedDate(){
        final var task = new Task(
                "Buy Christmas gifts",
                "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                Status.FINISHED,
                LocalDateTime.of(2023,11,23,10,2),
                LocalDateTime.of(2023,12,5,19,10)
        );

        Assertions.assertThat(task.getName()).isEqualTo("Buy Christmas gifts");
        Assertions.assertThat(task.getDescription()).isEqualTo("Buy a shirt to my brother, a hat to my mom and glasses to my gf");
        Assertions.assertThat(task.getStatus()).isEqualTo(Status.FINISHED);
        Assertions.assertThat(task.getStartDate()).isEqualTo(LocalDateTime.of(2023,11,23,10,2));
        Assertions.assertThat(task.getFinishedDate()).isEqualTo(LocalDateTime.of(2023,12,5,19,10));
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnDoingStatusWithoutStartDate(){
        Exception ex = assertThrows(TaskCreationException.class, () -> {
            new Task(
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.DOING
            );
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Doing tasks must have a startDate");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnDoingStatusWithFinishedDate(){
        Exception ex = assertThrows(TaskCreationException.class, () -> {
            new Task(
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.DOING,
                    null
                    );
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Doing tasks must have a startDate");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnFinishedStatusWithoutStartedDate(){
        Exception ex = assertThrows(TaskCreationException.class, () -> {
            new Task(
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.FINISHED,
                    null
            );
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Finished tasks must have a start and finished date");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnFinishedStatusWithoutFinishedDate(){
        Exception ex = assertThrows(TaskCreationException.class, () -> {
            new Task(
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.FINISHED,
                    null
            );
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Finished tasks must have a start and finished date");
    }

}
