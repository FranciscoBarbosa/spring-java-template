package useCases.interactorPort;

import Repository.TaskRepository;
import entities.Task;
import lombok.RequiredArgsConstructor;
import services.TaskService;
import useCases.inputPort.TaskUseCase;

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
