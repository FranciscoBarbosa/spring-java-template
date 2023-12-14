package pt.com.francisco.launchers.springBoot.dataAccess;

import pt.com.francisco.entities.Task;
import pt.com.francisco.useCases.databaseGateways.TaskGateway;

import java.util.List;
import java.util.Optional;

public class TaskGatewayImpl implements TaskGateway {
    @Override
    public void create(Task task) {

    }

    @Override
    public Optional<Task> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Task> getAll() {
        return null;
    }
}
