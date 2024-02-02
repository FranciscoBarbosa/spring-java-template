package pt.com.francisco.web.mappers;

import javax.annotation.processing.Generated;
import org.openapitools.model.TaskRequest;
import org.springframework.stereotype.Component;
import pt.com.francisco.entities.Status;
import pt.com.francisco.entities.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T00:16:06+0000",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class TaskRequestMapperImpl implements TaskRequestMapper {

    @Override
    public Task map(TaskRequest taskRequest) {
        if ( taskRequest == null ) {
            return null;
        }

        Task task = new Task();

        task.setName( taskRequest.getName() );
        task.setDescription( taskRequest.getDescription() );
        task.setStatus( statusEnumToStatus( taskRequest.getStatus() ) );

        return task;
    }

    protected Status statusEnumToStatus(TaskRequest.StatusEnum statusEnum) {
        if ( statusEnum == null ) {
            return null;
        }

        Status status;

        switch ( statusEnum ) {
            case FINISHED: status = Status.FINISHED;
            break;
            case DOING: status = Status.DOING;
            break;
            case NOT_STARTED: status = Status.NOT_STARTED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + statusEnum );
        }

        return status;
    }
}
