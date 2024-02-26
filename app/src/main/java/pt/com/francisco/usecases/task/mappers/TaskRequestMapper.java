package pt.com.francisco.usecases.task.mappers;

import org.mapstruct.Mapper;
import pt.com.francisco.entities.Task;
import pt.com.francisco.usecases.task.TaskRequest;

@Mapper
public interface TaskRequestMapper {
    Task map(TaskRequest taskRequest);
}
