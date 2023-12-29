package pt.com.francisco.interfaceAdapters.dataAccess;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import pt.com.francisco.entities.Status;

import java.time.LocalDateTime;

@Entity
public class TaskDbEntity {
    @Id
    private long id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime finishedDate;
}
