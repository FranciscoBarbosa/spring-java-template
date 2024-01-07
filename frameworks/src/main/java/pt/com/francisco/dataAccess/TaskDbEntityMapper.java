package pt.com.francisco.dataAccess;

import org.mapstruct.Mapper;
import pt.com.francisco.entities.Task;

@Mapper(componentModel = "spring")
public interface TaskDbEntityMapper {
    Task toTask(TaskDbEntity taskDbEntity);

    TaskDbEntity toTaskDbEntity(Task task);
}
