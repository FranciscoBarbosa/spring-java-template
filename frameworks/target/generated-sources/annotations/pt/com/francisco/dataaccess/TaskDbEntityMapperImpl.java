package pt.com.francisco.dataaccess;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.com.francisco.entities.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T00:16:06+0000",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class TaskDbEntityMapperImpl implements TaskDbEntityMapper {

    @Override
    public Task toTask(TaskDbEntity taskDbEntity) {
        if ( taskDbEntity == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDbEntity.getId() );
        task.setName( taskDbEntity.getName() );
        task.setDescription( taskDbEntity.getDescription() );
        task.setStatus( taskDbEntity.getStatus() );
        task.setStartDate( taskDbEntity.getStartDate() );
        task.setFinishedDate( taskDbEntity.getFinishedDate() );

        return task;
    }

    @Override
    public TaskDbEntity toTaskDbEntity(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDbEntity taskDbEntity = new TaskDbEntity();

        taskDbEntity.setId( task.getId() );
        taskDbEntity.setName( task.getName() );
        taskDbEntity.setDescription( task.getDescription() );
        taskDbEntity.setStatus( task.getStatus() );
        taskDbEntity.setStartDate( task.getStartDate() );
        taskDbEntity.setFinishedDate( task.getFinishedDate() );

        return taskDbEntity;
    }
}
