package pt.com.francisco.interfaceAdapters.dataAccess;

import lombok.RequiredArgsConstructor;
import pt.com.francisco.entities.Task;
import pt.com.francisco.useCases.task.TaskGateway;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class TaskGatewayImpl implements TaskGateway {
    private final TaskDbRepository taskDbRepository;
    private final TaskDbEntityMapper taskDbEntityMapper;
    @Override
    public Task create(Task task) {
        return taskDbEntityMapper.toTask(taskDbRepository.save(taskDbEntityMapper.toTaskDbEntity(task)));
    }

    @Override
    public Optional<Task> get(UUID id) {
        return Optional.ofNullable(taskDbEntityMapper.toTask(taskDbRepository.findById(id).get()));
    }

    @Override
    public Task update(Task task) {
        TaskDbEntity taskDbEntity = taskDbRepository.findById(task.getId()).get();
        return taskDbEntityMapper.toTask(taskDbRepository.save(taskDbEntity));
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
}
