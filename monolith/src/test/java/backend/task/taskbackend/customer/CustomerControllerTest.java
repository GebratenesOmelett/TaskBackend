package backend.task.taskbackend.customer;

import backend.task.taskbackend.config.dto.AuthenticationResponse;
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
    void init(){
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
          "Test@gmail.com",
          "Testtest",
          "Testtest"
        );
    }

    @Test
    @DisplayName("createShouldReturnCustomer")
    void createCustomer() throws Exception{
        CustomerCreateDto customerCreateDto = new CustomerCreateDto(
                "Test2@gmail.com",
                "Testtest",
                "Testtest"
        );
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(objectMapper.writeValueAsString(customerCreateDto)))
                .andExpect(jsonPath("$.email", is("Test2@gmail.com")))
                .andExpect(status().is2xxSuccessful()).andReturn();

    }
}
