package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.CustomerFacade;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TaskControllerTest {

    private final CustomerFacade customerFacade;
    @Autowired
    TaskControllerTest(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
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
    @DisplayName("saveShouldReturnTask")
    void saveTask(){

    }
}
