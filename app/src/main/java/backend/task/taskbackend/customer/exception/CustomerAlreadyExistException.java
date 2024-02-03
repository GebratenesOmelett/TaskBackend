package backend.task.taskbackend.customer.exception;

public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(String email){
        super("Customer with that email already exists: " + email);
    }
}
