package pt.com.francisco.interfaceAdapters.dataAccess.mappers;

import org.mapstruct.Mapper;
import org.openapitools.model.TaskRequest;
import org.openapitools.model.TaskResponse;
import pt.com.francisco.entities.Task;

@Mapper
public interface taskResponseMapper {

    TaskResponse mapTaskToTaskResponse(Task task);

    Task mapTaskRequest(TaskRequest taskRequest);
}
