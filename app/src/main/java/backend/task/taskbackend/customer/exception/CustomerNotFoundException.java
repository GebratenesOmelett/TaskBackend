package backend.task.taskbackend.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
    public <T> CustomerNotFoundException(T notFound) {
        super("There is no customer with : " + notFound);
    }
}
