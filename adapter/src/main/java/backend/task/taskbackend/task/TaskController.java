package backend.task.taskbackend.task;

import backend.task.taskbackend.task.dto.TaskCreateDto;
import backend.task.taskbackend.task.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class TaskController {

    private final TaskFacade taskFacade;
    TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @PostMapping()
    ResponseEntity<TaskDto> save(TaskCreateDto taskCreateDto){
        return new ResponseEntity<>(taskFacade.save(taskCreateDto), HttpStatus.OK);
    }
}
