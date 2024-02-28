package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.exception.CustomerValidationException;
import backend.task.taskbackend.task.dto.TaskCreateDto;
import backend.task.taskbackend.task.dto.TaskDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
class TaskController {

    private final TaskFacade taskFacade;
    TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @PostMapping()
    ResponseEntity<TaskDto> save(@RequestBody @Valid TaskCreateDto taskCreateDto, BindingResult errors){
        if(errors.hasErrors()){
            throw new CustomerValidationException(errors);
        }
        return new ResponseEntity<>(taskFacade.save(taskCreateDto), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    ResponseEntity<List<TaskDto>> get(@PathVariable String email){
        return new ResponseEntity<>(taskFacade.getAllTasks(email), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<TaskDto> delete(@PathVariable int id){
        return new ResponseEntity<>(taskFacade.delete(id), HttpStatus.OK);
    }

}
