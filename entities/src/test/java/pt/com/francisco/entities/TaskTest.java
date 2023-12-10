package pt.com.francisco.entities;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pt.com.francisco.entities.exceptions.TaskException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    void shouldCreateNotStartedTask(){
        final var task = new Task(0,
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
        final var task = new Task(0,
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
        final var task = new Task(0,
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
        Exception ex = assertThrows(TaskException.class, () -> {
            new Task(0,
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.DOING
            );
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Doing tasks must have a startDate");
    }

    @Test
    void shouldThrowExceptionWhenCreatingTaskOnDoingStatusWithFinishedDate(){
        Exception ex = assertThrows(TaskException.class, () -> {
            new Task(0,
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
        Exception ex = assertThrows(TaskException.class, () -> {
            new Task(0,
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
        Exception ex = assertThrows(TaskException.class, () -> {
            new Task(0,
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.FINISHED,
                    null
            );
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Finished tasks must have a start and finished date");
    }

    @Test
    void shouldCompleteTask(){
        final var task = new Task(0,
                "Buy Christmas gifts",
                "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                Status.DOING,
                LocalDateTime.of(2023,11,23,10,2)
        );
        LocalDateTime currentLocalDate = LocalDateTime.of(2023,11,24,1,20);

        task.complete();

        Assertions.assertThat(task.getStatus()).isEqualTo(Status.FINISHED);
        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(LocalDateTime::now).thenReturn(currentLocalDate);
        }
    }

    @Test
    void shouldNotCompleteAlreadyFinishedTask(){

        Exception ex = assertThrows(TaskException.class, () -> {
            final var task = new Task(0,
                    "Buy Christmas gifts",
                    "Buy a shirt to my brother, a hat to my mom and glasses to my gf",
                    Status.FINISHED,
                    LocalDateTime.of(2023,11,23,10,2),
                    LocalDateTime.of(2023,11,24,10,2)
            );

            task.complete();
        });

        Assertions.assertThat(ex.getMessage()).isEqualTo("Can't complete an already finished task");
    }

}
