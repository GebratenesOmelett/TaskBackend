package backend.task.taskbackend.task.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(int id){
        super("Task with that id does not exist: " + id);
    }
}
