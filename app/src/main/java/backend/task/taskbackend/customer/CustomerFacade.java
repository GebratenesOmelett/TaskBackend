package backend.task.taskbackend.customer;

import backend.task.taskbackend.config.dto.AuthenticationResponse;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerFacade {
    private final CustomerRepository customerRepository;
    CustomerFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public AuthenticationResponse save(CustomerCreateDto customerCreateDto){
        return customerRepository.save()
    }
}
