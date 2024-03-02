package backend.task.taskbackend.customer;


import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import backend.task.taskbackend.customer.dto.CustomerLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CustomerControllerTest {

    private final CustomerFacade customerFacade;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    CustomerControllerTest(CustomerFacade customerFacade, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.customerFacade = customerFacade;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void init() {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test@gmail.com",
                "Testtest",
                "Testtest"
        );
        customerFacade.save(customerCreateDto);
    }

    @Test
    @DisplayName("createShouldReturnCustomer")
    void createCustomer() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test2@gmail.com",
                "Testtest",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(jsonPath("$.email", is("Test2@gmail.com")))
                .andExpect(status().is2xxSuccessful()).andReturn();

    }

    @Test
    @DisplayName("createShouldReturnEmailValidationException")
    void createCustomerReturnEmailException() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test2",
                "Testtest",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Email is not valid", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

    }
    @Test
    @DisplayName("createShouldReturnEmailValidationException")
    void createCustomerReturnEmailExceptionEmpty() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                null,
                "Testtest",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Email is not valid", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

    }

    @Test
    @DisplayName("createShouldReturnPasswordValidationException")
    void createCustomerReturnPasswordException() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test2@gmail.com",
                "Test",
                "Test"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Password must be at least 8 characters long", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

    }

    @Test
    @DisplayName("createShouldReturnPasswordReturnValidationException")
    void createCustomerReturnPasswordReturnException() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test2@gmail.com",
                "Testtest",
                "Test"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Passwords are not the same", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

    }

    @Test
    @DisplayName("createShouldReturnCustomerAlreadyExistException")
    void createCustomerReturnAlreadyExistException() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test@gmail.com",
                "Testtest",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(result -> Assertions.assertEquals("Customer with that email already exists: " + customerCreateDto.getEmail(), result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

    }
    @Test
    @DisplayName("loginShouldReturnCustomer")
    void loginCustomer() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerLoginDto)))
                .andExpect(jsonPath("$.email", is("Test@gmail.com")))
                .andExpect(status().is2xxSuccessful()).andReturn();

    }
    @Test
    @DisplayName("loginShouldReturnCustomerLoginExceptionEmail")
    void loginCustomerReturnLoginExceptionEmail() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "WrongTest@gmail.com",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerLoginDto)))
                .andExpect(status().isForbidden()).andReturn();

    }
    @Test
    @DisplayName("loginShouldReturnCustomerLoginExceptionPassword")
    void loginCustomerReturnLoginExceptionPassword() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "WrongPassword"
        );
        mockMvc.perform(post("/api/customers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerLoginDto)))
                .andExpect(status().isForbidden()).andReturn();

    }
}
