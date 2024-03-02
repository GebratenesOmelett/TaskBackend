package backend.task.taskbackend.customer.exception;

public class CustomerPasswordRepeatException extends RuntimeException{
    public CustomerPasswordRepeatException() {
        super("Passwords are not the same");
    }
}
