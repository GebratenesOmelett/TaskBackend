package backend.task.taskbackend.customer;

import backend.task.taskbackend.customer.exception.CustomerAlreadyExistException;
import backend.task.taskbackend.customer.exception.CustomerNotFoundException;
import backend.task.taskbackend.customer.exception.CustomerPasswordRepeatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    ResponseEntity<Object> CustomerNotFound(RuntimeException ex, WebRequest request) {
        String response = ex.getMessage();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = CustomerAlreadyExistException.class)
    ResponseEntity<Object> CustomerEmailAlreadyExist(RuntimeException ex, WebRequest request) {
        String response = ex.getMessage();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = CustomerPasswordRepeatException.class)
    ResponseEntity<Object> CustomerPasswordRepeatException(RuntimeException ex, WebRequest request) {
        String response = ex.getMessage();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
