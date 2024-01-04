package pt.com.francisco.dataAccess;

import org.mapstruct.Mapper;
import pt.com.francisco.entities.Task;

@Mapper(componentModel = "spring")
public interface TaskDbEntityMapper {
    public Task toTask(TaskDbEntity taskDbEntity);

    public TaskDbEntity toTaskDbEntity(Task task);
}
