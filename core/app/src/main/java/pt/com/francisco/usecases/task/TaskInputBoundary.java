package pt.com.francisco.usecases.task;

public interface TaskInputBoundary<I, O> {

    I execute(O task);
}
