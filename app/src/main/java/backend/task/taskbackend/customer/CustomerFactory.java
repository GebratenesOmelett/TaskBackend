package backend.task.taskbackend.customer;

import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class CustomerFactory {
    private final PasswordEncoder encoder;
    CustomerFactory(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    Customer from(CustomerCreateDto customerCreateDto){
        return Customer.restore(CustomerSnapshot.builder()
                        .email(customerCreateDto.getEmail())
                        .password(encoder.encode(customerCreateDto.getPassword()))
                .build());
    }
}
