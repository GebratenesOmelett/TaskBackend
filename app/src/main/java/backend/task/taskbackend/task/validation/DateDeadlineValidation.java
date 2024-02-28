package backend.task.taskbackend.task.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateDeadline.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDeadlineValidation {
    String message() default "Deadline has to be later than today";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}
