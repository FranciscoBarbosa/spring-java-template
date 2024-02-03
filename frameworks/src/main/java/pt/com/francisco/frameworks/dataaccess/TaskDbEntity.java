package pt.com.francisco.frameworks.dataaccess;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import pt.com.francisco.entities.Status;

@Entity
@Getter
@Setter
public class TaskDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime finishedDate;
}
