package backend.task.taskbackend.task.validation;

import backend.task.taskbackend.task.dto.TaskCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Date;

public class DateDeadline implements ConstraintValidator<DateDeadlineValidation, Date> {
    @Override
    public void initialize(DateDeadlineValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date deadline, ConstraintValidatorContext context) {
        if(deadline == null){
            return false;
        }
        return !deadline.before(new Date());
    }
}
