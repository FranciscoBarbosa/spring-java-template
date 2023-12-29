package pt.com.francisco.interfaceAdapters.dataAccess;

import org.mapstruct.Mapper;
import pt.com.francisco.entities.Task;

@Mapper
public interface TaskDbEntityMapper {

    public Task toTask(TaskDbEntity taskDbEntity);

    public TaskDbEntity toTaskDbEntity(Task task);

}
