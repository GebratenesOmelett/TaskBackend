package backend.task.taskbackend.customer;

import backend.task.taskbackend.config.JwtService;
import backend.task.taskbackend.config.dto.AuthenticationResponse;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import backend.task.taskbackend.customer.dto.CustomerLoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerFacade {
    private final CustomerRepository customerRepository;
    private final CustomerQueryRepository customerQueryRepository;
    private final CustomerFactory customerFactory;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    CustomerFacade(CustomerRepository customerRepository, CustomerQueryRepository customerQueryRepository, CustomerFactory customerFactory, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.customerQueryRepository = customerQueryRepository;
        this.customerFactory = customerFactory;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse save(CustomerCreateDto customerCreateDto){
        Customer customer = customerFactory.from(customerCreateDto);
        customerRepository.save(customer);
        var jwtToken  = jwtService.generateToken(customer.getSnapshot());
        return new AuthenticationResponse(jwtToken, customer.getSnapshot().getEmail(), Integer.toString(JwtService.expiration));
    }
    public AuthenticationResponse login(CustomerLoginDto customerLoginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        customerLoginDto.getEmail(),
                        customerLoginDto.getPassword()
                )
        );
        CustomerSnapshot customer = getCustomerSnapshotByEmail(customerLoginDto.getEmail());
        var jwtToken = jwtService.generateToken(customer);
        return new AuthenticationResponse(jwtToken, customer.getEmail(), Integer.toString(JwtService.expiration));
    }
    public CustomerSnapshot getCustomerSnapshotByEmail(String email){
        return customerQueryRepository.findCustomerSnapshotByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Not found"));
    }
}
