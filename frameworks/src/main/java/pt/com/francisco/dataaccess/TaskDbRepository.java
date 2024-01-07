package pt.com.francisco.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskDbRepository extends CrudRepository<TaskDbEntity, UUID> {
    @Override
    public TaskDbEntity save(TaskDbEntity task);

    @Override
    public Optional<TaskDbEntity> findById(UUID id);

    @Override
    public Iterable<TaskDbEntity> findAll();


}
