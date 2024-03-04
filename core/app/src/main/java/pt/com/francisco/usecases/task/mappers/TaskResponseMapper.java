package pt.com.francisco.usecases.task.mappers;

import org.mapstruct.Mapper;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskResponse;

@Mapper
public interface TaskResponseMapper {
    TaskResponse map(Task task);
}
