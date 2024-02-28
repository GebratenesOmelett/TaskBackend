package backend.task.taskbackend.customer;

import backend.task.taskbackend.config.dto.AuthenticationResponse;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import backend.task.taskbackend.customer.dto.CustomerLoginDto;
import backend.task.taskbackend.customer.exception.CustomerValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
class CustomerController {
    private final CustomerFacade customerFacade;

    CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @PostMapping
    ResponseEntity<AuthenticationResponse> create(@RequestBody @Valid CustomerCreateDto customerCreateDto, BindingResult errors){
        if(errors.hasErrors()){
            throw new CustomerValidationException(errors);
        }
        return new ResponseEntity<>(customerFacade.save(customerCreateDto), HttpStatus.OK);
    }
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody CustomerLoginDto customerLoginDto){
        return new ResponseEntity<>(customerFacade.login(customerLoginDto), HttpStatus.OK);
    }
}
