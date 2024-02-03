package backend.task.taskbackend.customer.dto;

import lombok.Getter;
@Getter
public class CustomerCreateDto {
    private final String email;
    private final String password;
    private final String passwordRepeat;
    CustomerCreateDto(String email, String password, String passwordRepeat) {
        this.email = email;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

}
