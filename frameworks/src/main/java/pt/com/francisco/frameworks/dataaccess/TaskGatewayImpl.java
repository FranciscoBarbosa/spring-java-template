package pt.com.francisco.frameworks.dataaccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskGateway;

@RequiredArgsConstructor
@Component
public class TaskGatewayImpl implements TaskGateway {
    private final TaskDbRepository taskDbRepository;
    private final TaskDbEntityMapper taskDbEntityMapper;

    @Override
    public Task create(Task task) {
        return taskDbEntityMapper.toTask(
                taskDbRepository.save(taskDbEntityMapper.toTaskDbEntity(task)));
    }

    @Override
    public Optional<Task> get(UUID id) {
        return Optional.ofNullable(taskDbEntityMapper.toTask(taskDbRepository.findById(id).get()));
    }

    @Override
    public Task update(UUID taskId, Task task) {
        if (taskDbRepository.findById(taskId).isPresent()) {
            TaskDbEntity taskDbEntity = taskDbRepository.findById(taskId).get();
            recreateTask(taskDbEntity, taskId, task);
            return taskDbEntityMapper.toTask(taskDbEntity);
        } else {
            return create(task);
        }
    }

    @Override
    public void delete(UUID id) {
        taskDbRepository.deleteById(id);
    }

    @Override
    public List<Task> getAll() {
        return StreamSupport.stream(taskDbRepository.findAll().spliterator(), false)
                .toList()
                .stream()
                .map(taskDbEntityMapper::toTask)
                .toList();
    }

    private void recreateTask(TaskDbEntity taskDbEntity, UUID taskId, Task task) {
        taskDbEntity.setId(taskId);
        taskDbEntity.setName(task.getName());
        taskDbEntity.setStatus(task.getStatus());
        taskDbEntity.setDescription(task.getDescription());
        taskDbEntity.setStartDate(task.getStartDate());
        taskDbEntity.setFinishedDate(task.getFinishedDate());

        taskDbRepository.save((taskDbEntity));
    }
}
