package pt.com.francisco.web.mappers;

import org.mapstruct.Mapper;
import org.openapitools.model.TaskResponse;
import pt.com.francisco.entities.Task;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {
    TaskResponse map(Task task);
}
