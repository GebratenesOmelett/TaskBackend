package backend.task.taskbackend.task.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class TaskValidationException extends RuntimeException{
    public TaskValidationException(BindingResult errors){
        super(errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList().get(0));
    }
}
