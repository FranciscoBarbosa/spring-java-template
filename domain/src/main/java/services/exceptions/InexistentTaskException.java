package services.exceptions;

public class InexistentTaskException extends RuntimeException{
    public InexistentTaskException(int id) {
        super(String.format("The task with Id %s does not exist", id));
    }
}
