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
    final TaskDbEntity firstTask = new TaskDbEntity("firstTask", "description", Status.NOT_STARTED);

    @BeforeEach
    void setUp() {
        taskDbRepository.save(firstTask);
    }

    @Test
    void shouldGetCachedTask() {
        UUID firstTaskUUID = firstTask.getId();
        Task task = taskGatewayImpl.get(firstTaskUUID).get();

        Assertions.assertThat(task).isEqualTo(getCachedTask(firstTaskUUID).get());
    }

    @Test
    void shouldNotGetCachedTask() {
        final TaskDbEntity secondTask = new TaskDbEntity("secondTask", "description", Status.DOING);
        UUID secondTaskUUID = secondTask.getId();

        Assertions.assertThat(Optional.empty()).isEqualTo(getCachedTask(secondTaskUUID));
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
