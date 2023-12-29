package pt.com.francisco.interfaceAdapters.dataAccess;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskDbRepository extends CrudRepository<TaskDbEntity, UUID> {
    @Override
    public TaskDbEntity save(TaskDbEntity task);

    @Override
    public Optional<TaskDbEntity> findById(UUID id);

    @Override
    public Iterable<TaskDbEntity> findAll();


}
