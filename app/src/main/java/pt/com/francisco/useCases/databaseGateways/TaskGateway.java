package pt.com.francisco.useCases.databaseGateways;

import org.springframework.stereotype.Repository;
import pt.com.francisco.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskGateway {
        void create(Task task);
        Optional<Task> get(int id);
        void update(Task task);
        void delete(int id);
        List<Task> getAll();
}
