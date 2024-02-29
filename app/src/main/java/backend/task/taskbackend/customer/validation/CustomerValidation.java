package backend.task.taskbackend.customer.validation;

import backend.task.taskbackend.customer.CustomerQueryRepository;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import backend.task.taskbackend.customer.dto.CustomerLoginDto;
import backend.task.taskbackend.customer.exception.CustomerAlreadyExistException;
import backend.task.taskbackend.customer.exception.CustomerPasswordRepeatException;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {
    private final CustomerQueryRepository customerQueryRepository;
    CustomerValidation(CustomerQueryRepository customerQueryRepository) {
        this.customerQueryRepository = customerQueryRepository;
    }
    public void registerValidation(CustomerCreateDto customer){
        if(!customer.getPassword().equals(customer.getPasswordRepeat())){
            throw new CustomerPasswordRepeatException();
        }
        if(customerQueryRepository.findCustomerSnapshotByEmail(customer.getEmail()).isPresent()){
            throw new CustomerAlreadyExistException(customer.getEmail());
        }
    }
}
