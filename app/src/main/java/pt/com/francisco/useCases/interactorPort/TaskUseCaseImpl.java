package pt.com.francisco.useCases.interactorPort;

import pt.com.francisco.repository.TaskRepository;
import pt.com.francisco.entities.Task;
import lombok.RequiredArgsConstructor;
import pt.com.francisco.services.TaskService;
import pt.com.francisco.useCases.inputPort.TaskUseCase;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskUseCaseImpl implements TaskUseCase {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        taskRepository.create(task);
    }

    @Override
    public void updateTask(Task task) {
        taskService.updateTask(task);
    }
    @Override
    public void completeTask(int id) {
        taskService.completeTask(id);
    }
    @Override
    public Optional<Task> getTask(int id) {
        return taskRepository.get(id);
    }
    @Override
    public List<Task> listTasks() {
        return taskRepository.getAll();
    }
    @Override
    public void removeTask(int id) {
        taskService.removeTask(id);
    }
}
