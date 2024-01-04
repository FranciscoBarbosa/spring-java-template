package pt.com.francisco.web.mappers;

import org.mapstruct.Mapper;
import org.openapitools.model.TaskRequest;
import pt.com.francisco.entities.Task;

@Mapper(componentModel="spring")
public interface TaskRequestMapper {
    Task map(TaskRequest taskRequest);
}