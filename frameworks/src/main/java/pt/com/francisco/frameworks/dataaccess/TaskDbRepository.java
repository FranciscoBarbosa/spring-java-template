package pt.com.francisco.frameworks.dataaccess;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDbRepository extends CrudRepository<TaskDbEntity, UUID> {
    @Override
    public TaskDbEntity save(TaskDbEntity task);

    @Override
    public Optional<TaskDbEntity> findById(UUID id);

    @Override
    public Iterable<TaskDbEntity> findAll();
}
