package backend.task.taskbackend.customer.dto;

import lombok.Getter;

@Getter
public class CustomerLoginDto {
    private final String email;
    private final String password;
    CustomerLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
