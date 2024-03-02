package backend.task.taskbackend.task;

import backend.task.taskbackend.config.dto.AuthenticationResponse;
import backend.task.taskbackend.customer.CustomerFacade;
import backend.task.taskbackend.customer.dto.CustomerCreateDto;
import backend.task.taskbackend.customer.dto.CustomerLoginDto;
import backend.task.taskbackend.task.dto.TaskCreateDto;
import backend.task.taskbackend.task.exception.TaskNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TaskControllerTest {

    private final CustomerFacade customerFacade;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final TaskFacade taskFacade;

    @Autowired
    TaskControllerTest(CustomerFacade customerFacade, MockMvc mockMvc, ObjectMapper objectMapper, TaskFacade taskFacade) {
        this.customerFacade = customerFacade;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.taskFacade = taskFacade;
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
    void saveTask() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        TaskCreateDto taskCreateDto = new TaskCreateDto(
                "Test",
                "3",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        this.mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskCreateDto))
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.importance", is("3")))
                .andExpect(jsonPath("$.description", is("TestTestTest")))
                .andExpect(jsonPath("$.creationDate", is(dateFormat.format(new Date()))))
                .andExpect(jsonPath("$.deadLine", is(dateFormat.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)))))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }

    @Test
    @DisplayName("saveShouldReturnDateDeadlineException")
    void saveTaskReturnDeadlineException() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);

        TaskCreateDto taskCreateDto = new TaskCreateDto(
                "Test",
                "3",
                "TestTestTest",
                new Date(),
                "Test@gmail.com"
        );
        this.mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskCreateDto))
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("Deadline has to be later than today", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("saveShouldReturnTitleNullException")
    void saveTaskReturnTitleNullException() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);

        TaskCreateDto taskCreateDtoEmpty = new TaskCreateDto(
                null,
                "3",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        TaskCreateDto taskCreateDtoBlank = new TaskCreateDto(
                "     ",
                "3",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        this.mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskCreateDtoEmpty))
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("title must not be empty", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

        this.mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskCreateDtoBlank))
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("title must not be empty", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("saveShouldReturnImportanceNullException")
    void saveTaskReturnImportanceNullException() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);

        TaskCreateDto taskCreateDtoEmpty = new TaskCreateDto(
                "Test",
                null,
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        TaskCreateDto taskCreateDtoBlank = new TaskCreateDto(
                "Test",
                "",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        this.mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskCreateDtoEmpty))
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("importance must not be empty", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();

        this.mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskCreateDtoBlank))
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("importance must not be empty", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    @DisplayName("getShouldReturnListOfTasks")
    void getListTasks() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        TaskCreateDto taskCreateDto = new TaskCreateDto(
                "Test",
                "3",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        TaskCreateDto taskCreateDto2 = new TaskCreateDto(
                "Test2",
                "4",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );

        taskFacade.save(taskCreateDto);
        taskFacade.save(taskCreateDto2);

        Assertions.assertNotNull(taskFacade.getTaskSnapshotById(1));
        Assertions.assertNotNull(taskFacade.getTaskSnapshotById(2));

        this.mockMvc.perform(get("/api/tasks/{email}", customerLoginDto.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("Test2")))
                .andExpect(jsonPath("$[0].importance", is("4")))
                .andExpect(jsonPath("$[0].description", is("TestTestTest")))
                .andExpect(jsonPath("$[0].creationDate", is(dateFormat.format(new Date()))))
                .andExpect(jsonPath("$[0].deadLine", is(dateFormat.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)))))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].title", is("Test")))
                .andExpect(jsonPath("$[1].importance", is("3")))
                .andExpect(jsonPath("$[1].description", is("TestTestTest")))
                .andExpect(jsonPath("$[1].creationDate", is(dateFormat.format(new Date()))))
                .andExpect(jsonPath("$[1].deadLine", is(dateFormat.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)))))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }
    @Test
    @DisplayName("getShouldReturnCustomerNotFoundException")
    void getListTasksReturnNotFoundException() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);

        this.mockMvc.perform(get("/api/tasks/{email}", "WrongEmail@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("There is no customer with : WrongEmail@gmail.com", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }
    @Test
    @DisplayName("deleteTaskReturnTask")
    void deleteTask() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        TaskCreateDto taskCreateDto = new TaskCreateDto(
                "Test",
                "3",
                "TestTestTest",
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
                "Test@gmail.com"
        );
        taskFacade.save(taskCreateDto);

        Assertions.assertNotNull(taskFacade.getTaskSnapshotById(1));

        this.mockMvc.perform(delete("/api/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.importance", is("3")))
                .andExpect(jsonPath("$.description", is("TestTestTest")))
                .andExpect(jsonPath("$.creationDate", is(dateFormat.format(new Date()))))
                .andExpect(jsonPath("$.deadLine", is(dateFormat.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)))))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Assertions.assertThrows(TaskNotFoundException.class, () -> taskFacade.getTaskSnapshotById(1));
    }

    @Test
    @DisplayName("deleteTaskReturnTaskNotFoundException")
    void deleteTaskReturnNotFoundException() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto(
                "Test@gmail.com",
                "Testtest"
        );
        AuthenticationResponse login = customerFacade.login(customerLoginDto);

        Assertions.assertThrows(TaskNotFoundException.class, () -> taskFacade.getTaskSnapshotById(1));

        this.mockMvc.perform(delete("/api/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + login.token()))
                .andExpect(result -> Assertions.assertEquals("Task with that id does not exist: 1", result.getResolvedException().getMessage()))
                .andExpect(status().is4xxClientError()).andReturn();
    }

}
