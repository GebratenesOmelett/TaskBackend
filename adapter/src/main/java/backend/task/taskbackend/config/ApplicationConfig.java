package backend.task.taskbackend.config;

import backend.task.taskbackend.customer.CustomerQueryRepository;
import backend.task.taskbackend.customer.exception.CustomerLoginException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

@Configuration
public class ApplicationConfig {
    private final CustomerQueryRepository customerQueryRepository;

    ApplicationConfig(CustomerQueryRepository customerQueryRepository) {
        this.customerQueryRepository = customerQueryRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> customerQueryRepository.findCustomerSnapshotByEmail(username)
                .orElseThrow(CustomerLoginException::new);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(false)
                .timeZone(TimeZone.getDefault())
                .build();
    }
}
