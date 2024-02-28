package backend.task.taskbackend.customer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class CustomerCreateDto {
    @Email(message = "Email is not valid")
    private final String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private final String password;
    private final String passwordRepeat;
    CustomerCreateDto(String email, String password, String passwordRepeat) {
        this.email = email;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

}
