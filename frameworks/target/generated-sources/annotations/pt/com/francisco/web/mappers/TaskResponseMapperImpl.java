package pt.com.francisco.web.mappers;

import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.openapitools.model.TaskResponse;
import org.springframework.stereotype.Component;
import pt.com.francisco.entities.Status;
import pt.com.francisco.entities.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T00:16:06+0000",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class TaskResponseMapperImpl implements TaskResponseMapper {

    @Override
    public TaskResponse map(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId( task.getId() );
        taskResponse.setName( task.getName() );
        taskResponse.setDescription( task.getDescription() );
        taskResponse.setStatus( statusToStatusEnum( task.getStatus() ) );
        if ( task.getStartDate() != null ) {
            taskResponse.setStartDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( task.getStartDate() ) );
        }
        if ( task.getFinishedDate() != null ) {
            taskResponse.setFinishedDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( task.getFinishedDate() ) );
        }

        return taskResponse;
    }

    protected TaskResponse.StatusEnum statusToStatusEnum(Status status) {
        if ( status == null ) {
            return null;
        }

        TaskResponse.StatusEnum statusEnum;

        switch ( status ) {
            case FINISHED: statusEnum = TaskResponse.StatusEnum.FINISHED;
            break;
            case DOING: statusEnum = TaskResponse.StatusEnum.DOING;
            break;
            case NOT_STARTED: statusEnum = TaskResponse.StatusEnum.NOT_STARTED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + status );
        }

        return statusEnum;
    }
}
