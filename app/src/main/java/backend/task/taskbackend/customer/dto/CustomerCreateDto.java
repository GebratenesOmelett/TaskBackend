package backend.task.taskbackend.customer.dto;

import lombok.Getter;
@Getter
public class CustomerCreateDto {
    private final String email;
    private final String password;
    CustomerCreateDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
