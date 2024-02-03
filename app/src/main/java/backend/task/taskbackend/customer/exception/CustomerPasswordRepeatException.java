package backend.task.taskbackend.customer.exception;

public class CustomerPasswordRepeatException extends RuntimeException{
    public <T> CustomerPasswordRepeatException() {
        super("Passwords are not the same");
    }
}
