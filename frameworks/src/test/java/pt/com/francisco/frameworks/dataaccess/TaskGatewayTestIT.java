package pt.com.francisco.frameworks.dataaccess;

import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.DirtiesContext;
import pt.com.francisco.entities.Status;
import pt.com.francisco.entities.Task;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskGatewayTestIT {
    @Autowired CacheManager cacheManager;
    @Autowired TaskGatewayImpl taskGatewayImpl;
    @Autowired TaskDbRepository taskDbRepository;
    @Autowired TaskDbEntityMapper taskDbEntityMapper;
    static final UUID FIRST_UUID = UUID.randomUUID();
    static final UUID SECOND_UUID = UUID.randomUUID();
    final TaskDbEntity firstTask =
            new TaskDbEntity(FIRST_UUID, "firstTask", "description", Status.NOT_STARTED);

    @BeforeEach
    void setUp() {
        taskDbRepository.save(firstTask);
    }

    @Test
    void shouldGetCachedTask() {
        Task task = taskGatewayImpl.get(FIRST_UUID).get();

        Assertions.assertThat(task).isEqualTo(getCachedTask(FIRST_UUID).get());
    }

    @Test
    void shouldNotGetCachedTask() {
        new TaskDbEntity(SECOND_UUID, "secondTask", "description", Status.DOING);

        Assertions.assertThat(Optional.empty()).isEqualTo(getCachedTask(SECOND_UUID));
    }

    private Optional<Task> getCachedTask(UUID taskUUID) {
        try {
            Optional<Cache> tasksCached = Optional.ofNullable(cacheManager.getCache("tasks"));
            return tasksCached.map(c -> c.get(taskUUID, Task.class));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}
