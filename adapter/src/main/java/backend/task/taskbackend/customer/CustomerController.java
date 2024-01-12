package backend.task.taskbackend.customer;

import backend.task.taskbackend.config.dto.AuthenticationResponse;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import backend.task.taskbackend.customer.dto.CustomerLoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<AuthenticationResponse> create(@RequestBody CustomerCreateDto customerCreateDto){
        return new ResponseEntity<>(customerFacade.save(customerCreateDto), HttpStatus.OK);
    }
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody CustomerLoginDto customerLoginDto){
        return new ResponseEntity<>(customerFacade.login(customerLoginDto), HttpStatus.OK);
    }
}
