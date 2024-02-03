package pt.com.francisco.frameworks.dataaccess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.com.francisco.entities.Status;
import pt.com.francisco.entities.Task;

class TaskDbEntityMapperTest {
    TaskDbEntityMapper taskDbEntityMapper = new TaskDbEntityMapperImpl();

    @Test
    void shouldMaptoTaskDbEntity() {
        final var task = new Task("name", "description", Status.NOT_STARTED);
        TaskDbEntity taskDbEntity = taskDbEntityMapper.toTaskDbEntity(task);

        Assertions.assertThat(taskDbEntity.getDescription()).isEqualTo("description");
        Assertions.assertThat(taskDbEntity.getName()).isEqualTo("name");
        Assertions.assertThat(taskDbEntity.getStatus()).isEqualTo(Status.NOT_STARTED);
    }
}
